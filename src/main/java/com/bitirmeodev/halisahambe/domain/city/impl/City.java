package com.bitirmeodev.halisahambe.domain.city.impl;

import com.bitirmeodev.halisahambe.domain.district.impl.District;
import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class City extends AbstractEntity {
    public static final String TABLE = "city";
    private static final String COL_CODE = "code";
    private static final String COL_NAME = "name";

    @Column(nullable = false,name =COL_CODE,unique = true)
    private String code;
    @Column(nullable = false,name = COL_NAME)
    private String name;


}
