package com.carscompany.infraestructure.web.exceptions;

import lombok.Getter;

@Getter
public class ExceptionDataQuery extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ExceptionDataQuery(String message) {
		super(message);
		this.message = message;
	}

}
