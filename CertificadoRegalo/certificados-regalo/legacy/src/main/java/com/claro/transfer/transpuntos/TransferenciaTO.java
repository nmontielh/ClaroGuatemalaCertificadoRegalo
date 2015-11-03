/*     */ package com.claro.transfer.transpuntos;
/*     */ 
/*     */ import com.claro.transfer.PerfilTO;
/*     */ import com.claro.transfer.PuntosTO;
/*     */ import com.claro.transfer.TelefonoTO;
/*     */ import java.io.Serializable;
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
/*     */ public class TransferenciaTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1098520152549260617L;
/*     */   private String telefonoOrigen;
/*     */   private String cuentaOrigen;
/*     */   private int regionOrigen;
/*     */   private String tipoTransferencia;
/*     */   private String fecFactura;
/*     */   private String fechaAlta;
/*     */   private String telefonoDestino;
/*     */   private String cuentaDestino;
/*     */   private int regionDestino;
/*     */   private int puntosTrasnferidos;
/*     */   private String comentario;
/*     */   private String nombreClienteOrigen;
/*     */   private int ptosDisponiblesOrigen;
/*     */   private String tecnologiaOrigen;
/*     */   private TelefonoTO telefonoTO;
/*     */   private PuntosTO puntosOrigenTO;
/*     */   private String cuentaPadreOrigen;
/*     */   private String cuentaLineaOrigen;
/*     */   private String estatusLineaOrigen;
/*     */   private int secuenciaOrigen;
/*     */   private int secuenciaDestino;
/*     */   private String idUsuario;
/*     */   private String rfcOrigen;
/*     */   private PerfilTO perfilTO;
/*     */   
/*     */   public String getTelefonoOrigen()
/*     */   {
/*  81 */     return this.telefonoOrigen;
/*     */   }
/*     */   
/*     */   public void setTelefonoOrigen(String telefonoOrigen) {
/*  85 */     this.telefonoOrigen = telefonoOrigen;
/*     */   }
/*     */   
/*     */   public String getCuentaOrigen() {
/*  89 */     return this.cuentaOrigen;
/*     */   }
/*     */   
/*     */   public void setCuentaOrigen(String cuentaOrigen) {
/*  93 */     this.cuentaOrigen = cuentaOrigen;
/*     */   }
/*     */   
/*     */   public int getRegionOrigen() {
/*  97 */     return this.regionOrigen;
/*     */   }
/*     */   
/*     */   public void setRegionOrigen(int regionOrigen) {
/* 101 */     this.regionOrigen = regionOrigen;
/*     */   }
/*     */   
/*     */   public String getTipoTransferencia() {
/* 105 */     return this.tipoTransferencia;
/*     */   }
/*     */   
/*     */   public void setTipoTransferencia(String tipoTransferencia) {
/* 109 */     this.tipoTransferencia = tipoTransferencia;
/*     */   }
/*     */   
/*     */   public String getTelefonoDestino() {
/* 113 */     return this.telefonoDestino;
/*     */   }
/*     */   
/*     */   public void setTelefonoDestino(String telefonoDestino) {
/* 117 */     this.telefonoDestino = telefonoDestino;
/*     */   }
/*     */   
/*     */   public String getCuentaDestino() {
/* 121 */     return this.cuentaDestino;
/*     */   }
/*     */   
/*     */   public void setCuentaDestino(String cuentaDestino) {
/* 125 */     this.cuentaDestino = cuentaDestino;
/*     */   }
/*     */   
/*     */   public int getRegionDestino() {
/* 129 */     return this.regionDestino;
/*     */   }
/*     */   
/*     */   public void setRegionDestino(int regionDestino) {
/* 133 */     this.regionDestino = regionDestino;
/*     */   }
/*     */   
/*     */   public int getPuntosTrasnferidos() {
/* 137 */     return this.puntosTrasnferidos;
/*     */   }
/*     */   
/*     */   public void setPuntosTrasnferidos(int puntosTrasnferidos) {
/* 141 */     this.puntosTrasnferidos = puntosTrasnferidos;
/*     */   }
/*     */   
/*     */   public String getComentario() {
/* 145 */     return this.comentario;
/*     */   }
/*     */   
/*     */   public void setComentario(String comentario) {
/* 149 */     this.comentario = comentario;
/*     */   }
/*     */   
/*     */   public String getNombreClienteOrigen() {
/* 153 */     return this.nombreClienteOrigen;
/*     */   }
/*     */   
/*     */   public void setNombreClienteOrigen(String nombreClienteOrigen) {
/* 157 */     this.nombreClienteOrigen = nombreClienteOrigen;
/*     */   }
/*     */   
/*     */   public int getPtosDisponiblesOrigen() {
/* 161 */     return this.ptosDisponiblesOrigen;
/*     */   }
/*     */   
/*     */   public void setPtosDisponiblesOrigen(int ptosDisponiblesOrigen) {
/* 165 */     this.ptosDisponiblesOrigen = ptosDisponiblesOrigen;
/*     */   }
/*     */   
/*     */   public String getTecnologiaOrigen() {
/* 169 */     return this.tecnologiaOrigen;
/*     */   }
/*     */   
/*     */   public void setTecnologiaOrigen(String tecnologiaOrigen) {
/* 173 */     this.tecnologiaOrigen = tecnologiaOrigen;
/*     */   }
/*     */   
/*     */   public String getCuentaPadreOrigen() {
/* 177 */     return this.cuentaPadreOrigen;
/*     */   }
/*     */   
/*     */   public void setCuentaPadreOrigen(String cuentaPadreOrigen) {
/* 181 */     this.cuentaPadreOrigen = cuentaPadreOrigen;
/*     */   }
/*     */   
/*     */   public String getCuentaLineaOrigen() {
/* 185 */     return this.cuentaLineaOrigen;
/*     */   }
/*     */   
/*     */   public void setCuentaLineaOrigen(String cuentaLineaOrigen) {
/* 189 */     this.cuentaLineaOrigen = cuentaLineaOrigen;
/*     */   }
/*     */   
/*     */   public String getEstatusLineaOrigen() {
/* 193 */     return this.estatusLineaOrigen;
/*     */   }
/*     */   
/*     */   public void setEstatusLineaOrigen(String estatusLineaOrigen) {
/* 197 */     this.estatusLineaOrigen = estatusLineaOrigen;
/*     */   }
/*     */   
/*     */   public int getSecuenciaOrigen() {
/* 201 */     return this.secuenciaOrigen;
/*     */   }
/*     */   
/*     */   public void setSecuenciaOrigen(int secuenciaOrigen) {
/* 205 */     this.secuenciaOrigen = secuenciaOrigen;
/*     */   }
/*     */   
/*     */   public String getIdUsuario() {
/* 209 */     return this.idUsuario;
/*     */   }
/*     */   
/*     */   public void setIdUsuario(String idUsuario) {
/* 213 */     this.idUsuario = idUsuario;
/*     */   }
/*     */   
/*     */   public TelefonoTO getTelefonoTO() {
/* 217 */     return this.telefonoTO;
/*     */   }
/*     */   
/*     */   public void setTelefonoTO(TelefonoTO telefonoTO) {
/* 221 */     this.telefonoTO = telefonoTO;
/*     */   }
/*     */   
/*     */   public PuntosTO getPuntosOrigenTO() {
/* 225 */     return this.puntosOrigenTO;
/*     */   }
/*     */   
/*     */   public void setPuntosOrigenTO(PuntosTO puntosOrigenTO) {
/* 229 */     this.puntosOrigenTO = puntosOrigenTO;
/*     */   }
/*     */   
/*     */   public String getFecFactura() {
/* 233 */     return this.fecFactura;
/*     */   }
/*     */   
/*     */   public void setFecFactura(String fecFactura) {
/* 237 */     this.fecFactura = fecFactura;
/*     */   }
/*     */   
/*     */   public String getFechaAlta() {
/* 241 */     return this.fechaAlta;
/*     */   }
/*     */   
/*     */   public void setFechaAlta(String fechaAlta) {
/* 245 */     this.fechaAlta = fechaAlta;
/*     */   }
/*     */   
/*     */   public String getRfcOrigen()
/*     */   {
/* 250 */     return this.rfcOrigen;
/*     */   }
/*     */   
/*     */   public void setRfcOrigen(String rfcOrigen)
/*     */   {
/* 255 */     this.rfcOrigen = rfcOrigen;
/*     */   }
/*     */   
/*     */   public PerfilTO getPerfilTO()
/*     */   {
/* 260 */     return this.perfilTO;
/*     */   }
/*     */   
/*     */   public void setPerfilTO(PerfilTO perfilTO)
/*     */   {
/* 265 */     this.perfilTO = perfilTO;
/*     */   }
/*     */   
/*     */   public int getSecuenciaDestino()
/*     */   {
/* 270 */     return this.secuenciaDestino;
/*     */   }
/*     */   
/*     */   public void setSecuenciaDestino(int secuenciaDestino)
/*     */   {
/* 275 */     this.secuenciaDestino = secuenciaDestino;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/transpuntos/TransferenciaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */