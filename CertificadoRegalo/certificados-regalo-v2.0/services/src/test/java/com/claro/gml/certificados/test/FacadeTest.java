package com.claro.gml.certificados.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.facade.ICertificadosFacade;
import com.claro.transfer.certificados.exception.BussinesException;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml",
		"classpath:beanRefContextTest.xml" })
public class FacadeTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(FacadeTest.class);

	@Autowired
	private ICertificadosFacade facade;

	@Test
	public void saveTarjetaCertificado() {

		String numeroTarjeta = "1000000000000117";

		String idUsuario = "UNIT-TEST";// BATCH

		long montoCertificado = 50;
		try {
			String resultado = facade.activaTarjetaCertificado(numeroTarjeta, montoCertificado, idUsuario);
			logger.info("resultado [{}]", resultado);

		} catch (BussinesException e) {
			logger.error("Error [{}]", e.getMessage(), e);

		}

		
	}

}
