package com.cemserit.redis.repository.imp;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.repository.PersonRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private static final String PERSON_KEY = "Person";
    private static final String AGE_KEY_PREFIX = "Person:age:";

    @Autowired
    private RedisTemplate<String, Person> redisTemplate;

    @Override
    public Set<Person> filterByEmailList() {
        return redisTemplate.opsForSet().members(PERSON_KEY);
    }

    @Override
    public Set<Person> filterByAgeEmailList(int age) {
        return redisTemplate.opsForSet().members(AGE_KEY_PREFIX + age);
    }
}