/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.util.Constantes;
import com.claro.util.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PuntosTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String ptosStatus;
    private int ptsRedimidos;
    private int ptsTransferidos;
    private int ptsVencidos;
    private int ptsPorVencer;
    private int ptsPorVencer2;
    private int ptsPorVencer1;
    private int ptsRenta;
    private int ptsExcedentes;
    private int ptsPorAntiguedad;
    private int ptsPromocion;
    private int ptsSaldoAnt;
    private int ptsSubasta;
    private int ptosDisponibles;
    private int ptosDisponiblesTmp;
    private int ptsAntiguedad;
    private int ptsDisReserva;
    private int bonoEquipo;
    private int ptsTotales;
    private java.sql.Date fecVencer;
    private java.sql.Date fecVencer2;
    private java.sql.Date fecVencer1;
    private java.sql.Date fecVencerTmp;
    private java.sql.Date fecVencer2Tmp;
    private java.sql.Date fecVencer1Tmp;
    private java.sql.Date fecVencidos;
    private java.sql.Date fecReservacion;
    private java.sql.Date fecFactura;
    private boolean bandVencer60Dias;
    private String fecReservacionCF;
    private String descPtsReservados;
    private String distribuidorReserva;
    private String ptsBonoProm;
    private String bBono;
    private String estatusPuntos;
    private String ptsAsignados;

    public String getEstatusPuntos() {
        return this.estatusPuntos;
    }

    public void setEstatusPuntos(String estatusPuntos) {
        this.estatusPuntos = estatusPuntos;
    }

    public String getBBono() {
        return this.bBono;
    }

    public void setBBono(String bono) {
        this.bBono = bono;
    }

    public String getPtosStatus() {
        return this.ptosStatus;
    }

    public void setPtosStatus(String ptosStatus) {
        this.ptosStatus = ptosStatus;
    }

    public int getPtsRedimidos() {
        return this.ptsRedimidos;
    }

    public void setPtsRedimidos(int ptsRedimidos) {
        this.ptsRedimidos = ptsRedimidos;
    }

    public int getPtsTransferidos() {
        return this.ptsTransferidos;
    }

    public void setPtsTransferidos(int ptsTransferidos) {
        this.ptsTransferidos = ptsTransferidos;
    }

    public int getPtsVencidos() {
        return this.ptsVencidos;
    }

    public void setPtsVencidos(int ptsVencidos) {
        this.ptsVencidos = ptsVencidos;
    }

    public int getPtsPorVencer() {
        return this.ptsPorVencer;
    }

    public void setPtsPorVencer(int ptsPorVencer) {
        this.ptsPorVencer = ptsPorVencer;
    }

    public int getPtsPorVencer2() {
        return this.ptsPorVencer2;
    }

    public void setPtsPorVencer2(int ptsPorVencer2) {
        this.ptsPorVencer2 = ptsPorVencer2;
    }

    public int getPtsPorVencer1() {
        return this.ptsPorVencer1;
    }

    public void setPtsPorVencer1(int ptsPorVencer1) {
        this.ptsPorVencer1 = ptsPorVencer1;
    }

    public int getPtsRenta() {
        return this.ptsRenta;
    }

    public void setPtsRenta(int ptsRenta) {
        this.ptsRenta = ptsRenta;
    }

    public int getPtsExcedentes() {
        return this.ptsExcedentes;
    }

    public void setPtsExcedentes(int ptsExcedentes) {
        this.ptsExcedentes = ptsExcedentes;
    }

    public int getPtsPorAntiguedad() {
        return this.ptsPorAntiguedad;
    }

    public void setPtsPorAntiguedad(int ptsPorAntiguedad) {
        this.ptsPorAntiguedad = ptsPorAntiguedad;
    }

    public int getPtsPromocion() {
        return this.ptsPromocion;
    }

    public void setPtsPromocion(int ptsPromocion) {
        this.ptsPromocion = ptsPromocion;
    }

    public int getPtsSaldoAnt() {
        return this.ptsSaldoAnt;
    }

    public void setPtsSaldoAnt(int ptsSaldoAnt) {
        this.ptsSaldoAnt = ptsSaldoAnt;
    }

    public int getPtsSubasta() {
        return this.ptsSubasta;
    }

    public void setPtsSubasta(int ptsSubasta) {
        this.ptsSubasta = ptsSubasta;
    }

    public int getBonoEquipo() {
        return this.bonoEquipo;
    }

    public void setBonoEquipo(int bonoEquipo) {
        this.bonoEquipo = bonoEquipo;
    }

    public int getPtosDisponibles() {
        this.ptosDisponibles = this.ptsExcedentes + this.ptsPorVencer + this.ptsPorVencer1 + this.ptsPorVencer2 + this.ptsRenta + this.ptsAntiguedad + this.ptsPromocion;
        return this.ptosDisponibles;
    }

    public void setPtosDisponibles(int ptosDisponibles) {
        this.ptosDisponibles = ptosDisponibles;
    }

    public int getPtosTotalesTemp() {
        return this.ptosDisponibles;
    }

    public java.sql.Date getFecVencer() {
        this.setFecVencerTmp(this.fecVencer);
        if (this.ptsPorVencer == 0) {
            this.fecVencer = null;
            return this.fecVencer;
        }
        return this.fecVencer;
    }

    public void setFecVencer(java.sql.Date fecVencer) {
        this.fecVencer = fecVencer;
    }

    public java.sql.Date getFecVencer2() {
        this.setFecVencer2Tmp(this.fecVencer2);
        if (this.ptsPorVencer2 == 0) {
            this.fecVencer2 = null;
            return this.fecVencer2;
        }
        return this.fecVencer2;
    }

    public void setFecVencer2(java.sql.Date fecVencer2) {
        this.fecVencer2 = fecVencer2;
    }

    public java.sql.Date getFecVencer1() {
        this.setFecVencer1Tmp(this.fecVencer1);
        if (this.ptsPorVencer1 == 0) {
            this.fecVencer1 = null;
            return this.fecVencer1;
        }
        return this.fecVencer1;
    }

    public void setFecVencer1(java.sql.Date fecVencer1) {
        this.fecVencer1 = fecVencer1;
    }

    public java.sql.Date getFecVencidos() {
        return this.fecVencidos;
    }

    public void setFecVencidos(java.sql.Date fecVencidos) {
        this.fecVencidos = fecVencidos;
    }

    public String getPtsDisReservaCF_() {
        return Utils.setFormatoPtos(this.ptsDisReserva);
    }

    public int getPtsDisReserva() {
        return this.ptsDisReserva;
    }

    public void setPtsDisReserva(int ptsDisReserva) {
        this.ptsDisReserva = ptsDisReserva;
    }

    public String getPtsBonoProm() {
        return this.ptsBonoProm;
    }

    public void setPtsBonoProm(String ptsBonoProm) {
        this.ptsBonoProm = ptsBonoProm;
    }

    public int getPtsAntiguedad() {
        return this.ptsAntiguedad;
    }

    public void setPtsAntiguedad(int ptsAntiguedad) {
        this.ptsAntiguedad = ptsAntiguedad;
    }

    public boolean isBandVencer60Dias() {
        return this.bandVencer60Dias;
    }

    public void setBandVencer60Dias(boolean bandVencer60Dias) {
        this.bandVencer60Dias = bandVencer60Dias;
    }

    public String getPtsRedimidosCF() {
        return Utils.setFormatoPtos(this.ptsRedimidos);
    }

    public String getPtsTransferidosCF() {
        return Utils.setFormatoPtos(this.ptsTransferidos);
    }

    public String getPtsVencidosCF() {
        return Utils.setFormatoPtos(this.ptsVencidos);
    }

    public String getPtsPorVencerCF() {
        return Utils.setFormatoPtos(this.ptsPorVencer);
    }

    public String getPtsPorVencer2CF() {
        return Utils.setFormatoPtos(this.ptsPorVencer2);
    }

    public String getPtsPorVencer1CF() {
        return Utils.setFormatoPtos(this.ptsPorVencer1);
    }

    public String getPtsRentaCF() {
        return Utils.setFormatoPtos(this.ptsRenta);
    }

    public String getPtsExcedentesCF() {
        return Utils.setFormatoPtos(this.ptsExcedentes);
    }

    public String getPtsPorAntiguedadCF() {
        return Utils.setFormatoPtos(this.ptsPorAntiguedad);
    }

    public String getPtsPromocionCF() {
        return Utils.setFormatoPtos(this.ptsPromocion);
    }

    public String getPtsSaldoAntCF() {
        return Utils.setFormatoPtos(this.ptsSaldoAnt);
    }

    public String getPtsSubastaCF() {
        return Utils.setFormatoPtos(this.ptsSubasta);
    }

    public String getPtosDisponiblesCF() {
        return Utils.setFormatoPtos(this.getPtosDisponibles());
    }

    public String getPtsAntiguedadCF() {
        return Utils.setFormatoPtos(this.ptsAntiguedad);
    }

    public String getDescPtsReservados() {
        return this.descPtsReservados;
    }

    public void setDescPtsReservados(String descPtsReservados) {
        this.descPtsReservados = descPtsReservados;
    }

    public java.sql.Date getFecReservacion() {
        return this.fecReservacion;
    }

    public void setFecReservacion(java.sql.Date fecReservacion) {
        this.fecReservacion = fecReservacion;
    }

    public String getFecReservacionCF() {
        try {
            return Constantes.DATEFORMATdd_MM_YYYY.format(this.fecReservacion);
        }
        catch (Exception e) {
            return this.fecReservacionCF;
        }
    }

    public String getDistribuidorReserva() {
        return this.distribuidorReserva;
    }

    public void setDistribuidorReserva(String distribuidorReserva) {
        this.distribuidorReserva = distribuidorReserva;
    }

    public String getBonoEquipoCF() {
        return Utils.setFormatoPtos(this.bonoEquipo);
    }

    public int getPtosDisponiblesTmp() {
        return this.ptosDisponiblesTmp;
    }

    public void setPtosDisponiblesTmp(int ptosDisponiblesTmp) {
        this.ptosDisponiblesTmp = ptosDisponiblesTmp;
    }

    public java.sql.Date getFecVencerTmp() {
        return this.fecVencerTmp;
    }

    public void setFecVencerTmp(java.sql.Date fecVencerTmp) {
        this.fecVencerTmp = fecVencerTmp;
    }

    public java.sql.Date getFecVencer2Tmp() {
        return this.fecVencer2Tmp;
    }

    public void setFecVencer2Tmp(java.sql.Date fecVencer2Tmp) {
        this.fecVencer2Tmp = fecVencer2Tmp;
    }

    public java.sql.Date getFecVencer1Tmp() {
        return this.fecVencer1Tmp;
    }

    public void setFecVencer1Tmp(java.sql.Date fecVencer1Tmp) {
        this.fecVencer1Tmp = fecVencer1Tmp;
    }

    public java.sql.Date getFecFactura() {
        return this.fecFactura;
    }

    public void setFecFactura(java.sql.Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public int getPtsTotales() {
        this.ptsTotales = this.ptsPorVencer + this.ptsPorVencer1 + this.ptsPorVencer2 + this.ptsPromocion + this.ptsAntiguedad + this.ptsExcedentes + this.ptsRenta;
        return this.ptsTotales;
    }

    public void setPtsTotales(int ptsTotales) {
        this.ptsTotales = ptsTotales;
    }

    public String getPtsAsignados() {
        return this.ptsAsignados;
    }

    public void setPtsAsignados(String ptsAsignados) {
        this.ptsAsignados = ptsAsignados;
    }
}

