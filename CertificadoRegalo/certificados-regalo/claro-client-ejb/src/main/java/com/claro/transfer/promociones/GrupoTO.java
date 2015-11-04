/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import com.claro.transfer.promociones.ErrorTO;
import java.io.Serializable;

public class GrupoTO
extends ErrorTO
implements Serializable {
    private static final long serialVersionUID = 4855634203558484800L;
    private String idGrupoPromocion;
    private String tipoPromocion;
    private String estatus;
    private String grupoPromocion;
    private String descuento;
    private String descuentoValorAlto;

    public GrupoTO() {
    }

    public GrupoTO(String[] grupo) {
        this.idGrupoPromocion = grupo[0];
        this.tipoPromocion = grupo[1];
        this.estatus = grupo[2];
        this.grupoPromocion = grupo[3];
        this.descuento = grupo[4];
        this.descuentoValorAlto = grupo[5];
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getIdGrupoPromocion() {
        return this.idGrupoPromocion;
    }

    public void setIdGrupoPromocion(String idGrupoPromocion) {
        this.idGrupoPromocion = idGrupoPromocion;
    }

    public String getDescuento() {
        return this.descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public String getGrupoPromocion() {
        return this.grupoPromocion;
    }

    public void setGrupoPromocion(String grupoPromocion) {
        this.grupoPromocion = grupoPromocion;
    }

    public String getDescuentoValorAlto() {
        return this.descuentoValorAlto;
    }

    public void setDescuentoValorAlto(String descuentoValorAlto) {
        this.descuentoValorAlto = descuentoValorAlto;
    }

    public String toString() {
        return String.valueOf(this.idGrupoPromocion) + "," + this.tipoPromocion + "," + this.estatus + "," + this.grupoPromocion + "," + this.descuento + "," + this.descuentoValorAlto;
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

