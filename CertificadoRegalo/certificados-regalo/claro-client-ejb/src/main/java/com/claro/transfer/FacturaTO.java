/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.math.BigDecimal;

public class FacturaTO {
    private double monto;
    private BigDecimal fechaFactura;

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public BigDecimal getFechaFactura() {
        return this.fechaFactura;
    }

    public void setFechaFactura(BigDecimal fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
}

