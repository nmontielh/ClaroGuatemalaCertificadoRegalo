/*    */ package com.claro.redencion.sms;
/*    */ 
/*    */ import com.claro.catalogo.Catalogo;
/*    */ import com.claro.transfer.MensajeTO;
/*    */ import com.claro.util.Constantes.CODIGO_ERROR;
/*    */ import com.claro.util.mq.UtilMQ;
/*    */ import com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSProxy;
/*    */ import com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSSoapBindingStub;
/*    */ import com.telcel.gscrm.dccrm.mensajessms.exception.MensajesSMSException;
/*    */ import com.telcel.gscrm.dccrm.mensajessms.to.EnviaSMS;
/*    */ import com.telcel.gscrm.dccrm.mensajessms.to.RespuestaSMS;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.rmi.RemoteException;
/*    */ import org.apache.axis.client.Service;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class NotificaSMS
/*    */ {
/* 20 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/* 21 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*    */   
/*    */   public MensajeTO enviaSMSRedencionCA(String linea, String mensajeSMS, Catalogo properties) throws com.claro.exception.CAException
/*    */   {
/* 25 */     MensajesSMSProxy proxySMS = null;
/* 26 */     RespuestaSMS respuestaSMS = null;
/* 27 */     MensajeTO mensajeTO = new MensajeTO();
/* 28 */     EnviaSMS enviaSMS = null;
/*    */     
/* 30 */     String horaProdInicio = properties.getPropiedad("ws.horaValida.inicio.redencion.mensajeSMS");
/* 31 */     String horaProdFin = properties.getPropiedad("ws.horaValida.fin.redencion.mensajeSMS");
/*    */     
/* 33 */     int horarioProducInicio = 0;
/* 34 */     int horarioProducFin = 0;
/*    */     try
/*    */     {
/* 37 */       if (horaProdInicio != null) {
/* 38 */         horarioProducInicio = Integer.parseInt(horaProdInicio.trim());
/*    */       }
/* 40 */       if (horaProdFin != null) {
/* 41 */         horarioProducFin = Integer.parseInt(horaProdFin.trim());
/*    */       }
/* 43 */       proxySMS = new MensajesSMSProxy();
/* 44 */       enviaSMS = new EnviaSMS();
/*    */       
/* 46 */       String endPointSMS = properties.getPropiedad("ws.endpoint.redencion.mensajeSMS");
/*    */       
/* 48 */       enviaSMS.setNumeroTelefonico(linea);
/* 49 */       enviaSMS.setMensajeSMS(mensajeSMS);
/* 50 */       proxySMS.setEndpoint(endPointSMS);
/*    */       
/*    */ 
/* 53 */       MensajesSMSSoapBindingStub stub = new MensajesSMSSoapBindingStub(new URL(endPointSMS), new Service());
/* 54 */       stub.setTimeout(3000);
/* 55 */       respuestaSMS = stub.enviaSMSRedencionCA(enviaSMS, horarioProducInicio, horarioProducFin);
/*    */       
/*    */ 
/* 58 */       if (respuestaSMS.getCodigoError().equals(Constantes.CODIGO_ERROR.C109.getValor())) {
/* 59 */         mensajeTO.setIdMensaje(0);
/* 60 */         mensajeTO.setMensaje(respuestaSMS.getCodigoError() + "-" + respuestaSMS.getMensaje());
/*    */       } else {
/* 62 */         mensajeTO.setIdMensaje(-1);
/* 63 */         mensajeTO.setMensaje(respuestaSMS.getCodigoError() + "-" + respuestaSMS.getMensaje());
/*    */       }
/*    */     } catch (MalformedURLException e) {
/* 66 */       this.logger.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       try
/*    */       {
/* 69 */         UtilMQ.respaldaMensaje(linea + "|" + mensajeSMS);
/*    */       } catch (Exception cae) {
/* 71 */         this.error.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       }
/*    */     } catch (MensajesSMSException e) {
/* 74 */       this.logger.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       try
/*    */       {
/* 77 */         UtilMQ.respaldaMensaje(linea + "|" + mensajeSMS);
/*    */       } catch (Exception cae) {
/* 79 */         this.error.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       }
/*    */     } catch (RemoteException e) {
/* 82 */       this.logger.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       try
/*    */       {
/* 85 */         UtilMQ.respaldaMensaje(linea + "|" + mensajeSMS);
/*    */       } catch (Exception cae) {
/* 87 */         this.error.error("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS);
/*    */       }
/*    */     }
/* 90 */     return mensajeTO;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/redencion/sms/NotificaSMS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */