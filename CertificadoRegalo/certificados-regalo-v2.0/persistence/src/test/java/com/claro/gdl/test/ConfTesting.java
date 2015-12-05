package com.claro.gdl.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;



@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml" })
public class ConfTesting extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(ConfTesting.class);

	@Test
	public void test() {

		logger.info("Es una prueba de conectividad..");

	}

}
