package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityAverage;
import com.commento.cleanair.dto.ParticularAirQuality;
import com.commento.cleanair.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.seoul.SeoulAirQualityApiDto.GetAirQualityResponse;
import com.commento.cleanair.utils.CalculateAirCondition;
import com.commento.cleanair.utils.LocationNameRules;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.commento.cleanair.seoul.SeoulAirQualityApiDto.*;

@Service
@Slf4j
@RequiredArgsConstructor // Constructor Injection
public class SeoulApiService {
    final private SeoulAirQualityApiCaller qualityApiCaller;

    //business logic
    public List<AirQualityAverage> getSeoulAirInfo(){
        AirQualityAverage.AirQuality airQuality = qualityApiCaller.getAirQuality();
        List<AirQualityAverage> seoulTotalQualityInfo = new ArrayList<>();
//
//        //서울 각 구에 해당하는 Air Condition 정보
//        List<Item> items = airQuality.getResult().getItems();
//
//        for(Item item : items){
//            //Util 클래스를 통한 대기질 정보 변환(GOOD, SOSO, BAD etc)
//            //Build 패턴을 이용한 가독성
//            String pm10Grade = CalculateAirCondition.getPM10Grade(item.getPm10());
//            AirQualityAverage airQualityAverage = AirQualityAverage.builder()
//                    .grade(pm10Grade)
//                    .PM10(item.getPm10())
//                    .locationName(item.getMsrsteName())
//                    .build();
//
//            seoulTotalQualityInfo.add(airQualityAverage);
//        }
//
//        //마지막 서울시 평균 미세먼지
//        Double seoulAverage = items.stream()
//                .mapToDouble(v -> Double.parseDouble(v.getPm10()))
//                .average().getAsDouble();
//
//
//        AirQualityAverage airQualityAverage = AirQualityAverage.builder()
//                .locationName("서울시")
//                .PM10(seoulAverage.toString())
//                .grade(CalculateAirCondition.getPM10Grade(seoulAverage.toString()))
//                .build();
//
//        //마지막 레코드에 서울시 평균 대기질 정보를 넣는다
//        seoulTotalQualityInfo.add(airQualityAverage);

        return seoulTotalQualityInfo;
    }

    public ParticularAirQuality getAirInfoByCityName(String cityName) {
//        GetAirQualityResponse airQuality = qualityApiCaller.getAirQuality();
//        String translatedCity = LocationNameRules.translateCity.get(cityName);
//
//        Item item = airQuality.getResult()
//                .getItems()
//                .stream()
//                .filter(v -> v.getMsrsteName().equals(translatedCity))
//                .findAny()
//                .orElse(null);
//
//        ParticularAirQuality particularAirQuality = ParticularAirQuality.builder()
//                .locationName(item.getMsrsteName())
//                .PM10(item.getPm10())
//                .PM25(item.getPm25())
//                .O3(item.getO3())
//                .NO2(item.getNo2())
//                .SO2(item.getSo2())
//                .CO(item.getCo())
//                .PM10grade(CalculateAirCondition.getPM10Grade(item.getPm10()))
//                .PM25grade(CalculateAirCondition.getPM25Grade(item.getPm25()))
//                .O3Grade(CalculateAirCondition.getO3Grade(item.getO3()))
//                .NO2Grade(CalculateAirCondition.getNO2Grade(item.getNo2()))
//                .SO2Grade(CalculateAirCondition.getSO2Grade(item.getSo2()))
//                .COGrade(CalculateAirCondition.getCOGrade(item.getCo()))
//                .build();

//        return particularAirQuality;
        return null;
    }
}
