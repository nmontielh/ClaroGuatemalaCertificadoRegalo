/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.util.Constantes
 *  com.claro.util.Constantes$CODIGO_ERROR
 *  com.claro.util.mq.UtilMQ
 *  com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSProxy
 *  com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSSoapBindingStub
 *  com.telcel.gscrm.dccrm.mensajessms.exception.MensajesSMSException
 *  com.telcel.gscrm.dccrm.mensajessms.to.EnviaSMS
 *  com.telcel.gscrm.dccrm.mensajessms.to.RespuestaSMS
 *  javax.xml.rpc.Service
 *  org.apache.axis.client.Service
 *  org.apache.log4j.Logger
 */
package com.claro.redencion.sms;

import com.claro.catalogo.Catalogo;
import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.util.Constantes;
import com.claro.util.mq.UtilMQ;
import com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSProxy;
import com.telcel.gscrm.dccrm.mensajessms.business.local.MensajesSMSSoapBindingStub;
import com.telcel.gscrm.dccrm.mensajessms.exception.MensajesSMSException;
import com.telcel.gscrm.dccrm.mensajessms.to.EnviaSMS;
import com.telcel.gscrm.dccrm.mensajessms.to.RespuestaSMS;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.Service;
import org.apache.log4j.Logger;

public class NotificaSMS {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");

    public MensajeTO enviaSMSRedencionCA(String linea, String mensajeSMS, Catalogo properties) throws CAException {
        MensajesSMSProxy proxySMS = null;
        RespuestaSMS respuestaSMS = null;
        MensajeTO mensajeTO = new MensajeTO();
        EnviaSMS enviaSMS = null;
        String horaProdInicio = properties.getPropiedad("ws.horaValida.inicio.redencion.mensajeSMS");
        String horaProdFin = properties.getPropiedad("ws.horaValida.fin.redencion.mensajeSMS");
        int horarioProducInicio = 0;
        int horarioProducFin = 0;
        try {
            if (horaProdInicio != null) {
                horarioProducInicio = Integer.parseInt(horaProdInicio.trim());
            }
            if (horaProdFin != null) {
                horarioProducFin = Integer.parseInt(horaProdFin.trim());
            }
            proxySMS = new MensajesSMSProxy();
            enviaSMS = new EnviaSMS();
            String endPointSMS = properties.getPropiedad("ws.endpoint.redencion.mensajeSMS");
            enviaSMS.setNumeroTelefonico(linea);
            enviaSMS.setMensajeSMS(mensajeSMS);
            proxySMS.setEndpoint(endPointSMS);
            MensajesSMSSoapBindingStub stub = new MensajesSMSSoapBindingStub(new URL(endPointSMS), (Service)new org.apache.axis.client.Service());
            stub.setTimeout(3000);
            respuestaSMS = stub.enviaSMSRedencionCA(enviaSMS, horarioProducInicio, horarioProducFin);
            if (respuestaSMS.getCodigoError().equals(Constantes.CODIGO_ERROR.C109.getValor())) {
                mensajeTO.setIdMensaje(0);
                mensajeTO.setMensaje(String.valueOf(respuestaSMS.getCodigoError()) + "-" + respuestaSMS.getMensaje());
            } else {
                mensajeTO.setIdMensaje(-1);
                mensajeTO.setMensaje(String.valueOf(respuestaSMS.getCodigoError()) + "-" + respuestaSMS.getMensaje());
            }
        }
        catch (MalformedURLException e) {
            this.logger.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            try {
                UtilMQ.respaldaMensaje((String)(String.valueOf(linea) + "|" + mensajeSMS));
            }
            catch (Exception cae) {
                this.error.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            }
        }
        catch (MensajesSMSException e) {
            this.logger.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            try {
                UtilMQ.respaldaMensaje((String)(String.valueOf(linea) + "|" + mensajeSMS));
            }
            catch (Exception cae) {
                this.error.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            }
        }
        catch (RemoteException e) {
            this.logger.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            try {
                UtilMQ.respaldaMensaje((String)(String.valueOf(linea) + "|" + mensajeSMS));
            }
            catch (Exception cae) {
                this.error.error((Object)("ERROR MENSAJE SMS:" + linea + "|" + mensajeSMS));
            }
        }
        return mensajeTO;
    }
}

