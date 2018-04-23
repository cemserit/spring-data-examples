package com.cemserit.cassandra.repository;

import com.cemserit.cassandra.model.Person;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface PersonRepository extends CrudRepository<Person, UUID> {

    // Don't use ALLOW FILTERING.
    // If you use ALLOW FILTERING you will have performance problems

    // Crud syntax findBy + params
    Optional<Person> findByUuid(UUID uuid);

    @Query(allowFiltering = true)
    Optional<Person> findByEmailAndAge(String email, int age);

    // Custom query
    @Query("SELECT * FROM PERSON WHERE age=:age ALLOW FILTERING")
    Stream<Person> findByAgeStream(@Param("age") int age);
}
