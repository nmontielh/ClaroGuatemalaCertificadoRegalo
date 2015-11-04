/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import com.claro.transfer.promociones.ErrorTO;
import java.io.Serializable;

public class ProductosSmsTO
extends ErrorTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String claveM2k;
    private String claveSms;
    private String idProducto;
    private String descripcion;
    private String tipoProducto;
    private String estatus;
    private int valorPuntos;

    public ProductosSmsTO() {
    }

    public ProductosSmsTO(String[] productosSms) {
        this.claveM2k = productosSms[0];
        this.claveSms = productosSms[1];
        this.idProducto = productosSms[2];
        this.descripcion = productosSms[3];
        this.tipoProducto = productosSms[4];
        this.estatus = productosSms[5];
        this.valorPuntos = Integer.parseInt(productosSms[6]);
    }

    public String getLineaLog() {
        return this.claveM2k + "@" + this.claveSms + "@" + this.idProducto + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.estatus + "@" + this.valorPuntos;
    }

    public String toString() {
        return String.valueOf(this.claveM2k) + "," + this.claveSms + "," + this.idProducto + "," + this.descripcion + "," + this.tipoProducto + "," + this.estatus + "," + this.valorPuntos;
    }

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

