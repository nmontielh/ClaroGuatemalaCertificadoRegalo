/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.ProductosTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class DescuentoTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private int aplicaDescuentoRoext;
    private int aplicaDescuentoAltoValor;
    private int aplicaDescuentoPromocion;
    private int aplicaProductoM2K;
    private int numBonosRoext;
    private int numBonosAltoValor;
    private int numBonosInbursa;
    private int numBonosGap;
    private BigDecimal bonoDescuentoAltoValor;
    private BigDecimal bonoDescuentoRoext;
    private BigDecimal bonoDescuentoPromocion;
    private BigDecimal descuentoUtilizado;
    private int aplicaDescuentoInbursa;
    private BigDecimal bonoDescuentoMarca;
    private BigDecimal bonoDescuentoInbursa;
    private Map<String, ProductosTO> descuentosInbursa;
    private BigDecimal descuentoInbursaRestante;
    private BigDecimal descuentoMarcaRestante;

    public BigDecimal getBonoDescuentoRoext() {
        return this.bonoDescuentoRoext;
    }

    public void setBonoDescuentoRoext(BigDecimal bonoDescuentoRoext) {
        this.bonoDescuentoRoext = bonoDescuentoRoext;
    }

    public BigDecimal getBonoDescuentoAltoValor() {
        return this.bonoDescuentoAltoValor;
    }

    public void setBonoDescuentoAltoValor(BigDecimal bonoDescuentoAltoValor) {
        this.bonoDescuentoAltoValor = bonoDescuentoAltoValor;
    }

    public int getNumBonosAltoValor() {
        return this.numBonosAltoValor;
    }

    public void setNumBonosAltoValor(int numBonosAltoValor) {
        this.numBonosAltoValor = numBonosAltoValor;
    }

    public int getAplicaDescuentoRoext() {
        return this.aplicaDescuentoRoext;
    }

    public void setAplicaDescuentoRoext(int aplicaDescuentoRoext) {
        this.aplicaDescuentoRoext = aplicaDescuentoRoext;
    }

    public int getAplicaDescuentoAltoValor() {
        return this.aplicaDescuentoAltoValor;
    }

    public void setAplicaDescuentoAltoValor(int aplicaDescuentoAltoValor) {
        this.aplicaDescuentoAltoValor = aplicaDescuentoAltoValor;
    }

    public int getNumBonosRoext() {
        return this.numBonosRoext;
    }

    public void setNumBonosRoext(int numBonosRoext) {
        this.numBonosRoext = numBonosRoext;
    }

    public BigDecimal getDescuentoUtilizado() {
        return this.descuentoUtilizado;
    }

    public void setDescuentoUtilizado(BigDecimal descuentoUtilizado) {
        this.descuentoUtilizado = descuentoUtilizado;
    }

    public int getNumBonosGap() {
        return this.numBonosGap;
    }

    public void setNumBonosGap(int numBonosGap) {
        this.numBonosGap = numBonosGap;
    }

    public int getAplicaProductoM2K() {
        return this.aplicaProductoM2K;
    }

    public void setAplicaProductoM2K(int aplicaProductoM2K) {
        this.aplicaProductoM2K = aplicaProductoM2K;
    }

    public int getAplicaDescuentoPromocion() {
        return this.aplicaDescuentoPromocion;
    }

    public void setAplicaDescuentoPromocion(int aplicaDescuentoPromocion) {
        this.aplicaDescuentoPromocion = aplicaDescuentoPromocion;
    }

    public BigDecimal getBonoDescuentoPromocion() {
        return this.bonoDescuentoPromocion;
    }

    public void setBonoDescuentoPromocion(BigDecimal bonoDescuentoPromocion) {
        this.bonoDescuentoPromocion = bonoDescuentoPromocion;
    }

    public int getAplicaDescuentoInbursa() {
        return this.aplicaDescuentoInbursa;
    }

    public void setAplicaDescuentoInbursa(int aplicaDescuentoInbursa) {
        this.aplicaDescuentoInbursa = aplicaDescuentoInbursa;
    }

    public BigDecimal getBonoDescuentoMarca() {
        return this.bonoDescuentoMarca;
    }

    public void setBonoDescuentoMarca(BigDecimal bonoDescuentoMarca) {
        this.bonoDescuentoMarca = bonoDescuentoMarca;
    }

    public BigDecimal getBonoDescuentoInbursa() {
        return this.bonoDescuentoInbursa;
    }

    public void setBonoDescuentoInbursa(BigDecimal bonoDescuentoInbursa) {
        this.bonoDescuentoInbursa = bonoDescuentoInbursa;
    }

    public int getNumBonosInbursa() {
        return this.numBonosInbursa;
    }

    public void setNumBonosInbursa(int numBonosInbursa) {
        this.numBonosInbursa = numBonosInbursa;
    }

    public Map<String, ProductosTO> getDescuentosInbursa() {
        return this.descuentosInbursa;
    }

    public void setDescuentosInbursa(Map<String, ProductosTO> descuentosInbursa) {
        this.descuentosInbursa = descuentosInbursa;
    }

    public BigDecimal getDescuentoInbursaRestante() {
        return this.descuentoInbursaRestante;
    }

    public void setDescuentoInbursaRestante(BigDecimal descuentoInbursaRestante) {
        this.descuentoInbursaRestante = descuentoInbursaRestante;
    }

    public BigDecimal getDescuentoMarcaRestante() {
        return this.descuentoMarcaRestante;
    }

    public void setDescuentoMarcaRestante(BigDecimal descuentoMarcaRestante) {
        this.descuentoMarcaRestante = descuentoMarcaRestante;
    }
}

