package com.cemserit.redis.controller;

import com.cemserit.core.controller.AbstractController;
import com.cemserit.redis.model.Person;
import com.cemserit.redis.service.PersonService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.cemserit.core.constant.ControllerConstant.*;

@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Person Controller Api", tags = {"Person Controller"})
public class PersonController extends AbstractController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{email}")
    public ResponseEntity<Object> getPerson(@PathVariable String email) {
        Optional<Person> personOptional = personService.getPerson(email);

        if (!personOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR, PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        return createResponse(personOptional.get(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<Object> getPersons() {
        List<Person> personList = personService.getPersons();
        return createResponse(personList, HttpStatus.OK);
    }

    @GetMapping("/emails")
    public ResponseEntity<Object> getPersonsEmailList() {
        return createResponse(personService.getPersonEmailList(), HttpStatus.OK);
    }

    @GetMapping("/emails/{age}")
    public ResponseEntity<Object> getPersonsEmailList(@PathVariable String age) {
        return createResponse(personService.getPersonAgeEmailList(age), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {

        // person object cannot be null
        if (person == null)
            return createErrorResponse(PERSON_CANNOT_NULL_ERROR,
                    PERSON_CANNOT_NULL_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        // person email cannot be null
        if (Strings.isNullOrEmpty(person.getEmail()))
            return createErrorResponse(PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR,
                    PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> storedPersonOptional = personService.getPerson(person.getEmail());

        // if the person is registered then return error
        if (storedPersonOptional.isPresent())
            return createErrorResponse(PERSON_ALREADY_EXITS_ERROR,
                    PERSON_ALREADY_EXITS_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        personService.savePerson(person);
        return createResponse(person, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Object> updatePerson(@PathVariable String email,
                                               @RequestBody Person person) {
        if (Strings.isNullOrEmpty(email))
            return createErrorResponse(PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR,
                    PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> storedPersonOptional = personService.getPerson(email);

        // if the person is not registered, the return error
        if (storedPersonOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        person.setEmail(email);
        personService.savePerson(person);
        return createResponse(person, HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deletePerson(@PathVariable String email) {

        if (Strings.isNullOrEmpty(email))
            return createErrorResponse(PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR,
                    PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> storedPersonOptional = personService.getPerson(email);

        // if the person is not registered, the return error
        if (storedPersonOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        personService.deletePerson(email);
        String message = String.format("Person[%s] deleted.", storedPersonOptional.get().getName());
        return createResponse(message, HttpStatus.OK);
    }
}
