/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.CuentaPadreTO;
import java.sql.Date;

public class LineasTO {
    private String cuenta;
    private Integer secuencia;
    private String ctaPadre;
    private String linea;
    private Integer idRegion;
    private String plan;
    private String estatusTel;
    private Integer cicloFact;
    private Integer addendum;
    private Date fechaAdd;
    private Date fechaAlta;
    private Date fechaAnt;
    private String sistema;
    private String estatusPuntos;
    private String estatusCarta;
    private String anacr;
    private CuentaPadreTO cuentaPadre;

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getCtaPadre() {
        return this.ctaPadre;
    }

    public void setCtaPadre(String ctaPadre) {
        this.ctaPadre = ctaPadre;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Integer getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getEstatusTel() {
        return this.estatusTel;
    }

    public void setEstatusTel(String estatusTel) {
        this.estatusTel = estatusTel;
    }

    public Integer getCicloFact() {
        return this.cicloFact;
    }

    public void setCicloFact(Integer cicloFact) {
        this.cicloFact = cicloFact;
    }

    public Integer getAddendum() {
        return this.addendum;
    }

    public void setAddendum(Integer addendum) {
        this.addendum = addendum;
    }

    public Date getFechaAdd() {
        return this.fechaAdd;
    }

    public void setFechaAdd(Date fechaAdd) {
        this.fechaAdd = fechaAdd;
    }

    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaAnt() {
        return this.fechaAnt;
    }

    public void setFechaAnt(Date fechaAnt) {
        this.fechaAnt = fechaAnt;
    }

    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getEstatusPuntos() {
        return this.estatusPuntos;
    }

    public void setEstatusPuntos(String estatusPuntos) {
        this.estatusPuntos = estatusPuntos;
    }

    public String getEstatusCarta() {
        return this.estatusCarta;
    }

    public void setEstatusCarta(String estatusCarta) {
        this.estatusCarta = estatusCarta;
    }

    public String getAnacr() {
        return this.anacr;
    }

    public void setAnacr(String anacr) {
        this.anacr = anacr;
    }

    public CuentaPadreTO getCuentaPadre() {
        return this.cuentaPadre;
    }

    public void setCuentaPadre(CuentaPadreTO cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }
}

