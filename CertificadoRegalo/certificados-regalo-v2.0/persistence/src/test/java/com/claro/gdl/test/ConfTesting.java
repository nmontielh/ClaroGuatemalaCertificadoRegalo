package com.claro.gdl.test;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.persistence.dao.BaseDAO;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml" })
public class ConfTesting extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(ConfTesting.class);

	@Autowired
	private BaseDAO dao;

	@Test
	public void test() {

		logger.info("Es una prueba de conectividad..");
		
		logger.info(" month [{}]", GregorianCalendar.MONTH);
		

	}

}
