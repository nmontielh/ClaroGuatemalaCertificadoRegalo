package com.claro.transfer.certificados.constants;

public enum AplicadoCatalog {

	SI("S"), CANCELADO("C"), NO("N"), APLICADO("A"), MOVTO_CANCELADO("I");

	private String value;

	AplicadoCatalog(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
