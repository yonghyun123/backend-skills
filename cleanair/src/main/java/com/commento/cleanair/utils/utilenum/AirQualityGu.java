package com.commento.cleanair.utils.utilenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirQualityGu {

    /* 서울 */
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
    dobong("도봉구"),
    
    /* 부산 */
    taejong("태종대"),
    chunghak("청학동"),
    jeonpo("전포동"),
    onchyun("온천동"),
    myoungjang("명장동"),
    daeyeon("대연동"),
    yongho("용호동"),
    hakjang("학장동"),
    dukcheon("덕천동"),
    hwamyoung("화명동"),
    samrak("삼락동"),
    chungyong("청룡동"),
    jwa("좌동"),
    jaesong("재송동"),
    janglim("장림동"),
    daeju("대저동"),
    noksan("녹산동"),
    myoungji("명지동"),
    yeonsan("연산동"),
    kijang("기장읍"),
    yongsu("용수리"),
    soojung("수정동"),
    buguk("부곡동"),
    whedong("회동동"),
    kwangan("광안동"),
    daesin("대신동"),
    dukpo("덕포동"),
    gaeguem("개금동"),
    dangri("당리동"),
    busanbook("부산북항"),
    busansin("부산신항"),
    kwangbok("광복동"),
    choryang("초량동");

    private final String description;
}
