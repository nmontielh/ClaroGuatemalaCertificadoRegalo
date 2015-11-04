/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.util.Redencion;
import java.io.Serializable;

public class ParametrosTO
implements Serializable {
    private static final long serialVersionUID = 2094039818724429749L;
    private String folio;
    private String telefono;
    private String cuenta;
    private String planNvo;
    private String planAnt;
    private String tipoRed;
    private String formaRed;
    private String mesCareg;
    private String addCareg;
    private String usuariMovimiento;
    private String comentario;
    private String descFormaRed;
    private String descTipoRed;
    private String estatus;
    private String bRedencionAnct;
    private String numeroSerieT;
    private String numeroSerieP;
    private String iccid;
    private String ipAddress;
    private int opcion;
    private int region;
    private int puntosTotalesTmp;
    private int secuencia;
    private boolean consultaGeneral;
    private String fzaVentas;

    public boolean isConsultaGeneral() {
        return this.consultaGeneral;
    }

    public void setConsultaGeneral(boolean consultaGeneral) {
        this.consultaGeneral = consultaGeneral;
    }

    public String getNumeroSerieT() {
        return this.numeroSerieT;
    }

    public void setNumeroSerieT(String numeroSerieT) {
        this.numeroSerieT = numeroSerieT;
    }

    public String getNumeroSerieP() {
        return this.numeroSerieP;
    }

    public void setNumeroSerieP(String numeroSerieP) {
        this.numeroSerieP = numeroSerieP;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public int getOpcion() {
        return this.opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public String getBRedencionAnct() {
        return this.bRedencionAnct;
    }

    public void setBRedencionAnct(String redencionAnct) {
        this.bRedencionAnct = redencionAnct;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDescTipoRed() {
        return this.descTipoRed;
    }

    public void setDescTipoRed(String descTipoRed) {
        this.descTipoRed = descTipoRed;
    }

    public String getDescTipoRed(String tipoRed) {
        return Redencion.getDescTipoRed(tipoRed);
    }

    public String getDescFormaRed(String formaRed) {
        return Redencion.getDescFormaRed(formaRed);
    }

    public String getDescFormaRed() {
        return this.descFormaRed;
    }

    public void setDescFormaRed(String descFormaRed) {
        this.descFormaRed = descFormaRed;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuariMovimiento() {
        return this.usuariMovimiento;
    }

    public void setUsuariMovimiento(String usuariMovimiento) {
        this.usuariMovimiento = usuariMovimiento;
    }

    public String getFormaRed() {
        return this.formaRed;
    }

    public void setFormaRed(String formaRed) {
        this.formaRed = formaRed;
    }

    public String getTelefono() {
        if (!(this.telefono == null || this.telefono.trim().equals(""))) {
            return this.telefono.trim();
        }
        return null;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuenta() {
        if (!(this.cuenta == null || this.cuenta.trim().equals(""))) {
            return this.cuenta.trim();
        }
        return null;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPlanNvo() {
        return this.planNvo;
    }

    public void setPlanNvo(String planNvo) {
        this.planNvo = planNvo;
    }

    public String getTipoRed() {
        return this.tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
    }

    public String getMesCareg() {
        return this.mesCareg;
    }

    public void setMesCareg(String mesCareg) {
        this.mesCareg = mesCareg == null || mesCareg.trim().equals("") ? "0" : mesCareg;
    }

    public String getAddCareg() {
        return this.addCareg;
    }

    public void setAddCareg(String addCareg) {
        this.addCareg = addCareg == null || addCareg.trim().equals("") ? "0" : addCareg;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getPlanAnt() {
        return this.planAnt;
    }

    public void setPlanAnt(String planAnt) {
        this.planAnt = planAnt;
    }

    public int getPuntosTotalesTmp() {
        return this.puntosTotalesTmp;
    }

    public void setPuntosTotalesTmp(int puntosTotalesTmp) {
        this.puntosTotalesTmp = puntosTotalesTmp;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getFzaVentas() {
        return this.fzaVentas;
    }

    public void setFzaVentas(String fzaVentas) {
        this.fzaVentas = fzaVentas;
    }
}

