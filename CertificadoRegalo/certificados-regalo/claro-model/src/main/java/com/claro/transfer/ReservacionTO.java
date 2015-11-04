/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MobileTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.service.ReservacionServiceTO;
import com.claro.util.Utils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservacionTO
extends RedencionTO {
    private static final long serialVersionUID = 8621114546241946379L;
    private String motivo;
    private Date fechaExpiracion;

    public ReservacionTO() {
    }

    public ReservacionTO(ReservacionServiceTO reservacionServiceTO) throws ParseException {
        TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
        telefonoSimpleTO.setCuenta(reservacionServiceTO.getCuenta());
        telefonoSimpleTO.setSecuencia(reservacionServiceTO.getSecuencia());
        telefonoSimpleTO.setLinea(reservacionServiceTO.getTelefono());
        telefonoSimpleTO.setRegion(reservacionServiceTO.getRegion());
        this.setTelefonoSimpleTO(telefonoSimpleTO);
        MobileTO mobileTO = new MobileTO();
        mobileTO.setEstCobranza(reservacionServiceTO.getEstatusCobranza());
        mobileTO.setMotivo(reservacionServiceTO.getMotivo());
        mobileTO.setPlanM2K(reservacionServiceTO.getPlanM2K());
        this.setMobileTO(mobileTO);
        PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
        puntoVentaTO.setPtoVenta(reservacionServiceTO.getPuntoVenta());
        UsuarioTO usuarioTO = new UsuarioTO();
        usuarioTO.setIdUsuario(reservacionServiceTO.getUsuario());
        usuarioTO.setRegion(reservacionServiceTO.getRegion());
        this.setFuerzaVenta(reservacionServiceTO.getFuerzaVenta());
        usuarioTO.setPuntoVentaTO(puntoVentaTO);
        this.setUsuarioTO(usuarioTO);
        PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO();
        puntosRedimidosTO.setPtosDisponibles(reservacionServiceTO.getPuntosDisponibles());
        this.setPuntosRedimidosTO(puntosRedimidosTO);
        ProductosTO productosTO = new ProductosTO();
        productosTO.setIdProducto(reservacionServiceTO.getIdProducto());
        productosTO.setDifPesos(reservacionServiceTO.getValorPesos());
        productosTO.setValorPuntos(reservacionServiceTO.getValorPuntos());
        productosTO.setPtosARedimir(reservacionServiceTO.getPuntosaRedimir());
        productosTO.setDescripcion(reservacionServiceTO.getDescripcion());
        productosTO.setMarca(reservacionServiceTO.getMarca());
        productosTO.setModelo(reservacionServiceTO.getModelo());
        productosTO.setTipoPromocion(reservacionServiceTO.getTipoPromocion());
        productosTO.setPrecioIva(new BigDecimal(reservacionServiceTO.getPrecioIVA()));
        productosTO.setPrecio(new BigDecimal(reservacionServiceTO.getPrecio()));
        productosTO.setBonosAltoValor(reservacionServiceTO.getBonoAltoValor());
        productosTO.setBonosRoext(reservacionServiceTO.getBonoRoext());
        productosTO.setDescuento(new BigDecimal(reservacionServiceTO.getDescuento()));
        productosTO.setAplicaPromocionGap(reservacionServiceTO.getAplicaPromocionGap());
        productosTO.setAplicaEP(reservacionServiceTO.getAplicaEP());
        productosTO.setBonoDescuentoGap(reservacionServiceTO.getBonoDescuentoGap());
        productosTO.setProductoM2KGap(reservacionServiceTO.getProductoM2KGap());
        productosTO.setNombrePromocionGap(reservacionServiceTO.getNombrePromocionGap());
        productosTO.setBonosGap(reservacionServiceTO.getBonosGap());
        productosTO.setIdPromocionGapCA(reservacionServiceTO.getIdPromocionGapCA());
        productosTO.setIdPromocionGap(reservacionServiceTO.getIdPromocionGap());
        productosTO.setVerPromocionGap(reservacionServiceTO.getVerPromocionGap());
        this.setProductosTO(productosTO);
        this.setComentario(reservacionServiceTO.getComentario());
        this.setPlanNuevo(reservacionServiceTO.getPlanNuevo());
        this.setTipoRedencion(reservacionServiceTO.getTipoRedencion());
        this.setFechaAdendumAnterior(Utils.DATEFORMATyyyy_MM_dd.parse(reservacionServiceTO.getFechaAddendumM2K()));
        this.setPlazoAnterior(reservacionServiceTO.getAddendumM2K());
        this.setPlazoNuevo(reservacionServiceTO.getAddendumNuevo());
        this.setFormaRedencion(reservacionServiceTO.getFormaRedencion());
        this.setPtsSobrantes(reservacionServiceTO.getPuntosSobrantes());
        this.setEstatus(reservacionServiceTO.getEstatus());
    }

    public Date getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

