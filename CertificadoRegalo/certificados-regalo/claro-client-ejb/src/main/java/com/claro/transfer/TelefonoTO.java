/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.PuntosTO;
import java.io.PrintStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelefonoTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -2641358147985483649L;
    private String cuenta;
    private String secuencia;
    private String ctaPadre;
    private String telefono;
    private java.sql.Date fechaAlta;
    private String sistema;
    private int region;
    private String regionTxt;
    private String ciclo;
    private int banSubasta;
    private String plan;
    private int addendum;
    private java.sql.Date fecFactura;
    private String tecnologia;
    private int bonoEquipo;
    private int renta;
    private int idSegmento;
    private String segmento;
    private String formaRedencion;
    private String tipoRedencion;
    private String promocionSim;
    private int millas;
    private PuntosTO puntosTO;
    private MobileTO mobileTO;
    private PlanTO planTO;
    private Timestamp fechaExpira;
    private Timestamp fechaAltaTime;
    private String fechaUltMov;
    private boolean existePromocionGap;
    private boolean aplicaGapCA;
    private String fzaVta;
    private boolean aceptaBonoInbursa;
    private String tipoProm;
    private String banRedime;
    private String idGrupo;
    private String addNvo;
    private String nickName;
    private String sAnacr;
    private int nMesActual;
    private int nDiasMes;
    private int nDiaActual;
    private int nBonoEquipo;
    private int sobBonoEquipo;
    private BigDecimal precio = new BigDecimal(0);
    private long nPtsaRedimir = 0;
    private String marca;
    private String modelo;
    private String folio;
    private String estatusPuntos;
    private String estatusCarta;
    private boolean aplicaRedencion;
    private String descripcionPlan;
    private String idUsuario;

    public String getFechaUltMov() {
        return this.fechaUltMov;
    }

    public void setFechaUltMov(String fecUltMov) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = formatter.parse(fecUltMov);
            this.fechaAltaTime = new Timestamp(date.getTime());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getEstatusPuntos() {
        return this.estatusPuntos;
    }

    public void setEstatusPuntos(String estatusPuntos) {
        this.estatusPuntos = estatusPuntos;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public String getDescripcionPlan() {
        return this.descripcionPlan;
    }

    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }

    public MobileTO getMobileTO() {
        return this.mobileTO;
    }

    public void setMobileTO(MobileTO mobileTO) {
        this.mobileTO = mobileTO;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCtaPadre() {
        return this.ctaPadre;
    }

    public void setCtaPadre(String ctaPadre) {
        this.ctaPadre = ctaPadre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public java.sql.Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(java.sql.Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getCiclo() {
        return this.ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getBanSubasta() {
        return this.banSubasta;
    }

    public void setBanSubasta(int banSubasta) {
        this.banSubasta = banSubasta;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getAddendum() {
        return this.addendum;
    }

    public void setAddendum(int addendum) {
        this.addendum = addendum;
    }

    public java.sql.Date getFecFactura() {
        return this.fecFactura;
    }

    public void setFecFactura(java.sql.Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getBonoEquipo() {
        return this.bonoEquipo;
    }

    public void setBonoEquipo(int bonoEquipo) {
        this.bonoEquipo = bonoEquipo;
    }

    public int getRenta() {
        return this.renta;
    }

    public void setRenta(int renta) {
        this.renta = renta;
    }

    public int getIdSegmento() {
        return this.idSegmento;
    }

    public void setIdSegmento(int idSegmento) {
        this.idSegmento = idSegmento;
    }

    public String getSegmento() {
        return this.segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public PuntosTO getPuntosTO() {
        return this.puntosTO;
    }

    public void setPuntosTO(PuntosTO puntosTO) {
        this.puntosTO = puntosTO;
    }

    public int getNMesActual() {
        return this.nMesActual;
    }

    public void setNMesActual(int mesActual) {
        this.nMesActual = mesActual;
    }

    public int getNDiasMes() {
        return this.nDiasMes;
    }

    public void setNDiasMes(int diasMes) {
        this.nDiasMes = diasMes;
    }

    public int getNBonoEquipo() {
        return this.nBonoEquipo;
    }

    public void setNBonoEquipo(int bonoEquipo) {
        this.nBonoEquipo = bonoEquipo;
    }

    public int getSobBonoEquipo() {
        return this.sobBonoEquipo;
    }

    public void setSobBonoEquipo(int sobBonoEquipo) {
        this.sobBonoEquipo = sobBonoEquipo;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public long getNPtsaRedimir() {
        return this.nPtsaRedimir;
    }

    public void setNPtsaRedimir(long ptsaRedimir) {
        this.nPtsaRedimir = ptsaRedimir;
    }

    public int getNDiaActual() {
        return this.nDiaActual;
    }

    public void setNDiaActual(int diaActual) {
        this.nDiaActual = diaActual;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSAnacr() {
        return this.sAnacr;
    }

    public void setSAnacr(String anacr) {
        this.sAnacr = anacr;
    }

    public String getTipoProm() {
        return this.tipoProm;
    }

    public void setTipoProm(String tipoProm) {
        this.tipoProm = tipoProm;
    }

    public String getBanRedime() {
        return this.banRedime;
    }

    public void setBanRedime(String banRedime) {
        this.banRedime = banRedime;
    }

    public String getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getAddNvo() {
        return this.addNvo;
    }

    public void setAddNvo(String addNvo) {
        this.addNvo = addNvo;
    }

    public String getRegionTxt() {
        this.regionTxt = "R0" + this.region;
        return this.regionTxt;
    }

    public void setRegionTxt(String regionTxt) {
        this.regionTxt = regionTxt;
    }

    public int getMillas() {
        return this.millas;
    }

    public void setMillas(int millas) {
        this.millas = millas;
    }

    public PlanTO getPlanTO() {
        return this.planTO;
    }

    public void setPlanTO(PlanTO planTO) {
        this.planTO = planTO;
    }

    public String getFormaRedencion() {
        return this.formaRedencion;
    }

    public void setFormaRedencion(String formaRedencion) {
        this.formaRedencion = formaRedencion;
    }

    public boolean getAplicaRedencion() {
        return this.aplicaRedencion;
    }

    public void setAplicaRedencion(boolean aplicaRedencion) {
        this.aplicaRedencion = aplicaRedencion;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getPromocionSim() {
        return this.promocionSim;
    }

    public void setPromocionSim(String promocionSim) {
        this.promocionSim = promocionSim;
    }

    public Timestamp getFechaExpira() {
        return this.fechaExpira;
    }

    public void setFechaExpira(Timestamp FechaExpira) {
        this.fechaExpira = FechaExpira;
    }

    public Timestamp getFechaAltaTime() {
        return this.fechaAltaTime;
    }

    public void setFechaAltaTime(Timestamp FechaAltaTime) {
        this.fechaAltaTime = FechaAltaTime;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isExistePromocionGap() {
        return this.existePromocionGap;
    }

    public void setExistePromocionGap(boolean existePromocionGap) {
        this.existePromocionGap = existePromocionGap;
    }

    public boolean isAplicaGapCA() {
        return this.aplicaGapCA;
    }

    public void setAplicaGapCA(boolean aplicaGapCA) {
        this.aplicaGapCA = aplicaGapCA;
    }

    public String getEstatusCarta() {
        return this.estatusCarta;
    }

    public void setEstatusCarta(String estatusCarta) {
        this.estatusCarta = estatusCarta;
    }

    public String getEstatusCarta(String statusLinea) {
        this.estatusCarta = "AC".equals(statusLinea) || "SU".equals(statusLinea) ? "P" : "O";
        return this.estatusCarta;
    }

    public String getFzaVta() {
        return this.fzaVta;
    }

    public void setFzaVta(String fzaVta) {
        this.fzaVta = fzaVta;
    }

    public boolean isAceptaBonoInbursa() {
        return this.aceptaBonoInbursa;
    }

    public void setAceptaBonoInbursa(boolean aceptaBonoInbursa) {
        this.aceptaBonoInbursa = aceptaBonoInbursa;
    }
}

