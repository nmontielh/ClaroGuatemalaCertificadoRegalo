/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;
import java.util.Date;

public class CicloFacturacionTO
implements Serializable {
    private static final long serialVersionUID = -8959872228626683654L;
    private int cicloFac;
    private int idRegion;
    private Date fechaCorte;
    private String descripcion;

    public int getCicloFac() {
        return this.cicloFac;
    }

    public void setCicloFac(int cicloFac) {
        this.cicloFac = cicloFac;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public Date getFechaCorte() {
        return this.fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

