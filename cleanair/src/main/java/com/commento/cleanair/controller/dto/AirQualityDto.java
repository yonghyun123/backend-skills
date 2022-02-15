package com.commento.cleanair.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@ToString
public class AirQualityDto {

    private String sido;
    private String sidoPm10Avg;
    private String sidoPm10AvgGrade;
    private List<GuAirQuality> guList;

    public AirQualityDto getAirQuality(String gu){
        GuAirQuality guAirQuality = searchByGu(gu);
        guList = Collections.singletonList(guAirQuality);
        return this;
    }

    public GuAirQuality searchByGu(String gu){
        return guList.stream()
                .filter(item -> gu.equals(item.getGu()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 자치구에 해당하는 지역이 없습니다."));
    }


    @Getter
    @Builder
    @ToString
    public static class GuAirQuality{
        private String gu;
        private String pm10;
        private String pm10Grade;
        private String pm25;
        private String pm25Grade;
        private String o3;
        private String o3Grade;
        private String no2;
        private String no2Grade;
        private String co;
        private String coGrade;
        private String so2;
        private String so2Grade;
    }
}

