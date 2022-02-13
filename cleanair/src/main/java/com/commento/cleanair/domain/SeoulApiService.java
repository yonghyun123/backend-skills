package com.commento.cleanair.domain;

import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeoulApiService {
    final private AirQualityReaderSelector readerSelector;

    //business logic
    public AirQuality getSeoulAirInfo(AirQualitySido sido, AirQualityGu gu){
        AirQualityReader reader = readerSelector.selectBy(sido);
        AirQuality airQuality = reader.airQuality();
        if (gu != null) {
            return airQuality.getAirQuality(gu.getDescription());
        }
        return airQuality;
    }
}
