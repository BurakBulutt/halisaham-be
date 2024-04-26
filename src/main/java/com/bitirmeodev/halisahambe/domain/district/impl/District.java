package com.bitirmeodev.halisahambe.domain.district.impl;

import com.bitirmeodev.halisahambe.domain.city.impl.City;
import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class District extends AbstractEntity {
    public static final String TABLE = "district";
    private static final String COL_NAME = "name";
    private static final String COL_CITY_ID = "city_id";

    @Column(nullable = false,name = COL_NAME)
    private String name;

    @Column(nullable = false,name = COL_CITY_ID)
    private String cityId;
}
