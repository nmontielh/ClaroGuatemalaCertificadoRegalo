package com.claro.gml.certificados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gml.certificados.persistence.dao.BaseDAO;

@Service
public class CertificadosService {

	@Autowired
	private BaseDAO dao;

}
