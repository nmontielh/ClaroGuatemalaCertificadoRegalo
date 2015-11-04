/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.LineasTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.UsuarioTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RedencionTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private PuntosRedimidosTO puntosRedimidosTO;
    private TelefonoSimpleTO telefonoSimpleTO;
    private ProductosTO productosTO;
    private LineasTO lineasTO;
    private UsuarioTO usuarioTO;
    private MobileTO mobileTO;
    private String folio;
    private String folioHex;
    private Date fechaOperacion;
    private Date fechaAdendumAnterior;
    private Date fechaAdendumNuevo;
    private Date fechaPlazoSeg;
    private String comentario;
    private String fechaAdendum;
    private String precioConFormato;
    private String tipoRedencion;
    private String estatus;
    private String idPlan;
    private String planNuevo;
    private String plazoNuevo;
    private String plazoAnterior;
    private String formaRedencion;
    private String fuerzaVenta;
    private String direccionIP;
    private Timestamp fechaFolio;
    private long fechaOperacionlong;
    private long fechaFoliolong;
    private int region;
    private int sobrantesBono;
    private int ptsSobrantes;
    private int puntosSobrantes1;
    private int addendumNuevo;
    private int idMovimiento;
    private String planAnterior;
    private int addendumAnterior;
    private int bonoProrrateo;
    private int ptsMinimos;
    private String planDescuento;
    private int tipoRedPromOnline;
    private int origenRedOnline;

    public long getFechaOperacionlong() {
        if (this.fechaOperacion != null) {
            return this.fechaOperacion.getTime();
        }
        return this.fechaOperacionlong;
    }

    public void setFechaOperacionlong(long fechaOperacionlong) {
        this.fechaOperacionlong = fechaOperacionlong;
    }

    public int getBonoProrrateo() {
        return this.bonoProrrateo;
    }

    public void setBonoProrrateo(int bonoProrrateo) {
        this.bonoProrrateo = bonoProrrateo;
    }

    public long getDiasTranscurridos() {
        return Utils.calcularDiasEntreFechas(this.fechaOperacion.getTime(), System.currentTimeMillis());
    }

    public int getIdMovimiento() {
        return this.idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getAddendumNuevo() {
        return this.addendumNuevo;
    }

    public void setAddendumNuevo(int addendumNuevo) {
        this.addendumNuevo = addendumNuevo;
    }

    public int getPuntosSobrantes1() {
        return this.puntosSobrantes1;
    }

    public void setPuntosSobrantes1(int puntosSobrantes1) {
        this.puntosSobrantes1 = puntosSobrantes1;
    }

    public int getPtsSobrantes() {
        return this.ptsSobrantes;
    }

    public void setPtsSobrantes(int ptsSobrantes) {
        this.ptsSobrantes = ptsSobrantes;
    }

    public int getSobrantesBono() {
        return this.sobrantesBono;
    }

    public void setSobrantesBono(int sobrantesBono) {
        this.sobrantesBono = sobrantesBono;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public PuntosRedimidosTO getPuntosRedimidosTO() {
        return this.puntosRedimidosTO;
    }

    public void setPuntosRedimidosTO(PuntosRedimidosTO puntosRedimidosTO) {
        this.puntosRedimidosTO = puntosRedimidosTO;
    }

    public TelefonoSimpleTO getTelefonoSimpleTO() {
        return this.telefonoSimpleTO;
    }

    public void setTelefonoSimpleTO(TelefonoSimpleTO telefonoSimpleTO) {
        this.telefonoSimpleTO = telefonoSimpleTO;
    }

    public LineasTO getLineasTO() {
        return this.lineasTO;
    }

    public void setLineasTO(LineasTO lineasTO) {
        this.lineasTO = lineasTO;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
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

    public String getPrecioConFormato() {
        return this.precioConFormato;
    }

    public void setPrecioConFormato(String precioConFormato) {
        this.precioConFormato = precioConFormato;
    }

    public Timestamp getFechaFolio() {
        return this.fechaFolio;
    }

    public void setFechaFolio(Timestamp fechaFolio) {
        this.fechaFolio = fechaFolio;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public UsuarioTO getUsuarioTO() {
        return this.usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getIdPlan() {
        return this.idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public void setPlanNuevo(String planNuevo) {
        this.planNuevo = planNuevo;
    }

    public Date getFechaAdendumAnterior() {
        return this.fechaAdendumAnterior;
    }

    public void setFechaAdendumAnterior(Date fechaAdendumAnterior) {
        this.fechaAdendumAnterior = fechaAdendumAnterior;
    }

    public Date getFechaAdendumNuevo() {
        return this.fechaAdendumNuevo;
    }

    public void setFechaAdendumNuevo(Date fechaAdendumNuevo) {
        this.fechaAdendumNuevo = fechaAdendumNuevo;
    }

    public String getPlazoNuevo() {
        return this.plazoNuevo;
    }

    public void setPlazoNuevo(String plazoNuevo) {
        this.plazoNuevo = plazoNuevo;
    }

    public String getPlazoAnterior() {
        return this.plazoAnterior;
    }

    public void setPlazoAnterior(String plazoAnterior) {
        this.plazoAnterior = plazoAnterior;
    }

    public String getFormaRedencion() {
        return this.formaRedencion;
    }

    public void setFormaRedencion(String formaRedencion) {
        this.formaRedencion = formaRedencion;
    }

    public String getFuerzaVenta() {
        return this.fuerzaVenta;
    }

    public void setFuerzaVenta(String fuerzaVenta) {
        this.fuerzaVenta = fuerzaVenta;
    }

    public ProductosTO getProductosTO() {
        if (this.productosTO == null) {
            this.productosTO = new ProductosTO();
        }
        return this.productosTO;
    }

    public void setProductosTO(ProductosTO productosTO) {
        this.productosTO = productosTO;
    }

    public String toString() {
        return String.valueOf(this.productosTO.getDescripcion()) + "|" + this.productosTO.getMarca() + "|" + this.productosTO.getModelo();
    }

    public String getPlanAnterior() {
        return this.planAnterior;
    }

    public void setPlanAnterior(String planAnterior) {
        this.planAnterior = planAnterior;
    }

    public int getAddendumAnterior() {
        return this.addendumAnterior;
    }

    public void setAddendumAnterior(int addendumAnterior) {
        this.addendumAnterior = addendumAnterior;
    }

    public int getPtsMinimos() {
        return this.ptsMinimos;
    }

    public void setPtsMinimos(int ptsMinimos) {
        this.ptsMinimos = ptsMinimos;
    }

    public long getFechaFoliolong() {
        if (this.fechaFolio != null) {
            return this.fechaFolio.getTime();
        }
        return this.fechaFoliolong;
    }

    public Date getFechaPlazoSeg() {
        return this.fechaPlazoSeg;
    }

    public void setFechaPlazoSeg(Date fechaPlazoSeg) {
        this.fechaPlazoSeg = fechaPlazoSeg;
    }

    public MobileTO getMobileTO() {
        return this.mobileTO;
    }

    public void setMobileTO(MobileTO mobileTO) {
        this.mobileTO = mobileTO;
    }

    public String getFolioHex() {
        return this.folioHex;
    }

    public void setFolioHex(String folioHex) {
        this.folioHex = folioHex;
    }

    public String getDireccionIP() {
        return this.direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getPlanDescuento() {
        return this.planDescuento;
    }

    public void setPlanDescuento(String planDescuento) {
        this.planDescuento = planDescuento;
    }

    public int getTipoRedPromOnline() {
        return this.tipoRedPromOnline;
    }

    public void setTipoRedPromOnline(int tipoRedPromOnline) {
        this.tipoRedPromOnline = tipoRedPromOnline;
    }

    public int getOrigenRedOnline() {
        return this.origenRedOnline;
    }

    public void setOrigenRedOnline(int origenRedOnline) {
        this.origenRedOnline = origenRedOnline;
    }
}

