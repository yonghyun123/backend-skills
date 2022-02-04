package com.commento.cleanair.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AirQualityAverage {
    String locationName;
    String PM10;
    String grade;
}

