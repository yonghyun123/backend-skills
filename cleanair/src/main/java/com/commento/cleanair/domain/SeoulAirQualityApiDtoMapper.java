package com.commento.cleanair.domain;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiDto;
import com.commento.cleanair.utils.CalculateAirCondition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeoulAirQualityApiDtoMapper {

    public AirQualityDto.AirQuality mapToAirQuality(SeoulAirQualityApiDto.Result result) {
        double avgPm10Avg = result
                .getItems()
                .stream()
                .mapToDouble(v -> Double.parseDouble(v.getPm10()))
                .average()
                .orElse(Double.NaN);

        String avgPm10 = String.valueOf(avgPm10Avg);
        String avgPm10AvgGrade = CalculateAirCondition.getPM10Grade(avgPm10);
        List<AirQualityDto.GuAirQuality> guList = toGuAirQuality(result.getItems());

        return AirQualityDto.AirQuality.builder()
                .sido("seoul") //타입수정필요
                .sidoPm10AvgGrade(avgPm10AvgGrade)
                .sidoPm10Avg(avgPm10)
                .guList(guList)
                .build();
    }

    private List<AirQualityDto.GuAirQuality> toGuAirQuality(List<SeoulAirQualityApiDto.Item> response) {
        return response
                .stream()
                .map(responseAir -> AirQualityDto.GuAirQuality
                        .builder()
                        .GU(responseAir.getMsrsteName())
                        .PM10(responseAir.getCo())
                        .PM25(responseAir.getPm25())
                        .O3(responseAir.getO3())
                        .NO2(responseAir.getNo2())
                        .CO(responseAir.getCo())
                        .SO2(responseAir.getSo2())
                        .PM10grade(CalculateAirCondition.getPM10Grade(responseAir.getPm10()))
                        .PM25grade(CalculateAirCondition.getPM25Grade(responseAir.getPm25()))
                        .O3Grade(CalculateAirCondition.getO3Grade(responseAir.getO3()))
                        .NO2Grade(CalculateAirCondition.getNO2Grade(responseAir.getNo2()))
                        .SO2Grade(CalculateAirCondition.getSO2Grade(responseAir.getSo2()))
                        .COGrade(CalculateAirCondition.getCOGrade(responseAir.getCo()))
                        .build()
                ).collect(Collectors.toList());
    }
}
