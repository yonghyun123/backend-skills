package com.commento.cleanair.infrastructure;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.springframework.stereotype.Component;


public interface AirApiCaller {
    AirQualitySido getSidoType();
    AirQualityDto.AirQuality getAirQuality();
}
