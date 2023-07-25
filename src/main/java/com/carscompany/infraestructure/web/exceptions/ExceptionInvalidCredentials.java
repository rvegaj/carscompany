package com.carscompany.infraestructure.web.exceptions;

import lombok.Getter;

@Getter
public class ExceptionInvalidCredentials extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public ExceptionInvalidCredentials(String exception){
      super(exception);
      this.message = exception;
    }

}
