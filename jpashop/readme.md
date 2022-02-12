# 도메인 분석 설계

### 목차
 - 요구사항 분석
 - 도메인 모델과 테이블 설계
 - 엔티티 클래스 개발
 - 엔티티 설계시 주의점

- 회원 기능
	- 회원 등록
	- 회원 조회
- 상품 기능
	- 상품 등록
	- 상품 수정
	- 상품 조회
- 주문 기능
	- 상품 주문
	- 주문 내역 조회
	- 주문 취소
- 기타 요구사항
	- 상품은 재고 관리가 필요하다.
	- 상품의 종류는 도서, 음반, 영화가 있다.
	- 상품을 카테고리로 구분할 수 있다.
	- 상품 주문시 배송 정보를 입력할 수 있다.

	
<img width="601" alt="스크린샷 2022-02-05 오후 4 28 29" src="https://user-images.githubusercontent.com/15208005/152632955-bae188bb-567c-456d-9254-e57ff2039332.png">


<img width="572" alt="스크린샷 2022-02-05 오후 4 30 39" src="https://user-images.githubusercontent.com/15208005/152633018-677ea7b7-e554-4e72-a5c9-57c18f7d69af.png">


<img width="576" alt="스크린샷 2022-02-05 오후 4 59 42" src="https://user-images.githubusercontent.com/15208005/152633749-2f27ee77-c6a8-46bc-ba1d-f23abebd85f8.png">

> **참고**: 외래키가 있는곳을 연관관계의 주인으로 정해라
> 연관관계의 주인은 단순히 외래키를 누가 관리하냐의 문제이지 비즈니스상 우위에 있다고 주인으로 정하면 안도니다.예를 들어서 자동차와 바퀴가 있으면, 일대다 관계에서 항상 다쪽에 외래키가 있으므로 외래키가 있는 바퀴를 연관관계의 주인으로 정하면 된다. 물론 자동차를 연관관계의 주인으로 정하는 것이 불가능 한 것은 아니지만, 자동차를 연관관계의 주인으로 정하면 자동차가 관리하지 않는 바퀴 테이블의 외래키 값이 업데이트 되므로 관리와 유지보수가 어렵고, 추가적으로 별도의 업데이트 쿼리가 발생하는 성능 문제도 있다.


# 엔티티 클래스 개발
- 예제에서는 설명을 쉽게하기 위해 클래스에 Getter, Setter를 모두 열고 최대한 단순하게 설계
- 실무에서는 가급적 Getter는 열어두고, Setter는 꼭 필요한 경우에만 사용하는 것을 추천

> 참고: 이론적으로 Getter, Setter모두 제공하지 않고, 꼭 필요한 별도의 메서드를 제공하는게 가장 이상적이다. 실무에서는 엔티티의 데이터는 조회할 일이 너무 많으므로, Getter의 경우 모두 열어두는 것이 편리하다. Getter는 아무리 호출해도 호출 하는 것만으로 어떤 일이 발생하지는 않는다. 하지만 Setter는 문제가 다르다 Setter를 호출하면 데이터가 변한다. Setter를 막 열어두면 가까운 미래에 엔티티가 왜 변경되는지 추적하기 점점 힘들어진다. 엔티티를 변경할 때는 Setter 대신에 변경 지점이 명확하도록 변경을 위한 비즈니스 메서드를 별도로 제공해야 한다.


# 엔티티 설계시 주의점

### 엔티티에는 가급접 Setter를 사용하지 말자
변경 포인트가 많아서, 유지보수가 어렵다.

### 모든 연관관계는 지연로딩으로 설정!
- 즉시로딩은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다. 특히 JQPL을 사용할때 N+1 문제가 자주 발생한다.
- 실무에서는 모든 연관관계는 지연로딩으로 설정해야 한다.
- ㅇ연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.
- @XToOne(OneToOne, ManyToOne)관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정해야 한다.

```java
Member member = new Member();
System.out.print(member.getOrders().getClass());

em.persist(member);
System.out.print(member.getOrders().getClass());

//결과
class java.util.ArrayList
clas org.hibernate.collection.internal.PersistentBag

```

```
@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
```

private List<Order> orders = new ArrayList<>();
위의 방식이 제일 좋음

### 컬렉션은 필드에서 초기화 하자
- 하이버네이트는 엔티티를 영속화 할때, 컬렉션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다. 만약 getOrders()처럼 임의의 메서드에서 컬렉션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문제가 발생할 수 있다. 따라서 필드레벨에서 생성하는 것이 가장 안전하고, 간결하다.




 
 
 