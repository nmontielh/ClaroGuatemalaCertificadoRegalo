/*    */ package com.claro.transfer;


/*    */ 
/*    */ 
/*    */ public class CiudadEdoTO
/*    */   extends MensajeTO
/*    */ {
/*    */   private static final long serialVersionUID = 5316110903571825412L;
/*    */   
/*    */   private String estado;
/*    */   private String ciudad;
/*    */   private int costo;
/*    */   private int opcion;
/*    */   
/*    */   public String getEstado()
/*    */   {
/* 16 */     return this.estado;
/*    */   }
/*    */   
/* 19 */   public void setEstado(String estado) { this.estado = estado; }
/*    */   
/*    */   public String getCiudad() {
/* 22 */     return this.ciudad;
/*    */   }
/*    */   
/* 25 */   public void setCiudad(String ciudad) { this.ciudad = ciudad; }
/*    */   
/*    */   public int getCosto() {
/* 28 */     return this.costo;
/*    */   }
/*    */   
/* 31 */   public void setCosto(int costo) { this.costo = costo; }
/*    */   
/*    */   public int getOpcion() {
/* 34 */     return this.opcion;
/*    */   }
/*    */   
/* 37 */   public void setOpcion(int opcion) { this.opcion = opcion; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/CiudadEdoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */