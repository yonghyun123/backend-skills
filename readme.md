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

