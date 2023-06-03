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

### 스프링데이터 JPA 페이징과 정렬
- org.springframwork.data.domain.Sort: 정렬기능
- org.springframwork.data.domain.PageableL: 페이징기능


### 벌크성 수정 쿼리

### @EntityGraph
- @EntityGraph를 사용하면 페치조인에 기능과 동일하다

### JPA Hint & Lock
- JPA 힌트
- 쿼리힌트 (SQL힌트가 아니라 JPA 구현체에게 제공하는 힌트)

### 확장 기능
- 사용자 정의 리포지토리 구현
- 다양한 이유로 인터페이스의 메서드를 직접 구현하고 싶다면?
	- JPA직접 사용
	- 스프링 JDBC 템플릿 사용
	- Mybatis 사용
	- DB 커넥션 직접 사용
	- QueryDsl 등등


**JavaConfig설정**
```
@EnableJpaRepositories(basePackage = "study.datajpa.repository", repositoryImplementationPostfix = "Impl")
```

> 참고: 실무에선 QueryDSL이나 SpringJdbcTemplate을 함께 사용할때 적용


### 도메인 클래스 컨버터
```
    @GetMapping("/members2/{id}")
    public String findMembe2r(@PathVariable("id") Member member) {
        return member.getUserName();
    }
```
- 스프링데이터JPA가 member에 컨버팅을 해준다


### 스프링데이터 JPA 구현체
- JPA의 모든 변경은 트랜잭션 안에서 동작
- 서비스 계층에서 트랜잭션을 시작하지 않으면 리파지토리에서 트랜잭션 시작
- 그래서 스프링 데이터 JPA를 사용할때 트랜잭션이 없어도 데이터 등록, 변경이 가능

**매우 중요**
- save() 메서드
- 새로운 엔티티면 저장(persist)
- 새로운 엔티티가 아니면 병합(merge)

### 새로운 엔티티를 구별하는 방법

### Specifications(명세)
- 거의 사용 안함

### 네이티브 쿼리
- 가급적 네이티브 쿼리는 사용하지 않는게 좋음
- 웬만하면 QueryDSL로 풀어냄
- 정안되면 myBatis를 사용








