/*    */ package com.claro.services.acr;
/*    */ 
/*    */ import com.claro.exception.CAException;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import org.jdom.Document;
/*    */ import org.jdom.Element;
/*    */ import org.jdom.input.SAXBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParserXML
/*    */ {
/*    */   public String construyeMensaje(String telefono, int region, String marca, String modelo, String plan)
/*    */     throws CAException
/*    */   {
/* 19 */     StringBuffer buffer = new StringBuffer();
/*    */     try {
/* 21 */       buffer.append("<ValidaPromocion>");
/* 22 */       buffer.append("<Telefono>").append(telefono.trim()).append("</Telefono>");
/* 23 */       buffer.append("<Region>").append("R0").append(region).append("</Region>");
/* 24 */       buffer.append("<Marca>").append(marca).append("</Marca>");
/* 25 */       buffer.append("<Modelo>").append(modelo).append("</Modelo>");
/* 26 */       buffer.append("<Plan>").append(plan).append("</Plan>");
/* 27 */       buffer.append("</ValidaPromocion>");
/*    */     }
/*    */     catch (Exception e) {
/* 30 */       throw new CAException(-1, "ParserXML.construyeMensaje [Al crear mensaje XML]");
/*    */     }
/* 32 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public String construyeMensajePeticionLinea(String telefono) throws CAException
/*    */   {
/* 37 */     StringBuffer buffer = new StringBuffer();
/*    */     try {
/* 39 */       buffer.append("<PETICION>");
/* 40 */       buffer.append("<TELEFONO>").append(telefono.trim()).append("</TELEFONO>");
/* 41 */       buffer.append("</PETICION>");
/*    */     } catch (Exception e) {
/* 43 */       throw new CAException(-1, "ParserXML.construyeMensajePeticionLinea [Al crear mensaje XML]");
/*    */     }
/* 45 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public String construyeMensajePeticionCuenta(String cuenta) throws CAException
/*    */   {
/* 50 */     StringBuffer buffer = new StringBuffer();
/*    */     try {
/* 52 */       buffer.append("<PETICION>");
/* 53 */       buffer.append("<CUENTA>").append(cuenta.trim()).append("</CUENTA>");
/* 54 */       buffer.append("</PETICION>");
/*    */     } catch (Exception e) {
/* 56 */       throw new CAException(-1, "ParserXML.construyeMensajePeticionCuenta [Al crear mensaje XML]");
/*    */     }
/* 58 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public int parseaRespuesta(String respuestaXML)
/*    */     throws CAException
/*    */   {
/* 64 */     SAXBuilder sax = new SAXBuilder();
/* 65 */     Document doc = null;
/* 66 */     Element region = null;
/*    */     try
/*    */     {
/* 69 */       doc = sax.build(new ByteArrayInputStream(respuestaXML.getBytes()));
/* 70 */       Element root = doc.getRootElement();
/*    */       
/* 72 */       region = root.getChild("REGION");
/* 73 */       return Integer.parseInt(region.getValue().substring(1));
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 77 */       throw new CAException(-1, e.toString());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/services/acr/ParserXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */