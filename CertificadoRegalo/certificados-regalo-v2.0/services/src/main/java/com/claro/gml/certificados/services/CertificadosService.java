package com.claro.gml.certificados.services;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.claro.gml.certificados.persistence.dao.BaseDAO;
import com.claro.gml.certificados.transforms.CertificadosTransform;
import com.claro.gml.certificados.utils.CertificadosUtils;
import com.claro.gml.certificados.utils.DateUtilsClaro;
import com.claro.gml.persistence.model.CcCtlusuarios;
import com.claro.gml.persistence.model.CcTblcertificado;
import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.gml.persistence.model.CcTblmovimientocertificadoId;
import com.claro.gml.persistence.model.CcTbltarjetacertificado;
import com.claro.transfer.certificados.constants.AplicadoCatalog;
import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.constants.EstatusCatalog;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;
import com.claro.transfer.certificados.response.ActivaTarjeta;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;

@Repository
public class CertificadosService {

	private static Logger log = LoggerFactory.getLogger(CertificadosService.class);

	@Autowired
	private BaseDAO dao;

	/**
	 * @see Metodo para traerse todos los movimientos asociados a un numero de
	 *      tarjeta
	 * @param numcertificado
	 * @return
	 */
	public List<ConsultaMovimientosCertificado> findMovtoCert(String numTarjeta) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		List<CcTblmovimientocertificado> movtos = new ArrayList<>();

