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
        GetAirQualityResponse airQuality = qualityApiCaller.getAirQuality();
        List<AirQualityAverage> seoulTotalQualityInfo = new ArrayList<>();

        //서울 각 구에 해당하는 Air Condition 정보
        List<Item> items = airQuality.getResult().getItems();

        for(Item item : items){
            //Util 클래스를 통한 대기질 정보 변환(GOOD, SOSO, BAD etc)
            AirQualityAverage airQualityAverage = new AirQualityAverage();
            String pm10Grade = CalculateAirCondition.getPM10Grade(item.getPm10());
            airQualityAverage.setGrade(pm10Grade);
            airQualityAverage.setPM10(item.getPm10());
            airQualityAverage.setLocationName(item.getMsrsteName());
            seoulTotalQualityInfo.add(airQualityAverage);
        }

        //마지막 서울시 평균 미세먼지
        Double seoulAverage = items.stream()
                .mapToDouble(v -> Double.parseDouble(v.getPm10()))
                .average().getAsDouble();


        AirQualityAverage airQualityAverage = new AirQualityAverage();
        airQualityAverage.setLocationName("서울시");
        airQualityAverage.setPM10(seoulAverage.toString());
        airQualityAverage.setGrade(CalculateAirCondition.getPM10Grade(seoulAverage.toString()));

        //마지막 레코드에 서울시 평균 대기질 정보를 넣는다
        seoulTotalQualityInfo.add(airQualityAverage);

        return seoulTotalQualityInfo;
    }

    public ParticularAirQuality getAirInfoByCityName(String cityName) {
        GetAirQualityResponse airQuality = qualityApiCaller.getAirQuality();
        String translatedCity = LocationNameRules.translateCity.get(cityName);

        Item item = airQuality.getResult()
                .getItems()
                .stream()
                .filter(v -> v.getMsrsteName().equals(translatedCity))
                .findAny()
                .orElse(null);

        log.info("item = {}" + item);
        ParticularAirQuality particularAirQuality = new ParticularAirQuality();
        particularAirQuality.setLocationName(item.getMsrsteName());
        particularAirQuality.setCO(item.getCo());
        particularAirQuality.setPM10(item.getPm10());
        particularAirQuality.setPM25(item.getPm25());
        particularAirQuality.setSO2(item.getSo2());
        particularAirQuality.setNO2(item.getNo2());
        particularAirQuality.setO3(item.getO3());

        particularAirQuality.setPM10grade(CalculateAirCondition.getPM10Grade(item.getPm10()));
        particularAirQuality.setPM25grade(CalculateAirCondition.getPM25Grade(item.getPm25()));
        particularAirQuality.setO3Grade(CalculateAirCondition.getO3Grade(item.getO3()));
        particularAirQuality.setCOGrade(CalculateAirCondition.getCOGrade(item.getCo()));
        particularAirQuality.setSO2Grade(CalculateAirCondition.getSO2Grade(item.getSo2()));
        particularAirQuality.setNO2Grade(CalculateAirCondition.getNO2Grade(item.getNo2()));

        return particularAirQuality;
    }
}
