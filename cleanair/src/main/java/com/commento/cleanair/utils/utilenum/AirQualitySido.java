package com.commento.cleanair.utils.utilenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirQualitySido {
    seoul("서울시"),
    busan("부산시");

    private final String description;
}
