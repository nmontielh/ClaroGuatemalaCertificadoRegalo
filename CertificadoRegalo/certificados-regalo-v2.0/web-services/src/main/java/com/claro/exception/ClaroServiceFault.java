package com.claro.exception;

import java.io.Serializable;

import com.claro.transfer.certificados.constants.ErrorCatalog;

public class ClaroServiceFault implements Serializable {

	private static final long serialVersionUID = 1L;

	private String faultCode;

	private String faultString;

	public ClaroServiceFault() {
		super();
	}

	public ClaroServiceFault(ErrorCatalog error) {
		this.faultCode = error.getCode();
		this.faultString = error.getMessage();
	}

	public ClaroServiceFault(String faultCode, String faultString) {
		super();
		this.faultCode = faultCode;
		this.faultString = faultString;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

}
