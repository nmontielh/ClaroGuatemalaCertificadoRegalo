/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class GrupoPromocionTO
implements Serializable {
    private static final long serialVersionUID = -814566303199471298L;
    private int idGrupoPromocion;
    private String tipoPromocion;
    private String estatus;
    private String grupoPromocion;
    private int descuento;
    private int descuentoValorAlto;

    public int getIdGrupoPromocion() {
        return this.idGrupoPromocion;
    }

    public void setIdGrupoPromocion(int idGrupoPromocion) {
        this.idGrupoPromocion = idGrupoPromocion;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getGrupoPromocion() {
        return this.grupoPromocion;
    }

    public void setGrupoPromocion(String grupoPromocion) {
        this.grupoPromocion = grupoPromocion;
    }

    public int getDescuentoValorAlto() {
        return this.descuentoValorAlto;
    }

    public void setDescuentoValorAlto(int descuentoValorAlto) {
        this.descuentoValorAlto = descuentoValorAlto;
    }

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getLineaLog() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.idGrupoPromocion).append("@");
        buffer.append(this.tipoPromocion).append("@");
        buffer.append(this.estatus).append("@");
        buffer.append(this.grupoPromocion).append("@");
        buffer.append(this.descuento).append("@");
        buffer.append(this.descuentoValorAlto);
        return buffer.toString();
    }
}

