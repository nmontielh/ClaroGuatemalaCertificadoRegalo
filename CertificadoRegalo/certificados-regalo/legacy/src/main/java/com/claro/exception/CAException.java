/*    */ package com.claro.exception;
/*    */ 
/*    */ 
/*    */ public class CAException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -8171021823861709766L;
/*    */   
/*    */   private int errorId;
/*    */   
/*    */   private String errorMessage;
/*    */   
/*    */   public CAException(int i, String s)
/*    */   {
/* 15 */     this.errorId = i;
/* 16 */     this.errorMessage = s;
/*    */   }
/*    */   
/*    */   public CAException(int i, String s, Exception e)
/*    */   {
/* 21 */     this.errorId = i;
/* 22 */     this.errorMessage = s;
/* 23 */     setStackTrace(e.getStackTrace());
/*    */   }
/*    */   
/*    */   public int getErrorId()
/*    */   {
/* 28 */     return this.errorId;
/*    */   }
/*    */   
/*    */   public String getErrorMessage()
/*    */   {
/* 33 */     return this.errorMessage;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 38 */     return "( " + this.errorId + "-" + this.errorMessage + " )";
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/exception/CAException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */