/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.MobileTO;
/*     */ import com.claro.transfer.PlanTO;
/*     */ import com.claro.transfer.PuntosTO;
/*     */ import com.claro.transfer.TelefonoTO;
/*     */ import com.claro.util.Utils;
/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TelefonoServiceTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6732672972723198931L;
/*     */   private String cuenta;
/*     */   private String mensaje;
/*     */   private String nombre;
/*     */   private String ciclo;
/*     */   private String cuentaPadre;
/*     */   private String fechaAlta;
/*     */   private String fechaAltaM2K;
/*     */   private String sistema;
/*     */   private String estatusTel;
/*     */   private String tecnologia;
/*     */   private String telefono;
/*     */   private String fecVencidos;
/*     */   private String fecVencer;
/*     */   private String fecVencer2;
/*     */   private String fecVencer1;
/*     */   private String ptosStatus;
/*     */   private String fecFactura;
/*     */   private String fechaSuspension;
/*     */   private String formaRedencion;
/*     */   private String ptsDisponiblesCF;
/*     */   private String ptsExcedentesCF;
/*     */   private String ptsRedimidosCF;
/*     */   private String ptsVencidosCF;
/*     */   private String ptsPorVencerCF;
/*     */   private String ptsPorVencer2CF;
/*     */   private String ptsPorVencer1CF;
/*     */   private String ptsAntiguedadCF;
/*     */   private String ptsPromocionCF;
/*     */   private String ptsBonoEquipoCF;
/*     */   private String ptsRentaCF;
/*     */   private String ptsSubastaCF;
/*     */   private String tipoProm;
/*     */   private String planM2K;
/*     */   private String motivo;
/*     */   private String planNuevo;
/*     */   private String estCobranza;
/*     */   private String claseCredito;
/*     */   private String addendumM2K;
/*     */   private String idGrupo;
/*     */   private String fechaAddendumM2K;
/*     */   private String tipoRedencion;
/*     */   private String fechaFolioReservaCF;
/*     */   private String distReserva;
/*     */   private String sPromFacturaAV;
/*     */   private String addCareg;
/*     */   private String mesesCareg;
/*     */   private String segmento;
/*     */   private int banRedime;
/*     */   private int addendNvo;
/*     */   private int secuencia;
/*     */   private int idMensaje;
/*     */   private int region;
/*     */   private int ptsDisponibles;
/*     */   private int ptsExcedentes;
/*     */   private int ptsRedimidos;
/*     */   private int ptsVencidos;
/*     */   private int ptsPorVencer;
/*     */   private int ptsPorVencer2;
/*     */   private int ptsPorVencer1;
/*     */   private int ptsAntiguedad;
/*     */   private int ptsPromocion;
/*     */   private int ptsBonoEquipo;
/*     */   private int ptsTransferidos;
/*     */   private int ptsRenta;
/*     */   private int ptsSubasta;
/*     */   private int ptsRedimidosSF;
/*     */   private int bonoEquipo;
/*     */   private String banMixto;
/*     */   private String modalidad;
/*     */   
/*     */   public int getPtsTransferidos()
/*     */   {
/*  91 */     return this.ptsTransferidos;
/*     */   }
/*     */   
/*     */   public void setPtsTransferidos(int ptsTransferidos) {
/*  95 */     this.ptsTransferidos = ptsTransferidos;
/*     */   }
/*     */   
/*     */   public int getBonoEquipo() {
/*  99 */     return this.bonoEquipo;
/*     */   }
/*     */   
/*     */   public void setBonoEquipo(int bonoEquipo) {
/* 103 */     this.bonoEquipo = bonoEquipo;
/*     */   }
/*     */   
/*     */   public TelefonoServiceTO() {}
/*     */   
/*     */   public TelefonoServiceTO(TelefonoTO telefonoTO)
/*     */   {
/* 110 */     this.cuenta = telefonoTO.getCuenta();
/* 111 */     this.mensaje = telefonoTO.getMensaje();
/* 112 */     this.nombre = telefonoTO.getMobileTO().getNombre();
/* 113 */     this.ciclo = telefonoTO.getCiclo();
/* 114 */     this.cuentaPadre = telefonoTO.getCtaPadre();
/* 115 */     this.fechaAlta = Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getFechaAlta());
/* 116 */     this.fechaAltaM2K = telefonoTO.getMobileTO().getFecAltaUser();
/* 117 */     this.sistema = telefonoTO.getSistema();
/* 118 */     this.estatusTel = telefonoTO.getMobileTO().getStatus();
/* 119 */     this.tecnologia = telefonoTO.getTecnologia();
/* 120 */     this.telefono = telefonoTO.getTelefono();
/* 121 */     this.fecVencidos = (telefonoTO.getPuntosTO().getFecVencidos() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencidos()));
/* 122 */     this.fecVencer = (telefonoTO.getPuntosTO().getFecVencer() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer()));
/* 123 */     this.fecVencer2 = (telefonoTO.getPuntosTO().getFecVencer2() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer2()));
/* 124 */     this.fecVencer1 = (telefonoTO.getPuntosTO().getFecVencer1() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getPuntosTO().getFecVencer1()));
/* 125 */     this.ptosStatus = telefonoTO.getPuntosTO().getPtosStatus();
/* 126 */     this.fecFactura = (telefonoTO.getFecFactura() == null ? "" : Utils.DATEFORMATdd_MM_YYYY.format(telefonoTO.getFecFactura()));
/* 127 */     this.formaRedencion = telefonoTO.getFormaRedencion();
/* 128 */     this.ptsDisponiblesCF = telefonoTO.getPuntosTO().getPtosDisponiblesCF();
/* 129 */     this.ptsExcedentesCF = telefonoTO.getPuntosTO().getPtsExcedentesCF();
/* 130 */     this.ptsRedimidosCF = telefonoTO.getPuntosTO().getPtsRedimidosCF();
/* 131 */     this.ptsVencidosCF = telefonoTO.getPuntosTO().getPtsVencidosCF();
/* 132 */     this.ptsPorVencerCF = telefonoTO.getPuntosTO().getPtsPorVencerCF();
/* 133 */     this.ptsPorVencer2CF = telefonoTO.getPuntosTO().getPtsPorVencer2CF();
/* 134 */     this.ptsPorVencer1CF = telefonoTO.getPuntosTO().getPtsPorVencer1CF();
/* 135 */     this.ptsAntiguedadCF = telefonoTO.getPuntosTO().getPtsAntiguedadCF();
/* 136 */     this.ptsPromocionCF = telefonoTO.getPuntosTO().getPtsPromocionCF();
/* 137 */     this.ptsBonoEquipoCF = telefonoTO.getPuntosTO().getBonoEquipoCF();
/* 138 */     this.ptsRentaCF = telefonoTO.getPuntosTO().getPtsRentaCF();
/* 139 */     this.ptsSubastaCF = telefonoTO.getPuntosTO().getPtsSubastaCF();
/* 140 */     this.bonoEquipo = telefonoTO.getPuntosTO().getBonoEquipo();
/* 141 */     this.banRedime = (telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getBanRedencion() : 0);
/* 142 */     this.tipoProm = (telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getTipoPromocion() : "");
/* 143 */     this.planM2K = telefonoTO.getMobileTO().getPlanM2K();
/* 144 */     this.addendNvo = (telefonoTO.getPlanTO() != null ? telefonoTO.getPlanTO().getAdendumNvo() : 0);
/* 145 */     this.motivo = telefonoTO.getMobileTO().getMotivo();
/* 146 */     this.planNuevo = ((telefonoTO.getPlanTO() == null) || (telefonoTO.getPlanTO().getIdPlanNuevo() == null) ? "" : telefonoTO.getPlanTO().getIdPlanNuevo());
/* 147 */     this.estCobranza = telefonoTO.getMobileTO().getEstCobranza();
/* 148 */     this.claseCredito = telefonoTO.getMobileTO().getClaseCredit();
/* 149 */     this.addendumM2K = telefonoTO.getMobileTO().getAddM2K();
/* 150 */     this.idGrupo = telefonoTO.getIdGrupo();
/* 151 */     this.fechaAddendumM2K = telefonoTO.getMobileTO().getFecAddM2K();
/* 152 */     this.fechaSuspension = telefonoTO.getMobileTO().getFechaSuspension();
/* 153 */     this.sPromFacturaAV = telefonoTO.getMobileTO().getSPromFacturaAV();
/* 154 */     this.addCareg = telefonoTO.getMobileTO().getAddCareg();
/* 155 */     this.mesesCareg = telefonoTO.getMobileTO().getMesesCareg();
/* 156 */     this.tipoRedencion = telefonoTO.getTipoRedencion();
/* 157 */     this.fechaFolioReservaCF = telefonoTO.getPuntosTO().getFecReservacionCF();
/* 158 */     this.distReserva = telefonoTO.getPuntosTO().getDistribuidorReserva();
/*     */     
/* 160 */     this.secuencia = Integer.parseInt(telefonoTO.getSecuencia());
/* 161 */     this.idMensaje = telefonoTO.getIdMensaje();
/* 162 */     this.region = telefonoTO.getRegion();
/* 163 */     this.segmento = telefonoTO.getSegmento();
/* 164 */     this.ptsDisponibles = telefonoTO.getPuntosTO().getPtosDisponibles();
/* 165 */     this.ptsExcedentes = telefonoTO.getPuntosTO().getPtsExcedentes();
/* 166 */     this.ptsRedimidos = telefonoTO.getPuntosTO().getPtsRedimidos();
/* 167 */     this.ptsVencidos = telefonoTO.getPuntosTO().getPtsVencidos();
/* 168 */     this.ptsPorVencer = telefonoTO.getPuntosTO().getPtsPorVencer();
/* 169 */     this.ptsPorVencer2 = telefonoTO.getPuntosTO().getPtsPorVencer2();
/* 170 */     this.ptsPorVencer1 = telefonoTO.getPuntosTO().getPtsPorVencer1();
/* 171 */     this.ptsAntiguedad = telefonoTO.getPuntosTO().getPtsAntiguedad();
/* 172 */     this.ptsPromocion = telefonoTO.getPuntosTO().getPtsPromocion();
/* 173 */     this.ptsBonoEquipo = telefonoTO.getPuntosTO().getBonoEquipo();
/* 174 */     this.ptsTransferidos = telefonoTO.getPuntosTO().getPtsTransferidos();
/* 175 */     this.ptsRenta = telefonoTO.getPuntosTO().getPtsRenta();
/* 176 */     this.ptsSubasta = telefonoTO.getPuntosTO().getPtsSubasta();
/* 177 */     this.ptsRedimidosSF = telefonoTO.getPuntosTO().getPtsRedimidos();
/* 178 */     this.banMixto = telefonoTO.getPlanTO().getBanMixto();
/* 179 */     this.modalidad = telefonoTO.getPlanTO().getModalidad();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getPtsRedimidosSF()
/*     */   {
/* 186 */     return this.ptsRedimidosSF;
/*     */   }
/*     */   
/*     */   public void setPtsRedimidosSF(int ptsRedimidosSF) {
/* 190 */     this.ptsRedimidosSF = ptsRedimidosSF;
/*     */   }
/*     */   
/*     */   public String getCuenta() {
/* 194 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/* 198 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public String getMensaje() {
/* 202 */     return this.mensaje;
/*     */   }
/*     */   
/*     */   public void setMensaje(String mensaje) {
/* 206 */     this.mensaje = mensaje;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/* 210 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/* 214 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getCiclo() {
/* 218 */     return this.ciclo;
/*     */   }
/*     */   
/*     */   public void setCiclo(String ciclo) {
/* 222 */     this.ciclo = ciclo;
/*     */   }
/*     */   
/*     */   public String getCuentaPadre() {
/* 226 */     return this.cuentaPadre;
/*     */   }
/*     */   
/*     */   public void setCuentaPadre(String cuentaPadre) {
/* 230 */     this.cuentaPadre = cuentaPadre;
/*     */   }
/*     */   
/*     */   public String getFechaAlta() {
/* 234 */     return this.fechaAlta;
/*     */   }
/*     */   
/*     */   public void setFechaAlta(String fechaAlta) {
/* 238 */     this.fechaAlta = fechaAlta;
/*     */   }
/*     */   
/*     */   public String getFechaAltaM2K() {
/* 242 */     return this.fechaAltaM2K;
/*     */   }
/*     */   
/*     */   public void setFechaAltaM2K(String fechaAltaM2K) {
/* 246 */     this.fechaAltaM2K = fechaAltaM2K;
/*     */   }
/*     */   
/*     */   public String getSistema() {
/* 250 */     return this.sistema;
/*     */   }
/*     */   
/*     */   public void setSistema(String sistema) {
/* 254 */     this.sistema = sistema;
/*     */   }
/*     */   
/*     */   public String getEstatusTel() {
/* 258 */     return this.estatusTel;
/*     */   }
/*     */   
/*     */   public void setEstatusTel(String estatusTel) {
/* 262 */     this.estatusTel = estatusTel;
/*     */   }
/*     */   
/*     */   public String getTecnologia() {
/* 266 */     return this.tecnologia;
/*     */   }
/*     */   
/*     */   public void setTecnologia(String tecnologia) {
/* 270 */     this.tecnologia = tecnologia;
/*     */   }
/*     */   
/*     */   public String getTelefono() {
/* 274 */     return this.telefono;
/*     */   }
/*     */   
/*     */   public void setTelefono(String telefono) {
/* 278 */     this.telefono = telefono;
/*     */   }
/*     */   
/*     */   public String getFecVencidos() {
/* 282 */     return this.fecVencidos;
/*     */   }
/*     */   
/*     */   public void setFecVencidos(String fecVencidos) {
/* 286 */     this.fecVencidos = fecVencidos;
/*     */   }
/*     */   
/*     */   public String getFecVencer() {
/* 290 */     return this.fecVencer;
/*     */   }
/*     */   
/*     */   public void setFecVencer(String fecVencer) {
/* 294 */     this.fecVencer = fecVencer;
/*     */   }
/*     */   
/*     */   public String getFecVencer2() {
/* 298 */     return this.fecVencer2;
/*     */   }
/*     */   
/*     */   public void setFecVencer2(String fecVencer2) {
/* 302 */     this.fecVencer2 = fecVencer2;
/*     */   }
/*     */   
/*     */   public String getFecVencer1() {
/* 306 */     return this.fecVencer1;
/*     */   }
/*     */   
/*     */   public void setFecVencer1(String fecVencer1) {
/* 310 */     this.fecVencer1 = fecVencer1;
/*     */   }
/*     */   
/*     */   public String getPtosStatus() {
/* 314 */     return this.ptosStatus;
/*     */   }
/*     */   
/*     */   public void setPtosStatus(String ptosStatus) {
/* 318 */     this.ptosStatus = ptosStatus;
/*     */   }
/*     */   
/*     */   public String getFecFactura() {
/* 322 */     return this.fecFactura;
/*     */   }
/*     */   
/*     */   public void setFecFactura(String fecFactura) {
/* 326 */     this.fecFactura = fecFactura;
/*     */   }
/*     */   
/*     */   public String getFormaRedencion() {
/* 330 */     return this.formaRedencion;
/*     */   }
/*     */   
/*     */   public void setFormaRedencion(String formaRedencion) {
/* 334 */     this.formaRedencion = formaRedencion;
/*     */   }
/*     */   
/*     */   public String getPtsDisponiblesCF() {
/* 338 */     return this.ptsDisponiblesCF;
/*     */   }
/*     */   
/*     */   public void setPtsDisponiblesCF(String ptsDisponiblesCF) {
/* 342 */     this.ptsDisponiblesCF = ptsDisponiblesCF;
/*     */   }
/*     */   
/*     */   public String getPtsExcedentesCF() {
/* 346 */     return this.ptsExcedentesCF;
/*     */   }
/*     */   
/*     */   public void setPtsExcedentesCF(String ptsExcedentesCF) {
/* 350 */     this.ptsExcedentesCF = ptsExcedentesCF;
/*     */   }
/*     */   
/*     */   public String getPtsRedimidosCF() {
/* 354 */     return this.ptsRedimidosCF;
/*     */   }
/*     */   
/*     */   public void setPtsRedimidosCF(String ptsRedimidosCF) {
/* 358 */     this.ptsRedimidosCF = ptsRedimidosCF;
/*     */   }
/*     */   
/*     */   public String getPtsVencidosCF() {
/* 362 */     return this.ptsVencidosCF;
/*     */   }
/*     */   
/*     */   public void setPtsVencidosCF(String ptsVencidosCF) {
/* 366 */     this.ptsVencidosCF = ptsVencidosCF;
/*     */   }
/*     */   
/*     */   public String getPtsPorVencerCF() {
/* 370 */     return this.ptsPorVencerCF;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencerCF(String ptsPorVencerCF) {
/* 374 */     this.ptsPorVencerCF = ptsPorVencerCF;
/*     */   }
/*     */   
/*     */   public String getPtsPorVencer2CF() {
/* 378 */     return this.ptsPorVencer2CF;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer2CF(String ptsPorVencer2CF) {
/* 382 */     this.ptsPorVencer2CF = ptsPorVencer2CF;
/*     */   }
/*     */   
/*     */   public String getPtsPorVencer1CF() {
/* 386 */     return this.ptsPorVencer1CF;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer1CF(String ptsPorVencer1CF) {
/* 390 */     this.ptsPorVencer1CF = ptsPorVencer1CF;
/*     */   }
/*     */   
/*     */   public String getPtsAntiguedadCF() {
/* 394 */     return this.ptsAntiguedadCF;
/*     */   }
/*     */   
/*     */   public void setPtsAntiguedadCF(String ptsAntiguedadCF) {
/* 398 */     this.ptsAntiguedadCF = ptsAntiguedadCF;
/*     */   }
/*     */   
/*     */   public String getPtsPromocionCF() {
/* 402 */     return this.ptsPromocionCF;
/*     */   }
/*     */   
/*     */   public void setPtsPromocionCF(String ptsPromocionCF) {
/* 406 */     this.ptsPromocionCF = ptsPromocionCF;
/*     */   }
/*     */   
/*     */   public String getPtsBonoEquipoCF() {
/* 410 */     return this.ptsBonoEquipoCF;
/*     */   }
/*     */   
/*     */   public void setPtsBonoEquipoCF(String ptsBonoEquipoCF) {
/* 414 */     this.ptsBonoEquipoCF = ptsBonoEquipoCF;
/*     */   }
/*     */   
/*     */   public String getPtsRentaCF() {
/* 418 */     return this.ptsRentaCF;
/*     */   }
/*     */   
/*     */   public void setPtsRentaCF(String ptsRentaCF) {
/* 422 */     this.ptsRentaCF = ptsRentaCF;
/*     */   }
/*     */   
/*     */   public String getPtsSubastaCF() {
/* 426 */     return this.ptsSubastaCF;
/*     */   }
/*     */   
/*     */   public void setPtsSubastaCF(String ptsSubastaCF) {
/* 430 */     this.ptsSubastaCF = ptsSubastaCF;
/*     */   }
/*     */   
/*     */   public int getBanRedime() {
/* 434 */     return this.banRedime;
/*     */   }
/*     */   
/*     */   public void setBanRedime(int banRedime) {
/* 438 */     this.banRedime = banRedime;
/*     */   }
/*     */   
/*     */   public String getTipoProm() {
/* 442 */     return this.tipoProm;
/*     */   }
/*     */   
/*     */   public void setTipoProm(String tipoProm) {
/* 446 */     this.tipoProm = tipoProm;
/*     */   }
/*     */   
/*     */   public String getPlanM2K() {
/* 450 */     return this.planM2K;
/*     */   }
/*     */   
/*     */   public void setPlanM2K(String planM2K) {
/* 454 */     this.planM2K = planM2K;
/*     */   }
/*     */   
/*     */   public int getAddendNvo() {
/* 458 */     return this.addendNvo;
/*     */   }
/*     */   
/*     */   public void setAddendNvo(int addendNvo) {
/* 462 */     this.addendNvo = addendNvo;
/*     */   }
/*     */   
/*     */   public String getMotivo() {
/* 466 */     return this.motivo;
/*     */   }
/*     */   
/*     */   public void setMotivo(String motivo) {
/* 470 */     this.motivo = motivo;
/*     */   }
/*     */   
/*     */   public String getPlanNuevo() {
/* 474 */     return this.planNuevo;
/*     */   }
/*     */   
/*     */   public void setPlanNuevo(String planNuevo) {
/* 478 */     this.planNuevo = planNuevo;
/*     */   }
/*     */   
/*     */   public String getEstCobranza() {
/* 482 */     return this.estCobranza;
/*     */   }
/*     */   
/*     */   public void setEstCobranza(String estCobranza) {
/* 486 */     this.estCobranza = estCobranza;
/*     */   }
/*     */   
/*     */   public String getClaseCredito() {
/* 490 */     return this.claseCredito;
/*     */   }
/*     */   
/*     */   public void setClaseCredito(String claseCredito) {
/* 494 */     this.claseCredito = claseCredito;
/*     */   }
/*     */   
/*     */   public String getAddendumM2K() {
/* 498 */     return this.addendumM2K;
/*     */   }
/*     */   
/*     */   public void setAddendumM2K(String addendumM2K) {
/* 502 */     this.addendumM2K = addendumM2K;
/*     */   }
/*     */   
/*     */   public String getIdGrupo() {
/* 506 */     return this.idGrupo;
/*     */   }
/*     */   
/*     */   public void setIdGrupo(String idGrupo) {
/* 510 */     this.idGrupo = idGrupo;
/*     */   }
/*     */   
/*     */   public String getFechaAddendumM2K() {
/* 514 */     return this.fechaAddendumM2K;
/*     */   }
/*     */   
/*     */   public void setFechaAddendumM2K(String fechaAddendumM2K) {
/* 518 */     this.fechaAddendumM2K = fechaAddendumM2K;
/*     */   }
/*     */   
/*     */   public String getTipoRedencion() {
/* 522 */     return this.tipoRedencion;
/*     */   }
/*     */   
/*     */   public void setTipoRedencion(String tipoRedencion) {
/* 526 */     this.tipoRedencion = tipoRedencion;
/*     */   }
/*     */   
/*     */   public String getFechaFolioReservaCF() {
/* 530 */     return this.fechaFolioReservaCF;
/*     */   }
/*     */   
/*     */   public void setFechaFolioReservaCF(String fechaFolioReservaCF) {
/* 534 */     this.fechaFolioReservaCF = fechaFolioReservaCF;
/*     */   }
/*     */   
/*     */   public String getDistReserva() {
/* 538 */     return this.distReserva;
/*     */   }
/*     */   
/*     */   public void setDistReserva(String distReserva) {
/* 542 */     this.distReserva = distReserva;
/*     */   }
/*     */   
/*     */   public int getSecuencia() {
/* 546 */     return this.secuencia;
/*     */   }
/*     */   
/*     */   public void setSecuencia(int secuencia) {
/* 550 */     this.secuencia = secuencia;
/*     */   }
/*     */   
/*     */   public int getIdMensaje() {
/* 554 */     return this.idMensaje;
/*     */   }
/*     */   
/*     */   public void setIdMensaje(int idMensaje) {
/* 558 */     this.idMensaje = idMensaje;
/*     */   }
/*     */   
/*     */   public int getRegion() {
/* 562 */     return this.region;
/*     */   }
/*     */   
/*     */   public void setRegion(int region) {
/* 566 */     this.region = region;
/*     */   }
/*     */   
/*     */   public String getSegmento() {
/* 570 */     return this.segmento;
/*     */   }
/*     */   
/*     */   public void setSegmento(String segmento) {
/* 574 */     this.segmento = segmento;
/*     */   }
/*     */   
/*     */   public int getPtsDisponibles() {
/* 578 */     return this.ptsDisponibles;
/*     */   }
/*     */   
/*     */   public void setPtsDisponibles(int ptsDisponibles) {
/* 582 */     this.ptsDisponibles = ptsDisponibles;
/*     */   }
/*     */   
/*     */   public int getPtsExcedentes() {
/* 586 */     return this.ptsExcedentes;
/*     */   }
/*     */   
/*     */   public void setPtsExcedentes(int ptsExcedentes) {
/* 590 */     this.ptsExcedentes = ptsExcedentes;
/*     */   }
/*     */   
/*     */   public int getPtsRedimidos() {
/* 594 */     return this.ptsRedimidos;
/*     */   }
/*     */   
/*     */   public void setPtsRedimidos(int ptsRedimidos) {
/* 598 */     this.ptsRedimidos = ptsRedimidos;
/*     */   }
/*     */   
/*     */   public int getPtsVencidos() {
/* 602 */     return this.ptsVencidos;
/*     */   }
/*     */   
/*     */   public void setPtsVencidos(int ptsVencidos) {
/* 606 */     this.ptsVencidos = ptsVencidos;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer() {
/* 610 */     return this.ptsPorVencer;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer(int ptsPorVencer) {
/* 614 */     this.ptsPorVencer = ptsPorVencer;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer2() {
/* 618 */     return this.ptsPorVencer2;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer2(int ptsPorVencer2) {
/* 622 */     this.ptsPorVencer2 = ptsPorVencer2;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer1() {
/* 626 */     return this.ptsPorVencer1;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer1(int ptsPorVencer1) {
/* 630 */     this.ptsPorVencer1 = ptsPorVencer1;
/*     */   }
/*     */   
/*     */   public int getPtsAntiguedad() {
/* 634 */     return this.ptsAntiguedad;
/*     */   }
/*     */   
/*     */   public void setPtsAntiguedad(int ptsAntiguedad) {
/* 638 */     this.ptsAntiguedad = ptsAntiguedad;
/*     */   }
/*     */   
/*     */   public int getPtsPromocion() {
/* 642 */     return this.ptsPromocion;
/*     */   }
/*     */   
/*     */   public void setPtsPromocion(int ptsPromocion) {
/* 646 */     this.ptsPromocion = ptsPromocion;
/*     */   }
/*     */   
/*     */   public int getPtsBonoEquipo() {
/* 650 */     return this.ptsBonoEquipo;
/*     */   }
/*     */   
/*     */   public void setPtsBonoEquipo(int ptsBonoEquipo) {
/* 654 */     this.ptsBonoEquipo = ptsBonoEquipo;
/*     */   }
/*     */   
/*     */   public int getPtsRenta() {
/* 658 */     return this.ptsRenta;
/*     */   }
/*     */   
/*     */   public void setPtsRenta(int ptsRenta) {
/* 662 */     this.ptsRenta = ptsRenta;
/*     */   }
/*     */   
/*     */   public int getPtsSubasta() {
/* 666 */     return this.ptsSubasta;
/*     */   }
/*     */   
/*     */   public void setPtsSubasta(int ptsSubasta) {
/* 670 */     this.ptsSubasta = ptsSubasta;
/*     */   }
/*     */   
/*     */   public String getFechaSuspension() {
/* 674 */     return this.fechaSuspension;
/*     */   }
/*     */   
/*     */   public void setFechaSuspension(String fechaSuspension) {
/* 678 */     this.fechaSuspension = fechaSuspension;
/*     */   }
/*     */   
/*     */   public String getSPromFacturaAV() {
/* 682 */     return this.sPromFacturaAV;
/*     */   }
/*     */   
/*     */   public void setSPromFacturaAV(String promFacturaAV) {
/* 686 */     this.sPromFacturaAV = promFacturaAV;
/*     */   }
/*     */   
/*     */   public String getAddCareg() {
/* 690 */     return this.addCareg;
/*     */   }
/*     */   
/*     */   public void setAddCareg(String addCareg) {
/* 694 */     this.addCareg = addCareg;
/*     */   }
/*     */   
/*     */   public String getMesesCareg() {
/* 698 */     return this.mesesCareg;
/*     */   }
/*     */   
/*     */   public void setMesesCareg(String mesesCareg) {
/* 702 */     this.mesesCareg = mesesCareg;
/*     */   }
/*     */   
/* 705 */   public String getBanMixto() { return this.banMixto; }
/*     */   
/*     */   public void setBanMixto(String banMixto)
/*     */   {
/* 709 */     this.banMixto = banMixto;
/*     */   }
/*     */   
/*     */   public String getModalidad() {
/* 713 */     return this.modalidad;
/*     */   }
/*     */   
/*     */   public void setModalidad(String modalidad) {
/* 717 */     this.modalidad = modalidad;
/*     */   }
/*     */   
/*     */   public String toStringTelefonoServiceTO() {
/* 721 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 735 */       this.cuenta + "|" + this.mensaje + "|" + this.nombre + "|" + this.ciclo + "|" + this.cuentaPadre + "|" + this.fechaAlta + "|" + this.fechaAltaM2K + "|" + this.sistema + "|" + this.estatusTel + "|" + this.tecnologia + "|" + this.telefono + "|" + this.fecVencidos + "|" + this.fecVencer + "|" + this.fecVencer2 + "|" + this.fecVencer1 + "|" + this.ptosStatus + "|" + this.fecFactura + "|" + this.fechaSuspension + "|" + this.formaRedencion + "|" + this.ptsDisponiblesCF + "|" + this.ptsExcedentesCF + "|" + this.ptsRedimidosCF + "|" + this.ptsVencidosCF + "|" + this.ptsPorVencerCF + "|" + this.ptsPorVencer2CF + "|" + this.ptsPorVencer1CF + "|" + this.ptsAntiguedadCF + "|" + this.ptsPromocionCF + "|" + this.ptsBonoEquipoCF + "|" + this.ptsRentaCF + "|" + this.ptsSubastaCF + "|" + this.tipoProm + "|" + this.planM2K + "|" + this.motivo + "|" + this.planNuevo + "|" + this.estCobranza + "|" + this.claseCredito + "|" + this.addendumM2K + "|" + this.idGrupo + "|" + this.fechaAddendumM2K + "|" + this.tipoRedencion + "|" + this.fechaFolioReservaCF + "|" + this.distReserva + "|" + this.sPromFacturaAV + "|" + this.addCareg + "|" + this.mesesCareg + "|" + this.segmento + "|" + this.banRedime + "|" + this.addendNvo + "|" + this.secuencia + "|" + this.idMensaje + "|" + this.region + "|" + this.ptsDisponibles + "|" + this.ptsExcedentes + "|" + this.ptsRedimidos + "|" + this.ptsVencidos + "|" + this.ptsPorVencer + "|" + this.ptsPorVencer2 + "|" + this.ptsPorVencer1 + "|" + this.ptsAntiguedad + "|" + this.ptsPromocion + "|" + this.ptsBonoEquipo + "|" + this.ptsTransferidos + "|" + this.ptsRenta + "|" + this.ptsSubasta + "|" + this.ptsRedimidosSF + "|" + this.bonoEquipo;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/TelefonoServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */