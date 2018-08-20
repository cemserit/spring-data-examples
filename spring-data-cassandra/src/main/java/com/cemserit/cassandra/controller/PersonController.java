package com.cemserit.cassandra.controller;

import com.cemserit.cassandra.model.Person;
import com.cemserit.cassandra.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Person Controller Api", tags = {"person Controller"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getPerson(@PathVariable UUID uuid) {
        return new ResponseEntity<>(personService.getPerson(uuid), HttpStatus.OK);
    }

    @GetMapping("/custom_param")
    public ResponseEntity<Object> getPersons(@RequestParam("age") int age) {
        return new ResponseEntity<>(personService.getPerson(age), HttpStatus.OK);
    }

    @GetMapping("/custom_params")
    public ResponseEntity<Object> getPerson(@RequestParam("email") String email,
                                            @RequestParam("age") int age) {
        return new ResponseEntity<>(personService.getPerson(email, age), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getPersons() {
        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        String message = "Person[" + person.getName() + "] saved";

        return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person,
                                               @PathVariable UUID uuid) {
        boolean result = personService.updatePerson(uuid, person);
        String message = result ? "Person[" + person.getName() + "] updated" :
                "Person[" + person.getName() + "] cannot be found";

        return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> deletePerson(@PathVariable UUID uuid) {
        boolean result = personService.deletePerson(uuid);
        String message = result ? "Person[" + uuid + "] deleted" : "Person[" + uuid + "] cannot be found";

        return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllPerson() {
        personService.deletePersons();
        return new ResponseEntity<>(Collections.singletonMap("message", "Persons deleted"), HttpStatus.OK);
    }

}
