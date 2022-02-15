package com.commento.cleanair.service;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SeoulApiServiceTest {
    @Autowired
    AirQualityApiService seoulApiService;

    @Test
    public void seoulApiServiceTest() {
        //when
        AirQualityDto seoulAirInfo = seoulApiService.getSeoulAirInfo(AirQualitySido.busan, AirQualityGu.myoungjang);
        //then
        Assertions.assertThat(seoulAirInfo.getGuList().size()).isEqualTo(1);
    }

}