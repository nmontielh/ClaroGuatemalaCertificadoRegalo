/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.BeneficioTO;
import java.util.ArrayList;

public class PromoBeneficiosTO {
    private String marca;
    private String modelo;
    private String idbeneficio;
    private String idGpoBeneficio;
    private ArrayList<BeneficioTO> beneficios;

    public ArrayList<BeneficioTO> getBeneficios() {
        return this.beneficios;
    }

    public void setBeneficios(ArrayList<BeneficioTO> beneficios) {
        this.beneficios = beneficios;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIdbeneficio() {
        return this.idbeneficio;
    }

    public void setIdbeneficio(String idbeneficio) {
        this.idbeneficio = idbeneficio;
    }

    public String getIdGpoBeneficio() {
        return this.idGpoBeneficio;
    }

    public void setIdGpoBeneficio(String idGpoBeneficio) {
        this.idGpoBeneficio = idGpoBeneficio;
    }
}

