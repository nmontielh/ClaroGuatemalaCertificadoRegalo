package com.claro.transfer.certificados.constants;

public enum SucessCatalog {

	CANCELA_ACTIVACION_TARJETA("00","Se canceló el certificado"),
	PROCESO_EXITOSO("00","proceso exitoso"),
	APLICA_CERT_EXITOSO("00","Se realizó satisfactoriamente la operación. Saldo Restante"),
	MOVTO_CANCEL_OK("00","El movimiento fue cancelado exitosamente");
	
	private String code;
	private String message;

	private SucessCatalog(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

}
