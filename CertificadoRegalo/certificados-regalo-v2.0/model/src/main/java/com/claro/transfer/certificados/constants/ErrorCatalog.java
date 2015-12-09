package com.claro.transfer.certificados.constants;

public enum ErrorCatalog {
	
	UNKNOW,
	BUSSINES_ERROR,
	UNEXPECTED_ERROR("XX"),
	//Errores para la activacion tarjeta
	NO_EXISTE_USUARIO("03","No se pueden activar certificados ya que usuario generico no existe"),
	TARJETA_VENDIDA ("03", "La tarjeta: XXXXXXXXXXXXXXXX ya fue vendida"), 
	NO_EXISTE_TARJETA("03","No existe la tarjeta: XXXXXXXXXXXXXXXX a Activar o el monto: YYY es invalido"),
	NUM_CERTIFICADO_REQUERIDO("01","El numero de certificado es requerido"),
	NUM_TARJETA_REQUERIDO("01","El numero de tarjeta es requerido"),
	MONTO_MAYOR_0("01","El monto del certificado debe ser mayor a 0"),
	USUARIO_REQUERIDO("01","El usuario es requerido"),
	OTROS("07","otros (Error) : [EEEEEEEEEE]"),
	//Errores para la cancelacion de activacion
	NO_EXISTE_ACTIVACION_CERTIFICADO("02","No existe activacion para el certificado"),
	CERTIFICADO_UTILIZADO("01","El certificado ha sido utilizado y no puede ser cancelado"),
	CERTIFICADO_CADUCADO("06","No se puede cancelar el certificado ya que ha caducado"),
	CERTIFICADO_NO_VENDIDO("01","No se puede cancelar el certificado por que no ha sido vendido"),
	//Errores para la consulta de saldo
	CERTIFICADO_NO_VALIDO("02","Certificado no válido"),
	//Errorres para consulta de movto
	NO_EXISTEN_MOVTOS_CERT("08","NO EXISTEN MOVIMIENTOS DEL CERTIFICADO"),
	//Errores en aplica certificado
	SALDO_INSUFICIENTE("05","No cuenta con saldo suficiente: [SSSSSRRRRR]"),
	CERTIFICADO_EXPIRADO("06","El certificado ha expirado, fecha de expiración: [FECHAEXP]"),
	CERTIFICADO_CANCELADO("3","Error, el certificado esta cancelado"),	
	NO_EXISTE_ACTIVACION_TARJETA("01","No existe activación para la tarjeta"),
	//Exception de cancelacion
	MOVTO_CANCELADO("01","El movimiento ya fue cancelado."),
	CERTIFICADO_NO_ACTIVO("01","El certificado tiene un estatus diferente a activo"),
	MOVTO_MAS_1_DIA("01","El movimiento tiene más de 1 día, no puede ser cancelado"),
	NO_EXISTE_INFO("02","No existe información del certificado de regalo"),
	NO_EXISTEN_MOVTOS_FOLIO("01","No existen movimientos relacionados al folio")
	;
	
	private String code;
	private String message;

	private ErrorCatalog(String code){
		this.code = code;
	}

	
	private ErrorCatalog(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	private ErrorCatalog(){
		
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
