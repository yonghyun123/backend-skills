package com.commento.cleanair.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ParticularAirQuality {
    String locationName;
    String PM10;
    String PM10grade;
    String PM25;
    String PM25grade;
    String O3;
    String O3Grade;
    String NO2;
    String NO2Grade;
    String CO;
    String COGrade;
    String SO2;
    String SO2Grade;
}
