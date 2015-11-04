/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class BeneficioTO
implements Serializable {
    private static final long serialVersionUID = -7447228798768354384L;
    private String idBeneficio;
    private String idGpoBeneficio;
    private String descBeneficio;
    private String idMotivo;
    private String folio;

    public String getIdBeneficio() {
        return this.idBeneficio;
    }

    public void setIdBeneficio(String idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getDescBeneficio() {
        return this.descBeneficio;
    }

    public void setDescBeneficio(String descBeneficio) {
        this.descBeneficio = descBeneficio;
    }

    public String getIdMotivo() {
        return this.idMotivo;
    }

    public void setIdMotivo(String idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIdGpoBeneficio() {
        return this.idGpoBeneficio;
    }

    public void setIdGpoBeneficio(String idGpoBeneficio) {
        this.idGpoBeneficio = idGpoBeneficio;
    }
}

