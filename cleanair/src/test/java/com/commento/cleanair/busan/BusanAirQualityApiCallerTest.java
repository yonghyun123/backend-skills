package com.commento.cleanair.busan;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.infrastructure.busan.BusanAirQualityApiCaller;
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
//        GetAirQualityResponse airQuality = busanAirQualityApiCaller.getAirQuality();
        AirQualityDto.AirQuality airQuality = busanAirQualityApiCaller.getAirQuality();
        Assertions.assertThat(airQuality).isNotNull();
    }
}
