package com.claro.gml.certificados.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.claro.gml.certificados.services.CertificadosService;
import com.claro.gml.persistence.model.CcCtlusuarios;
import com.claro.gml.persistence.model.CcTblcertificado;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.response.ActivaTarjeta;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;

@ContextConfiguration(locations = { "classpath:dbms-connect-context.xml", "classpath:persistence-context.xml",
		"classpath:beanRefContextTest.xml" })
public class ServiceTest extends AbstractTestNGSpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(ServiceTest.class);

	@Autowired
	private CertificadosService service;

	@Test
	public void consultasMovtosTest() throws BussinesException {

		final String numcertificado = "1000000000000117";

		List<ConsultaMovimientosCertificado> resultado = service.findMovtoCert(numcertificado);

		logger.info(" Total[{}]", resultado.size());

	}

	@Test
	public void consultaRegion() throws BussinesException {

		final String usuario = "certGT";

		CcCtlusuarios resultado = service.findUsuario(usuario);

		logger.info("resultado:[{}]", resultado);
		logger.info("Region : [{}]", resultado.getId().getRegion());

	}

	@Test
	public void consultaCertificado() throws BussinesException {
		final String numeroTarjeta = "1000000000000117";
		final long monto = 50;
		final String region = "1";

		CcTblcertificado certificado = service.findCertificado(numeroTarjeta, monto, region);

		logger.info("Data [{}]", certificado);
		logger.info("num_cert : [{}]", certificado.getNumcertificado());
		logger.info("aplicado : [{}]", certificado.getAplicado());

	}

	@Test
	public void consultaCertificadoByTarjeta() {

		String numeroTarjeta = "1000000000000117";
		String numCertificado = "6151807679260546";
		CcTblcertificado certificado = service.findCertificadoByNumTarjeta(numeroTarjeta, numCertificado);

		logger.info("resultado [{}]", certificado);

	}

	@Test
	public void updateCertificado() {

		String numeroTarjeta = "1000000000000117";
		String numCertificado = "6151807679260546";
		String idUsuario = "UNIT-TEST";// BATCH

		service.updateCertificado(numCertificado, numeroTarjeta, idUsuario);
	}

	@Test
	public void saveTarjetaCertificado() throws BussinesException {

		String certificado = "6151807679260546";
		String idUsuario = "UNIT-TEST";
		String numeroTarjeta = "1000000000000117";
		long monto = 40;

		ActivaTarjeta tarjeta = service.saveTarjetaCertificado(certificado, idUsuario, numeroTarjeta, monto);

		logger.info("resultado:[{}]", tarjeta);
	}

}
