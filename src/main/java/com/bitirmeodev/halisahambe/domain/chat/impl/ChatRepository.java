package com.bitirmeodev.halisahambe.domain.chat.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,String> {
    Optional<Chat> findByEventId(String eventId);
}
