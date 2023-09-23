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

 docker run hello-world

<img width="601" alt="스크린샷 2023-06-10 오후 6 29 22" src="https://github.com/yonghyun123/backend-skills/assets/15208005/f62f3a6b-469c-4731-9aa2-137e299f6165">

### 도커와 기존의 가상화 기술과의 차이를 통한 컨테이너 이해

<img width="557" alt="스크린샷 2023-06-10 오후 6 34 52" src="https://github.com/yonghyun123/backend-skills/assets/15208005/9048aa71-d76c-4833-ab05-d01a106736e6">

### 도커와 vm 공통점

도커 컨테이너와 가상 머신은 기본 하드웨어에서 격리된 환경 내에 어플리케이션을 배치하는 방법


<img width="508" alt="스크린샷 2023-06-10 오후 6 41 32" src="https://github.com/yonghyun123/backend-skills/assets/15208005/51aaad52-3a96-4b44-92af-2045a8eb9b61">

> 결과적으로는 컨테이너끼리 격리되어 있다.
> 


### 이미지로 컨테이너를 만들기
이미지는 응용 프로그램을 실행하는데 필요한 모든것을 포함하고 있습니다.

> 이미지
시작시 실행 될 명령어
run kakaotalk
카카오톡 스냅샷


이미지로 컨테이너를 만드는 순서
- Docker클라이언트에 docker run <이미지> 입력
- 도커 이미지에 있는 파일 스냅샷을 컨테이너 하드디스크에 옮김
- 이미지에서 가지고 있는 명령어(컨테이너가 실행될때 사용될 명령어)를 이용해서 카카오톡을 실행시켜줌


### Cgroup과 네임스페이스를 사용할 수 있는 이유

Cgroup과 네임스페이스는 리눅스 환경에서 사용함
> 하지만 맥os나 윈도우에서 사용하고 있음

<img width="699" alt="스크린샷 2023-09-16 오후 2 31 12" src="https://github.com/yonghyun123/poolside_back/assets/15208005/db656441-12f9-4667-8536-8a30f56a41d4">


### 도커이미지 내부 파일 구조 보기

ex) docker run 이미지이름 ls

ls-> 이 자리는 원래 이미지가 가지고 있는 시작 명령어를 무시하고 여기에 있는 커맨드를 실행하게 함.


ex) docker run hello-world ls 
-> 에러남 hello-world에 ls 커맨드를 사용할 수 있는 파일시스템이 없음.


### 컨테이너들 나열하기

docker run -> docker create + docker start

### docker stop vs docker kill

둘다 실행중인 컨테이너를 중지시킨다
1. Stop은 gracefully하게 중지
2. kill은 바로 중지

### 컨테이너 삭제

docker rm <아이디/이름>

- 실행중인 컨테이너는 중지 후 삭제가 가능 

도커 이미지를 삭제하고 싶다면
docker rmi <이미지 id>


한번에 컨테이너, 이미지, 네트워크 모두  삭제하고 싶다면

docker system prune





