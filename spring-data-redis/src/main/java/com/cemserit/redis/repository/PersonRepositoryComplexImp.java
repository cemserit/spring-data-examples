package com.cemserit.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class PersonRepositoryComplexImp implements PersonRepositoryComplex {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setMapCache(String key, String hashKey, String value){
        redisTemplate.opsForHash().put();
    }

}
