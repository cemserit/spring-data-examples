package com.cemserit.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.cemserit.core.constant.ControllerConstant.*;

public abstract class AbstractController {

    protected ResponseEntity<Object> createResponse(final String message, final HttpStatus httpStatus) {
        return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, message), httpStatus);
    }

    protected ResponseEntity<Object> createErrorResponse(final String error, final String description, final HttpStatus httpStatus) {
        Map responseBody = new HashMap();
        responseBody.put(ERROR_KEY, error);
        responseBody.put(ERROR_DESCRIPTION_KEY, description);
        return new ResponseEntity<>(responseBody, httpStatus);
    }

    protected ResponseEntity<Object> createErrorResponse(final String error, final String description, final Object errorList, final HttpStatus httpStatus) {
        Map responseBody = new HashMap();
        responseBody.put(ERROR_KEY, error);
        responseBody.put(ERROR_LIST_KEY, errorList);
        responseBody.put(ERROR_DESCRIPTION_KEY, description);
        return new ResponseEntity<>(responseBody, httpStatus);
    }

    protected ResponseEntity<Object> createResponse(final Object message, final HttpStatus httpStatus) {
        return new ResponseEntity<>(message, httpStatus);
    }
}