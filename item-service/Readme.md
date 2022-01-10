### 프로젝트 요구사항

**상품 도메인 모델**
- 상품 ID 
- 상품명
- 가격
- 수량

**상품 관리 기능**
- 상품 목록
- 상품 상세
- 상품 등록
- 상품 수정

### 상품 도메인 개발

```
 RequiredArgsConstructor 해당 어노테이션을 통해 
 밑에 final 변수에 repository 객체의 의존성 주입을 가능하도록 함
      @Autowired
      public BasicItemController(ItemRepository itemRepository) {
          this.itemRepository = itemRepository;
      }
      
     위의 소스 역할을 하게 됨
```

## 상품 등록 처리
POST - HTML form
- content-type:application/x-www-form-urlencoded

**리다이렉트**
상품 수정은 마지막에 뷰 템플릿을 호출하는 대신에 상품 상세 화면으로 이동하도록 리다이렉트 호출한다.
- 스프링은 'redirect:/...'으로 편리하게 리다이렉트를 지원한다.

**참고**
HTML form 전송은 PUT, PATCH를 지원하지 않는다. GET, POST만 사용할 수 있다.
