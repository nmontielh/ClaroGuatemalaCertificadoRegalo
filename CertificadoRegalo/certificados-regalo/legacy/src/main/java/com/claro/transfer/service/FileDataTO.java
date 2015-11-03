/*    */ package com.claro.transfer.service;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileDataTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8568305888184280851L;
/*    */   private InputStream data;
/*    */   private int size;
/*    */   private String name;
/*    */   
/*    */   public FileDataTO() {}
/*    */   
/*    */   public FileDataTO(InputStream data, int size)
/*    */   {
/* 20 */     this.data = data;
/* 21 */     this.size = size;
/*    */   }
/*    */   
/* 24 */   public InputStream getData() { return this.data; }
/*    */   
/* 26 */   public void setData(InputStream data) { this.data = data; }
/*    */   
/*    */ 
/* 29 */   public int getSize() { return this.size; }
/*    */   
/* 31 */   public void setSize(int size) { this.size = size; }
/*    */   
/*    */   public String getName()
/*    */   {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */   
/*    */   public static long getSerialVersionUID() {
/* 43 */     return -8568305888184280851L;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/FileDataTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */