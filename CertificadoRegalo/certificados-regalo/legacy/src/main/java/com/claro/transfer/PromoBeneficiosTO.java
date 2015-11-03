/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PromoBeneficiosTO
/*    */ {
/*    */   private String marca;
/*    */   private String modelo;
/*    */   private String idbeneficio;
/*    */   private String idGpoBeneficio;
/*    */   private ArrayList<BeneficioTO> beneficios;
/*    */   
/*    */   public ArrayList<BeneficioTO> getBeneficios() {
/* 14 */     return this.beneficios;
/*    */   }
/*    */   
/* 17 */   public void setBeneficios(ArrayList<BeneficioTO> beneficios) { this.beneficios = beneficios; }
/*    */   
/*    */   public String getMarca() {
/* 20 */     return this.marca;
/*    */   }
/*    */   
/* 23 */   public void setMarca(String marca) { this.marca = marca; }
/*    */   
/*    */   public String getModelo() {
/* 26 */     return this.modelo;
/*    */   }
/*    */   
/* 29 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*    */   
/*    */   public String getIdbeneficio() {
/* 32 */     return this.idbeneficio;
/*    */   }
/*    */   
/* 35 */   public void setIdbeneficio(String idbeneficio) { this.idbeneficio = idbeneficio; }
/*    */   
/*    */   public String getIdGpoBeneficio() {
/* 38 */     return this.idGpoBeneficio;
/*    */   }
/*    */   
/* 41 */   public void setIdGpoBeneficio(String idGpoBeneficio) { this.idGpoBeneficio = idGpoBeneficio; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PromoBeneficiosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */