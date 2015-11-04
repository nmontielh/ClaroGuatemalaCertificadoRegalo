/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.transfer.MotivoTO;
import java.io.Serializable;
import java.util.ArrayList;

public class RetencionTO
implements Serializable {
    private static final long serialVersionUID = -2876315591050173269L;
    private int PorcARPU;
    private int PorcAntig;
    private int PorcCob;
    private int ValorCupon;
    private int iValorCuponExtra;
    private String telefono;
    private String cuenta;
    private String fechaOperacion;
    private String folio;
    private String vCertif;
    private String vCentifextra;
    private String motivo;
    private String fechaCaduca;
    private String estatus;
    private MensajeTO mensajeTO;
    private int iIndAntig = 0;
    private ArrayList<MotivoTO> motivos;

    public int getIIndAntig() {
        return this.iIndAntig;
    }

    public void setIIndAntig(int indAntig) {
        this.iIndAntig = indAntig;
    }

    public int getPorcARPU() {
        return this.PorcARPU;
    }

    public void setPorcARPU(int porcARPU) {
        this.PorcARPU = porcARPU;
    }

    public int getPorcAntig() {
        return this.PorcAntig;
    }

    public void setPorcAntig(int porcAntig) {
        this.PorcAntig = porcAntig;
    }

    public int getPorcCob() {
        return this.PorcCob;
    }

    public void setPorcCob(int porcCob) {
        this.PorcCob = porcCob;
    }

    public int getValorCupon() {
        return this.ValorCupon;
    }

    public void setValorCupon(int valorCupon) {
        this.ValorCupon = valorCupon;
    }

    public int getIValorCuponExtra() {
        return this.iValorCuponExtra;
    }

    public void setIValorCuponExtra(int valorCuponExtra) {
        this.iValorCuponExtra = valorCuponExtra;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getVCertif() {
        return this.vCertif;
    }

    public void setVCertif(String certif) {
        this.vCertif = certif;
    }

    public String getVCentifextra() {
        return this.vCentifextra;
    }

    public void setVCentifextra(String centifextra) {
        this.vCentifextra = centifextra;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaCaduca() {
        return this.fechaCaduca;
    }

    public void setFechaCaduca(String fechaCaduca) {
        this.fechaCaduca = fechaCaduca;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public MensajeTO getMensajeTO() {
        return this.mensajeTO;
    }

    public void setMensajeTO(MensajeTO mensajeTO) {
        this.mensajeTO = mensajeTO;
    }

    public ArrayList<MotivoTO> getMotivos() {
        return this.motivos;
    }

    public void setMotivos(ArrayList<MotivoTO> motivos) {
        this.motivos = motivos;
    }
}

