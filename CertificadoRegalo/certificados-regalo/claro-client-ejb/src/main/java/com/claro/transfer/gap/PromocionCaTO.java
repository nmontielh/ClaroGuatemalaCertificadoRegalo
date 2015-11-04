/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO
 */
package com.claro.transfer.gap;

import com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO;
import java.io.Serializable;
import java.math.BigDecimal;

public class PromocionCaTO
implements Serializable {
    private static final long serialVersionUID = -8008442086123602365L;
    private int idPromocionCA;
    private int idPromocion;
    private int versionPromocion;
    private String aplicaPromoGap;
    private String bonoDescuento;
    private String productoM2K;
    private String nombrePromocion;
    private String aplicaEp;
    private int modoSuscripcionNuevo;
    private int modoSuscripcionAnterior;
    private String modelo;
    private String marca;
    private int idGrupoPlanNuevo;
    private int idGrupoPlanAnterior;
    private int plazoFzoNuevo;
    private int plazoFzoAnterior;
    private BigDecimal cantidadDescuento;
    private String idCampania;
    private String idOferta;
    private CatalogoTO[] equipos;

    public int getIdPromocionCA() {
        return this.idPromocionCA;
    }

    public void setIdPromocionCA(int idPromocionCA) {
        this.idPromocionCA = idPromocionCA;
    }

    public int getIdPromocion() {
        return this.idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getVersionPromocion() {
        return this.versionPromocion;
    }

    public void setVersionPromocion(int versionPromocion) {
        this.versionPromocion = versionPromocion;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdGrupoPlanNuevo() {
        return this.idGrupoPlanNuevo;
    }

    public void setIdGrupoPlanNuevo(int idGrupoPlanNuevo) {
        this.idGrupoPlanNuevo = idGrupoPlanNuevo;
    }

    public int getIdGrupoPlanAnterior() {
        return this.idGrupoPlanAnterior;
    }

    public void setIdGrupoPlanAnterior(int idGrupoPlanAnterior) {
        this.idGrupoPlanAnterior = idGrupoPlanAnterior;
    }

    public int getPlazoFzoNuevo() {
        return this.plazoFzoNuevo;
    }

    public void setPlazoFzoNuevo(int plazoFzoNuevo) {
        this.plazoFzoNuevo = plazoFzoNuevo;
    }

    public int getPlazoFzoAnterior() {
        return this.plazoFzoAnterior;
    }

    public void setPlazoFzoAnterior(int plazoFzoAnterior) {
        this.plazoFzoAnterior = plazoFzoAnterior;
    }

    public String getBonoDescuento() {
        return this.bonoDescuento;
    }

    public void setBonoDescuento(String bonoDescuento) {
        this.bonoDescuento = bonoDescuento;
    }

    public String getProductoM2K() {
        return this.productoM2K;
    }

    public void setProductoM2K(String productoM2K) {
        this.productoM2K = productoM2K;
    }

    public String toString() {
        return "\nidPromocionCA: " + this.idPromocionCA + "\nidPromocion: " + this.idPromocion + "\nversionPromocion: " + this.versionPromocion + "\nmodelo: " + this.modelo + "\nmarca: " + this.marca + "\nnmodelo: " + this.modelo + "\nidGrupoPlanNuevo: " + this.idGrupoPlanNuevo + "\nidGrupoPlanAnterior: " + this.idGrupoPlanAnterior + "\nplazoFzoAnterior: " + this.plazoFzoAnterior + "\nbonoDescuento: " + this.bonoDescuento + "\ncantidadDescuento: " + this.cantidadDescuento + "\nmodoSuscripcionNuevo: " + this.modoSuscripcionNuevo + "\nmodoSuscripcionAnterior: " + this.modoSuscripcionAnterior + "\naplicaPromoGap: " + this.aplicaPromoGap + "\nnombrePromocion: " + this.nombrePromocion;
    }

    public BigDecimal getCantidadDescuento() {
        return this.cantidadDescuento;
    }

    public void setCantidadDescuento(BigDecimal cantidadDescuento) {
        this.cantidadDescuento = cantidadDescuento;
    }

    public int getModoSuscripcionNuevo() {
        return this.modoSuscripcionNuevo;
    }

    public void setModoSuscripcionNuevo(int modoSuscripcionNuevo) {
        this.modoSuscripcionNuevo = modoSuscripcionNuevo;
    }

    public int getModoSuscripcionAnterior() {
        return this.modoSuscripcionAnterior;
    }

    public void setModoSuscripcionAnterior(int modoSuscripcionAnterior) {
        this.modoSuscripcionAnterior = modoSuscripcionAnterior;
    }

    public String getAplicaPromoGap() {
        return this.aplicaPromoGap;
    }

    public void setAplicaPromoGap(String aplicaPromoGap) {
        this.aplicaPromoGap = aplicaPromoGap;
    }

    public String getNombrePromocion() {
        return this.nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getAplicaEp() {
        return this.aplicaEp;
    }

    public void setAplicaEp(String aplicaEp) {
        this.aplicaEp = aplicaEp;
    }

    public String getIdCampania() {
        return this.idCampania;
    }

    public void setIdCampania(String idCampania) {
        this.idCampania = idCampania;
    }

    public String getIdOferta() {
        return this.idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public CatalogoTO[] getEquipos() {
        return this.equipos;
    }

    public void setEquipos(CatalogoTO[] equipos) {
        this.equipos = equipos;
    }
}

