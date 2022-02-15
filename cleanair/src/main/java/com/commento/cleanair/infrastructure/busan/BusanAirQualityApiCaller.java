package com.commento.cleanair.infrastructure.busan;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.service.AirApiCaller;
import com.commento.cleanair.service.MappedAirQuality;
import com.commento.cleanair.utils.CalculateAirCondition;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BusanAirQualityApiCaller implements AirApiCaller {
    private final BusanAirQualityApi busanAirQualityApi;

    public BusanAirQualityApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanAirQualityApi.class);
    }

    public AirQualitySido getSidoType() {
        return AirQualitySido.busan;
    }

    public MappedAirQuality getAirQuality() {
        try {
            var call = busanAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResult().isSuccess()) {
                log.info(response.toString());
                log.info("convert(response) = {} ",convert(response));

                return convert(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. header=" + response.getResult().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    public MappedAirQuality convert(BusanAirQualityApiDto.GetAirQualityResponse response) {
        double avgPm10Avg = response.getResult()
                .getItems()
                .stream()
                .mapToDouble(v -> Double.parseDouble(v.getPm10()))
                .average()
                .orElse(Double.NaN);

        String avgPm10 = String.valueOf(avgPm10Avg);
        String avgPm10AvgGrade = CalculateAirCondition.getPM10Grade(avgPm10);
        List<MappedAirQuality.GuAirQuality> guList = convert(response.getResult().getItems());

        return MappedAirQuality.builder()
                .sido("busan") //타입수정필요
                .sidoPm10AvgGrade(avgPm10AvgGrade)
                .sidoPm10Avg(avgPm10)
                .guList(guList)
                .build();
    }

    public List<MappedAirQuality.GuAirQuality> convert(List<BusanAirQualityApiDto.Item> response){
        return response
                .stream()
                .map(responseAir -> MappedAirQuality.GuAirQuality
                        .builder()
                        .gu(responseAir.getSite())
                        .pm10(responseAir.getCo())
                        .pm25(responseAir.getPm25())
                        .o3(responseAir.getO3())
                        .no2(responseAir.getNo2())
                        .co(responseAir.getCo())
                        .so2(responseAir.getSo2())
                        .pm10Grade(CalculateAirCondition.getPM10Grade(responseAir.getPm10()))
                        .pm25Grade(CalculateAirCondition.getPM25Grade(responseAir.getPm25()))
                        .o3Grade(CalculateAirCondition.getO3Grade(responseAir.getO3()))
                        .no2Grade(CalculateAirCondition.getNO2Grade(responseAir.getNo2()))
                        .so2Grade(CalculateAirCondition.getSO2Grade(responseAir.getSo2()))
                        .coGrade(CalculateAirCondition.getCOGrade(responseAir.getCo()))
                        .build()
                ).collect(Collectors.toList());
    }
}
