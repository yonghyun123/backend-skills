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


추가적인 내용

### @RequestBody 객체 변환

```
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
```

**@RequestBody 객체 파라미터**
- @RequestBody에 직접 만든 객체를 지정할 수 있다.

'HttpEntity', '@RequestBody'를 사용하면 HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 우리가 원하는 문자나 객체 등으로 변환해준다.
HTTP 메시지 컨버터는 문자 뿐만 아니라 JSON도 객체로 변환해주는데, 우리가 방금 V2에서 했던 작업을 대신 처리해준다.
자세한 내용은 HTTP 메시지 컨버터에서 다룬다.


**@RequestBody는 생략불가능**
- 스프링은 @ModelAttribute, @RequestParam 해당 생략시 다음과 같은 규칙을 적용한다.
- String, int, Integer같은 단순 타입 = @RequestParam
- 나머지 = @ModelAttribute


- @RequestBody요청
	- JSON 요청 -> HTTP 메시지 컨버터 -> 객체
- @ResponseBody 응답
	- 객체 -> HTTP 메시지 컨버터 -> JSON 응답

### HTTP 응답 - 정적리소스, 뷰 템플릿
응답 데이터는 이미 앞부분에서 다룬내용이지만, 응답 부분에 초점을 맞춰 정리한다.
응답 데이터를 만드는 방법은 크게 3가지이다.
- 정적 리소스
	- 웹 브라우저에 정적인 HTML, css, js을 제공
- 뷰 템플릿 사용
	- 웹 브라우저에 동적인 HTML을 제공하는 뷰 템플릿 사용
- HTTP 메시지 사용
	- HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON같은 형식으로 데이터를 실어 보낸다

	
### 정적리소스
스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공한다.
/static, /public, /resources/, /META-INF/resources

'src/main/resources' 는 리소스를 보관하는 곳이고, 또 클래스패스의 시작 경로이다.
따라서 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공한다.

### 뷰템플릿
뷰템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다.
일반적으로 HTML을 동적으로 만드는데 사용한다.

```
@RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                               .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        Model data = model.addAttribute("data", "hello!!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        Model data = model.addAttribute("data", "hello!!");

   }
```

**String을 반환하는 경우 - View or HTTP 메시지**
> @ResponseBody가 없으면 response/hello로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링을 한다. @ResponseBody가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 reponse/hello라는 문자가 입력된다.


## HTTP 응답 - HTTP API 메시지 바디에 직접 입력
HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다. HTTP 요청에서 응답까지 대부분 다루었으므로 이번시간에 정리를 한다.

**참고**
> HTML이나 뷰 템플릿을 사용해도 HTTP응답 메시지 바디에 HTML 데이터가 담겨서 전달된다. 여기서 설명하는 내용은 정적리소스나 뷰 템플릿을 거치지 않고, 직접 HTTP응답 데이터를 전달하는 경우를 말한다.
  
  ```
  @Slf4j
@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);
        return new ResponseEntity<HelloData>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);
        return helloData;
    }
}

  ```
  
##   HTTP 메시지 컨버터
뷰 템플릿으로 HTML을 생성해서 응답하는 것이 아니라 HTTP API처럼 JSON 데이터를 HTTP 메시지 바디에서 직접 읽거나 쓰는 경우 HTTP 메시지 컨버터를 사용하면 편리하다.
HTTP 메시지 컨버터를 설명하기 전에 아래를 보자

사진

