/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.util.Date;

public class FactorTO
extends MensajeTO {
    private static final long serialVersionUID = -4971336931931456206L;
    private int idcuenta;
    private int factor;
    private Date fechaActiva;
    private String estatus;
    private int millanMin;
    private int millasPosibles;

    public int getMillasPosibles(int ptsDisponibles) {
        int nPtosDisp = ptsDisponibles / this.millanMin * this.millanMin;
        if (nPtosDisp > 0 && this.factor > 0) {
            int millas = (int)Math.floor((double)nPtosDisp / (double)this.factor + 0.5);
            return millas;
        }
        return this.millasPosibles;
    }

    public int getIdcuenta() {
        return this.idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public int getFactor() {
        return this.factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public Date getFechaActiva() {
        return this.fechaActiva;
    }

    public void setFechaActiva(Date fechaActiva) {
        this.fechaActiva = fechaActiva;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getMillanMin() {
        return this.millanMin;
    }

    public void setMillanMin(int millanMin) {
        this.millanMin = millanMin;
    }
}

