package com.claro.gml.certificados.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.facade.ICertificadosFacade;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml",
		"classpath:beanRefContextTest.xml" })
public class BussinesTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(BussinesTest.class);

	@Autowired
	private ICertificadosFacade facade;

	/**
	 * Para las pruebas primero se debe ejecutar esta prueba
	 */
	@Test(enabled = false)
	public void saveTarjetaCertificado() {

		String numeroTarjeta = "1000000000000118";

		String idUsuario = "certGT";// BATCH

		long montoCertificado = 50;

		String resultado = null;

		try {
			resultado = facade.activaTarjetaCertificado(numeroTarjeta, montoCertificado, idUsuario);
			logger.info("resultado [{}]", resultado);

		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);

		}

	}

	/**
	 * 2o metodo a ejecutar para la prueba
	 */
	@Test(enabled = false)
	public void aplicaCertificado() {

		String resultado = null;

		MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();

		String idUsuario = "certGT";
		String numeroCertificado = "4351807679251398";
		String puntoVenta = "MiCasita";
		String referencia = "MiReferenciaPrueba";
		float valorAplicado = 50f;

		movimientoCertificadoTO.setIdUsuario(idUsuario);
		movimientoCertificadoTO.setNumeroCertificado(numeroCertificado);
		movimientoCertificadoTO.setPuntoVenta(puntoVenta);
		movimientoCertificadoTO.setReferencia(referencia);
		movimientoCertificadoTO.setValorAplicado(valorAplicado);

		try {
			resultado = facade.aplicaCertificado(movimientoCertificadoTO);
			logger.info("resultado : [{}]", resultado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

	}

	@Test(enabled = false)
	public void cancelaAplicaCertificado() {

		//TODO revisas las multiples excepciones que puede lanzar
		String resultado = null;

		String folio = "1398111215121023";
		String idUsuario = "certGT";
		String idpuntoVta = "MiCasita";
		String referencia = "MiReferenciaPrueba";
		try {
			resultado = facade.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta, referencia);
			logger.info("resultado : [{}]", resultado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

	}

	/**
	 * Este es es el 3er metodo que se debe ejecutar, se debe activar el
	 * ceryificado
	 */
	@Test(enabled = false)
	public void cancelaTarjetaCertificado() {

		//TODO revisar las excepciones y hacer que se cancele
		String idUsuario = "certGT";

		String numeroCertificado = "4351807679251398";

		String resultado = null;

		try {
			resultado = facade.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
			logger.info("resultado : [{}]", resultado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

		

	}

	@Test(enabled = false)
	public void consultaSaldoTarjetaCertificado() {
		String numeroCertificado = "4351807679251398";
		String resultado = null;

		try {
			resultado = facade.consultaSaldoTarjetaCertificado(numeroCertificado);
			logger.info("resultado : [{}]", resultado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

		

	}

	@Test(enabled = false)
	public void consultaMovimientosCertificado() {

		String resultado = null;

		String numeroTarjeta = "1000000000000118";
		try {
			resultado = facade.consultaMovimientosCertificado(numeroTarjeta);
			logger.info("resultado : [{}]", resultado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);

		}

		

	}

}
