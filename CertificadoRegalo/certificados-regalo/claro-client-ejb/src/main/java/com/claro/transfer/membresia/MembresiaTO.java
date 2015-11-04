/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.membresia;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;
import java.util.Date;

public class MembresiaTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -42109309820494988L;
    private String cuenta;
    private String nombreM2K;
    private String fechaAltaM2K;
    private String telefono;
    private String segmento;
    private String plan;
    private String descPlan;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String tipoCalle;
    private String calle;
    private String numExterior;
    private String numInterior;
    private String colonia;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    private String usuario;
    private String estatus;
    private String motivo;
    private int costo;
    private int secuencia;
    private int region;
    private Date fechaOperacion;

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombreM2K() {
        return this.nombreM2K;
    }

    public void setNombreM2K(String nombreM2K) {
        this.nombreM2K = nombreM2K;
    }

    public String getFechaAltaM2K() {
        return this.fechaAltaM2K;
    }

    public void setFechaAltaM2K(String fechaAltaM2K) {
        this.fechaAltaM2K = fechaAltaM2K;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSegmento() {
        return this.segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getDescPlan() {
        return this.descPlan;
    }

    public void setDescPlan(String descPlan) {
        this.descPlan = descPlan;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return this.apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return this.apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getTipoCalle() {
        return this.tipoCalle;
    }

    public void setTipoCalle(String tipoCalle) {
        this.tipoCalle = tipoCalle;
    }

    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumExterior() {
        return this.numExterior;
    }

    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    public String getNumInterior() {
        return this.numInterior;
    }

    public void setNumInterior(String numInterior) {
        this.numInterior = numInterior;
    }

    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getCosto() {
        return this.costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String toString() {
        return String.valueOf(this.apMaterno) + "|" + this.apPaterno + "|" + this.calle + "|" + this.ciudad + "|" + this.codigoPostal + "|" + this.colonia + "|" + this.costo + "|" + this.cuenta + "|" + this.descPlan + "|" + this.estado + "|" + this.estatus + "|" + this.fechaAltaM2K + "|" + this.motivo + "|" + this.nombre + "|" + this.nombreM2K + "|" + this.numExterior + "|" + this.numInterior + "|" + this.pais + "|" + this.plan + "|" + this.region + "|" + this.secuencia + "|" + this.segmento + "|" + this.telefono + "|" + this.usuario + "|";
    }
}

