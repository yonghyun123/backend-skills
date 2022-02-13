package com.commento.cleanair.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@ToString
public class AirQuality {
    private String sido;
    private String sidoPm10Avg;
    private String sidoPm10AvgGrade;
    private List<GuAirQuality> guList;

    @Getter
    @Builder
    @ToString
    public static class GuAirQuality {
        private String GU;
        private String PM10;
        private String PM10grade;
        private String PM25;
        private String PM25grade;
        private String O3;
        private String O3Grade;
        private String NO2;
        private String NO2Grade;
        private String CO;
        private String COGrade;
        private String SO2;
        private String SO2Grade;
    }

    public AirQuality getAirQuality(String gu) {
        GuAirQuality guAirQuality = searchByGu(gu);
        guList = Collections.singletonList(guAirQuality);
        return this;
    }

    public GuAirQuality searchByGu(String gu) {
        return guList.stream()
                .filter(item -> gu.equals(item.getGU()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 자치구에 해당하는 지역이 없습니다."));
    }
}
