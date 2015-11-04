/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class CatalogoTO
implements Serializable {
    private static final long serialVersionUID = -2876315591050173260L;
    private String idVariable;
    private String descripcion;
    private String valor;

    public String getIdVariable() {
        return this.idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public static long getSerialVersionUID() {
        return -2876315591050173260L;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

