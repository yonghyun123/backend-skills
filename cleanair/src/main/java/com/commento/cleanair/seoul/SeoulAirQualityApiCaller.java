package com.commento.cleanair.seoul;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.utils.CalculateAirCondition;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SeoulAirQualityApiCaller {

    private final SeoulAirQualityApi seoulAirQualityApi;

    public SeoulAirQualityApiCaller(@Value("${api.seoul.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        log.info("baseUrl = {}", baseUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        this.seoulAirQualityApi = retrofit.create(SeoulAirQualityApi.class);
    }

    public AirQualityDto.AirQuality getAirQuality() {
        try {
            Call<SeoulAirQualityApiDto.GetAirQualityResponse> call = seoulAirQualityApi.getAirQuality();
            log.info("call-url = {}",call.request().url());
            var response = call.execute().body();
            log.info("response-getResult = {}",response.getResult());
            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResult().isSuccess()) {
                log.info("convert() = {}", convert(response));
                return convert(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. header=" + response.getResult().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    public AirQualityDto.AirQuality convert(SeoulAirQualityApiDto.GetAirQualityResponse response) {
        double avgPm10Avg = response.getResult()
                .getItems()
                .stream()
                .mapToDouble(v -> Double.parseDouble(v.getPm10()))
                .average()
                .orElse(Double.NaN);

        String avgPm10 = String.valueOf(avgPm10Avg);
        String avgPm10AvgGrade = CalculateAirCondition.getPM10Grade(avgPm10);
        List<AirQualityDto.GuAirQuality> guList = convert(response.getResult().getItems());

        return AirQualityDto.AirQuality.builder()
                .sido("seoul") //타입수정필요
                .sidoPm10AvgGrade(avgPm10AvgGrade)
                .sidoPm10Avg(avgPm10)
                .guList(guList)
                .build();
    }

    public List<AirQualityDto.GuAirQuality> convert(List<SeoulAirQualityApiDto.Item> response){
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
