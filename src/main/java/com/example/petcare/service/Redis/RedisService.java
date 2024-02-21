package com.example.petcare.service.Redis;

import com.example.petcare.entity.UserCareService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisService {
    private final RedisTemplate<String, UserCareService> redisTemplate;
    private static final long PETCARE_EXPIRE_TIME = 3 * 60 * 1000L;

    public RedisService(RedisTemplate<String, UserCareService> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addValue(String key, UserCareService data) {
        long score = System.currentTimeMillis() + PETCARE_EXPIRE_TIME;
        redisTemplate.opsForValue().set(key, data, score);
        redisTemplate.expire(key, PETCARE_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    public List<UserCareService> getAllValues() {
        // Redis의 모든 키를 가져옴
        Set<String> keys = redisTemplate.keys("*");

        // UserCareService 객체를 저장할 리스트
        List<UserCareService> values = keys.stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .collect(Collectors.toList());

        return values;
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public void expireValues(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    public void setHashOps(String key, Map<String, String> data) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.putAll(key, data);
    }

    @Transactional
    public String getHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        return Boolean.TRUE.equals(values.hasKey(key, hashKey)) ? (String) redisTemplate.opsForHash().get(key, hashKey) : "";
    }

    public void deleteHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.delete(key, hashKey);
    }

    public boolean checkExistsValue(String value) {
        return !value.equals("false");
    }
}