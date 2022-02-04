package com.commento.cleanair.controller;

import com.commento.cleanair.dto.AirQualityAverage;
import com.commento.cleanair.dto.ParticularAirQuality;
import com.commento.cleanair.service.SeoulApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/air-info")
@RequiredArgsConstructor
public class SeoulApiController {

    private final SeoulApiService seoulApiService;

    @GetMapping("/seoul")
    public List<AirQualityAverage> getAllAirConditionInfo(){
        return seoulApiService.getSeoulAirInfo();
    }

    @GetMapping("/seoul/{cityName}")
    public ParticularAirQuality getAirInfoByCityName(@PathVariable String cityName){
        return seoulApiService.getAirInfoByCityName(cityName);
    }

}
