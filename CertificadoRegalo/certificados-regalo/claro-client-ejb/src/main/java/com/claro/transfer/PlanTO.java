/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class PlanTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -8964901768875452603L;
    private String idPlanNuevo;
    private int segmento;
    private int idRegion;
    private int idGrupoPromocion;
    private String descripcion;
    private String tecnologia;
    private String banMixto;
    private String modalidad;
    private String banSisact;
    private int adendum;
    private int renta;
    private int banRedencion;
    private String estatus;
    private String banRedencionAnct;
    private String descSegmento;
    private int adendumNvo;
    private String tipoPromocion;
    private int ptosMinimos;
    private String tipoPlan;
    private int bonoXSegmento;

    public PlanTO() {
    }

    public PlanTO(String[] columnas) {
        this.idPlanNuevo = columnas[0];
        this.segmento = Integer.parseInt(columnas[1]);
        this.idRegion = Integer.parseInt(columnas[2]);
        this.idGrupoPromocion = Integer.parseInt(columnas[3]);
        this.descripcion = columnas[4];
        this.tecnologia = columnas[5];
        this.banMixto = columnas[6];
        this.modalidad = columnas[7];
        this.banSisact = columnas[8];
        if (columnas[9] != null) {
            this.adendum = Integer.parseInt(columnas[9]);
        }
        if (columnas[10] != null) {
            this.renta = Integer.parseInt(columnas[10]);
        }
        if (columnas[11] != null) {
            this.banRedencion = Integer.parseInt(columnas[11]);
        }
        this.estatus = columnas[12];
        this.banRedencionAnct = columnas[13];
        this.tipoPlan = columnas[14];
    }

    public String getDescSegmento() {
        return this.descSegmento;
    }

    public void setDescSegmento(String descSegmento) {
        this.descSegmento = descSegmento;
    }

    public int getPtosMinimos() {
        return this.ptosMinimos;
    }

    public void setPtosMinimos(int ptosMinimos) {
        this.ptosMinimos = ptosMinimos;
    }

    public String getTipoPlan() {
        return this.tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public int getBonoXSegmento() {
        return this.bonoXSegmento;
    }

    public void setBonoXSegmento(int bonoXSegmento) {
        this.bonoXSegmento = bonoXSegmento;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public int getIdGrupoPromocion() {
        return this.idGrupoPromocion;
    }

    public void setIdGrupoPromocion(int idGrupoPromocion) {
        this.idGrupoPromocion = idGrupoPromocion;
    }

    public int getSegmento() {
        return this.segmento;
    }

    public void setSegmento(int segmento) {
        this.segmento = segmento;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getBanRedencion() {
        return this.banRedencion;
    }

    public void setBanRedencion(int redencion) {
        this.banRedencion = redencion;
    }

    public int getAdendum() {
        return this.adendum;
    }

    public void setAdendum(int adendum) {
        this.adendum = adendum;
    }

    public int getRenta() {
        return this.renta;
    }

    public void setRenta(int renta) {
        this.renta = renta;
    }

    public String getIdPlanNuevo() {
        return this.idPlanNuevo;
    }

    public void setIdPlanNuevo(String idPlanNuevo) {
        this.idPlanNuevo = idPlanNuevo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getBanMixto() {
        return this.banMixto;
    }

    public void setBanMixto(String mixto) {
        this.banMixto = mixto;
    }

    public String getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getBanSisact() {
        return this.banSisact;
    }

    public void setBanSisact(String sisact) {
        this.banSisact = sisact;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getBanRedencionAnct() {
        return this.banRedencionAnct;
    }

    public void setBanRedencionAnct(String redencionAnct) {
        this.banRedencionAnct = redencionAnct;
    }

    public int getAdendumNvo() {
        return this.adendumNvo;
    }

    public void setAdendumNvo(int adendumNvo) {
        this.adendumNvo = adendumNvo;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.idPlanNuevo).append(",").append(this.segmento).append(",");
        buffer.append(this.idRegion).append(",").append(this.idGrupoPromocion).append(",");
        buffer.append(this.descripcion).append(",").append(this.tecnologia).append(",");
        buffer.append(this.banMixto).append(",").append(this.modalidad).append(",");
        buffer.append(this.banSisact).append(",").append(this.adendum).append(",");
        buffer.append(this.renta).append(",").append(this.banRedencion).append(",");
        buffer.append(this.estatus).append(",").append(this.banRedencionAnct).append(",");
        buffer.append(this.tipoPlan);
        return buffer.toString();
    }

    public String getLineaLog() {
        StringBuffer linea = new StringBuffer();
        linea.append(this.idPlanNuevo).append("@").append(this.descripcion).append("@");
        linea.append(this.segmento).append("@").append(this.idRegion).append("@");
        linea.append(this.ptosMinimos).append("@").append(this.tecnologia).append("@");
        linea.append(this.banMixto).append("@").append(this.estatus).append("@");
        linea.append(this.modalidad).append("@").append(this.banSisact).append("@");
        linea.append(this.renta).append("@").append(this.adendum).append("@");
        linea.append(this.tipoPlan).append("@").append(this.idGrupoPromocion).append("@");
        linea.append(this.banRedencion).append("@");
        if (this.banRedencionAnct != null) {
            linea.append(this.banRedencionAnct);
        } else {
            linea.append(" ");
        }
        linea.append("@").append(this.tipoPlan).append("@");
        return linea.toString();
    }
}

