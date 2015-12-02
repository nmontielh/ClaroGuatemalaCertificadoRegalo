package com.claro.gml.certificados.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.claro.gml.certificados.persistence.dao.BaseDAO;

@Repository
public class CertificadosService {

	private static Logger log = LoggerFactory.getLogger(CertificadosService.class);

	@Autowired
	private BaseDAO dao;

	public void print(String algo) {
		log.info("input [{}]", algo);
	}

}
