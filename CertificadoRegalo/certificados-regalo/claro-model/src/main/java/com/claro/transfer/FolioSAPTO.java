/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.sql.Timestamp;

public class FolioSAPTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -7692655089580911508L;
    private String esnimeir;
    private String esnimeip;
    private String sFechaFolio;
    private String folio;
    private String iccidant;
    private String iccidnvo;
    private String esnimeiant1;
    private String esnimeinvo1;
    private String esnimeiant2;
    private String esnimeinvo2;
    private String usuario;
    private String puntovta;
    private String cuenta;
    private String estatus;
    private Timestamp fechaFolio;
    private int diasTranscurridos;
    private int secuencia;

    public Timestamp getFechaFolio() {
        return this.fechaFolio;
    }

    public void setFechaFolio(Timestamp fechaFolio) {
        this.fechaFolio = fechaFolio;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEsnimeir() {
        return this.esnimeir;
    }

    public void setEsnimeir(String esnimeir) {
        this.esnimeir = esnimeir;
    }

    public String getEsnimeip() {
        return this.esnimeip;
    }

    public void setEsnimeip(String esnimeip) {
        this.esnimeip = esnimeip;
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

    public long getDiasTranscurridos() {
        if (this.fechaFolio != null) {
            return Utils.calcularDiasEntreFechas(this.fechaFolio.getTime(), System.currentTimeMillis());
        }
        return this.diasTranscurridos;
    }

    public void setDiasTranscurridos(int diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    public String getSFechaFolio() {
        return this.sFechaFolio;
    }

    public void setSFechaFolio(String fechaFolio) {
        this.sFechaFolio = fechaFolio;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIccidant() {
        return this.iccidant;
    }

    public void setIccidant(String iccidant) {
        this.iccidant = iccidant;
    }

    public String getIccidnvo() {
        return this.iccidnvo;
    }

    public void setIccidnvo(String iccidnvo) {
        this.iccidnvo = iccidnvo;
    }

    public String getEsnimeiant1() {
        return this.esnimeiant1;
    }

    public void setEsnimeiant1(String esnimeiant1) {
        this.esnimeiant1 = esnimeiant1;
    }

    public String getEsnimeinvo1() {
        return this.esnimeinvo1;
    }

    public void setEsnimeinvo1(String esnimeinvo1) {
        this.esnimeinvo1 = esnimeinvo1;
    }

    public String getEsnimeiant2() {
        return this.esnimeiant2;
    }

    public void setEsnimeiant2(String esnimeiant2) {
        this.esnimeiant2 = esnimeiant2;
    }

    public String getEsnimeinvo2() {
        return this.esnimeinvo2;
    }

    public void setEsnimeinvo2(String esnimeinvo2) {
        this.esnimeinvo2 = esnimeinvo2;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPuntovta() {
        return this.puntovta;
    }

    public void setPuntovta(String puntovta) {
        this.puntovta = puntovta;
    }
}

