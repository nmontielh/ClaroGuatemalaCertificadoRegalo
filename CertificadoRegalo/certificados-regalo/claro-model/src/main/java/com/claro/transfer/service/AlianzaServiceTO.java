/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.AlianzasTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.util.Constantes;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlianzaServiceTO
implements Serializable {
    private static final long serialVersionUID = -6022606958056269122L;
    private String cuenta;
    private String linea;
    private String fechaOperacion;
    private String cuentaAlianza;
    private String usuario;
    private String comentario;
    private int puntosCanjeados;
    private int millas;
    private int valorCupon;

    public AlianzaServiceTO(AlianzasTO alianzasTO) {
        this.cuenta = alianzasTO.getTelefonoSimpleTO().getCuenta();
        this.linea = alianzasTO.getTelefonoSimpleTO().getLinea();
        this.fechaOperacion = Constantes.DATEFORMATdd_MM_YYYY.format(alianzasTO.getFechaOperacion());
        this.cuentaAlianza = alianzasTO.getCuentaAlianza();
        this.puntosCanjeados = alianzasTO.getPuntosRedimidosTO().getPtsCanjeados();
        this.millas = alianzasTO.getMillas();
        this.usuario = alianzasTO.getUsuario();
        this.comentario = alianzasTO.getComentario();
        this.valorCupon = alianzasTO.getValorCuponOrig();
    }

    public int getValorCupon() {
        return this.valorCupon;
    }

    public void setValorCupon(int valorCupon) {
        this.valorCupon = valorCupon;
    }

    public AlianzaServiceTO() {
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

    public String getCuentaAlianza() {
        return this.cuentaAlianza;
    }

    public void setCuentaAlianza(String cuentaAlianza) {
        this.cuentaAlianza = cuentaAlianza;
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

    public int getPuntosCanjeados() {
        return this.puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public int getMillas() {
        return this.millas;
    }

    public void setMillas(int millas) {
        this.millas = millas;
    }
}

