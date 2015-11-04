/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.PuntosTO;
import java.io.Serializable;
import java.sql.Date;

public class PuntosRedimidosTO
extends PuntosTO
implements Serializable {
    private static final long serialVersionUID = 7060777587156048745L;
    private int ptsPorVencerRedimidos;
    private int ptsPorVencer1Redimidos;
    private int ptsPorVencer2Redimidos;
    private int ptsRentaRedimidos;
    private int ptsExcedentesRedimidos;
    private int ptsPorAntiguedadRedimidos;
    private int ptsPromocionRedimidos;
    private int ptsCanjeados;
    private int ptsAcumulados;
    private int ptsTotaltes;
    private int ptsAcumRestantes;
    private int ptsSobrantes;
    private int ptsSobrantes1;
    private int ptsMinimos;
    private String ptsTotaltesconFormato;
    private int bonoProrrateo;

    public int getBonoProrrateo() {
        return this.bonoProrrateo;
    }

    public void setBonoProrrateo(int bonoProrrateo) {
        this.bonoProrrateo = bonoProrrateo;
    }

    public String getPtsTotaltesconFormato() {
        return this.ptsTotaltesconFormato;
    }

    public void setPtsTotaltesconFormato(String ptsTotaltesconFormato) {
        this.ptsTotaltesconFormato = ptsTotaltesconFormato;
    }

    public PuntosRedimidosTO(PuntosTO puntosTO) {
        this.setPtsPorVencer(puntosTO.getPtsPorVencer());
        this.setPtsPorVencer1(puntosTO.getPtsPorVencer1());
        this.setPtsPorVencer2(puntosTO.getPtsPorVencer2());
        this.setPtsPromocion(puntosTO.getPtsPromocion());
        this.setPtsAntiguedad(puntosTO.getPtsAntiguedad());
        this.setPtsExcedentes(puntosTO.getPtsExcedentes());
        this.setPtsRenta(puntosTO.getPtsRenta());
        this.setFecVencer(puntosTO.getFecVencer());
        this.setFecVencerTmp(puntosTO.getFecVencerTmp());
        this.setFecVencer1(puntosTO.getFecVencer1());
        this.setFecVencer1Tmp(puntosTO.getFecVencer1Tmp());
        this.setFecVencer2(puntosTO.getFecVencer2());
        this.setFecVencer2Tmp(puntosTO.getFecVencer2Tmp());
        this.setPtsRedimidos(puntosTO.getPtsRedimidos());
        this.setPtosDisponiblesTmp(puntosTO.getPtosDisponiblesTmp());
        this.setBonoEquipo(puntosTO.getBonoEquipo());
        this.setIdMensaje(puntosTO.getIdMensaje());
        this.setMensaje(puntosTO.getMensaje());
    }

    public int getPtsSobrantes() {
        return this.ptsSobrantes;
    }

    public void setPtsSobrantes(int ptsSobrantes) {
        this.ptsSobrantes = ptsSobrantes;
    }

    public int getPtsSobrantes1() {
        return this.ptsSobrantes1;
    }

    public void setPtsSobrantes1(int ptsSobrantes1) {
        this.ptsSobrantes1 = ptsSobrantes1;
    }

    public int getPtsMinimos() {
        return this.ptsMinimos;
    }

    public void setPtsMinimos(int ptsMinimos) {
        this.ptsMinimos = ptsMinimos;
    }

    public PuntosRedimidosTO() {
    }

    public int getPtsCanjeados() {
        return this.ptsCanjeados;
    }

    public void setPtsCanjeados(int ptsCanjeados) {
        this.ptsCanjeados = ptsCanjeados;
    }

    public int getPtsAcumulados() {
        return this.ptsAcumulados;
    }

    public void setPtsAcumulados(int ptsAcumulados) {
        this.ptsAcumulados = ptsAcumulados;
    }

    public int getPtsTotaltes() {
        return this.ptsTotaltes;
    }

    public void setPtsTotaltes(int ptsTotaltes) {
        this.ptsTotaltes = ptsTotaltes;
    }

    public int getPtsAcumRestantes() {
        return this.ptsAcumRestantes;
    }

    public void setPtsAcumRestantes(int ptsAcumRestantes) {
        this.ptsAcumRestantes = ptsAcumRestantes;
    }

    public int getPtsPorVencerRedimidos() {
        return this.ptsPorVencerRedimidos;
    }

    public void setPtsPorVencerRedimidos(int ptsPorVencerRedimidos) {
        this.ptsPorVencerRedimidos = ptsPorVencerRedimidos;
    }

    public int getPtsPorVencer1Redimidos() {
        return this.ptsPorVencer1Redimidos;
    }

    public void setPtsPorVencer1Redimidos(int ptsPorVencer1Redimidos) {
        this.ptsPorVencer1Redimidos = ptsPorVencer1Redimidos;
    }

    public int getPtsPorVencer2Redimidos() {
        return this.ptsPorVencer2Redimidos;
    }

    public void setPtsPorVencer2Redimidos(int ptsPorVencer2Redimidos) {
        this.ptsPorVencer2Redimidos = ptsPorVencer2Redimidos;
    }

    public int getPtsRentaRedimidos() {
        return this.ptsRentaRedimidos;
    }

    public void setPtsRentaRedimidos(int ptsRentaRedimidos) {
        this.ptsRentaRedimidos = ptsRentaRedimidos;
    }

    public int getPtsExcedentesRedimidos() {
        return this.ptsExcedentesRedimidos;
    }

    public void setPtsExcedentesRedimidos(int ptsExcedentesRedimidos) {
        this.ptsExcedentesRedimidos = ptsExcedentesRedimidos;
    }

    public int getPtsPorAntiguedadRedimidos() {
        return this.ptsPorAntiguedadRedimidos;
    }

    public void setPtsPorAntiguedadRedimidos(int ptsPorAntiguedadRedimidos) {
        this.ptsPorAntiguedadRedimidos = ptsPorAntiguedadRedimidos;
    }

    public int getPtsPromocionRedimidos() {
        return this.ptsPromocionRedimidos;
    }

    public void setPtsPromocionRedimidos(int ptsPromocionRedimidos) {
        this.ptsPromocionRedimidos = ptsPromocionRedimidos;
    }

    public int getTotalRentaRedimidos() {
        return this.getPtsRenta() + this.ptsRentaRedimidos;
    }

    public int getTotalRedimidosPorVencer2() {
        return this.getPtsPorVencer2() + this.ptsPorVencer2Redimidos;
    }

    public int getTotalRedimidosPorVencer1() {
        return this.getPtsPorVencer1() + this.ptsPorVencer1Redimidos;
    }

    public int getTotalRedimidosPorVencer() {
        return this.getPtsPorVencer() + this.ptsPorVencerRedimidos;
    }
}

