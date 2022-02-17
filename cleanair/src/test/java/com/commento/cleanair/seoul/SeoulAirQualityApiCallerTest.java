package com.commento.cleanair.seoul;

import com.commento.cleanair.controller.dto.AirQualityDto;
import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.service.MappedAirQuality;
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
        MappedAirQuality airQuality = airQualityApiCaller.getAirQuality();
        Assertions.assertThat(airQuality).isNotNull();
    }
}