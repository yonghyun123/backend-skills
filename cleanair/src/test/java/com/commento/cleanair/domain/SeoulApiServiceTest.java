package com.commento.cleanair.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SeoulApiServiceTest {
    @Autowired
    SeoulApiService seoulApiService;

    @Test
    public void seoulApiServiceTest() {
        //when
//        AirQualityDto.AirQuality seoulAirInfo = seoulApiService.getSeoulAirInfo(AirQualitySido.busan, AirQualityGu.myoungjang);
        //then
//        Assertions.assertThat(seoulAirInfo.getGuList().size()).isEqualTo(1);
    }

}