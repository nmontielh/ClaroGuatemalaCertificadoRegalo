/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.certificados;

public class ActivacionTarjetaCertificadoTO {
    private String numeroCertificado;
    private String numeroTarjeta;
    private long montoCertificado;
    private String fechaActivacion;
    private String fechaExpiracion;
    private String estatus;
    private String idMensaje;
    private String mensaje;

    public String getNumeroCertificado() {
        return this.numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public String getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public long getMontoCertificado() {
        return this.montoCertificado;
    }

    public void setMontoCertificado(long montoCertificado) {
        this.montoCertificado = montoCertificado;
    }

    public String getFechaActivacion() {
        return this.fechaActivacion;
    }

    public void setFechaActivacion(String fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public String getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getIdMensaje() {
        return this.idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

