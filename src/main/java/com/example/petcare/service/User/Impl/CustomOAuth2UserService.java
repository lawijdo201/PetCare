package com.example.petcare.service.User.Impl;

import com.example.petcare.data.dto.User.*;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info(oAuth2User.getAttributes().toString());
        OAuth2Response oAuth2Response = null;

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("naver")) {
            oAuth2Response = new OAuth2Naver(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new OAuth2Google(oAuth2User.getAttributes());
        } else {
            return null;
        }
        String role = "ROLE_USER";
        return processOAuth2User(oAuth2Response);
        //return new CustomOAuth2User(oAuth2Response, role);
    }
    private OAuth2User processOAuth2User(OAuth2Response oAuth2Response) {
        //oAuth2UserInfo = oAuth2Response userRepository.findByUsername(oAuth2Response.getName());
        Optional<UserEntity> userEntity = userRepository.findByEmail(oAuth2Response.getEmail());
        log.info(oAuth2Response.getEmail());
        log.info(String.valueOf(userRepository.existsByEmail(oAuth2Response.getEmail())));
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
}
