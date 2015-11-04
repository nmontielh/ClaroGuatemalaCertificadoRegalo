/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class RedencionesOnlineTO
implements Reportable {
    private String origen;
    private String region;
    private String paquete;
    private String totalRegion;
    private String totalPuntos;

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPaquete() {
        return this.paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getTotalRegion() {
        return this.totalRegion;
    }

    public void setTotalRegion(String totalRegion) {
        this.totalRegion = totalRegion;
    }

    public String getTotalPuntos() {
        return this.totalPuntos;
    }

    public void setTotalPuntos(String totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
}

