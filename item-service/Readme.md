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

## PRG Post/Redirect/Get
지금까지 진행한 상품 등록 처리 컨트롤러는 심각한 문제가 있다.
상품을 등록하고 새로고침하면 중복등록된다.

##  RedirectAttribute
리다이렉트는 좋았지만 고객 입장에서는 저장이 된것인지 안된 것인지 확신이 들지 않는다.
그래서 저장이 잘 되었으면 상품 상세 화면에 "저장되었습니다"라는 메시지를 보여달라는 요구사항이 왔다.

```
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }
```

```
<h2 th:if="${param.status}" th:text="'저장 완료'"></h2>
```
