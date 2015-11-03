/*    */ package com.claro.transfer;
/*    */ 
/*    */ public class PrivilegioTO
/*    */ {
/*    */   private int idProceso;
/*    */   private String nombre;
/*    */   private String descripcion;
/*    */   private String tipo;
/*    */   private String estatus;
/*    */   
/*    */   public int getIdProceso() {
/* 12 */     return this.idProceso;
/*    */   }
/*    */   
/* 15 */   public void setIdProceso(int idProceso) { this.idProceso = idProceso; }
/*    */   
/*    */   public String getTipo() {
/* 18 */     return this.tipo;
/*    */   }
/*    */   
/* 21 */   public void setTipo(String tipo) { this.tipo = tipo; }
/*    */   
/*    */   public String getEstatus() {
/* 24 */     return this.estatus;
/*    */   }
/*    */   
/* 27 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public String getDescripcion()
/*    */   {
/* 31 */     return this.descripcion;
/*    */   }
/*    */   
/* 34 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */   public String getNombre()
/*    */   {
/* 38 */     return this.nombre;
/*    */   }
/*    */   
/* 41 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*    */   
/*    */   public String toString()
/*    */   {
/* 45 */     StringBuffer buffer = new StringBuffer();
/* 46 */     buffer.append("idProceso:").append(this.idProceso).append(", ");
/* 47 */     buffer.append("nombre:").append(this.nombre).append(", ");
/* 48 */     buffer.append("tipo:").append(this.tipo).append(", ");
/* 49 */     buffer.append("estatus:").append(this.estatus);
/* 50 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PrivilegioTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */