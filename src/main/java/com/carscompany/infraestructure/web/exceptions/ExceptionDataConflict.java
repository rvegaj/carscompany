package com.carscompany.infraestructure.web.exceptions;

import lombok.Getter;

@Getter
public class ExceptionDataConflict extends RuntimeException{

    /**
     *rvega
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public ExceptionDataConflict(String exception){
      super(exception);
      this.message = exception;
    }

}
