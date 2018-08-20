package com.cemserit.redis.service;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public Person getPerson(Person person) {
        return personRepository.findByEmail(person.getEmail());
    }

    public Object getPersonFieldValue(String email, String fieldName) {
        return personRepository.getHash(email, fieldName);
    }

    public long getPersonCount() {
        return personRepository.count();
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);

    }
}
