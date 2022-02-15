package com.commento.cleanair.infrastructure;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;


public interface AirApiCaller {
    AirQualitySido getSidoType();
    AirQualityDto getAirQuality();
}
