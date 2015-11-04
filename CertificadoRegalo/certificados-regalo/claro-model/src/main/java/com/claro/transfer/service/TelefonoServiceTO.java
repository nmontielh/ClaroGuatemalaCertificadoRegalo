/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import com.claro.transfer.MobileTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.util.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelefonoServiceTO
implements Serializable {
    private static final long serialVersionUID = 6732672972723198931L;
    private String cuenta;
    private String mensaje;
    private String nombre;
    private String ciclo;
    private String cuentaPadre;
    private String fechaAlta;
    private String fechaAltaM2K;
    private String sistema;
    private String estatusTel;
    private String tecnologia;
    private String telefono;
    private String fecVencidos;
    private String fecVencer;
    private String fecVencer2;
    private String fecVencer1;
    private String ptosStatus;
    private String fecFactura;
    private String fechaSuspension;
    private String formaRedencion;
    private String ptsDisponiblesCF;
    private String ptsExcedentesCF;
    private String ptsRedimidosCF;
    private String ptsVencidosCF;
    private String ptsPorVencerCF;
    private String ptsPorVencer2CF;
    private String ptsPorVencer1CF;
    private String ptsAntiguedadCF;
    private String ptsPromocionCF;
    private String ptsBonoEquipoCF;
    private String ptsRentaCF;
    private String ptsSubastaCF;
    private String tipoProm;
    private String planM2K;
    private String motivo;
    private String planNuevo;
    private String estCobranza;
    private String claseCredito;
    private String addendumM2K;
    private String idGrupo;
    private String fechaAddendumM2K;
    private String tipoRedencion;
    private String fechaFolioReservaCF;
    private String distReserva;
    private String sPromFacturaAV;
    private String addCareg;
    private String mesesCareg;
    private String segmento;
    private int banRedime;
    private int addendNvo;
    private int secuencia;
    private int idMensaje;
    private int region;
    private int ptsDisponibles;
    private int ptsExcedentes;
    private int ptsRedimidos;
    private int ptsVencidos;
    private int ptsPorVencer;
    private int ptsPorVencer2;
    private int ptsPorVencer1;
    private int ptsAntiguedad;
    private int ptsPromocion;
    private int ptsBonoEquipo;
    private int ptsTransferidos;
    private int ptsRenta;
    private int ptsSubasta;
    private int ptsRedimidosSF;
    private int bonoEquipo;
    private String banMixto;
    private String modalidad;

    public int getPtsTransferidos() {
        return this.ptsTransferidos;
    }

    public void setPtsTransferidos(int ptsTransferidos) {
        this.ptsTransferidos = ptsTransferidos;
    }

    public int getBonoEquipo() {
        return this.bonoEquipo;
    }

    public void setBonoEquipo(int bonoEquipo) {
        this.bonoEquipo = bonoEquipo;
    }

    public TelefonoServiceTO() {
    }

    public TelefonoServiceTO(TelefonoTO telefonoTO) {
        this.cuenta = telefonoTO.getCuenta();
        this.mensaje = telefonoTO.getMensaje();
        this.nombre = telefonoTO.getMobileTO().getNombre();
        this.ciclo = telefonoTO.getCiclo();
        this.cuentaPadre = telefonoTO.getCtaPadre();
        this.fechaAlta = Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getFechaAlta());
        this.fechaAltaM2K = telefonoTO.getMobileTO().getFecAltaUser();
        this.sistema = telefonoTO.getSistema();
        this.estatusTel = telefonoTO.getMobileTO().getStatus();
        this.tecnologia = telefonoTO.getTecnologia();
        this.telefono = telefonoTO.getTelefono();
        this.fecVencidos = telefonoTO.getPuntosTO().getFecVencidos() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencidos());
        this.fecVencer = telefonoTO.getPuntosTO().getFecVencer() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer());
        this.fecVencer2 = telefonoTO.getPuntosTO().getFecVencer2() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer2());
        this.fecVencer1 = telefonoTO.getPuntosTO().getFecVencer1() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer1());
        this.ptosStatus = telefonoTO.getPuntosTO().getPtosStatus();
        this.fecFactura = telefonoTO.getFecFactura() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getFecFactura());
        this.formaRedencion = telefonoTO.getFormaRedencion();
        this.ptsDisponiblesCF = telefonoTO.getPuntosTO().getPtosDisponiblesCF();
        this.ptsExcedentesCF = telefonoTO.getPuntosTO().getPtsExcedentesCF();
        this.ptsRedimidosCF = telefonoTO.getPuntosTO().getPtsRedimidosCF();
        this.ptsVencidosCF = telefonoTO.getPuntosTO().getPtsVencidosCF();
        this.ptsPorVencerCF = telefonoTO.getPuntosTO().getPtsPorVencerCF();
        this.ptsPorVencer2CF = telefonoTO.getPuntosTO().getPtsPorVencer2CF();
        this.ptsPorVencer1CF = telefonoTO.getPuntosTO().getPtsPorVencer1CF();
        this.ptsAntiguedadCF = telefonoTO.getPuntosTO().getPtsAntiguedadCF();
        this.ptsPromocionCF = telefonoTO.getPuntosTO().getPtsPromocionCF();
        this.ptsBonoEquipoCF = telefonoTO.getPuntosTO().getBonoEquipoCF();
        this.ptsRentaCF = telefonoTO.getPuntosTO().getPtsRentaCF();
        this.ptsSubastaCF = telefonoTO.getPuntosTO().getPtsSubastaCF();
        this.bonoEquipo = telefonoTO.getPuntosTO().getBonoEquipo();
        this.banRedime = telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getBanRedencion() : 0;
        this.tipoProm = telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getTipoPromocion() : "";
        this.planM2K = telefonoTO.getMobileTO().getPlanM2K();
        this.addendNvo = telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getAdendumNvo() : 0;
        this.motivo = telefonoTO.getMobileTO().getMotivo();
        this.planNuevo = telefonoTO.getPlanTO() == null || telefonoTO.getPlanTO().getIdPlanNuevo() == null ? "" : telefonoTO.getPlanTO().getIdPlanNuevo();
        this.estCobranza = telefonoTO.getMobileTO().getEstCobranza();
        this.claseCredito = telefonoTO.getMobileTO().getClaseCredit();
        this.addendumM2K = telefonoTO.getMobileTO().getAddM2K();
        this.idGrupo = telefonoTO.getIdGrupo();
        this.fechaAddendumM2K = telefonoTO.getMobileTO().getFecAddM2K();
        this.fechaSuspension = telefonoTO.getMobileTO().getFechaSuspension();
        this.sPromFacturaAV = telefonoTO.getMobileTO().getSPromFacturaAV();
        this.addCareg = telefonoTO.getMobileTO().getAddCareg();
        this.mesesCareg = telefonoTO.getMobileTO().getMesesCareg();
        this.tipoRedencion = telefonoTO.getTipoRedencion();
        this.fechaFolioReservaCF = telefonoTO.getPuntosTO().getFecReservacionCF();
        this.distReserva = telefonoTO.getPuntosTO().getDistribuidorReserva();
        this.secuencia = Integer.parseInt(telefonoTO.getSecuencia());
        this.idMensaje = telefonoTO.getIdMensaje();
        this.region = telefonoTO.getRegion();
        this.segmento = telefonoTO.getSegmento();
        this.ptsDisponibles = telefonoTO.getPuntosTO().getPtosDisponibles();
        this.ptsExcedentes = telefonoTO.getPuntosTO().getPtsExcedentes();
        this.ptsRedimidos = telefonoTO.getPuntosTO().getPtsRedimidos();
        this.ptsVencidos = telefonoTO.getPuntosTO().getPtsVencidos();
        this.ptsPorVencer = telefonoTO.getPuntosTO().getPtsPorVencer();
        this.ptsPorVencer2 = telefonoTO.getPuntosTO().getPtsPorVencer2();
        this.ptsPorVencer1 = telefonoTO.getPuntosTO().getPtsPorVencer1();
        this.ptsAntiguedad = telefonoTO.getPuntosTO().getPtsAntiguedad();
        this.ptsPromocion = telefonoTO.getPuntosTO().getPtsPromocion();
        this.ptsBonoEquipo = telefonoTO.getPuntosTO().getBonoEquipo();
        this.ptsTransferidos = telefonoTO.getPuntosTO().getPtsTransferidos();
        this.ptsRenta = telefonoTO.getPuntosTO().getPtsRenta();
        this.ptsSubasta = telefonoTO.getPuntosTO().getPtsSubasta();
        this.ptsRedimidosSF = telefonoTO.getPuntosTO().getPtsRedimidos();
        this.banMixto = telefonoTO.getPlanTO().getBanMixto();
        this.modalidad = telefonoTO.getPlanTO().getModalidad();
    }

    public int getPtsRedimidosSF() {
        return this.ptsRedimidosSF;
    }

    public void setPtsRedimidosSF(int ptsRedimidosSF) {
        this.ptsRedimidosSF = ptsRedimidosSF;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return this.ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCuentaPadre() {
        return this.cuentaPadre;
    }

    public void setCuentaPadre(String cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }

    public String getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAltaM2K() {
        return this.fechaAltaM2K;
    }

    public void setFechaAltaM2K(String fechaAltaM2K) {
        this.fechaAltaM2K = fechaAltaM2K;
    }

    public String getSistema() {
        return this.sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getEstatusTel() {
        return this.estatusTel;
    }

    public void setEstatusTel(String estatusTel) {
        this.estatusTel = estatusTel;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecVencidos() {
        return this.fecVencidos;
    }

    public void setFecVencidos(String fecVencidos) {
        this.fecVencidos = fecVencidos;
    }

    public String getFecVencer() {
        return this.fecVencer;
    }

    public void setFecVencer(String fecVencer) {
        this.fecVencer = fecVencer;
    }

    public String getFecVencer2() {
        return this.fecVencer2;
    }

    public void setFecVencer2(String fecVencer2) {
        this.fecVencer2 = fecVencer2;
    }

    public String getFecVencer1() {
        return this.fecVencer1;
    }

    public void setFecVencer1(String fecVencer1) {
        this.fecVencer1 = fecVencer1;
    }

    public String getPtosStatus() {
        return this.ptosStatus;
    }

    public void setPtosStatus(String ptosStatus) {
        this.ptosStatus = ptosStatus;
    }

    public String getFecFactura() {
        return this.fecFactura;
    }

    public void setFecFactura(String fecFactura) {
        this.fecFactura = fecFactura;
    }

    public String getFormaRedencion() {
        return this.formaRedencion;
    }

    public void setFormaRedencion(String formaRedencion) {
        this.formaRedencion = formaRedencion;
    }

    public String getPtsDisponiblesCF() {
        return this.ptsDisponiblesCF;
    }

    public void setPtsDisponiblesCF(String ptsDisponiblesCF) {
        this.ptsDisponiblesCF = ptsDisponiblesCF;
    }

    public String getPtsExcedentesCF() {
        return this.ptsExcedentesCF;
    }

    public void setPtsExcedentesCF(String ptsExcedentesCF) {
        this.ptsExcedentesCF = ptsExcedentesCF;
    }

    public String getPtsRedimidosCF() {
        return this.ptsRedimidosCF;
    }

    public void setPtsRedimidosCF(String ptsRedimidosCF) {
        this.ptsRedimidosCF = ptsRedimidosCF;
    }

    public String getPtsVencidosCF() {
        return this.ptsVencidosCF;
    }

    public void setPtsVencidosCF(String ptsVencidosCF) {
        this.ptsVencidosCF = ptsVencidosCF;
    }

    public String getPtsPorVencerCF() {
        return this.ptsPorVencerCF;
    }

    public void setPtsPorVencerCF(String ptsPorVencerCF) {
        this.ptsPorVencerCF = ptsPorVencerCF;
    }

    public String getPtsPorVencer2CF() {
        return this.ptsPorVencer2CF;
    }

    public void setPtsPorVencer2CF(String ptsPorVencer2CF) {
        this.ptsPorVencer2CF = ptsPorVencer2CF;
    }

    public String getPtsPorVencer1CF() {
        return this.ptsPorVencer1CF;
    }

    public void setPtsPorVencer1CF(String ptsPorVencer1CF) {
        this.ptsPorVencer1CF = ptsPorVencer1CF;
    }

    public String getPtsAntiguedadCF() {
        return this.ptsAntiguedadCF;
    }

    public void setPtsAntiguedadCF(String ptsAntiguedadCF) {
        this.ptsAntiguedadCF = ptsAntiguedadCF;
    }

    public String getPtsPromocionCF() {
        return this.ptsPromocionCF;
    }

    public void setPtsPromocionCF(String ptsPromocionCF) {
        this.ptsPromocionCF = ptsPromocionCF;
    }

    public String getPtsBonoEquipoCF() {
        return this.ptsBonoEquipoCF;
    }

    public void setPtsBonoEquipoCF(String ptsBonoEquipoCF) {
        this.ptsBonoEquipoCF = ptsBonoEquipoCF;
    }

    public String getPtsRentaCF() {
        return this.ptsRentaCF;
    }

    public void setPtsRentaCF(String ptsRentaCF) {
        this.ptsRentaCF = ptsRentaCF;
    }

    public String getPtsSubastaCF() {
        return this.ptsSubastaCF;
    }

    public void setPtsSubastaCF(String ptsSubastaCF) {
        this.ptsSubastaCF = ptsSubastaCF;
    }

    public int getBanRedime() {
        return this.banRedime;
    }

    public void setBanRedime(int banRedime) {
        this.banRedime = banRedime;
    }

    public String getTipoProm() {
        return this.tipoProm;
    }

    public void setTipoProm(String tipoProm) {
        this.tipoProm = tipoProm;
    }

    public String getPlanM2K() {
        return this.planM2K;
    }

    public void setPlanM2K(String planM2K) {
        this.planM2K = planM2K;
    }

    public int getAddendNvo() {
        return this.addendNvo;
    }

    public void setAddendNvo(int addendNvo) {
        this.addendNvo = addendNvo;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPlanNuevo() {
        return this.planNuevo;
    }

    public void setPlanNuevo(String planNuevo) {
        this.planNuevo = planNuevo;
    }

    public String getEstCobranza() {
        return this.estCobranza;
    }

    public void setEstCobranza(String estCobranza) {
        this.estCobranza = estCobranza;
    }

    public String getClaseCredito() {
        return this.claseCredito;
    }

    public void setClaseCredito(String claseCredito) {
        this.claseCredito = claseCredito;
    }

    public String getAddendumM2K() {
        return this.addendumM2K;
    }

    public void setAddendumM2K(String addendumM2K) {
        this.addendumM2K = addendumM2K;
    }

    public String getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getFechaAddendumM2K() {
        return this.fechaAddendumM2K;
    }

    public void setFechaAddendumM2K(String fechaAddendumM2K) {
        this.fechaAddendumM2K = fechaAddendumM2K;
    }

    public String getTipoRedencion() {
        return this.tipoRedencion;
    }

    public void setTipoRedencion(String tipoRedencion) {
        this.tipoRedencion = tipoRedencion;
    }

    public String getFechaFolioReservaCF() {
        return this.fechaFolioReservaCF;
    }

    public void setFechaFolioReservaCF(String fechaFolioReservaCF) {
        this.fechaFolioReservaCF = fechaFolioReservaCF;
    }

    public String getDistReserva() {
        return this.distReserva;
    }

    public void setDistReserva(String distReserva) {
        this.distReserva = distReserva;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getIdMensaje() {
        return this.idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getSegmento() {
        return this.segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public int getPtsDisponibles() {
        return this.ptsDisponibles;
    }

    public void setPtsDisponibles(int ptsDisponibles) {
        this.ptsDisponibles = ptsDisponibles;
    }

    public int getPtsExcedentes() {
        return this.ptsExcedentes;
    }

    public void setPtsExcedentes(int ptsExcedentes) {
        this.ptsExcedentes = ptsExcedentes;
    }

    public int getPtsRedimidos() {
        return this.ptsRedimidos;
    }

    public void setPtsRedimidos(int ptsRedimidos) {
        this.ptsRedimidos = ptsRedimidos;
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

    public int getPtsAntiguedad() {
        return this.ptsAntiguedad;
    }

    public void setPtsAntiguedad(int ptsAntiguedad) {
        this.ptsAntiguedad = ptsAntiguedad;
    }

    public int getPtsPromocion() {
        return this.ptsPromocion;
    }

    public void setPtsPromocion(int ptsPromocion) {
        this.ptsPromocion = ptsPromocion;
    }

    public int getPtsBonoEquipo() {
        return this.ptsBonoEquipo;
    }

    public void setPtsBonoEquipo(int ptsBonoEquipo) {
        this.ptsBonoEquipo = ptsBonoEquipo;
    }

    public int getPtsRenta() {
        return this.ptsRenta;
    }

    public void setPtsRenta(int ptsRenta) {
        this.ptsRenta = ptsRenta;
    }

    public int getPtsSubasta() {
        return this.ptsSubasta;
    }

    public void setPtsSubasta(int ptsSubasta) {
        this.ptsSubasta = ptsSubasta;
    }

    public String getFechaSuspension() {
        return this.fechaSuspension;
    }

    public void setFechaSuspension(String fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getSPromFacturaAV() {
        return this.sPromFacturaAV;
    }

    public void setSPromFacturaAV(String promFacturaAV) {
        this.sPromFacturaAV = promFacturaAV;
    }

    public String getAddCareg() {
        return this.addCareg;
    }

    public void setAddCareg(String addCareg) {
        this.addCareg = addCareg;
    }

    public String getMesesCareg() {
        return this.mesesCareg;
    }

    public void setMesesCareg(String mesesCareg) {
        this.mesesCareg = mesesCareg;
    }

    public String getBanMixto() {
        return this.banMixto;
    }

    public void setBanMixto(String banMixto) {
        this.banMixto = banMixto;
    }

    public String getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String toStringTelefonoServiceTO() {
        return String.valueOf(this.cuenta) + "|" + this.mensaje + "|" + this.nombre + "|" + this.ciclo + "|" + this.cuentaPadre + "|" + this.fechaAlta + "|" + this.fechaAltaM2K + "|" + this.sistema + "|" + this.estatusTel + "|" + this.tecnologia + "|" + this.telefono + "|" + this.fecVencidos + "|" + this.fecVencer + "|" + this.fecVencer2 + "|" + this.fecVencer1 + "|" + this.ptosStatus + "|" + this.fecFactura + "|" + this.fechaSuspension + "|" + this.formaRedencion + "|" + this.ptsDisponiblesCF + "|" + this.ptsExcedentesCF + "|" + this.ptsRedimidosCF + "|" + this.ptsVencidosCF + "|" + this.ptsPorVencerCF + "|" + this.ptsPorVencer2CF + "|" + this.ptsPorVencer1CF + "|" + this.ptsAntiguedadCF + "|" + this.ptsPromocionCF + "|" + this.ptsBonoEquipoCF + "|" + this.ptsRentaCF + "|" + this.ptsSubastaCF + "|" + this.tipoProm + "|" + this.planM2K + "|" + this.motivo + "|" + this.planNuevo + "|" + this.estCobranza + "|" + this.claseCredito + "|" + this.addendumM2K + "|" + this.idGrupo + "|" + this.fechaAddendumM2K + "|" + this.tipoRedencion + "|" + this.fechaFolioReservaCF + "|" + this.distReserva + "|" + this.sPromFacturaAV + "|" + this.addCareg + "|" + this.mesesCareg + "|" + this.segmento + "|" + this.banRedime + "|" + this.addendNvo + "|" + this.secuencia + "|" + this.idMensaje + "|" + this.region + "|" + this.ptsDisponibles + "|" + this.ptsExcedentes + "|" + this.ptsRedimidos + "|" + this.ptsVencidos + "|" + this.ptsPorVencer + "|" + this.ptsPorVencer2 + "|" + this.ptsPorVencer1 + "|" + this.ptsAntiguedad + "|" + this.ptsPromocion + "|" + this.ptsBonoEquipo + "|" + this.ptsTransferidos + "|" + this.ptsRenta + "|" + this.ptsSubasta + "|" + this.ptsRedimidosSF + "|" + this.bonoEquipo;
    }
}

