/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.distribuidores;

public class UsuarioPortalDist {
    private String telefono = "";
    private String cuenta = "";
    private String region = "";
    private String nombre = "";
    private String claveSisact = "";
    private String cuentaPadre = "";

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono.trim();
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta.trim();
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuentaPadre() {
        return this.cuentaPadre;
    }

    public void setCuentaPadre(String cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }

    public String getClaveSisact() {
        return this.claveSisact;
    }

    public void setClaveSisact(String claveSisact) {
        this.claveSisact = claveSisact.trim();
    }
}

