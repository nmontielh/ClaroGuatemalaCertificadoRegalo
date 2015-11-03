/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PuntoVentaTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5408897824792290650L;
/*    */   private String ptoVenta;
/*    */   private String accion;
/*    */   private String porcentajeIva;
/*    */   private String idPuntoVta;
/*    */   private String segmentoIP;
/*    */   private int rangoInf;
/*    */   private int rangoSup;
/*    */   private int idRegion;
/*    */   private int ivaProcentaje;
/*    */   private String tipoPuntovta;
/*    */   
/*    */   public String getIdPuntoVta()
/*    */   {
/* 24 */     if (this.idPuntoVta != null) {
/* 25 */       this.idPuntoVta = this.idPuntoVta.replace("\"", "&quot;");
/*    */     }
/* 27 */     return this.idPuntoVta;
/*    */   }
/*    */   
/* 30 */   public void setIdPuntoVta(String idPuntoVta) { this.idPuntoVta = idPuntoVta.toUpperCase(); }
/*    */   
/* 32 */   public String getAccion() { return this.accion; }
/*    */   
/* 34 */   public void setAccion(String accion) { this.accion = accion; }
/*    */   
/* 36 */   public String getSegmentoIP() { return this.segmentoIP; }
/*    */   
/* 38 */   public void setSegmentoIP(String segmentoIP) { this.segmentoIP = segmentoIP; }
/*    */   
/* 40 */   public int getRangoInf() { return this.rangoInf; }
/*    */   
/* 42 */   public void setRangoInf(int rangoInf) { this.rangoInf = rangoInf; }
/*    */   
/* 44 */   public int getRangoSup() { return this.rangoSup; }
/*    */   
/* 46 */   public void setRangoSup(int rangoSup) { this.rangoSup = rangoSup; }
/*    */   
/* 48 */   public int getIdRegion() { return this.idRegion; }
/*    */   
/* 50 */   public void setIdRegion(int idRegion) { this.idRegion = idRegion; }
/*    */   
/*    */   public int getIvaProcentaje() {
/* 53 */     return this.ivaProcentaje;
/*    */   }
/*    */   
/* 56 */   public void setIvaProcentaje(int ivaProcentaje) { this.ivaProcentaje = ivaProcentaje; }
/*    */   
/*    */   public String getPtoVenta()
/*    */   {
/* 60 */     return this.ptoVenta;
/*    */   }
/*    */   
/* 63 */   public void setPtoVenta(String ptoVenta) { this.ptoVenta = ptoVenta; }
/*    */   
/*    */   public String getPorcentajeIva() {
/* 66 */     return "1." + this.porcentajeIva;
/*    */   }
/*    */   
/* 69 */   public void setPorcentajeIva(String porcentajeIva) { this.porcentajeIva = porcentajeIva; }
/*    */   
/*    */   public String getTipoPuntovta() {
/* 72 */     return this.tipoPuntovta;
/*    */   }
/*    */   
/* 75 */   public void setTipoPuntovta(String tipoPuntovta) { this.tipoPuntovta = tipoPuntovta; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PuntoVentaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */