package com.commento.cleanair.service;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirQualityApiService {
    final private AirApiCallerFactory airApiCallerFactory;

    //business logic
    public AirQualityDto getSeoulAirInfo(AirQualitySido sido, AirQualityGu gu){

        //factroy 패턴을 이용한 지역 APICaller 셋팅
        AirApiCaller apiType = airApiCallerFactory.getApiType(sido);
        MappedAirQuality airQuality = apiType.getAirQuality();
        if (gu != null) {
            return mappedAirQualityDto(airQuality.getAirQuality(gu.getDescription()));
        }
        return mappedAirQualityDto(airQuality);
    }

    public AirQualityDto mappedAirQualityDto(MappedAirQuality mappedAirQuality) {
        return AirQualityDto.builder()
                .sido(mappedAirQuality.getSido())
                .sidoPm10Avg(mappedAirQuality.getSidoPm10Avg())
                .sidoPm10AvgGrade(mappedAirQuality.getSidoPm10AvgGrade())
                .guList(mappedAirQuality.getGuList()
                        .stream()
                        .map(v -> AirQualityDto.GuAirQuality
                                .builder()
                                .gu(v.getGu())
                                .pm10(v.getPm10())
                                .pm25(v.getPm25())
                                .o3(v.getO3())
                                .so2(v.getSo2())
                                .co(v.getCo())
                                .no2(v.getNo2())
                                .pm10Grade(v.getPm10Grade())
                                .pm25Grade(v.getPm25Grade())
                                .o3Grade(v.getO3Grade())
                                .so2Grade(v.getSo2Grade())
                                .coGrade(v.getCo())
                                .no2Grade(v.getNo2Grade())
                                .build())
                        .collect(Collectors.toList())).build();


    }
}
