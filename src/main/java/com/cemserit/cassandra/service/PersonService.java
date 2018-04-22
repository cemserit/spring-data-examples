package com.cemserit.cassandra.service;

import com.cemserit.cassandra.model.Person;
import com.cemserit.cassandra.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(UUID uuid) {
        return personRepository.findByUuid(uuid);
    }

    public Stream<Person> getPerson(int age) {
        return personRepository.findByAgeStream(age);
    }

    public Optional<Person> getPerson(String email, int age) {
        return personRepository.findByEmailAndAge(email, age);
    }

    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public boolean updatePerson(UUID uuid, Person actualPerson) {
        Optional<Person> person = getPerson(uuid);
        if (person.isPresent()) {
            personRepository.save(actualPerson);
            return true;
        }
        return false;
    }

    public boolean deletePerson(UUID uuid) {
        Optional<Person> person = getPerson(uuid);
        if (person.isPresent()) {
            personRepository.delete(person.get());
            return true;
        }
        return false;
    }

    public void deletePersons() {
        personRepository.deleteAll();
    }

}
