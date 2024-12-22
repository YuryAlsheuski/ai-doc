package org.ai.doc.model.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.ai.doc.model.exception.ModelNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelExceptionHandler {

  @ExceptionHandler(ModelNotFoundException.class)
  @ResponseStatus(BAD_REQUEST)
  String handleModelNotFoundException(ModelNotFoundException exception) {
    return exception.getMessage();
  }
}
