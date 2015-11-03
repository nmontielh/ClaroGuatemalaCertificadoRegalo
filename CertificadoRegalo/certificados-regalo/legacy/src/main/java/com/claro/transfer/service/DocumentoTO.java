/*    */ package com.claro.transfer.service;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ 
/*    */ public class DocumentoTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String descripcion;
/*    */   private int idDocumento;
/*    */   private String idUsuario;
/*    */   private String ruta;
/*    */   private String nombre;
/*    */   private Timestamp fechaCreacion;
/*    */   private String estatus;
/*    */   
/* 21 */   public String getDescripcion() { return this.descripcion; }
/*    */   
/* 23 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */ 
/* 26 */   public String getEstatus() { return this.estatus; }
/*    */   
/* 28 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */ 
/* 31 */   public int getIdDocumento() { return this.idDocumento; }
/*    */   
/* 33 */   public void setIdDocumento(int IdDocumento) { this.idDocumento = IdDocumento; }
/*    */   
/*    */ 
/* 36 */   public String getIdUsuario() { return this.idUsuario; }
/*    */   
/* 38 */   public void setIdUsuario(String IdUsuario) { this.idUsuario = IdUsuario; }
/*    */   
/*    */ 
/* 41 */   public String getRuta() { return this.ruta; }
/*    */   
/* 43 */   public void setRuta(String Ruta) { this.ruta = Ruta; }
/*    */   
/*    */ 
/* 46 */   public String getNombre() { return this.nombre; }
/*    */   
/* 48 */   public void setNombre(String Nombre) { this.nombre = Nombre; }
/*    */   
/*    */ 
/* 51 */   public Timestamp getFechaCreacion() { return this.fechaCreacion; }
/*    */   
/* 53 */   public void setFechaCreacion(Timestamp FechaCreacion) { this.fechaCreacion = FechaCreacion; }
/*    */   
/*    */   public void setDataDocumento(String idDocumento, String fileName, String sufijo, InputStream stream)
/*    */     throws IOException
/*    */   {
/* 58 */     this.nombre = idDocumento;
/*    */     
/* 60 */     if ((fileName.length() > 4) && (fileName.indexOf('.') > -1)) {
/* 61 */       this.nombre = (idDocumento + sufijo + fileName.substring(fileName.length() - 4));
/*    */     } else {
/* 63 */       this.nombre = (sufijo + idDocumento + obtenTipoArchivo(stream));
/*    */     }
/*    */   }
/*    */   
/*    */   private String obtenTipoArchivo(InputStream stream) throws IOException {
/* 68 */     String tipo = "";
/*    */     
/* 70 */     if (stream.markSupported())
/*    */     {
/* 72 */       byte[] buffer = new byte[10];
/* 73 */       String bufferString = null;
/*    */       
/* 75 */       stream.mark(11);
/* 76 */       stream.read(buffer);
/*    */       
/* 78 */       bufferString = new String(buffer);
/* 79 */       if (bufferString.startsWith("%PDF")) {
/* 80 */         tipo = ".pdf";
/* 81 */       } else if (bufferString.startsWith("GIF89")) {
/* 82 */         tipo = ".gif";
/* 83 */       } else if (bufferString.indexOf("JFIF") > -1) {
/* 84 */         tipo = ".jpg";
/* 85 */       } else if (bufferString.startsWith("PK")) {
/* 86 */         tipo = ".zip";
/*    */       }
/* 88 */       stream.reset();
/*    */     } else {
/* 90 */       tipo = ".pdf";
/*    */     }
/* 92 */     return tipo;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/DocumentoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */