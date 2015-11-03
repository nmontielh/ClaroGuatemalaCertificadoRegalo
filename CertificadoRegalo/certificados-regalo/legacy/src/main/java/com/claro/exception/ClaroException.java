/*    */ package com.claro.exception;
/*    */ 
/*    */ 
/*    */ public class ClaroException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 2856040899762181825L;
/*    */   
/*    */   private String errorId;
/*    */   private String errorMessage;
/*    */   
/*    */   public ClaroException(String errorId, String s)
/*    */   {
/* 14 */     this.errorId = errorId;
/* 15 */     this.errorMessage = s;
/*    */   }
/*    */   
/*    */   public ClaroException(String errorId, String s, Exception e) {
/* 19 */     this.errorId = errorId;
/* 20 */     this.errorMessage = s;
/* 21 */     setStackTrace(e.getStackTrace());
/*    */   }
/*    */   
/*    */   public String getErrorId() {
/* 25 */     return this.errorId;
/*    */   }
/*    */   
/*    */   public String getErrorMessage() {
/* 29 */     return this.errorMessage;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 34 */     return "( " + this.errorId + "-" + this.errorMessage + " )";
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/exception/ClaroException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */