package com.commento.cleanair.seoul;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SeoulAirQualityApiCallerTest {
    @Autowired
    SeoulAirQualityApiCaller airQualityApiCaller;

    @Test
    public void seoulAirAPICallTest() {
        SeoulAirQualityApiDto.GetAirQualityResponse airQuality = airQualityApiCaller.getAirQuality();
        Assertions.assertThat(airQuality).isNotNull();
    }
}