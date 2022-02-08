package com.commento.cleanair.controller;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.service.SeoulApiService;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/air-info")
@RequiredArgsConstructor
public class SeoulApiController {

    private final SeoulApiService seoulApiService;

    @GetMapping("/{sido}")
    public AirQualityDto.AirQuality getAirQuality(@PathVariable AirQualitySido sido,
                                                  @RequestParam(required = false) AirQualityGu gu){
        return seoulApiService.getSeoulAirInfo(sido, gu);
    }

}
