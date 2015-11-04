/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.gap;

import java.io.Serializable;

public class InfoPromocionGapTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String IdSecuencia;
    private String IdPromocion;
    private String versionPromocion;
    private String NombrePromocion;
    private String JustificacionPromocion;
    private String DescripcionPromocion;
    private String FechaInicio;
    private String FechaTermino;
    private String Familia;
    private String FechaAplicacionPromocion;
    private String TerminoStandBy;
    private String urlProducto;
    private String tecnologia;
    private String fzaVentaEntrega;
    private String aplicaEp;
    private String aplicaCA;

    public String getIdSecuencia() {
        return this.IdSecuencia;
    }

    public void setIdSecuencia(String idSecuencia) {
        this.IdSecuencia = idSecuencia;
    }

    public String getIdPromocion() {
        return this.IdPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.IdPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return this.NombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.NombrePromocion = nombrePromocion;
    }

    public String getJustificacionPromocion() {
        return this.JustificacionPromocion;
    }

    public void setJustificacionPromocion(String justificacionPromocion) {
        this.JustificacionPromocion = justificacionPromocion;
    }

    public String getDescripcionPromocion() {
        return this.DescripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.DescripcionPromocion = descripcionPromocion;
    }

    public String getFechaInicio() {
        return this.FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.FechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return this.FechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.FechaTermino = fechaTermino;
    }

    public String getFamilia() {
        return this.Familia;
    }

    public void setFamilia(String familia) {
        this.Familia = familia;
    }

    public String getFechaAplicacionPromocion() {
        return this.FechaAplicacionPromocion;
    }

    public void setFechaAplicacionPromocion(String fechaAplicacionPromocion) {
        this.FechaAplicacionPromocion = fechaAplicacionPromocion;
    }

    public String getTerminoStandBy() {
        return this.TerminoStandBy;
    }

    public void setTerminoStandBy(String terminoStandBy) {
        this.TerminoStandBy = terminoStandBy;
    }

    public String getUrlProducto() {
        return this.urlProducto;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getFzaVentaEntrega() {
        return this.fzaVentaEntrega;
    }

    public void setFzaVentaEntrega(String fzaVentaEntrega) {
        this.fzaVentaEntrega = fzaVentaEntrega;
    }

    public String getAplicaEp() {
        return this.aplicaEp;
    }

    public void setAplicaEp(String aplicaEp) {
        this.aplicaEp = aplicaEp;
    }

    public String getAplicaCA() {
        return this.aplicaCA;
    }

    public void setAplicaCA(String aplicaCA) {
        this.aplicaCA = aplicaCA;
    }

    public String getVersionPromocion() {
        return this.versionPromocion;
    }

    public void setVersionPromocion(String versionPromocion) {
        this.versionPromocion = versionPromocion;
    }
}

