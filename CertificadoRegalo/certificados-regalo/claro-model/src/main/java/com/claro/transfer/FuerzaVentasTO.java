/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class FuerzaVentasTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -859845217988431256L;
    private String idFuerzaVenta;
    private String planVisible;
    private String estatus;
    private String descripcion;

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
}

