/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeneficioTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7447228798768354384L;
/*    */   private String idBeneficio;
/*    */   private String idGpoBeneficio;
/*    */   private String descBeneficio;
/*    */   private String idMotivo;
/*    */   private String folio;
/*    */   
/*    */   public String getIdBeneficio()
/*    */   {
/* 19 */     return this.idBeneficio;
/*    */   }
/*    */   
/* 22 */   public void setIdBeneficio(String idBeneficio) { this.idBeneficio = idBeneficio; }
/*    */   
/*    */   public String getDescBeneficio() {
/* 25 */     return this.descBeneficio;
/*    */   }
/*    */   
/* 28 */   public void setDescBeneficio(String descBeneficio) { this.descBeneficio = descBeneficio; }
/*    */   
/*    */   public String getIdMotivo() {
/* 31 */     return this.idMotivo;
/*    */   }
/*    */   
/* 34 */   public void setIdMotivo(String idMotivo) { this.idMotivo = idMotivo; }
/*    */   
/*    */   public String getFolio() {
/* 37 */     return this.folio;
/*    */   }
/*    */   
/* 40 */   public void setFolio(String folio) { this.folio = folio; }
/*    */   
/*    */   public String getIdGpoBeneficio() {
/* 43 */     return this.idGpoBeneficio;
/*    */   }
/*    */   
/* 46 */   public void setIdGpoBeneficio(String idGpoBeneficio) { this.idGpoBeneficio = idGpoBeneficio; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/BeneficioTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */