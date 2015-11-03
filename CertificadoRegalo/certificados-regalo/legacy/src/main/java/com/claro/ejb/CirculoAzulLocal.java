package com.claro.ejb;

import com.claro.catalogo.Catalogo;
import com.claro.exception.CAException;
import com.claro.exception.ClaroException;
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

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBLocalObject;

public abstract interface CirculoAzulLocal
  extends EJBLocalObject
{
  public abstract UsuarioTO consultaEmpleado(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws CAException;
  
  public abstract PerfilTO getPrivilegiosCirculoAzulEcac(int paramInt)
    throws CAException;
  
  public abstract UsuarioTO validaUserPerfil(String paramString1, String paramString2)
    throws CAException;
  
  public abstract UsuarioTO consultaUsuario(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract boolean agregarUsuario(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract boolean actualizaUsuario(UsuarioTO paramUsuarioTO1, UsuarioTO paramUsuarioTO2, int paramInt)
    throws Exception;
  
  public abstract void fechaAcceso(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract boolean eliminaUsuario(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract ArrayList<RedencionTO> detalleRedencion(String paramString1, int paramInt, String paramString2)
    throws CAException;
  
  public abstract ArrayList<MovimientoTO> detalleMovimientos(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ArrayList<AlianzasTO> consultaCanjesAlianzas(String paramString1, String paramString2)
    throws CAException;
  
  public abstract TelefonoTO procedimientoGeneral(ParametrosTO paramParametrosTO, PerfilTO paramPerfilTO, String paramString)
    throws CAException;
  
  public abstract FactorTO consultaFactor(int paramInt1, int paramInt2)
    throws CAException;
  
  public abstract ArrayList<CatalogoTO> getMarcas(int paramInt1, int paramInt2)
    throws CAException;
  
  public abstract ArrayList<CatalogoTO> getModelos(String paramString1, int paramInt1, int paramInt2, String paramString2)
    throws CAException;
  
  public abstract ArrayList<CuentaPadreTO> consultaPadre(String paramString)
    throws CAException;
  
  public abstract int consultaRegion(String paramString)
    throws CAException;
  
  public abstract RedencionTO aplicaredencion(ProductosTO paramProductosTO, ParametrosTO paramParametrosTO, RedencionTO paramRedencionTO, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, BeneficioTO paramBeneficioTO, String paramString3)
    throws CAException;
  
  public abstract TelefonoTO procedimientoGeneralPuntitos(ParametrosTO paramParametrosTO)
    throws CAException;
  
  public abstract ArrayList<TelefonoTO> consultaLinea(TelefonoTO paramTelefonoTO)
    throws Exception;
  
  public abstract ArrayList<TelefonoTO> consultaReserva(TelefonoTO paramTelefonoTO)
    throws Exception;
  
  public abstract boolean cancelaReserva(TelefonoTO paramTelefonoTO)
    throws Exception;
  
  public abstract ArrayList<PuntoVentaTO> consultaPtosVta(PuntoVentaTO paramPuntoVentaTO)
    throws Exception;
  
  public abstract boolean agregaPtoVta(PuntoVentaTO paramPuntoVentaTO)
    throws Exception;
  
  public abstract boolean actualizaPtoVta(PuntoVentaTO paramPuntoVentaTO)
    throws Exception;
  
  public abstract boolean eliminaPtoVta(PuntoVentaTO paramPuntoVentaTO)
    throws Exception;
  
  public abstract void borraArchivo(DocumentoTO paramDocumentoTO)
    throws Exception;
  
  public abstract void guardaArchivo(DocumentoTO paramDocumentoTO, FileDataTO paramFileDataTO)
    throws Exception;
  
  public abstract int insertaPromocion(DocumentoTO paramDocumentoTO)
    throws Exception;
  
  public abstract void borrarPromocion(DocumentoTO paramDocumentoTO)
    throws Exception;
  
  public abstract int obtenIDDocumento()
    throws Exception;
  
  public abstract ArrayList<DocumentoTO> obtenPromociones(String paramString)
    throws Exception;
  
  public abstract ArrayList<PromocionTO> consultaPromociones(PromocionTO paramPromocionTO)
    throws Exception;
  
  public abstract ArrayList<PlanTO> consultaPlanes(PlanTO paramPlanTO)
    throws Exception;
  
  public abstract ArrayList<FuerzaVentasTO> consultaFuerzaVentas(FuerzaVentasTO paramFuerzaVentasTO)
    throws Exception;
  
  public abstract ArrayList<GrupoTO> consultaGpoPromo(GrupoTO paramGrupoTO)
    throws Exception;
  
  public abstract byte[] generaReporte(InputStream paramInputStream, HashMap<String, String> paramHashMap)
    throws Exception;
  
  public abstract byte[] generaReporteCSV(InputStream paramInputStream, HashMap<String, String> paramHashMap)
    throws Exception;
  
  public abstract ArrayList<CatalogoTO> obtienePropiedades()
    throws CAException;
  
  public abstract ArrayList<CatalogoTO> obtienePerfil()
    throws CAException;
  
  public abstract ArrayList<CatalogoTO> obtieneGrupoPromocion()
    throws CAException;
  
  public abstract Map<String, PrivilegioTO> getprivilegiosCa()
    throws CAException;
  
  public abstract ArrayList<ProductosTO> listaPromociones(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, String paramString3, String paramString4, String paramString5)
    throws CAException;
  
  public abstract ArrayList<AlianzasTO> listaCertLealtad(String paramString, int paramInt)
    throws CAException;
  
  public abstract RetencionTO calculaValorCupon(Double paramDouble, int paramInt1, int paramInt2, int paramInt3)
    throws CAException;
  
  public abstract MensajeTO actualizaPassword(String paramString1, String paramString2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract PlanTO datosPlan(String paramString, int paramInt)
    throws CAException;
  
  public abstract MensajeTO actualizaFolioSAP(FolioSAPTO paramFolioSAPTO)
    throws CAException;
  
  public abstract ArrayList<RedencionTO> consultaFolioSAP(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, boolean paramBoolean)
    throws CAException;
  
  public abstract ReservacionTO realizaApartado(ReservacionTO paramReservacionTO, TelefonoTO paramTelefonoTO, boolean paramBoolean, String paramString)
    throws CAException;
  
  public abstract ImpresionTO datosConstanciaImpresion(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    throws CAException;
  
  public abstract ImpresionTO datosConstanciaBonoInbursa(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ArrayList<ImpresionTO> detalleRedenImp(String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract ArrayList<ImpresionTO> detalleBonosInbursa(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ImpresionTO obtieneConstanciaAlianzas(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract ArrayList<ImpresionTO> detalleImpresionAlianzas(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
    throws CAException;
  
  public abstract PuntosTO obtienePuntos(String paramString, int paramInt)
    throws CAException;
  
  public abstract ArrayList<ProductosTO> consultaProductos(String paramString1, String paramString2, String paramString3, TelefonoTO paramTelefonoTO, UsuarioTO paramUsuarioTO, String paramString4, String paramString5, String paramString6, double paramDouble, String paramString7, String paramString8, String paramString9, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString10)
    throws CAException;
  
  public abstract MensajeTO cancelaApartado(ParametrosTO paramParametrosTO, UsuarioTO paramUsuarioTO, boolean paramBoolean)
    throws CAException;
  
  public abstract MensajeTO agregarMembresia(MembresiaTO paramMembresiaTO)
    throws CAException;
  
  public abstract MensajeTO actualizaPromoAlianza(String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
    throws CAException;
  
  public abstract MensajeTO altaPromoAlianza(String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
    throws CAException;
  
  public abstract MembresiaTO consultaMembresia(String paramString, int paramInt, Date paramDate)
    throws CAException;
  
  public abstract MensajeTO reactivaPuntos(ParametrosTO paramParametrosTO)
    throws CAException;
  
  public abstract MensajeTO renunciaPuntos(ParametrosTO paramParametrosTO)
    throws CAException;
  
  public abstract MensajeTO confirmaCanje(int paramInt1, FolioLiberacionTO paramFolioLiberacionTO, int paramInt2, String paramString1, String paramString2, int paramInt3, String paramString3, String paramString4, int paramInt4)
    throws CAException;
  
  public abstract MensajeTO cancelaRedencion(ParametrosTO paramParametrosTO, String paramString, int paramInt, UsuarioTO paramUsuarioTO)
    throws CAException;
  
  public abstract TransferenciaTO transfierePuntosAnexo(TransferenciaTO paramTransferenciaTO)
    throws CAException;
  
  public abstract TransferenciaTO transfierePuntosRegion(TransferenciaTO paramTransferenciaTO)
    throws CAException;
  
  public abstract ArrayList<AlianzasTO> consultaCancelaCanje(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract MensajeTO cancelaAlianza(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract MensajeTO realizaCancelacion(ParametrosTO paramParametrosTO, int paramInt)
    throws CAException;
  
  public abstract ArrayList<ImpresionTO> detalleEquipo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws CAException;
  
  public abstract ArrayList<CiudadEdoTO> getEstadoCiudad(String paramString)
    throws CAException;
  
  public abstract ReservacionTO consultaFolioReservacion(String paramString1, String paramString2)
    throws CAException;
  
  public abstract MensajeTO realizaCanje(ParametrosTO paramParametrosTO, int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, int paramInt4, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt5, String paramString7, int paramInt6)
    throws CAException;
  
  public abstract Hashtable<String, AlianzasTO> consultaInicialAlianzas(int paramInt1, String paramString, int paramInt2, int paramInt3)
    throws CAException;
  
  public abstract MensajeTO procesaAsignacion(ParametrosTO paramParametrosTO, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3)
    throws CAException;
  
  public abstract FolioLiberacionTO asignaPorAntiguedad(ParametrosTO paramParametrosTO, String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract MensajeTO consultaCertificado(String paramString1, String paramString2)
    throws CAException;
  
  public abstract RetencionTO consultaRetencion(String paramString1, String paramString2)
    throws CAException;
  
  public abstract MensajeTO validaImpresion(String paramString, Date paramDate)
    throws CAException;
  
  public abstract MensajeTO cancelarCertificado(String paramString1, String paramString2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract void obtenFacturaciones(MobileTO paramMobileTO, int paramInt1, String paramString, int paramInt2)
    throws CAException;
  
  public abstract MobileTO consultaPorCuenta(String paramString, int paramInt)
    throws CAException;
  
  public abstract MensajeTO asignacionPuntos(ParametrosTO paramParametrosTO, int paramInt, String paramString)
    throws CAException;
  
  public abstract MobileTO consultaDatosM2K(ParametrosTO paramParametrosTO)
    throws CAException;
  
  public abstract String consultaPrecioEquipo(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract MensajeTO almacenaCertificado(TelefonoTO paramTelefonoTO, UsuarioTO paramUsuarioTO, RetencionTO paramRetencionTO, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    throws CAException;
  
  public abstract RetencionTO getMotivos()
    throws CAException;
  
  public abstract String getPromoSIM(String paramString1, String paramString2, String paramString3, int paramInt)
    throws CAException;
  
  public abstract TelefonoTO consultaDatosSIM(String paramString)
    throws CAException;
  
  public abstract ImpresionTO datosImpresion(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ImpresionTO consultaCertificados(String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract ImpresionTO obtieneCertificado(String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract AlianzasTO consultaPromocionesAmex(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4)
    throws CAException;
  
  public abstract ArrayList<PromoBeneficiosTO> obtieneBeneficios(int paramInt, String paramString)
    throws CAException;
  
  public abstract ArrayList<MotivoTO> obtieneCatalogoRechazo(int paramInt)
    throws CAException;
  
  public abstract ArrayList<PlanTO> getPlanesTarifarios(int paramInt)
    throws CAException;
  
  public abstract ArrayList<AvisosTO> historicoAvisos(String paramString1, String paramString2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract MensajeTO insertarAvisos(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws CAException;
  
  public abstract MensajeTO eliminarAvisos(String paramString, int paramInt)
    throws CAException;
  
  public abstract ArrayList<AvisosTO> marquesinaAvisos(long paramLong)
    throws CAException;
  
  public abstract ArrayList<UsuarioTO> consultaUsuarios(UsuarioTO paramUsuarioTO, int paramInt)
    throws CAException;
  
  public abstract PuntoVentaTO obtienePuntoVenta(String paramString)
    throws CAException;
  
  public abstract PlanTO consultaPlan(String paramString, int paramInt)
    throws CAException;
  
  public abstract MensajeTO consultaTipoPlan(String paramString, int paramInt)
    throws CAException;
  
  public abstract boolean existeUsuario(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract ProductosSmsTO promocionxSms(String paramString, int paramInt)
    throws CAException;
  
  public abstract PerfilTO getPrivilegiosPerfil(String paramString)
    throws CAException;
  
  public abstract void actualizaPrivilegios(int paramInt, List<PrivilegioTO> paramList, String paramString)
    throws CAException;
  
  public abstract List<CertificadoLealtadTO> getCertificadosLealtad(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<RedencionReporteTO> getRedenciones(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<RedencionDetalleTO> getRedencionesDetalle(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<RoextTO> getRoext(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<ComisionTO> getComisiones(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<RetencionReporteTO> getRetenciones(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract Map<String, List<Reportable>> getAlianzas(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<AcumuladosTO> getAcumulados(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<AltoValorTO> getAltoValor(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<PuntosVencerTO> getPuntosPorVencer(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<RedencionLineaTO> getRedencionesLinea(String paramString1, String paramString2, List<String> paramList)
    throws CAException;
  
  public abstract ArrayList<PlanTO> planesRedencionByRegion(int paramInt)
    throws CAException;
  
  public abstract ArrayList<PromocionTO> consultaPromocionesDistribuidores(PromocionTO paramPromocionTO, String paramString, int paramInt)
    throws Exception;
  
  public abstract ArrayList<CatalogoTO> getMarcasDistribuidores(int paramInt, String paramString1, String paramString2)
    throws CAException;
  
  public abstract ArrayList<ReservacionServiceTO> consultaFoliosDistribuidores(String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract int consultaRegionLinea(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ArrayList<ProductosSmsTO> productosonline()
    throws CAException;
  
  public abstract int valorPuntosOnline(String paramString)
    throws CAException;
  
  public abstract ArrayList<ProductosSmsTO> consultaPromocionesSms(ProductosSmsTO paramProductosSmsTO)
    throws Exception;
  
  public abstract List<RedencionesOnlineTO> getRedencionesOnline(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract ArrayList<CatalogoTO> consultaAreasPromocion()
    throws CAException;
  
  public abstract boolean aplicaRedencionMulticotizador(String paramString)
    throws Exception;
  
  public abstract ArrayList<PlanTO> planesSisact(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    throws CAException;
  
  public abstract boolean aplicaRedencionSisact(String paramString1, TelefonoServiceTO paramTelefonoServiceTO, String paramString2)
    throws CAException;
  
  public abstract TransferenciaTO transfierePuntosExcelente(TransferenciaTO paramTransferenciaTO)
    throws CAException;
  
  public abstract Hashtable<String, Object> validaProductoDescuento(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
    throws CAException;
  
  public abstract ArrayList<CteExcelenteTO> consultaLineasCteExc(CteExcelenteTO paramCteExcelenteTO)
    throws CAException;
  
  public abstract AsignaPorErrorTO VerificaEPBAJ(String paramString1, String paramString2)
    throws CAException;
  
  public abstract void AsignacionPorErrorAplic(AsignaPorErrorTO paramAsignaPorErrorTO, String paramString1, String paramString2, String paramString3)
    throws CAException;
  
  public abstract boolean VerificasiexisteAPTOE(String paramString1, String paramString2)
    throws CAException;
  
  public abstract ArrayList<MembresiaTO> consultaDatosM2KMembresias(ParametrosTO paramParametrosTO, FileDataTO paramFileDataTO)
    throws CAException;
  
  public abstract boolean listaMembresia(ArrayList<MembresiaTO> paramArrayList, int paramInt)
    throws CAException;
  
  public abstract MensajeTO enviaSMS(String paramString1, String paramString2, Catalogo paramCatalogo)
    throws CAException;
  
  public abstract boolean isLineaBloqueada(String paramString1, String paramString2)
    throws CAException;
  
  public abstract void cargaLineasRestringidas(List<TelefonoTO> paramList)
    throws CAException;
  
  public abstract TelefonoTO cuentaDestinoCancelacion(String paramString, int paramInt)
    throws CAException;
  
  public abstract TransferenciaTO cancelaTransferenciaCareg(TransferenciaTO paramTransferenciaTO)
    throws CAException;
  
  public abstract boolean ValidaCambioPass(UsuarioTO paramUsuarioTO)
    throws Exception;
  
  public abstract List<TelefonoTO> generaReporteLineasRestringidas()
    throws Exception;
  
  public abstract boolean validaLineaAsignacion(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract int obtienePuntosMaxAsignar(int paramInt)
    throws CAException;
  
  public abstract ArrayList<PerfilTO> obtienePerfilesValidos()
    throws CAException;
  
  public abstract ArrayList<PerfilTO> obtienePerfilesAsignacion(int paramInt1, int paramInt2)
    throws CAException;
  
  public abstract boolean actualizaPrivilegiosAsignacion(int paramInt1, int paramInt2, String paramString)
    throws CAException;
  
  public abstract boolean actualizaPerfilAsignacion(int paramInt1, int paramInt2, String paramString)
    throws CAException;
  
  public abstract boolean eliminaPerfilAsignacion(int paramInt)
    throws CAException;
  
  public abstract ArrayList<MotivoTO> obtieneMotivosAsignacion(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract boolean insertaMotivoAsignacion(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract boolean actualizaMotivoAsignacion(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract boolean eliminaMotivoAsignacion(String paramString)
    throws CAException;
  
  public abstract List<TelefonoTO> getLineasPrueba()
    throws CAException;
  
  public abstract MensajeTO insertaLineaPrueba(TelefonoTO paramTelefonoTO, String paramString)
    throws CAException;
  
  public abstract MensajeTO eliminaLineaPrueba(TelefonoTO paramTelefonoTO)
    throws CAException;
  
  public abstract String getPlanesInbursa()
    throws CAException;
  
  public abstract String getMarcasInbursa()
    throws CAException;
  
  public abstract String getModelosInbursa()
    throws CAException;
  
  public abstract boolean validaBonoInbursa(String paramString)
    throws CAException;
  
  public abstract String getLineasInbursa()
    throws CAException;
  
  public abstract ArrayList<InbursaTO> getMovimientosInbursa(String paramString)
    throws CAException;
  
  public abstract List<InbursaTO> getInbursaRentas(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<InbursaTO> getInbursaMinutos(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<InbursaTO> getInbursaDescuento1000(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<InbursaTO> getInbursaPaquetes(String paramString1, String paramString2, int paramInt)
    throws CAException;
  
  public abstract List<MovimientoCertificadoTO> consultaMovimientosTajetaCertificado(String paramString)
    throws ClaroException;
  
  public abstract TarjetaCertificadoTO consultaSaldoTarjetaCertificado(String paramString)
    throws ClaroException;
  
  public abstract ActivacionTarjetaCertificadoTO activaTarjetaCertificado(String paramString1, long paramLong, String paramString2)
    throws ClaroException;
  
  public abstract MensajeServiceTO aplicaCertificado(MovimientoCertificadoTO paramMovimientoCertificadoTO)
    throws ClaroException;
  
  public abstract MensajeServiceTO cancelaTarjetaCertificado(String paramString1, String paramString2)
    throws ClaroException;
  
  public abstract MensajeServiceTO cancelaAplicaCertificado(String paramString1, String paramString2, String paramString3, String paramString4)
    throws ClaroException;
  
  public abstract boolean consultaTajeta(String paramString, long paramLong)
    throws ClaroException;
  
  public abstract TarjetaCertificadoTO consultaSaldoCertificado(String paramString)
    throws ClaroException, RemoteException;
  
  public abstract List<MovimientoCertificadoTO> consultaMovimientosCertificado(String paramString)
    throws ClaroException;
  
  public abstract String consultaTajetaVendida(String paramString, long paramLong)
    throws ClaroException;
}


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/ejb/CirculoAzulLocal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */