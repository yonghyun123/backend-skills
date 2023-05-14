# 영속성 컨텍스트

JPA에서 가장 중요한 2가지

- 객체와 관계형 데이터베이스 매핑하기
- 영속성 컨텍스트
	- JPA를 이해하는데 가장 중요한 용어
	- "엔티티를 영구 저장하는 환경" 
	- EntityManager.persist(entity);
- 엔티티 매니저를 통해서 영속성 컨텍스트에 접근


**엔티티의 생명주기** 

- 비영속(new/transient): 새로운 상태
- 영속(managed): 영속성 컨텍스트에 관리되는 상태
- 준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제: 삭제된 상태

### 영속성 컨텍스트의 이점

- 1차 캐시
- 동일성(identity) 보장
- 트랜잭션을 지원하는 쓰기 지연
- 변경 감지(Dirty Checking)
- 지연 로딩(Lazy Loading)

**1차캐시 - 한 트랜잭션 안에서만 사용되기때문에 성능적으로 크게 차이는 없음**

### 영속 엔티티의 동일성 보장

```
Member findMember = em.find(Member.class, 100L);
Member findMember2 = em.find(Member.class, 100L);

findMember == findMember2 //true
```

1차캐시로 repeatable read 등급의 트랜잭션 격리수준을 애플리케이션 차원에서 제공

### 엔티티 등록 트랜잭션을 지원하는 쓰기 지연

```
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야함.
        tx.begin();
        
        em.persist(MemberA);
	em.persist(MemberB);
	// Insert SQL이 나가지 않음
	
	tx.commit(); //여기서 나감
```


### 엔티티 수정 변경 감지
```
  Member findMember = em.find(Member.class, 150L);
            findMember.setName("Ciba");
            tx.commit();
```

### 엔티티 삭제
em.remove(memberA);

### 플러시

영속성 컨텍스트의 변경내용을 DB에 반영 

- 변경 감지
- 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL저장소의 쿼리를 데이터베이스에 전송
- 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨

### 준영속 상태

- 영속 -> 준영속
- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
- 영속성 컨텍스트가 제공하는 기능을 사용 못함
- em.detach(entity)
- em.clear()
- em.close()


### 엔티티 매핑

- 객체와 테이블 매핑
- 데이터베이스 스키마 자동 생성
- 필드와 컬럼 매핑
- 기본 키 매핑
- 실전 예제 - 1.요구사항 분석과 기본 매핑

### @Entity

- @Entity가 붙은 클래스는 JPA가 관리
- 주의
	- **기본 생성자 필수**
	- final 클래스, enum, interface, inner 클래스 X
	- 저장할 필드에 final 사용 X


### 데이터베이스 스키마 자동 생성

- DDL을 애플리케이션 실행 시점에 자동 생성
- 테이블 중심 -> 객체 중심
- DB방언을 활용해서 DB에 맞는 DDL 생성
- 이렇게 생성된 DDL은 개발 장비에서만 사용
- 생성된 DDL은 운영서버에는 사용하지 않거나 적절히 다듬은 후 사용


> 옵션
>  create: drop -> create
> create-drop : 종료시점에 drop
> update: DDL 추가(변경분만)
> validate: 엔티티와 테이블이 정상 매핑되었는지?


### 운영장비에는 절대 create, create-drop,update를 사용하면 안된다.

- 개발 초기에는 create 또는 update
- 테스트 서버에서는 update, validate
- 스테이징과 운영은 validate, none

**운영중에 데이터가 많은 테이블에 alter를 하게 되면 시스템이 멈출 수 있다.**


###DDL 생성 기능

- 제약조건 추가
- @Column(unique = true, length = 10)


### 요구사항 추가

1. 회원은 일반 회원과 관리자로 구분해야 한다.
2. 회원 가입일과 수정일이 있어야 한다.
3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.

### 어노테이션 Column
nullable = false // not null
insertable = false // not insert
unique -> @Table(uniqueConstraint = "" )

### @Enummerated
EnumType.ORDINAL: enum 순서를
**ORDINAL 사용은 금지**

