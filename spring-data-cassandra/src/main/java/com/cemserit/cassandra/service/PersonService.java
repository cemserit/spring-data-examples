package com.cemserit.cassandra.service;

import com.cemserit.cassandra.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Optional<Person> getPerson(UUID uuid);

    List<Person> getPerson(int age);

    List<Person> getPerson(String email, int age);

    List<Person> getPersons();

    void savePerson(Person person);

    void deletePerson(UUID uuid);
}
