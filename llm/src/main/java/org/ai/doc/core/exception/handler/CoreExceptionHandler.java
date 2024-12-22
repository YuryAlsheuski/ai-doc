package org.ai.doc.core.exception.handler;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class CoreExceptionHandler {

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(BAD_REQUEST)
  List<String> handleBindException(WebExchangeBindException exception) {
    return exception.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(toList());
  }
}