**운영 장비에는 절대 create, create-drop, update를 사용하면 안된다.**



@Column

name: 필드와 매핑할 테이블 컬럼이름
updatable: 등록변경 가능여부
nullable: not null 조건
precision Scale

Identity 전략
@GeneratedValue(strategy = Identity) 기본키 생성을 DB에 위임
@GeneratedValue(strategy = sequence)
오라클 시퀀스를 만들어버림

ID는 Long이 일반적임

운영에서 테이블sequence전략을 사용하는건 부담스러움

Sequence전략에서는 db 시퀀스를 먼저 읽어서 pk를 셋팅한 후 영속성 컨텍스트에 넣음. Identity와는 반대

Member - Team 다대일 관계에서 Member내의 참조변수로 team_id가 아닌 team을 가지면서 @ManyToOne, @JoinColumn(name = "TEAM_ID")가 필요

양방향 연관관계

Team에서 여러명에 Memeber가 존재할 수 있으니
```@OneToMany(mappedby="team")
List<Member> members```

**연관관계의 주인과 mappedBy**

둘 중 하나로 외래키를 관리해야한다

### 양방향 매핑 규칙
- 객체의 관계중 하나를 주인으로 지정
- 연관관계의 주인만이 외래 키를 관리
- 주인이 아닌쪽은 읽기만 가능
- 주인은 mappedBy 속성 사용 X
- 주인이 아니면 mappedBy속성으로 주인을 지정


### 주인은 누구인가
- 외래키가 있는 곳이 주인

### 컨트롤러에서 엔티티를 반환하지마라
- 무한루프 발생 가능성(json 생성 라이브러리)
- 엔티티가 변경되면 api가 변경되어야한다.

### 상속관계 매핑
- 관계형 DB는 상속x
- 슈퍼타입 서브타입 관계 모델링 기법이 존재


### 주요 어노테이션
- @Inheritance(strategy = InheritanceType.JOINED)


###MappedSuperclass
- 상속관계 매핑 X
- 엔티티 X
- 매핑 정보만 제공
- 조회, 검색 불가
- 추상클래스 권장


### 프록시, 로딩, 영속성 전이

### 프록시 기초
- em.find() vs em.getReference()
- em.find() : db를 통해서 객체 조회
- em.getReference(): db조회를 미루는 가짜 조회(프록시엔티티 객체)

### 프록시 특징
-  실제 클래스를 상속 받아서 만들어짐
-  실제 클래스와 겉모양은 같은
-  사용자는 프록시인지 진짜인지 구분하지 않고 사용하면 됨.

- 프록시 객체는 첨 사용할 때 한번만 초기화
- 프록시 객체를 초기화 할때, 프록시 객체가 실제 엔티티로 바뀌는것은 아님, 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능
- 프록시 객체는 원본 엔티티를 상속받음. 따라서 타입 체크시 instance of 사용해야함.


### 즉시 로딩과 지연 로딩
로딩 -> member -> team1(지연로딩, proxy)
사용이 될때 초기화됨 (DB 조회)

### member와 team을 항상 같이 사용한다면?
eager로 조회(즉시로딩)

### 프록시와 즉시로딩 주의
- 가급적 지연 로딩만 사용(특히 실무)
- 즉시로딩을 사용하면 예상하지 못한 SQL이 발생
- 즉시로딩은 JPQL에서 N+1 문제를 일으킨다.
- @ManyToOne, @OneToOne은 기본이 즉시로딩 -> lazy 로 변경해야함.

### 영속성 전이: cascade - 주의
- 영속성 전이는 연관관계를 매핑하는것과 관련이 없음.
- 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화하는 편리를 제공할 뿐


### 고아객체
- orphanRemoval = true

```
parent = em.find(Parent.class, parent.getId())
parent.getChildList().remove(0)

delete * from child where child_id = ?
```

- cacade와 orphanRemoval을 사용하면 부모엔티티를 통해서 자식의 생명주기를 관리함


### JPA의 데이터 타입 분류
엔티티 타입

