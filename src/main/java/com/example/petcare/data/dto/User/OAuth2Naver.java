package com.example.petcare.data.dto.User;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OAuth2Naver implements OAuth2Response{
    private final Map<String, Object> attribute;

    public OAuth2Naver(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }
}
