/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;

public class DireccionTO
extends MensajeTO {
    private static final long serialVersionUID = 2286769544666355038L;
    private long fechaOperacion;
    private String cuenta;
    private int secuencia;
    private String linea;
    private int tipoEntrega;
    private String nombreTitular;
    private String nombreContacto;
    private String direccion;
    private String colonia;
    private String callles;
    private String estado;
    private int codigoPostal;
    private String usuario;
    private String idReferencia;

    public long getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(long fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public int getTipoEntrega() {
        return this.tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getNombreTitular() {
        return this.nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNombreContacto() {
        return this.nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCallles() {
        return this.callles;
    }

    public void setCallles(String callles) {
        this.callles = callles;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdReferencia() {
        return this.idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }
}

