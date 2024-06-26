package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaService;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserServiceImpl;
import com.bitirmeodev.halisahambe.domain.chat.api.ChatDto;
import com.bitirmeodev.halisahambe.domain.chat.api.ChatService;
import com.bitirmeodev.halisahambe.domain.event.api.EventCreationEvent;
import com.bitirmeodev.halisahambe.domain.event.api.EventDto;
import com.bitirmeodev.halisahambe.domain.event.api.EventService;
import com.bitirmeodev.halisahambe.domain.event.impl.eventuser.EventUser;
import com.bitirmeodev.halisahambe.domain.event.impl.eventuser.EventUserRepository;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "events")
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final EventUserRepository eventUserRepository;
    private final UserServiceImpl userService;
    private final AreaService areaService;
    private final ApplicationEventPublisher publisher;
    private final ChatService chatService;

    @Override
    @Cacheable
    public List<EventDto> getAll() {
        return repository.findAll().stream()
                .map(event -> {
                    List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
                    List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
                    List<UserDto> users = userService.getAllByUserIdIn(userIds);
                    return EventMapper.toDto(event, users, areaService, userService);
                })
                .toList();
    }

    @Override
    public EventDto getById(String id) {
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
        List<UserDto> users = userService.getAllByUserIdIn(userIds);
        return repository.findById(id).map(event -> EventMapper.toDto(event, users, areaService, userService)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), id));
    }

    @Override
    public List<EventDto> getByCityAndDistrictAndStreetAndArea(String cityId, String districtId, String streetId, String areaId) {
        List<EventDto> events = new ArrayList<>(repository.findAllByCityIdAndDistrictIdAndStreetIdAndAreaId(cityId, districtId, streetId, areaId).stream()
                .map(event -> {
                    List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
                    List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
                    List<UserDto> users = userService.getAllByUserIdIn(userIds);
                    return EventMapper.toDto(event, users, areaService, userService);
                })
                .toList());

        events.removeIf(event -> {
            List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
            return eventUserList.stream().anyMatch(eventUser -> eventUser.getUserId().equals(JwtUtil.extractUserId()));
        });

        return events;
    }

    @Override
    public List<EventDto> getByCityAndDistrictAndStreet(String cityId, String districtId, String streetId) {
        List<EventDto> events = new ArrayList<>(repository.findAllByCityIdAndDistrictIdAndStreetId(cityId, districtId, streetId).stream()
                .map(event -> {
                    List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
                    List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
                    List<UserDto> users = userService.getAllByUserIdIn(userIds);
                    return EventMapper.toDto(event, users, areaService, userService);
                })
                .toList());

        events.removeIf(event -> {
            List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
            return eventUserList.stream().anyMatch(eventUser -> eventUser.getUserId().equals(JwtUtil.extractUserId()));
        });
        return events;
    }

    @Override
    //@Cacheable
    public List<EventDto> getUserEvents() {
        String userId = JwtUtil.extractUserId();
        List<EventUser> eventUserList = eventUserRepository.findAllByUserId(userId);
        List<Event> events = repository.findAllById(eventUserList.stream().map(EventUser::getEventId).toList());
        return events.stream().map(event -> {
            List<EventUser> eventUsers = eventUserRepository.findAllByEventId(event.getId());
            List<String> userIds = eventUsers.stream().map(EventUser::getUserId).toList();
            List<UserDto> users = userService.getAllByUserIdIn(userIds);
            return EventMapper.toDto(event, users, areaService, userService);
        }).toList();
    }

    @Override
    @Transactional
    @CachePut(value = "events")
    public EventDto save(EventDto dto) {
        Event event = repository.save(EventMapper.toEntity(new Event(), dto));
        List<EventUser> eventUserList = new ArrayList<>();
        eventUserList.add(new EventUser(dto.getAdminUser().getId(), event.getId()));
        eventUserRepository.saveAll(eventUserList);
        publisher.publishEvent(new EventCreationEvent(event.getId()));
        return EventMapper.toDto(event, List.of(userService.getById(dto.getAdminUser().getId())), areaService, userService);
    }

    @Override
    @Transactional
    public EventDto update(String id, EventDto dto) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), id));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        List<UserDto> userDtos = userService.getAllByUserIdIn(eventUserList.stream().map(EventUser::getUserId).toList());

        return EventMapper.toDto(repository.save(EventMapper.toEntity(event, dto)), userDtos, areaService, userService);
    }

    @Override
    @Transactional
    public void deleteUserOnEvent(String eventId, String userId) {
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), eventId));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());

        eventUserList.stream()
                .filter(eventUser -> eventUser.getUserId().equals(userId))
                .findFirst()
                .ifPresentOrElse(eventUser -> {
                    eventUserList.remove(eventUser);
                    eventUserRepository.delete(eventUser);
                }, () -> {
                    throw new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), eventId);
                });
    }

    @Override
    @Transactional
    public void exitOnEvent(String eventId) {
        String userId = JwtUtil.extractUserId();
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), eventId));

        if (eventUserRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            eventUserRepository.delete(eventUserRepository.findByUserIdAndEventId(userId, eventId).get());
        }

        if (event.getUserId().equals(userId)) {
            List<EventUser> eventUserList = eventUserRepository.findAllByEventId(eventId);
            repository.delete(event);
            eventUserRepository.deleteAll(eventUserList);
        }
    }

    @Override
    @Transactional
    public EventDto joinEvent(String eventId) {
        String userId = JwtUtil.extractUserId();
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), eventId));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
        eventUserList.stream()
                .filter(eventUser -> eventUser.getUserId().equals(event.getUserId()))
                .findFirst()
                .ifPresent(eventUserList::remove);

        if (event.getMaxPeople().equals(eventUserList.size())){
            throw new BaseException(MessageCodes.FAIL);
        }

        if (eventUserRepository.findByUserIdAndEventId(userId, eventId).isPresent()) {
            throw new BaseException(MessageCodes.ENTITY_ALREADY_EXISTS);
        }
        eventUserList.add(eventUserRepository.save(new EventUser(userId, eventId)));
        List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
        List<UserDto> users = userService.getAllByUserIdIn(userIds);
        return EventMapper.toDto(event, users, areaService, userService);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), id));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        repository.delete(event);
        eventUserRepository.deleteAll(eventUserList);
    }

    @Override
    public Boolean checkEventAuthority(String id) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Event.class.getSimpleName(), id));
        UserDto user = userService.getById(JwtUtil.extractUserId());

        if (event.getUserId().equals(user.getId())) {
            return true;
        }
        return false;
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eventCreationEvent(EventCreationEvent event) {
        chatService.save(ChatDto.builder()
                .eventId(event.eventId())
                .messages(new ArrayList<>())
                .build());
    }
}
