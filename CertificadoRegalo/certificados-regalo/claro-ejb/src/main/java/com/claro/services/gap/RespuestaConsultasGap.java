/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.gap.InfoPromocionGapTO
 *  com.claro.transfer.gap.ValoracionGapTO
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.JDOMException
 *  org.jdom.input.SAXBuilder
 */
package com.claro.services.gap;

import com.claro.exception.CAException;
import com.claro.services.gap.ClientePromocionesGap;
import com.claro.transfer.gap.InfoPromocionGapTO;
import com.claro.transfer.gap.ValoracionGapTO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class RespuestaConsultasGap {
    private String xmlInput = "<SolicitudInfoValoracion><CodigoMensaje>WS_CODIGOMENSAJE</CodigoMensaje><NumeroTelefonico>WS_NUMEROTELEFONO</NumeroTelefonico><IdUsuario>WS_IDUSUARIO</IdUsuario></SolicitudInfoValoracion>";
    private String xmlPromoCA = "<PromAceptaCA><CodigoMensaje>WS_CODIGOMENSAJE</CodigoMensaje><NumeroTelefonico>WS_NUMEROTELEFONO</NumeroTelefonico><IdUsuario>WS_IDUSUARIO</IdUsuario><IdPromocion>WS_IDPROMOCIONGAP</IdPromocion><RegionLinea>WS_REGIONLINEA</RegionLinea><Ip>WS_IPCLIENTE</Ip><VersionPromocion>WS_VERSION</VersionPromocion><AplicaEp>WS_APLICAEP</AplicaEp></PromAceptaCA>";
    private ClientePromocionesGap clientePromocionesGap = null;

    public RespuestaConsultasGap(String codigoMensaje, String telefono, String idUsuario) {
        this.xmlInput = this.xmlInput.replaceAll("WS_CODIGOMENSAJE", codigoMensaje).replaceAll("WS_NUMEROTELEFONO", telefono).replaceAll("WS_IDUSUARIO", idUsuario);
        this.clientePromocionesGap = new ClientePromocionesGap();
    }

    public ValoracionGapTO consultaValoracionLinea(String endpoint) throws CAException {
        ValoracionGapTO valoracionGapTO = null;
        String rspXMLInfoValoracion = "";
        try {
            rspXMLInfoValoracion = this.clientePromocionesGap.consultaValoracionLinea(endpoint, this.xmlInput);
            valoracionGapTO = this.obtieneInfoValoracion(rspXMLInfoValoracion);
            return valoracionGapTO;
        }
        catch (CAException exception) {
            throw new CAException(-1, "CA.consultaValoracionLinea[" + exception.toString() + "]");
        }
        catch (Exception exception) {
            throw new CAException(-1, "CA.consultaValoracionLinea[" + exception.toString() + "]");
        }
    }

    public ValoracionGapTO obtienePromocionesGap(String endpoint, ValoracionGapTO valoracionGapTO) throws CAException {
        String rspXMLInfoValoracion = "";
        try {
            rspXMLInfoValoracion = this.clientePromocionesGap.consultaValoracionLinea(endpoint, this.xmlInput);
            return this.obtieneInfoPromocionesGap(rspXMLInfoValoracion, valoracionGapTO);
        }
        catch (CAException exception) {
            throw new CAException(-1, "CA.obtienePromocionesGap[" + exception.toString() + "]");
        }
        catch (Exception exception) {
            throw new CAException(-1, "CA.obtienePromocionesGap[" + exception.toString() + "]");
        }
    }

    public String enviaPromoCA(String endpoint, String xmlMsgCA) throws CAException {
        try {
            return this.clientePromocionesGap.aceptaPromocionCirculoAzul(endpoint, xmlMsgCA);
        }
        catch (CAException exception) {
            throw new CAException(-1, "CA.enviaPromoCA[" + exception.toString() + "]");
        }
        catch (Exception exception) {
            throw new CAException(-1, "CA.enviaPromoCA[" + exception.toString() + "]");
        }
    }

    public ValoracionGapTO obtieneInfoValoracion(String xmlInput) throws CAException {
        ValoracionGapTO valoracionGapTO = new ValoracionGapTO();
        SAXBuilder builder = new SAXBuilder();
        Document document = null;
        Element root = null;
        try {
            document = builder.build((InputStream)new ByteArrayInputStream(xmlInput.getBytes()));
            root = document.getRootElement();
            valoracionGapTO.setCodigoMensaje(root.getChildText("CodigoMensaje"));
            valoracionGapTO.setNumeroTelefonico(root.getChildText("NumeroTelefonico"));
            valoracionGapTO.setIdUsuario(root.getChildText("IdUsuario"));
            valoracionGapTO.setValoracion(root.getChildText("Valoracion"));
            valoracionGapTO.setNivelValorCliente(root.getChildText("NivelValorCliente"));
            valoracionGapTO.setContadorPromociones(root.getChildText("ContadorPromociones"));
            valoracionGapTO.setNivelChurnCliente(root.getChildText("NivelChurnCliente"));
            valoracionGapTO.setSegmentacionCliente(root.getChildText("SegmentacionCliente"));
            valoracionGapTO.setCodigoErrorMensaje(root.getChildText("CodigoErrorMensaje"));
            valoracionGapTO.setDescripcionErrorMensaje(root.getChildText("DescripcionErrorMensaje"));
        }
        catch (JDOMException e) {
            throw new CAException(-1, e.toString());
        }
        catch (IOException e) {
            throw new CAException(-1, e.toString());
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
        return valoracionGapTO;
    }

    public ValoracionGapTO obtieneInfoPromocionesGap(String xmlInput, ValoracionGapTO valoracionGapTO) throws CAException {
        ArrayList<InfoPromocionGapTO> listPromociones = null;
        List promociones = null;
        List promocion = null;
        SAXBuilder builder = new SAXBuilder();
        Document document = null;
        Element root = null;
        try {
            ListIterator iterator;
            document = builder.build((InputStream)new ByteArrayInputStream(xmlInput.getBytes()));
            root = document.getRootElement();
            promociones = root.getChildren("Promociones");
            valoracionGapTO.setCodigoMensaje(root.getChildText("CodigoMensaje"));
            valoracionGapTO.setNumeroTelefonico(root.getChildText("NumeroTelefonico"));
            valoracionGapTO.setIdUsuario(root.getChildText("IdUsuario"));
            valoracionGapTO.setContadorPromociones(root.getChildText("ContadorPromociones"));
            valoracionGapTO.setCodigoErrorMensaje(root.getChildText("CodigoErrorMensaje"));
            valoracionGapTO.setDescripcionErrorMensaje(root.getChildText("DescripcionErrorMensaje"));
            if (promociones != null && (iterator = promociones.listIterator()).hasNext()) {
                Element element = (Element)iterator.next();
                promocion = element.getChildren("Promocion");
            }
            if (promocion != null) {
                listPromociones = new ArrayList<InfoPromocionGapTO>();
                iterator = promocion.listIterator();
                while (iterator.hasNext()) {
                    InfoPromocionGapTO infoPromocionGapTO = new InfoPromocionGapTO();
                    Element element = (Element)iterator.next();
                    infoPromocionGapTO.setIdSecuencia(element.getChildText("IdSecuencia"));
                    infoPromocionGapTO.setIdPromocion(element.getChildText("IdPromocion"));
                    infoPromocionGapTO.setVersionPromocion(element.getChildText("VersionPromo"));
                    infoPromocionGapTO.setNombrePromocion(element.getChildText("NombrePromocion"));
                    infoPromocionGapTO.setJustificacionPromocion(element.getChildText("JustificacionPromocion"));
                    infoPromocionGapTO.setFechaInicio(element.getChildText("FechaInicio"));
                    infoPromocionGapTO.setFechaTermino(element.getChildText("FechaTermino"));
                    infoPromocionGapTO.setFamilia(element.getChildText("Familia"));
                    infoPromocionGapTO.setFechaAplicacionPromocion(element.getChildText("FechaAplicacionPromocion"));
                    infoPromocionGapTO.setTerminoStandBy(element.getChildText("TerminoStandBy"));
                    infoPromocionGapTO.setDescripcionPromocion(element.getChildText("DescripcionPromocion"));
                    infoPromocionGapTO.setAplicaEp(element.getChildText("AplicaEP"));
                    infoPromocionGapTO.setAplicaCA(element.getChildText("AplicaCA"));
                    listPromociones.add(infoPromocionGapTO);
                }
            }
            valoracionGapTO.setPromocionesList((ArrayList)listPromociones);
        }
        catch (JDOMException e) {
            throw new CAException(-1, e.toString());
        }
        catch (IOException e) {
            throw new CAException(-1, e.toString());
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
        return valoracionGapTO;
    }

    public String getXmlPromoCA(String xmlInputCA, String codigoMensaje, String telefono, String idUsuario, String idpromocion, String region, String ip, String aplicaEP, String version) {
        return xmlInputCA.replaceAll("WS_CODIGOMENSAJE", codigoMensaje).replaceAll("WS_NUMEROTELEFONO", telefono).replaceAll("WS_IDUSUARIO", idUsuario).replaceAll("WS_IDPROMOCIONGAP", idpromocion).replaceAll("WS_REGIONLINEA", region).replaceAll("WS_IPCLIENTE", ip).replaceAll("WS_VERSION", version).replaceAll("WS_APLICAEP", aplicaEP);
    }

    public String getXmlPromoCA() {
        return this.xmlPromoCA;
    }

    public void setXmlPromoCA(String xmlPromoCA) {
        this.xmlPromoCA = xmlPromoCA;
    }
}

