/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;
import java.sql.Date;

public class MovimientoTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String cuenta;
    private String secuencia;
    private String linea;
    private Date fechaOperacion;
    private Date facturacion;
    private String usuario;
    private String movimiento;
    private int numPuntos;
    private int numPuntosExc;
    private int totalAjustes;
    private String bonoPromocion;
    private String referencia;
    private Date fechaAdendum;

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMovimiento() {
        return this.movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getBonoPromocion() {
        return this.bonoPromocion;
    }

    public void setBonoPromocion(String bonoPromocion) {
        this.bonoPromocion = bonoPromocion;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Date getFacturacion() {
        return this.facturacion;
    }

    public void setFacturacion(Date facturacion) {
        this.facturacion = facturacion;
    }

    public int getNumPuntos() {
        return this.numPuntos;
    }

    public void setNumPuntos(int numPuntos) {
        this.numPuntos = numPuntos;
    }

    public int getNumPuntosExc() {
        return this.numPuntosExc;
    }

    public void setNumPuntosExc(int numPuntosExc) {
        this.numPuntosExc = numPuntosExc;
    }

    public int getTotalAjustes() {
        return this.totalAjustes;
    }

    public void setTotalAjustes(int totalAjustes) {
        this.totalAjustes = totalAjustes;
    }

    public Date getFechaAdendum() {
        return this.fechaAdendum;
    }

    public void setFechaAdendum(Date fechaAdendum) {
        this.fechaAdendum = fechaAdendum;
    }
}

