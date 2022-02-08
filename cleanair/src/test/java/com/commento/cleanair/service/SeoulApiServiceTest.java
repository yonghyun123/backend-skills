package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityDto;
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
        AirQualityDto.AirQuality seoulAirInfo = seoulApiService.getSeoulAirInfo("seoul", "gangnam");
        //then
        Assertions.assertThat(seoulAirInfo.getGuList().size()).isEqualTo(25);
    }

}