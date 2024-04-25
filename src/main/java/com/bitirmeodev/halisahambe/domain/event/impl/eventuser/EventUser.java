package com.bitirmeodev.halisahambe.domain.event.impl.eventuser;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUser extends AbstractEntity {

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String eventId;


}
