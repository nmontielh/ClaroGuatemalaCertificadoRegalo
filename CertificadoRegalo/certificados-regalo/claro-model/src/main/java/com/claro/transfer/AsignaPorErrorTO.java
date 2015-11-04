/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.sql.Timestamp;

public class AsignaPorErrorTO {
    private String puntos;
    private Timestamp fechaOperacion;
    private String SM;
    private String secuencia;

    public String getPuntos() {
        return this.puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public Timestamp getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Timestamp fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getSM() {
        return this.SM;
    }

    public void setSM(String sm) {
        this.SM = sm;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }
}

