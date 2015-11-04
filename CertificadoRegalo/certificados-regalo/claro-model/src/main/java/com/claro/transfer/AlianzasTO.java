/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AlianzasTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String folio;
    private String cuentaAlianza;
    private int idCuentaAlianza;
    private int statusTrans;
    private int millas;
    private int numeroCanje;
    private int valorDls;
    private int valorCuponOrig;
    private int opcion;
    private Date fechaOperacion;
    private Date fechaUpd;
    private Date vigenciaMax;
    private String comentario;
    private String usuario;
    private String estatus;
    private String archivoSalida;
    private String claveFactura;
    private String idPuntoVenta;
    private String idReferencia;
    private String idUsuarioUpd;
    private String factor;
    private String millasMin;
    private String cuenta;
    private int secuencia;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String apPaterno;
    private String apMaterno;
    private String cLinea;
    private String cveAlianza;
    private String mensajeEstatusUltimoCanje;
    private String statusAlianza;
    private Integer porcAntig;
    private Integer porcArpu;
    private Integer porcCob;
    private Integer vCertif;
    private Integer vCentifExtra;
    private Timestamp fechaToper;
    private int ptsTransferidos;
    private String ptsTransferidosCF;
    private Timestamp fechaFolio;
    private PuntosRedimidosTO puntosRedimidosTO;
    private TelefonoSimpleTO telefonoSimpleTO;
    private ArrayList<AlianzasTO> cupones;
    private ArrayList<ProductosTO> productos;

    public ArrayList<AlianzasTO> getCupones() {
        return this.cupones;
    }

    public void setCupones(ArrayList<AlianzasTO> cupones) {
        this.cupones = cupones;
    }

    public ArrayList<ProductosTO> getProductos() {
        return this.productos;
    }

    public void setProductos(ArrayList<ProductosTO> productos) {
        this.productos = productos;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getCuentaAlianza() {
        return this.cuentaAlianza;
    }

    public void setCuentaAlianza(String cuentaAlianza) {
        this.cuentaAlianza = cuentaAlianza;
    }

    public int getIdCuentaAlianza() {
        return this.idCuentaAlianza;
    }

    public void setIdCuentaAlianza(int idCuentaAlianza) {
        this.idCuentaAlianza = idCuentaAlianza;
    }

    public int getStatusTrans() {
        return this.statusTrans;
    }

    public void setStatusTrans(int statusTrans) {
        this.statusTrans = statusTrans;
    }

    public int getMillas() {
        return this.millas;
    }

    public void setMillas(int millas) {
        this.millas = millas;
    }

    public int getNumeroCanje() {
        return this.numeroCanje;
    }

    public void setNumeroCanje(int numeroCanje) {
        this.numeroCanje = numeroCanje;
    }

    public int getValorDls() {
        return this.valorDls;
    }

    public void setValorDls(int valorDls) {
        this.valorDls = valorDls;
    }

    public int getValorCuponOrig() {
        return this.valorCuponOrig;
    }

    public void setValorCuponOrig(int valorCuponOrig) {
        this.valorCuponOrig = valorCuponOrig;
    }

    public int getOpcion() {
        return this.opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Date getFechaUpd() {
        return this.fechaUpd;
    }

    public void setFechaUpd(Date fechaUpd) {
        this.fechaUpd = fechaUpd;
    }

    public Date getVigenciaMax() {
        return this.vigenciaMax;
    }

    public void setVigenciaMax(Date vigenciaMax) {
        this.vigenciaMax = vigenciaMax;
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

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getArchivoSalida() {
        return this.archivoSalida;
    }

    public void setArchivoSalida(String archivoSalida) {
        this.archivoSalida = archivoSalida;
    }

    public String getClaveFactura() {
        return this.claveFactura;
    }

    public void setClaveFactura(String claveFactura) {
        this.claveFactura = claveFactura;
    }

    public String getIdPuntoVenta() {
        return this.idPuntoVenta;
    }

    public void setIdPuntoVenta(String idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

    public String getIdReferencia() {
        return this.idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getIdUsuarioUpd() {
        return this.idUsuarioUpd;
    }

    public void setIdUsuarioUpd(String idUsuarioUpd) {
        this.idUsuarioUpd = idUsuarioUpd;
    }

    public Timestamp getFechaFolio() {
        return this.fechaFolio;
    }

    public void setFechaFolio(Timestamp fechaFolio) {
        this.fechaFolio = fechaFolio;
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

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAPaterno() {
        return this.aPaterno;
    }

    public void setAPaterno(String paterno) {
        this.aPaterno = paterno;
    }

    public String getAMaterno() {
        return this.aMaterno;
    }

    public void setAMaterno(String materno) {
        this.aMaterno = materno;
    }

    public String getCLinea() {
        return this.cLinea;
    }

    public void setCLinea(String linea) {
        this.cLinea = linea;
    }

    public String getCveAlianza() {
        return this.cveAlianza;
    }

    public void setCveAlianza(String cveAlianza) {
        this.cveAlianza = cveAlianza;
    }

    public Integer getPorcAntig() {
        return this.porcAntig;
    }

    public void setPorcAntig(Integer porcAntig) {
        this.porcAntig = porcAntig;
    }

    public Integer getPorcArpu() {
        return this.porcArpu;
    }

    public void setPorcArpu(Integer porcArpu) {
        this.porcArpu = porcArpu;
    }

    public Integer getPorcCob() {
        return this.porcCob;
    }

    public void setPorcCob(Integer porcCob) {
        this.porcCob = porcCob;
    }

    public Integer getVCertif() {
        return this.vCertif;
    }

    public void setVCertif(Integer certif) {
        this.vCertif = certif;
    }

    public Integer getVCentifExtra() {
        return this.vCentifExtra;
    }

    public void setVCentifExtra(Integer centifExtra) {
        this.vCentifExtra = centifExtra;
    }

    public Timestamp getFechaToper() {
        return this.fechaToper;
    }

    public void setFechaToper(Timestamp fechaToper) {
        this.fechaToper = fechaToper;
    }

    public int getPtsTransferidos() {
        return this.ptsTransferidos;
    }

    public void setPtsTransferidos(int ptsTransferidos) {
        this.ptsTransferidos = ptsTransferidos;
    }

    public String getMensajeEstatusUltimoCanje() {
        return this.mensajeEstatusUltimoCanje;
    }

    public void setMensajeEstatusUltimoCanje(String mensajeEstatusUltimoCanje) {
        this.mensajeEstatusUltimoCanje = mensajeEstatusUltimoCanje;
    }

    public String getStatusAlianza() {
        return this.statusAlianza;
    }

    public void setStatusAlianza(String statusAlianza) {
        this.statusAlianza = statusAlianza;
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getMillasMin() {
        return this.millasMin;
    }

    public void setMillasMin(String millasMin) {
        this.millasMin = millasMin;
    }

    public String getPtsTransferidosCF() {
        if (this.ptsTransferidos > 0) {
            this.ptsTransferidosCF = Utils.setFormatoPtos(this.ptsTransferidos);
        }
        return this.ptsTransferidosCF;
    }

    public String getApPaterno() {
        this.apPaterno = this.aPaterno;
        return this.apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        this.apMaterno = this.aMaterno;
        return this.apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = this.aMaterno;
    }
}

