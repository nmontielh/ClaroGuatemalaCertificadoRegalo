/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.certificados;

public class CancelaMovimientoTO {
    private String numeroTarjeta;
    private String numeroCertificado;
    private float saldo;
    private String fechaOperacion;
    private String estatusMovto;

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

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getEstatusMovto() {
        return this.estatusMovto;
    }

    public void setEstatusMovto(String estatusMovto) {
        this.estatusMovto = estatusMovto;
    }
}

