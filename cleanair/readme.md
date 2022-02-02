# 서울시 미세먼지 API 정보 구축 프로젝트

### 요구사항
 
 - 서울시 전체 평균 미세먼지 정보 (PM-10)가 표시되어야 함
 - 시/도 와 자치구(동) 별로 선택이 가능해야함
 - 자치구(동)별 대기질 정보가 표시 되어야함

 
 > 표시 항목
초미세먼지 (PM-2.5) / 등급  
 미세먼지 (PM-10) / 등급  
 오존 (O3) / 등급  
 이산화질소 (NO2) / 등급  
 일산화탄소 (CO) / 등급  
아황산가스 (SO2) / 등급


### API 정보 URL
https://app.gitbook.com/s/jFuKLpJzOhw9LM3pKPss/

### 스토리보드
<img width="1176" alt="스크린샷 2022-02-02 오후 1 33 36" src="https://user-images.githubusercontent.com/15208005/152093618-c1f70745-4fac-459f-a94b-b3fd1b58791d.png">



<img width="684" alt="스크린샷 2022-02-02 오후 1 34 42" src="https://user-images.githubusercontent.com/15208005/152093698-d73f4508-b781-4b8e-b4bd-70b8df3b72bc.png">

<img width="937" alt="스크린샷 2022-02-02 오후 1 35 29" src="https://user-images.githubusercontent.com/15208005/152093762-589e13b9-416e-4abf-ab1a-e09432752e52.png">



# 추가 요구사항 
 - 확장성을 고려한 API 설계 필요(부산시까지 확장)
 - 공공데이터 활용으로 1시간 주기의 갱신에 따른 캐시메모리 사용 필요
 - 추후 전국으로 확장가능한지 설계 검토(자체진행)

 
 # 기술스택
  - Springboot
  - retrofit library
  - Open API
  - InteliJ