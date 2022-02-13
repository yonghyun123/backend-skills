package com.commento.cleanair.infrastructure;

import com.commento.cleanair.domain.AirQualityReader;
import com.commento.cleanair.domain.AirQualityReaderSelector;
import com.commento.cleanair.domain.SeoulAirQualityReader;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AirApiCallerFactoryTest {

    @Autowired
    AirQualityReaderSelector selector;

    @Test
    public void 지역별_api_가져오기(){

        //given
        AirQualitySido t1 = AirQualitySido.seoul;

        //when
        AirQualityReader apiType1 = selector.selectBy(t1);

        //then

        Assertions.assertThat(apiType1).isInstanceOf(SeoulAirQualityReader.class);
    }
}
