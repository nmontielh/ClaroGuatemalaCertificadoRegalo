package com.claro.gml.certificados.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.claro.gml.certificados.persistence.dao.BaseDAO;
import com.claro.gml.certificados.transforms.CertificadosTransform;
import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;

@Repository
public class CertificadosService {

	private static Logger log = LoggerFactory.getLogger(CertificadosService.class);

	@Autowired
	private BaseDAO dao;

	/**
	 * @see Metodo para traerse todos los movimientos asociados a un numero de
	 *      certificado
	 * @param numcertificado
	 * @return
	 */
	public List<ConsultaMovimientosCertificado> findMovtoCert(String numcertificado) {

		Type[] types = { StringType.INSTANCE };

		List<CcTblmovimientocertificado> movtos = new ArrayList<>();

		try {
			movtos = dao.findListByQuery("findMovCertByNumCertif", new Object[] { numcertificado }, types,
					CcTblmovimientocertificado.class);
		} catch (IOException e) {
			log.error("Error to request to database findMovCertByNumCertif[{}] :  [{}]", numcertificado, e.getMessage(),
					e);
		}

		List<ConsultaMovimientosCertificado> resultado = new ArrayList<>();

		// Si vienen mas de 1, transformamos

		if (movtos.size() > 0) {

			for (CcTblmovimientocertificado movto : movtos) {
				ConsultaMovimientosCertificado newMovto = CertificadosTransform.transform(movto);
				resultado.add(newMovto);
			}

		}

		return resultado;
	}

}