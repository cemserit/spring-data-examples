package com.cemserit.redis.service;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public Person getPerson(Person person) {
        return personRepository.findByEmail(person.getEmail());
    }

    public long getPersonCount() {
        return personRepository.count();
    }

    public void deletePerson(Person person){
        personRepository.delete(person);

    }

    public void basicCrudOperations() {

        Person person = new Person("cemsserit@gmail.com", "Cem Serit", 27);


    }

}
