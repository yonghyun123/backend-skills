### 로그인 처리하기 - 서블릿 HTTP 세션
세션이라는 개념은 대부분의 웹 애플리케이션에 필요한 것이다. 어쩌면 웹이 등장하면서 부터 나온 문제이다.
서블릿은 세션을 위해 httpSession이라는 기능을 제공한다.

**HttpSession**
서블릿이 제공하는 HttpSession도 결국 우리가 직접 만든 SessionManager와 같은 방식으로 동작한다.
쿠키 이름이 JSESSIONID이고, 값은 추정 불가능한 랜덤값이다.
cookie: JSESSIONID=asdfasldkfjsl123123

**세션에 로그인 회원 정보 보관**
session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
세션에 데이터를 보관하는 방법은 request.setAttribute(..)와 비슷하다.

