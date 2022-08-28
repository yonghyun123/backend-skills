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




