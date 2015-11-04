/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.exception.ClaroException
 *  com.claro.transfer.AlianzasTO
 *  com.claro.transfer.AsignaPorErrorTO
 *  com.claro.transfer.AvisosTO
 *  com.claro.transfer.BeneficioTO
 *  com.claro.transfer.CatalogoTO
 *  com.claro.transfer.CiudadEdoTO
 *  com.claro.transfer.CteExcelenteTO
 *  com.claro.transfer.CuentaPadreTO
 *  com.claro.transfer.FactorTO
 *  com.claro.transfer.FolioLiberacionTO
 *  com.claro.transfer.FolioSAPTO
 *  com.claro.transfer.FuerzaVentasTO
 *  com.claro.transfer.ImpresionTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.MotivoTO
 *  com.claro.transfer.MovimientoTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PlanTO
 *  com.claro.transfer.PrivilegioTO
 *  com.claro.transfer.ProductosSmsTO
 *  com.claro.transfer.ProductosTO
 *  com.claro.transfer.PromoBeneficiosTO
 *  com.claro.transfer.PromocionTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.RedencionTO
 *  com.claro.transfer.ReservacionTO
 *  com.claro.transfer.RetencionTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO
 *  com.claro.transfer.certificados.MensajeServiceTO
 *  com.claro.transfer.certificados.MovimientoCertificadoTO
 *  com.claro.transfer.certificados.TarjetaCertificadoTO
 *  com.claro.transfer.inbursa.InbursaTO
 *  com.claro.transfer.membresia.MembresiaTO
 *  com.claro.transfer.promociones.GrupoTO
 *  com.claro.transfer.reportes.AcumuladosTO
 *  com.claro.transfer.reportes.AltoValorTO
 *  com.claro.transfer.reportes.CertificadoLealtadTO
 *  com.claro.transfer.reportes.ComisionTO
 *  com.claro.transfer.reportes.PuntosVencerTO
 *  com.claro.transfer.reportes.RedencionDetalleTO
 *  com.claro.transfer.reportes.RedencionLineaTO
 *  com.claro.transfer.reportes.RedencionReporteTO
 *  com.claro.transfer.reportes.RedencionesOnlineTO
 *  com.claro.transfer.reportes.Reportable
 *  com.claro.transfer.reportes.RetencionReporteTO
 *  com.claro.transfer.reportes.RoextTO
 *  com.claro.transfer.service.DocumentoTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.transfer.service.ReservacionServiceTO
 *  com.claro.transfer.service.TelefonoServiceTO
 *  com.claro.transfer.transpuntos.TransferenciaTO
 *  javax.ejb.CreateException
 *  javax.ejb.EJBException
 *  javax.ejb.SessionBean
 *  javax.ejb.SessionContext
 */
package com.claro.ejb;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.claro.catalogo.Catalogo;
import com.claro.certificados.dao.CertificadosDAO;
import com.claro.dao.AdministracionDAO;
import com.claro.dao.AlianzasDAO;
import com.claro.dao.AsignacionDAO;
import com.claro.dao.AsignacionPorErrorAplicaDAO;
import com.claro.dao.AsignacionporErrorDAO;
import com.claro.dao.AvisosDAO;
import com.claro.dao.CatalogoDAO;
import com.claro.dao.ConsultaM2KDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.dao.ImpresionDAO;
import com.claro.dao.LineasRestringidasDAO;
import com.claro.dao.MembresiaDAO;
import com.claro.dao.PuntitosDAO;
import com.claro.dao.PuntosDAO;
import com.claro.dao.RedencionDAO;
import com.claro.dao.RenunciaDAO;
import com.claro.dao.RetencionDAO;
import com.claro.dao.SeguridadDAO;
import com.claro.dao.SimCardDAO;
import com.claro.dao.TransferenciaAnexoDAO;
import com.claro.dao.TransferenciaCaregDAO;
import com.claro.dao.TransferenciaCteExcelenteDAO;
import com.claro.dao.UsuarioDAO;
import com.claro.exception.CAException;
import com.claro.exception.ClaroException;
import com.claro.inbursa.dao.ConsultaInbursaDAO;
import com.claro.promociones.dao.PromocionesDAO;
import com.claro.redencion.dao.RedencionMulticotizadorDAO;
import com.claro.redencion.sms.NotificaSMS;
import com.claro.reportes.dao.ReportesCirculoAzulDAO;
import com.claro.transfer.AlianzasTO;
import com.claro.transfer.AsignaPorErrorTO;
import com.claro.transfer.AvisosTO;
import com.claro.transfer.BeneficioTO;
import com.claro.transfer.CatalogoTO;
import com.claro.transfer.CiudadEdoTO;
import com.claro.transfer.CteExcelenteTO;
import com.claro.transfer.CuentaPadreTO;
import com.claro.transfer.FactorTO;
import com.claro.transfer.FolioLiberacionTO;
import com.claro.transfer.FolioSAPTO;
import com.claro.transfer.FuerzaVentasTO;
import com.claro.transfer.ImpresionTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.MotivoTO;
import com.claro.transfer.MovimientoTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.PrivilegioTO;
import com.claro.transfer.ProductosSmsTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PromoBeneficiosTO;
import com.claro.transfer.PromocionTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.ReservacionTO;
import com.claro.transfer.RetencionTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO;
import com.claro.transfer.certificados.MensajeServiceTO;
import com.claro.transfer.certificados.MovimientoCertificadoTO;
import com.claro.transfer.certificados.TarjetaCertificadoTO;
import com.claro.transfer.inbursa.InbursaTO;
import com.claro.transfer.membresia.MembresiaTO;
import com.claro.transfer.promociones.GrupoTO;
import com.claro.transfer.reportes.AcumuladosTO;
import com.claro.transfer.reportes.AltoValorTO;
import com.claro.transfer.reportes.CertificadoLealtadTO;
import com.claro.transfer.reportes.ComisionTO;
import com.claro.transfer.reportes.PuntosVencerTO;
import com.claro.transfer.reportes.RedencionDetalleTO;
import com.claro.transfer.reportes.RedencionLineaTO;
import com.claro.transfer.reportes.RedencionReporteTO;
import com.claro.transfer.reportes.RedencionesOnlineTO;
import com.claro.transfer.reportes.Reportable;
import com.claro.transfer.reportes.RetencionReporteTO;
import com.claro.transfer.reportes.RoextTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.transfer.service.ReservacionServiceTO;
import com.claro.transfer.service.TelefonoServiceTO;
import com.claro.transfer.transpuntos.TransferenciaTO;

public class CirculoAzulBean implements SessionBean {
	static final long serialVersionUID = 3206093459760846163L;
	private SessionContext mySessionCtx;
	private UsuarioDAO usuarioDAO;
	private ConsultasDAO consultasDAO;
	private AlianzasDAO alianzasDAO;
	private CatalogoDAO catalogoDAO;
	private ConsultaM2KDAO consultaM2KDAO;
	private SimCardDAO simCardDAO;
	private SeguridadDAO seguridadDAO;
	private ReportesCirculoAzulDAO reportesCirculoAzulDAO;
	private LineasRestringidasDAO lineasRestringidasDAO;

	public String foo(String param) {
		return null;
	}

	public SessionContext getSessionContext() {
		return this.mySessionCtx;
	}

	public void setSessionContext(SessionContext ctx) throws EJBException,
			RemoteException {
		this.mySessionCtx = ctx;
		this.usuarioDAO = new UsuarioDAO();
		this.consultasDAO = new ConsultasDAO();
		this.alianzasDAO = new AlianzasDAO();
		this.catalogoDAO = new CatalogoDAO();
		this.simCardDAO = new SimCardDAO();
		this.consultaM2KDAO = new ConsultaM2KDAO();
		this.seguridadDAO = new SeguridadDAO();
		this.reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
		this.lineasRestringidasDAO = new LineasRestringidasDAO();
	}

	public void ejbCreate() throws CreateException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
		this.usuarioDAO = null;
		this.consultasDAO = null;
		this.alianzasDAO = null;
		this.catalogoDAO = null;
		this.simCardDAO = null;
		this.consultaM2KDAO = null;
		this.seguridadDAO = null;
	}

	public UsuarioTO consultaEmpleado(String numEmpleado, String sIP,
			String passActual, boolean validaPwd) throws CAException {
		return this.usuarioDAO.consultaEmpleado(numEmpleado, sIP, passActual,
				validaPwd);
	}

	public UsuarioTO validaUserPerfil(String lClave, String sIP)
			throws CAException {
		return this.usuarioDAO.validaUserPerfil(lClave, sIP);
	}

	public PerfilTO getPrivilegiosCirculoAzulEcac(int idPerfiln)
			throws CAException {
		return this.usuarioDAO.getPrivilegiosCirculoAzulEcac(idPerfiln);
	}

	public UsuarioTO consultaUsuario(UsuarioTO usuarioTO) throws Exception {
		return this.usuarioDAO.consultaUsuario(usuarioTO);
	}

	public boolean agregarUsuario(UsuarioTO usuarioTO) throws Exception {
		return this.usuarioDAO.agregaUsuario(usuarioTO);
	}

	public boolean actualizaUsuario(UsuarioTO usuarioCA,
			UsuarioTO usuarioActualizado, int perfilAnterior) throws Exception {
		return this.usuarioDAO.actualizaUsuario(usuarioCA, usuarioActualizado,
				perfilAnterior);
	}

	public boolean eliminaUsuario(UsuarioTO usuarioTO) throws Exception {
		return this.usuarioDAO.eliminaUsuario(usuarioTO);
	}

	public void borraArchivo(DocumentoTO documentoTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		promocionesDAO.borraArchivo(documentoTO);
	}

	public void guardaArchivo(DocumentoTO documentoTO, FileDataTO fileDataTO)
			throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		promocionesDAO.guardaArchivo(documentoTO, fileDataTO);
	}

	public int insertaPromocion(DocumentoTO documentoTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.insertaPromocion(documentoTO);
	}

	public void borrarPromocion(DocumentoTO documentoTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		promocionesDAO.borrarPromocion(documentoTO.getIdDocumento());
	}

	public int obtenIDDocumento() throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.obtenIDDocumento();
	}

	public ArrayList<DocumentoTO> obtenPromociones(String estatus)
			throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.obtenPromociones(estatus);
	}

	public ArrayList<PromocionTO> consultaPromociones(PromocionTO promocionTO)
			throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaPromociones(promocionTO);
	}

	public ArrayList<PlanTO> consultaPlanes(PlanTO planTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaPlanes(planTO);
	}

	public ArrayList<FuerzaVentasTO> consultaFuerzaVentas(
			FuerzaVentasTO fuerzaVentasTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaFuerzaVentas(fuerzaVentasTO);
	}

	public ArrayList<GrupoTO> consultaGpoPromo(GrupoTO grupoTO)
			throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaGpoPromo(grupoTO);
	}

	public byte[] generaReporte(InputStream archivo,
			HashMap<String, String> mapa) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.generaReporte(archivo, mapa);
	}

	public byte[] generaReporteCSV(InputStream archivo,
			HashMap<String, String> mapa) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.generaReporteCSV(archivo, mapa);
	}

	public ArrayList<RedencionTO> detalleRedencion(String cuenta,
			int secuencia, String fecha) throws CAException {
		return this.consultasDAO.detalleRedencion(cuenta, secuencia, fecha);
	}

	public ArrayList<MovimientoTO> detalleMovimientos(String cuenta,
			String secuencia) throws CAException {
		return this.consultasDAO.detalleMovimientos(cuenta, secuencia);
	}

	public ArrayList<AlianzasTO> consultaCanjesAlianzas(String cuenta,
			String secuencia) throws CAException {
		return this.alianzasDAO.consultaCanjesAlianzas(cuenta, secuencia);
	}

	public TelefonoTO procedimientoGeneral(ParametrosTO parametrosTO,
			PerfilTO perfilTO, String fzaVentas) throws CAException {
		return this.consultasDAO.procedimientoGeneral(parametrosTO, perfilTO,
				fzaVentas);
	}

	public FactorTO consultaFactor(int idAlianza, int nPtsDisp)
			throws CAException {
		return this.alianzasDAO.consultaFactor(idAlianza, nPtsDisp);
	}

	public ArrayList<CatalogoTO> getMarcas(int region, int grupo)
			throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getMarcas(region, grupo);
	}

	public ArrayList<CatalogoTO> getModelos(String marca, int region,
			int grupo, String fzaVentas) throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getModelos(marca, region, grupo, fzaVentas);
	}

	public ArrayList<CuentaPadreTO> consultaPadre(String cuenta)
			throws CAException {
		return this.consultasDAO.consultaPadre(cuenta);
	}

	public String consultaPrecioEquipo(String plan, int region, String marca,
			String modelo, String estatus) throws CAException {
		return this.consultasDAO.consultaPrecioEquipo(plan, region, marca,
				modelo, estatus);
	}

	public int consultaRegion(String lTelefono) throws CAException {
		return this.consultasDAO.consultaRegion(lTelefono);
	}

	public RedencionTO aplicaredencion(ProductosTO productosTO,
			ParametrosTO parametrosTO, RedencionTO redencionTO, boolean isEcac,
			boolean isDistribuidores, String endPoint, String usuario,
			BeneficioTO beneficioTO, String endpointGap) throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.aplicaredencion(productosTO, parametrosTO,
				redencionTO, isEcac, isDistribuidores, endPoint, usuario,
				beneficioTO, endpointGap);
	}

	public TelefonoTO procedimientoGeneralPuntitos(ParametrosTO parametrosTO)
			throws CAException {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.procedimientoGeneral(parametrosTO);
	}

	public ArrayList<TelefonoTO> consultaLinea(TelefonoTO telefonoTO)
			throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.consultaLinea(telefonoTO);
	}

	public ArrayList<TelefonoTO> consultaReserva(TelefonoTO telefonoTO)
			throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.consultaReserva(telefonoTO);
	}

	public boolean cancelaReserva(TelefonoTO telefonoTO) throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.cancelaReserva(telefonoTO);
	}

	public ArrayList<PuntoVentaTO> consultaPtosVta(PuntoVentaTO puntoVentaTO)
			throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.consultaPtosVta(puntoVentaTO);
	}

	public boolean agregaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.agregaPtoVta(puntoVentaTO);
	}

	public boolean actualizaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.actualizaPtoVta(puntoVentaTO);
	}

	public boolean eliminaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
		PuntitosDAO puntitosDAO = new PuntitosDAO();
		return puntitosDAO.eliminaPtoVta(puntoVentaTO);
	}

	public ArrayList<CatalogoTO> obtienePropiedades() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.obtienePropiedades();
	}

	public ArrayList<CatalogoTO> obtienePerfil() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.obtienePerfil();
	}

	public ArrayList<CatalogoTO> obtieneGrupoPromocion() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.obtieneGrupoPromocion();
	}

	public Map<String, PrivilegioTO> getprivilegiosCa() throws CAException {
		SeguridadDAO seguridadDAO = new SeguridadDAO();
		return seguridadDAO.getprivilegiosCa();
	}

	public ArrayList<ProductosTO> listaPromociones(int iRegion,
			int iRangoPuntos, String sPlan, int iGrupoPromo,
			String cacsFzaVenta, String todosFzaVenta, String marca,
			String modelo) throws CAException {
		return this.catalogoDAO.listaPromociones(iRegion, iRangoPuntos, sPlan,
				iGrupoPromo, cacsFzaVenta, todosFzaVenta, marca, modelo);
	}

	public ArrayList<ProductosTO> consultaProductos(String ptsExactos,
			String marca, String modelo, TelefonoTO telefonoTO,
			UsuarioTO usuarioTO, String tipoRed, String formaRed,
			String planNuevo, double costoPuntos, String cacsFzaVenta,
			String todosFzaVenta, String fzaVentasDistribuidores,
			int mesAdendum, int diaAdendum, int diasMesAdendum,
			int adendumNuevo, String endpointGap) throws CAException {
		return this.catalogoDAO.consultaProductos(ptsExactos, marca, modelo,
				telefonoTO, usuarioTO, tipoRed, formaRed, planNuevo,
				costoPuntos, cacsFzaVenta, todosFzaVenta,
				fzaVentasDistribuidores, mesAdendum, diaAdendum,
				diasMesAdendum, adendumNuevo, endpointGap);
	}

	public ArrayList<AlianzasTO> listaCertLealtad(String sCuenta, int iSecuencia)
			throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.listaCertLealtad(sCuenta, iSecuencia);
	}

	public RetencionTO calculaValorCupon(Double dARPU, int iMeses, int iBajas,
			int iPcAutor) throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.calculaValorCupon(dARPU, iMeses, iBajas, iPcAutor);
	}

	public MensajeTO actualizaPassword(String _usuario, String _passActual,
			String _passNuevo, String _passConfirm) throws CAException {
		AdministracionDAO adminDAO = new AdministracionDAO();
		return adminDAO.actualizarPassword(_usuario, _passActual, _passNuevo,
				_passConfirm);
	}

	public PlanTO datosPlan(String sPlan, int region) throws CAException {
		TelefonoTO telefonoTO = new TelefonoTO();
		this.consultasDAO.datosPlan(sPlan, region, null, telefonoTO);
		PlanTO planTO = new PlanTO();
		planTO.setIdGrupoPromocion(telefonoTO.getPlanTO().getIdGrupoPromocion());
		planTO.setTipoPromocion(telefonoTO.getPlanTO().getTipoPromocion());
		planTO.setAdendum(telefonoTO.getPlanTO().getAdendumNvo());
		planTO.setBanRedencionAnct(telefonoTO.getPlanTO().getBanRedencionAnct());
		planTO.setSegmento(telefonoTO.getPlanTO().getSegmento());
		planTO.setIdMensaje(telefonoTO.getIdMensaje());
		planTO.setMensaje(telefonoTO.getMensaje());
		return planTO;
	}

	public MensajeTO actualizaFolioSAP(FolioSAPTO folioSAPTO)
			throws CAException {
		PuntosDAO puntosDAO = new PuntosDAO();
		return puntosDAO.actualizaFolioSAP(folioSAPTO);
	}

	public ArrayList<RedencionTO> consultaFolioSAP(String folio,
			String tipoRedencion, String estatus, int secuencia, String cuenta,
			int region, boolean consultaM2K) throws CAException {
		return this.consultasDAO.consultaFolioSAP(folio, tipoRedencion,
				estatus, secuencia, cuenta, region, consultaM2K);
	}

	public ImpresionTO datosConstanciaImpresion(String sTelefono,
			String cuenta, String fechaFolio, String folio,
			String fechaOperacion, String tipoReden) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.datosConstanciaImpresion(sTelefono, cuenta,
				fechaFolio, folio, fechaOperacion, tipoReden);
	}

	public ImpresionTO datosConstanciaBonoInbursa(String sTelefono,
			String cuenta) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.datosConstanciaBonoInbursa(sTelefono, cuenta);
	}

	public ReservacionTO realizaApartado(ReservacionTO reservacionTO,
			TelefonoTO telefonoTO, boolean isDistribuidores, String fzaVentas)
			throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.realizaApartado(reservacionTO, telefonoTO,
				isDistribuidores, fzaVentas);
	}

	public ArrayList<ImpresionTO> detalleBonosInbursa(String sTelefono,
			String cuenta) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.detalleBonosInbursa(sTelefono, cuenta);
	}

	public ArrayList<ImpresionTO> detalleRedenImp(String sTelefono,
			String cuenta, String sFecha) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.detalleRedenImp(sTelefono, cuenta, sFecha);
	}

	public ImpresionTO obtieneConstanciaAlianzas(String sFecha, String sCuenta,
			int iSecuencia, int tipoAlianza, String sFechaFolio, String sLinea)
			throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.obtieneConstanciaAlianzas(sFecha, sCuenta,
				iSecuencia, tipoAlianza, sFechaFolio, sLinea);
	}

	public ArrayList<ImpresionTO> detalleImpresionAlianzas(String sFecha,
			String sCuenta, int iSecuencia, int tipoAlianza, String sFolio)
			throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.detalleImpresionAlianzas(sFecha, sCuenta,
				iSecuencia, tipoAlianza, sFolio);
	}

	public PuntosTO obtienePuntos(String cuenta, int secuencia)
			throws CAException {
		return this.consultasDAO.obtienePuntos(cuenta, secuencia);
	}

	public MensajeTO cancelaApartado(ParametrosTO parametrosTO,
			UsuarioTO usuarioTO, boolean isDistribuidores) throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.cancelaApartado(parametrosTO, usuarioTO,
				isDistribuidores);
	}

	public MensajeTO agregarMembresia(MembresiaTO membresiaTO)
			throws CAException {
		MembresiaDAO membresiaDAO = new MembresiaDAO();
		return membresiaDAO.agregarMembresia(membresiaTO);
	}

	public MensajeTO actualizaPromoAlianza(String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.actualizaPromoAlianza(sCuentaAl, iCveAl, sCuenta,
				iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel);
	}

	public MensajeTO altaPromoAlianza(String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.altaPromoAlianza(sCuentaAl, iCveAl, sCuenta,
				iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel);
	}

	public MembresiaTO consultaMembresia(String cuenta, int secuencia,
			Date fecha) throws CAException {
		MembresiaDAO membresiaDAO = new MembresiaDAO();
		return membresiaDAO.consultaMembresia(cuenta, secuencia, fecha);
	}

	public MensajeTO reactivaPuntos(ParametrosTO parametrosTO)
			throws CAException {
		RenunciaDAO renunciaDAO = new RenunciaDAO();
		return renunciaDAO.reactivaPuntos(parametrosTO);
	}

	public MensajeTO renunciaPuntos(ParametrosTO parametrosTO)
			throws CAException {
		RenunciaDAO renunciaDAO = new RenunciaDAO();
		return renunciaDAO.renunciaPuntos(parametrosTO);
	}

	public MensajeTO confirmaCanje(int lValViaje,
			FolioLiberacionTO folioLiberacion, int lNumAcomp, String lIdCanje,
			String sCuenta, int isecuencia, String sTelefono, String sUsuario,
			int iRegion) throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.confirmaCanje(lValViaje, folioLiberacion, lNumAcomp,
				lIdCanje, sCuenta, isecuencia, sTelefono, sUsuario, iRegion);
	}

	public MensajeTO cancelaRedencion(ParametrosTO parametrosTO,
			String fechaOperacion, int diasValidacion, UsuarioTO usuarioTO)
			throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.cancelaRedencion(parametrosTO, fechaOperacion,
				diasValidacion, usuarioTO);
	}

	public TransferenciaTO transfierePuntosAnexo(TransferenciaTO transfTO)
			throws CAException {
		TransferenciaAnexoDAO transfiereAnexoDAO = new TransferenciaAnexoDAO();
		return transfiereAnexoDAO.transferirPuntosAnexo(transfTO);
	}

	public TransferenciaTO transfierePuntosRegion(TransferenciaTO transfTO)
			throws CAException {
		TransferenciaCaregDAO transfiereCaregDAO = new TransferenciaCaregDAO();
		return transfiereCaregDAO.transferirPuntosCareg(transfTO);
	}

	public ArrayList<AlianzasTO> consultaCancelaCanje(String telefono,
			String cuentaAlianza, int opcion) throws CAException {
		return this.consultasDAO.consultaCancelaCanje(telefono, cuentaAlianza,
				opcion);
	}

	public MensajeTO realizaCancelacion(ParametrosTO parametrosTO,
			int estatusTrans) throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.realizaCancelacion(parametrosTO, estatusTrans);
	}

	public MensajeTO realizaCanje(ParametrosTO parametrosTO,
			int millasPorTranferir, int valorPuntos, String descAlianza,
			String cuentaAlianza, int ptsTransferir, int valorCertificado,
			String puntoVenta, String numEmpleado, String dirIP,
			String idViaje, int valorPesos, String referencia, int opcion)
			throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.realizaCanje(parametrosTO, millasPorTranferir,
				valorPuntos, descAlianza, cuentaAlianza, ptsTransferir,
				valorCertificado, puntoVenta, numEmpleado, dirIP, idViaje,
				valorPesos, referencia, opcion);
	}

	public MensajeTO cancelaAlianza(String sCuenta, String sCuentaAl, int iCveAl)
			throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.cancelaAlianza(sCuenta, sCuentaAl, iCveAl);
	}

	public ArrayList<ImpresionTO> detalleEquipo(String sFecha, String sTipoRed,
			String sFechaFolio, String sCuenta, String sTelefono)
			throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.detalleEquipo(sFecha, sTipoRed, sFechaFolio,
				sCuenta, sTelefono);
	}

	public ArrayList<CiudadEdoTO> getEstadoCiudad(String estado)
			throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getEstadoCiudad(estado);
	}

	public ReservacionTO consultaFolioReservacion(String folio, String estatus)
			throws CAException {
		return this.consultasDAO.obtieneReservacion(folio, estatus, false);
	}

	public Hashtable<String, AlianzasTO> consultaInicialAlianzas(int iRegion,
			String sCuenta, int nPtsDisp, int iSecuencia) throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.consultaInicialAlianzas(iRegion, sCuenta, nPtsDisp,
				iSecuencia);
	}

	public AlianzasTO consultaPromocionesAmex(int alianza, int iRegion,
			String linea, int iSecuencia, int ptosDisponibles)
			throws CAException {
		AlianzasDAO alianzasDAO = new AlianzasDAO();
		return alianzasDAO.consultaPromocionesAmex(alianza, iRegion, linea,
				iSecuencia, ptosDisponibles);
	}

	public MensajeTO procesaAsignacion(ParametrosTO parametrosTO,
			String usuario, int accion, int ptsAsignar, String comentario,
			String motivoAsig) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.procesaAsignacion(parametrosTO, usuario, accion,
				ptsAsignar, comentario, motivoAsig);
	}

	public MensajeTO consultaCertificado(String cuenta, String telefono)
			throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.consultaCertificado(cuenta, telefono);
	}

	public RetencionTO consultaRetencion(String sCta, String sSec)
			throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.consultaRetencion(sCta, sSec);
	}

	public MensajeTO validaImpresion(String sFolio, Date dfechaM2K)
			throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.validaImpresion(sFolio, dfechaM2K);
	}

	public MensajeTO cancelarCertificado(String sUsuario, String sModo,
			String sFolio, String sComenta) throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.cancelarCertificado(sUsuario, sModo, sFolio,
				sComenta);
	}

	public FolioLiberacionTO asignaPorAntiguedad(ParametrosTO parametrosTO,
			String lFecAltaM2K, String comentario, String usuario)
			throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.asignaPorAntiguedad(parametrosTO, lFecAltaM2K,
				comentario, usuario);
	}

	public void obtenFacturaciones(MobileTO mobileTO, int region,
			String cuenta, int numFacturas) throws CAException {
		ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
		consultaM2KDAO
				.obtenFacturaciones(mobileTO, region, cuenta, numFacturas);
	}

	public MobileTO consultaPorCuenta(String lCta, int nReg) throws CAException {
		ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
		return consultaM2KDAO.consultaPorCuenta(lCta, nReg);
	}

	public MensajeTO asignacionPuntos(ParametrosTO parametrosTO,
			int ptsAsignar, String ip) throws CAException {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.asignacionPuntos(parametrosTO, ptsAsignar, ip);
	}

	public MobileTO consultaDatosM2K(ParametrosTO parametrosTO)
			throws CAException {
		return this.consultaM2KDAO.consultaDatosM2K(parametrosTO);
	}

	public MensajeTO almacenaCertificado(TelefonoTO telefonoTO,
			UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses,
			String sMotivo, String sComenta, String sComenta2,
			String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo)
			throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.almacenaCertificado(telefonoTO, usuarioTO,
				retencionTO, sNMeses, sMotivo, sComenta, sComenta2, sCuentaAnt,
				sRegionAnt, sMesesAnt, sTipo);
	}

	public RetencionTO getMotivos() throws CAException {
		RetencionDAO retencionDAO = new RetencionDAO();
		return retencionDAO.getMotivos();
	}

	public String getPromoSIM(String estatus, String segmento, String tipoProd,
			int region) throws CAException {
		return this.simCardDAO.getPromoSIM(estatus, segmento, tipoProd, region);
	}

	public TelefonoTO consultaDatosSIM(String telefono) throws CAException {
		return this.simCardDAO.consultaDatosSIM(telefono);
	}

	public ImpresionTO datosImpresion(String telefono, String fechaFolio)
			throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.datosImpresion(telefono, fechaFolio);
	}

	public ImpresionTO consultaCertificados(String lFecha, String cuenta,
			String sec) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.consultaCertificados(lFecha, cuenta, sec);
	}

	public ImpresionTO obtieneCertificado(String folio, String cuenta,
			String sec) throws CAException {
		ImpresionDAO impresionDAO = new ImpresionDAO();
		return impresionDAO.obtieneCertificado(folio, cuenta, sec);
	}

	public ArrayList<PromoBeneficiosTO> obtieneBeneficios(int region,
			String marca) throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.obtieneBeneficios(region, marca);
	}

	public ArrayList<MotivoTO> obtieneCatalogoRechazo(int tipoMotivo)
			throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.obtieneCatalogoRechazo(tipoMotivo);
	}

	public ArrayList<PlanTO> getPlanesTarifarios(int region) throws CAException {
		return this.catalogoDAO.getPlanesTarifarios(region);
	}

	public ArrayList<AvisosTO> historicoAvisos(String sFechaIni,
			String sFechaFin, String tipoAviso, String estatus)
			throws CAException {
		AvisosDAO avisosDAO = new AvisosDAO();
		return avisosDAO.historicoAvisos(sFechaIni, sFechaFin, tipoAviso,
				estatus);
	}

	public MensajeTO insertarAvisos(String sDescripcion, String sFechaFin,
			String sTipoAviso, String sUsuario, String sFechaInicio)
			throws CAException {
		AvisosDAO avisosDAO = new AvisosDAO();
		return avisosDAO.insertarAvisos(sDescripcion, sFechaFin, sTipoAviso,
				sUsuario, sFechaInicio);
	}

	public MensajeTO eliminarAvisos(String sUsuarioMod, int idAviso)
			throws CAException {
		AvisosDAO avisosDAO = new AvisosDAO();
		return avisosDAO.eliminarAvisos(sUsuarioMod, idAviso);
	}

	public ArrayList<AvisosTO> marquesinaAvisos(long fecha) throws CAException {
		AvisosDAO avisosDAO = new AvisosDAO();
		return avisosDAO.marquesinaAvisos(fecha);
	}

	public ArrayList<UsuarioTO> consultaUsuarios(UsuarioTO user, int max)
			throws CAException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultaUsuarios(user, max);
	}

	public PuntoVentaTO obtienePuntoVenta(String sIP) throws CAException {
		return this.consultasDAO.obtienePuntoVenta(sIP, null);
	}

	public PlanTO consultaPlan(String plan, int region) throws CAException {
		return this.catalogoDAO.consultaPlan(plan, region);
	}

	public MensajeTO consultaTipoPlan(String idPlan, int nReg)
			throws CAException {
		return this.consultaM2KDAO.consultaTipoPlan(idPlan, nReg);
	}

	public boolean existeUsuario(UsuarioTO usuarioTO) throws Exception {
		return this.usuarioDAO.existeUsuario(usuarioTO);
	}

	public ProductosSmsTO promocionxSms(String claveProducto, int puntosDispo)
			throws CAException {
		return this.catalogoDAO.promocionxSms(claveProducto, puntosDispo);
	}

	public PerfilTO getPrivilegiosPerfil(String idPerfil) throws CAException {
		return this.seguridadDAO.getPrivilegiosPerfil(idPerfil);
	}

	public void actualizaPrivilegios(int idPerfil,
			List<PrivilegioTO> privilegios, String numEmpleado)
			throws CAException {
		this.seguridadDAO.actualizaPrivilegios(idPerfil, privilegios,
				numEmpleado);
	}

	public List<CertificadoLealtadTO> getCertificadosLealtad(
			String fechaInicial, String fechaFinal, int idRegion)
			throws CAException {
		return this.reportesCirculoAzulDAO.getCertificadosLealtad(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<RedencionReporteTO> getRedenciones(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getRedenciones(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<RedencionDetalleTO> getRedencionesDetalle(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getRedencionesDetalle(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<RoextTO> getRoext(String fechaInicial, String fechaFinal,
			int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getRoext(fechaInicial, fechaFinal,
				idRegion);
	}

	public List<ComisionTO> getComisiones(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getComisiones(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<RetencionReporteTO> getRetenciones(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getRetenciones(fechaInicial,
				fechaFinal, idRegion);
	}

	public Map<String, List<Reportable>> getAlianzas(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getAlianzas(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<AcumuladosTO> getAcumulados(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getAcumulados(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<AltoValorTO> getAltoValor(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getAltoValor(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<PuntosVencerTO> getPuntosPorVencer(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getPuntosPorVencer(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<RedencionLineaTO> getRedencionesLinea(String fechaInicial,
			String fechaFinal, List<String> lineas) throws CAException {
		return this.reportesCirculoAzulDAO.getRedencionesLinea(fechaInicial,
				fechaFinal, lineas);
	}

	public ArrayList<PlanTO> planesRedencionByRegion(int region)
			throws CAException {
		return this.consultasDAO.planesRedencionByRegion(region);
	}

	public ArrayList<PromocionTO> consultaPromocionesDistribuidores(
			PromocionTO promocionTO, String idPlan, int idGpoPromocion)
			throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaPromocionesDistribuidores(promocionTO,
				idPlan, idGpoPromocion);
	}

	public ArrayList<CatalogoTO> getMarcasDistribuidores(int region,
			String idPlan, String fzaVentas) throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getMarcasXPlan(region, idPlan, fzaVentas);
	}

	public ArrayList<ReservacionServiceTO> consultaFoliosDistribuidores(
			String telefono, String cuenta, String fzaVenta) throws CAException {
		return this.consultasDAO.consultaFolioDistribuidores(telefono, cuenta,
				fzaVenta);
	}

	public int consultaRegionLinea(String lTelefono, String cuenta)
			throws CAException {
		return this.consultasDAO.consultaRegionLinea(lTelefono, cuenta);
	}

	public ArrayList<ProductosSmsTO> productosonline() throws CAException {
		return this.catalogoDAO.listaproductosonline();
	}

	public int valorPuntosOnline(String claveProducto) throws CAException {
		return this.catalogoDAO.valorPuntosOnline(claveProducto);
	}

	public ArrayList<ProductosSmsTO> consultaPromocionesSms(
			ProductosSmsTO productosSmsTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaPromocionesSms(productosSmsTO);
	}

	public List<RedencionesOnlineTO> getRedencionesOnline(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		return this.reportesCirculoAzulDAO.getRedencionesOnline(fechaInicial,
				fechaFinal, idRegion);
	}

	public ArrayList<CatalogoTO> consultaAreasPromocion() throws CAException {
		return this.consultasDAO.consultaAreasPromocion();
	}

	public boolean aplicaRedencionMulticotizador(String idVariable)
			throws Exception {
		RedencionMulticotizadorDAO redencionMultiDAO = new RedencionMulticotizadorDAO();
		return Boolean.parseBoolean(redencionMultiDAO
				.obtienePropiedad(idVariable));
	}

	public ArrayList<PlanTO> planesSisact(int region, String tecnologia,
			String modalidad, String mixto, String banSisact, String tipoPlan,
			String fzaVenta) throws CAException {
		return this.consultasDAO.planesSisact(region, tecnologia, modalidad,
				mixto, banSisact, tipoPlan, fzaVenta);
	}

	public boolean aplicaRedencionSisact(String folio,
			TelefonoServiceTO telefonoTO, String claveSisact)
			throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.aplicaRedencionSisact(folio, telefonoTO,
				claveSisact);
	}

	public TransferenciaTO transfierePuntosExcelente(TransferenciaTO transfTO)
			throws CAException {
		TransferenciaCteExcelenteDAO transfiereExcelenteDAO = new TransferenciaCteExcelenteDAO();
		return transfiereExcelenteDAO.transferirPuntosCteExc(transfTO);
	}

	public void fechaAcceso(UsuarioTO usuarioTO) throws Exception {
		this.usuarioDAO.fechaAcceso(usuarioTO);
	}

	public Hashtable<String, Object> validaProductoDescuento(String telefono,
			int region, String cuenta, String endPoint, String usuario)
			throws CAException {
		RedencionDAO redencionDAO = new RedencionDAO();
		return redencionDAO.validaProductoDescuento(telefono, region, cuenta,
				endPoint, usuario);
	}

	public ArrayList<CteExcelenteTO> consultaLineasCteExc(
			CteExcelenteTO cteExcelenteTO) throws Exception {
		PromocionesDAO promocionesDAO = new PromocionesDAO();
		return promocionesDAO.consultaClientExcelente(cteExcelenteTO);
	}

	public AsignaPorErrorTO VerificaEPBAJ(String cuenta, String linea)
			throws CAException {
		AsignacionporErrorDAO asignacionporErrorDAO = new AsignacionporErrorDAO();
		return asignacionporErrorDAO.VerificaEPBAJ(cuenta, linea);
	}

	public void AsignacionPorErrorAplic(AsignaPorErrorTO asignaPorErrorTO,
			String Cuenta, String Telefono, String numempleado)
			throws CAException {
		AsignacionPorErrorAplicaDAO asignacionPorErrorAplicaDAO = new AsignacionPorErrorAplicaDAO();
		asignacionPorErrorAplicaDAO.AsignacionPorErrorAplic(asignaPorErrorTO,
				Cuenta, Telefono, numempleado);
	}

	public boolean VerificasiexisteAPTOE(String Cuenta, String Telefono)
			throws CAException {
		AsignacionPorErrorAplicaDAO asignacionPorErrorAplicaDAO = new AsignacionPorErrorAplicaDAO();
		return asignacionPorErrorAplicaDAO.VerificasiexisteAPTOE(Cuenta,
				Telefono);
	}

	public ArrayList<MembresiaTO> consultaDatosM2KMembresias(
			ParametrosTO parametrosTO, FileDataTO fileDataTO)
			throws CAException {
		ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
		return consultaM2KDAO.consultaDatosM2KMembresias(parametrosTO,
				fileDataTO);
	}

	public boolean listaMembresia(ArrayList<MembresiaTO> membresias,
			int idRegion) throws CAException {
		MembresiaDAO membresiaDAO = new MembresiaDAO();
		return membresiaDAO.listaMembresia(membresias, idRegion);
	}

	public MensajeTO enviaSMS(String linea, String mensajeSMS,
			Catalogo properties) throws CAException {
		NotificaSMS notificaSMS = new NotificaSMS();
		return notificaSMS.enviaSMSRedencionCA(linea, mensajeSMS, properties);
	}

	public boolean isLineaBloqueada(String cuenta, String telefono)
			throws CAException {
		return this.lineasRestringidasDAO.isLineaBloqueada(cuenta, telefono);
	}

	public void cargaLineasRestringidas(List<TelefonoTO> lineas)
			throws CAException {
		this.lineasRestringidasDAO.cargaLineasRestringidas(lineas);
	}

	public TelefonoTO cuentaDestinoCancelacion(String cuentaOrigen,
			int secuenciaOrigen) throws CAException {
		TransferenciaCaregDAO transferencia = new TransferenciaCaregDAO();
		return transferencia.cuentaDestinoCancelacion(cuentaOrigen,
				secuenciaOrigen);
	}

	public TransferenciaTO cancelaTransferenciaCareg(TransferenciaTO _transfTO)
			throws CAException {
		TransferenciaCaregDAO transferencia = new TransferenciaCaregDAO();
		return transferencia.cancelaTransferenciaCareg(_transfTO);
	}

	public boolean ValidaCambioPass(UsuarioTO usuarioTO) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.ValidaCambioPass(usuarioTO);
	}

	public List<TelefonoTO> generaReporteLineasRestringidas() throws Exception {
		LineasRestringidasDAO lineasRestringidasDAO = new LineasRestringidasDAO();
		return lineasRestringidasDAO.generaReporteLineasRestringidas();
	}

	public boolean validaLineaAsignacion(String cuenta, String telefono,
			int region) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.validaLineaAsignacion(cuenta, telefono, region);
	}

	public int obtienePuntosMaxAsignar(int idPerfilN) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.obtienePuntosMaxAsignar(idPerfilN);
	}

	public ArrayList<PerfilTO> obtienePerfilesValidos() throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.obtienePerfilesValidos();
	}

	public ArrayList<PerfilTO> obtienePerfilesAsignacion(int idPerfilN,
			int numPtosMaximo) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO
				.obtienePerfilesAsignacion(idPerfilN, numPtosMaximo);
	}

	public boolean actualizaPrivilegiosAsignacion(int idPerfilN,
			int numMaxPtos, String numEmpleado) throws CAException {
		return this.seguridadDAO.actualizaPrivilegiosAsignacion(idPerfilN,
				numMaxPtos, numEmpleado);
	}

	public boolean actualizaPerfilAsignacion(int idPerfilN, int numMaxPtos,
			String numEmpleado) throws CAException {
		return this.seguridadDAO.actualizaPerfilAsignacion(idPerfilN,
				numMaxPtos, numEmpleado);
	}

	public boolean eliminaPerfilAsignacion(int idPerfilN) throws CAException {
		return this.seguridadDAO.eliminaPerfilAsignacion(idPerfilN);
	}

	public ArrayList<MotivoTO> obtieneMotivosAsignacion(String idMotivo,
			String descripcion, int estatus) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.obtieneMotivosAsignacion(idMotivo, descripcion,
				estatus);
	}

	public boolean insertaMotivoAsignacion(String idMotivo, String descripcion,
			int estatus) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.insertaMotivoAsignacion(idMotivo, descripcion,
				estatus);
	}

	public boolean actualizaMotivoAsignacion(String idMotivo,
			String descripcion, int estatus) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.actualizaMotivoAsignacion(idMotivo, descripcion,
				estatus);
	}

	public boolean eliminaMotivoAsignacion(String idMotivo) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.eliminaMotivoAsignacion(idMotivo);
	}

	public List<TelefonoTO> getLineasPrueba() throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.getLineasPrueba();
	}

	public MensajeTO insertaLineaPrueba(TelefonoTO telefonoTO,
			String numeroEmpleado) throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.insertaLineaPrueba(telefonoTO, numeroEmpleado);
	}

	public MensajeTO eliminaLineaPrueba(TelefonoTO telefonoTO)
			throws CAException {
		AsignacionDAO asignacionDAO = new AsignacionDAO();
		return asignacionDAO.eliminaLineaPrueba(telefonoTO);
	}

	public String getPlanesInbursa() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getPlanesInbursa();
	}

	public String getMarcasInbursa() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getMarcasInbursa();
	}

	public String getModelosInbursa() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getModelosInbursa();
	}

	public boolean validaBonoInbursa(String cuenta) throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.validaBonoInbursa(cuenta);
	}

	public String getLineasInbursa() throws CAException {
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		return catalogoDAO.getLineasInbursa();
	}

	public ArrayList<InbursaTO> getMovimientosInbursa(String telefono)
			throws CAException {
		ConsultaInbursaDAO consultaInbursaDAO = new ConsultaInbursaDAO();
		return consultaInbursaDAO.getMovimientosInbursa(telefono);
	}

	public List<InbursaTO> getInbursaRentas(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
		reportesCirculoAzulDAO.getInbursaRentas(fechaInicial, fechaFinal,
				idRegion);
		return reportesCirculoAzulDAO.getInbursaRentas(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<InbursaTO> getInbursaMinutos(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
		reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial, fechaFinal,
				idRegion);
		return reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<InbursaTO> getInbursaDescuento1000(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
		reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial, fechaFinal,
				idRegion);
		return reportesCirculoAzulDAO.getInbursaDescuento1000(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<InbursaTO> getInbursaPaquetes(String fechaInicial,
			String fechaFinal, int idRegion) throws CAException {
		ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
		reportesCirculoAzulDAO.getInbursaPaquetes(fechaInicial, fechaFinal,
				idRegion);
		return reportesCirculoAzulDAO.getInbursaPaquetes(fechaInicial,
				fechaFinal, idRegion);
	}

	public List<MovimientoCertificadoTO> consultaMovimientosTajetaCertificado(
			String numeroCertificado) throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO
				.consultaMovimientosTajetaCertificado(numeroCertificado);
	}

	/**
	 * @see Metodo de consultaSaldo
	 * @param numeroCertificado
	 * @return
	 * @throws ClaroException
	 */
	public TarjetaCertificadoTO consultaSaldoTarjetaCertificado(
			String numeroCertificado) throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO
				.consultaSaldoTarjetaCertificado(numeroCertificado);
	}

	/**
	 * @see Metodo para activaTarjetaCertificado
	 * @param numeroTarjeta
	 * @param montoCertificado
	 * @param idUsuario
	 * @return
	 * @throws ClaroException
	 */
	public ActivacionTarjetaCertificadoTO activaTarjetaCertificado(
			String numeroTarjeta, long montoCertificado, String idUsuario)
			throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.activaTarjetaCertificado(numeroTarjeta,
				montoCertificado, idUsuario);
	}

	/**
	 * @see Metodo para aplicaCertificado
	 * @param movimientoCertificadoTO
	 * @return
	 * @throws ClaroException
	 */
	public MensajeServiceTO aplicaCertificado(
			MovimientoCertificadoTO movimientoCertificadoTO)
			throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.aplicaCertificado(movimientoCertificadoTO);
	}

	/**
	 * @see Metodo para CANCELA CERTIFICADO
	 * @param numeroCertificado
	 * @param idUsuario
	 * @return
	 * @throws ClaroException
	 */
	public MensajeServiceTO cancelaTarjetaCertificado(String numeroCertificado,
			String idUsuario) throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.cancelaTarjetaCertificado(numeroCertificado,
				idUsuario);
	}

	/**
	 * @see Metodo para
	 * @param folio
	 * @param idUsuario
	 * @param idpuntoVta
	 * @param referencia
	 * @return
	 * @throws ClaroException
	 */
	public MensajeServiceTO cancelaAplicaCertificado(String folio,
			String idUsuario, String idpuntoVta, String referencia)
			throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.cancelaAplicaCertificado(folio, idUsuario,
				idpuntoVta, referencia);
	}

	public boolean consultaTajeta(String numeroTarjeta, long montoCertificado)
			throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.consultaTajeta(numeroTarjeta, montoCertificado);
	}

	public TarjetaCertificadoTO consultaSaldoCertificado(String numeroTarjeta)
			throws ClaroException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.consultaSaldoCertificado(numeroTarjeta);
	}

	public List<MovimientoCertificadoTO> consultaMovimientosCertificado(
			String numeroTarjeta) throws ClaroException, RemoteException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.consultaMovimientosCertificado(numeroTarjeta);
	}

	public String consultaTajetaVendida(String numeroTarjeta,
			long montoCertificado) throws ClaroException, RemoteException {
		CertificadosDAO certificadosDAO = new CertificadosDAO();
		return certificadosDAO.consultaTajetaVendida(numeroTarjeta,
				montoCertificado);
	}
}
