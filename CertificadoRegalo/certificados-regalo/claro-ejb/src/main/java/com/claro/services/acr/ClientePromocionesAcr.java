/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.gap.PromocionCaTO
 *  com.telcel.gscrm.dccrm.admin.promo.ws.AcrValidacionTO
 *  com.telcel.gscrm.dccrm.admin.promo.ws.AcrWebServiceProxy
 *  com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO
 *  com.telcel.promociones.business.AplicaPromocionProxy
 *  com.telcel.promociones.to.PromAceptRech
 */
package com.claro.services.acr;

import com.claro.exception.CAException;
import com.claro.services.acr.ParserXML;
import com.claro.transfer.gap.PromocionCaTO;
import com.telcel.gscrm.dccrm.admin.promo.ws.AcrValidacionTO;
import com.telcel.gscrm.dccrm.admin.promo.ws.AcrWebServiceProxy;
import com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO;
import com.telcel.promociones.business.AplicaPromocionProxy;
import com.telcel.promociones.to.PromAceptRech;
import java.math.BigDecimal;
import java.rmi.RemoteException;

public class ClientePromocionesAcr {
    public PromocionCaTO consultaPromocionACR(String endpoint, String telefono, int region, String marca, String modelo, String plan) throws CAException {
        PromocionCaTO promocionTO;
        block4 : {
            promocionTO = null;
            ParserXML xml = new ParserXML();
            String xmlInput = "";
            try {
                xmlInput = xml.construyeMensaje(telefono, region, marca, modelo, plan);
                AcrWebServiceProxy serviceProxy = new AcrWebServiceProxy();
                serviceProxy.setEndpoint(endpoint);
                AcrValidacionTO promocionAcrTO = serviceProxy.validaPromocionCirculoAzul(xmlInput);
                if (promocionAcrTO != null) {
                    promocionTO = new PromocionCaTO();
                    promocionTO.setIdCampania(promocionAcrTO.getIdCampania());
                    promocionTO.setIdOferta(promocionAcrTO.getIdOferta());
                    promocionTO.setNombrePromocion(promocionAcrTO.getNombre());
                    promocionTO.setIdPromocionCA(promocionAcrTO.getIdConfiguracion());
                    promocionTO.setCantidadDescuento(new BigDecimal(promocionAcrTO.getMontoPuntos()));
                    promocionTO.setEquipos(promocionAcrTO.getEquipos());
                    break block4;
                }
                throw new CAException(-1, "ClientePromocionesAcr.consultaPromocionACR:[No se han encontrado promociones disponibles]");
            }
            catch (RemoteException e) {
                throw new CAException(-1, e.toString());
            }
            catch (Exception e) {
                throw new CAException(-1, e.toString());
            }
        }
        return promocionTO;
    }

    public String notificaPromocionSiebel(String endPointConsulta, String endPointAplica, String telefono, int region, String marca, String modelo, String planNuevo, String usuario, String ip) throws CAException {
        ClientePromocionesAcr promosACR = new ClientePromocionesAcr();
        String xmlRespuesta = "";
        try {
            PromocionCaTO gapCaTO = promosACR.consultaPromocionACR(endPointConsulta, telefono, region, marca, modelo, planNuevo);
            PromAceptRech promocionTO = new PromAceptRech();
            promocionTO.setAplicaEP("");
            promocionTO.setCodigoMensaje("PromocionAceptada");
            promocionTO.setIdCampania(gapCaTO.getIdCampania());
            promocionTO.setIdOferta(gapCaTO.getIdOferta());
            promocionTO.setIdUsuario(usuario);
            promocionTO.setIp(ip);
            promocionTO.setNumeroTelefonico(telefono);
            promocionTO.setRazonRechazo("");
            AplicaPromocionProxy serviceProxy = new AplicaPromocionProxy();
            serviceProxy.setEndpoint(endPointAplica);
            xmlRespuesta = serviceProxy.promocionAceptadaRechazada(promocionTO);
        }
        catch (RemoteException e) {
            throw new CAException(-1, e.toString());
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
        return xmlRespuesta;
    }
}

