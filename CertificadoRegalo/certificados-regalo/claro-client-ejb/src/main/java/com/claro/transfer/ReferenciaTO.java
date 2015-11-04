/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;

public class ReferenciaTO
extends MensajeTO {
    private static final long serialVersionUID = -1113323878109805556L;
    private String idReferencia;
    private int valor;
    private long fechaModificacion;
    private String usuario;

    public String getIdReferencia() {
        return this.idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public long getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(long fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

