/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.MensajeTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.ReservacionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.UsuarioTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReservacionServiceTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 5007688798391824448L;
    private String folio;
    private String idProducto;
    private String comentario;
    private String usuario;
    private String puntoVenta;
    private String descripcion;
    private String marca;
    private String modelo;
    private String tipoRedencion;
    private String formaRedencion;
    private String tipoPromocion;
    private String planNuevo;
    private String planM2K;
    private String precioIVA;
    private String precio;
    private String descuento;
    private String idPerfil;
    private String cuenta;
    private String telefono;
    private String fuerzaVenta;
    private String fechaAddendumM2K;
    private String fechaAltaUser;
    private String addendumM2K;
    private String addendumNuevo;
    private String estatus;
    private String estatusCobranza;
    private String fechaExpiracion;
    private String fechaOperacion;
    private String motivo;
    private int valorPesos;
    private int valorPuntos;
    private int bonoRoext;
    private int bonoAltoValor;
    private int secuencia;
    private int region;
    private int puntosSobrantes;
    private int puntosDisponibles;
    private long puntosaRedimir;
    private String aplicaPromocionGap;
    private String bonoDescuentoGap;
    private String productoM2KGap;
    private String nombrePromocionGap;
    private String aplicaEP;
    private int bonosGap;
    private int idPromocionGapCA;
    private int idPromocionGap;
    private int verPromocionGap;

    public ReservacionServiceTO() {
    }

    public ReservacionServiceTO(ReservacionTO _reservacionTO) {
        this.addendumM2K = _reservacionTO.getFechaAdendum();
        this.addendumNuevo = String.valueOf(_reservacionTO.getAddendumNuevo());
        this.bonoAltoValor = _reservacionTO.getProductosTO().getBonosAltoValor();
        this.bonoRoext = _reservacionTO.getProductosTO().getBonosRoext();
        this.comentario = _reservacionTO.getComentario();
        this.cuenta = _reservacionTO.getTelefonoSimpleTO().getCuenta();
        this.descripcion = _reservacionTO.getProductosTO().getDescripcion();
        this.descuento = String.valueOf(_reservacionTO.getProductosTO().getDescuento());
        this.estatus = _reservacionTO.getEstatus();
        this.fechaAddendumM2K = _reservacionTO.getPlazoAnterior();
        this.fechaExpiracion = String.valueOf(_reservacionTO.getFechaExpiracion());
        this.fechaOperacion = String.valueOf(_reservacionTO.getFechaOperacion());
        this.folio = _reservacionTO.getFolio();
        this.formaRedencion = _reservacionTO.getFormaRedencion();
        this.fuerzaVenta = _reservacionTO.getFuerzaVenta();
        this.idPerfil = _reservacionTO.getUsuarioTO().getPerfilTO().getIdPuesto();
        this.idProducto = _reservacionTO.getProductosTO().getIdProducto();
        this.marca = _reservacionTO.getProductosTO().getMarca();
        this.modelo = _reservacionTO.getProductosTO().getModelo();
        this.planNuevo = _reservacionTO.getPlanNuevo();
        this.precio = _reservacionTO.getProductosTO().getPrecio().toString();
        this.precioIVA = _reservacionTO.getProductosTO().getPrecioIva().toString();
        this.puntosSobrantes = _reservacionTO.getPtsSobrantes();
        this.puntoVenta = _reservacionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta();
        this.region = _reservacionTO.getRegion();
        this.secuencia = _reservacionTO.getTelefonoSimpleTO().getSecuencia();
        this.telefono = _reservacionTO.getTelefonoSimpleTO().getLinea();
        this.tipoPromocion = _reservacionTO.getProductosTO().getTipoPromocion();
        this.tipoRedencion = _reservacionTO.getTipoRedencion();
        this.usuario = _reservacionTO.getUsuarioTO().getIdUsuario();
        this.valorPesos = _reservacionTO.getProductosTO().getDifPesos();
        this.valorPuntos = _reservacionTO.getProductosTO().getValorPuntos();
        this.aplicaPromocionGap = _reservacionTO.getProductosTO().getAplicaPromocionGap();
        this.bonoDescuentoGap = _reservacionTO.getProductosTO().getBonoDescuentoGap();
        this.productoM2KGap = _reservacionTO.getProductosTO().getProductoM2KGap();
        this.nombrePromocionGap = _reservacionTO.getProductosTO().getNombrePromocionGap();
        this.bonosGap = _reservacionTO.getProductosTO().getBonosGap();
        this.idPromocionGapCA = _reservacionTO.getProductosTO().getIdPromocionGapCA();
        this.idPromocionGap = _reservacionTO.getProductosTO().getIdPromocionGap();
        this.verPromocionGap = _reservacionTO.getProductosTO().getVerPromocionGap();
        this.aplicaEP = _reservacionTO.getProductosTO().getAplicaEP();
    }

    public long getPuntosaRedimir() {
        return this.puntosaRedimir;
    }

    public void setPuntosaRedimir(long puntosaRedimir) {
        this.puntosaRedimir = puntosaRedimir;
    }

    public int getPuntosDisponibles() {
        return this.puntosDisponibles;
    }

    public void setPuntosDisponibles(int puntosDisponibles) {
        this.puntosDisponibles = puntosDisponibles;
    }

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getPuntosSobrantes() {
        return this.puntosSobrantes;
    }

    public void setPuntosSobrantes(int puntosSobrantes) {
        this.puntosSobrantes = puntosSobrantes;
    }

    public String getPrecio() {
        return this.precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getAddendumM2K() {
        return this.addendumM2K;
    }

    public void setAddendumM2K(String addendumM2K) {
        this.addendumM2K = addendumM2K;
    }

    public String getFechaAltaUser() {
        return this.fechaAltaUser;
    }

    public void setFechaAltaUser(String fechaAltaUser) {
        this.fechaAltaUser = fechaAltaUser;
    }

    public String getFechaAddendumM2K() {
        if (this.fechaAddendumM2K == null || this.fechaAddendumM2K.trim().equals("") || this.fechaAddendumM2K.trim().equals("00/00/00")) {
            return this.fechaAltaUser;
        }
        return this.fechaAddendumM2K;
    }

    public void setFechaAddendumM2K(String fechaAddendumM2K) {
        this.fechaAddendumM2K = fechaAddendumM2K;
    }

    public String getFuerzaVenta() {
        return this.fuerzaVenta;
    }

    public void setFuerzaVenta(String fuerzaVenta) {
        this.fuerzaVenta = fuerzaVenta;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPuntoVenta() {
        return this.puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getFormaRedencion() {
        return this.formaRedencion;
    }

    public void setFormaRedencion(String formaRedencion) {
        this.formaRedencion = formaRedencion;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public void setPlanNuevo(String planNuevo) {
        this.planNuevo = planNuevo;
    }

    public String getPrecioIVA() {
        return this.precioIVA;
    }

    public void setPrecioIVA(String precioIVA) {
        this.precioIVA = precioIVA;
    }

    public String getDescuento() {
        return this.descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getIdPerfil() {
        return this.idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getValorPesos() {
        return this.valorPesos;
    }

    public void setValorPesos(int valorPesos) {
        this.valorPesos = valorPesos;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public void setValorPuntos(int valorPuntos) {
        this.valorPuntos = valorPuntos;
    }

    public String getAddendumNuevo() {
        return this.addendumNuevo;
    }

    public void setAddendumNuevo(String addendumNuevo) {
        this.addendumNuevo = addendumNuevo;
    }

    public int getBonoRoext() {
        return this.bonoRoext;
    }

    public void setBonoRoext(int bonoRoext) {
        this.bonoRoext = bonoRoext;
    }

    public int getBonoAltoValor() {
        return this.bonoAltoValor;
    }

    public void setBonoAltoValor(int bonoAltoValor) {
        this.bonoAltoValor = bonoAltoValor;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstatusCobranza() {
        return this.estatusCobranza;
    }

    public void setEstatusCobranza(String estatusCobranza) {
        this.estatusCobranza = estatusCobranza;
    }

    public String getPlanM2K() {
        return this.planM2K;
    }

    public void setPlanM2K(String planM2K) {
        this.planM2K = planM2K;
    }

    public String getAplicaPromocionGap() {
        return this.aplicaPromocionGap;
    }

    public void setAplicaPromocionGap(String aplicaPromocionGap) {
        this.aplicaPromocionGap = aplicaPromocionGap;
    }

    public String getBonoDescuentoGap() {
        return this.bonoDescuentoGap;
    }

    public void setBonoDescuentoGap(String bonoDescuentoGap) {
        this.bonoDescuentoGap = bonoDescuentoGap;
    }

    public String getProductoM2KGap() {
        return this.productoM2KGap;
    }

    public void setProductoM2KGap(String productoM2KGap) {
        this.productoM2KGap = productoM2KGap;
    }

    public String getNombrePromocionGap() {
        return this.nombrePromocionGap;
    }

    public void setNombrePromocionGap(String nombrePromocionGap) {
        this.nombrePromocionGap = nombrePromocionGap;
    }

    public int getBonosGap() {
        return this.bonosGap;
    }

    public void setBonosGap(int bonosGap) {
        this.bonosGap = bonosGap;
    }

    public int getIdPromocionGapCA() {
        return this.idPromocionGapCA;
    }

    public void setIdPromocionGapCA(int idPromocionGapCA) {
        this.idPromocionGapCA = idPromocionGapCA;
    }

    public int getIdPromocionGap() {
        return this.idPromocionGap;
    }

    public void setIdPromocionGap(int idPromocionGap) {
        this.idPromocionGap = idPromocionGap;
    }

    public int getVerPromocionGap() {
        return this.verPromocionGap;
    }

    public void setVerPromocionGap(int verPromocionGap) {
        this.verPromocionGap = verPromocionGap;
    }

    public String getAplicaEP() {
        return this.aplicaEP;
    }

    public void setAplicaEP(String aplicaEP) {
        this.aplicaEP = aplicaEP;
    }
}

