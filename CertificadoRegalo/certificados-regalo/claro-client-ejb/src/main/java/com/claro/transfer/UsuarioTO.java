/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntoVentaTO;
import java.io.Serializable;
import java.sql.Timestamp;

public class UsuarioTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String numEmpleado;
    private int region;
    private String idUsuario;
    private String nombre;
    private String password;
    private String status;
    private Integer contadorPMP;
    private String idUsuarioCaptura;
    private Timestamp fechaUpdate;
    private String sistemaAdmin;
    private Timestamp fechaAdmin;
    private PuntoVentaTO puntoVentaTO;
    private PerfilTO perfilTO;
    private String perfil;
    private String passwordNuevo;
    private String confirmacionPassword;
    private String accion;

    public PerfilTO getPerfilTO() {
        if (this.perfilTO == null) {
            this.perfilTO = new PerfilTO();
        }
        return this.perfilTO;
    }

    public void setPerfilTO(PerfilTO perfilTO) {
        this.perfilTO = perfilTO;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String Perfil) {
        this.perfil = Perfil;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public PuntoVentaTO getPuntoVentaTO() {
        return this.puntoVentaTO;
    }

    public void setPuntoVentaTO(PuntoVentaTO puntoVentaTO) {
        this.puntoVentaTO = puntoVentaTO;
    }

    public String getNumEmpleado() {
        return this.numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String Accion) {
        this.accion = Accion;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getContadorPMP() {
        return this.contadorPMP;
    }

    public void setContadorPMP(Integer contadorPMP) {
        this.contadorPMP = contadorPMP;
    }

    public String getIdUsuarioCaptura() {
        return this.idUsuarioCaptura;
    }

    public void setIdUsuarioCaptura(String idUsuarioCaptura) {
        this.idUsuarioCaptura = idUsuarioCaptura;
    }

    public Timestamp getFechaUpdate() {
        return this.fechaUpdate;
    }

    public void setFechaUpdate(Timestamp fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public String getSistemaAdmin() {
        return this.sistemaAdmin;
    }

    public void setSistemaAdmin(String sistemaAdmin) {
        this.sistemaAdmin = sistemaAdmin;
    }

    public Timestamp getFechaAdmin() {
        return this.fechaAdmin;
    }

    public void setFechaAdmin(Timestamp fechaAdmin) {
        this.fechaAdmin = fechaAdmin;
    }

    public String getPasswordNuevo() {
        return this.passwordNuevo;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    public String getConfirmacionPassword() {
        return this.confirmacionPassword;
    }

    public void setConfirmacionPassword(String confirmacionPassword) {
        this.confirmacionPassword = confirmacionPassword;
    }

    public String toString() {
        return "[UsuariosTO] numEmpleado:" + this.numEmpleado + " idUsuario:" + this.idUsuario + " nombre:" + this.nombre + " password:" + this.password + " status:" + this.status + "contadorPMP:" + this.contadorPMP + " idUsuarioCaptura:" + this.idUsuarioCaptura + " fechaUpdate:" + this.fechaUpdate + " sistemaAdmin:" + this.sistemaAdmin + " fechaAdmin:" + this.fechaAdmin;
    }
}

