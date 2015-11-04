/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class AmigoKitReporteTO
implements Reportable {
    private String region;
    private String numCanjes;
    private String totalPuntos;
    private String diferenciaPesos;
    private String promedioPuntos;
    private String promedioPesos;

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

    public String getDiferenciaPesos() {
        return this.diferenciaPesos;
    }

    public void setDiferenciaPesos(String diferenciaPesos) {
        this.diferenciaPesos = diferenciaPesos;
    }

    public String getPromedioPuntos() {
        return this.promedioPuntos;
    }

    public void setPromedioPuntos(String promedioPuntos) {
        this.promedioPuntos = promedioPuntos;
    }

    public String getPromedioPesos() {
        return this.promedioPesos;
    }

    public void setPromedioPesos(String promedioPesos) {
        this.promedioPesos = promedioPesos;
    }
}

