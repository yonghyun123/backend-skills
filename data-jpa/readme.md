### 스프링데이터JPA 동작
- jUnit테스트에서 실 데이터를 확인하고 싶으면
- @Rollback(false) 

### 예제 도메인 모델



### 공통 인터페이스 기능
- 스프링데이터 JPA 공통 인터페이스 소개 및 활용

- 실제 출력해보니(Proxy가 나옴)
- @Repository 생략가능

### 공통 인터페이스 분석
- 최상위의 Repository<T,ID> 인터페이스가 존재
- 마커 인터페이스로 정의

<img width="420" alt="스크린샷 2023-05-27 오후 4 58 03" src="https://github.com/yonghyun123/backend-skills/assets/15208005/bc390bef-8e64-4c78-9ab0-9cefc90a9442">

### 주요메서드
- save
- delete
- findById
- getOne: 엔티티를 프록시로  조회한다
- findAll


```
> List<Member> findByUsername(String userName) 
> 이건 어떻게 구현해야하는가?
```