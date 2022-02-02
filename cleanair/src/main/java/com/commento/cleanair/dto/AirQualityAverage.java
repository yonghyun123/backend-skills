package com.commento.cleanair.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AirQualityAverage {
    String locationName;
    String PM10;
    String grade;
}

