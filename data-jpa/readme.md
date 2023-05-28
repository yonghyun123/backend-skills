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


### 쿼리 메서드 기능
- 메서드 이름으로 쿼리 생성
- NameQuery
- @Query - 레파지토리 메서드에 쿼리 정의
- 파라미터 바인딩
- 반환 타입
- 페이징과 정렬
- 벌크성 수정 쿼리
- @EntityGraph

**쿼리 메서드 기능 3가지**

- 메서드 이름으로 쿼리 생성
- 메서드 이름으로 JPA NamedQuery 호출
- @Query어노테이션을 사용해서 리파지토리 인터페이스에 쿼리 직접 정의


> 참고: 필드명이 변경되면 인터페이스에 정의한 메서드 이름도 변경해야한다. 어플리케이션 실행중 컴파일단에서 체크한다.

#JPA NamedQuery

- @Query(name="Member.findByUsername" 주석가능 
- 메서드명으로 네임드쿼리를 찾음
- 없다면 메서드명으로 쿼리생성
- 실무에선 NamedQuery를 사용하지 않음.
- 애플리케이션 로딩시점에 쿼리를 파싱해서 오류가 있다면 에러를 내버림 **아주 큰 장점**


### Query 값, DTO 조회
- 단순히 값 하나를 조회

### 파라미터 바인딩
- 위치 기반
- 이름 기반

**컬렉션 파라미터 바인딩**

### 반환 타입

```
 List<Member> result = memberRepository.findListUsername("asdfasdfasdf");//없는 리스트 반환
        //result 는 null 아님!!!
        
Member findMember = memberRepository.findMemberUsername("asdfasfsadf");
        //findMember 는 null !!!
        
Optional<Member> optionalMember = memberRepository.findOptionalUsername("asdfasdfsadf");
        //대부분 이걸로 처리함
        
 //만약 두건이 조회된다면..? Exception 발생 -> NonUniqueException ->
IncorrectResultSizeDataAccessException
```

### JPA 페이징과 정렬
- 검색조건 : 10살
- 이름으로 내림차순
- 페이징 조건: 페이지당 3건


