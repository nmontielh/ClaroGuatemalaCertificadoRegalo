/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.telcel.sds.gccrm.dccrm.ws.gap.cliente.ConsultaPromocionesGap
 */
package com.claro.services.gap;

import com.claro.exception.CAException;
import com.telcel.sds.gccrm.dccrm.ws.gap.cliente.ConsultaPromocionesGap;

public class ClientePromocionesGap {
    public String consultaValoracionLinea(String endpoint, String xmlInput) throws CAException {
        String xmlOutput = "";
        ConsultaPromocionesGap consultaPromocionesGap = new ConsultaPromocionesGap();
        try {
            xmlOutput = consultaPromocionesGap.consultaInfoValoracionPromociones(endpoint, xmlInput);
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
        return xmlOutput;
    }

    public String aceptaPromocionCirculoAzul(String endpoint, String xmlMsg) throws CAException {
        String respuesta = "";
        try {
            ConsultaPromocionesGap consultaPromocionesGap = new ConsultaPromocionesGap();
            respuesta = consultaPromocionesGap.aceptaPromocionCirculoAzul(endpoint, xmlMsg);
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
        return respuesta;
    }
}

