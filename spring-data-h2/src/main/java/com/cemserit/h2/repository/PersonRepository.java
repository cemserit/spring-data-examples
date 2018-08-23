package com.cemserit.h2.repository;

import com.cemserit.h2.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    Optional<Person> findByEmail(String email);
}