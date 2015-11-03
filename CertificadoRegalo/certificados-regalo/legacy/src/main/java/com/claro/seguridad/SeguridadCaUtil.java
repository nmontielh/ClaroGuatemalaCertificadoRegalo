/*    */ package com.claro.seguridad;
/*    */ 
/*    */ import com.claro.transfer.PerfilTO;
/*    */ import com.claro.transfer.PrivilegioTO;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SeguridadCaUtil
/*    */ {
/*    */   private static SeguridadCaUtil seguridadCaUtil;
/*    */   
/*    */   public static SeguridadCaUtil getInstance()
/*    */   {
/* 15 */     if (seguridadCaUtil == null) seguridadCaUtil = new SeguridadCaUtil();
/* 16 */     return seguridadCaUtil;
/*    */   }
/*    */   
/*    */   public boolean validaPerfilProcesoCa(PerfilTO perfilTO, String idProceso) {
/* 20 */     Hashtable<String, PrivilegioTO> privilegios = perfilTO.getPrivilegiosCa();
/*    */     
/* 22 */     return privilegios.containsKey(idProceso);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean validaPerfilProcesoEcac(PerfilTO perfilTO, String idProceso)
/*    */   {
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/seguridad/SeguridadCaUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */