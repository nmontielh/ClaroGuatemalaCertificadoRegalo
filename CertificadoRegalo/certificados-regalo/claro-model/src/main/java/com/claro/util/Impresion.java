/*
 * Decompiled with CFR 0_102.
 */
package com.claro.util;

public class Impresion {
    public static String getFormaRed(String formaRed) {
        if (formaRed.equals("PM")) {
            return "PUNTOS MINIMOS";
        }
        if (formaRed.equals("PR")) {
            return "PRORRATEO";
        }
        if (formaRed.equals("PC")) {
            return "PPRORRATEO CAREG";
        }
        if (formaRed.equals("PD")) {
            return "PUNTOS DISPONIBLES";
        }
        return formaRed;
    }

    public static String getTipoRed(String tipoRed) {
        tipoRed = tipoRed.equals("C") ? "CON FIRMA DE ADDENDUM" : "SIN FIRMA DE ADDENDUM";
        return tipoRed;
    }
}

