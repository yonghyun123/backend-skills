package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor // Constructor Injection
public class SeoulApiService {
    final private SeoulAirQualityApiCaller qualityApiCaller;

    //business logic
    public AirQualityDto.AirQuality getSeoulAirInfo(String sido, String gu){
        AirQualityDto.AirQuality airQuality = qualityApiCaller.getAirQuality();
        return airQuality;
    }
}
