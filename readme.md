### 도커와 CI환경


https://github.com/jaewonhimnae/docker-codes 

### 도커를 쓰는 이유
도커 없이 다운받고 실행

<img width="560" alt="스크린샷 2023-06-04 오전 9 01 31" src="https://github.com/yonghyun123/backend-skills/assets/15208005/42701c69-eb52-4e91-98f9-70073663a74e">

**Redis 받는 과정**
레디스 홈페이지 -> 다운로드
-> Installation -> 에러 발생 -> wget 없어서...

$ docker run -it redis
-> 한방에 해결


### 도커란 무엇인가?
- 컨테이너를 사용하여 응용프로그램을 더 쉽게 만들고 배포하고 실행할 수 있도록 설계된 도구, 오픈소스 가상화 플랫폼

#### 컨테이너는요?
- 다양한 프로그램, 실행환경을 컨테이너로 추상화하고 동일한 인터페이스를 제공 및 배포하는 것(Mysql, Redis, Webpack..etc)
- AWS, Asure, Google cloud

### 도커이미지와 컨테이너
- 컨테이너는 코드와 종속성을 패키지화하여 빠르고 안정적으로 실행되도록 하는 소프트웨어 단위다.

- 컨테이너 이미지는 코드, 런타임, 시스템 도구, 라이브러리 및 설정과 같은 프로그램을 실행하는 모든 것을 포함하는 가볍고 독립적인 소프트웨어 패키지이다.

<img width="506" alt="스크린샷 2023-06-08 오후 11 26 37" src="https://github.com/yonghyun123/backend-skills/assets/15208005/9b224792-499c-42ee-bdfa-ff2467250e8c">

> 도커 이미지는 프로그램을 실행하는데 필요한 설정이나 종속성을 갖고 있으며, 도커 이미지를 이용하여 컨테이너를 생상하면 도커 컨테이너를 이용하여 프로그램을 실행한다.


### 도커설치 in Mac

### CLI에서 커맨드 입력하기

$ docker run hello-world

<img width="601" alt="스크린샷 2023-06-10 오후 6 29 22" src="https://github.com/yonghyun123/backend-skills/assets/15208005/f62f3a6b-469c-4731-9aa2-137e299f6165">



