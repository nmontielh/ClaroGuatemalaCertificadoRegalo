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

		logger.info("resultado : [{}]", resultado);

	}

	@Test(enabled = true)
	public void cancelaTarjetaCertificado() {

		String idUsuario = "certGT";
//[1000000000000118|4351807679251398|50|1|10122015|10062016]
		String numeroCertificado = "1000000000000118";

		String resultado = null;

		try {
			resultado = facade.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

		logger.info("resultado : [{}]", resultado);

	}

	@Test(enabled = false)
	public void consultaSaldoTarjetaCertificado() {
		String numeroCertificado = null;
		String resultado = null;

		try {
			resultado = facade.consultaSaldoTarjetaCertificado(numeroCertificado);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

		logger.info("resultado : [{}]", resultado);

	}

	@Test(enabled = false)
	public void consultaMovimientosCertificado() {

		String resultado = null;

		String numeroTarjeta = null;
		try {
			resultado = facade.consultaMovimientosCertificado(numeroTarjeta);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);

		}

		logger.info("resultado : [{}]", resultado);

	}

	@Test(enabled = false)
	public void aplicaCertificado() {

		String resultado = null;

		MovimientoCertificadoTO movimientoCertificadoTO = null;
		try {
			resultado = facade.aplicaCertificado(movimientoCertificadoTO);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}
		logger.info("resultado : [{}]", resultado);

	}

	@Test(enabled = false)
	public void cancelaAplicaCertificado() {

		String resultado = null;

		String folio=null;
		String idUsuario=null;
		String idpuntoVta=null;
		String referencia=null;
		try {
			resultado = facade.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta, referencia);
		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);
		}

		logger.info("resultado : [{}]", resultado);

	}

}
