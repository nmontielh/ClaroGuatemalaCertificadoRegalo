package com.claro.transfer.certificados.constants;

public enum EstatusCatalog {
	
	ACTIVO("1"), CANCELADO("3"), CADUCADO("2");
	
	private String value;
	
	private EstatusCatalog(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	
	
	
}
