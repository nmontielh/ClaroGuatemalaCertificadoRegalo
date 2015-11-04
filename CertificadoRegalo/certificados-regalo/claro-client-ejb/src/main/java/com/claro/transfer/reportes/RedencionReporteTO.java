/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

public class RedencionReporteTO {
    private String marca;
    private String modelo;
    private String tipoRedencion;
    private String idProducto;
    private String estatus;
    private String totalPuntos;
    private String totalPesos;
    private String totalEquipos;

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTotalPuntos() {
        return this.totalPuntos;
    }

    public void setTotalPuntos(String totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getTotalPesos() {
        return this.totalPesos;
    }

    public void setTotalPesos(String totalPesos) {
        this.totalPesos = totalPesos;
    }

    public String getTotalEquipos() {
        return this.totalEquipos;
    }

    public void setTotalEquipos(String totalEquipos) {
        this.totalEquipos = totalEquipos;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

