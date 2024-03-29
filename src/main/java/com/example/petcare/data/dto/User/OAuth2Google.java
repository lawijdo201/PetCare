package com.example.petcare.data.dto.User;

import java.util.Map;

public class OAuth2Google implements OAuth2Response{
    private final Map<String, Object> attribute;

    public OAuth2Google(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public Join_Provider getProvider() {
        return Join_Provider.Google;
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
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
