/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.ImpresionTO;
import com.claro.transfer.MensajeTO;
import com.claro.util.Constantes;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImpresionServiceTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1815031627280032289L;
    private String idPuntoVenta;
    private String planAnterior;
    private String fechaPlazoAnterior;
    private String fechaOperacion;
    private String planNuevo;
    private String fechaPlazoSeguro;
    private String fechaFolio;
    private String tipoRedencion;
    private String formaRedencion;
    private String idProducto;
    private String descripcion;
    private String marca;
    private String modelo;
    private String precio;
    private String precioIva;
    private String comentario;
    private String esnImeiR;
    private String esnImeiP;
    private String iccid;
    private String folio;
    private String descuento;
    private String ptsDispRestantesCF;
    private String valorPuntosCF;
    private int addendumAnterior;
    private int puntosDisponibles;
    private int valorPuntos;
    private int puntosDispRestantes;
    private int addendumNuevo;
    private int puntosminimos;
    private int bonoProrrateo;
    private int puntosAcumulados;
    private int puntosAcumuladosRestantes;
    private int bonosAltoValor;
    private int bonosRoext;
    private int bonosGap;

    public ImpresionServiceTO(ImpresionTO impresionTO) {
        this.agregaMensaje(impresionTO.getIdMensaje(), impresionTO.getMensaje());
        this.idPuntoVenta = impresionTO.getIdPuntoVenta();
        this.planAnterior = impresionTO.getPlanAnt();
        this.addendumAnterior = impresionTO.getAddAnt();
        this.fechaPlazoAnterior = impresionTO.getFechaPlazoAnt() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaPlazoAnt());
        this.fechaOperacion = impresionTO.getFechaOperacion() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaOperacion());
        this.puntosDisponibles = impresionTO.getPtosDisp();
        this.valorPuntos = impresionTO.getValorPuntos();
        this.puntosDispRestantes = impresionTO.getPtsDispRestantes();
        this.planNuevo = impresionTO.getPlanNuevo();
        this.addendumNuevo = impresionTO.getAddNuevo();
        this.fechaPlazoSeguro = impresionTO.getFechaPlazoSeg() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaPlazoSeg());
        this.fechaFolio = impresionTO.getFechaFolio() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaFolio());
        this.tipoRedencion = impresionTO.getTipoReden();
        this.formaRedencion = impresionTO.getFormaReden();
        this.idProducto = impresionTO.getIdProducto();
        this.descripcion = impresionTO.getDescripcion();
        this.marca = impresionTO.getMarca();
        this.modelo = impresionTO.getModelo();
        this.precio = impresionTO.getPrecioFormato() != null ? impresionTO.getPrecioFormato() : "";
        this.descuento = impresionTO.getDescuentoFormato() != null ? impresionTO.getDescuentoFormato() : "";
        this.precioIva = impresionTO.getPrecioIvaFormato() != null ? impresionTO.getPrecioIvaFormato() : "";
        this.comentario = impresionTO.getComentario();
        this.esnImeiR = impresionTO.getEsnImeiR();
        this.esnImeiP = impresionTO.getEsnImeiP();
        this.iccid = impresionTO.getIccid();
        this.puntosminimos = impresionTO.getPtsMinimos();
        this.bonoProrrateo = impresionTO.getBonoProrr();
        this.puntosAcumulados = impresionTO.getPtsAcum();
        this.puntosAcumuladosRestantes = impresionTO.getPtsAcumRes();
        this.folio = impresionTO.getFolio();
        this.ptsDispRestantesCF = impresionTO.getPtsDispRestantesCF();
        this.valorPuntosCF = impresionTO.getValorPuntosCF();
        this.bonosAltoValor = impresionTO.getBonosAltoValor();
        this.bonosRoext = impresionTO.getBonosRoext();
        this.bonosGap = impresionTO.getBonosGap();
    }

    public ImpresionServiceTO() {
    }

    public String getIdPuntoVenta() {
        return this.idPuntoVenta;
    }

    public String getPlanAnterior() {
        return this.planAnterior;
    }

    public String getFechaPlazoAnterior() {
        return this.fechaPlazoAnterior;
    }

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public String getFechaPlazoSeguro() {
        return this.fechaPlazoSeguro;
    }

    public String getFechaFolio() {
        return this.fechaFolio;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public String getFormaRedencion() {
        return this.formaRedencion;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getPrecio() {
        return this.precio;
    }

    public String getPrecioIva() {
        return this.precioIva;
    }

    public String getComentario() {
        return this.comentario;
    }

    public String getEsnImeiR() {
        return this.esnImeiR;
    }

    public String getEsnImeiP() {
        return this.esnImeiP;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getFolio() {
        return this.folio;
    }

    public String getDescuento() {
        return this.descuento;
    }

    public int getAddendumAnterior() {
        return this.addendumAnterior;
    }

    public int getPuntosDisponibles() {
        return this.puntosDisponibles;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public int getPuntosDispRestantes() {
        return this.puntosDispRestantes;
    }

    public int getAddendumNuevo() {
        return this.addendumNuevo;
    }

    public int getPuntosminimos() {
        return this.puntosminimos;
    }

    public int getBonoProrrateo() {
        return this.bonoProrrateo;
    }

    public int getPuntosAcumulados() {
        return this.puntosAcumulados;
    }

    public int getPuntosAcumuladosRestantes() {
        return this.puntosAcumuladosRestantes;
    }

    public String getPtsDispRestantesCF() {
        return this.ptsDispRestantesCF;
    }

    public String getValorPuntosCF() {
        return this.valorPuntosCF;
    }

    public int getBonosAltoValor() {
        return this.bonosAltoValor;
    }

    public void setBonosAltoValor(int bonosAltoValor) {
        this.bonosAltoValor = bonosAltoValor;
    }

    public int getBonosRoext() {
        return this.bonosRoext;
    }

    public void setBonosRoext(int bonosRoext) {
        this.bonosRoext = bonosRoext;
    }

    public int getBonosGap() {
        return this.bonosGap;
    }

    public void setBonosGap(int bonosGap) {
        this.bonosGap = bonosGap;
    }
}

