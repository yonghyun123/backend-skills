package com.commento.cleanair.domain;

import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.springframework.stereotype.Component;

@Component
public class SeoulAirQualityReader implements AirQualityReader {
    private final SeoulAirQualityApiCaller caller;
    private final SeoulAirQualityApiDtoMapper mapper;

    public SeoulAirQualityReader(SeoulAirQualityApiCaller caller, SeoulAirQualityApiDtoMapper mapper) {
        this.caller = caller;
        this.mapper = mapper;
    }

    @Override
    public boolean support(AirQualitySido sido) {
        return AirQualitySido.seoul == sido;
    }

    @Override
    public AirQuality airQuality() {
        SeoulAirQualityApiDto.Result result = caller.getAirQuality();
        return mapper.mapToAirQuality(result);
    }
}
