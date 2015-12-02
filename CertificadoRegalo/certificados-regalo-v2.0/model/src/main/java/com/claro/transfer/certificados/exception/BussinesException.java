package com.claro.transfer.certificados.exception;

import com.claro.transfer.certificados.constants.ErrorCatalog;

public class BussinesException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorCatalog error;

	public BussinesException(ErrorCatalog error) {
		super();
		this.error = error;
	}

	public BussinesException() {
		super();
	}

	public ErrorCatalog getError() {
		return error;
	}

	public void setError(ErrorCatalog error) {
		this.error = error;
	}

}
