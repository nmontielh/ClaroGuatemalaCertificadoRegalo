/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={"ejecutaServicioReturn"})
@XmlRootElement(name="ejecutaServicioResponse")
public class EjecutaServicioResponse {
    @XmlElement(required=true, nillable=true)
    protected String ejecutaServicioReturn;

    public String getEjecutaServicioReturn() {
        return this.ejecutaServicioReturn;
    }

    public void setEjecutaServicioReturn(String value) {
        this.ejecutaServicioReturn = value;
    }
}

