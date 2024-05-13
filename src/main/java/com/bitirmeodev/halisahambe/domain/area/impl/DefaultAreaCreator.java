package com.bitirmeodev.halisahambe.domain.area.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultAreaCreator {
    private final AreaRepository repository;

    @EventListener(value = ApplicationReadyEvent.class)
    @Order(2)
    @SneakyThrows
    public void createAreas() {
        List<AreaDto> areas = new ArrayList<>();

        areas.add(AreaDto.builder()
                .name("KAFKALE HALI SAHA")
                .streetId("1")
                .districtId("1")
                .photo(new FileInputStream(new File("C:\\Users\\lothe\\OneDrive\\Masaüstü\\projectImages\\saha-1.jpg")).readAllBytes())
                .build());
        areas.add(AreaDto.builder()
                        .name("SIÇKALE HALI SAHA")
                        .streetId("1")
                        .districtId("1")
                        .photo(new FileInputStream(new File("C:\\Users\\lothe\\OneDrive\\Masaüstü\\projectImages\\saha-2.jpg")).readAllBytes())
                .build());

        saveAreas(areas);
    }

    public void saveAreas(List<AreaDto> areas){
        areas.forEach(areaDto -> {
            if (repository.findAllByDistrictIdAndStreetId(areaDto.getDistrictId(), areaDto.getStreetId()) == null){
                repository.save(AreaMapper.toEntity(new Area(),areaDto));
            }
        });
    }

}
