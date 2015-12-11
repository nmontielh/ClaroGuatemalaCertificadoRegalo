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
import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.gml.persistence.model.CcTbltarjetacertificado;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;
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

	@Deprecated
	@Test
	public void consultaCertificadoByTarjeta() {

		// TODO revisar si aun se usa
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

	@Test(enabled = false)
	public void updateCertificadoComplete() {

		CcTblcertificado certificado = null;
		String idUsuario = null;
		try {
			service.updateCertificado(certificado, idUsuario);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(enabled = false)
	public void findTarjetaCertificado() {

		String numCertificado = null;

		try {
			CcTbltarjetacertificado certificado = service.findTarjetaCertificado(numCertificado);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = false)
	public void findCertificadoByNumCertificado() {

		String numCertificado = null;

		try {
			CcTblcertificado certificado = service.findCertificadoByNumCertificado(numCertificado);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = true)
	public void findTarjetaCertificadoByNumTarjeta() {

		String numeroTarjeta = null;
		try {
			CcTbltarjetacertificado resultado = service.findTarjetaCertificadoByNumTarjeta(numeroTarjeta);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(enabled = true)
	public void findMovtoCertificadoByFolio() {

		String folio = null;
		try {
			service.findMovtoCertificadoByFolio(folio);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = true)
	public void updateCanceladoTarjetaCertificados() {

		String idUsuario = null;
		CcTbltarjetacertificado tarjeta = null;
		try {
			service.updateCanceladoTarjetaCertificados(idUsuario, tarjeta);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = true)
	public void saveMovtoCertificado() {
		CcTbltarjetacertificado tarjeta = null;
		MovimientoCertificadoTO movto = null;
		try {
			service.saveMovtoCertificado(tarjeta, movto);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(enabled = true)
	public void saveMovtoCertificadoComplete() {

		CcTbltarjetacertificado tarjeta = null;
		CcTblmovimientocertificado movto = null;
		String folio = null;
		String idUsuario = null;
		String ptoVenta = null;
		String referencia = null;
		float valorRestante = 0;

		try {
			service.saveMovtoCertificado(tarjeta, movto, folio, idUsuario, ptoVenta, referencia, valorRestante);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateTarjetaCertificado() {

		CcTbltarjetacertificado tarjeta = null;
		float valorRestante = 0;
		String idUsuario = null;
		try {
			service.updateTarjetaCertificado(tarjeta, valorRestante, idUsuario);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateTarjetaCertificado2() {

		CcTbltarjetacertificado tarjeta = null;
		String idUsuario = null;
		try {
			service.updateTarjetaCertificado(tarjeta, idUsuario);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateMovtoCertificadoCancelado() {

		CcTblmovimientocertificado movto = null;
		String idUsuario = null;
		try {
			service.updateMovtoCertificadoCancelado(movto, idUsuario);
		} catch (BussinesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void saveTarjetaCertificado() {

		String certificado = "6151807679260547";
		String idUsuario = "UNIT-TEST2";
		String numeroTarjeta = "1000000000000117";
		long monto = 40;

		ActivaTarjeta tarjeta = null;
		try {
			tarjeta = service.saveTarjetaCertificado(certificado, idUsuario, numeroTarjeta, monto);
		} catch (BussinesException e) {
			logger.error("Error al salvar [{}] ", e.getMessage());
		}

		logger.info("resultado:[{}]", tarjeta);
	}

}
