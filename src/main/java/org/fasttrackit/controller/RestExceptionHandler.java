package org.fasttrackit.controller;

import org.fasttrackit.exception.ResourcesNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourcesNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ErrorRespons handleResourceNotFound(ResourcesNotFound exception) {
        return new ErrorRespons(exception.getMessage(), exception.getEntityId());
    }
}

record ErrorRespons(String message, long entityId) {

}