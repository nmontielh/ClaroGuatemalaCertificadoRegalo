/*     */ package com.claro.transfer;


/*     */ 
/*     */ 
/*     */ public class DireccionTO
/*     */   extends MensajeTO
/*     */ {
/*     */   private static final long serialVersionUID = 2286769544666355038L;
/*     */   private long fechaOperacion;
/*     */   private String cuenta;
/*     */   private int secuencia;
/*     */   private String linea;
/*     */   private int tipoEntrega;
/*     */   private String nombreTitular;
/*     */   private String nombreContacto;
/*     */   private String direccion;
/*     */   private String colonia;
/*     */   private String callles;
/*     */   private String estado;
/*     */   private int codigoPostal;
/*     */   private String usuario;
/*     */   private String idReferencia;
/*     */   
/*     */   public long getFechaOperacion()
/*     */   {
/*  25 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*  28 */   public void setFechaOperacion(long fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public String getCuenta() {
/*  31 */     return this.cuenta;
/*     */   }
/*     */   
/*  34 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public int getSecuencia() {
/*  37 */     return this.secuencia;
/*     */   }
/*     */   
/*  40 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getLinea() {
/*  43 */     return this.linea;
/*     */   }
/*     */   
/*  46 */   public void setLinea(String linea) { this.linea = linea; }
/*     */   
/*     */   public int getTipoEntrega() {
/*  49 */     return this.tipoEntrega;
/*     */   }
/*     */   
/*  52 */   public void setTipoEntrega(int tipoEntrega) { this.tipoEntrega = tipoEntrega; }
/*     */   
/*     */   public String getNombreTitular() {
/*  55 */     return this.nombreTitular;
/*     */   }
/*     */   
/*  58 */   public void setNombreTitular(String nombreTitular) { this.nombreTitular = nombreTitular; }
/*     */   
/*     */   public String getNombreContacto() {
/*  61 */     return this.nombreContacto;
/*     */   }
/*     */   
/*  64 */   public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }
/*     */   
/*     */   public String getDireccion() {
/*  67 */     return this.direccion;
/*     */   }
/*     */   
/*  70 */   public void setDireccion(String direccion) { this.direccion = direccion; }
/*     */   
/*     */   public String getColonia() {
/*  73 */     return this.colonia;
/*     */   }
/*     */   
/*  76 */   public void setColonia(String colonia) { this.colonia = colonia; }
/*     */   
/*     */   public String getCallles() {
/*  79 */     return this.callles;
/*     */   }
/*     */   
/*  82 */   public void setCallles(String callles) { this.callles = callles; }
/*     */   
/*     */   public String getEstado() {
/*  85 */     return this.estado;
/*     */   }
/*     */   
/*  88 */   public void setEstado(String estado) { this.estado = estado; }
/*     */   
/*     */   public int getCodigoPostal() {
/*  91 */     return this.codigoPostal;
/*     */   }
/*     */   
/*  94 */   public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }
/*     */   
/*     */   public String getUsuario() {
/*  97 */     return this.usuario;
/*     */   }
/*     */   
/* 100 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*     */   
/*     */   public String getIdReferencia() {
/* 103 */     return this.idReferencia;
/*     */   }
/*     */   
/* 106 */   public void setIdReferencia(String idReferencia) { this.idReferencia = idReferencia; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/DireccionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */