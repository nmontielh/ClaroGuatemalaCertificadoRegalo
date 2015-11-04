/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class DocumentoTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String descripcion;
    private int idDocumento;
    private String idUsuario;
    private String ruta;
    private String nombre;
    private Timestamp fechaCreacion;
    private String estatus;

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(int IdDocumento) {
        this.idDocumento = IdDocumento;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String IdUsuario) {
        this.idUsuario = IdUsuario;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String Ruta) {
        this.ruta = Ruta;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Timestamp FechaCreacion) {
        this.fechaCreacion = FechaCreacion;
    }

    public void setDataDocumento(String idDocumento, String fileName, String sufijo, InputStream stream) throws IOException {
        this.nombre = idDocumento;
        this.nombre = fileName.length() > 4 && fileName.indexOf(46) > -1 ? String.valueOf(idDocumento) + sufijo + fileName.substring(fileName.length() - 4) : String.valueOf(sufijo) + idDocumento + this.obtenTipoArchivo(stream);
    }

    private String obtenTipoArchivo(InputStream stream) throws IOException {
        String tipo = "";
        if (stream.markSupported()) {
            byte[] buffer = new byte[10];
            String bufferString = null;
            stream.mark(11);
            stream.read(buffer);
            bufferString = new String(buffer);
            if (bufferString.startsWith("%PDF")) {
                tipo = ".pdf";
            } else if (bufferString.startsWith("GIF89")) {
                tipo = ".gif";
            } else if (bufferString.indexOf("JFIF") > -1) {
                tipo = ".jpg";
            } else if (bufferString.startsWith("PK")) {
                tipo = ".zip";
            }
            stream.reset();
        } else {
            tipo = ".pdf";
        }
        return tipo;
    }
}

