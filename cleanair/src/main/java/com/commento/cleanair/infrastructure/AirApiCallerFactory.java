package com.commento.cleanair.infrastructure;

import com.commento.cleanair.utils.utilenum.AirQualitySido;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AirApiCallerFactory {
    private final Map<AirQualitySido, AirApiCaller> apiCallerMap = new HashMap<>();

    public AirApiCallerFactory(List<AirApiCaller> airApiCallerList){
        if(CollectionUtils.isEmpty(airApiCallerList)) {
            throw new IllegalArgumentException("해당 지역은 없습니다.");
        }
        //map은 중간연산이라 값이 들어가지 않음
//        airApiCallerList.stream()
//                .map(apiList -> apiCallerMap.put(apiList.getSidoType(), apiList));

        airApiCallerList.stream()
                .forEach(apiList -> apiCallerMap.put(apiList.getSidoType(), apiList));
    }

    public AirApiCaller getApiType(AirQualitySido sido){
        return apiCallerMap.get(sido);
    }
}
