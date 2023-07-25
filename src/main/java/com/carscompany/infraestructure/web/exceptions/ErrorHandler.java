package com.carscompany.infraestructure.web.exceptions;

import com.carscompany.dto.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler  extends ResponseEntityExceptionHandler {
  private void printErrorRequest(HttpServletRequest req) {
    log.error("Request: " + req.getRequestURL());
  }

  @ExceptionHandler(ExceptionInvalidCredentials.class)
  public ResponseEntity<ErrorResponse> methodInvalidCredentialException(HttpServletRequest request, ExceptionInvalidCredentials e) {
    this.printErrorRequest(request);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

  }

  @ExceptionHandler(ExceptionRequestInvalid.class)
  public ResponseEntity<ErrorResponse> methodEmailNotValidException(HttpServletRequest request, ExceptionRequestInvalid e){
    this.printErrorRequest(request);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ExceptionDataQuery.class)
  public ResponseEntity<ErrorResponse> methodQueryDataException(HttpServletRequest request, ExceptionRequestInvalid e) {
    this.printErrorRequest(request);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @ExceptionHandler(ExceptionDataConflict.class)
  public ResponseEntity<ErrorResponse> methodQueryDataException(HttpServletRequest request, ExceptionDataConflict e) {
    this.printErrorRequest(request);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

}
