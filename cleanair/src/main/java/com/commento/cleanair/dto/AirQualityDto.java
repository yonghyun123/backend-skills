package com.commento.cleanair.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
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

        public AirQuality getAirQuality(String gu){
            GuAirQuality guAirQuality = searchByGu(gu);
            guList = Collections.singletonList(guAirQuality);
            return this;
        }

        public GuAirQuality searchByGu(String gu){
            return guList.stream()
                    .filter(item -> gu.equals(item.getGU()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("해당 자치구에 해당하는 지역이 없습니다."));
        }
    }

    @Getter
    @Builder
    @ToString
    public static class GuAirQuality{
        String GU;
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

