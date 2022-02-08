package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SeoulApiServiceTest {
    @Autowired
    SeoulApiService seoulApiService;

    @Test
    public void seoulApiServiceTest() {
        //when
        AirQualityDto.AirQuality seoulAirInfo = seoulApiService.getSeoulAirInfo(AirQualitySido.seoul, "gangnam");
        //then
        Assertions.assertThat(seoulAirInfo.getGuList().size()).isEqualTo(1);
    }

}