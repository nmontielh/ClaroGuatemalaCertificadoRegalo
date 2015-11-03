/*     */ package com.claro.transfer.gap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValoracionGapTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String codigoMensaje;
/*     */   private String numeroTelefonico;
/*     */   private String idUsuario;
/*     */   private String region;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String valoracion;
/*     */   private String nivelValorCliente;
/*     */   private String contadorPromociones;
/*     */   private String nivelChurnCliente;
/*     */   private String segmentacionCliente;
/*     */   private String codigoErrorMensaje;
/*     */   private String descripcionErrorMensaje;
/*     */   private ArrayList<InfoPromocionGapTO> promocionesList;
/*     */   
/*     */   public String getCodigoMensaje()
/*     */   {
/*  33 */     return this.codigoMensaje;
/*     */   }
/*     */   
/*     */   public void setCodigoMensaje(String codigoMensaje)
/*     */   {
/*  38 */     this.codigoMensaje = codigoMensaje;
/*     */   }
/*     */   
/*     */   public String getNumeroTelefonico()
/*     */   {
/*  43 */     return this.numeroTelefonico;
/*     */   }
/*     */   
/*     */   public void setNumeroTelefonico(String numeroTelefonico)
/*     */   {
/*  48 */     this.numeroTelefonico = numeroTelefonico;
/*     */   }
/*     */   
/*     */   public String getIdUsuario()
/*     */   {
/*  53 */     return this.idUsuario;
/*     */   }
/*     */   
/*     */   public void setIdUsuario(String idUsuario)
/*     */   {
/*  58 */     this.idUsuario = idUsuario;
/*     */   }
/*     */   
/*     */   public String getRegion()
/*     */   {
/*  63 */     return this.region;
/*     */   }
/*     */   
/*     */   public void setRegion(String region)
/*     */   {
/*  68 */     this.region = region;
/*     */   }
/*     */   
/*     */   public String getMarca()
/*     */   {
/*  73 */     return this.marca;
/*     */   }
/*     */   
/*     */   public void setMarca(String marca)
/*     */   {
/*  78 */     this.marca = marca;
/*     */   }
/*     */   
/*     */   public String getModelo()
/*     */   {
/*  83 */     return this.modelo;
/*     */   }
/*     */   
/*     */   public void setModelo(String modelo)
/*     */   {
/*  88 */     this.modelo = modelo;
/*     */   }
/*     */   
/*     */   public String getValoracion()
/*     */   {
/*  93 */     return this.valoracion;
/*     */   }
/*     */   
/*     */   public void setValoracion(String valoracion)
/*     */   {
/*  98 */     this.valoracion = valoracion;
/*     */   }
/*     */   
/*     */   public String getNivelValorCliente()
/*     */   {
/* 103 */     return this.nivelValorCliente;
/*     */   }
/*     */   
/*     */   public void setNivelValorCliente(String nivelValorCliente)
/*     */   {
/* 108 */     this.nivelValorCliente = nivelValorCliente;
/*     */   }
/*     */   
/*     */   public String getContadorPromociones()
/*     */   {
/* 113 */     return this.contadorPromociones;
/*     */   }
/*     */   
/*     */   public void setContadorPromociones(String contadorPromociones)
/*     */   {
/* 118 */     this.contadorPromociones = contadorPromociones;
/*     */   }
/*     */   
/*     */   public String getNivelChurnCliente()
/*     */   {
/* 123 */     return this.nivelChurnCliente;
/*     */   }
/*     */   
/*     */   public void setNivelChurnCliente(String nivelChurnCliente)
/*     */   {
/* 128 */     this.nivelChurnCliente = nivelChurnCliente;
/*     */   }
/*     */   
/*     */   public String getSegmentacionCliente()
/*     */   {
/* 133 */     return this.segmentacionCliente;
/*     */   }
/*     */   
/*     */   public void setSegmentacionCliente(String segmentacionCliente)
/*     */   {
/* 138 */     this.segmentacionCliente = segmentacionCliente;
/*     */   }
/*     */   
/*     */   public String getCodigoErrorMensaje()
/*     */   {
/* 143 */     return this.codigoErrorMensaje;
/*     */   }
/*     */   
/*     */   public void setCodigoErrorMensaje(String codigoErrorMensaje)
/*     */   {
/* 148 */     this.codigoErrorMensaje = codigoErrorMensaje;
/*     */   }
/*     */   
/*     */   public String getDescripcionErrorMensaje()
/*     */   {
/* 153 */     return this.descripcionErrorMensaje;
/*     */   }
/*     */   
/*     */   public void setDescripcionErrorMensaje(String descripcionErrorMensaje)
/*     */   {
/* 158 */     this.descripcionErrorMensaje = descripcionErrorMensaje;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 163 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 175 */       "\nCodigoMensaje: " + this.codigoMensaje + "\numeroTelefonico: " + this.numeroTelefonico + "\nidUsuario: " + this.idUsuario + "\nregion: " + this.region + "\nmarca: " + this.marca + "\nmodelo: " + this.modelo + "\nvaloracion: " + this.valoracion + "\nnivelValorCliente: " + this.nivelValorCliente + "\ncontadorPromociones: " + this.contadorPromociones + "\nnivelChurnCliente: " + this.nivelChurnCliente + "\nsegmentacionCliente: " + this.segmentacionCliente + "\ncodigoErrorMensaje: " + this.codigoErrorMensaje + "\ndescripcionErrorMensaje: " + this.descripcionErrorMensaje;
/*     */   }
/*     */   
/*     */   public ArrayList<InfoPromocionGapTO> getPromocionesList()
/*     */   {
/* 180 */     return this.promocionesList;
/*     */   }
/*     */   
/*     */   public void setPromocionesList(ArrayList<InfoPromocionGapTO> promocionesList)
/*     */   {
/* 185 */     this.promocionesList = promocionesList;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/gap/ValoracionGapTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */