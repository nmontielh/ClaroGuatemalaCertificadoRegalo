/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.MovimientoTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoServiceTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String cuenta;
    private String secuencia;
    private String linea;
    private String fechaOperacion;
    private String facturacion;
    private String usuario;
    private String movimiento;
    private int numPuntos;
    private int numPuntosExc;
    private int totalAjustes;
    private String bonoPromocion;
    private String referencia;
    private String fechaAdendum;

    public MovimientoServiceTO() {
    }

    public MovimientoServiceTO(MovimientoTO movimientoTO) {
        this.cuenta = movimientoTO.getCuenta();
        this.secuencia = movimientoTO.getSecuencia();
        this.linea = movimientoTO.getLinea();
        this.fechaOperacion = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFechaOperacion());
        this.facturacion = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFacturacion());
        this.usuario = movimientoTO.getUsuario();
        this.movimiento = movimientoTO.getMovimiento();
        this.numPuntos = movimientoTO.getNumPuntos();
        this.numPuntosExc = movimientoTO.getNumPuntosExc();
        this.totalAjustes = movimientoTO.getTotalAjustes();
        this.bonoPromocion = movimientoTO.getBonoPromocion();
        this.referencia = movimientoTO.getReferencia();
        this.fechaAdendum = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFechaAdendum());
    }

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

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getFacturacion() {
        return this.facturacion;
    }

    public void setFacturacion(String facturacion) {
        this.facturacion = facturacion;
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

    public String getFechaAdendum() {
        return this.fechaAdendum;
    }

    public void setFechaAdendum(String fechaAdendum) {
        this.fechaAdendum = fechaAdendum;
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
}

