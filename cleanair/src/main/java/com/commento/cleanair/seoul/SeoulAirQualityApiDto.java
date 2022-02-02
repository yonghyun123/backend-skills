package com.commento.cleanair.seoul;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

public class SeoulAirQualityApiDto {
    @Getter
    @Setter
    @ToString
    public static class GetAirQualityResponse{
        @JsonProperty("RealtimeCityAir")
        private Result result;
    }

    @Getter
    @Setter
    @ToString
    public static class Result{
        @JsonProperty("list_total_count")
        private Integer listTotalCount;
        @JsonProperty("RESULT")
        private Header header;
        @JsonProperty("row")
        private List<Item> items;

        public boolean isSuccess() {
            if (Objects.equals(header.getCode(), "INFO-000")) {
                return true;
            }
            return false;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Header{
        @JsonProperty("CODE")
        String code;
        @JsonProperty("MESSAGE")
        String message;
    }

    @Getter
    @Setter
    @ToString
    public static class Item{
        @JsonProperty("MSRDT")
        private String measurementTime;
        @JsonProperty("MSRRGN_NM")
        private String msrgrnName;
        @JsonProperty("MSRSTE_NM")
        private String msrsteName;
        @JsonProperty("PM10")
        private String pm10;
        @JsonProperty("PM25")
        private String pm25;
        @JsonProperty("O3")
        private String o3;
        @JsonProperty("NO2")
        private String no2;
        @JsonProperty("CO")
        private String co;
        @JsonProperty("SO2")
        private String so2;
        @JsonProperty("IDEX_NM")
        private String gradeString;
        @JsonProperty("IDEX_MVL")
        private String gradeCode;
        @JsonProperty("ARPLT_MAIN")
        private String measuredItem;

    }
}
