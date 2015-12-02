package com.claro.gml.certificados.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.facade.CertificadosFacade;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:beanRefContext.xml" })
public class AspectsTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(AspectsTest.class);

	@Autowired
	private CertificadosFacade facade;

	@Test
	public void test() {

		logger.info("Es una prueba de conectividad..");

	}

}
