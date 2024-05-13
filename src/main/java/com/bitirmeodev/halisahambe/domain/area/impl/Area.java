package com.bitirmeodev.halisahambe.domain.area.impl;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
@Table(name = Area.TABLE)
public class Area extends AbstractEntity {
    public static final String TABLE = "area";
    private static final String COL_NAME = "name";
    private static final String COL_DISTRICT = "district_id";
    private static final String COL_STREET = "street_id";
    private static final String COL_PHOTO = "photo";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_DISTRICT)
    private String districtId;

    @Column(name = COL_STREET)
    private String streetId;

    @Column(name = COL_PHOTO)
    @Lob
    private byte[] photo;
}
