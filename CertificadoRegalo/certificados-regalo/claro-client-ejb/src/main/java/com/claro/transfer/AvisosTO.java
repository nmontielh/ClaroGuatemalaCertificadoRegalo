/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;
import java.sql.Timestamp;

public class AvisosTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private int idAviso;
    private String idUsuario;
    private String descripcion;
    private Timestamp fechaAlta;
    private Timestamp fechaActivacion;
    private Timestamp fechaExpiracion;
    private String tipoAviso;
    private String tipoMsg;
    private String estatus;
    private Timestamp fechaModificacion;
    private String idUsuarioMod;
    private String formatoDescripcion;

    public int getIdAviso() {
        return this.idAviso;
    }

    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaActivacion() {
        return this.fechaActivacion;
    }

    public void setFechaActivacion(Timestamp fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public Timestamp getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(Timestamp fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipoAviso() {
        return this.tipoAviso;
    }

    public void setTipoAviso(String tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public String getTipoMsg() {
        return this.tipoMsg;
    }

    public void setTipoMsg(String tipoMsg) {
        this.tipoMsg = tipoMsg;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Timestamp getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getIdUsuarioMod() {
        return this.idUsuarioMod;
    }

    public void setIdUsuarioMod(String idUsuarioMod) {
        this.idUsuarioMod = idUsuarioMod;
    }

    public String getFormatoDescripcion() {
        return this.formatoDescripcion;
    }

    public void setFormatoDescripcion(String formatoDescripcion) {
        this.formatoDescripcion = formatoDescripcion;
    }
}

