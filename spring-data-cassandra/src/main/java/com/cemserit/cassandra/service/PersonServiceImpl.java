package com.cemserit.cassandra.service;

import com.cemserit.cassandra.model.Person;
import com.cemserit.cassandra.repository.PersonRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Person> getPerson(UUID uuid) {
        if (uuid == null) {
            log.debug("UUID cannot be null");
            return Optional.empty();
        }
        return personRepository.findByUuid(uuid);
    }

    @Override
    public List<Person> getPerson(int age) {
        return personRepository.findByAge(age);
    }

    @Override
    public List<Person> getPerson(String email, int age) {
        if (Strings.isNullOrEmpty(email)) {
            log.debug("Email cannot be null or empty");
            return Collections.emptyList();
        }
        return personRepository.findByEmailAndAge(email, age);
    }

    @Override
    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public void savePerson(Person person) {
        if (person == null) {
            log.debug("person cannot be null");
            return;
        }

        if (person.getUuid() == null) {
            log.debug("person uuid cannot be null");
            return;
        }

        personRepository.save(person);
        log.debug("Person[{}] saved", person.getName());
    }

    @Override
    public void deletePerson(UUID uuid) {
        Optional<Person> personOptional = getPerson(uuid);
        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
            log.debug("Person[{}] deleted", personOptional.get().getName());
        } else {
            log.debug("Person[{}] not found", uuid);
        }
    }
}
