package com.commento.cleanair.controller;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.domain.AirQuality;
import com.commento.cleanair.domain.SeoulApiService;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/air-info")
@RequiredArgsConstructor
public class AirQualityApiController {

    private final SeoulApiService seoulApiService;

    @GetMapping("/{sido}")
    public AirQualityDto getAirQuality(@PathVariable AirQualitySido sido,
                                       @RequestParam(required = false) AirQualityGu gu) {
        final AirQuality airQuality = seoulApiService.getSeoulAirInfo(sido, gu);
        return AirQualityDto.builder()
                .sido(airQuality.getSido())
                .sidoPm10Avg(airQuality.getSidoPm10Avg())
                .sidoPm10AvgGrade(airQuality.getSidoPm10AvgGrade())
                .guList(
                        airQuality.getGuList().stream()
                                .map(it -> AirQualityDto.GuAirQuality.builder()
                                        .GU(it.getGU())
                                        .PM10(it.getPM10())
                                        .PM10grade(it.getPM10grade())
                                        .PM25(it.getPM25())
                                        .PM25grade(it.getPM25grade())
                                        .O3(it.getO3())
                                        .O3Grade(it.getO3Grade())
                                        .NO2(it.getNO2())
                                        .NO2Grade(it.getNO2Grade())
                                        .CO(it.getCO())
                                        .COGrade(it.getCOGrade())
                                        .SO2(it.getSO2())
                                        .SO2Grade(it.getSO2Grade())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
