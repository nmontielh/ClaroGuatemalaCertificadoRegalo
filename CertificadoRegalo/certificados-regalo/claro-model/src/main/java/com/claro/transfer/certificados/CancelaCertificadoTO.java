/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.certificados;

public class CancelaCertificadoTO {
    private String numeroTarjeta;
    private String numeroCertificado;
    private float saldoinicial;
    private float saldo;
    private String fechaActivacion;
    private String fechaExpiracion;
    private String estatusCertificado;
    private String estatusTarjeta;

    public String getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroCertificado() {
        return this.numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public float getSaldoinicial() {
        return this.saldoinicial;
    }

    public void setSaldoinicial(float saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
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

    public String getEstatusCertificado() {
        return this.estatusCertificado;
    }

    public void setEstatusCertificado(String estatusCertificado) {
        this.estatusCertificado = estatusCertificado;
    }

    public String getEstatusTarjeta() {
        return this.estatusTarjeta;
    }

    public void setEstatusTarjeta(String estatusTarjeta) {
        this.estatusTarjeta = estatusTarjeta;
    }
}

