/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.MobileTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.UsuarioTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RedencionServiceTO
implements Serializable {
    private static final long serialVersionUID = -3765146311453053615L;
    private String cuenta;
    private String linea;
    private String fechaOperacion;
    private String material;
    private String marca;
    private String modelo;
    private String descripcion;
    private String esnimeiR;
    private String esnimeiP;
    private String iccid;
    private String precio;
    private String precioIva;
    private String precioDescuento;
    private String descuento;
    private String descripcionDescuento;
    private String usuario;
    private String nombreUsuario;
    private String comentario;
    private String fechaAdendum;
    private String fechaFolio;
    private String folio;
    private String puntoVenta;
    private String tipoRedencion;
    private String planNuevo;
    private String fechaPlazoSeguro;
    private String estatus;
    private String fechaAddendumM2K;
    private String firstName;
    private String lastName;
    private String nombreCliente;
    private int addendumNuevo;
    private int ptsTotales;
    private int region;
    private int valorPuntos;

    public RedencionServiceTO(RedencionTO redencionTO) {
        this.cuenta = redencionTO.getTelefonoSimpleTO().getCuenta();
        this.linea = redencionTO.getTelefonoSimpleTO().getLinea();
        this.fechaOperacion = Utils.DATEFORMATdd_MM_YYYY.format(redencionTO.getFechaOperacion());
        this.material = redencionTO.getProductosTO().getIdProducto();
        this.marca = redencionTO.getProductosTO().getMarca() == null || "null".equals(redencionTO.getProductosTO().getMarca()) ? "" : redencionTO.getProductosTO().getMarca();
        this.modelo = redencionTO.getProductosTO().getModelo() == null || "null".equals(redencionTO.getProductosTO().getModelo()) ? "" : redencionTO.getProductosTO().getModelo();
        this.ptsTotales = redencionTO.getPuntosRedimidosTO() != null ? redencionTO.getPuntosRedimidosTO().getPtsTotaltes() : 0;
        this.descuento = redencionTO.getProductosTO().getDescuento().toString();
        this.descripcionDescuento = redencionTO.getProductosTO().getDescripcionDescuento();
        this.precio = redencionTO.getProductosTO().getPrecio().toString();
        this.precioIva = redencionTO.getProductosTO().getPrecioIva().toString();
        this.usuario = redencionTO.getUsuarioTO().getIdUsuario();
        this.precioDescuento = redencionTO.getProductosTO().getPrecioDescuento() != null ? redencionTO.getProductosTO().getPrecioDescuento().toString() : "";
        this.comentario = redencionTO.getComentario();
        this.fechaAdendum = redencionTO.getFechaAdendum();
        this.fechaFolio = redencionTO.getFechaFolio().toString();
        this.folio = redencionTO.getFolio();
        this.puntoVenta = redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta() == null ? "" : redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta();
        this.tipoRedencion = redencionTO.getTipoRedencion();
        this.planNuevo = redencionTO.getPlanNuevo() == null || "null".equals(redencionTO.getPlanNuevo()) ? "" : redencionTO.getPlanNuevo();
        this.addendumNuevo = redencionTO.getAddendumNuevo();
        this.fechaPlazoSeguro = redencionTO.getFechaPlazoSeg() == null ? "" : Utils.DATEFORMATyyyy_MM_dd.format(redencionTO.getFechaPlazoSeg());
        this.descripcion = redencionTO.getProductosTO().getDescripcion();
        this.region = redencionTO.getRegion();
        this.estatus = redencionTO.getEstatus();
        this.nombreUsuario = redencionTO.getUsuarioTO().getNombre();
        this.fechaAddendumM2K = redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getFecAddM2K();
        this.firstName = redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getFirstName();
        this.lastName = redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getLastName();
        this.nombreCliente = redencionTO.getMobileTO() == null ? "" : String.valueOf(redencionTO.getMobileTO().getLastName()) + " " + redencionTO.getMobileTO().getFirstName();
        this.valorPuntos = redencionTO.getProductosTO().getValorPuntos();
        this.esnimeiR = redencionTO.getProductosTO().getNumeroSerieT() == null || "null".equals(redencionTO.getProductosTO().getNumeroSerieT()) || "0".equals(redencionTO.getProductosTO().getNumeroSerieT()) ? "" : redencionTO.getProductosTO().getNumeroSerieT();
        this.esnimeiP = redencionTO.getProductosTO().getNumeroSerieP() == null || "null".equals(redencionTO.getProductosTO().getNumeroSerieP()) || "0".equals(redencionTO.getProductosTO().getNumeroSerieP()) ? "" : redencionTO.getProductosTO().getNumeroSerieP();
        this.iccid = redencionTO.getProductosTO().getIccid() == null || "null".equals(redencionTO.getProductosTO().getIccid()) || "0".equals(redencionTO.getProductosTO().getIccid()) ? "" : redencionTO.getProductosTO().getIccid();
    }

    public RedencionServiceTO() {
    }

    public int getPtsTotales() {
        return this.ptsTotales;
    }

    public void setPtsTotales(int ptsTotales) {
        this.ptsTotales = ptsTotales;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    public String getPrecio() {
        return this.precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescuento() {
        return this.descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getPrecioIva() {
        return this.precioIva;
    }

    public void setPrecioIva(String precioIva) {
        this.precioIva = precioIva;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaAdendum() {
        return this.fechaAdendum;
    }

    public void setFechaAdendum(String fechaAdendum) {
        this.fechaAdendum = fechaAdendum;
    }

    public String getFechaFolio() {
        return this.fechaFolio;
    }

    public void setFechaFolio(String fechaFolio) {
        this.fechaFolio = fechaFolio;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String toString() {
        return String.valueOf(this.cuenta) + "|" + this.linea + "|" + this.fechaOperacion + "|" + this.material + "|" + this.marca + "|" + this.modelo + "|" + this.ptsTotales + "|" + this.precio + "|" + this.descuento + "|" + this.precioIva + "|" + this.usuario + "|" + this.comentario + "|" + this.fechaAdendum + "|" + this.fechaFolio + "|" + this.folio + "|" + this.puntoVenta + "|" + this.tipoRedencion + "|" + this.addendumNuevo + "|" + this.fechaPlazoSeguro + "|" + this.descripcion + "|" + this.region + "|" + this.esnimeiR + "|" + this.esnimeiP + "|" + this.nombreUsuario + this.iccid + "|" + this.getFechaAddendumM2K();
    }

    public String getPuntoVenta() {
        return this.puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public void setPlanNuevo(String planNuevo) {
        this.planNuevo = planNuevo;
    }

    public int getAddendumNuevo() {
        return this.addendumNuevo;
    }

    public void setAddendumNuevo(int addendumNuevo) {
        this.addendumNuevo = addendumNuevo;
    }

    public String getFechaPlazoSeguro() {
        return this.fechaPlazoSeguro;
    }

    public void setFechaPlazoSeguro(String fechaPlazoSeguro) {
        this.fechaPlazoSeguro = fechaPlazoSeguro;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getEsnimeiR() {
        return this.esnimeiR;
    }

    public void setEsnimeiR(String esnimeiR) {
        this.esnimeiR = esnimeiR;
    }

    public String getEsnimeiP() {
        return this.esnimeiP;
    }

    public void setEsnimeiP(String esnimeiP) {
        this.esnimeiP = esnimeiP;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getFechaAddendumM2K() {
        return this.fechaAddendumM2K;
    }

    public void setFechaAddendumM2K(String fechaAddencumM2K) {
        this.fechaAddendumM2K = fechaAddencumM2K;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public void setValorPuntos(int valorPuntos) {
        this.valorPuntos = valorPuntos;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNombreCliente() {
        return this.nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getPrecioDescuento() {
        return this.precioDescuento;
    }

    public void setPrecioDescuento(String precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public String getDescripcionDescuento() {
        return this.descripcionDescuento;
    }

    public void setDescripcionDescuento(String descripcionDescuento) {
        this.descripcionDescuento = descripcionDescuento;
    }
}

