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

스프링 부트 메시지 소스 기본값
spring.messages.basename=messages,config.i18n.messages

'MessageSource'를 스프링빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면, messages라는 이름으로 기본 등록된다. 따라서 messages_en.properties, messages_ko.properties, messages.properties 파일만 등록하면 자동으로 인식된다.


**참고**
파라미터 사용법
<p th:text="#{hello.name(${item.itemName})}"></p>

### 로그인 처리 및 쿠키 세션

**보안문제**
쿠키의 값을 임의로 변경할 수 있다.
- 클라이언트가 쿠키를 강제로 변경하면 다른 사용자가 된다.

쿠키에 보관된 정보는 훔쳐갈 수 있다.
- 반약 쿠키에 개인정보나, 신용카드 정보가 있다면?
- 이 정보가 웹 브라우저에도 보관되고, 네트워크 요청마다 계속 클라이언트에서 서버로 전달된다.
- 쿠키의 정보가 네트워크 전송 구간에서 털릴 수 있다.

**대안**
- 쿠키에 중요한 값을 노출하지 않고, 사용자별로 예측 불가능한 임의의 토큰을 노출하고, 서버에서 토큰과 사용자 ID를 매핑해서 인식한다. 그리고 서버에서 토큰을 관리한다.
- 토큰은 해커가 임의의 값을 넣어도 찾을 수 없도록 예상 불가능 해야한다.

