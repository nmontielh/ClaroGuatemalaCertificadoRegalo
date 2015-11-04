/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class AlianzaAmexTO
implements Reportable {
    private String region;
    private String numCanjes;
    private String totalPuntos;
    private String estatus;

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumCanjes() {
        return this.numCanjes;
    }

    public void setNumCanjes(String numCanjes) {
        this.numCanjes = numCanjes;
    }

    public String getTotalPuntos() {
        return this.totalPuntos;
    }

    public void setTotalPuntos(String totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

