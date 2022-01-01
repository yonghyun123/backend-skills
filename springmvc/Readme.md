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


### HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form

클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 3가지를 사용한다.

- Get- 쿼리 파라미터
- POST HTML form
- HTTP message body에 데이터를 직접 담아서 요청
	- 데이터형식은 주로 JSON


ex) get 방식
http://localhost:8-8-/request-param?username=hello
ex) post form 전송

```
POST /request-param ...
content-type: application/x-www-form-urlencoded

username=hello
```

위의 두 방식이든 형식이 같으므로 구분없이 조회가능
이것을 간단히 **요청 파라미터**라 한다.

### HTTP 요청 파라미터 - @ModelAttribute 적용
```
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("helloData={}", helloData);

        return "ok";// @Controller에서 ok 로 반환시 해당 파일을 찾음 Error resolving template [ok],
    }
```

Spring MVC는 @ModelAttribute가 있으면 다음을 실행한다.
- HelloData 객체 생성
- 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
- 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 바인딩한다.
- 파라미터 이름이 username이면 setUsername() 메서드를 호출 및 값 셋팅

@ModelAttribute 생략

```
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("helloData={}", helloData);

        return "ok";// @Controller에서 ok 로 반환시 해당 파일을 찾음 Error resolving template [ok],
    }
```

스프링은 다음과 같은 규칙을 적용한다
> String, int, Integer와 같은 단순타입 @RequestParam
> 나머지 @ModelAttribute(argument resolver로 지정해둔 타입 외)


###HTTP 요청 메시지 - 단순 텍스트

HTTP message body에 데이터를 직접 담아서 요청
- HTTP API에서 주로 사용, JSON, XML, TEXT
- 데이터 형식은 JSON
- POST, PUT, PATCH

요청 파라미터와 다르게 HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우는 @RequestParam, @ModelAttribute를 **사용할 수 없다.**

**HttpEntity**

```
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("message body={}", messageBody);
        return new HttpEntity<>("ok");
    }
```

- HttpEntity: HTTP header, body 정보를 편리하게 조회
	- 메시지 바디 정보를 직접 조회
	- 요청 파라미터를 조회하는 기능과 관계 없음
- HttpEntity는 응답에도 사용 가능
	-  메시지 바디 정보 직접 반환
	-  헤더 정보 포함 가능
	-  view 조회 x

```

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("message body={}", messageBody);
        return "ok";
    }
```

**@RequestBody**
@RequestBody를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회할 수 있다. 참고로 헤더 정보가 필요하다면 HttpEntity를 사용하거나, @RequestHeader를 사용하면 된다.
이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam과 @ModelAttribute와는 전혀 관계가 없다.

"요청 파라미터 vs HTTP 메시지 바디"
- 요청 파라미터를 조회하는 기능: @RequestParam, @ModelAttribute
- HTTP 메시지 바디를 직접 조회하는 기능 @RequestBody



