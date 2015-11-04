/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.util.SFtp
 *  com.claro.util.Utils
 */
package com.claro.promociones.deur;

import com.claro.catalogo.Catalogo;
import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.exception.CAException;
import com.claro.promociones.deur.dao.DeurDAO;
import com.claro.util.SFtp;
import com.claro.util.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeurThread
implements Runnable {
    private String nombreArchivo;
    private String usuario;
    private Catalogo propiedades;

    public DeurThread(String nombreArchivo, String usuario, Catalogo propiedades) {
        this.nombreArchivo = nombreArchivo.trim();
        this.usuario = usuario;
        this.propiedades = propiedades;
    }

    @Override
    public void run() {
        DeurDAO deurDAO = new DeurDAO();
        int idArchivo = 0;
        try {
            SFtp.transfiereArchivoPromoDeur((String)this.nombreArchivo, (Catalogo)this.propiedades);
            idArchivo = deurDAO.verificaExisteDetalleReplicaPromoDeur(this.nombreArchivo, "DEUR");
            if (idArchivo != 0) {
                deurDAO.actualizaDetalleReplicaPromoDeur(idArchivo, "DEUR", this.nombreArchivo, System.currentTimeMillis(), 0, this.usuario, "T");
            } else {
                idArchivo = deurDAO.obtieneConsecutivoIdArchivoReplicaPromoDeur();
                deurDAO.insertaDetalleReplicaPromoDeur(idArchivo, "DEUR", this.nombreArchivo, this.usuario, "T");
            }
        }
        catch (CAException e) {
            String errorMsg = "";
            errorMsg = e.getErrorMessage().indexOf("No such file") != -1 ? "El archivo " + this.nombreArchivo + " no existe." : "Error al procesar el archivo " + this.nombreArchivo + ", error: " + e.getMessage();
            Map parametrosCorreo = new HashMap();
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            int idReporte = 2018;
            parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreo(idReporte);
            Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar promociones para DEUR. ", (String)errorMsg, (List)null);
        }
    }
}

