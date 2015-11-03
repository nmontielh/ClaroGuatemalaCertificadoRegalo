/*     */ package com.claro.services.gap;
/*     */ 
/*     */ import com.claro.exception.CAException;
/*     */ import com.claro.transfer.gap.InfoPromocionGapTO;
/*     */ import com.claro.transfer.gap.ValoracionGapTO;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.input.SAXBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RespuestaConsultasGap
/*     */ {
/*  26 */   private String xmlInput = "<SolicitudInfoValoracion><CodigoMensaje>WS_CODIGOMENSAJE</CodigoMensaje><NumeroTelefonico>WS_NUMEROTELEFONO</NumeroTelefonico><IdUsuario>WS_IDUSUARIO</IdUsuario></SolicitudInfoValoracion>";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  33 */   private String xmlPromoCA = "<PromAceptaCA><CodigoMensaje>WS_CODIGOMENSAJE</CodigoMensaje><NumeroTelefonico>WS_NUMEROTELEFONO</NumeroTelefonico><IdUsuario>WS_IDUSUARIO</IdUsuario><IdPromocion>WS_IDPROMOCIONGAP</IdPromocion><RegionLinea>WS_REGIONLINEA</RegionLinea><Ip>WS_IPCLIENTE</Ip><VersionPromocion>WS_VERSION</VersionPromocion><AplicaEp>WS_APLICAEP</AplicaEp></PromAceptaCA>";
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
/*  44 */   private ClientePromocionesGap clientePromocionesGap = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RespuestaConsultasGap(String codigoMensaje, String telefono, String idUsuario)
/*     */   {
/*  53 */     this.xmlInput = this.xmlInput.replaceAll("WS_CODIGOMENSAJE", codigoMensaje).replaceAll("WS_NUMEROTELEFONO", telefono).replaceAll("WS_IDUSUARIO", idUsuario);
/*  54 */     this.clientePromocionesGap = new ClientePromocionesGap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValoracionGapTO consultaValoracionLinea(String endpoint)
/*     */     throws CAException
/*     */   {
/*  66 */     ValoracionGapTO valoracionGapTO = null;
/*  67 */     String rspXMLInfoValoracion = "";
/*     */     try {
/*  69 */       rspXMLInfoValoracion = this.clientePromocionesGap.consultaValoracionLinea(endpoint, this.xmlInput);
/*  70 */       return obtieneInfoValoracion(rspXMLInfoValoracion);
/*     */     }
/*     */     catch (CAException exception) {
/*  73 */       throw new CAException(-1, "CA.consultaValoracionLinea[" + exception.toString() + "]");
/*     */     } catch (Exception exception) {
/*  75 */       throw new CAException(-1, "CA.consultaValoracionLinea[" + exception.toString() + "]");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValoracionGapTO obtienePromocionesGap(String endpoint, ValoracionGapTO valoracionGapTO)
/*     */     throws CAException
/*     */   {
/*  87 */     String rspXMLInfoValoracion = "";
/*     */     try {
/*  89 */       rspXMLInfoValoracion = this.clientePromocionesGap.consultaValoracionLinea(endpoint, this.xmlInput);
/*  90 */       return obtieneInfoPromocionesGap(rspXMLInfoValoracion, valoracionGapTO);
/*     */     } catch (CAException exception) {
/*  92 */       throw new CAException(-1, "CA.obtienePromocionesGap[" + exception.toString() + "]");
/*     */     } catch (Exception exception) {
/*  94 */       throw new CAException(-1, "CA.obtienePromocionesGap[" + exception.toString() + "]");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String enviaPromoCA(String endpoint, String xmlMsgCA)
/*     */     throws CAException
/*     */   {
/*     */     try
/*     */     {
/* 106 */       return this.clientePromocionesGap.aceptaPromocionCirculoAzul(endpoint, xmlMsgCA);
/*     */     } catch (CAException exception) {
/* 108 */       throw new CAException(-1, "CA.enviaPromoCA[" + exception.toString() + "]");
/*     */     } catch (Exception exception) {
/* 110 */       throw new CAException(-1, "CA.enviaPromoCA[" + exception.toString() + "]");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValoracionGapTO obtieneInfoValoracion(String xmlInput)
/*     */     throws CAException
/*     */   {
/* 121 */     ValoracionGapTO valoracionGapTO = new ValoracionGapTO();
/* 122 */     SAXBuilder builder = new SAXBuilder();
/* 123 */     Document document = null;
/* 124 */     Element root = null;
/*     */     try {
/* 126 */       document = builder.build(new ByteArrayInputStream(xmlInput.getBytes()));
/* 127 */       root = document.getRootElement();
/* 128 */       valoracionGapTO.setCodigoMensaje(root.getChildText("CodigoMensaje"));
/* 129 */       valoracionGapTO.setNumeroTelefonico(root.getChildText("NumeroTelefonico"));
/* 130 */       valoracionGapTO.setIdUsuario(root.getChildText("IdUsuario"));
/* 131 */       valoracionGapTO.setValoracion(root.getChildText("Valoracion"));
/* 132 */       valoracionGapTO.setNivelValorCliente(root.getChildText("NivelValorCliente"));
/* 133 */       valoracionGapTO.setContadorPromociones(root.getChildText("ContadorPromociones"));
/* 134 */       valoracionGapTO.setNivelChurnCliente(root.getChildText("NivelChurnCliente"));
/* 135 */       valoracionGapTO.setSegmentacionCliente(root.getChildText("SegmentacionCliente"));
/* 136 */       valoracionGapTO.setCodigoErrorMensaje(root.getChildText("CodigoErrorMensaje"));
/* 137 */       valoracionGapTO.setDescripcionErrorMensaje(root.getChildText("DescripcionErrorMensaje"));
/*     */     } catch (JDOMException e) {
/* 139 */       throw new CAException(-1, e.toString());
/*     */     } catch (IOException e) {
/* 141 */       throw new CAException(-1, e.toString());
/*     */     } catch (Exception e) {
/* 143 */       throw new CAException(-1, e.toString());
/*     */     }
/* 145 */     return valoracionGapTO;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValoracionGapTO obtieneInfoPromocionesGap(String xmlInput, ValoracionGapTO valoracionGapTO)
/*     */     throws CAException
/*     */   {
/* 156 */     ArrayList<InfoPromocionGapTO> listPromociones = null;
/* 157 */     List promociones = null;
/* 158 */     List promocion = null;
/* 159 */     SAXBuilder builder = new SAXBuilder();
/* 160 */     Document document = null;
/* 161 */     Element root = null;
/*     */     try {
/* 163 */       document = builder.build(new ByteArrayInputStream(xmlInput.getBytes()));
/* 164 */       root = document.getRootElement();
/* 165 */       promociones = root.getChildren("Promociones");
/* 166 */       valoracionGapTO.setCodigoMensaje(root.getChildText("CodigoMensaje"));
/* 167 */       valoracionGapTO.setNumeroTelefonico(root.getChildText("NumeroTelefonico"));
/* 168 */       valoracionGapTO.setIdUsuario(root.getChildText("IdUsuario"));
/* 169 */       valoracionGapTO.setContadorPromociones(root.getChildText("ContadorPromociones"));
/* 170 */       valoracionGapTO.setCodigoErrorMensaje(root.getChildText("CodigoErrorMensaje"));
/* 171 */       valoracionGapTO.setDescripcionErrorMensaje(root.getChildText("DescripcionErrorMensaje"));
/*     */       
/* 173 */       if (promociones != null) {
/* 174 */         ListIterator iterator = promociones.listIterator();
/* 175 */         if (iterator.hasNext()) {
/* 176 */           Element element = (Element)iterator.next();
/* 177 */           promocion = element.getChildren("Promocion");
/*     */         }
/*     */       }
/* 180 */       if (promocion != null) {
/* 181 */         listPromociones = new ArrayList();
/* 182 */         ListIterator iterator = promocion.listIterator();
/* 183 */         while (iterator.hasNext()) {
/* 184 */           InfoPromocionGapTO infoPromocionGapTO = new InfoPromocionGapTO();
/* 185 */           Element element = (Element)iterator.next();
/* 186 */           infoPromocionGapTO.setIdSecuencia(element.getChildText("IdSecuencia"));
/* 187 */           infoPromocionGapTO.setIdPromocion(element.getChildText("IdPromocion"));
/* 188 */           infoPromocionGapTO.setVersionPromocion(element.getChildText("VersionPromo"));
/* 189 */           infoPromocionGapTO.setNombrePromocion(element.getChildText("NombrePromocion"));
/* 190 */           infoPromocionGapTO.setJustificacionPromocion(element.getChildText("JustificacionPromocion"));
/* 191 */           infoPromocionGapTO.setFechaInicio(element.getChildText("FechaInicio"));
/* 192 */           infoPromocionGapTO.setFechaTermino(element.getChildText("FechaTermino"));
/* 193 */           infoPromocionGapTO.setFamilia(element.getChildText("Familia"));
/* 194 */           infoPromocionGapTO.setFechaAplicacionPromocion(element.getChildText("FechaAplicacionPromocion"));
/* 195 */           infoPromocionGapTO.setTerminoStandBy(element.getChildText("TerminoStandBy"));
/* 196 */           infoPromocionGapTO.setDescripcionPromocion(element.getChildText("DescripcionPromocion"));
/* 197 */           infoPromocionGapTO.setAplicaEp(element.getChildText("AplicaEP"));
/* 198 */           infoPromocionGapTO.setAplicaCA(element.getChildText("AplicaCA"));
/* 199 */           listPromociones.add(infoPromocionGapTO);
/*     */         }
/*     */       }
/* 202 */       valoracionGapTO.setPromocionesList(listPromociones);
/*     */     } catch (JDOMException e) {
/* 204 */       throw new CAException(-1, e.toString());
/*     */     } catch (IOException e) {
/* 206 */       throw new CAException(-1, e.toString());
/*     */     } catch (Exception e) {
/* 208 */       throw new CAException(-1, e.toString());
/*     */     }
/* 210 */     return valoracionGapTO;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getXmlPromoCA(String xmlInputCA, String codigoMensaje, String telefono, String idUsuario, String idpromocion, String region, String ip, String aplicaEP, String version)
/*     */   {
/* 234 */     return 
/*     */     
/*     */ 
/*     */ 
/* 238 */       xmlInputCA.replaceAll("WS_CODIGOMENSAJE", codigoMensaje).replaceAll("WS_NUMEROTELEFONO", telefono).replaceAll("WS_IDUSUARIO", idUsuario).replaceAll("WS_IDPROMOCIONGAP", idpromocion).replaceAll("WS_REGIONLINEA", region).replaceAll("WS_IPCLIENTE", ip).replaceAll("WS_VERSION", version).replaceAll("WS_APLICAEP", aplicaEP);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getXmlPromoCA()
/*     */   {
/* 246 */     return this.xmlPromoCA;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setXmlPromoCA(String xmlPromoCA)
/*     */   {
/* 253 */     this.xmlPromoCA = xmlPromoCA;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/services/gap/RespuestaConsultasGap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */