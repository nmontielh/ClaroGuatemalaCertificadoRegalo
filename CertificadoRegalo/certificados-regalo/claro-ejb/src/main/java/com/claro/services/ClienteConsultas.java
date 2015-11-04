/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.telcel.gscrm.dccrm.wsm2k.ws.CRMWebServiceM2KProxy
 */
package com.claro.services;

import com.claro.exception.CAException;
import com.claro.services.acr.ParserXML;
import com.telcel.gscrm.dccrm.wsm2k.ws.CRMWebServiceM2KProxy;
import java.rmi.RemoteException;

public class ClienteConsultas {
    public int consultaRegionPorLinea(String endpoint, String telefono) throws CAException {
        ParserXML xml = new ParserXML();
        String xmlInput = "";
        try {
            xmlInput = xml.construyeMensajePeticionLinea(telefono);
            CRMWebServiceM2KProxy serviceProxy = new CRMWebServiceM2KProxy();
            serviceProxy.setEndpoint(endpoint);
            String respuesta = serviceProxy.consultaRegionOrigen(xmlInput);
            return xml.parseaRespuesta(respuesta);
        }
        catch (RemoteException e) {
            throw new CAException(-1, e.toString());
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
    }

    public int consultaRegionPorCuenta(String endpoint, String cuenta) throws CAException {
        ParserXML xml = new ParserXML();
        String xmlInput = "";
        try {
            xmlInput = xml.construyeMensajePeticionCuenta(cuenta);
            CRMWebServiceM2KProxy serviceProxy = new CRMWebServiceM2KProxy();
            serviceProxy.setEndpoint(endpoint);
            String respuesta = serviceProxy.consultaRegionOrigenPorCuenta(xmlInput);
            return xml.parseaRespuesta(respuesta);
        }
        catch (RemoteException e) {
            throw new CAException(-1, e.toString());
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
    }
}

