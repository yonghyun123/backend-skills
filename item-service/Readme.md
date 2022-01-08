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