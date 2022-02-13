package com.commento.cleanair.domain;

import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirQualityReaderSelector {
    private final List<AirQualityReader> readers;

    public AirQualityReaderSelector(List<AirQualityReader> readers) {
        this.readers = readers;
    }

    public AirQualityReader selectBy(AirQualitySido sido) {
        return readers.stream()
                .filter(it -> it.support(sido))
                .findFirst()
                .get();
    }
}
