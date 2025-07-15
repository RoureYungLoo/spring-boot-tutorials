package com.xxx.springboot15redis.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisService {
    private final Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
        logger.info("set key:" + key + " value:" + value);
    }

    public String get(String key) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        logger.info("get key:{}", key);
        return operations.get(key);
    }

    public void setHash(String key, String filed, String value) {
        HashOperations<String, Object, Object> operations = stringRedisTemplate.opsForHash();
        operations.put(key, filed, value);
    }

    public String getHash(String key, String filed) {
        HashOperations<String, Object, Object> operations = stringRedisTemplate.opsForHash();
        return (String) operations.get(key, filed);
    }


    public void leftPush(String key, String value) {
        ListOperations<String, String> operations = stringRedisTemplate.opsForList();
        operations.leftPush(key, value);
    }

    public List<String> range(String key, int start, int end) {
        ListOperations<String, String> operations = stringRedisTemplate.opsForList();
        return operations.range(key, start, end);
    }
}
