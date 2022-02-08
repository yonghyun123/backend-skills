package com.commento.cleanair.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class AirQualityDto {

    @Getter
    @Builder
    @ToString
    public static class AirQuality{
        String sido;
        String sidoPm10Avg;
        String sidoPm10AvgGrade;
        List<GuAirQuality> guList;
    }

    @Getter
    @Builder
    @ToString
    public static class GuAirQuality{
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
}

