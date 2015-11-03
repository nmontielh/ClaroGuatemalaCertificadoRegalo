/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerfilTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8828266038303257532L;
/*    */   private int idPerfilN;
/*    */   private String idPuesto;
/*    */   private String descripcion;
/*    */   private int region;
/*    */   private int nivelAutorizacion;
/*    */   private Hashtable<String, PrivilegioTO> privilegiosCa;
/*    */   private Hashtable<String, PrivilegioTO> privilegiosEcac;
/*    */   
/*    */   public Hashtable<String, PrivilegioTO> getPrivilegiosCa()
/*    */   {
/* 23 */     return this.privilegiosCa;
/*    */   }
/*    */   
/*    */   public void setPrivilegiosCa(Hashtable<String, PrivilegioTO> privilegiosCa) {
/* 27 */     this.privilegiosCa = privilegiosCa;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getIdPerfilN()
/*    */   {
/* 36 */     return this.idPerfilN;
/*    */   }
/*    */   
/*    */   public void setIdPerfilN(int idPerfilN) {
/* 40 */     this.idPerfilN = idPerfilN;
/*    */   }
/*    */   
/*    */   public String getIdPuesto() {
/* 44 */     return this.idPuesto;
/*    */   }
/*    */   
/*    */   public void setIdPuesto(String idPuesto) {
/* 48 */     this.idPuesto = idPuesto;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 52 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 56 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public int getRegion() {
/* 60 */     return this.region;
/*    */   }
/*    */   
/*    */   public void setRegion(int region) {
/* 64 */     this.region = region;
/*    */   }
/*    */   
/*    */   public int getNivelAutorizacion() {
/* 68 */     return this.nivelAutorizacion;
/*    */   }
/*    */   
/*    */   public void setNivelAutorizacion(int nivelAutorizacion) {
/* 72 */     this.nivelAutorizacion = nivelAutorizacion;
/*    */   }
/*    */   
/*    */   public Hashtable<String, PrivilegioTO> getPrivilegiosEcac() {
/* 76 */     return this.privilegiosEcac;
/*    */   }
/*    */   
/*    */   public void setPrivilegiosEcac(Hashtable<String, PrivilegioTO> privilegiosEcac) {
/* 80 */     this.privilegiosEcac = privilegiosEcac;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PerfilTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */