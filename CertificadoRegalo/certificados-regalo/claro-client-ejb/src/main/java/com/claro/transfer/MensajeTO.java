/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import java.io.Serializable;

public class MensajeTO
implements Serializable {
    private static final long serialVersionUID = 8809627781369251976L;
    private int idMensaje;
    private String mensaje;

    public MensajeTO(int idMensaje, String mensaje) {
        this.idMensaje = idMensaje;
        this.mensaje = mensaje;
    }

    public MensajeTO() {
    }

    public int getIdMensaje() {
        return this.idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void agregaMensaje(int idMensaje, String mensaje) {
        this.idMensaje = idMensaje;
        this.mensaje = mensaje;
    }

    public MensajeTO obtieneMensajeTO() {
        return new MensajeTO(this.idMensaje, this.mensaje);
    }
}

