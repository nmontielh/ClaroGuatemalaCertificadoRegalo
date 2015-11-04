/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class TiempoAireReporteTO
implements Reportable {
    private String region;
    private String paqueteTiempoAire;
    private String totalRegion;
    private String totalPuntos;

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPaqueteTiempoAire() {
        return this.paqueteTiempoAire;
    }

    public void setPaqueteTiempoAire(String paqueteTiempoAire) {
        this.paqueteTiempoAire = paqueteTiempoAire;
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

