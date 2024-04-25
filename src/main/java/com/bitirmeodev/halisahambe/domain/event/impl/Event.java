package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Event extends AbstractEntity {
    public static final String TABLE = "event";
    private static final String COL_EXPIRATION_DATE = "expiration_date";
    private static final String COL_CITY = "city";
    private static final String COL_DISTRICT = "disrict";
    private static final String COL_STREET = "street";
    private static final String COL_MAX_PEOPLE = "max_people";
    private static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_PHOTO = "photo";
    private static final String COL_USER_ID = "user_id";

    @Column(name = COL_EXPIRATION_DATE)
    private Date expirationDate;

    @Column(name = COL_CITY)
    private String city;

    @Column(name = COL_DISTRICT)
    private String district;

    @Column(name = COL_STREET)
    private String street;

    @Column(name = COL_MAX_PEOPLE)
    private Integer maxPeople;

    @Column(name = COL_TITLE)
    private String title;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column(name = COL_PHOTO)
    @Lob
    private byte[] photo;

    @Column(name = COL_USER_ID,nullable = false,updatable = false)
    private String userId;
}
