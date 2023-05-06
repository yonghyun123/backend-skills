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





