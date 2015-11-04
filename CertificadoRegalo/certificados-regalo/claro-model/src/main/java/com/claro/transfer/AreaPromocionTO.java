/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class AreaPromocionTO
implements Serializable {
    private static final long serialVersionUID = -1470664204782238908L;
    private int idAreaPromocion;
    private String descAreaPromocion;
    private String estatus;

    public int getIdAreaPromocion() {
        return this.idAreaPromocion;
    }

    public void setIdAreaPromocion(int idAreaPromocion) {
        this.idAreaPromocion = idAreaPromocion;
    }

    public String getDescAreaPromocion() {
        return this.descAreaPromocion;
    }

    public void setDescAreaPromocion(String descAreaPromocion) {
        this.descAreaPromocion = descAreaPromocion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

