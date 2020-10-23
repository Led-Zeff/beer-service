package courses.microservices.beerservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionValidationController {
  
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public List<String> validationsErrorHandler(ConstraintViolationException e) {
    return  e.getConstraintViolations().stream().map(cv -> cv.getPropertyPath() + " " + e.getMessage()).collect(Collectors.toList());
  }

}
