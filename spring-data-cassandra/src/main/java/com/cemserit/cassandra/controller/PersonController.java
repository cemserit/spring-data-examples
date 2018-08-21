package com.cemserit.cassandra.controller;

import com.cemserit.cassandra.model.Person;
import com.cemserit.cassandra.service.PersonService;
import com.cemserit.core.constant.ControllerConstant;
import com.cemserit.core.controller.AbstractController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static com.cemserit.core.constant.ControllerConstant.*;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Person Controller Api", tags = {"Person Controller"})
public class PersonController extends AbstractController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getPerson(@PathVariable UUID uuid) {
        Optional<Person> personOptional = personService.getPerson(uuid);

        if (!personOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    ControllerConstant.PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        return createResponse(personOptional.get(), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    public ResponseEntity<Object> getPersonsByAge(@RequestParam("age") int age) {
        return createResponse(personService.getPerson(age), HttpStatus.OK);
    }

    @GetMapping(params = {"email", "age"})
    public ResponseEntity<Object> getPersonsByEmailAndAge(@RequestParam("email") String email,
                                                          @RequestParam("age") int age) {
        return createResponse(personService.getPerson(email, age), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getPersons() {
        return createResponse(personService.getPersons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {

        if (person == null)
            return createErrorResponse(PERSON_CANNOT_NULL_ERROR,
                    ControllerConstant.PERSON_ALREADY_EXITS_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        if (person.getUuid() == null)
            return createErrorResponse(PERSON_UUID_CANNOT_NULL_ERROR,
                    ControllerConstant.PERSON_UUID_CANNOT_NULL_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> personOptional = personService.getPerson(person.getUuid());

        if (personOptional.isPresent())
            return createErrorResponse(PERSON_ALREADY_EXITS_ERROR,
                    ControllerConstant.PERSON_ALREADY_EXITS_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        personService.savePerson(person);
        return createResponse(person, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person,
                                               @PathVariable UUID uuid) {
        if (uuid == null)
            return createErrorResponse(PERSON_UUID_CANNOT_NULL_ERROR,
                    ControllerConstant.PERSON_UUID_CANNOT_NULL_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> personOptional = personService.getPerson(uuid);

        if (!personOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    ControllerConstant.PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        person.setUuid(uuid);
        personService.savePerson(person);
        return createResponse(person, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> deletePerson(@PathVariable UUID uuid) {

        if (uuid == null)
            return createErrorResponse(PERSON_UUID_CANNOT_NULL_ERROR,
                    ControllerConstant.PERSON_UUID_CANNOT_NULL_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> personOptional = personService.getPerson(uuid);

        if (!personOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    ControllerConstant.PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        personService.deletePerson(uuid);
        String message = String.format("Person[%s] deleted.", personOptional.get().getName());
        return createResponse(message, HttpStatus.OK);
    }
}
