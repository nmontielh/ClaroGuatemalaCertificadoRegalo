/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJBObject
 */
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
import com.claro.transfer.reportes.Reportable;
import com.claro.transfer.reportes.RetencionReporteTO;
import com.claro.transfer.reportes.RoextTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
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
import javax.ejb.EJBObject;

public interface CirculoAzul
extends EJBObject {
    public UsuarioTO consultaEmpleado(String var1, String var2, String var3, boolean var4) throws CAException, RemoteException;

    public PerfilTO getPrivilegiosCirculoAzulEcac(int var1) throws CAException, RemoteException;

    public UsuarioTO validaUserPerfil(String var1, String var2) throws CAException, RemoteException;

    public UsuarioTO consultaUsuario(UsuarioTO var1) throws Exception, RemoteException;

    public boolean agregarUsuario(UsuarioTO var1) throws Exception, RemoteException;

    public boolean actualizaUsuario(UsuarioTO var1, UsuarioTO var2, int var3) throws Exception, RemoteException;

    public void fechaAcceso(UsuarioTO var1) throws Exception;

    public boolean eliminaUsuario(UsuarioTO var1) throws Exception, RemoteException;

    public ArrayList<RedencionTO> detalleRedencion(String var1, int var2, String var3) throws CAException, RemoteException;

    public ArrayList<MovimientoTO> detalleMovimientos(String var1, String var2) throws CAException, RemoteException;

    public ArrayList<AlianzasTO> consultaCanjesAlianzas(String var1, String var2) throws CAException, RemoteException;

    public TelefonoTO procedimientoGeneral(ParametrosTO var1, PerfilTO var2, String var3) throws CAException, RemoteException;

    public ArrayList<TelefonoTO> consultaLinea(TelefonoTO var1) throws Exception, RemoteException;

    public ArrayList<TelefonoTO> consultaReserva(TelefonoTO var1) throws Exception, RemoteException;

    public boolean cancelaReserva(TelefonoTO var1) throws Exception, RemoteException;

    public ArrayList<PuntoVentaTO> consultaPtosVta(PuntoVentaTO var1) throws Exception, RemoteException;

    public boolean agregaPtoVta(PuntoVentaTO var1) throws Exception, RemoteException;

    public boolean actualizaPtoVta(PuntoVentaTO var1) throws Exception, RemoteException;

    public boolean eliminaPtoVta(PuntoVentaTO var1) throws Exception, RemoteException;

    public void borraArchivo(DocumentoTO var1) throws Exception, RemoteException;

    public void guardaArchivo(DocumentoTO var1, FileDataTO var2) throws Exception, RemoteException;

    public int insertaPromocion(DocumentoTO var1) throws Exception, RemoteException;

    public void borrarPromocion(DocumentoTO var1) throws Exception, RemoteException;

    public int obtenIDDocumento() throws Exception, RemoteException;

    public ArrayList<DocumentoTO> obtenPromociones(String var1) throws Exception, RemoteException;

    public ArrayList<PromocionTO> consultaPromociones(PromocionTO var1) throws Exception, RemoteException;

    public ArrayList<PlanTO> consultaPlanes(PlanTO var1) throws Exception, RemoteException;

    public ArrayList<GrupoTO> consultaGpoPromo(GrupoTO var1) throws Exception, RemoteException;

    public ArrayList<CatalogoTO> obtieneGrupoPromocion() throws CAException, RemoteException;

    public Map<String, PrivilegioTO> getprivilegiosCa() throws CAException, RemoteException;

    public byte[] generaReporte(InputStream var1, HashMap<String, String> var2) throws Exception, RemoteException;

    public byte[] generaReporteCSV(InputStream var1, HashMap<String, String> var2) throws Exception, RemoteException;

    public FactorTO consultaFactor(int var1, int var2) throws CAException, RemoteException;

    public ArrayList<CatalogoTO> getMarcas(int var1, int var2) throws CAException, RemoteException;

    public ArrayList<CatalogoTO> getModelos(String var1, int var2, int var3, String var4) throws CAException, RemoteException;

    public ArrayList<CuentaPadreTO> consultaPadre(String var1) throws CAException, RemoteException;

    public int consultaRegion(String var1) throws CAException, RemoteException;

    public RedencionTO aplicaredencion(ProductosTO var1, ParametrosTO var2, RedencionTO var3, boolean var4, boolean var5, String var6, String var7, BeneficioTO var8, String var9) throws CAException, RemoteException;

    public TelefonoTO procedimientoGeneralPuntitos(ParametrosTO var1) throws CAException, RemoteException;

    public ArrayList<CatalogoTO> obtienePropiedades() throws CAException, RemoteException;

    public ArrayList<CatalogoTO> obtienePerfil() throws CAException, RemoteException;

    public ArrayList<ProductosTO> listaPromociones(int var1, int var2, String var3, int var4, String var5, String var6, String var7, String var8) throws CAException, RemoteException;

    public ArrayList<AlianzasTO> listaCertLealtad(String var1, int var2) throws CAException, RemoteException;

    public RetencionTO calculaValorCupon(Double var1, int var2, int var3, int var4) throws CAException, RemoteException;

    public MensajeTO actualizaPassword(String var1, String var2, String var3, String var4) throws CAException, RemoteException;

    public PlanTO datosPlan(String var1, int var2) throws CAException, RemoteException;

    public MensajeTO actualizaFolioSAP(FolioSAPTO var1) throws CAException, RemoteException;

    public ArrayList<RedencionTO> consultaFolioSAP(String var1, String var2, String var3, int var4, String var5, int var6, boolean var7) throws CAException, RemoteException;

    public ReservacionTO realizaApartado(ReservacionTO var1, TelefonoTO var2, boolean var3, String var4) throws CAException, RemoteException;

    public ImpresionTO datosConstanciaImpresion(String var1, String var2, String var3, String var4, String var5, String var6) throws CAException, RemoteException;

    public ArrayList<ImpresionTO> detalleRedenImp(String var1, String var2, String var3) throws CAException, RemoteException;

    public ImpresionTO obtieneConstanciaAlianzas(String var1, String var2, int var3, int var4, String var5, String var6) throws CAException, RemoteException;

    public ArrayList<ImpresionTO> detalleImpresionAlianzas(String var1, String var2, int var3, int var4, String var5) throws CAException, RemoteException;

    public PuntosTO obtienePuntos(String var1, int var2) throws CAException, RemoteException;

    public ArrayList<ProductosTO> consultaProductos(String var1, String var2, String var3, TelefonoTO var4, UsuarioTO var5, String var6, String var7, String var8, double var9, String var11, String var12, String var13, int var14, int var15, int var16, int var17, String var18) throws CAException, RemoteException;

    public MensajeTO cancelaApartado(ParametrosTO var1, UsuarioTO var2, boolean var3) throws CAException, RemoteException;

    public MensajeTO agregarMembresia(MembresiaTO var1) throws CAException, RemoteException;

    public MensajeTO actualizaPromoAlianza(String var1, int var2, String var3, int var4, String var5, String var6, String var7, String var8, String var9) throws CAException, RemoteException;

    public MensajeTO altaPromoAlianza(String var1, int var2, String var3, int var4, String var5, String var6, String var7, String var8, String var9) throws CAException, RemoteException;

    public MembresiaTO consultaMembresia(String var1, int var2, Date var3) throws CAException, RemoteException;

    public MensajeTO reactivaPuntos(ParametrosTO var1) throws CAException, RemoteException;

    public MensajeTO renunciaPuntos(ParametrosTO var1) throws CAException, RemoteException;

    public MensajeTO confirmaCanje(int var1, FolioLiberacionTO var2, int var3, String var4, String var5, int var6, String var7, String var8, int var9) throws CAException, RemoteException;

    public MensajeTO cancelaRedencion(ParametrosTO var1, String var2, int var3, UsuarioTO var4) throws CAException, RemoteException;

    public TransferenciaTO transfierePuntosAnexo(TransferenciaTO var1) throws CAException, RemoteException;

    public TransferenciaTO transfierePuntosRegion(TransferenciaTO var1) throws CAException, RemoteException;

    public ArrayList<AlianzasTO> consultaCancelaCanje(String var1, String var2, int var3) throws CAException, RemoteException;

    public MensajeTO cancelaAlianza(String var1, String var2, int var3) throws CAException, RemoteException;

    public MensajeTO realizaCancelacion(ParametrosTO var1, int var2) throws CAException, RemoteException;

    public MensajeTO realizaCanje(ParametrosTO var1, int var2, int var3, String var4, String var5, int var6, int var7, String var8, String var9, String var10, String var11, int var12, String var13, int var14) throws CAException, RemoteException;

    public ArrayList<ImpresionTO> detalleEquipo(String var1, String var2, String var3, String var4, String var5) throws CAException, RemoteException;

    public ArrayList<CiudadEdoTO> getEstadoCiudad(String var1) throws CAException, RemoteException;

    public ReservacionTO consultaFolioReservacion(String var1, String var2) throws CAException, RemoteException;

    public Hashtable<String, AlianzasTO> consultaInicialAlianzas(int var1, String var2, int var3, int var4) throws CAException, RemoteException;

    public MensajeTO procesaAsignacion(ParametrosTO var1, String var2, int var3, int var4, String var5, String var6) throws CAException, RemoteException;

    public FolioLiberacionTO asignaPorAntiguedad(ParametrosTO var1, String var2, String var3, String var4) throws CAException, RemoteException;

    public MensajeTO consultaCertificado(String var1, String var2) throws CAException, RemoteException;

    public RetencionTO consultaRetencion(String var1, String var2) throws CAException, RemoteException;

    public MensajeTO validaImpresion(String var1, Date var2) throws CAException, RemoteException;

    public MensajeTO cancelarCertificado(String var1, String var2, String var3, String var4) throws CAException, RemoteException;

    public MobileTO consultaDatosM2K(ParametrosTO var1) throws CAException, RemoteException;

    public void obtenFacturaciones(MobileTO var1, int var2, String var3, int var4) throws CAException, RemoteException;

    public MobileTO consultaPorCuenta(String var1, int var2) throws CAException, RemoteException;

    public MensajeTO asignacionPuntos(ParametrosTO var1, int var2, String var3) throws CAException, RemoteException;

    public String consultaPrecioEquipo(String var1, int var2, String var3, String var4, String var5) throws CAException, RemoteException;

    public MensajeTO almacenaCertificado(TelefonoTO var1, UsuarioTO var2, RetencionTO var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11) throws CAException, RemoteException;

    public RetencionTO getMotivos() throws CAException, RemoteException;

    public String getPromoSIM(String var1, String var2, String var3, int var4) throws CAException, RemoteException;

    public TelefonoTO consultaDatosSIM(String var1) throws CAException, RemoteException;

    public ImpresionTO datosImpresion(String var1, String var2) throws CAException, RemoteException;

    public ImpresionTO consultaCertificados(String var1, String var2, String var3) throws CAException, RemoteException;

    public ImpresionTO obtieneCertificado(String var1, String var2, String var3) throws CAException, RemoteException;

    public AlianzasTO consultaPromocionesAmex(int var1, int var2, String var3, int var4, int var5) throws CAException, RemoteException;

    public ArrayList<PromoBeneficiosTO> obtieneBeneficios(int var1, String var2) throws CAException, RemoteException;

    public ArrayList<MotivoTO> obtieneCatalogoRechazo(int var1) throws CAException, RemoteException;

    public ArrayList<PlanTO> getPlanesTarifarios(int var1) throws CAException, RemoteException;

    public ArrayList<AvisosTO> historicoAvisos(String var1, String var2, String var3, String var4) throws CAException, RemoteException;

    public MensajeTO insertarAvisos(String var1, String var2, String var3, String var4, String var5) throws CAException, RemoteException;

    public MensajeTO eliminarAvisos(String var1, int var2) throws CAException, RemoteException;

    public ArrayList<AvisosTO> marquesinaAvisos(long var1) throws CAException, RemoteException;

    public ArrayList<UsuarioTO> consultaUsuarios(UsuarioTO var1, int var2) throws CAException, RemoteException;

    public PuntoVentaTO obtienePuntoVenta(String var1) throws CAException, RemoteException;

    public PlanTO consultaPlan(String var1, int var2) throws CAException, RemoteException;

    public MensajeTO consultaTipoPlan(String var1, int var2) throws CAException, RemoteException;

    public boolean existeUsuario(UsuarioTO var1) throws Exception, RemoteException;

    public ProductosSmsTO promocionxSms(String var1, int var2) throws CAException, RemoteException;

    public PerfilTO getPrivilegiosPerfil(String var1) throws CAException, RemoteException;

    public void actualizaPrivilegios(int var1, List<PrivilegioTO> var2, String var3) throws CAException, RemoteException;

    public List<CertificadoLealtadTO> getCertificadosLealtad(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<RedencionReporteTO> getRedenciones(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<RedencionDetalleTO> getRedencionesDetalle(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<RoextTO> getRoext(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<ComisionTO> getComisiones(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<RetencionReporteTO> getRetenciones(String var1, String var2, int var3) throws CAException, RemoteException;

    public Map<String, List<Reportable>> getAlianzas(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<AcumuladosTO> getAcumulados(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<AltoValorTO> getAltoValor(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<PuntosVencerTO> getPuntosPorVencer(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<RedencionLineaTO> getRedencionesLinea(String var1, String var2, List<String> var3) throws CAException, RemoteException;

    public ArrayList<PlanTO> planesRedencionByRegion(int var1) throws CAException, RemoteException;

    public ArrayList<PromocionTO> consultaPromocionesDistribuidores(PromocionTO var1, String var2, int var3) throws Exception;

    public ArrayList<CatalogoTO> getMarcasDistribuidores(int var1, String var2, String var3) throws CAException, RemoteException;

    public ArrayList<PlanTO> planesSisact(int var1, String var2, String var3, String var4, String var5, String var6, String var7) throws CAException, RemoteException;

    public TransferenciaTO transfierePuntosExcelente(TransferenciaTO var1) throws CAException, RemoteException;

    public boolean aplicaRedencionSisact(String var1, TelefonoServiceTO var2, String var3) throws CAException, RemoteException;

    public Hashtable<String, Object> validaProductoDescuento(String var1, int var2, String var3, String var4, String var5) throws CAException, RemoteException;

    public ArrayList<CteExcelenteTO> consultaLineasCteExc(CteExcelenteTO var1) throws CAException, RemoteException;

    public AsignaPorErrorTO VerificaEPBAJ(String var1, String var2) throws RemoteException, CAException;

    public void AsignacionPorErrorAplic(AsignaPorErrorTO var1, String var2, String var3, String var4) throws RemoteException, CAException;

    public boolean VerificasiexisteAPTOE(String var1, String var2) throws RemoteException, CAException;

    public MensajeTO enviaSMS(String var1, String var2, Catalogo var3) throws RemoteException, CAException;

    public boolean isLineaBloqueada(String var1, String var2) throws RemoteException, CAException;

    public void cargaLineasRestringidas(List<TelefonoTO> var1) throws RemoteException, CAException;

    public TelefonoTO cuentaDestinoCancelacion(String var1, int var2) throws RemoteException, CAException;

    public TransferenciaTO cancelaTransferenciaCareg(TransferenciaTO var1) throws RemoteException, CAException;

    public boolean ValidaCambioPass(UsuarioTO var1) throws RemoteException, Exception;

    public List<TelefonoTO> generaReporteLineasRestringidas() throws Exception, RemoteException;

    public boolean validaLineaAsignacion(String var1, String var2, int var3) throws RemoteException, Exception;

    public int obtienePuntosMaxAsignar(int var1) throws RemoteException, Exception;

    public ArrayList<PerfilTO> obtienePerfilesValidos() throws RemoteException, Exception;

    public ArrayList<PerfilTO> obtienePerfilesAsignacion(int var1, int var2) throws RemoteException, Exception;

    public boolean actualizaPrivilegiosAsignacion(int var1, int var2, String var3) throws RemoteException, Exception;

    public boolean actualizaPerfilAsignacion(int var1, int var2, String var3) throws RemoteException, Exception;

    public boolean eliminaPerfilAsignacion(int var1) throws RemoteException, Exception;

    public ArrayList<MotivoTO> obtieneMotivosAsignacion(String var1, String var2, int var3) throws RemoteException, Exception;

    public boolean insertaMotivoAsignacion(String var1, String var2, int var3) throws RemoteException, Exception;

    public boolean actualizaMotivoAsignacion(String var1, String var2, int var3) throws RemoteException, Exception;

    public boolean eliminaMotivoAsignacion(String var1) throws RemoteException, Exception;

    public List<TelefonoTO> getLineasPrueba() throws RemoteException, CAException;

    public MensajeTO insertaLineaPrueba(TelefonoTO var1, String var2) throws RemoteException, CAException;

    public MensajeTO eliminaLineaPrueba(TelefonoTO var1) throws RemoteException, CAException;

    public ArrayList<InbursaTO> getMovimientosInbursa(String var1) throws CAException, RemoteException;

    public List<InbursaTO> getInbursaRentas(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<InbursaTO> getInbursaMinutos(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<InbursaTO> getInbursaDescuento1000(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<InbursaTO> getInbursaPaquetes(String var1, String var2, int var3) throws CAException, RemoteException;

    public List<MovimientoCertificadoTO> consultaMovimientosTajetaCertificado(String var1) throws ClaroException, RemoteException;

    public TarjetaCertificadoTO consultaSaldoTarjetaCertificado(String var1) throws ClaroException, RemoteException;

    public ActivacionTarjetaCertificadoTO activaTarjetaCertificado(String var1, long var2, String var4) throws ClaroException, RemoteException;

    public MensajeServiceTO aplicaCertificado(MovimientoCertificadoTO var1) throws ClaroException, RemoteException;

    public MensajeServiceTO cancelaTarjetaCertificado(String var1, String var2) throws ClaroException, RemoteException;

    public MensajeServiceTO cancelaAplicaCertificado(String var1, String var2, String var3, String var4) throws ClaroException, RemoteException;

    public boolean consultaTajeta(String var1, long var2) throws ClaroException, RemoteException;

    public TarjetaCertificadoTO consultaSaldoCertificado(String var1) throws ClaroException, RemoteException;

    public List<MovimientoCertificadoTO> consultaMovimientosCertificado(String var1) throws ClaroException, RemoteException;

    public String consultaTajetaVendida(String var1, long var2) throws ClaroException, RemoteException;
}

