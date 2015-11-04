/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.AreaPromocionTO;
import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class PromocionTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -4669325907897942635L;
    private String idProducto;
    private String idPromocion;
    private int idRegion;
    private int idGrupoPromocion;
    private String tipoPromocion;
    private String estatus;
    private String gpoPromocion;
    private int descuento;
    private int descuentoAltoValor;
    private String descripcion;
    private String tipoProducto;
    private float precioLista;
    private float precioActiva;
    private String marca;
    private String modelo;
    private String URL;
    private String tecnologia;
    private String banSISACT;
    private int addendum;
    private String fzaVta;
    private int valorPtos;
    private int indicador;
    private AreaPromocionTO areaPromocion;

    public PromocionTO() {
    }

    public PromocionTO(String[] promocion) {
        this.idProducto = promocion[0];
        this.idRegion = Integer.parseInt(promocion[1]);
        this.idGrupoPromocion = Integer.parseInt(promocion[2]);
        this.descripcion = promocion[3];
        this.tipoProducto = promocion[4];
        this.precioLista = Float.valueOf(promocion[5]).floatValue();
        this.precioActiva = Float.valueOf(promocion[6]).floatValue();
        this.marca = promocion[7];
        this.modelo = promocion[8];
        this.URL = promocion[9];
        this.tecnologia = promocion[10];
        this.estatus = promocion[11];
        this.banSISACT = promocion[12];
        this.addendum = Integer.parseInt(promocion[13]);
        this.fzaVta = promocion[14];
        if (promocion[15] != null & !promocion[15].trim().equals("")) {
            this.valorPtos = Integer.parseInt(promocion[15]);
        }
        this.indicador = Integer.parseInt(promocion[16]);
    }

    public String getIdPromocion() {
        return this.idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getValorPtos() {
        return this.valorPtos;
    }

    public void setValorPtos(int valorPtos) {
        this.valorPtos = valorPtos;
    }

    public int getIndicador() {
        return this.indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
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

    public float getPrecioLista() {
        return this.precioLista;
    }

    public void setPrecioLista(float precioLista) {
        this.precioLista = precioLista;
    }

    public float getPrecioActiva() {
        return this.precioActiva;
    }

    public void setPrecioActiva(float precioActiva) {
        this.precioActiva = precioActiva;
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

    public int getAddendum() {
        return this.addendum;
    }

    public void setAddendum(int addendum) {
        this.addendum = addendum;
    }

    public String getFzaVta() {
        return this.fzaVta;
    }

    public void setFzaVta(String fzaVta) {
        this.fzaVta = fzaVta;
    }

    public static long getSerialVersionUID() {
        return -4669325907897942635L;
    }

    public int getIdGrupoPromocion() {
        return this.idGrupoPromocion;
    }

    public void setIdGrupoPromocion(int IdGrupoPromocion) {
        this.idGrupoPromocion = IdGrupoPromocion;
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

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int Descuento) {
        this.descuento = Descuento;
    }

    public int getDescuentoAltoValor() {
        return this.descuentoAltoValor;
    }

    public void setDescuentoAltoValor(int DescuentoAltoValor) {
        this.descuentoAltoValor = DescuentoAltoValor;
    }

    public String getLineaLog() {
        return this.idProducto + "@" + this.idRegion + "@" + this.idGrupoPromocion + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.precioLista + "@" + this.precioActiva + "@" + this.marca + "@" + this.modelo + "@" + this.URL + "@" + this.tecnologia + "@" + this.estatus + "@" + this.banSISACT + "@" + this.addendum + "@" + this.fzaVta + "@" + this.valorPtos + "@" + this.indicador + "@" + this.descuento + "@" + this.descuentoAltoValor + "@" + this.tipoPromocion + "@" + this.gpoPromocion;
    }

    public String toString() {
        return String.valueOf(this.idProducto) + "," + this.idRegion + "," + this.idGrupoPromocion + "," + this.descripcion + "," + this.tipoProducto + "," + this.precioLista + "," + this.precioActiva + "," + this.marca + "," + this.modelo + "," + this.URL + "," + this.tecnologia + "," + this.estatus + "," + this.banSISACT + "," + this.addendum + "," + this.fzaVta + "," + this.valorPtos + "," + this.indicador;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public AreaPromocionTO getAreaPromocion() {
        return this.areaPromocion;
    }

    public void setAreaPromocion(AreaPromocionTO areaPromocion) {
        this.areaPromocion = areaPromocion;
    }
}

