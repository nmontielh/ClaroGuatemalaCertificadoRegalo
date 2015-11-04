/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

public class PuntosVencerTO {
    private String cuenta;
    private int secuencia;
    private String linea;
    private int region;
    private String fechaCad;
    private int puntosaCad;
    private int puntosaCad1;
    private int puntosaCad2;
    private int puntosAntiguedad;
    private int puntosExcedentes;
    private int puntosPromocion;
    private int puntosRenta;

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

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getFechaCad() {
        return this.fechaCad;
    }

    public void setFechaCad(String fechaCad) {
        this.fechaCad = fechaCad;
    }

    public int getPuntosaCad() {
        return this.puntosaCad;
    }

    public void setPuntosaCad(int puntosaCad) {
        this.puntosaCad = puntosaCad;
    }

    public int getPuntosaCad1() {
        return this.puntosaCad1;
    }

    public void setPuntosaCad1(int puntosaCad1) {
        this.puntosaCad1 = puntosaCad1;
    }

    public int getPuntosaCad2() {
        return this.puntosaCad2;
    }

    public void setPuntosaCad2(int puntosaCad2) {
        this.puntosaCad2 = puntosaCad2;
    }

    public int getPuntosAntiguedad() {
        return this.puntosAntiguedad;
    }

    public void setPuntosAntiguedad(int puntosAntiguedad) {
        this.puntosAntiguedad = puntosAntiguedad;
    }

    public int getPuntosExcedentes() {
        return this.puntosExcedentes;
    }

    public void setPuntosExcedentes(int puntosExcedentes) {
        this.puntosExcedentes = puntosExcedentes;
    }

    public int getPuntosPromocion() {
        return this.puntosPromocion;
    }

    public void setPuntosPromocion(int puntosPromocion) {
        this.puntosPromocion = puntosPromocion;
    }

    public int getPuntosRenta() {
        return this.puntosRenta;
    }

    public void setPuntosRenta(int puntosRenta) {
        this.puntosRenta = puntosRenta;
    }

    public int getTotales() {
        return this.puntosaCad + this.puntosaCad1 + this.puntosaCad2 + this.puntosAntiguedad + this.puntosExcedentes + this.puntosPromocion + this.puntosRenta;
    }
}

