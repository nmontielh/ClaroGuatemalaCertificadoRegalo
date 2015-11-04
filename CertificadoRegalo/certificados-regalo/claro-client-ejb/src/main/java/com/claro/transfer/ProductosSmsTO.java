/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class ProductosSmsTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String claveM2k;
    private String claveSms;
    private String idProducto;
    private String descripcion;
    private String tipoProducto;
    private String estatus;
    private int valorPuntos;

    public String getClaveM2k() {
        return this.claveM2k;
    }

    public void setClaveM2k(String claveM2k) {
        this.claveM2k = claveM2k;
    }

    public String getClaveSms() {
        return this.claveSms;
    }

    public void setClaveSms(String claveSms) {
        this.claveSms = claveSms;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public void setValorPuntos(int valorPuntos) {
        this.valorPuntos = valorPuntos;
    }
}

