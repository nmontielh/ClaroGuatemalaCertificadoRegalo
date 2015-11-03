/*    */ package com.claro.transfer.service;
/*    */ 
/*    */ import com.claro.transfer.PerfilTO;
/*    */ import com.claro.transfer.PuntoVentaTO;
/*    */ import com.claro.transfer.UsuarioTO;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UsuarioServiceTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7319890221700201435L;
/*    */   private String mensaje;
/*    */   private String claveUsuario;
/*    */   private String nombreUsuario;
/*    */   private String puntoVenta;
/*    */   private String perfil;
/*    */   private String porcentajeIVA;
/*    */   private int idMensaje;
/*    */   private int regionPerfil;
/*    */   
/*    */   public UsuarioServiceTO(UsuarioTO usuarioTO)
/*    */   {
/* 26 */     this.claveUsuario = usuarioTO.getIdUsuario();
/* 27 */     this.nombreUsuario = usuarioTO.getNombre();
/* 28 */     this.puntoVenta = usuarioTO.getPuntoVentaTO().getPtoVenta();
/* 29 */     this.perfil = String.valueOf(usuarioTO.getPerfilTO().getIdPerfilN());
/* 30 */     this.porcentajeIVA = usuarioTO.getPuntoVentaTO().getPorcentajeIva().substring(2, usuarioTO.getPuntoVentaTO().getPorcentajeIva().length());
/* 31 */     this.idMensaje = usuarioTO.getIdMensaje();
/* 32 */     this.mensaje = usuarioTO.getMensaje();
/* 33 */     this.regionPerfil = (usuarioTO.getPerfilTO() != null ? usuarioTO.getPerfilTO().getRegion() : 0);
/*    */   }
/*    */   
/*    */   public UsuarioServiceTO() {}
/*    */   
/*    */   public String getMensaje()
/*    */   {
/* 40 */     return this.mensaje;
/*    */   }
/*    */   
/* 43 */   public void setMensaje(String mensaje) { this.mensaje = mensaje; }
/*    */   
/*    */   public String getClaveUsuario() {
/* 46 */     return this.claveUsuario;
/*    */   }
/*    */   
/* 49 */   public void setClaveUsuario(String claveUsuario) { this.claveUsuario = claveUsuario; }
/*    */   
/*    */   public String getNombreUsuario() {
/* 52 */     return this.nombreUsuario;
/*    */   }
/*    */   
/* 55 */   public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
/*    */   
/*    */   public String getPuntoVenta() {
/* 58 */     return this.puntoVenta;
/*    */   }
/*    */   
/* 61 */   public void setPuntoVenta(String puntoVenta) { this.puntoVenta = puntoVenta; }
/*    */   
/*    */   public String getPerfil() {
/* 64 */     return this.perfil;
/*    */   }
/*    */   
/* 67 */   public void setPerfil(String perfil) { this.perfil = perfil; }
/*    */   
/*    */   public String getPorcentajeIVA() {
/* 70 */     return this.porcentajeIVA;
/*    */   }
/*    */   
/* 73 */   public void setPorcentajeIVA(String porcentajeIVA) { this.porcentajeIVA = porcentajeIVA; }
/*    */   
/*    */   public int getIdMensaje() {
/* 76 */     return this.idMensaje;
/*    */   }
/*    */   
/* 79 */   public void setIdMensaje(int idMensaje) { this.idMensaje = idMensaje; }
/*    */   
/*    */   public int getRegionPerfil()
/*    */   {
/* 83 */     return this.regionPerfil;
/*    */   }
/*    */   
/*    */   public void setRegionPerfil(int regionPerfil) {
/* 87 */     this.regionPerfil = regionPerfil;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/UsuarioServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */