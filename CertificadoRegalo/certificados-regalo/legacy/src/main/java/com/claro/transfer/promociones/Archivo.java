/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class Archivo
/*    */ {
/*    */   private String nombre;
/*    */   private String ruta;
/*    */   
/*    */   public File obtieneArchivo(String nombreArchivo, String ruta)
/*    */   {
/*    */     try
/*    */     {
/* 14 */       File file = new File(ruta + nombreArchivo);
/*    */       
/* 16 */       if (file.exists()) {
/* 17 */         return file;
/*    */       }
/* 19 */       System.out.println("El archivo no existe");
/*    */     }
/*    */     catch (Exception e) {
/* 22 */       e.printStackTrace();
/* 23 */       System.out.println(" Error al obtener el archivo.");
/*    */     }
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public String getNombre() {
/* 29 */     return this.nombre;
/*    */   }
/*    */   
/*    */   public void setNombre(String nombre) {
/* 33 */     this.nombre = nombre;
/*    */   }
/*    */   
/*    */   public String getRuta() {
/* 37 */     return this.ruta;
/*    */   }
/*    */   
/*    */   public void setRuta(String ruta) {
/* 41 */     this.ruta = ruta;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/Archivo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */