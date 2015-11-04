/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

import com.claro.m2k.web.servicios.EjecutaServicio;
import com.claro.m2k.web.servicios.EjecutaServicioResponse;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public EjecutaServicioResponse createEjecutaServicioResponse() {
        return new EjecutaServicioResponse();
    }

    public EjecutaServicio createEjecutaServicio() {
        return new EjecutaServicio();
    }
}

