package com.commento.cleanair.domain;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.infrastructure.AirApiCaller;
import com.commento.cleanair.infrastructure.AirApiCallerFactory;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeoulApiService {
    final private AirApiCallerFactory airApiCallerFactory;

    //business logic
    public AirQualityDto.AirQuality getSeoulAirInfo(AirQualitySido sido, AirQualityGu gu){

        //factroy 패턴을 이용한 지역 APICaller 셋팅
        AirApiCaller apiType = airApiCallerFactory.getApiType(sido);
        AirQualityDto.AirQuality airQuality = apiType.getAirQuality();
        if (gu != null) {
            return airQuality.getAirQuality(gu.getDescription());
        }
        return airQuality;
    }
}
