/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import com.claro.transfer.promociones.ErrorTO;

public class CtesExcelentesTO
extends ErrorTO {
    private String cuenta;
    private String linea;
    private String idRegion;
    private String estatus;

    public CtesExcelentesTO(String[] cteExc) {
        this.cuenta = cteExc[0];
        this.linea = cteExc[1];
        this.idRegion = cteExc[2];
        this.estatus = cteExc[3];
    }

    public CtesExcelentesTO() {
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String toString() {
        return String.valueOf(this.cuenta) + "," + this.linea + "," + this.idRegion + "," + this.estatus;
    }
}

