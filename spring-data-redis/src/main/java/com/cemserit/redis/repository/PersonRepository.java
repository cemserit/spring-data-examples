package com.cemserit.redis.repository;

import com.cemserit.redis.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String>, PersonRepositoryCustom {
}