- @Entity
- 데이터가 변해도 식별자로 지속해서 추적 가능

값 타입

- int, Integer, String 단순히 값으로 사용하는 타입
- 식별자가 없고 값만 있으므로 변경시 추적 불가

### @AttributeOverride: 속성 재정의
- 한 엔티티에서 같은 값 타입을 사용하면?
- 컬럼명이 중복됨.


```
Member class
@Embedded
private Address homeAddress;

@Embedded
@AttributeOverride(name="city", column=@Column(name = "OFFICE_CITY")),
@AttributeOverride(name="street", column=@Column(name = "OFFICE_STREET")
...
private Address officeAddress;

```

### 값 타입 공유 참조
- 임베디트 타입 같은 값을 변경하면 영향을 미침
- 부작용이 발생

```
address = new Address("city", "street")
member = new Member("A", 10, address)
member2 = new Member("B", 10, address)

em.persist(member)
em.persist(member2)

member.getAddress().setcity("newCity"). 
-> 여기에서 member, member2 모두 변경됨!!

address = new Address("newCity", "street")
address2 = new Address("city", "street")


```
### 값 타입 컬렉션의 제약사항
- 값 타입은 엔티티와 다르게 식별자 개념이 없다.
- 값 타입 컬렉션에 변경사항이 발생하면, 주인 엔티티에 값을 모두 삭제하고, 변경된 값을 적재 결과적으로는 **사용하지마라**

### JPA는 다양한 쿼리 방법을 지원

- JPQL
- JPA Criteria
- QueryDSL
- 네이티브 SQL

### JPQL
- JPA를 사용하면 엔티티 객체를 중심으로 개발
- 문제는 검색 쿼리
- 검색을 할때도 테이블이 아닌 엔티티 객체를 대상으로 검색
- 필요한 DB에서 불러오려면 결국 검색조건이 포함된 SQL이 필요

### QueryDSL
- 문자가 아닌 자바코드로 JPQL을 작성할 수 있음.
- JPQL 빌더 역할
- 컴파일 시점에 문법 오류를 찾을 수 있음
- 동적쿼리 작성 편리


### 주의점
> 영속성인 상태에서(DB에 반영되기 전) SpringJdbcTemplate를 이용해서 조회하게 되면 조회결과 0

### JPQL
- JPQL은 객체지향 쿼리언어이다. **엔티티를 대상으로 쿼리**한다.
- JPQL은 SQL을 추상화해서 특정데이터베이스 SQL에 의존하지 않는다.

### 프로젝션 - 여러값 조회
- select m from Member m
- Object로 반환
- new 명령어로 조회


### 페이징 API
-  JPA는 페이징을 다음 두 API로 추상화
-  setFirstResult(int startPosition): 조회시작위치
-  setMaxResults(int maxResult): 조회할 데이터 수

### Oracle 방언
```
SELECT * FROM
	  ( SELECT ROW_.* ROWNUM ROWNUM_
	  	FROM 
		  ( SELECT
			    M.ID AS ID,
			    M.AGE AS AGE,
			    M.TEAM_ID AS TEAM_ID,
			    M.NAME AS NAME
		    FROM MEMBER M
		    ORDER BY M.NAME 
	    )  ROW_
	    WHERE ROWNUM <= ?
    )
WHERE ROWNUM_ > ?

```

### 조인
- 내부조인
- 외부조인
- 세타조인


ex) 회원과 팀을 조인하면서, 팀 이름이 A 인 팀
```select m, t FROM Member m LEFT JOIN m.team t on t.name='A'	```
### JPA 서브쿼리 한계
- JPA 표준스펙에서는 where, having에서만 서브쿼리 가능
- 하이버네이트는 select절에서도 가능
- **inline view에서는 JPQL에서 불가능**


### JPQL 기본함수
- CONCAT
- SUBSTRING
- TRIM
- LOWER, UPPER
- LENGTH
- LOCATE
- ABS,SQRT,MOD
- SIZE, INDEX

> 각각의 함수들은 필요할때 검색해서 사용하면 될듯



