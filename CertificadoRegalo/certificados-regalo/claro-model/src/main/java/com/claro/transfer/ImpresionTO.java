/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.AlianzasTO;
import com.claro.transfer.MensajeTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ImpresionTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -2650842559951836887L;
    private String idPuntoVenta;
    private String planAnt;
    private String planNuevo;
    private String tipoReden;
    private String formaReden;
    private String idProducto;
    private String descripcion;
    private String comentario;
    private String marca;
    private String modelo;
    private String esnImeiR;
    private String esnImeiP;
    private String iccid;
    private String folio;
    private String idUsuario;
    private String telefono;
    private String cuenta;
    private String cuentaAlianza;
    private String archivoSalida;
    private String precioFormato;
    private String descuentoFormato;
    private String precioIvaFormato;
    private String tipoRedenDB;
    private String ptosDispCF;
    private String ptsDispRestantesCF;
    private String valorPuntosCF;
    private String ptsMinimosCF;
    private String ptsAcumCF;
    private String ptsAcumResCF;
    private String ptsTransferidosCF;
    private String ptsMillasCF;
    private String ptsCanjeadoCF;
    private String tipoAlianza;
    private String fechaFAlianza;
    private String idReferencia;
    private int addAnt;
    private int ptosDisp;
    private int valorPuntos;
    private int ptsDispRestantes;
    private int addNuevo;
    private int ptsMinimos;
    private int bonoProrr;
    private int ptsAcum;
    private int ptsAcumRes;
    private int idRegion;
    private int ptsTransferidos;
    private int valorCuponOrig;
    private int secuencia;
    private int opcion;
    private int ptsCanjeado;
    private int ptsMillas;
    private int bonosRoext;
    private int bonosAltoValor;
    private int bonosGap;
    private String inbursaFormato;
    private String marcaFormato;
    private String folioRedencion;
    private long lFechaFolio;
    private long fechaF;
    private Date fechaPlazoAnt;
    private Date fechaOperacion;
    private Date fechaPlazoSeg;
    private Timestamp fechaFolio;
    private BigDecimal precio;
    private BigDecimal descuento;
    private BigDecimal precioIva;
    private ArrayList<AlianzasTO> alianzas;

    public long getFechaF() {
        if (this.fechaFolio != null) {
            return this.fechaFolio.getTime();
        }
        return this.fechaF;
    }

    public void setFechaF(long fechaF) {
        this.fechaF = fechaF;
    }

    public long getLFechaFolio() {
        if (this.fechaFolio != null) {
            return this.fechaFolio.getTime();
        }
        return this.lFechaFolio;
    }

    public void setLFechaFolio(long fechaFolio) {
        this.lFechaFolio = fechaFolio;
    }

    public ArrayList<AlianzasTO> getAlianzas() {
        return this.alianzas;
    }

    public void setAlianzas(ArrayList<AlianzasTO> alianzas) {
        this.alianzas = alianzas;
    }

    public String getIdPuntoVenta() {
        return this.idPuntoVenta;
    }

    public void setIdPuntoVenta(String idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

    public String getPlanAnt() {
        return this.planAnt;
    }

    public void setPlanAnt(String planAnt) {
        this.planAnt = planAnt;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public void setPlanNuevo(String planNuevo) {
        this.planNuevo = planNuevo;
    }

    public String getTipoReden() {
        return this.tipoReden;
    }

    public void setTipoReden(String tipoReden) {
        this.tipoReden = tipoReden;
    }

    public String getFormaReden() {
        return this.formaReden;
    }

    public void setFormaReden(String formaReden) {
        this.formaReden = formaReden;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEsnImeiR() {
        return this.esnImeiR;
    }

    public void setEsnImeiR(String esnImeiR) {
        this.esnImeiR = esnImeiR;
    }

    public String getEsnImeiP() {
        return this.esnImeiP;
    }

    public void setEsnImeiP(String esnImeiP) {
        this.esnImeiP = esnImeiP;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getAddAnt() {
        return this.addAnt;
    }

    public void setAddAnt(int addAnt) {
        this.addAnt = addAnt;
    }

    public int getPtosDisp() {
        return this.ptosDisp;
    }

    public void setPtosDisp(int ptosDisp) {
        this.ptosDisp = ptosDisp;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public void setValorPuntos(int valorPuntos) {
        this.valorPuntos = valorPuntos;
    }

    public int getPtsDispRestantes() {
        return this.ptsDispRestantes;
    }

    public void setPtsDispRestantes(int ptsDispRestantes) {
        this.ptsDispRestantes = ptsDispRestantes;
    }

    public int getAddNuevo() {
        return this.addNuevo;
    }

    public void setAddNuevo(int addNuevo) {
        this.addNuevo = addNuevo;
    }

    public int getPtsMinimos() {
        return this.ptsMinimos;
    }

    public void setPtsMinimos(int ptsMinimos) {
        this.ptsMinimos = ptsMinimos;
    }

    public int getBonoProrr() {
        return this.bonoProrr;
    }

    public void setBonoProrr(int bonoProrr) {
        this.bonoProrr = bonoProrr;
    }

    public int getPtsAcum() {
        return this.ptsAcum;
    }

    public void setPtsAcum(int ptsAcum) {
        this.ptsAcum = ptsAcum;
    }

    public int getPtsAcumRes() {
        return this.ptsAcumRes;
    }

    public void setPtsAcumRes(int ptsAcumRes) {
        this.ptsAcumRes = ptsAcumRes;
    }

    public Date getFechaPlazoAnt() {
        return this.fechaPlazoAnt;
    }

    public void setFechaPlazoAnt(Date fechaPlazoAnt) {
        this.fechaPlazoAnt = fechaPlazoAnt;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Date getFechaPlazoSeg() {
        return this.fechaPlazoSeg;
    }

    public void setFechaPlazoSeg(Date fechaPlazoSeg) {
        this.fechaPlazoSeg = fechaPlazoSeg;
    }

    public Timestamp getFechaFolio() {
        return this.fechaFolio;
    }

    public void setFechaFolio(Timestamp fechaFolio) {
        this.fechaFolio = fechaFolio;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getDescuento() {
        return this.descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getPrecioIva() {
        return this.precioIva;
    }

    public void setPrecioIva(BigDecimal precioIva) {
        this.precioIva = precioIva;
    }

    public int getSumaFormaRedencion() {
        return this.ptsMinimos + this.bonoProrr;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getPtsTransferidos() {
        return this.ptsTransferidos;
    }

    public void setPtsTransferidos(int ptsTransferidos) {
        this.ptsTransferidos = ptsTransferidos;
    }

    public int getValorCuponOrig() {
        return this.valorCuponOrig;
    }

    public void setValorCuponOrig(int valorCuponOrig) {
        this.valorCuponOrig = valorCuponOrig;
    }

    public String getCuentaAlianza() {
        return this.cuentaAlianza;
    }

    public void setCuentaAlianza(String cuentaAlianza) {
        this.cuentaAlianza = cuentaAlianza;
    }

    public String getArchivoSalida() {
        return this.archivoSalida;
    }

    public void setArchivoSalida(String archivoSalida) {
        this.archivoSalida = archivoSalida;
    }

    public int getPtsCanjeado() {
        return this.ptsCanjeado;
    }

    public void setPtsCanjeado(int ptsCanjeado) {
        this.ptsCanjeado = ptsCanjeado;
    }

    public int getPtsMillas() {
        return this.ptsMillas;
    }

    public void setPtsMillas(int ptsMillas) {
        this.ptsMillas = ptsMillas;
    }

    public String getPrecioFormato() {
        return this.precioFormato;
    }

    public void setPrecioFormato(String precioFormato) {
        this.precioFormato = precioFormato;
    }

    public String getDescuentoFormato() {
        return this.descuentoFormato;
    }

    public void setDescuentoFormato(String descuentoFormato) {
        this.descuentoFormato = descuentoFormato;
    }

    public String getPrecioIvaFormato() {
        return this.precioIvaFormato;
    }

    public void setPrecioIvaFormato(String precioIvaFormato) {
        this.precioIvaFormato = precioIvaFormato;
    }

    public String getTipoRedenDB() {
        return this.tipoRedenDB;
    }

    public void setTipoRedenDB(String tipoRedenDB) {
        this.tipoRedenDB = tipoRedenDB;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getOpcion() {
        return this.opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public String getPtosDispCF() {
        return this.ptosDispCF;
    }

    public void setPtosDispCF(String ptosDispCF) {
        this.ptosDispCF = ptosDispCF;
    }

    public String getPtsDispRestantesCF() {
        return this.ptsDispRestantesCF;
    }

    public void setPtsDispRestantesCF(String ptsDispRestantesCF) {
        this.ptsDispRestantesCF = ptsDispRestantesCF;
    }

    public String getValorPuntosCF() {
        return this.valorPuntosCF;
    }

    public void setValorPuntosCF(String valorPuntosCF) {
        this.valorPuntosCF = valorPuntosCF;
    }

    public String getPtsMinimosCF() {
        return this.ptsMinimosCF;
    }

    public void setPtsMinimosCF(String ptsMinimosCF) {
        this.ptsMinimosCF = ptsMinimosCF;
    }

    public String getPtsAcumCF() {
        return this.ptsAcumCF;
    }

    public void setPtsAcumCF(String ptsAcumCF) {
        this.ptsAcumCF = ptsAcumCF;
    }

    public String getPtsAcumResCF() {
        return this.ptsAcumResCF;
    }

    public void setPtsAcumResCF(String ptsAcumResCF) {
        this.ptsAcumResCF = ptsAcumResCF;
    }

    public String getPtsTransferidosCF() {
        return this.ptsTransferidosCF;
    }

    public void setPtsTransferidosCF(String ptsTransferidosCF) {
        this.ptsTransferidosCF = ptsTransferidosCF;
    }

    public String getPtsMillasCF() {
        return this.ptsMillasCF;
    }

    public void setPtsMillasCF(String ptsMillasCF) {
        this.ptsMillasCF = ptsMillasCF;
    }

    public String getPtsCanjeadoCF() {
        return this.ptsCanjeadoCF;
    }

    public void setPtsCanjeadoCF(String ptsCanjeadoCF) {
        this.ptsCanjeadoCF = ptsCanjeadoCF;
    }

    public static long getSerialVersionUID() {
        return -2650842559951836887L;
    }

    public String getTipoAlianza() {
        return this.tipoAlianza;
    }

    public void setTipoAlianza(String tipoAlianza) {
        this.tipoAlianza = tipoAlianza;
    }

    public String getFechaFAlianza() {
        return this.fechaFAlianza;
    }

    public void setFechaFAlianza(String fechaFAlianza) {
        this.fechaFAlianza = fechaFAlianza;
    }

    public String getIdReferencia() {
        return this.idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public int getBonosRoext() {
        return this.bonosRoext;
    }

    public void setBonosRoext(int bonosRoext) {
        this.bonosRoext = bonosRoext;
    }

    public int getBonosAltoValor() {
        return this.bonosAltoValor;
    }

    public void setBonosAltoValor(int bonosAltoValor) {
        this.bonosAltoValor = bonosAltoValor;
    }

    public int getBonosGap() {
        return this.bonosGap;
    }

    public void setBonosGap(int bonosGap) {
        this.bonosGap = bonosGap;
    }

    public String getInbursaFormato() {
        return this.inbursaFormato;
    }

    public void setInbursaFormato(String inbursaFormato) {
        this.inbursaFormato = inbursaFormato;
    }

    public String getMarcaFormato() {
        return this.marcaFormato;
    }

    public void setMarcaFormato(String marcaFormato) {
        this.marcaFormato = marcaFormato;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFolioRedencion() {
        return this.folioRedencion;
    }

    public void setFolioRedencion(String folioRedencion) {
        this.folioRedencion = folioRedencion;
    }
}

