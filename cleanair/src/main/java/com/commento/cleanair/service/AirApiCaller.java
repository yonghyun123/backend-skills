package com.commento.cleanair.service;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;


public interface AirApiCaller {
    AirQualitySido getSidoType();
    MappedAirQuality getAirQuality();
}
