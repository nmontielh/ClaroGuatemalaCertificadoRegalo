/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.promociones;

import java.io.File;
import java.io.PrintStream;

public class Archivo {
    private String nombre;
    private String ruta;

    public File obtieneArchivo(String nombreArchivo, String ruta) {
        try {
            File file = new File(String.valueOf(ruta) + nombreArchivo);
            if (file.exists()) {
                return file;
            }
            System.out.println("El archivo no existe");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al obtener el archivo.");
        }
        return null;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}

