/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class PuntoVentaTO
implements Serializable {
    private static final long serialVersionUID = 5408897824792290650L;
    private String ptoVenta;
    private String accion;
    private String porcentajeIva;
    private String idPuntoVta;
    private String segmentoIP;
    private int rangoInf;
    private int rangoSup;
    private int idRegion;
    private int ivaProcentaje;
    private String tipoPuntovta;

    public String getIdPuntoVta() {
        if (this.idPuntoVta != null) {
            this.idPuntoVta = this.idPuntoVta.replace((CharSequence)"\"", (CharSequence)"&quot;");
        }
        return this.idPuntoVta;
    }

    public void setIdPuntoVta(String idPuntoVta) {
        this.idPuntoVta = idPuntoVta.toUpperCase();
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getSegmentoIP() {
        return this.segmentoIP;
    }

    public void setSegmentoIP(String segmentoIP) {
        this.segmentoIP = segmentoIP;
    }

    public int getRangoInf() {
        return this.rangoInf;
    }

    public void setRangoInf(int rangoInf) {
        this.rangoInf = rangoInf;
    }

    public int getRangoSup() {
        return this.rangoSup;
    }

    public void setRangoSup(int rangoSup) {
        this.rangoSup = rangoSup;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIvaProcentaje() {
        return this.ivaProcentaje;
    }

    public void setIvaProcentaje(int ivaProcentaje) {
        this.ivaProcentaje = ivaProcentaje;
    }

    public String getPtoVenta() {
        return this.ptoVenta;
    }

    public void setPtoVenta(String ptoVenta) {
        this.ptoVenta = ptoVenta;
    }

    public String getPorcentajeIva() {
        return "1." + this.porcentajeIva;
    }

    public void setPorcentajeIva(String porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public String getTipoPuntovta() {
        return this.tipoPuntovta;
    }

    public void setTipoPuntovta(String tipoPuntovta) {
        this.tipoPuntovta = tipoPuntovta;
    }
}

