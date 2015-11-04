/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class AlianzaTicketMasterTO
implements Reportable {
    private String region;
    private String valor;
    private String totalRegion;
    private String totalPuntos;

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

