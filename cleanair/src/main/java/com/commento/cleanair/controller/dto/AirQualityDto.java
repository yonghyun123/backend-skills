package com.commento.cleanair.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class AirQualityDto {

    public String sido;
    public String sidoPm10Avg;
    public String sidoPm10AvgGrade;
    public List<GuAirQuality> guList;

    @Getter
    @Builder
    @ToString
    public static class GuAirQuality {
        public String GU;
        public String PM10;
        public String PM10grade;
        public String PM25;
        public String PM25grade;
        public String O3;
        public String O3Grade;
        public String NO2;
        public String NO2Grade;
        public String CO;
        public String COGrade;
        public String SO2;
        public String SO2Grade;
    }
}

