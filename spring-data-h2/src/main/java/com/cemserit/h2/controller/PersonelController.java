package com.cemserit.h2.controller;

import com.cemserit.core.constant.ControllerConstant;
import com.cemserit.core.controller.AbstractController;
import com.cemserit.h2.model.Person;
import com.cemserit.h2.service.PersonService;
import com.google.common.base.Strings;
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
public class PersonelController extends AbstractController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Object> getPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getPerson(@PathVariable UUID uuid) {
        Optional<Person> personOptional = personService.getPerson(uuid);

        if (!personOptional.isPresent())
            return createErrorResponse(PERSON_NOT_FOUND_ERROR,
                    PERSON_NOT_FOUND_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        return createResponse(personOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {
        if (person == null)
            return createErrorResponse(PERSON_CANNOT_NULL_ERROR,
                    ControllerConstant.PERSON_ALREADY_EXITS_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        Optional<Person> personOptional = personService.getPerson(person.getEmail());

        if (personOptional.isPresent())
            return createErrorResponse(PERSON_ALREADY_EXITS_ERROR,
                    ControllerConstant.PERSON_ALREADY_EXITS_ERROR_DESCRIPTION,
                    HttpStatus.BAD_REQUEST);

        personService.savePerson(person);

        // return with actual uuid
        return createResponse(personService.getPerson(person.getEmail()), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> updatePerson(@PathVariable UUID uuid,
                                               @RequestBody Person person) {

        if (Strings.isNullOrEmpty(person.getEmail()))
            return createErrorResponse(PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR,
                    ControllerConstant.PERSON_EMAIL_CANNOT_NULL_EMPTY_ERROR_DESCRIPTION,
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
