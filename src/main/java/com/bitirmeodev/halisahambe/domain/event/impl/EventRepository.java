package com.bitirmeodev.halisahambe.domain.event.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,String> {
    List<Event> findAllByUserId(String userId);
}
