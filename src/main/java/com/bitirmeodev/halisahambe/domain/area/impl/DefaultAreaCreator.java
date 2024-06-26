package com.bitirmeodev.halisahambe.domain.area.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultAreaCreator {
    private final AreaRepository repository;
    private final ResourceLoader resourceLoader;

    public static final String TEST_IMAGE = "0x0-dsc-5184-jpg.jpg";

    @EventListener(value = ApplicationReadyEvent.class)
    @Order(2)
    public void createAreas() {
        List<AreaDto> areas = new ArrayList<>();

        areas.add(AreaDto.builder()
                .name("KAFKALE HALI SAHA")
                .streetId("1")
                .districtId("1")
                .photo(loadImage(TEST_IMAGE))
                .build());
        areas.add(AreaDto.builder()
                .name("KAFKALE HALI SAHA 2")
                .streetId("1")
                .districtId("1")
                .photo(loadImage(TEST_IMAGE))
                .build());

        saveAreas(areas);
    }

    public void saveAreas(List<AreaDto> areas) {
        areas.forEach(areaDto -> {
            Optional<Area> area = repository.findAllByName(areaDto.getName())
                    .stream()
                    .findFirst();
            if (area.isEmpty()){
                repository.save(AreaMapper.toEntity(new Area(),areaDto));
            }
        });
    }

    @SneakyThrows
    private byte[] loadImage(String fileName){
        Resource resource = resourceLoader.getResource("classpath:areaImages/" + fileName);
        Path path = Paths.get(resource.getURI());
        return Files.readAllBytes(path);
    }

}
