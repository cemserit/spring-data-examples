package com.cemserit.redis.repository;

import com.cemserit.redis.model.Person;

import java.util.Set;

// Alternative sample
public interface PersonRepositoryCustom {
    Set<Person> filterByEmailList();

    Set<Person> filterByAgeEmailList(int age);
}