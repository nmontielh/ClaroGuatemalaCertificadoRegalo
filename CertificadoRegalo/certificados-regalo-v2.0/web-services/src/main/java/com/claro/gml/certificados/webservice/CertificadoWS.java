package com.claro.gml.certificados.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.claro.gml.certificados.services.CertificadosService;

@WebService(serviceName = "")
public class CertificadoWS extends SpringBeanAutowiringSupport {

	@Autowired
	private CertificadosService service;

}
