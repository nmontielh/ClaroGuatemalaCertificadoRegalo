/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.AlianzasTO;
/*     */ import com.claro.transfer.PuntosRedimidosTO;
/*     */ import com.claro.transfer.TelefonoSimpleTO;
/*     */ import com.claro.util.Constantes;
/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ 
/*     */ public class AlianzaServiceTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6022606958056269122L;
/*     */   private String cuenta;
/*     */   private String linea;
/*     */   private String fechaOperacion;
/*     */   private String cuentaAlianza;
/*     */   private String usuario;
/*     */   private String comentario;
/*     */   private int puntosCanjeados;
/*     */   private int millas;
/*     */   private int valorCupon;
/*     */   
/*     */   public AlianzaServiceTO(AlianzasTO alianzasTO)
/*     */   {
/*  27 */     this.cuenta = alianzasTO.getTelefonoSimpleTO().getCuenta();
/*  28 */     this.linea = alianzasTO.getTelefonoSimpleTO().getLinea();
/*  29 */     this.fechaOperacion = Constantes.DATEFORMATdd_MM_YYYY.format(alianzasTO.getFechaOperacion());
/*  30 */     this.cuentaAlianza = alianzasTO.getCuentaAlianza();
/*  31 */     this.puntosCanjeados = alianzasTO.getPuntosRedimidosTO().getPtsCanjeados();
/*  32 */     this.millas = alianzasTO.getMillas();
/*  33 */     this.usuario = alianzasTO.getUsuario();
/*  34 */     this.comentario = alianzasTO.getComentario();
/*  35 */     this.valorCupon = alianzasTO.getValorCuponOrig();
/*     */   }
/*     */   
/*     */   public int getValorCupon() {
/*  39 */     return this.valorCupon;
/*     */   }
/*     */   
/*     */   public void setValorCupon(int valorCupon) {
/*  43 */     this.valorCupon = valorCupon;
/*     */   }
/*     */   
/*     */   public AlianzaServiceTO() {}
/*     */   
/*     */   public String getCuenta() {
/*  49 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/*  53 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public String getLinea() {
/*  57 */     return this.linea;
/*     */   }
/*     */   
/*     */   public void setLinea(String linea) {
/*  61 */     this.linea = linea;
/*     */   }
/*     */   
/*     */   public String getFechaOperacion() {
/*  65 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*     */   public void setFechaOperacion(String fechaOperacion) {
/*  69 */     this.fechaOperacion = fechaOperacion;
/*     */   }
/*     */   
/*     */   public String getCuentaAlianza() {
/*  73 */     return this.cuentaAlianza;
/*     */   }
/*     */   
/*     */   public void setCuentaAlianza(String cuentaAlianza) {
/*  77 */     this.cuentaAlianza = cuentaAlianza;
/*     */   }
/*     */   
/*     */   public String getUsuario() {
/*  81 */     return this.usuario;
/*     */   }
/*     */   
/*     */   public void setUsuario(String usuario) {
/*  85 */     this.usuario = usuario;
/*     */   }
/*     */   
/*     */   public String getComentario() {
/*  89 */     return this.comentario;
/*     */   }
/*     */   
/*     */   public void setComentario(String comentario) {
/*  93 */     this.comentario = comentario;
/*     */   }
/*     */   
/*     */   public int getPuntosCanjeados() {
/*  97 */     return this.puntosCanjeados;
/*     */   }
/*     */   
/*     */   public void setPuntosCanjeados(int puntosCanjeados) {
/* 101 */     this.puntosCanjeados = puntosCanjeados;
/*     */   }
/*     */   
/*     */   public int getMillas() {
/* 105 */     return this.millas;
/*     */   }
/*     */   
/*     */   public void setMillas(int millas) {
/* 109 */     this.millas = millas;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/AlianzaServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */