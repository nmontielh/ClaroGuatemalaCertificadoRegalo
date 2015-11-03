/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class AlianzasTipoTO
/*    */   extends MensajeTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ArrayList<AlianzasTO> alianzasArray;
/*    */   private ArrayList<ProductosTO> productosArray;
/*    */   private AlianzasTO alianzas;
/*    */   
/*    */   public ArrayList<AlianzasTO> getAlianzasArray()
/*    */   {
/* 18 */     return this.alianzasArray;
/*    */   }
/*    */   
/* 21 */   public void setAlianzasArray(ArrayList<AlianzasTO> alianzasArray) { this.alianzasArray = alianzasArray; }
/*    */   
/*    */   public ArrayList<ProductosTO> getProductosArray() {
/* 24 */     return this.productosArray;
/*    */   }
/*    */   
/* 27 */   public void setProductosArray(ArrayList<ProductosTO> productosArray) { this.productosArray = productosArray; }
/*    */   
/*    */   public AlianzasTO getAlianzas() {
/* 30 */     return this.alianzas;
/*    */   }
/*    */   
/* 33 */   public void setAlianzas(AlianzasTO alianzas) { this.alianzas = alianzas; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/AlianzasTipoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */