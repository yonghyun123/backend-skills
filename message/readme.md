#메시지, 국제화 소개

### 메시지
기획자가 화면에 보이는 문구가 마음에 들지 않는다고, "상품명"이라는 단어를 모두 "상품이름"으로 고쳐달라고 하면 어떻게 해야할까?

화면을 모두 찾아다니면서 고쳐야한다.

이런 다양한 메시지를 한곳에서 관리하도록 하는 기능을 메시지 기능이라고 한다.

```
item=상품
item.id=상품 ID
item.itemName=상품명
item.price=가격
item.quantity=수량
```

ex)
<label for="itemName" th:text="#{item.itemName}"></label>

