/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class AlianzaMexicanaTO
implements Reportable {
    private String region;
    private String numCanjes;
    private String millas;
    private String totalPuntos;
    private String promedio;

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

    public String getMillas() {
        return this.millas;
    }

    public void setMillas(String millas) {
        this.millas = millas;
    }

    public String getTotalPuntos() {
        return this.totalPuntos;
    }

    public void setTotalPuntos(String totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getPromedio() {
        return this.promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
}

