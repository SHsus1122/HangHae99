# Spring 입문 주차 개인 과제

간단한 게시판을 구현해서 CRUD 작동까지 해보기

## 변동사항
2022-12-06
4주차 과제로 인해서 내용에 많은 변화가 생겨서 아래의 내용들은 이전 파일에 대한 주석 및 내용들
추후에 따로 파일을 빼서 추가할 예정

<br/>

## 요구사항

1. 아래의 요구사항을 기반으로 Use Case 그려보기
    - 손으로 그려도 됩니다.
    - cf. [https://narup.tistory.com/70](https://narup.tistory.com/70)
2. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
3. 게시글 작성 API
    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기
4. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 
    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
5. 선택한 게시글 수정 API
    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
6. 선택한 게시글 삭제 API
    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
    
<br/>

## API 명세서

<br/>

|Method|URL|Request|Response|
|:---|:---|:---|:---|
|GET|/api/boards||{<br/>"crtAt" : "날짜" ,<br/>"modAt" : "날짜" ,<br/>"id" : 1 ,<br/>"boardName" : "게시글이름" ,<br/>"username" : "유저명" ,<br/>"contents" : "내용"<br/>}|
|GET|/api/boards/{id}|{<br/>"id": "id"<br/>}|{<br/>"crtAt" : "날짜" ,<br/>"modAt" : "날짜" ,<br/>"id" : 1 ,<br/>"boardName" : "게시글이름" ,<br/>"username" : "유저명" ,<br/>"contents" : "내용"<br/>}|
|POST|/api/boards|{<br/>"boardName" : "boardName" ,<br/>"username" : "username" ,<br/>"password" : "password" ,<br/>"contents" : "contents"<br/>}|{<br/>"crtAt" : "날짜" ,<br/>"modAt" : "날짜" ,<br/>"id" : 1 ,<br/>"boardName" : "게시글이름" ,<br/>"username" : "유저명" ,<br/>"contents" : "내용"<br/>}|
|PUT|/api/boards/{id}|{<br/>"boardName" : "boardName" ,<br/>"username" : "username" ,<br/>"password" : "password" ,<br/>"contents" : "contents"<br/>}|{<br/>"crtAt" : "날짜" ,<br/>"modAt" : "날짜" ,<br/>"id" : 1 ,<br/>"boardName" : "게시글이름" ,<br/>"username" : "유저명" ,<br/>"contents" : "내용"<br/>}|
|DELETE|/api/boards/{id}|{<br/>"password" : "password"<br/>}|{<br/>"msg" : "msg" ,<br/>"result" : "true"<br/>}|


<br/>
<br/>

## 과제 제출관련 답변


---

**1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body) <br/>**
>수정시에는 `@PathVariable` 으로 `id` 값을 가져와서 "localhost:8080/api/board/1" 이렇게 `url` 링크로 접근해서 
마지막 뒤에 붙은 값을 기준으로 삭제 및 업데이트에서 파라미터로 이용해서 사용했다. <br/>
그리고 나서 `requestDto로` 비밀번호 값만을 가져와서 입력하여 삭제하는데 `@RequestBody` 를 사용했다. <br/>
이 때에는 `JSON` 형태로 또 입력된 값 그 자체를 가져오기 위해서 사용하였으며 이를 이용해 비밀번호 값을 가져와서 
비교하는데 사용했다.


**2. 어떤 상황에 어떤 방식의 request를 써야하나요? <br/>**
>● @PathVariable <br/>
우선 이번처럼 `uri` 경로의 일부를 파라미터로 이용하는 방법을 통해 내가 원하는 게시글을 찾거나 또는 상품페이지 같은 경우를 <br/>
찾고 또 수정하고 하는데 있어서 매우 유용한것 같아서 이러한 상황에서 `@PathVariable` 이 매우 유용한 것 같다. <br/>
https://u0hun.tistory.com/21 이런 식으로 글마다 `@PathVariable` 를 사용해서 주소로 활용하는것 같다. <br/>
● @RequestParam <br/>
GET 방식으로 넘어오는 경우에 용이한데 그 예로 들자면 https://newsstand.naver.com/?list=&pcode=422 <br/>
위 링크 처럼 주소 뒤에 `key` 와 `value` 받는 모습처럼 기능한다. 이 또한 사이트 주소별로 구분짓기에 매우 용이하다.
즉, `uri` 의 뒤에 부튼 파라미터의 값을 가져오는 방식이다. <br/>
● @RequestBody <br/>
이런식의 경우에는 객체를 반환해주기 때문에 `response` 로 받아올 때 `JSON` 객체로 받아오기에 유용하다. <br/>
다른 기존의 방식들처럼 통째로 넘기기 보다는 딱 필요한 부분의 데이터만을 넘기기 때문에 지금처럼 게시판에서
삭제 및 수정에서 비밀번호의 값만을 `JSON` 객체로 사용하기에 이런 상황에 유용하다.
	
**3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요? <br/>**
> 어느정도 그렇게 했다고 생각합니다. <br/>
게시판의 글을 업로드, 삭제, 수정, 긁어오기 등... `REST API` 를 말하는 핵심요소인 `CRUD` 에 있어서 필요한 기능은 기본적으로 다 구현했기 때문에 `Restfull` 하게 설계했다고 생각합니다. <br/>
하지만 아직 `DTO` 를 세세하게 나누고 메소드명만을 보고 바로바로 이 기능에 대해 이해하는 부분에 있어서 즉, 메소드명을 잘 만드는 부분에 있어서 좀 부족하다고 생각합니다.


**4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service) <br/>**
>● Controller <br/>
클라이언트의 요청을 받아서 `service로` 날려보내서 중간다리 역할을 하는 부분 <br/>
즉, 게시판에서 `CRUD` 에 관련한 기능들을 수행하기 요청과 응답을 날려주는 부분으로서만의 기능을 하게 구현했다고 생각합니다. <br/>
● Service <br/>
흔히들 말하는 "비즈니스 로직" 을 실행하는 부분으로 이번 게시판에서는 값을 `Repo` 에서 가져오고 그것을 `Dto` 에 넣으며, <br/>
그 과정에서 `DB` 에 접근 및 중간에서 생기는 예외 사항들에 대한 부분들을 처리하는등 서비스의 역할을 잘 적용했다고 생각합니다. <br/>
● Repository <br/>
엔티티에 의해 생성된 게시판 자료들을 접근하기 위한 코드가 들어가 있다. <br/>
이를 이용해서 `board` 의 정보를 긁어와서 담는것이 가능해지기 때문에 일반적으로 `DB` 접근 관려 코드가 모인 장소로서의 기능을
비슷하게나마 사용하였다고 생각합니다.

<br/>

-----
