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
@XmlType(name="", propOrder={"xml"})
@XmlRootElement(name="ejecutaServicio")
public class EjecutaServicio {
    @XmlElement(required=true, nillable=true)
    protected String xml;

    public String getXml() {
        return this.xml;
    }

    public void setXml(String value) {
        this.xml = value;
    }
}

