package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.domain.event.api.EventDto;
import com.bitirmeodev.halisahambe.domain.event.api.EventService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;

    @Override
    public List<EventDto> getAll() {
        return repository.findAll().stream()
                .map(EventMapper::toDto)
                .toList();
    }

    @Override
    public List<EventDto> getAllByUserId(String id){
        return repository.findAllByUserId(id).stream()
                .map(EventMapper::toDto)
                .toList();
    }

    @Override
    public EventDto getById(String id) {
        return repository.findById(id).map(EventMapper::toDto).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));
    }

    @Override
    public EventDto save(EventDto dto) {
        return EventMapper.toDto(repository.save(EventMapper.toEntity(new Event(),dto)));
    }

    @Override
    public EventDto update(String id, EventDto dto) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));

        return EventMapper.toDto(repository.save(EventMapper.toEntity(event,dto)));
    }

    @Override
    public void delete(String id) {
        Event event = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Event.class.getSimpleName(),id));
        repository.delete(event);
    }
}
