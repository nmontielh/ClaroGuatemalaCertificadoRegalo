package com.claro.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "ClaroServiceFault", faultBean = "com.claro.exception.ClaroServiceFault", targetNamespace = "http://service.ws.claro.com", messageName = "Error al procesar")
public class ClaroServiceException extends Exception {

	private static final long serialVersionUID = 2856040899762181825L;

	private ClaroServiceFault faultInfo;

	public ClaroServiceException(String message, ClaroServiceFault faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	public ClaroServiceException(String message, ClaroServiceFault faultInfo, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public ClaroServiceFault getFaultInfo() {
		return faultInfo;
	}

}
