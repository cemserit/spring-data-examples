package com.cemserit.h2.service;

import com.cemserit.h2.model.Person;
import com.cemserit.h2.repository.PersonRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String email) {
        if (Strings.isNullOrEmpty(email)) {
            log.debug("Person email cannot be null or empty");
            return Optional.empty();
        }
        return personRepository.findByEmail(email);
    }

    public Optional<Person> getPerson(UUID uuid) {
        if (uuid == null) {
            log.debug("Person uuid cannot be null");
            return Optional.empty();
        }
        return personRepository.findById(uuid);
    }

    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public void savePerson(Person person) {
        if (person == null) {
            log.debug("Person cannot be null");
            return;
        }

        if (Strings.isNullOrEmpty(person.getEmail())) {
            log.debug("Person email cannot be null or empty");
            return;
        }
        personRepository.save(person);
        log.debug("Person[{}] saved", person.getName());
    }

    public void deletePerson(String email) {
        Optional<Person> personOptional = getPerson(email);
        personOptional.ifPresent(this::deletePerson);
    }

    public void deletePerson(UUID uuid) {
        Optional<Person> personOptional = getPerson(uuid);
        personOptional.ifPresent(this::deletePerson);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
        log.debug("Person[{}] deleted", person.getName());
    }
}