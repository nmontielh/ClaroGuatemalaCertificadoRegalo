/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UsuarioTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String numEmpleado;
/*     */   private int region;
/*     */   private String idUsuario;
/*     */   private String nombre;
/*     */   private String password;
/*     */   private String status;
/*     */   private Integer contadorPMP;
/*     */   private String idUsuarioCaptura;
/*     */   private Timestamp fechaUpdate;
/*     */   private String sistemaAdmin;
/*     */   private Timestamp fechaAdmin;
/*     */   private PuntoVentaTO puntoVentaTO;
/*     */   private PerfilTO perfilTO;
/*     */   private String perfil;
/*     */   private String passwordNuevo;
/*     */   private String confirmacionPassword;
/*     */   private String accion;
/*     */   
/*     */   public PerfilTO getPerfilTO()
/*     */   {
/*  33 */     if (this.perfilTO == null) this.perfilTO = new PerfilTO();
/*  34 */     return this.perfilTO;
/*     */   }
/*     */   
/*  37 */   public void setPerfilTO(PerfilTO perfilTO) { this.perfilTO = perfilTO; }
/*     */   
/*     */   public String getPerfil() {
/*  40 */     return this.perfil;
/*     */   }
/*     */   
/*  43 */   public void setPerfil(String Perfil) { this.perfil = Perfil; }
/*     */   
/*     */   public int getRegion() {
/*  46 */     return this.region;
/*     */   }
/*     */   
/*  49 */   public void setRegion(int region) { this.region = region; }
/*     */   
/*     */   public PuntoVentaTO getPuntoVentaTO() {
/*  52 */     return this.puntoVentaTO;
/*     */   }
/*     */   
/*  55 */   public void setPuntoVentaTO(PuntoVentaTO puntoVentaTO) { this.puntoVentaTO = puntoVentaTO; }
/*     */   
/*     */   public String getNumEmpleado() {
/*  58 */     return this.numEmpleado;
/*     */   }
/*     */   
/*  61 */   public void setNumEmpleado(String numEmpleado) { this.numEmpleado = numEmpleado; }
/*     */   
/*     */   public String getAccion() {
/*  64 */     return this.accion;
/*     */   }
/*     */   
/*  67 */   public void setAccion(String Accion) { this.accion = Accion; }
/*     */   
/*     */   public String getIdUsuario() {
/*  70 */     return this.idUsuario;
/*     */   }
/*     */   
/*  73 */   public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
/*     */   
/*     */   public String getNombre() {
/*  76 */     return this.nombre;
/*     */   }
/*     */   
/*  79 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*     */   
/*     */   public String getPassword() {
/*  82 */     return this.password;
/*     */   }
/*     */   
/*  85 */   public void setPassword(String password) { this.password = password; }
/*     */   
/*     */   public String getStatus() {
/*  88 */     return this.status;
/*     */   }
/*     */   
/*  91 */   public void setStatus(String status) { this.status = status; }
/*     */   
/*     */   public Integer getContadorPMP() {
/*  94 */     return this.contadorPMP;
/*     */   }
/*     */   
/*  97 */   public void setContadorPMP(Integer contadorPMP) { this.contadorPMP = contadorPMP; }
/*     */   
/*     */   public String getIdUsuarioCaptura() {
/* 100 */     return this.idUsuarioCaptura;
/*     */   }
/*     */   
/* 103 */   public void setIdUsuarioCaptura(String idUsuarioCaptura) { this.idUsuarioCaptura = idUsuarioCaptura; }
/*     */   
/*     */   public Timestamp getFechaUpdate() {
/* 106 */     return this.fechaUpdate;
/*     */   }
/*     */   
/* 109 */   public void setFechaUpdate(Timestamp fechaUpdate) { this.fechaUpdate = fechaUpdate; }
/*     */   
/*     */   public String getSistemaAdmin() {
/* 112 */     return this.sistemaAdmin;
/*     */   }
/*     */   
/* 115 */   public void setSistemaAdmin(String sistemaAdmin) { this.sistemaAdmin = sistemaAdmin; }
/*     */   
/*     */   public Timestamp getFechaAdmin() {
/* 118 */     return this.fechaAdmin;
/*     */   }
/*     */   
/* 121 */   public void setFechaAdmin(Timestamp fechaAdmin) { this.fechaAdmin = fechaAdmin; }
/*     */   
/*     */   public String getPasswordNuevo() {
/* 124 */     return this.passwordNuevo;
/*     */   }
/*     */   
/* 127 */   public void setPasswordNuevo(String passwordNuevo) { this.passwordNuevo = passwordNuevo; }
/*     */   
/*     */   public String getConfirmacionPassword() {
/* 130 */     return this.confirmacionPassword;
/*     */   }
/*     */   
/* 133 */   public void setConfirmacionPassword(String confirmacionPassword) { this.confirmacionPassword = confirmacionPassword; }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 138 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 144 */       "[UsuariosTO] numEmpleado:" + this.numEmpleado + " idUsuario:" + this.idUsuario + " nombre:" + this.nombre + " password:" + this.password + " status:" + this.status + "contadorPMP:" + this.contadorPMP + " idUsuarioCaptura:" + this.idUsuarioCaptura + " fechaUpdate:" + this.fechaUpdate + " sistemaAdmin:" + this.sistemaAdmin + " fechaAdmin:" + this.fechaAdmin;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/UsuarioTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */