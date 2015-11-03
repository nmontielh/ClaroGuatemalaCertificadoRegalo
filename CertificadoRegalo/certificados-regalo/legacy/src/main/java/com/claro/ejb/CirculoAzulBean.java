/*      */ package com.claro.ejb;
/*      */ 
/*      */ import com.claro.catalogo.Catalogo;
/*      */ import com.claro.certificados.dao.CertificadosDAO;
/*      */ import com.claro.dao.AdministracionDAO;
/*      */ import com.claro.dao.AlianzasDAO;
/*      */ import com.claro.dao.AsignacionDAO;
/*      */ import com.claro.dao.AsignacionPorErrorAplicaDAO;
/*      */ import com.claro.dao.AsignacionporErrorDAO;
/*      */ import com.claro.dao.AvisosDAO;
/*      */ import com.claro.dao.CatalogoDAO;
/*      */ import com.claro.dao.ConsultaM2KDAO;
/*      */ import com.claro.dao.ConsultasDAO;
/*      */ import com.claro.dao.ImpresionDAO;
/*      */ import com.claro.dao.LineasRestringidasDAO;
/*      */ import com.claro.dao.MembresiaDAO;
/*      */ import com.claro.dao.PuntitosDAO;
/*      */ import com.claro.dao.PuntosDAO;
/*      */ import com.claro.dao.RedencionDAO;
/*      */ import com.claro.dao.RenunciaDAO;
/*      */ import com.claro.dao.RetencionDAO;
/*      */ import com.claro.dao.SeguridadDAO;
/*      */ import com.claro.dao.SimCardDAO;
/*      */ import com.claro.dao.TransferenciaAnexoDAO;
/*      */ import com.claro.dao.TransferenciaCaregDAO;
/*      */ import com.claro.dao.TransferenciaCteExcelenteDAO;
/*      */ import com.claro.dao.UsuarioDAO;
/*      */ import com.claro.exception.CAException;
/*      */ import com.claro.exception.ClaroException;
/*      */ import com.claro.inbursa.dao.ConsultaInbursaDAO;
/*      */ import com.claro.promociones.dao.PromocionesDAO;
/*      */ import com.claro.redencion.dao.RedencionMulticotizadorDAO;
/*      */ import com.claro.redencion.sms.NotificaSMS;
/*      */ import com.claro.reportes.dao.ReportesCirculoAzulDAO;
/*      */ import com.claro.transfer.AlianzasTO;
/*      */ import com.claro.transfer.AsignaPorErrorTO;
/*      */ import com.claro.transfer.AvisosTO;
/*      */ import com.claro.transfer.BeneficioTO;
/*      */ import com.claro.transfer.CatalogoTO;
/*      */ import com.claro.transfer.CiudadEdoTO;
/*      */ import com.claro.transfer.CteExcelenteTO;
/*      */ import com.claro.transfer.CuentaPadreTO;
/*      */ import com.claro.transfer.FactorTO;
/*      */ import com.claro.transfer.FolioLiberacionTO;
/*      */ import com.claro.transfer.FolioSAPTO;
/*      */ import com.claro.transfer.FuerzaVentasTO;
/*      */ import com.claro.transfer.ImpresionTO;
/*      */ import com.claro.transfer.MensajeTO;
/*      */ import com.claro.transfer.MobileTO;
/*      */ import com.claro.transfer.MotivoTO;
/*      */ import com.claro.transfer.MovimientoTO;
/*      */ import com.claro.transfer.ParametrosTO;
/*      */ import com.claro.transfer.PerfilTO;
/*      */ import com.claro.transfer.PlanTO;
/*      */ import com.claro.transfer.PrivilegioTO;
/*      */ import com.claro.transfer.ProductosSmsTO;
/*      */ import com.claro.transfer.ProductosTO;
/*      */ import com.claro.transfer.PromoBeneficiosTO;
/*      */ import com.claro.transfer.PromocionTO;
/*      */ import com.claro.transfer.PuntoVentaTO;
/*      */ import com.claro.transfer.PuntosTO;
/*      */ import com.claro.transfer.RedencionTO;
/*      */ import com.claro.transfer.ReservacionTO;
/*      */ import com.claro.transfer.RetencionTO;
/*      */ import com.claro.transfer.TelefonoTO;
/*      */ import com.claro.transfer.UsuarioTO;
/*      */ import com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO;
/*      */ import com.claro.transfer.certificados.MensajeServiceTO;
/*      */ import com.claro.transfer.certificados.MovimientoCertificadoTO;
/*      */ import com.claro.transfer.certificados.TarjetaCertificadoTO;
/*      */ import com.claro.transfer.inbursa.InbursaTO;
/*      */ import com.claro.transfer.membresia.MembresiaTO;
/*      */ import com.claro.transfer.promociones.GrupoTO;
/*      */ import com.claro.transfer.reportes.AcumuladosTO;
/*      */ import com.claro.transfer.reportes.AltoValorTO;
/*      */ import com.claro.transfer.reportes.CertificadoLealtadTO;
/*      */ import com.claro.transfer.reportes.ComisionTO;
/*      */ import com.claro.transfer.reportes.PuntosVencerTO;
/*      */ import com.claro.transfer.reportes.RedencionDetalleTO;
/*      */ import com.claro.transfer.reportes.RedencionLineaTO;
/*      */ import com.claro.transfer.reportes.RedencionReporteTO;
/*      */ import com.claro.transfer.reportes.RedencionesOnlineTO;
/*      */ import com.claro.transfer.reportes.Reportable;
/*      */ import com.claro.transfer.reportes.RetencionReporteTO;
/*      */ import com.claro.transfer.reportes.RoextTO;
/*      */ import com.claro.transfer.service.DocumentoTO;
/*      */ import com.claro.transfer.service.FileDataTO;
/*      */ import com.claro.transfer.service.ReservacionServiceTO;
/*      */ import com.claro.transfer.service.TelefonoServiceTO;
/*      */ import com.claro.transfer.transpuntos.TransferenciaTO;
/*      */ import java.io.InputStream;
/*      */ import java.rmi.RemoteException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.ejb.CreateException;
/*      */ import javax.ejb.EJBException;
/*      */ import javax.ejb.SessionBean;
/*      */ import javax.ejb.SessionContext;
/*      */ 
/*      */ public class CirculoAzulBean implements SessionBean
/*      */ {
/*      */   static final long serialVersionUID = 3206093459760846163L;
/*      */   private SessionContext mySessionCtx;
/*      */   private UsuarioDAO usuarioDAO;
/*      */   private ConsultasDAO consultasDAO;
/*      */   private AlianzasDAO alianzasDAO;
/*      */   private CatalogoDAO catalogoDAO;
/*      */   private ConsultaM2KDAO consultaM2KDAO;
/*      */   private SimCardDAO simCardDAO;
/*      */   private SeguridadDAO seguridadDAO;
/*      */   private ReportesCirculoAzulDAO reportesCirculoAzulDAO;
/*      */   private LineasRestringidasDAO lineasRestringidasDAO;
/*      */   
/*      */   public String foo(String param)
/*      */   {
/*  120 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public SessionContext getSessionContext()
/*      */   {
/*  126 */     return this.mySessionCtx;
/*      */   }
/*      */   
/*      */   public void setSessionContext(SessionContext ctx)
/*      */     throws EJBException, RemoteException
/*      */   {
/*  132 */     this.mySessionCtx = ctx;
/*  133 */     this.usuarioDAO = new UsuarioDAO();
/*  134 */     this.consultasDAO = new ConsultasDAO();
/*  135 */     this.alianzasDAO = new AlianzasDAO();
/*  136 */     this.catalogoDAO = new CatalogoDAO();
/*  137 */     this.simCardDAO = new SimCardDAO();
/*  138 */     this.consultaM2KDAO = new ConsultaM2KDAO();
/*  139 */     this.seguridadDAO = new SeguridadDAO();
/*  140 */     this.reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
/*  141 */     this.lineasRestringidasDAO = new LineasRestringidasDAO();
/*      */   }
/*      */   
/*      */ 
/*      */   public void ejbCreate()
/*      */     throws CreateException
/*      */   {}
/*      */   
/*      */ 
/*      */   public void ejbActivate()
/*      */     throws EJBException, RemoteException
/*      */   {}
/*      */   
/*      */ 
/*      */   public void ejbPassivate()
/*      */     throws EJBException, RemoteException
/*      */   {}
/*      */   
/*      */   public void ejbRemove()
/*      */     throws EJBException, RemoteException
/*      */   {
/*  162 */     this.usuarioDAO = null;
/*  163 */     this.consultasDAO = null;
/*  164 */     this.alianzasDAO = null;
/*  165 */     this.catalogoDAO = null;
/*  166 */     this.simCardDAO = null;
/*  167 */     this.consultaM2KDAO = null;
/*  168 */     this.seguridadDAO = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public UsuarioTO consultaEmpleado(String numEmpleado, String sIP, String passActual, boolean validaPwd)
/*      */     throws CAException
/*      */   {
/*  181 */     return this.usuarioDAO.consultaEmpleado(numEmpleado, sIP, passActual, validaPwd);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public UsuarioTO validaUserPerfil(String lClave, String sIP)
/*      */     throws CAException
/*      */   {
/*  191 */     return this.usuarioDAO.validaUserPerfil(lClave, sIP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public PerfilTO getPrivilegiosCirculoAzulEcac(int idPerfiln)
/*      */     throws CAException
/*      */   {
/*  200 */     return this.usuarioDAO.getPrivilegiosCirculoAzulEcac(idPerfiln);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public UsuarioTO consultaUsuario(UsuarioTO usuarioTO)
/*      */     throws Exception
/*      */   {
/*  210 */     return this.usuarioDAO.consultaUsuario(usuarioTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean agregarUsuario(UsuarioTO usuarioTO)
/*      */     throws Exception
/*      */   {
/*  220 */     return this.usuarioDAO.agregaUsuario(usuarioTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean actualizaUsuario(UsuarioTO usuarioCA, UsuarioTO usuarioActualizado, int perfilAnterior)
/*      */     throws Exception
/*      */   {
/*  234 */     return this.usuarioDAO.actualizaUsuario(usuarioCA, usuarioActualizado, perfilAnterior);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean eliminaUsuario(UsuarioTO usuarioTO)
/*      */     throws Exception
/*      */   {
/*  244 */     return this.usuarioDAO.eliminaUsuario(usuarioTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void borraArchivo(DocumentoTO documentoTO)
/*      */     throws Exception
/*      */   {
/*  253 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  254 */     promocionesDAO.borraArchivo(documentoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void guardaArchivo(DocumentoTO documentoTO, FileDataTO fileDataTO)
/*      */     throws Exception
/*      */   {
/*  263 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  264 */     promocionesDAO.guardaArchivo(documentoTO, fileDataTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int insertaPromocion(DocumentoTO documentoTO)
/*      */     throws Exception
/*      */   {
/*  273 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  274 */     return promocionesDAO.insertaPromocion(documentoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void borrarPromocion(DocumentoTO documentoTO)
/*      */     throws Exception
/*      */   {
/*  283 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  284 */     promocionesDAO.borrarPromocion(documentoTO.getIdDocumento());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int obtenIDDocumento()
/*      */     throws Exception
/*      */   {
/*  292 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  293 */     return promocionesDAO.obtenIDDocumento();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<DocumentoTO> obtenPromociones(String estatus)
/*      */     throws Exception
/*      */   {
/*  302 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  303 */     return promocionesDAO.obtenPromociones(estatus);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<PromocionTO> consultaPromociones(PromocionTO promocionTO)
/*      */     throws Exception
/*      */   {
/*  312 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  313 */     return promocionesDAO.consultaPromociones(promocionTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<PlanTO> consultaPlanes(PlanTO planTO)
/*      */     throws Exception
/*      */   {
/*  322 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  323 */     return promocionesDAO.consultaPlanes(planTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<FuerzaVentasTO> consultaFuerzaVentas(FuerzaVentasTO fuerzaVentasTO)
/*      */     throws Exception
/*      */   {
/*  332 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  333 */     return promocionesDAO.consultaFuerzaVentas(fuerzaVentasTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<GrupoTO> consultaGpoPromo(GrupoTO grupoTO)
/*      */     throws Exception
/*      */   {
/*  342 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  343 */     return promocionesDAO.consultaGpoPromo(grupoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] generaReporte(InputStream archivo, HashMap<String, String> mapa)
/*      */     throws Exception
/*      */   {
/*  353 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  354 */     return promocionesDAO.generaReporte(archivo, mapa);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] generaReporteCSV(InputStream archivo, HashMap<String, String> mapa)
/*      */     throws Exception
/*      */   {
/*  364 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/*  365 */     return promocionesDAO.generaReporteCSV(archivo, mapa);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<RedencionTO> detalleRedencion(String cuenta, int secuencia, String fecha)
/*      */     throws CAException
/*      */   {
/*  377 */     return this.consultasDAO.detalleRedencion(cuenta, secuencia, fecha);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<MovimientoTO> detalleMovimientos(String cuenta, String secuencia)
/*      */     throws CAException
/*      */   {
/*  387 */     return this.consultasDAO.detalleMovimientos(cuenta, secuencia);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<AlianzasTO> consultaCanjesAlianzas(String cuenta, String secuencia)
/*      */     throws CAException
/*      */   {
/*  397 */     return this.alianzasDAO.consultaCanjesAlianzas(cuenta, secuencia);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public TelefonoTO procedimientoGeneral(ParametrosTO parametrosTO, PerfilTO perfilTO, String fzaVentas)
/*      */     throws CAException
/*      */   {
/*  406 */     return this.consultasDAO.procedimientoGeneral(parametrosTO, perfilTO, fzaVentas);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FactorTO consultaFactor(int idAlianza, int nPtsDisp)
/*      */     throws CAException
/*      */   {
/*  417 */     return this.alianzasDAO.consultaFactor(idAlianza, nPtsDisp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<CatalogoTO> getMarcas(int region, int grupo)
/*      */     throws CAException
/*      */   {
/*  427 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  428 */     return catalogoDAO.getMarcas(region, grupo);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<CatalogoTO> getModelos(String marca, int region, int grupo, String fzaVentas)
/*      */     throws CAException
/*      */   {
/*  439 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  440 */     return catalogoDAO.getModelos(marca, region, grupo, fzaVentas);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<CuentaPadreTO> consultaPadre(String cuenta)
/*      */     throws CAException
/*      */   {
/*  449 */     return this.consultasDAO.consultaPadre(cuenta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String consultaPrecioEquipo(String plan, int region, String marca, String modelo, String estatus)
/*      */     throws CAException
/*      */   {
/*  461 */     return this.consultasDAO.consultaPrecioEquipo(plan, region, marca, modelo, estatus);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int consultaRegion(String lTelefono)
/*      */     throws CAException
/*      */   {
/*  470 */     return this.consultasDAO.consultaRegion(lTelefono);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RedencionTO aplicaredencion(ProductosTO productosTO, ParametrosTO parametrosTO, RedencionTO redencionTO, boolean isEcac, boolean isDistribuidores, String endPoint, String usuario, BeneficioTO beneficioTO, String endpointGap)
/*      */     throws CAException
/*      */   {
/*  486 */     RedencionDAO redencionDAO = new RedencionDAO();
/*  487 */     return redencionDAO.aplicaredencion(productosTO, parametrosTO, redencionTO, isEcac, isDistribuidores, endPoint, usuario, beneficioTO, endpointGap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public TelefonoTO procedimientoGeneralPuntitos(ParametrosTO parametrosTO)
/*      */     throws CAException
/*      */   {
/*  496 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  497 */     return puntitosDAO.procedimientoGeneral(parametrosTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<TelefonoTO> consultaLinea(TelefonoTO telefonoTO)
/*      */     throws Exception
/*      */   {
/*  505 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  506 */     return puntitosDAO.consultaLinea(telefonoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<TelefonoTO> consultaReserva(TelefonoTO telefonoTO)
/*      */     throws Exception
/*      */   {
/*  514 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  515 */     return puntitosDAO.consultaReserva(telefonoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean cancelaReserva(TelefonoTO telefonoTO)
/*      */     throws Exception
/*      */   {
/*  523 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  524 */     return puntitosDAO.cancelaReserva(telefonoTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<PuntoVentaTO> consultaPtosVta(PuntoVentaTO puntoVentaTO)
/*      */     throws Exception
/*      */   {
/*  532 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  533 */     return puntitosDAO.consultaPtosVta(puntoVentaTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean agregaPtoVta(PuntoVentaTO puntoVentaTO)
/*      */     throws Exception
/*      */   {
/*  541 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  542 */     return puntitosDAO.agregaPtoVta(puntoVentaTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean actualizaPtoVta(PuntoVentaTO puntoVentaTO)
/*      */     throws Exception
/*      */   {
/*  550 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  551 */     return puntitosDAO.actualizaPtoVta(puntoVentaTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean eliminaPtoVta(PuntoVentaTO puntoVentaTO)
/*      */     throws Exception
/*      */   {
/*  559 */     PuntitosDAO puntitosDAO = new PuntitosDAO();
/*  560 */     return puntitosDAO.eliminaPtoVta(puntoVentaTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<CatalogoTO> obtienePropiedades()
/*      */     throws CAException
/*      */   {
/*  568 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  569 */     return catalogoDAO.obtienePropiedades();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<CatalogoTO> obtienePerfil()
/*      */     throws CAException
/*      */   {
/*  577 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  578 */     return catalogoDAO.obtienePerfil();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<CatalogoTO> obtieneGrupoPromocion()
/*      */     throws CAException
/*      */   {
/*  586 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  587 */     return catalogoDAO.obtieneGrupoPromocion();
/*      */   }
/*      */   
/*      */   public Map<String, PrivilegioTO> getprivilegiosCa() throws CAException {
/*  591 */     SeguridadDAO seguridadDAO = new SeguridadDAO();
/*  592 */     return seguridadDAO.getprivilegiosCa();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ProductosTO> listaPromociones(int iRegion, int iRangoPuntos, String sPlan, int iGrupoPromo, String cacsFzaVenta, String todosFzaVenta, String marca, String modelo)
/*      */     throws CAException
/*      */   {
/*  610 */     return this.catalogoDAO.listaPromociones(iRegion, iRangoPuntos, sPlan, iGrupoPromo, 
/*  611 */       cacsFzaVenta, todosFzaVenta, marca, modelo);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ProductosTO> consultaProductos(String ptsExactos, String marca, String modelo, TelefonoTO telefonoTO, UsuarioTO usuarioTO, String tipoRed, String formaRed, String planNuevo, double costoPuntos, String cacsFzaVenta, String todosFzaVenta, String fzaVentasDistribuidores, int mesAdendum, int diaAdendum, int diasMesAdendum, int adendumNuevo, String endpointGap)
/*      */     throws CAException
/*      */   {
/*  638 */     return this.catalogoDAO.consultaProductos(ptsExactos, marca, modelo, telefonoTO, usuarioTO, tipoRed, formaRed, 
/*  639 */       planNuevo, costoPuntos, cacsFzaVenta, todosFzaVenta, fzaVentasDistribuidores, mesAdendum, diaAdendum, diasMesAdendum, 
/*  640 */       adendumNuevo, endpointGap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<AlianzasTO> listaCertLealtad(String sCuenta, int iSecuencia)
/*      */     throws CAException
/*      */   {
/*  650 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  651 */     return catalogoDAO.listaCertLealtad(sCuenta, iSecuencia);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RetencionTO calculaValorCupon(Double dARPU, int iMeses, int iBajas, int iPcAutor)
/*      */     throws CAException
/*      */   {
/*  663 */     RetencionDAO retencionDAO = new RetencionDAO();
/*  664 */     return retencionDAO.calculaValorCupon(dARPU, iMeses, iBajas, iPcAutor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO actualizaPassword(String _usuario, String _passActual, String _passNuevo, String _passConfirm)
/*      */     throws CAException
/*      */   {
/*  674 */     AdministracionDAO adminDAO = new AdministracionDAO();
/*  675 */     return adminDAO.actualizarPassword(_usuario, _passActual, _passNuevo, _passConfirm);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PlanTO datosPlan(String sPlan, int region)
/*      */     throws CAException
/*      */   {
/*  685 */     TelefonoTO telefonoTO = new TelefonoTO();
/*  686 */     this.consultasDAO.datosPlan(sPlan, region, null, telefonoTO);
/*  687 */     PlanTO planTO = new PlanTO();
/*  688 */     planTO.setIdGrupoPromocion(telefonoTO.getPlanTO().getIdGrupoPromocion());
/*  689 */     planTO.setTipoPromocion(telefonoTO.getPlanTO().getTipoPromocion());
/*  690 */     planTO.setAdendum(telefonoTO.getPlanTO().getAdendumNvo());
/*  691 */     planTO.setBanRedencionAnct(telefonoTO.getPlanTO().getBanRedencionAnct());
/*  692 */     planTO.setSegmento(telefonoTO.getPlanTO().getSegmento());
/*  693 */     planTO.setIdMensaje(telefonoTO.getIdMensaje());
/*  694 */     planTO.setMensaje(telefonoTO.getMensaje());
/*  695 */     return planTO;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO actualizaFolioSAP(FolioSAPTO folioSAPTO)
/*      */     throws CAException
/*      */   {
/*  704 */     PuntosDAO puntosDAO = new PuntosDAO();
/*  705 */     return puntosDAO.actualizaFolioSAP(folioSAPTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<RedencionTO> consultaFolioSAP(String folio, String tipoRedencion, String estatus, int secuencia, String cuenta, int region, boolean consultaM2K)
/*      */     throws CAException
/*      */   {
/*  720 */     return this.consultasDAO.consultaFolioSAP(folio, tipoRedencion, estatus, secuencia, cuenta, region, consultaM2K);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO datosConstanciaImpresion(String sTelefono, String cuenta, String fechaFolio, String folio, String fechaOperacion, String tipoReden)
/*      */     throws CAException
/*      */   {
/*  733 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  734 */     return impresionDAO.datosConstanciaImpresion(sTelefono, cuenta, fechaFolio, folio, fechaOperacion, tipoReden);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO datosConstanciaBonoInbursa(String sTelefono, String cuenta)
/*      */     throws CAException
/*      */   {
/*  745 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  746 */     return impresionDAO.datosConstanciaBonoInbursa(sTelefono, cuenta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ReservacionTO realizaApartado(ReservacionTO reservacionTO, TelefonoTO telefonoTO, boolean isDistribuidores, String fzaVentas)
/*      */     throws CAException
/*      */   {
/*  756 */     RedencionDAO redencionDAO = new RedencionDAO();
/*  757 */     return redencionDAO.realizaApartado(reservacionTO, telefonoTO, isDistribuidores, fzaVentas);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ImpresionTO> detalleBonosInbursa(String sTelefono, String cuenta)
/*      */     throws CAException
/*      */   {
/*  766 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  767 */     return impresionDAO.detalleBonosInbursa(sTelefono, cuenta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ImpresionTO> detalleRedenImp(String sTelefono, String cuenta, String sFecha)
/*      */     throws CAException
/*      */   {
/*  776 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  777 */     return impresionDAO.detalleRedenImp(sTelefono, cuenta, sFecha);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO obtieneConstanciaAlianzas(String sFecha, String sCuenta, int iSecuencia, int tipoAlianza, String sFechaFolio, String sLinea)
/*      */     throws CAException
/*      */   {
/*  790 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  791 */     return impresionDAO.obtieneConstanciaAlianzas(sFecha, sCuenta, iSecuencia, tipoAlianza, sFechaFolio, sLinea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ImpresionTO> detalleImpresionAlianzas(String sFecha, String sCuenta, int iSecuencia, int tipoAlianza, String sFolio)
/*      */     throws CAException
/*      */   {
/*  803 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/*  804 */     return impresionDAO.detalleImpresionAlianzas(sFecha, sCuenta, iSecuencia, tipoAlianza, sFolio);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public PuntosTO obtienePuntos(String cuenta, int secuencia)
/*      */     throws CAException
/*      */   {
/*  813 */     return this.consultasDAO.obtienePuntos(cuenta, secuencia);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelaApartado(ParametrosTO parametrosTO, UsuarioTO usuarioTO, boolean isDistribuidores)
/*      */     throws CAException
/*      */   {
/*  821 */     RedencionDAO redencionDAO = new RedencionDAO();
/*  822 */     return redencionDAO.cancelaApartado(parametrosTO, usuarioTO, isDistribuidores);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO agregarMembresia(MembresiaTO membresiaTO)
/*      */     throws CAException
/*      */   {
/*  831 */     MembresiaDAO membresiaDAO = new MembresiaDAO();
/*  832 */     return membresiaDAO.agregarMembresia(membresiaTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO actualizaPromoAlianza(String sCuentaAl, int iCveAl, String sCuenta, int iSecuencia, String sNombre, String sApePat, String sApeMat, String sUsrCve, String sTel)
/*      */     throws CAException
/*      */   {
/*  849 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/*  850 */     return alianzasDAO.actualizaPromoAlianza(sCuentaAl, iCveAl, sCuenta, iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO altaPromoAlianza(String sCuentaAl, int iCveAl, String sCuenta, int iSecuencia, String sNombre, String sApePat, String sApeMat, String sUsrCve, String sTel)
/*      */     throws CAException
/*      */   {
/*  867 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/*  868 */     return alianzasDAO.altaPromoAlianza(sCuentaAl, iCveAl, sCuenta, iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MembresiaTO consultaMembresia(String cuenta, int secuencia, Date fecha)
/*      */     throws CAException
/*      */   {
/*  878 */     MembresiaDAO membresiaDAO = new MembresiaDAO();
/*  879 */     return membresiaDAO.consultaMembresia(cuenta, secuencia, fecha);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MensajeTO reactivaPuntos(ParametrosTO parametrosTO)
/*      */     throws CAException
/*      */   {
/*  887 */     RenunciaDAO renunciaDAO = new RenunciaDAO();
/*  888 */     return renunciaDAO.reactivaPuntos(parametrosTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO renunciaPuntos(ParametrosTO parametrosTO)
/*      */     throws CAException
/*      */   {
/*  897 */     RenunciaDAO renunciaDAO = new RenunciaDAO();
/*  898 */     return renunciaDAO.renunciaPuntos(parametrosTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO confirmaCanje(int lValViaje, FolioLiberacionTO folioLiberacion, int lNumAcomp, String lIdCanje, String sCuenta, int isecuencia, String sTelefono, String sUsuario, int iRegion)
/*      */     throws CAException
/*      */   {
/*  915 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/*  916 */     return alianzasDAO.confirmaCanje(lValViaje, folioLiberacion, lNumAcomp, lIdCanje, sCuenta, isecuencia, sTelefono, sUsuario, iRegion);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelaRedencion(ParametrosTO parametrosTO, String fechaOperacion, int diasValidacion, UsuarioTO usuarioTO)
/*      */     throws CAException
/*      */   {
/*  927 */     RedencionDAO redencionDAO = new RedencionDAO();
/*  928 */     return redencionDAO.cancelaRedencion(parametrosTO, fechaOperacion, diasValidacion, usuarioTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TransferenciaTO transfierePuntosAnexo(TransferenciaTO transfTO)
/*      */     throws CAException
/*      */   {
/*  940 */     TransferenciaAnexoDAO transfiereAnexoDAO = new TransferenciaAnexoDAO();
/*  941 */     return transfiereAnexoDAO.transferirPuntosAnexo(transfTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TransferenciaTO transfierePuntosRegion(TransferenciaTO transfTO)
/*      */     throws CAException
/*      */   {
/*  953 */     TransferenciaCaregDAO transfiereCaregDAO = new TransferenciaCaregDAO();
/*  954 */     return transfiereCaregDAO.transferirPuntosCareg(transfTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<AlianzasTO> consultaCancelaCanje(String telefono, String cuentaAlianza, int opcion)
/*      */     throws CAException
/*      */   {
/*  965 */     return this.consultasDAO.consultaCancelaCanje(telefono, cuentaAlianza, opcion);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO realizaCancelacion(ParametrosTO parametrosTO, int estatusTrans)
/*      */     throws CAException
/*      */   {
/*  975 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/*  976 */     return alianzasDAO.realizaCancelacion(parametrosTO, estatusTrans);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO realizaCanje(ParametrosTO parametrosTO, int millasPorTranferir, int valorPuntos, String descAlianza, String cuentaAlianza, int ptsTransferir, int valorCertificado, String puntoVenta, String numEmpleado, String dirIP, String idViaje, int valorPesos, String referencia, int opcion)
/*      */     throws CAException
/*      */   {
/*  999 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/* 1000 */     return alianzasDAO.realizaCanje(parametrosTO, millasPorTranferir, valorPuntos, descAlianza, cuentaAlianza, ptsTransferir, valorCertificado, puntoVenta, numEmpleado, dirIP, idViaje, valorPesos, referencia, opcion);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelaAlianza(String sCuenta, String sCuentaAl, int iCveAl)
/*      */     throws CAException
/*      */   {
/* 1011 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/* 1012 */     return alianzasDAO.cancelaAlianza(sCuenta, sCuentaAl, iCveAl);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<ImpresionTO> detalleEquipo(String sFecha, String sTipoRed, String sFechaFolio, String sCuenta, String sTelefono)
/*      */     throws CAException
/*      */   {
/* 1025 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/* 1026 */     return impresionDAO.detalleEquipo(sFecha, sTipoRed, sFechaFolio, sCuenta, sTelefono);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<CiudadEdoTO> getEstadoCiudad(String estado)
/*      */     throws CAException
/*      */   {
/* 1035 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1036 */     return catalogoDAO.getEstadoCiudad(estado);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ReservacionTO consultaFolioReservacion(String folio, String estatus)
/*      */     throws CAException
/*      */   {
/* 1047 */     return this.consultasDAO.obtieneReservacion(folio, estatus, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Hashtable<String, AlianzasTO> consultaInicialAlianzas(int iRegion, String sCuenta, int nPtsDisp, int iSecuencia)
/*      */     throws CAException
/*      */   {
/* 1059 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/* 1060 */     return alianzasDAO.consultaInicialAlianzas(iRegion, sCuenta, nPtsDisp, iSecuencia);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AlianzasTO consultaPromocionesAmex(int alianza, int iRegion, String linea, int iSecuencia, int ptosDisponibles)
/*      */     throws CAException
/*      */   {
/* 1072 */     AlianzasDAO alianzasDAO = new AlianzasDAO();
/* 1073 */     return alianzasDAO.consultaPromocionesAmex(alianza, iRegion, linea, iSecuencia, ptosDisponibles);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO procesaAsignacion(ParametrosTO parametrosTO, String usuario, int accion, int ptsAsignar, String comentario, String motivoAsig)
/*      */     throws CAException
/*      */   {
/* 1085 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1086 */     return asignacionDAO.procesaAsignacion(parametrosTO, usuario, accion, ptsAsignar, comentario, motivoAsig);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO consultaCertificado(String cuenta, String telefono)
/*      */     throws CAException
/*      */   {
/* 1096 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1097 */     return retencionDAO.consultaCertificado(cuenta, telefono);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RetencionTO consultaRetencion(String sCta, String sSec)
/*      */     throws CAException
/*      */   {
/* 1106 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1107 */     return retencionDAO.consultaRetencion(sCta, sSec);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO validaImpresion(String sFolio, Date dfechaM2K)
/*      */     throws CAException
/*      */   {
/* 1116 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1117 */     return retencionDAO.validaImpresion(sFolio, dfechaM2K);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelarCertificado(String sUsuario, String sModo, String sFolio, String sComenta)
/*      */     throws CAException
/*      */   {
/* 1129 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1130 */     return retencionDAO.cancelarCertificado(sUsuario, sModo, sFolio, sComenta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FolioLiberacionTO asignaPorAntiguedad(ParametrosTO parametrosTO, String lFecAltaM2K, String comentario, String usuario)
/*      */     throws CAException
/*      */   {
/* 1141 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1142 */     return asignacionDAO.asignaPorAntiguedad(parametrosTO, lFecAltaM2K, comentario, usuario);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void obtenFacturaciones(MobileTO mobileTO, int region, String cuenta, int numFacturas)
/*      */     throws CAException
/*      */   {
/* 1152 */     ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
/*      */     
/* 1154 */     consultaM2KDAO.obtenFacturaciones(mobileTO, region, cuenta, numFacturas);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MobileTO consultaPorCuenta(String lCta, int nReg)
/*      */     throws CAException
/*      */   {
/* 1164 */     ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
/* 1165 */     return consultaM2KDAO.consultaPorCuenta(lCta, nReg);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO asignacionPuntos(ParametrosTO parametrosTO, int ptsAsignar, String ip)
/*      */     throws CAException
/*      */   {
/* 1176 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/* 1177 */     return promocionesDAO.asignacionPuntos(parametrosTO, ptsAsignar, ip);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MobileTO consultaDatosM2K(ParametrosTO parametrosTO)
/*      */     throws CAException
/*      */   {
/* 1186 */     return this.consultaM2KDAO.consultaDatosM2K(parametrosTO);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO almacenaCertificado(TelefonoTO telefonoTO, UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses, String sMotivo, String sComenta, String sComenta2, String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo)
/*      */     throws CAException
/*      */   {
/* 1217 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1218 */     return retencionDAO.almacenaCertificado(telefonoTO, usuarioTO, retencionTO, sNMeses, sMotivo, sComenta, 
/* 1219 */       sComenta2, sCuentaAnt, sRegionAnt, sMesesAnt, sTipo);
/*      */   }
/*      */   
/*      */ 
/*      */   public RetencionTO getMotivos()
/*      */     throws CAException
/*      */   {
/* 1226 */     RetencionDAO retencionDAO = new RetencionDAO();
/* 1227 */     return retencionDAO.getMotivos();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPromoSIM(String estatus, String segmento, String tipoProd, int region)
/*      */     throws CAException
/*      */   {
/* 1239 */     return this.simCardDAO.getPromoSIM(estatus, segmento, tipoProd, region);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public TelefonoTO consultaDatosSIM(String telefono)
/*      */     throws CAException
/*      */   {
/* 1248 */     return this.simCardDAO.consultaDatosSIM(telefono);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO datosImpresion(String telefono, String fechaFolio)
/*      */     throws CAException
/*      */   {
/* 1257 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/* 1258 */     return impresionDAO.datosImpresion(telefono, fechaFolio);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO consultaCertificados(String lFecha, String cuenta, String sec)
/*      */     throws CAException
/*      */   {
/* 1268 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/* 1269 */     return impresionDAO.consultaCertificados(lFecha, cuenta, sec);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ImpresionTO obtieneCertificado(String folio, String cuenta, String sec)
/*      */     throws CAException
/*      */   {
/* 1279 */     ImpresionDAO impresionDAO = new ImpresionDAO();
/* 1280 */     return impresionDAO.obtieneCertificado(folio, cuenta, sec);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<PromoBeneficiosTO> obtieneBeneficios(int region, String marca)
/*      */     throws CAException
/*      */   {
/* 1289 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1290 */     return catalogoDAO.obtieneBeneficios(region, marca);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<MotivoTO> obtieneCatalogoRechazo(int tipoMotivo)
/*      */     throws CAException
/*      */   {
/* 1298 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1299 */     return catalogoDAO.obtieneCatalogoRechazo(tipoMotivo);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1307 */   public ArrayList<PlanTO> getPlanesTarifarios(int region)
/* 1307 */     throws CAException { return this.catalogoDAO.getPlanesTarifarios(region); }
/*      */   
/*      */   public ArrayList<AvisosTO> historicoAvisos(String sFechaIni, String sFechaFin, String tipoAviso, String estatus) throws CAException {
/* 1310 */     AvisosDAO avisosDAO = new AvisosDAO();
/* 1311 */     return avisosDAO.historicoAvisos(sFechaIni, sFechaFin, tipoAviso, estatus);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO insertarAvisos(String sDescripcion, String sFechaFin, String sTipoAviso, String sUsuario, String sFechaInicio)
/*      */     throws CAException
/*      */   {
/* 1323 */     AvisosDAO avisosDAO = new AvisosDAO();
/* 1324 */     return avisosDAO.insertarAvisos(sDescripcion, sFechaFin, sTipoAviso, sUsuario, sFechaInicio);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO eliminarAvisos(String sUsuarioMod, int idAviso)
/*      */     throws CAException
/*      */   {
/* 1333 */     AvisosDAO avisosDAO = new AvisosDAO();
/* 1334 */     return avisosDAO.eliminarAvisos(sUsuarioMod, idAviso);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<AvisosTO> marquesinaAvisos(long fecha)
/*      */     throws CAException
/*      */   {
/* 1342 */     AvisosDAO avisosDAO = new AvisosDAO();
/* 1343 */     return avisosDAO.marquesinaAvisos(fecha);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<UsuarioTO> consultaUsuarios(UsuarioTO user, int max)
/*      */     throws CAException
/*      */   {
/* 1353 */     UsuarioDAO usuarioDAO = new UsuarioDAO();
/* 1354 */     return usuarioDAO.consultaUsuarios(user, max);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PuntoVentaTO obtienePuntoVenta(String sIP)
/*      */     throws CAException
/*      */   {
/* 1364 */     return this.consultasDAO.obtienePuntoVenta(sIP, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PlanTO consultaPlan(String plan, int region)
/*      */     throws CAException
/*      */   {
/* 1372 */     return this.catalogoDAO.consultaPlan(plan, region);
/*      */   }
/*      */   
/*      */   public MensajeTO consultaTipoPlan(String idPlan, int nReg) throws CAException {
/* 1376 */     return this.consultaM2KDAO.consultaTipoPlan(idPlan, nReg);
/*      */   }
/*      */   
/* 1379 */   public boolean existeUsuario(UsuarioTO usuarioTO) throws Exception { return this.usuarioDAO.existeUsuario(usuarioTO); }
/*      */   
/*      */   public ProductosSmsTO promocionxSms(String claveProducto, int puntosDispo) throws CAException
/*      */   {
/* 1383 */     return this.catalogoDAO.promocionxSms(claveProducto, puntosDispo);
/*      */   }
/*      */   
/*      */   public PerfilTO getPrivilegiosPerfil(String idPerfil) throws CAException {
/* 1387 */     return this.seguridadDAO.getPrivilegiosPerfil(idPerfil);
/*      */   }
/*      */   
/*      */   public void actualizaPrivilegios(int idPerfil, List<PrivilegioTO> privilegios, String numEmpleado) throws CAException {
/* 1391 */     this.seguridadDAO.actualizaPrivilegios(idPerfil, privilegios, numEmpleado);
/*      */   }
/*      */   
/* 1394 */   public List<CertificadoLealtadTO> getCertificadosLealtad(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getCertificadosLealtad(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public List<RedencionReporteTO> getRedenciones(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1397 */     return this.reportesCirculoAzulDAO.getRedenciones(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1400 */   public List<RedencionDetalleTO> getRedencionesDetalle(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getRedencionesDetalle(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public List<RoextTO> getRoext(String fechaInicial, String fechaFinal, int idRegion) throws CAException
/*      */   {
/* 1404 */     return this.reportesCirculoAzulDAO.getRoext(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1407 */   public List<ComisionTO> getComisiones(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getComisiones(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public List<RetencionReporteTO> getRetenciones(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1410 */     return this.reportesCirculoAzulDAO.getRetenciones(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1413 */   public Map<String, List<Reportable>> getAlianzas(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getAlianzas(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public List<AcumuladosTO> getAcumulados(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1416 */     return this.reportesCirculoAzulDAO.getAcumulados(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1419 */   public List<AltoValorTO> getAltoValor(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getAltoValor(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public List<PuntosVencerTO> getPuntosPorVencer(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1422 */     return this.reportesCirculoAzulDAO.getPuntosPorVencer(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1425 */   public List<RedencionLineaTO> getRedencionesLinea(String fechaInicial, String fechaFinal, List<String> lineas) throws CAException { return this.reportesCirculoAzulDAO.getRedencionesLinea(fechaInicial, fechaFinal, lineas); }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<PlanTO> planesRedencionByRegion(int region)
/*      */     throws CAException
/*      */   {
/* 1436 */     return this.consultasDAO.planesRedencionByRegion(region);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayList<PromocionTO> consultaPromocionesDistribuidores(PromocionTO promocionTO, String idPlan, int idGpoPromocion)
/*      */     throws Exception
/*      */   {
/* 1446 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/* 1447 */     return promocionesDAO.consultaPromocionesDistribuidores(promocionTO, idPlan, idGpoPromocion);
/*      */   }
/*      */   
/*      */   public ArrayList<CatalogoTO> getMarcasDistribuidores(int region, String idPlan, String fzaVentas) throws CAException
/*      */   {
/* 1452 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1453 */     return catalogoDAO.getMarcasXPlan(region, idPlan, fzaVentas);
/*      */   }
/*      */   
/*      */   public ArrayList<ReservacionServiceTO> consultaFoliosDistribuidores(String telefono, String cuenta, String fzaVenta)
/*      */     throws CAException
/*      */   {
/* 1459 */     return this.consultasDAO.consultaFolioDistribuidores(telefono, cuenta, fzaVenta);
/*      */   }
/*      */   
/*      */   public int consultaRegionLinea(String lTelefono, String cuenta)
/*      */     throws CAException
/*      */   {
/* 1465 */     return this.consultasDAO.consultaRegionLinea(lTelefono, cuenta);
/*      */   }
/*      */   
/* 1468 */   public ArrayList<ProductosSmsTO> productosonline() throws CAException { return this.catalogoDAO.listaproductosonline(); }
/*      */   
/*      */   public int valorPuntosOnline(String claveProducto) throws CAException {
/* 1471 */     return this.catalogoDAO.valorPuntosOnline(claveProducto);
/*      */   }
/*      */   
/*      */   public ArrayList<ProductosSmsTO> consultaPromocionesSms(ProductosSmsTO productosSmsTO) throws Exception
/*      */   {
/* 1476 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/* 1477 */     return promocionesDAO.consultaPromocionesSms(productosSmsTO);
/*      */   }
/*      */   
/* 1480 */   public List<RedencionesOnlineTO> getRedencionesOnline(String fechaInicial, String fechaFinal, int idRegion) throws CAException { return this.reportesCirculoAzulDAO.getRedencionesOnline(fechaInicial, fechaFinal, idRegion); }
/*      */   
/*      */   public ArrayList<CatalogoTO> consultaAreasPromocion()
/*      */     throws CAException
/*      */   {
/* 1485 */     return this.consultasDAO.consultaAreasPromocion();
/*      */   }
/*      */   
/*      */   public boolean aplicaRedencionMulticotizador(String idVariable) throws Exception
/*      */   {
/* 1490 */     RedencionMulticotizadorDAO redencionMultiDAO = new RedencionMulticotizadorDAO();
/* 1491 */     return Boolean.parseBoolean(redencionMultiDAO.obtienePropiedad(idVariable));
/*      */   }
/*      */   
/*      */   public ArrayList<PlanTO> planesSisact(int region, String tecnologia, String modalidad, String mixto, String banSisact, String tipoPlan, String fzaVenta) throws CAException
/*      */   {
/* 1496 */     return this.consultasDAO.planesSisact(region, tecnologia, modalidad, mixto, banSisact, tipoPlan, fzaVenta);
/*      */   }
/*      */   
/*      */   public boolean aplicaRedencionSisact(String folio, TelefonoServiceTO telefonoTO, String claveSisact) throws CAException
/*      */   {
/* 1501 */     RedencionDAO redencionDAO = new RedencionDAO();
/* 1502 */     return redencionDAO.aplicaRedencionSisact(folio, telefonoTO, claveSisact);
/*      */   }
/*      */   
/*      */   public TransferenciaTO transfierePuntosExcelente(TransferenciaTO transfTO) throws CAException {
/* 1506 */     TransferenciaCteExcelenteDAO transfiereExcelenteDAO = new TransferenciaCteExcelenteDAO();
/* 1507 */     return transfiereExcelenteDAO.transferirPuntosCteExc(transfTO);
/*      */   }
/*      */   
/*      */ 
/* 1511 */   public void fechaAcceso(UsuarioTO usuarioTO) throws Exception { this.usuarioDAO.fechaAcceso(usuarioTO); }
/*      */   
/*      */   public Hashtable<String, Object> validaProductoDescuento(String telefono, int region, String cuenta, String endPoint, String usuario) throws CAException {
/* 1514 */     RedencionDAO redencionDAO = new RedencionDAO();
/* 1515 */     return redencionDAO.validaProductoDescuento(telefono, region, cuenta, endPoint, usuario);
/*      */   }
/*      */   
/*      */   public ArrayList<CteExcelenteTO> consultaLineasCteExc(CteExcelenteTO cteExcelenteTO) throws Exception {
/* 1519 */     PromocionesDAO promocionesDAO = new PromocionesDAO();
/* 1520 */     return promocionesDAO.consultaClientExcelente(cteExcelenteTO);
/*      */   }
/*      */   
/*      */   public AsignaPorErrorTO VerificaEPBAJ(String cuenta, String linea) throws CAException {
/* 1524 */     AsignacionporErrorDAO asignacionporErrorDAO = new AsignacionporErrorDAO();
/* 1525 */     return asignacionporErrorDAO.VerificaEPBAJ(cuenta, linea);
/*      */   }
/*      */   
/* 1528 */   public void AsignacionPorErrorAplic(AsignaPorErrorTO asignaPorErrorTO, String Cuenta, String Telefono, String numempleado) throws CAException { AsignacionPorErrorAplicaDAO asignacionPorErrorAplicaDAO = new AsignacionPorErrorAplicaDAO();
/* 1529 */     asignacionPorErrorAplicaDAO.AsignacionPorErrorAplic(asignaPorErrorTO, Cuenta, Telefono, numempleado);
/*      */   }
/*      */   
/* 1532 */   public boolean VerificasiexisteAPTOE(String Cuenta, String Telefono) throws CAException { AsignacionPorErrorAplicaDAO asignacionPorErrorAplicaDAO = new AsignacionPorErrorAplicaDAO();
/* 1533 */     return asignacionPorErrorAplicaDAO.VerificasiexisteAPTOE(Cuenta, Telefono);
/*      */   }
/*      */   
/*      */   public ArrayList<MembresiaTO> consultaDatosM2KMembresias(ParametrosTO parametrosTO, FileDataTO fileDataTO) throws CAException {
/* 1537 */     ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
/* 1538 */     return consultaM2KDAO.consultaDatosM2KMembresias(parametrosTO, fileDataTO);
/*      */   }
/*      */   
/*      */   public boolean listaMembresia(ArrayList<MembresiaTO> membresias, int idRegion) throws CAException {
/* 1542 */     MembresiaDAO membresiaDAO = new MembresiaDAO();
/* 1543 */     return membresiaDAO.listaMembresia(membresias, idRegion);
/*      */   }
/*      */   
/*      */   public MensajeTO enviaSMS(String linea, String mensajeSMS, Catalogo properties) throws CAException {
/* 1547 */     NotificaSMS notificaSMS = new NotificaSMS();
/* 1548 */     return notificaSMS.enviaSMSRedencionCA(linea, mensajeSMS, properties);
/*      */   }
/*      */   
/* 1551 */   public boolean isLineaBloqueada(String cuenta, String telefono) throws CAException { return this.lineasRestringidasDAO.isLineaBloqueada(cuenta, telefono); }
/*      */   
/*      */   public void cargaLineasRestringidas(List<TelefonoTO> lineas) throws CAException {
/* 1554 */     this.lineasRestringidasDAO.cargaLineasRestringidas(lineas);
/*      */   }
/*      */   
/*      */   public TelefonoTO cuentaDestinoCancelacion(String cuentaOrigen, int secuenciaOrigen) throws CAException {
/* 1558 */     TransferenciaCaregDAO transferencia = new TransferenciaCaregDAO();
/* 1559 */     return transferencia.cuentaDestinoCancelacion(cuentaOrigen, secuenciaOrigen);
/*      */   }
/*      */   
/*      */   public TransferenciaTO cancelaTransferenciaCareg(TransferenciaTO _transfTO) throws CAException {
/* 1563 */     TransferenciaCaregDAO transferencia = new TransferenciaCaregDAO();
/* 1564 */     return transferencia.cancelaTransferenciaCareg(_transfTO);
/*      */   }
/*      */   
/*      */   public boolean ValidaCambioPass(UsuarioTO usuarioTO) throws Exception
/*      */   {
/* 1569 */     UsuarioDAO usuarioDAO = new UsuarioDAO();
/* 1570 */     return usuarioDAO.ValidaCambioPass(usuarioTO);
/*      */   }
/*      */   
/*      */   public List<TelefonoTO> generaReporteLineasRestringidas() throws Exception {
/* 1574 */     LineasRestringidasDAO lineasRestringidasDAO = new LineasRestringidasDAO();
/* 1575 */     return lineasRestringidasDAO.generaReporteLineasRestringidas();
/*      */   }
/*      */   
/*      */   public boolean validaLineaAsignacion(String cuenta, String telefono, int region) throws CAException
/*      */   {
/* 1580 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1581 */     return asignacionDAO.validaLineaAsignacion(cuenta, telefono, region);
/*      */   }
/*      */   
/*      */   public int obtienePuntosMaxAsignar(int idPerfilN) throws CAException
/*      */   {
/* 1586 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1587 */     return asignacionDAO.obtienePuntosMaxAsignar(idPerfilN);
/*      */   }
/*      */   
/*      */   public ArrayList<PerfilTO> obtienePerfilesValidos() throws CAException
/*      */   {
/* 1592 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1593 */     return asignacionDAO.obtienePerfilesValidos();
/*      */   }
/*      */   
/*      */   public ArrayList<PerfilTO> obtienePerfilesAsignacion(int idPerfilN, int numPtosMaximo) throws CAException {
/* 1597 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1598 */     return asignacionDAO.obtienePerfilesAsignacion(idPerfilN, numPtosMaximo);
/*      */   }
/*      */   
/*      */   public boolean actualizaPrivilegiosAsignacion(int idPerfilN, int numMaxPtos, String numEmpleado) throws CAException
/*      */   {
/* 1603 */     return this.seguridadDAO.actualizaPrivilegiosAsignacion(idPerfilN, numMaxPtos, numEmpleado);
/*      */   }
/*      */   
/*      */   public boolean actualizaPerfilAsignacion(int idPerfilN, int numMaxPtos, String numEmpleado) throws CAException
/*      */   {
/* 1608 */     return this.seguridadDAO.actualizaPerfilAsignacion(idPerfilN, numMaxPtos, numEmpleado);
/*      */   }
/*      */   
/*      */   public boolean eliminaPerfilAsignacion(int idPerfilN) throws CAException
/*      */   {
/* 1613 */     return this.seguridadDAO.eliminaPerfilAsignacion(idPerfilN);
/*      */   }
/*      */   
/*      */   public ArrayList<MotivoTO> obtieneMotivosAsignacion(String idMotivo, String descripcion, int estatus) throws CAException
/*      */   {
/* 1618 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1619 */     return asignacionDAO.obtieneMotivosAsignacion(idMotivo, descripcion, estatus);
/*      */   }
/*      */   
/*      */   public boolean insertaMotivoAsignacion(String idMotivo, String descripcion, int estatus) throws CAException {
/* 1623 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1624 */     return asignacionDAO.insertaMotivoAsignacion(idMotivo, descripcion, estatus);
/*      */   }
/*      */   
/*      */   public boolean actualizaMotivoAsignacion(String idMotivo, String descripcion, int estatus) throws CAException {
/* 1628 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1629 */     return asignacionDAO.actualizaMotivoAsignacion(idMotivo, descripcion, estatus);
/*      */   }
/*      */   
/*      */   public boolean eliminaMotivoAsignacion(String idMotivo) throws CAException
/*      */   {
/* 1634 */     AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1635 */     return asignacionDAO.eliminaMotivoAsignacion(idMotivo);
/*      */   }
/*      */   
/* 1638 */   public List<TelefonoTO> getLineasPrueba() throws CAException { AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1639 */     return asignacionDAO.getLineasPrueba();
/*      */   }
/*      */   
/* 1642 */   public MensajeTO insertaLineaPrueba(TelefonoTO telefonoTO, String numeroEmpleado) throws CAException { AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1643 */     return asignacionDAO.insertaLineaPrueba(telefonoTO, numeroEmpleado);
/*      */   }
/*      */   
/* 1646 */   public MensajeTO eliminaLineaPrueba(TelefonoTO telefonoTO) throws CAException { AsignacionDAO asignacionDAO = new AsignacionDAO();
/* 1647 */     return asignacionDAO.eliminaLineaPrueba(telefonoTO);
/*      */   }
/*      */   
/*      */   public String getPlanesInbursa() throws CAException
/*      */   {
/* 1652 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1653 */     return catalogoDAO.getPlanesInbursa();
/*      */   }
/*      */   
/* 1656 */   public String getMarcasInbursa() throws CAException { CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1657 */     return catalogoDAO.getMarcasInbursa();
/*      */   }
/*      */   
/* 1660 */   public String getModelosInbursa() throws CAException { CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1661 */     return catalogoDAO.getModelosInbursa();
/*      */   }
/*      */   
/* 1664 */   public boolean validaBonoInbursa(String cuenta) throws CAException { CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1665 */     return catalogoDAO.validaBonoInbursa(cuenta);
/*      */   }
/*      */   
/*      */   public String getLineasInbursa() throws CAException {
/* 1669 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/* 1670 */     return catalogoDAO.getLineasInbursa();
/*      */   }
/*      */   
/*      */   public ArrayList<InbursaTO> getMovimientosInbursa(String telefono) throws CAException
/*      */   {
/* 1675 */     ConsultaInbursaDAO consultaInbursaDAO = new ConsultaInbursaDAO();
/* 1676 */     return consultaInbursaDAO.getMovimientosInbursa(telefono);
/*      */   }
/*      */   
/*      */   public List<InbursaTO> getInbursaRentas(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1680 */     ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
/* 1681 */     reportesCirculoAzulDAO.getInbursaRentas(fechaInicial, fechaFinal, idRegion);
/* 1682 */     return reportesCirculoAzulDAO.getInbursaRentas(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/*      */   public List<InbursaTO> getInbursaMinutos(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1686 */     ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
/* 1687 */     reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial, fechaFinal, idRegion);
/* 1688 */     return reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/*      */   public List<InbursaTO> getInbursaDescuento1000(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
/* 1692 */     ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
/* 1693 */     reportesCirculoAzulDAO.getInbursaMinutos(fechaInicial, fechaFinal, idRegion);
/* 1694 */     return reportesCirculoAzulDAO.getInbursaDescuento1000(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/* 1697 */   public List<InbursaTO> getInbursaPaquetes(String fechaInicial, String fechaFinal, int idRegion) throws CAException { ReportesCirculoAzulDAO reportesCirculoAzulDAO = new ReportesCirculoAzulDAO();
/* 1698 */     reportesCirculoAzulDAO.getInbursaPaquetes(fechaInicial, fechaFinal, idRegion);
/* 1699 */     return reportesCirculoAzulDAO.getInbursaPaquetes(fechaInicial, fechaFinal, idRegion);
/*      */   }
/*      */   
/*      */   public List<MovimientoCertificadoTO> consultaMovimientosTajetaCertificado(String numeroCertificado) throws ClaroException {
/* 1703 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1704 */     return certificadosDAO.consultaMovimientosTajetaCertificado(numeroCertificado);
/*      */   }
/*      */   
/* 1707 */   public TarjetaCertificadoTO consultaSaldoTarjetaCertificado(String numeroCertificado) throws ClaroException { CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1708 */     return certificadosDAO.consultaSaldoTarjetaCertificado(numeroCertificado);
/*      */   }
/*      */   
/*      */   public ActivacionTarjetaCertificadoTO activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
/*      */     throws ClaroException
/*      */   {
/* 1714 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1715 */     return certificadosDAO.activaTarjetaCertificado(numeroTarjeta, montoCertificado, idUsuario);
/*      */   }
/*      */   
/*      */   public MensajeServiceTO aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO)
/*      */     throws ClaroException
/*      */   {
/* 1721 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1722 */     return certificadosDAO.aplicaCertificado(movimientoCertificadoTO);
/*      */   }
/*      */   
/*      */   public MensajeServiceTO cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws ClaroException
/*      */   {
/* 1727 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1728 */     return certificadosDAO.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
/*      */   }
/*      */   
/*      */   public MensajeServiceTO cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia) throws ClaroException
/*      */   {
/* 1733 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1734 */     return certificadosDAO.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta, referencia);
/*      */   }
/*      */   
/*      */   public boolean consultaTajeta(String numeroTarjeta, long montoCertificado) throws ClaroException {
/* 1738 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1739 */     return certificadosDAO.consultaTajeta(numeroTarjeta, montoCertificado);
/*      */   }
/*      */   
/*      */   public TarjetaCertificadoTO consultaSaldoCertificado(String numeroTarjeta) throws ClaroException {
/* 1743 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1744 */     return certificadosDAO.consultaSaldoCertificado(numeroTarjeta);
/*      */   }
/*      */   
/*      */   public List<MovimientoCertificadoTO> consultaMovimientosCertificado(String numeroTarjeta) throws ClaroException, RemoteException {
/* 1748 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1749 */     return certificadosDAO.consultaMovimientosCertificado(numeroTarjeta);
/*      */   }
/*      */   
/*      */   public String consultaTajetaVendida(String numeroTarjeta, long montoCertificado) throws ClaroException, RemoteException {
/* 1753 */     CertificadosDAO certificadosDAO = new CertificadosDAO();
/* 1754 */     return certificadosDAO.consultaTajetaVendida(numeroTarjeta, montoCertificado);
/*      */   }
/*      */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/ejb/CirculoAzulBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */