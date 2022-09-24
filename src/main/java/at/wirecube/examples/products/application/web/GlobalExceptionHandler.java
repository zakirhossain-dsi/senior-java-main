package at.wirecube.examples.products.application.web;

import at.wirecube.examples.products.application.exception.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {

    ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleInvalidParameterException(IllegalArgumentException ex) {

    ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {

    ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  protected ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException ex) {

    ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> errors = ex
            .getAllErrors().stream()
            .filter(error -> !StringUtils.isEmpty(error.getDefaultMessage()))
            .map(error -> ((FieldError)error).getField() + " - " + error.getDefaultMessage())
            .collect(Collectors.toList());

    ApiError apiError = new ApiError(BAD_REQUEST, "Bean validation failed", errors);
    return buildResponseEntity(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

    ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
