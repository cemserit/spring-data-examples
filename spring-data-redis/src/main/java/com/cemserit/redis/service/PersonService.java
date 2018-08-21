package com.cemserit.redis.service;

import com.cemserit.redis.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {
    Optional<Person> getPerson(String email);

    List<Person> getPersons();

    Set<Person> getPersonEmailList();

    Set<Person> getPersonAgeEmailList(int age);

    void savePerson(Person person);

    void deletePerson(String email);
}
