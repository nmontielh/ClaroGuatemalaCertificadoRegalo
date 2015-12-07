package com.claro.gml.certificados.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.services.CertificadosService;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml","classpath:beanRefContextTest.xml" })
public class ServiceTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(ServiceTest.class);

	@Autowired
	private CertificadosService service;

	@Test
	public void consultasMovtosTest() {

		final String numcertificado = "1000000000000117";
		
		List<ConsultaMovimientosCertificado> resultado = service.findMovtoCert(numcertificado);
		
		logger.info(" Total[{}]", resultado.size());

	}

}
