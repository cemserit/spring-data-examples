package com.cemserit.redis.service;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.repository.PersonRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String email) {
        if (Strings.isNullOrEmpty(email)) {
            System.out.println("Email[" + email + "] cannot be null or empty");
            return Optional.empty();
        }

        Optional<Person> personOptional = personRepository.findById(email);
        if (!personOptional.isPresent()) {
            System.out.println("Person[" + email + "] not found");
            return Optional.empty();
        }

        return personOptional;
    }

    public List<Person> getPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Set<Person> getPersonEmailList() {
        return personRepository.filterByEmailList();
    }

    public Set<Person> getPersonAgeEmailList(String age) {
        return personRepository.filterByAgeEmailList(age);
    }

    public void savePerson(Person person) {
        if (person == null) {
            System.out.println("Person cannot be null");
            return;
        }

        if (Strings.isNullOrEmpty(person.getEmail())) {
            System.out.println("Person email cannot be null or empty");
            return;
        }
        personRepository.save(person);
        System.out.println("Person[" + person.getName() + "] saved");
    }


    public void deletePerson(String email) {
        Optional<Person> personOptional = getPerson(email);

        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
        } else {
            System.out.println("Person not found");
        }
    }
}
