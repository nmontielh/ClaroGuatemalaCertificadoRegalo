/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.input.SAXBuilder
 */
package com.claro.services.acr;

import com.claro.exception.CAException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ParserXML {
    public String construyeMensaje(String telefono, int region, String marca, String modelo, String plan) throws CAException {
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("<ValidaPromocion>");
            buffer.append("<Telefono>").append(telefono.trim()).append("</Telefono>");
            buffer.append("<Region>").append("R0").append(region).append("</Region>");
            buffer.append("<Marca>").append(marca).append("</Marca>");
            buffer.append("<Modelo>").append(modelo).append("</Modelo>");
            buffer.append("<Plan>").append(plan).append("</Plan>");
            buffer.append("</ValidaPromocion>");
        }
        catch (Exception e) {
            throw new CAException(-1, "ParserXML.construyeMensaje [Al crear mensaje XML]");
        }
        return buffer.toString();
    }

    public String construyeMensajePeticionLinea(String telefono) throws CAException {
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("<PETICION>");
            buffer.append("<TELEFONO>").append(telefono.trim()).append("</TELEFONO>");
            buffer.append("</PETICION>");
        }
        catch (Exception e) {
            throw new CAException(-1, "ParserXML.construyeMensajePeticionLinea [Al crear mensaje XML]");
        }
        return buffer.toString();
    }

    public String construyeMensajePeticionCuenta(String cuenta) throws CAException {
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("<PETICION>");
            buffer.append("<CUENTA>").append(cuenta.trim()).append("</CUENTA>");
            buffer.append("</PETICION>");
        }
        catch (Exception e) {
            throw new CAException(-1, "ParserXML.construyeMensajePeticionCuenta [Al crear mensaje XML]");
        }
        return buffer.toString();
    }

    public int parseaRespuesta(String respuestaXML) throws CAException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = null;
        Element region = null;
        try {
            doc = sax.build((InputStream)new ByteArrayInputStream(respuestaXML.getBytes()));
            Element root = doc.getRootElement();
            region = root.getChild("REGION");
            return Integer.parseInt(region.getValue().substring(1));
        }
        catch (Exception e) {
            throw new CAException(-1, e.toString());
        }
    }
}

