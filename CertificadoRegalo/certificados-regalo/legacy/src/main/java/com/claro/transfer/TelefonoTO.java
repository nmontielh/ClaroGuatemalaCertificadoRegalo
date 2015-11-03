/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.sql.Timestamp;
/*     */ 
/*     */ public class TelefonoTO extends MensajeTO implements java.io.Serializable {
/*     */   private static final long serialVersionUID = -2641358147985483649L;
/*     */   private String cuenta;
/*     */   private String secuencia;
/*     */   private String ctaPadre;
/*     */   private String telefono;
/*     */   private java.sql.Date fechaAlta;
/*     */   private String sistema;
/*     */   private int region;
/*     */   private String regionTxt;
/*     */   private String ciclo;
/*     */   private int banSubasta;
/*     */   private String plan;
/*     */   private int addendum;
/*     */   private java.sql.Date fecFactura;
/*     */   private String tecnologia;
/*     */   private int bonoEquipo;
/*     */   private int renta;
/*     */   private int idSegmento;
/*     */   private String segmento;
/*     */   private String formaRedencion;
/*     */   private String tipoRedencion;
/*     */   private String promocionSim;
/*     */   private int millas;
/*     */   private PuntosTO puntosTO;
/*     */   private MobileTO mobileTO;
/*     */   private PlanTO planTO;
/*     */   private Timestamp fechaExpira;
/*     */   private Timestamp fechaAltaTime;
/*     */   private String fechaUltMov;
/*     */   private boolean existePromocionGap;
/*     */   private boolean aplicaGapCA;
/*     */   private String fzaVta;
/*     */   private boolean aceptaBonoInbursa;
/*     */   private String tipoProm;
/*     */   private String banRedime;
/*     */   private String idGrupo;
/*     */   private String addNvo;
/*     */   private String nickName;
/*     */   private String sAnacr;
/*     */   private int nMesActual;
/*     */   private int nDiasMes;
/*     */   private int nDiaActual;
/*     */   private int nBonoEquipo;
/*     */   private int sobBonoEquipo;
/*     */   
/*  51 */   public String getFechaUltMov() { return this.fechaUltMov; }
/*     */   
/*     */   public void setFechaUltMov(String fecUltMov)
/*     */   {
/*     */     try {
/*  56 */       java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
/*  57 */       java.util.Date date = formatter.parse(fecUltMov);
/*  58 */       this.fechaAltaTime = new Timestamp(date.getTime());
/*     */     } catch (Exception e) {
/*  60 */       System.out.println(e);
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*  78 */   private java.math.BigDecimal precio = new java.math.BigDecimal(0);
/*  79 */   private long nPtsaRedimir = 0L;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String folio;
/*     */   private String estatusPuntos;
/*     */   
/*     */   public String getEstatusPuntos()
/*     */   {
/*  87 */     return this.estatusPuntos;
/*     */   }
/*     */   
/*  90 */   public void setEstatusPuntos(String estatusPuntos) { this.estatusPuntos = estatusPuntos; }
/*     */   
/*     */ 
/*     */   private String estatusCarta;
/*     */   private boolean aplicaRedencion;
/*     */   private String descripcionPlan;
/*     */   private String idUsuario;
/*     */   public String getIdUsuario()
/*     */   {
/*  99 */     return this.idUsuario;
/*     */   }
/*     */   
/* 102 */   public String getDescripcionPlan() { return this.descripcionPlan; }
/*     */   
/*     */   public void setDescripcionPlan(String descripcionPlan) {
/* 105 */     this.descripcionPlan = descripcionPlan;
/*     */   }
/*     */   
/*     */   public MobileTO getMobileTO() {
/* 109 */     return this.mobileTO;
/*     */   }
/*     */   
/* 112 */   public void setMobileTO(MobileTO mobileTO) { this.mobileTO = mobileTO; }
/*     */   
/*     */   public String getCuenta() {
/* 115 */     return this.cuenta;
/*     */   }
/*     */   
/* 118 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getSecuencia() {
/* 121 */     return this.secuencia;
/*     */   }
/*     */   
/* 124 */   public void setSecuencia(String secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getCtaPadre() {
/* 127 */     return this.ctaPadre;
/*     */   }
/*     */   
/* 130 */   public void setCtaPadre(String ctaPadre) { this.ctaPadre = ctaPadre; }
/*     */   
/*     */   public String getTelefono() {
/* 133 */     return this.telefono;
/*     */   }
/*     */   
/* 136 */   public void setTelefono(String telefono) { this.telefono = telefono; }
/*     */   
/*     */   public java.sql.Date getFechaAlta() {
/* 139 */     return this.fechaAlta;
/*     */   }
/*     */   
/* 142 */   public void setFechaAlta(java.sql.Date fechaAlta) { this.fechaAlta = fechaAlta; }
/*     */   
/*     */   public String getSistema() {
/* 145 */     return this.sistema;
/*     */   }
/*     */   
/* 148 */   public void setSistema(String sistema) { this.sistema = sistema; }
/*     */   
/*     */   public int getRegion() {
/* 151 */     return this.region;
/*     */   }
/*     */   
/* 154 */   public void setRegion(int region) { this.region = region; }
/*     */   
/*     */   public String getCiclo()
/*     */   {
/* 158 */     return this.ciclo;
/*     */   }
/*     */   
/* 161 */   public void setCiclo(String ciclo) { this.ciclo = ciclo; }
/*     */   
/*     */   public int getBanSubasta()
/*     */   {
/* 165 */     return this.banSubasta;
/*     */   }
/*     */   
/* 168 */   public void setBanSubasta(int banSubasta) { this.banSubasta = banSubasta; }
/*     */   
/*     */   public String getPlan() {
/* 171 */     return this.plan;
/*     */   }
/*     */   
/* 174 */   public void setPlan(String plan) { this.plan = plan; }
/*     */   
/*     */   public int getAddendum() {
/* 177 */     return this.addendum;
/*     */   }
/*     */   
/* 180 */   public void setAddendum(int addendum) { this.addendum = addendum; }
/*     */   
/*     */   public java.sql.Date getFecFactura() {
/* 183 */     return this.fecFactura;
/*     */   }
/*     */   
/* 186 */   public void setFecFactura(java.sql.Date fecFactura) { this.fecFactura = fecFactura; }
/*     */   
/*     */   public String getTecnologia() {
/* 189 */     return this.tecnologia;
/*     */   }
/*     */   
/* 192 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public int getBonoEquipo() {
/* 195 */     return this.bonoEquipo;
/*     */   }
/*     */   
/* 198 */   public void setBonoEquipo(int bonoEquipo) { this.bonoEquipo = bonoEquipo; }
/*     */   
/*     */   public int getRenta() {
/* 201 */     return this.renta;
/*     */   }
/*     */   
/* 204 */   public void setRenta(int renta) { this.renta = renta; }
/*     */   
/*     */   public int getIdSegmento() {
/* 207 */     return this.idSegmento;
/*     */   }
/*     */   
/* 210 */   public void setIdSegmento(int idSegmento) { this.idSegmento = idSegmento; }
/*     */   
/*     */   public String getSegmento() {
/* 213 */     return this.segmento;
/*     */   }
/*     */   
/* 216 */   public void setSegmento(String segmento) { this.segmento = segmento; }
/*     */   
/*     */   public PuntosTO getPuntosTO() {
/* 219 */     return this.puntosTO;
/*     */   }
/*     */   
/* 222 */   public void setPuntosTO(PuntosTO puntosTO) { this.puntosTO = puntosTO; }
/*     */   
/*     */   public int getNMesActual() {
/* 225 */     return this.nMesActual;
/*     */   }
/*     */   
/* 228 */   public void setNMesActual(int mesActual) { this.nMesActual = mesActual; }
/*     */   
/*     */   public int getNDiasMes() {
/* 231 */     return this.nDiasMes;
/*     */   }
/*     */   
/* 234 */   public void setNDiasMes(int diasMes) { this.nDiasMes = diasMes; }
/*     */   
/*     */   public int getNBonoEquipo() {
/* 237 */     return this.nBonoEquipo;
/*     */   }
/*     */   
/* 240 */   public void setNBonoEquipo(int bonoEquipo) { this.nBonoEquipo = bonoEquipo; }
/*     */   
/*     */   public int getSobBonoEquipo() {
/* 243 */     return this.sobBonoEquipo;
/*     */   }
/*     */   
/* 246 */   public void setSobBonoEquipo(int sobBonoEquipo) { this.sobBonoEquipo = sobBonoEquipo; }
/*     */   
/*     */   public java.math.BigDecimal getPrecio() {
/* 249 */     return this.precio;
/*     */   }
/*     */   
/* 252 */   public void setPrecio(java.math.BigDecimal precio) { this.precio = precio; }
/*     */   
/*     */   public long getNPtsaRedimir() {
/* 255 */     return this.nPtsaRedimir;
/*     */   }
/*     */   
/* 258 */   public void setNPtsaRedimir(long ptsaRedimir) { this.nPtsaRedimir = ptsaRedimir; }
/*     */   
/*     */   public int getNDiaActual() {
/* 261 */     return this.nDiaActual;
/*     */   }
/*     */   
/* 264 */   public void setNDiaActual(int diaActual) { this.nDiaActual = diaActual; }
/*     */   
/*     */   public String getNickName() {
/* 267 */     return this.nickName;
/*     */   }
/*     */   
/* 270 */   public void setNickName(String nickName) { this.nickName = nickName; }
/*     */   
/*     */   public String getSAnacr() {
/* 273 */     return this.sAnacr;
/*     */   }
/*     */   
/* 276 */   public void setSAnacr(String anacr) { this.sAnacr = anacr; }
/*     */   
/*     */   public String getTipoProm() {
/* 279 */     return this.tipoProm;
/*     */   }
/*     */   
/* 282 */   public void setTipoProm(String tipoProm) { this.tipoProm = tipoProm; }
/*     */   
/*     */   public String getBanRedime() {
/* 285 */     return this.banRedime;
/*     */   }
/*     */   
/* 288 */   public void setBanRedime(String banRedime) { this.banRedime = banRedime; }
/*     */   
/*     */   public String getIdGrupo() {
/* 291 */     return this.idGrupo;
/*     */   }
/*     */   
/* 294 */   public void setIdGrupo(String idGrupo) { this.idGrupo = idGrupo; }
/*     */   
/*     */   public String getAddNvo() {
/* 297 */     return this.addNvo;
/*     */   }
/*     */   
/* 300 */   public void setAddNvo(String addNvo) { this.addNvo = addNvo; }
/*     */   
/*     */   public String getRegionTxt() {
/* 303 */     this.regionTxt = ("R0" + this.region);
/* 304 */     return this.regionTxt;
/*     */   }
/*     */   
/* 307 */   public void setRegionTxt(String regionTxt) { this.regionTxt = regionTxt; }
/*     */   
/*     */   public int getMillas() {
/* 310 */     return this.millas;
/*     */   }
/*     */   
/* 313 */   public void setMillas(int millas) { this.millas = millas; }
/*     */   
/*     */   public PlanTO getPlanTO() {
/* 316 */     return this.planTO;
/*     */   }
/*     */   
/*     */   public void setPlanTO(PlanTO planTO) {
/* 320 */     this.planTO = planTO;
/*     */   }
/*     */   
/* 323 */   public String getFormaRedencion() { return this.formaRedencion; }
/*     */   
/*     */   public void setFormaRedencion(String formaRedencion) {
/* 326 */     this.formaRedencion = formaRedencion;
/*     */   }
/*     */   
/* 329 */   public boolean getAplicaRedencion() { return this.aplicaRedencion; }
/*     */   
/*     */   public void setAplicaRedencion(boolean aplicaRedencion) {
/* 332 */     this.aplicaRedencion = aplicaRedencion;
/*     */   }
/*     */   
/* 335 */   public String getTipoRedencion() { return this.tipoRedencion; }
/*     */   
/*     */   public void setTipoRedencion(String tipoRedencion) {
/* 338 */     this.tipoRedencion = tipoRedencion;
/*     */   }
/*     */   
/*     */   public String getPromocionSim() {
/* 342 */     return this.promocionSim;
/*     */   }
/*     */   
/* 345 */   public void setPromocionSim(String promocionSim) { this.promocionSim = promocionSim; }
/*     */   
/*     */ 
/* 348 */   public Timestamp getFechaExpira() { return this.fechaExpira; }
/*     */   
/* 350 */   public void setFechaExpira(Timestamp FechaExpira) { this.fechaExpira = FechaExpira; }
/*     */   
/*     */ 
/* 353 */   public Timestamp getFechaAltaTime() { return this.fechaAltaTime; }
/*     */   
/* 355 */   public void setFechaAltaTime(Timestamp FechaAltaTime) { this.fechaAltaTime = FechaAltaTime; }
/*     */   
/*     */ 
/* 358 */   public String getMarca() { return this.marca; }
/*     */   
/* 360 */   public void setMarca(String Marca) { this.marca = Marca; }
/*     */   
/*     */ 
/*     */ 
/* 364 */   public String getModelo() { return this.modelo; }
/*     */   
/* 366 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */ 
/* 369 */   public String getFolio() { return this.folio; }
/*     */   
/* 371 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public void setIdUsuario(String idUsuario) {
/* 374 */     this.idUsuario = idUsuario;
/*     */   }
/*     */   
/*     */   public boolean isExistePromocionGap() {
/* 378 */     return this.existePromocionGap;
/*     */   }
/*     */   
/*     */   public void setExistePromocionGap(boolean existePromocionGap) {
/* 382 */     this.existePromocionGap = existePromocionGap;
/*     */   }
/*     */   
/*     */   public boolean isAplicaGapCA() {
/* 386 */     return this.aplicaGapCA;
/*     */   }
/*     */   
/*     */   public void setAplicaGapCA(boolean aplicaGapCA) {
/* 390 */     this.aplicaGapCA = aplicaGapCA;
/*     */   }
/*     */   
/*     */   public String getEstatusCarta() {
/* 394 */     return this.estatusCarta;
/*     */   }
/*     */   
/*     */   public void setEstatusCarta(String estatusCarta) {
/* 398 */     this.estatusCarta = estatusCarta;
/*     */   }
/*     */   
/*     */   public String getEstatusCarta(String statusLinea)
/*     */   {
/* 403 */     if (("AC".equals(statusLinea)) || 
/* 404 */       ("SU".equals(statusLinea))) {
/* 405 */       this.estatusCarta = "P";
/*     */     } else {
/* 407 */       this.estatusCarta = "O";
/*     */     }
/* 409 */     return this.estatusCarta;
/*     */   }
/*     */   
/* 412 */   public String getFzaVta() { return this.fzaVta; }
/*     */   
/*     */   public void setFzaVta(String fzaVta)
/*     */   {
/* 416 */     this.fzaVta = fzaVta;
/*     */   }
/*     */   
/*     */   public boolean isAceptaBonoInbursa() {
/* 420 */     return this.aceptaBonoInbursa;
/*     */   }
/*     */   
/*     */   public void setAceptaBonoInbursa(boolean aceptaBonoInbursa) {
/* 424 */     this.aceptaBonoInbursa = aceptaBonoInbursa;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/TelefonoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */