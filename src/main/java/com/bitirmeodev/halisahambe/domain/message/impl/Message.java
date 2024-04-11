package com.bitirmeodev.halisahambe.domain.message.impl;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Message extends AbstractEntity {
    private String userId;
    private String chatId;
    private String message;
    private Date messageDate;
}
