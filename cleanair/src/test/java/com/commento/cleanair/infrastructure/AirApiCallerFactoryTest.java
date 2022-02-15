package com.commento.cleanair.infrastructure;

import com.commento.cleanair.infrastructure.busan.BusanAirQualityApiCaller;
import com.commento.cleanair.infrastructure.seoul.SeoulAirQualityApiCaller;
import com.commento.cleanair.service.AirApiCaller;
import com.commento.cleanair.service.AirApiCallerFactory;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AirApiCallerFactoryTest {

    @Autowired
    AirApiCallerFactory airApiCallerFactory;

    @Test
    public void 지역별_api_가져오기(){

        //given
        AirQualitySido t1 = AirQualitySido.seoul;
        AirQualitySido t2 = AirQualitySido.busan;

        //when
        AirApiCaller apiType1 = airApiCallerFactory.getApiType(t1);
        AirApiCaller apiType2 = airApiCallerFactory.getApiType(t2);

        //then

        Assertions.assertThat(apiType1).isInstanceOf(SeoulAirQualityApiCaller.class);
        Assertions.assertThat(apiType2).isInstanceOf(BusanAirQualityApiCaller.class);
    }
}