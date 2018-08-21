package com.cemserit.redis.service;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.repository.PersonRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Person> getPerson(String email) {
        if (Strings.isNullOrEmpty(email)) {
            log.debug("Email cannot be null or empty");
            return Optional.empty();
        }
        return personRepository.findById(email);
    }

    @Override
    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Set<Person> getPersonEmailList() {
        return personRepository.filterByEmailList();
    }

    @Override
    public Set<Person> getPersonAgeEmailList(int age) {
        return personRepository.filterByAgeEmailList(age);
    }

    @Override
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

    @Override
    public void deletePerson(String email) {
        Optional<Person> personOptional = getPerson(email);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personRepository.delete(person);
            log.debug("Person[{}] deleted", person.getName());
        } else {
            log.debug("Person[{}] not found", email);
        }
    }
}
