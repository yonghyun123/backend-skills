package com.commento.cleanair.domain;

import com.commento.cleanair.dto.AirQualityDto;
import com.commento.cleanair.utils.utilenum.AirQualityGu;
import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.assertj.core.api.Assertions;
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
        AirQualityDto.AirQuality seoulAirInfo = seoulApiService.getSeoulAirInfo(AirQualitySido.busan, AirQualityGu.myoungjang);
        //then
        Assertions.assertThat(seoulAirInfo.getGuList().size()).isEqualTo(1);
    }

}