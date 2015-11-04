/*
 * Decompiled with CFR 0_102.
 */
package com.claro.m2k.web.servicios;

public class CudisRequest {
    private String user = "";
    private String region = "";
    private String function = "";
    private String action = "";
    private String telefono = "";
    private String cuenta = "";
    private String cancelaCargoGenerado = "";
    private String planDescuento = "";
    private String acc = "";
    private String fechaExpiracion = "";
    private String cantidadNumeros = "";

    public String toXML(String operacion) {
        StringBuffer xml = new StringBuffer();
        if (operacion.equals("A") || operacion.equals("B")) {
            xml.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
            xml.append("<Request>");
            xml.append("<user>").append(this.user).append("</user>");
            xml.append("<region>").append(this.region).append("</region>");
            xml.append("<function>").append(this.function).append("</function>");
            xml.append("<action>").append(this.action).append("</action>");
            xml.append("<inputParameters>");
            xml.append("<servicio>");
            xml.append("<p01telefono longitud='10'>").append(this.telefono).append("</p01telefono>");
            xml.append("<p02cuenta longitud='09'>").append(this.cuenta).append("</p02cuenta>");
            xml.append("<p03cancelaCargoGenerado longitud='01'>").append(this.cancelaCargoGenerado).append("</p03cancelaCargoGenerado>");
            xml.append("<p04planDescuento longitud='05'>").append(this.planDescuento).append("</p04planDescuento>");
            xml.append("<p05acc longitud='01'>").append(this.acc).append("</p05acc>");
            xml.append("<p06fechaExpiracion longitud='08'>").append(this.fechaExpiracion).append("</p06fechaExpiracion>");
            xml.append("<p07cantidadNumeros longitud='06'>").append(this.cantidadNumeros).append("</p07cantidadNumeros>");
            xml.append("</servicio>");
            xml.append("</inputParameters>");
            xml.append("</Request>");
        }
        if (operacion.equals("C")) {
            xml.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
            xml.append("<Request>");
            xml.append("<user>").append(this.user).append("</user>");
            xml.append("<region>").append(this.region).append("</region>");
            xml.append("<function>").append(this.function).append("</function>");
            xml.append("<action>").append(this.action).append("</action>");
            xml.append("<inputParameters>");
            xml.append("<servicio>");
            xml.append("<p01telefono longitud='10'>").append(this.telefono).append("</p01telefono>");
            xml.append("<p02cuenta longitud='09'>").append(this.cuenta).append("</p02cuenta>");
            xml.append("</servicio>");
            xml.append("</inputParameters>");
            xml.append("</Request>");
        }
        return xml.toString();
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCancelaCargoGenerado() {
        return this.cancelaCargoGenerado;
    }

    public void setCancelaCargoGenerado(String cancelaCargoGenerado) {
        this.cancelaCargoGenerado = cancelaCargoGenerado;
    }

    public String getPlanDescuento() {
        return this.planDescuento;
    }

    public void setPlanDescuento(String planDescuento) {
        this.planDescuento = planDescuento;
    }

    public String getAcc() {
        return this.acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCantidadNumeros() {
        return this.cantidadNumeros;
    }

    public void setCantidadNumeros(String cantidadNumeros) {
        this.cantidadNumeros = cantidadNumeros;
    }
}

