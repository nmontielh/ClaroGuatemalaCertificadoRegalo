/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

import com.claro.m2k.web.servicios.ObjectFactory;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name="ControlWebService", targetNamespace="http://servicios.web.m2k.sds.telcel.com")
@XmlSeeAlso(value={ObjectFactory.class})
public interface ControlWebService {
    @WebMethod(action="ejecutaServicio")
    @WebResult(name="ejecutaServicioReturn", targetNamespace="")
    @RequestWrapper(localName="ejecutaServicio", targetNamespace="http://servicios.web.m2k.sds.telcel.com", className="com.telcel.sds.m2k.web.servicios.EjecutaServicio")
    @ResponseWrapper(localName="ejecutaServicioResponse", targetNamespace="http://servicios.web.m2k.sds.telcel.com", className="com.telcel.sds.m2k.web.servicios.EjecutaServicioResponse")
    public String ejecutaServicio(@WebParam(name="xml", targetNamespace="") String var1);
}

