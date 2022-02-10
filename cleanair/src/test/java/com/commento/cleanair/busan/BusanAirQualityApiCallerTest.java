package com.commento.cleanair.busan;

import com.commento.cleanair.infrastructure.busan.BusanAirQualityApiCaller;
import com.commento.cleanair.infrastructure.busan.BusanAirQualityApiDto.GetAirQualityResponse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusanAirQualityApiCallerTest {

    @Autowired
    BusanAirQualityApiCaller busanAirQualityApiCaller;

    @Test
    public void busanAirAPICallTest() {
        GetAirQualityResponse airQuality = busanAirQualityApiCaller.getAirQuality();
        Assertions.assertThat(airQuality).isNotNull();
    }
}