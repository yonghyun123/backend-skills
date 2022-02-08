package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor // Constructor Injection
public class SeoulApiService {
    final private SeoulAirQualityApiCaller qualityApiCaller;

    //business logic
    public AirQualityDto.AirQuality getSeoulAirInfo(AirQualitySido sido, AirQualityGu gu){
        AirQualityDto.AirQuality airQuality = qualityApiCaller.getAirQuality();
        if(AirQualitySido.seoul == sido){
            if(gu != null){
                return airQuality.getAirQuality(gu.getDescription());
            }
            return airQuality;
        }

        throw new RuntimeException("아직 준비되지 않은 도시입니다.");
    }
}
