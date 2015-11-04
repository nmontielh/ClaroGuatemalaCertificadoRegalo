/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.reportes;

import com.claro.transfer.reportes.Reportable;

public class RedencionEntregaTO
implements Reportable {
    private String medioEntrega;
    private String total;

    public String getMedioEntrega() {
        return this.medioEntrega;
    }

    public void setMedioEntrega(String medioEntrega) {
        this.medioEntrega = medioEntrega;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

