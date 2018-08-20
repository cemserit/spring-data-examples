package com.cemserit.redis.controller;

import com.cemserit.redis.model.Person;
import com.cemserit.redis.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Person Controller Api", tags = {"Person Controller"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{email}")
    public ResponseEntity<Object> getPerson(@PathVariable String email) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getPersons() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        String message = "Person[" + person.getName() + "] saved";

        return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
    }
}
