### 펫 돌봄 프로젝트

#### ERD
![](https://velog.velcdn.com/images/bang_se/post/98d19202-a21f-40fa-90a6-1f7e79c0b91b/image.png)

#### Project Stack
Mustache, Bootstrap, Ajax, Spring Boot, Spring Security, Spring Data JPA, JPQL, MySQL

#### 프로젝트 소개
이 프로젝트는 게시판을 통해 펫을 돌봐줄 사람과 돌봄이 필요한 사람 매칭 기능을 제공하고 있고 펫과 관련된 뉴스나 정보를 제공하고, 세션을 이용한 로그인&oauth2를 이용한 로그인을 제공하고 있습니다.

#### 구현 기능

* 회원 가입시 아이디와 이메일을 ajax통신으로 중복 체크하는 버튼 구현
![](https://velog.velcdn.com/images/bang_se/post/abdf492d-4180-42eb-8fb0-63bdd7533255/image.png)
* CRUD를 이용한 게시판 기능과 DTO, @Vaild를 이용한 검증 기능
* 페이징 처리 기능과 검색기능, 게시글 작성자일 경우 수정 삭제 기능
* 회원 가입시(자체 회원가입, Oauth2를 이용한 회원가입) 회원 정보가 담긴 User_entity 생성
```java
//CustomOAuth2UserService 일부 발쵀
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ...
        return processOAuth2User(oAuth2Response);
    }
   private OAuth2User processOAuth2User(OAuth2Response oAuth2Response) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(oAuth2Response.getEmail());
        
        //이미 등록된 사용자인 경우
        if (userRepository.existsByEmail(oAuth2Response.getEmail())) {
            try {
                throw new Exception();
            } catch (Exception e) {
                log.warn("이미 존재하는 아이디입니다.");
            }
            return new CustomOAuth2User(oAuth2Response, userEntity.get());
            //등록이 안된 사용자인 경우
        } else {
            return new CustomOAuth2User(oAuth2Response, registerUser(oAuth2Response));
        }
    }
    private UserEntity registerUser(OAuth2Response oAuth2Response) {
        return userRepository.save(UserEntity.builder()
                .username(oAuth2Response.getName())
                .email(oAuth2Response.getEmail())
                .joinProvider(oAuth2Response.getProvider())
                .role("ROLE_USER")
                .build());
    }
```
* 팻을 돌봐주는 역활, 펫 돌볼이 필요한 역활을 pet_care엔티티에 필요하다면 저장
![](https://velog.velcdn.com/images/bang_se/post/fc7e9ff6-ae90-41c4-a805-96755a658fd6/image.png)
* 팻을 돌봐주는 역활을 정하면 세부 입력사항을 추가 저장
![](https://velog.velcdn.com/images/bang_se/post/1e067616-9fe3-43fb-9966-88163d2d326a/image.png)
* 등록한 시간대별로 구인 게시판에 등록, 재 등록시 시간 갱신
* 회원 이름 변경, 역활과 세부 입력사항 변경을 위한 회원 정보 수정
* AOP를 이용해 사용자 로그인시 상단에 사용자 이름 출력
* 검색 기능, 페이징 처리 기능 제공
![](https://velog.velcdn.com/images/bang_se/post/9cfa67c5-69fd-4932-b17a-2e7f7ba656ec/image.png)
* 네이버 뉴스API를 가져와 반려동물에 관한 뉴스 제공


#### 화면
메인 화면
![image](https://github.com/lawijdo201/PetCare/assets/86397164/f20cdcac-e6b3-4d98-8a9f-5109b074270d)

읽을 거리
![image](https://github.com/lawijdo201/PetCare/assets/86397164/a965d51a-5e79-40f2-90a6-4dcc47cb28de)

내 아이 찾기
![image](https://github.com/lawijdo201/PetCare/assets/86397164/b1e24f22-79ea-4a27-92c0-78ea8d6ed680)

사료 칼로리 계산
![image](https://github.com/lawijdo201/PetCare/assets/86397164/57f93c20-8204-4663-8a1d-1d1996740b31)
![image](https://github.com/lawijdo201/PetCare/assets/86397164/d5cb19d2-fa40-446e-82ba-0cf277965f0f)
![image](https://github.com/lawijdo201/PetCare/assets/86397164/fa310d2b-0e31-4fd0-a5b3-6ce81bf18005)
![image](https://github.com/lawijdo201/PetCare/assets/86397164/60cdf56e-8418-4c56-92f1-7a3ac48fc8b5)

1일 권장 칼로리
![image](https://github.com/lawijdo201/PetCare/assets/86397164/0eed386a-21de-42f3-b7d8-255905681c1d)
![image](https://github.com/lawijdo201/PetCare/assets/86397164/87c519c1-e7b5-441c-a58f-e68319e77495)

커뮤니티
![image](https://github.com/lawijdo201/PetCare/assets/86397164/fb01a975-86db-4027-9cf4-6b7ef4fbebe5)
![image](https://github.com/lawijdo201/PetCare/assets/86397164/a7493cee-f0d6-466b-9dd8-152a0b62a623)

회원 정보
![image](https://github.com/lawijdo201/PetCare/assets/86397164/3685f24e-9c61-4666-a592-ec54e85a0c14)





