/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class PromocionTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 8180465079737604973L;
    private String idProducto;
    private String idPromocion;
    private String idRegion;
    private String idGrupoPromocion;
    private String tipoPromocion;
    private String estatus;
    private String gpoPromocion;
    private String descuento;
    private String descuentoAltoValor;
    private String descripcion;
    private String tipoProducto;
    private String precioLista;
    private String precioActiva;
    private String marca;
    private String modelo;
    private String URL;
    private String tecnologia;
    private String banSISACT;
    private String addendum;
    private String fzaVta;
    private String valorPtos;
    private String indicador;
    private String idAreaPromocion;

    public PromocionTO() {
    }

    public PromocionTO(String[] promocion) {
        this.idProducto = promocion[0];
        this.idRegion = promocion[1];
        this.idGrupoPromocion = promocion[2];
        this.descripcion = promocion[3];
        this.tipoProducto = promocion[4];
        this.precioLista = promocion[5];
        this.precioActiva = promocion[6];
        this.marca = promocion[7];
        this.modelo = promocion[8];
        this.URL = promocion[9];
        this.tecnologia = promocion[10];
        this.estatus = promocion[11];
        this.banSISACT = promocion[12];
        this.addendum = promocion[13];
        this.fzaVta = promocion[14];
        if (promocion[15] != null & !promocion[15].trim().equals("")) {
            this.valorPtos = promocion[15];
        }
        this.indicador = promocion[16];
        if (!(promocion.length <= 17 || promocion[17] == null || promocion[17].trim().equals(""))) {
            this.idAreaPromocion = promocion[17];
        }
    }

    public String getIdPromocion() {
        return this.idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getValorPtos() {
        return this.valorPtos;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public String getURL() {
        return this.URL;
    }

    public void setURL(String url) {
        this.URL = url;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getBanSISACT() {
        return this.banSISACT;
    }

    public void setBanSISACT(String banSISACT) {
        this.banSISACT = banSISACT;
    }

    public String getFzaVta() {
        return this.fzaVta;
    }

    public void setFzaVta(String fzaVta) {
        this.fzaVta = fzaVta;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String TipoPromocion) {
        this.tipoPromocion = TipoPromocion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String Estatus) {
        this.estatus = Estatus;
    }

    public String getGpoPromocion() {
        return this.gpoPromocion;
    }

    public void setGpoPromocion(String GpoPromocion) {
        this.gpoPromocion = GpoPromocion;
    }

    public String getLineaLog() {
        return this.idProducto + "@" + this.idRegion + "@" + this.idGrupoPromocion + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.precioLista + "@" + this.precioActiva + "@" + this.marca + "@" + this.modelo + "@" + this.URL + "@" + this.tecnologia + "@" + this.estatus + "@" + this.banSISACT + "@" + this.addendum + "@" + this.fzaVta + "@" + this.valorPtos + "@" + this.indicador + "@" + this.descuento + "@" + this.descuentoAltoValor + "@" + this.tipoPromocion + "@" + this.gpoPromocion;
    }

    public String toString() {
        return String.valueOf(this.idProducto) + "," + this.idRegion + "," + this.idGrupoPromocion + "," + this.descripcion + "," + this.tipoProducto + "," + this.precioLista + "," + this.precioActiva + "," + this.marca + "," + this.modelo + "," + this.URL + "," + this.tecnologia + "," + this.estatus + "," + this.banSISACT + "," + this.addendum + "," + this.fzaVta + "," + this.valorPtos + "," + this.indicador + "," + this.idAreaPromocion;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getIdGrupoPromocion() {
        return this.idGrupoPromocion;
    }

    public void setIdGrupoPromocion(String idGrupoPromocion) {
        this.idGrupoPromocion = idGrupoPromocion;
    }

    public String getDescuento() {
        return this.descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getDescuentoAltoValor() {
        return this.descuentoAltoValor;
    }

    public void setDescuentoAltoValor(String descuentoAltoValor) {
        this.descuentoAltoValor = descuentoAltoValor;
    }

    public String getPrecioLista() {
        return this.precioLista;
    }

    public void setPrecioLista(String precioLista) {
        this.precioLista = precioLista;
    }

    public String getPrecioActiva() {
        return this.precioActiva;
    }

    public void setPrecioActiva(String precioActiva) {
        this.precioActiva = precioActiva;
    }

    public String getAddendum() {
        return this.addendum;
    }

    public void setAddendum(String addendum) {
        this.addendum = addendum;
    }

    public String getIndicador() {
        return this.indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public void setValorPtos(String valorPtos) {
        this.valorPtos = valorPtos;
    }

    public String getIdAreaPromocion() {
        return this.idAreaPromocion;
    }

    public void setIdAreaPromocion(String idAreaPromocion) {
        this.idAreaPromocion = idAreaPromocion;
    }
}

