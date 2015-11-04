/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.transpuntos;

import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import java.io.Serializable;

public class TransferenciaTO
implements Serializable {
    private static final long serialVersionUID = 1098520152549260617L;
    private String telefonoOrigen;
    private String cuentaOrigen;
    private int regionOrigen;
    private String tipoTransferencia;
    private String fecFactura;
    private String fechaAlta;
    private String telefonoDestino;
    private String cuentaDestino;
    private int regionDestino;
    private int puntosTrasnferidos;
    private String comentario;
    private String nombreClienteOrigen;
    private int ptosDisponiblesOrigen;
    private String tecnologiaOrigen;
    private TelefonoTO telefonoTO;
    private PuntosTO puntosOrigenTO;
    private String cuentaPadreOrigen;
    private String cuentaLineaOrigen;
    private String estatusLineaOrigen;
    private int secuenciaOrigen;
    private int secuenciaDestino;
    private String idUsuario;
    private String rfcOrigen;
    private PerfilTO perfilTO;

    public String getTelefonoOrigen() {
        return this.telefonoOrigen;
    }

    public void setTelefonoOrigen(String telefonoOrigen) {
        this.telefonoOrigen = telefonoOrigen;
    }

    public String getCuentaOrigen() {
        return this.cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getRegionOrigen() {
        return this.regionOrigen;
    }

    public void setRegionOrigen(int regionOrigen) {
        this.regionOrigen = regionOrigen;
    }

    public String getTipoTransferencia() {
        return this.tipoTransferencia;
    }

    public void setTipoTransferencia(String tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }

    public String getTelefonoDestino() {
        return this.telefonoDestino;
    }

    public void setTelefonoDestino(String telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public String getCuentaDestino() {
        return this.cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getRegionDestino() {
        return this.regionDestino;
    }

    public void setRegionDestino(int regionDestino) {
        this.regionDestino = regionDestino;
    }

    public int getPuntosTrasnferidos() {
        return this.puntosTrasnferidos;
    }

    public void setPuntosTrasnferidos(int puntosTrasnferidos) {
        this.puntosTrasnferidos = puntosTrasnferidos;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombreClienteOrigen() {
        return this.nombreClienteOrigen;
    }

    public void setNombreClienteOrigen(String nombreClienteOrigen) {
        this.nombreClienteOrigen = nombreClienteOrigen;
    }

    public int getPtosDisponiblesOrigen() {
        return this.ptosDisponiblesOrigen;
    }

    public void setPtosDisponiblesOrigen(int ptosDisponiblesOrigen) {
        this.ptosDisponiblesOrigen = ptosDisponiblesOrigen;
    }

    public String getTecnologiaOrigen() {
        return this.tecnologiaOrigen;
    }

    public void setTecnologiaOrigen(String tecnologiaOrigen) {
        this.tecnologiaOrigen = tecnologiaOrigen;
    }

    public String getCuentaPadreOrigen() {
        return this.cuentaPadreOrigen;
    }

    public void setCuentaPadreOrigen(String cuentaPadreOrigen) {
        this.cuentaPadreOrigen = cuentaPadreOrigen;
    }

    public String getCuentaLineaOrigen() {
        return this.cuentaLineaOrigen;
    }

    public void setCuentaLineaOrigen(String cuentaLineaOrigen) {
        this.cuentaLineaOrigen = cuentaLineaOrigen;
    }

    public String getEstatusLineaOrigen() {
        return this.estatusLineaOrigen;
    }

    public void setEstatusLineaOrigen(String estatusLineaOrigen) {
        this.estatusLineaOrigen = estatusLineaOrigen;
    }

    public int getSecuenciaOrigen() {
        return this.secuenciaOrigen;
    }

    public void setSecuenciaOrigen(int secuenciaOrigen) {
        this.secuenciaOrigen = secuenciaOrigen;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TelefonoTO getTelefonoTO() {
        return this.telefonoTO;
    }

    public void setTelefonoTO(TelefonoTO telefonoTO) {
        this.telefonoTO = telefonoTO;
    }

    public PuntosTO getPuntosOrigenTO() {
        return this.puntosOrigenTO;
    }

    public void setPuntosOrigenTO(PuntosTO puntosOrigenTO) {
        this.puntosOrigenTO = puntosOrigenTO;
    }

    public String getFecFactura() {
        return this.fecFactura;
    }

    public void setFecFactura(String fecFactura) {
        this.fecFactura = fecFactura;
    }

    public String getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getRfcOrigen() {
        return this.rfcOrigen;
    }

    public void setRfcOrigen(String rfcOrigen) {
        this.rfcOrigen = rfcOrigen;
    }

    public PerfilTO getPerfilTO() {
        return this.perfilTO;
    }

    public void setPerfilTO(PerfilTO perfilTO) {
        this.perfilTO = perfilTO;
    }

    public int getSecuenciaDestino() {
        return this.secuenciaDestino;
    }

    public void setSecuenciaDestino(int secuenciaDestino) {
        this.secuenciaDestino = secuenciaDestino;
    }
}