		try {
			movtos = dao.findListByQuery("findMovCertByNumCertif", new Object[] { numTarjeta }, types,
					CcTblmovimientocertificado.class);
		} catch (Exception e) {
			log.error("Error to request to database findMovCertByNumCertif[{}] :  [{}]", numTarjeta, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
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

	/**
	 * @see Se encuentra la region del usuario
	 * @param usuario
	 * @return
	 */
	public CcCtlusuarios findUsuario(String usuario) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		CcCtlusuarios response = null;

		try {
			response = dao.findObjectByQuery("findUsuarioByUser", new Object[] { usuario }, types, CcCtlusuarios.class);
		} catch (Exception e) {
			log.error("Error to findUsuarioByUser [{}] [{}]", usuario, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}
		return response;

	}

	/**
	 * @see Busca el certificado con base al numero de tarjeta, monto y region
	 * @param numeroTarjeta
	 * @param monto
	 * @param region
	 * @return
	 */
	public CcTblcertificado findCertificado(String numeroTarjeta, long monto, String region) throws BussinesException {

		Type[] types = { StringType.INSTANCE, LongType.INSTANCE, StringType.INSTANCE };

		CcTblcertificado response = null;

		try {
			response = dao.findObjectByQuery("findCertificado", new Object[] { numeroTarjeta, monto, region }, types,
					CcTblcertificado.class);
		} catch (Exception e) {
			log.error("Error to findCertificado [{}] [{}] [{}] [{}]", numeroTarjeta, monto, region, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

		return response;
	}

	/**
	 * @see inserta la tarjeta del certificado en la base de datos
	 * @param certificado
	 * @param idUsuario
	 * @param numeroTarjeta
	 * @param monto
	 * @return
	 */
	public ActivaTarjeta saveTarjetaCertificado(String certificado, String idUsuario, String numeroTarjeta, long monto)
			throws BussinesException {

		// GregorianCalendar fechaActivacion = (GregorianCalendar)
		// GregorianCalendar.getInstance();

		// Agregamos 6 meses (Tomar del date utils)
		// GregorianCalendar fechaExpiracion = (GregorianCalendar)
		// GregorianCalendar.getInstance();
		// fechaExpiracion.add(GregorianCalendar.MONTH, 6);

		Date fechaActivacion = DateUtilsClaro.now();

		// Agregamos 6 meses a la fecha de activacion

		Date expirationDate = DateUtilsClaro.addMonth(fechaActivacion, 6);

		CcTbltarjetacertificado entity = new CcTbltarjetacertificado();
		entity.setNumcertificado(certificado);
		entity.setNumtarjeta(numeroTarjeta);
		entity.setValorrestante(new BigDecimal(monto));
		entity.setFechaactivacion(new Timestamp(fechaActivacion.getTime()));
		entity.setFechaexpiracion(new Timestamp(expirationDate.getTime()));
		entity.setEstatus(EstatusCatalog.ACTIVO.getValue());
		entity.setIdusuario(idUsuario);

		Serializable key = null;

		try {
			key = this.dao.save(entity, CcTbltarjetacertificado.class);
			log.info("key [{}] [{}]", key.getClass(), key);
		} catch (Exception e) {
			log.error("Error to saveTarjetaCertificado [{}] [{}] [{}] [{}] [{}]", certificado, idUsuario, numeroTarjeta,
					monto, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

		// Entregamos el objeto de OK
		ActivaTarjeta resultado = new ActivaTarjeta();
		resultado.setNumeroTarjeta(numeroTarjeta);
		resultado.setNumeroCertificado(certificado);
		resultado.setMontoCertificado(monto);

		// Ajustamos las fechas
		// SimpleDateFormat sdf = new SimpleDateFormat();

		String fes = DateUtilsClaro.toFormatString(expirationDate, "ddMMyyyy");
		String fas = DateUtilsClaro.toFormatString(fechaActivacion, "ddMMyyyy");

		resultado.setFechaExpiracion(fes);
		resultado.setFechaActivacion(fas);
		resultado.setEstatus(EstatusCatalog.ACTIVO.getValue());

		return resultado;

	}

	/**
	 * @see se trae el certificado con base a su numero de tarjeta y num
	 *      certificado
	 * @param numeroTarjeta
	 * @param numCertificado
	 * @return
	 */
	@Deprecated
	public CcTblcertificado findCertificadoByNumTarjeta(String numCertificado, String numeroTarjeta) {

		Type[] types = { StringType.INSTANCE, StringType.INSTANCE };

		CcTblcertificado response = dao.findObjectByQuery("findCertificadoByNumTarjeta",
				new Object[] { numeroTarjeta, numCertificado }, types, CcTblcertificado.class);

		return response;
	}

	/**
	 * @see Actualiza un certificado con base a su numero de tarjeta, num
	 *      certificado y idUsuario, se debe revisar si este metodo es valido
	 * @param numCertificado
	 * @param numTarjeta
	 * @param idUsuario
	 */
	@Deprecated
	public void updateCertificado(String numCertificado, String numTarjeta, String idUsuario) {

		CcTblcertificado certificado = findCertificadoByNumTarjeta(numTarjeta, numCertificado);

		certificado.setAplicado(AplicadoCatalog.SI.getValue());
		certificado.setIdusuario(idUsuario);

		try {
			dao.update(certificado, CcTblcertificado.class);
		} catch (IOException e) {
			log.error("Error to update certificado [{}]", e.getMessage(), e);
		}

	}

	/**
	 * @see actualiza el certificado a aplicado
	 * @param certificado
	 * @param idUsuario
	 */
	public void updateCertificado(CcTblcertificado certificado, String idUsuario) throws BussinesException {

		certificado.setAplicado(AplicadoCatalog.SI.getValue());
		certificado.setIdusuario(idUsuario);

		try {
			dao.update(certificado, CcTblcertificado.class);
		} catch (Exception e) {
			log.error("Error to updateCertificado [{}] [{}] [{}] ", certificado, idUsuario, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see Busca la tarjeta certificado con base a su numero de certificado
	 * @param numCertificado
	 * @return
	 * @throws BussinesException
	 */
	public CcTbltarjetacertificado findTarjetaCertificado(String numCertificado) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		CcTbltarjetacertificado response = null;

		try {
			response = dao.findObjectByQuery("findTarjetaCertificadoByNumCertificado", new Object[] { numCertificado },
					types, CcTbltarjetacertificado.class);
		} catch (Exception e) {
			log.error("Error to findTarjetaCertificado [{}] [{}]  ", numCertificado, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}
		return response;
	}

	/**
	 * @see Busca el certificado con base a su numero de certificado
	 * @param numCertificado
	 * @return
	 * @throws BussinesException
	 */
	public CcTblcertificado findCertificadoByNumCertificado(String numCertificado) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		CcTblcertificado response = null;

		try {
			response = dao.findObjectByQuery("findCertificadoByNumCertificado", new Object[] { numCertificado }, types,
					CcTblcertificado.class);
		} catch (Exception e) {
			log.error("Error to findCertificadoByNumCertificado [{}] [{}]  ", numCertificado, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

		return response;
	}

	@Deprecated
	public CcTbltarjetacertificado findTarjetaCertificadoByNumTarjeta(String numCertificado, String numeroTarjeta) {

		Type[] types = { StringType.INSTANCE, StringType.INSTANCE };

		CcTbltarjetacertificado response = dao.findObjectByQuery("findTarjetaCertificadoByNumTarjeta",
				new Object[] { numCertificado, numeroTarjeta }, types, CcTbltarjetacertificado.class);

		return response;
	}

	/**
	 * @see se trae la TarjetaCertificado por medio del num tarjeta
	 * @param numeroTarjeta
	 * @return
	 * @throws BussinesException
	 */
	public CcTbltarjetacertificado findTarjetaCertificadoByNumTarjeta(String numeroTarjeta) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		CcTbltarjetacertificado response = null;

		try {
			response = dao.findObjectByQuery("findTarjetaCertificadoByJustNumTarjeta", new Object[] { numeroTarjeta },
					types, CcTbltarjetacertificado.class);
		} catch (Exception e) {
			log.error("Error to findTarjetaCertificadoByNumTarjeta [{}] [{}]  ", numeroTarjeta, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}
		return response;
	}

	/**
	 * @see actualiza a cancelado la tarjeta de certificados
	 * @param idUsuario
	 * @param tarjeta
	 */
	public void updateCanceladoTarjetaCertificados(String idUsuario, CcTbltarjetacertificado tarjeta)
			throws BussinesException {

		tarjeta.setEstatus(EstatusCatalog.CANCELADO.getValue());
		tarjeta.setIdusuario(idUsuario);

		try {
			dao.update(tarjeta, CcTbltarjetacertificado.class);
		} catch (Exception e) {
			log.error("Error to updateCanceladoTarjetaCertificados [{}] [{}]  ", idUsuario, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see Actualizamos a cancelado el certificado
	 * @param idUsuario
	 * @param certificado
	 * @throws BussinesException
	 */
	public void updateCertificado(String idUsuario, CcTblcertificado certificado) throws BussinesException {

		certificado.setAplicado(AplicadoCatalog.CANCELADO.getValue());
		certificado.setIdusuario(idUsuario);

		try {
			dao.update(certificado, CcTblcertificado.class);
		} catch (Exception e) {
			log.error("Error to updateCertificado [{}] [{}]  ", idUsuario, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see crea un movimiento del certificado
	 * @param tarjeta
	 * @param movto
	 * @throws BussinesException
	 */
	public void saveMovtoCertificado(CcTbltarjetacertificado tarjeta, MovimientoCertificadoTO movto)
			throws BussinesException {

		CcTblmovimientocertificado entity = new CcTblmovimientocertificado();

		CcTblmovimientocertificadoId key = new CcTblmovimientocertificadoId();

		String folio = CertificadosUtils.getFolioMovimiento(tarjeta.getNumcertificado());

		key.setFolio(folio);
		key.setNumcertificado(tarjeta.getNumcertificado());
		key.setNumtarjeta(tarjeta.getNumtarjeta());
		key.setPuntovta(movto.getPuntoVenta());
		key.setFechaoper(DateUtilsClaro.nowTimeStamp());

		entity.setId(key);

		entity.setIdmovto(EstatusCatalog.ACTIVO.getValue());
		entity.setIdusuario(movto.getIdUsuario());
		entity.setEstatus(AplicadoCatalog.APLICADO.getValue());
		entity.setReferencia(movto.getReferencia());

		float valorAplicado = movto.getValorAplicado();
		float valorAnterior = tarjeta.getValorrestante().floatValue();
		float valorRestante = valorAnterior - valorAplicado;

		entity.setValoraplicado(new BigDecimal(valorAplicado));
		entity.setValoranterior(new BigDecimal(valorAnterior));
		entity.setValorrestante(new BigDecimal(valorRestante));

		Serializable keySaved = null;

		try {
			keySaved = dao.save(entity, CcTblmovimientocertificado.class);
			log.info("key [{}]", keySaved);
		} catch (Exception e) {
			log.error("Error to saveMovtoCertificado [{}] ", e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see crea un movto como caducado y aplicado
	 * @param tarjeta
	 * @param movto
	 * @param folio
	 * @param idUsuario
	 * @param ptoVenta
	 * @param referencia
	 * @param valorRestante
	 * @throws BussinesException
	 */
	public void saveMovtoCertificado(CcTbltarjetacertificado tarjeta, CcTblmovimientocertificado movto, String folio,
			String idUsuario, String ptoVenta, String referencia, float valorRestante) throws BussinesException {

		CcTblmovimientocertificado entity = new CcTblmovimientocertificado();
		CcTblmovimientocertificadoId key = new CcTblmovimientocertificadoId();

		key.setFolio(folio);
		key.setNumcertificado(tarjeta.getNumcertificado());
		key.setNumtarjeta(tarjeta.getNumtarjeta());
		key.setPuntovta(ptoVenta);
		key.setFechaoper(DateUtilsClaro.nowTimeStamp());

		entity.setId(key);

		entity.setIdmovto(EstatusCatalog.CADUCADO.getValue());
		entity.setIdusuario(idUsuario);
		entity.setEstatus(AplicadoCatalog.APLICADO.getValue());
		entity.setReferencia(referencia);

		entity.setValoraplicado(movto.getValoraplicado());
		entity.setValoranterior(tarjeta.getValorrestante());
		entity.setValorrestante(new BigDecimal(valorRestante));

		Serializable keySaved = null;

		try {
			keySaved = dao.save(entity, CcTblmovimientocertificado.class);
			log.info("key [{}]", keySaved);
		} catch (Exception e) {
			log.error("Error to saveMovtoCertificado [{}] ", e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see actualiza el valor restante de la tarjeta certificado
	 * @param tarjeta
	 * @param valorRestante
	 * @throws BussinesException
	 */
	public void updateTarjetaCertificado(CcTbltarjetacertificado tarjeta, float valorRestante, String idUsuario)
			throws BussinesException {

		// TODO preguntar si se debe actualizar con el id usuario
		tarjeta.setValorrestante(new BigDecimal(valorRestante));
		tarjeta.setIdusuario(idUsuario);

		try {
			dao.update(tarjeta, CcTbltarjetacertificado.class);
		} catch (Exception e) {
			log.error("Error to updateTarjetaCertificado [{}] [{}]  ", valorRestante, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	public void updateTarjetaCertificado(CcTbltarjetacertificado tarjeta, String idUsuario) throws BussinesException {

		// TODO preguntar si se debe actualizar con el id usuario
		tarjeta.setEstatus(EstatusCatalog.ACTIVO.getValue());
		tarjeta.setIdusuario(idUsuario);

		try {
			dao.update(tarjeta, CcTbltarjetacertificado.class);
		} catch (Exception e) {
			log.error("Error to updateTarjetaCertificado  [{}]  ", e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

	/**
	 * @see Busca el Movto del cerftificado por folio
	 * @param folio
	 * @return
	 * @throws BussinesException
	 */
	public CcTblmovimientocertificado findMovtoCertificadoByFolio(String folio) throws BussinesException {

		Type[] types = { StringType.INSTANCE };

		CcTblmovimientocertificado response = null;
		try {
			response = dao.findObjectByQuery("findMovtoCertificadoByFolio", new Object[] { folio }, types,
					CcTblmovimientocertificado.class);
		} catch (Exception e) {
			log.error("Error to findMovtoCertificadoByFolio  [{}] [{}]  ", folio, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

		return response;

	}

	/**
	 * @see actualiza el movto a cancelado
	 * @param movto
	 * @param idUsuario
	 * @throws BussinesException
	 */
	public void updateMovtoCertificadoCancelado(CcTblmovimientocertificado movto, String idUsuario)
			throws BussinesException {
		// TODO revisar si se debe actualizar el idusuario
		movto.setEstatus(AplicadoCatalog.MOVTO_CANCELADO.getValue());
		movto.setIdusuario(idUsuario);

		try {
			dao.update(movto, CcTblmovimientocertificado.class);
		} catch (Exception e) {
			log.error("Error to updateMovtoCertificado [{}] [{}]  ", idUsuario, e.getMessage(), e);
			ErrorCatalog error = ErrorCatalog.OTROS;
			CertificadosUtils.setError(e, error);
			throw new BussinesException(error);
		}

	}

}