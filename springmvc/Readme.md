### 로깅 간단히 알아보기

앞으로 로그를 사용하기 때문에 로그에 대해 알아보자. 별도의 로깅 라이브러리를 사용한다.

**로깅 라이브러리**

- SLF4J
- Logback


### 요청매핑에서 가장 중요한
@PathVariable

```
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }
```

**회원관리 API**
- 회원목록조회: GET '/users'
- 회원등록: POST '/users'
- 회원조회: GET '/users/{userId}'
- 회원수정: PATCH '/users/{userId}'
- 회원삭제: DELETE '/users/{userId}'

### HTTP 요청 - 기본, 헤더조회
애노테이션 기반의 스프링 컨트롤러는 다양한 파라미터를 지원한다.


```
@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String,String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("host={}", host);
        log.info("cookie={}", cookie);

        return "ok";
    }
}

```

MultiValueMap
> 하나의 키값에 여러 value를 셋팅할 수 있다.
> 

