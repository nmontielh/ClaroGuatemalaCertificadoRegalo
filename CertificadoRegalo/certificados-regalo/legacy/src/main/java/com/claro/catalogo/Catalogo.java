/*    */ package com.claro.catalogo;
/*    */ 
/*    */ import com.claro.ejb.CirculoAzulLocal;
/*    */ import com.claro.ejb.CirculoAzulLocalHome;
/*    */ import com.claro.transfer.CatalogoTO;
/*    */ import com.claro.util.ServiceLocator;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Catalogo
/*    */ {
/*    */   private ArrayList<CatalogoTO> catalogo;
/*    */   private String tabla;
/*    */   private HashMap<String, String> mapa;
/*    */   private ArrayList<String> keys;
/*    */   
/*    */   public void cargaCatalogo()
/*    */     throws Exception
/*    */   {
/* 22 */     CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome)ServiceLocator.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
/* 23 */     CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
/*    */     
/* 25 */     if (this.tabla.equals("propiedades")) {
/* 26 */       this.catalogo = circuloAzulLocal.obtienePropiedades();
/*    */ 
/*    */ 
/*    */ 
/*    */     }
/* 31 */     else if (this.tabla.equals("grupoPromocion")) {
/* 32 */       this.catalogo = circuloAzulLocal.obtieneGrupoPromocion();
/*    */     }
/* 34 */     iniciaMapa();
/*    */   }
/*    */   
/*    */   private void iniciaMapa() {
/* 38 */     if (this.catalogo != null) {
/* 39 */       this.mapa = new HashMap();
/* 40 */       this.keys = new ArrayList();
/* 41 */       for (CatalogoTO catalogoTO : this.catalogo) {
/* 42 */         this.mapa.put(catalogoTO.getIdVariable(), catalogoTO.getValor());
/* 43 */         this.keys.add(catalogoTO.getIdVariable());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ArrayList<CatalogoTO> getCatalogo() {
/* 49 */     return this.catalogo;
/*    */   }
/*    */   
/*    */   public void setCatalogo(ArrayList<CatalogoTO> catalogo) {
/* 53 */     this.catalogo = catalogo;
/*    */   }
/*    */   
/*    */   public String getTabla() {
/* 57 */     return this.tabla;
/*    */   }
/*    */   
/*    */   public void setTabla(String tabla) {
/* 61 */     this.tabla = tabla;
/*    */   }
/*    */   
/*    */   public String getPropiedad(String id) {
/* 65 */     return (String)this.mapa.get(id);
/*    */   }
/*    */   
/*    */   public int getPropiedadInt(String id) {
/* 69 */     if (this.mapa.get(id) != null) {
/* 70 */       return Integer.parseInt((String)this.mapa.get(id));
/*    */     }
/* 72 */     return 0;
/*    */   }
/*    */   
/* 75 */   public boolean contieneValorEnPropiedad(String propiedad, String valor) { return this.keys.contains(propiedad); }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/catalogo/Catalogo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */