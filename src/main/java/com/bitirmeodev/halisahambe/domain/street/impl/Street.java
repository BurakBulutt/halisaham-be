package com.bitirmeodev.halisahambe.domain.street.impl;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Street.TABLE)
public class Street extends AbstractEntity {
    public static final String TABLE = "district";
    private static final String COL_NAME = "name";
    private static final String COL_DISTRICT_ID = "city_id";

    @Column(nullable = false,name = COL_NAME)
    private String name;

    @Column(nullable = false,name = COL_DISTRICT_ID)
    private String districtId;
}
