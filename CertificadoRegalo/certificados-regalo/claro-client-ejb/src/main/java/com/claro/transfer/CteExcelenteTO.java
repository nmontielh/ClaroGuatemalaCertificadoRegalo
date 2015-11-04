/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;
import java.util.Date;

public class CteExcelenteTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer ID;
    private String cuenta;
    private String linea;
    private Integer idRegion;
    private String accion;
    private String idUsuario;
    private Date fecha;
    private String estatus;

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer id) {
        this.ID = id;
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

    public Integer getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

