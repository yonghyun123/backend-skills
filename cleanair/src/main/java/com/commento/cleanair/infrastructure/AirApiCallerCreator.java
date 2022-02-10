package com.commento.cleanair.infrastructure;

import com.commento.cleanair.dto.AirQualityDto;

public interface AirApiCallerCreator {
    public AirQualityDto.AirQuality getAirQuality();
}
