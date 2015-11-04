/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

public class ErrorTO {
    private int idError;
    private String mensaje;

    public int getIdError() {
        return this.idError;
    }

    public void setIdError(int idError) {
        this.idError = idError;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setAgregaIdMensaje(int idError, String mensaje) {
        this.mensaje = mensaje;
        this.idError = idError;
    }
}

