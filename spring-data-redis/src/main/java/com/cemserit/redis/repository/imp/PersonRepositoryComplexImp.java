package com.cemserit.redis.repository.imp;

import com.cemserit.redis.repository.PersonRepositoryComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@SuppressWarnings("unchecked")
public class PersonRepositoryComplexImp implements PersonRepositoryComplex {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setHash(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public void setHash(String key, Map keyValues) {
        redisTemplate.opsForHash().putAll(key, keyValues);
    }

    @Override
    public Object getHash(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void deleteHash(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }
}