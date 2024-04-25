package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserServiceImpl;
import com.bitirmeodev.halisahambe.domain.event.api.EventDto;
import com.bitirmeodev.halisahambe.domain.event.api.EventService;
import com.bitirmeodev.halisahambe.domain.event.impl.eventuser.EventUser;
import com.bitirmeodev.halisahambe.domain.event.impl.eventuser.EventUserRepository;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final EventUserRepository eventUserRepository;
    private final UserServiceImpl userService;

    @Override
    public List<EventDto> getAll() {
        return repository.findAll().stream()
                .map(event -> {
                    List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());
                    List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
                    List<UserDto> users = userService.getAllByUserIdIn(userIds);
                    return EventMapper.toDto(event,users);
                })
                .toList();
    }

    @Override
    public EventDto getById(String id) {
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        List<String> userIds = eventUserList.stream().map(EventUser::getUserId).toList();
        List<UserDto> users = userService.getAllByUserIdIn(userIds);
        return repository.findById(id).map(event -> EventMapper.toDto(event,users)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));
    }

    @Override
    public List<EventDto> getUserEvents() {
        String userId = JwtUtil.extractUserId();
        List<EventUser> eventUserList = eventUserRepository.findAllByUserId(userId);
        List<Event> events = repository.findAllById(eventUserList.stream().map(EventUser::getEventId).toList());
        return events.stream().map(event -> EventMapper.toDto(event,null)).toList();
    }


    @Override
    @Transactional
    public EventDto save(EventDto dto) {
        Event event = repository.save(EventMapper.toEntity(new Event(),dto));
        List<EventUser> eventUserList = new ArrayList<>();
        eventUserList.add(new EventUser(dto.getUserId(),event.getId()));
        eventUserRepository.saveAll(eventUserList);
        return EventMapper.toDto(event,List.of(userService.getById(dto.getUserId())));
    }

    @Override
    @Transactional
    public EventDto update(String id, EventDto dto) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        List<UserDto> userDtos = userService.getAllByUserIdIn(eventUserList.stream().map(EventUser::getUserId).toList());

        return EventMapper.toDto(repository.save(EventMapper.toEntity(event,dto)),userDtos);
    }

    @Override
    @Transactional
    public void deleteUserOnEvent(String eventId, String userId) {
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),eventId));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());

        eventUserList.stream()
                .filter(eventUser -> eventUser.getUserId().equals(userId))
                .findFirst()
                .ifPresentOrElse(eventUser -> {
                    eventUserList.remove(eventUser);
                    eventUserRepository.delete(eventUser);
                },()-> {
                    throw new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),eventId);
                });
    }

    @Override
    @Transactional
    public void exitOnEvent(String eventId) {
        String userId = JwtUtil.extractUserId();
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),eventId));

        eventUserRepository.delete(eventUserRepository.findByUserIdAndEventId(userId,eventId));

        if (event.getUserId().equals(userId)) {
            List<EventUser> eventUserList = eventUserRepository.findAllByEventId(eventId);
            repository.delete(event);
            eventUserRepository.deleteAll(eventUserList);
        }
    }

    @Override
    @Transactional
    public void joinEvent(String eventId) {
        String userId = JwtUtil.extractUserId();
        Event event = repository.findById(eventId).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),eventId));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(event.getId());

        eventUserList.add(eventUserRepository.save(new EventUser(userId,eventId)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));
        List<EventUser> eventUserList = eventUserRepository.findAllByEventId(id);
        repository.delete(event);
        eventUserRepository.deleteAll(eventUserList);
    }
}
