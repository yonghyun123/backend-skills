package com.commento.cleanair.service;

import com.commento.cleanair.dto.AirQualityAverage;
import com.commento.cleanair.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.seoul.SeoulAirQualityApiDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}