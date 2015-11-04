/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.UsuarioTO;
import java.io.Serializable;

public class UsuarioServiceTO
implements Serializable {
    private static final long serialVersionUID = -7319890221700201435L;
    private String mensaje;
    private String claveUsuario;
    private String nombreUsuario;
    private String puntoVenta;
    private String perfil;
    private String porcentajeIVA;
    private int idMensaje;
    private int regionPerfil;

    public UsuarioServiceTO(UsuarioTO usuarioTO) {
        this.claveUsuario = usuarioTO.getIdUsuario();
        this.nombreUsuario = usuarioTO.getNombre();
        this.puntoVenta = usuarioTO.getPuntoVentaTO().getPtoVenta();
        this.perfil = String.valueOf(usuarioTO.getPerfilTO().getIdPerfilN());
        this.porcentajeIVA = usuarioTO.getPuntoVentaTO().getPorcentajeIva().substring(2, usuarioTO.getPuntoVentaTO().getPorcentajeIva().length());
        this.idMensaje = usuarioTO.getIdMensaje();
        this.mensaje = usuarioTO.getMensaje();
        this.regionPerfil = usuarioTO.getPerfilTO() != null ? usuarioTO.getPerfilTO().getRegion() : 0;
    }

    public UsuarioServiceTO() {
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getClaveUsuario() {
        return this.claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPuntoVenta() {
        return this.puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPorcentajeIVA() {
        return this.porcentajeIVA;
    }

    public void setPorcentajeIVA(String porcentajeIVA) {
        this.porcentajeIVA = porcentajeIVA;
    }

    public int getIdMensaje() {
        return this.idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getRegionPerfil() {
        return this.regionPerfil;
    }

    public void setRegionPerfil(int regionPerfil) {
        this.regionPerfil = regionPerfil;
    }
}

