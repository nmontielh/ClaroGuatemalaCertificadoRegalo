/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.distribuidores;

import com.claro.transfer.distribuidores.UsuarioPortalDist;
import java.math.BigDecimal;

public class LiberacionPortalDist
extends UsuarioPortalDist {
    private String pSobrantes = "0";
    private String bonoEquipo = "0";
    private String puntosAcum = "0";
    private String puntosDisp = "0";
    private String bonoConProrrateo = "0";
    private String comentario = "";
    private String secuencia = "";
    private String idProducto = "";
    public String puntosMin = "0";
    public String valorPtos = "0";
    public String valorPesos = "0";
    public String puntoVenta = "";
    public String folio = "";
    public long folioDecimal = 0;
    public String fzaVentas = "";
    public String region = "";
    private String idTransSisAct = "";
    private String idTransPtos = "";
    private String statusRes = "";
    private String statusPtos = "";
    private String fechaExp = "";
    public String ptosRenta = "0";
    public String ptosExc = "0";
    public String ptosACad = "0";
    public String ptosACad1 = "0";
    public String ptosACad2 = "0";
    public String fecACad = null;
    public String fecACad1 = null;
    public String fecACad2 = null;
    private String modelo = "";
    private String marca = "";
    private String descripcion = "";
    public String sPlanActual = "";
    public String sPlanNuevo = "";
    public String sAddActual = "";
    public String sFecAddActual = "";
    public String sAddNuevo = "";
    public String sPtosAcumAnt = "0";
    public String sPtosDispAnt = "0";
    public String sPtosAcumRest = "0";
    public String sPtosDispRest = "0";
    private BigDecimal precio;
    private BigDecimal precioIva;
    private String ptosPromocion = "0";
    private String ptosAntiguedad = "0";
    private int gbSaldoAnt;
    private int gbPtosMin;
    private String gbAddAnt = "";
    private String gbTipoRed = "";
    private String gbTipoProm = "";
    private int[] puntosConsumidos = new int[9];
    private int[] puntosAConsumir = new int[9];

    public long getFolioDecimal() {
        return this.folioDecimal;
    }

    public void setFolioDecimal(long folioDecimal) {
        this.folioDecimal = folioDecimal;
    }

    public String getPuntosAcum() {
        return this.puntosAcum;
    }

    public void setPuntosAcum(String _puntosAcum) {
        this.puntosAcum = _puntosAcum;
    }

    public String getPuntosDisp() {
        return this.puntosDisp;
    }

    public void setPuntosDisp(String _puntosDisp) {
        this.puntosDisp = _puntosDisp;
    }

    public String getBonoConProrrateo() {
        return this.bonoConProrrateo;
    }

    public void setBonoConProrrateo(String _bonoConProrrateo) {
        this.bonoConProrrateo = _bonoConProrrateo;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String _secuencia) {
        this.secuencia = _secuencia;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String _idProducto) {
        this.idProducto = _idProducto;
    }

    public String getPuntosMin() {
        return this.puntosMin;
    }

    public void setPuntosMin(String _puntosMin) {
        this.puntosMin = _puntosMin;
    }

    public String getValorPtos() {
        return this.valorPtos;
    }

    public void setValorPtos(String _valorPtos) {
        this.valorPtos = _valorPtos;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String _comentario) {
        this.comentario = _comentario;
    }

    public String getFzaVentas() {
        return this.fzaVentas;
    }

    public void setFzaVentas(String _fzaVentas) {
        this.fzaVentas = _fzaVentas;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String _folio) {
        this.folio = _folio;
    }

    public String getPuntoVenta() {
        return this.puntoVenta;
    }

    public void setPuntoVenta(String _puntoVenta) {
        this.puntoVenta = _puntoVenta;
    }

    public String getValorPesos() {
        return this.valorPesos;
    }

    public void setValorPesos(String _valorPesos) {
        this.valorPesos = _valorPesos;
    }

    @Override
    public String getRegion() {
        return this.region;
    }

    @Override
    public void setRegion(String _region) {
        this.region = _region;
    }

    public String getTransSis() {
        return this.idTransSisAct;
    }

    public void setTransSis(String _idTransSisAct) {
        this.idTransSisAct = _idTransSisAct;
    }

    public String getTransPtos() {
        return this.idTransPtos;
    }

    public void setTransPtos(String _idTransPtos) {
        this.idTransPtos = _idTransPtos;
    }

    public String getStatusRes() {
        return this.statusRes;
    }

    public void setStatusRes(String _statusRes) {
        this.statusRes = _statusRes;
    }

    public String getFechaExp() {
        return this.fechaExp;
    }

    public void setFechaExp(String _fechaExp) {
        this.fechaExp = _fechaExp;
    }

    public String getPuntosRenta() {
        return this.ptosRenta;
    }

    public void setPuntosRenta(String _ptosRenta) {
        this.ptosRenta = _ptosRenta;
    }

    public String getPuntosExc() {
        return this.ptosExc;
    }

    public void setPuntosExc(String _ptosExc) {
        this.ptosExc = _ptosExc;
    }

    public String getPtosACad() {
        return this.ptosACad;
    }

    public void setPtosACad(String _ptosACad) {
        this.ptosACad = _ptosACad;
    }

    public String getPtosACad1() {
        return this.ptosACad1;
    }

    public void setPtosACad1(String _ptosACad1) {
        this.ptosACad1 = _ptosACad1;
    }

    public String getPtosACad2() {
        return this.ptosACad2;
    }

    public void setPtosACad2(String _ptosACad2) {
        this.ptosACad2 = _ptosACad2;
    }

    public String getFechaCad() {
        return this.fecACad;
    }

    public void setFechaCad(String _fecACad) {
        this.fecACad = _fecACad;
    }

    public String getFechaCad1() {
        return this.fecACad1;
    }

    public void setFechaCad1(String _fecACad1) {
        this.fecACad1 = _fecACad1;
    }

    public String getFechaCad2() {
        return this.fecACad2;
    }

    public void setFechaCad2(String _fecACad2) {
        this.fecACad2 = _fecACad2;
    }

    public void setModelo(String _modelo) {
        this.modelo = _modelo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setMarca(String _marca) {
        this.marca = _marca;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this.descripcion = _descripcion;
    }

    public String getPtosPromocion() {
        return this.ptosPromocion;
    }

    public void setPtosPromocion(String _ptosPromocion) {
        this.ptosPromocion = _ptosPromocion;
    }

    public String getPtosAntiguedad() {
        return this.ptosAntiguedad;
    }

    public void setPtosAntiguedad(String _ptosAntiguedad) {
        this.ptosAntiguedad = _ptosAntiguedad;
    }

    public String getStatusPtos() {
        return this.statusPtos;
    }

    public void setStatusPtos(String _statusPtos) {
        this.statusPtos = _statusPtos;
    }

    public String getPlanActual() {
        return this.sPlanActual;
    }

    public String getPlanNuevo() {
        return this.sPlanNuevo;
    }

    public String getAddActual() {
        return this.sAddActual;
    }

    public String getFecAddActual() {
        return this.sFecAddActual;
    }

    public String getAddNuevo() {
        return this.sAddNuevo;
    }

    public String getPtosAcumAnt() {
        return this.sPtosAcumAnt;
    }

    public String getPtosDispAnt() {
        return this.sPtosDispAnt;
    }

    public String getPtosAcumRest() {
        return this.sPtosAcumRest;
    }

    public String getPtosDispRest() {
        return this.sPtosDispRest;
    }

    public void setPlanActual(String _sPlanActual) {
        this.sPlanActual = _sPlanActual;
    }

    public void setPlanNuevo(String _sPlanNuevo) {
        this.sPlanNuevo = _sPlanNuevo;
    }

    public void setAddActual(String _sAddActual) {
        this.sAddActual = _sAddActual;
    }

    public void setFecAddActual(String _sFecAddActual) {
        this.sFecAddActual = _sFecAddActual;
    }

    public void setAddNuevo(String _sAddNuevo) {
        this.sAddNuevo = _sAddNuevo;
    }

    public void setPtosAcumAnt(String _sPtosAcumAnt) {
        this.sPtosAcumAnt = _sPtosAcumAnt;
    }

    public void setPtosDispAnt(String _sPtosDispAnt) {
        this.sPtosDispAnt = _sPtosDispAnt;
    }

    public void setPtosAcumRest(String _sPtosAcumRest) {
        this.sPtosAcumRest = _sPtosAcumRest;
    }

    public void setPtosDispRest(String _sPtosDispRest) {
        this.sPtosDispRest = _sPtosDispRest;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecioIva() {
        return this.precioIva;
    }

    public void setPrecioIva(BigDecimal precioIva) {
        this.precioIva = precioIva;
    }

    public String getBonoEquipo() {
        return this.bonoEquipo;
    }

    public void setBonoEquipo(String bonoEquipo) {
        this.bonoEquipo = bonoEquipo;
    }

    public String getPSobrantes() {
        return this.pSobrantes;
    }

    public void setPSobrantes(String sobrantes) {
        this.pSobrantes = sobrantes;
    }

    public int getGbSaldoAnt() {
        return this.gbSaldoAnt;
    }

    public void setGbSaldoAnt(int gbSaldoAnt) {
        this.gbSaldoAnt = gbSaldoAnt;
    }

    public int getGbPtosMin() {
        return this.gbPtosMin;
    }

    public void setGbPtosMin(int gbPtosMin) {
        this.gbPtosMin = gbPtosMin;
    }

    public String getGbAddAnt() {
        return this.gbAddAnt;
    }

    public void setGbAddAnt(String gbAddAnt) {
        this.gbAddAnt = gbAddAnt;
    }

    public String getGbTipoRed() {
        return this.gbTipoRed;
    }

    public void setGbTipoRed(String gbTipoRed) {
        this.gbTipoRed = gbTipoRed;
    }

    public String getGbTipoProm() {
        return this.gbTipoProm;
    }

    public void setGbTipoProm(String gbTipoProm) {
        this.gbTipoProm = gbTipoProm;
    }

    public int[] getPuntosConsumidos() {
        return this.puntosConsumidos;
    }

    public void setPuntosConsumidos(int[] puntosConsumidos) {
        this.puntosConsumidos = puntosConsumidos;
    }

    public int[] getPuntosAConsumir() {
        return this.puntosAConsumir;
    }

    public void setPuntosAConsumir(int[] puntosAConsumir) {
        this.puntosAConsumir = puntosAConsumir;
    }
}

