
### 1. 프로젝트 구조

![diagram](/diagram1.PNG)

### 2. 사용 및 실행방법

- MySQL 설치
- [ERD](/erd_script.txt) 실행하여 테이블 구성하기
- TEST 클래스의 CREATE 메소드를 아래 순서대로 실행, 테스트 데이터 생성하기 
	- CategorySample.java
	- PartnerSample.java
	- ItemSample.java
	- UserSample.java
	- OrderDetailSample.java

- http://localhost:8080/pages/user 유저 데이터 가져오는지 확인하기

### 3. 테스트하기

- src\test\java\com\example\test\repository 경로에 생성된 test클래스 실행
- 추가 개발사항 테스트 클래스에서 작성 후 src\main 경로에 작성하기
- REST API TEST 확장도구 설치(ex: Talend API Tester, postman)
- [API_PARAM_TEST](/restapiParam.txt) 파일을 열어 request api 구조에 맞게 테스트 진행
 

