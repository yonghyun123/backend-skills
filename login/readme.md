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

### 로그인 처리 - 필터, 인터셉터

서블릿 필터 - 소개
공통 관심 사항
요구사항을 보면 로그인 한 사용자만 상품 관리 페이지에 들어갈 수 있어야한다. 앞에서 로그인을 하지 않은 사용자에게는 상품관리 버튼이 보이지 않기 때문에 문제가 없어보인다. 하지만 url을 치고 들어가면 들어가진다.

더큰 문제는 향후 로그인과 관련된 로직이 변경될 때 모든 로직을 다 수정해야 하는 상황이 생긴다.

공통으로 관심이 있는것을 공통관심사(cross-cutting concern)이라고 한다. 여러 로직에서 공통으로 인증에 대해서 관심을 가지고 있다.

웹과 관련된 공통 관심사는 지금부터 설명할 서블릿 필터 또는 인터셉터를 사용하는 것이 좋다.

### 서블릿 필터 소개
필터는 서블릿이 지원하는 수문장이다. 필터의 특성은 다음과 같다.

**필터 흐름**
http 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러

**필터 제한**
http 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러 // 로그인 사용자
http 요청 -> WAS -> 필터(적절하지 않는 요청이라 판단, 서블릿 호출 X)

필터는 적절하지 않은 요청이라 판단하면 거기에서 끝을 낼 수 있다.

**필터 체인**
http 요청 -> WAS -> 필터 -> 필터 -> 필터 -> 필터 -> 서블릿 -> 컨트롤러

예를들어서 로그를 남기는 필터를 먼저 적용하고, 그 다음에 로그인 여부를 체크하는 필터를 만들 수 있다.

```
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE[{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
```

> 참고
> @ServletComponentScan @WebFilter(filterName="logFilter",urlPattern="/*")로 필터 등록이 가능하지만 필터 순서조절이 안된다. 따라서 FilterRegistrationBean을 이용하자.


> 실무에서 HTTP 요청시 같은 요청의 로그에 모두 같은 식별자를 자동으로 남기는 방법은 logback mdc로 검색하자


### 스프링 인터셉터 - 소개
**스프링 인터셉터 흐름**
```
http 요청 -> was -> 필터 -> 서블릿 -> 스프링 인터셉터 -> 컨트롤러
```

**예외 발생시**
- preHandle 컨트롤러 호출 전에 호출된다.
- postHandle 컨트롤러에서 예외가 발생하면 postHandler은 호출되지 않음
- afterCompletion: afterCompletion은 항상 호출된다. 예외를 파라미터로 받아서 어떤 예외가 발생했는지 로그로 출력 가능


