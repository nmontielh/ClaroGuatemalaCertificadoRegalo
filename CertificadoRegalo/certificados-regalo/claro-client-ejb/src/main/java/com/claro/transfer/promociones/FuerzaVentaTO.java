/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import com.claro.transfer.promociones.ErrorTO;

public class FuerzaVentaTO
extends ErrorTO {
    private String idFuerzaVenta;
    private String planVisible;
    private String estatus;
    private String descripcion;

    public FuerzaVentaTO(String[] fza) {
        this.idFuerzaVenta = fza[0];
        this.planVisible = fza[1];
        this.estatus = fza[2];
        this.descripcion = fza[3];
    }

    public FuerzaVentaTO() {
    }

    public String getIdFuerzaVenta() {
        return this.idFuerzaVenta;
    }

    public void setIdFuerzaVenta(String idFuerzaVenta) {
        this.idFuerzaVenta = idFuerzaVenta;
    }

    public String getPlanVisible() {
        return this.planVisible;
    }

    public void setPlanVisible(String planVisible) {
        this.planVisible = planVisible;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return String.valueOf(this.idFuerzaVenta) + "," + this.planVisible + "," + this.estatus + "," + this.descripcion;
    }
}

