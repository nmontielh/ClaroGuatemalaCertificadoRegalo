/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

public class PrivilegioTO {
    private int idProceso;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String estatus;

    public int getIdProceso() {
        return this.idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("idProceso:").append(this.idProceso).append(", ");
        buffer.append("nombre:").append(this.nombre).append(", ");
        buffer.append("tipo:").append(this.tipo).append(", ");
        buffer.append("estatus:").append(this.estatus);
        return buffer.toString();
    }
}

