package com.commento.cleanair.domain;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;

public interface AirQualityReader {
    boolean support(AirQualitySido sido);
    AirQualityDto.AirQuality airQuality();
}
