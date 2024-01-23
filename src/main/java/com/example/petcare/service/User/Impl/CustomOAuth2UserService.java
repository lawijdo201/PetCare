package com.example.petcare.service.User.Impl;

import com.example.petcare.data.dto.User.CustomOAuth2User;
import com.example.petcare.data.dto.User.OAuth2Google;
import com.example.petcare.data.dto.User.OAuth2Naver;
import com.example.petcare.data.dto.User.OAuth2Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
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
        return new CustomOAuth2User(oAuth2Response, role);
    }
}
