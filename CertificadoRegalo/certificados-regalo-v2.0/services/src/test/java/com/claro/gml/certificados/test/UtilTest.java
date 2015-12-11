package com.claro.gml.certificados.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.claro.gml.certificados.utils.CertificadosUtils;
import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.constants.SucessCatalog;

/**
 * @see Prueba unitaria para revisar las utilerias siendo de transformacion,
 *      fechas, constantes
 * @author nmontielh
 *
 */
public class UtilTest {

	private static Logger log = LoggerFactory.getLogger(UtilTest.class);

	@Test
	public void createException() {

		Exception exception = new Exception("Bussines Error Exception (Unit Test)");

		ErrorCatalog error = ErrorCatalog.OTROS;
		
		log.info("Before Code [{}] Message [{}]", error.getCode(), error.getMessage());
		
		CertificadosUtils.setError(exception, error);
		
		log.info("After Code [{}] Message [{}]", error.getCode(), error.getMessage());

	}
	
	@Test
	public void createFecha() {


		ErrorCatalog error = ErrorCatalog.CERTIFICADO_EXPIRADO;
		String fecha = "2015-10-13";
		
		log.info("Before Code [{}] Message [{}]", error.getCode(), error.getMessage());
		
	
		CertificadosUtils.setMessage(error, fecha);
		
		log.info("After Code [{}] Message [{}]", error.getCode(), error.getMessage());

	}
	
	@Test
	public void sucessMessage(){
		
		
		SucessCatalog sucess = SucessCatalog.APLICA_CERT_EXITOSO;
		float saldo = 167.5f;
		
		log.info("Before Code [{}] Message [{}]", sucess.getCode(), sucess.getMessage());
		CertificadosUtils.setMessage(sucess, saldo);
		log.info("After Code [{}] Message [{}]", sucess.getCode(), sucess.getMessage());
		
	}
	
	@Test
	public void errorMessage(){
		
		
		ErrorCatalog error = ErrorCatalog.SALDO_INSUFICIENTE;
		float saldo = 167.5f;
		
		log.info("Before Code [{}] Message [{}]", error.getCode(), error.getMessage());
		CertificadosUtils.setMessage(error, saldo);
		log.info("After Code [{}] Message [{}]", error.getCode(), error.getMessage());
		
	}

}
