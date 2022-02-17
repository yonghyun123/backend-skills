package com.commento.cleanair.controller;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.service.AirQualityApiService;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/air-info")
@RequiredArgsConstructor
public class AirQualityApiController {

    private final AirQualityApiService seoulApiService;

    @GetMapping("/{sido}")
    public AirQualityDto getAirQuality(@PathVariable AirQualitySido sido,
                                                  @RequestParam(required = false) AirQualityGu gu){
        return seoulApiService.getAirInfo(sido, gu);
    }

}
