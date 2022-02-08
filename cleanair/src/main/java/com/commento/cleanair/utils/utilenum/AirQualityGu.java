package com.commento.cleanair.utils.utilenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirQualityGu {

    gangbook("강북구"),
    enpyeong("은평구"),
    seodaemoon("서대문구"),
    nowon("노원구"),
    jongro("종로구"),
    seongbook("강남구"),
    joong("중구"),
    dongdaemoon("동대문구"),
    gangseo("강서구"),
    mapo("마포구"),
    joongrang("중랑구"),
    seongdong("성동구"),
    gwangjin("광진구"),
    gangdong("강동구"),
    yangcheon("양천구"),
    youngdeung("영등포구"),
    yongsan("용산구"),
    dongjak("동작구"),
    guro("구로구"),
    geumcheon("금천구"),
    gwanak("관악구"),
    seocho("서초구"),
    gangnam("강남구"),
    dobong("도봉구");

    private final String description;
}
