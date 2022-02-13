package com.commento.cleanair.infrastructure;

import com.commento.cleanair.domain.AirQuality;
import com.commento.cleanair.utils.utilenum.AirQualitySido;


public interface AirApiCaller {
    AirQualitySido getSidoType();
    AirQuality getAirQuality();
}
