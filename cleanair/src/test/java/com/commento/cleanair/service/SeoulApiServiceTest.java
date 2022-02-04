package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityAverage;
import com.commento.cleanair.dto.ParticularAirQuality;
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
        List<AirQualityAverage> seoulAirInfo = seoulApiService.getSeoulAirInfo();
        //then
        Assertions.assertThat(seoulAirInfo.size()).isEqualTo(26);
    }
    
    @Test
    public void 특정지역_API호출_Test(){
        ParticularAirQuality gangnam = seoulApiService.getAirInfoByCityName("gangnam");
        Assertions.assertThat(gangnam.getLocationName()).isEqualTo("강남구");
    }
}