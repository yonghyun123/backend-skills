package com.commento.cleanair.seoul;

import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiDto;
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
        SeoulAirQualityApiDto.Result result = airQualityApiCaller.getAirQuality();
        Assertions.assertThat(result).isNotNull();
    }
}
