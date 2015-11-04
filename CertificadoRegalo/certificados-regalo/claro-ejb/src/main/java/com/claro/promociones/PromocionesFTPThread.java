/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.SFtp
 *  com.claro.util.Utils
 */
package com.claro.promociones;

import com.claro.catalogo.Catalogo;
import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.promociones.dao.ReplicaPromoDAO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.SFtp;
import com.claro.util.Utils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromocionesFTPThread
implements Runnable {
    private FileDataTO archivo;
    private String operacion;
    private String usuario;
    private Catalogo propiedades;

    public PromocionesFTPThread(FileDataTO archivo, String operacion, String usuario, Catalogo propiedades) {
        this.archivo = archivo;
        this.operacion = operacion;
        this.usuario = usuario;
        this.propiedades = propiedades;
    }

    @Override
    public void run() {
        ReplicaPromoDAO replicaPromoDAO = new ReplicaPromoDAO();
        int idArchivo = 0;
        try {
            SFtp.guardaArchivo((String)this.archivo.getName(), (InputStream)this.archivo.getData(), (String)this.propiedades.getPropiedad("ca.r1r8.replicaPromo.transfiereArchivo.ftp.host"), (String)this.propiedades.getPropiedad("ca.r1r8.replicaPromo.transfiereArchivo.ftp.user"), (String)this.propiedades.getPropiedad("ca.r1r8.replicaPromo.transfiereArchivo.ftp.pwd"), (int)this.propiedades.getPropiedadInt("ca.r1r8.replicaPromo.transfiereArchivo.ftp.port"), (String)this.propiedades.getPropiedad("ca.r1r8.replicaPromo.transfiereArchivo.ftp.root"));
            idArchivo = replicaPromoDAO.verificaExisteDetalleReplicaPromoFTPR1R8(this.archivo.getName());
            if (idArchivo != 0) {
                replicaPromoDAO.actualizaDetalleReplicaPromoFTPR1R8(idArchivo, "T", this.operacion);
            } else {
                replicaPromoDAO.insertaDetalleReplicaPromoFTPR1R8(this.archivo.getName(), this.operacion);
            }
        }
        catch (Exception e) {
            String errorMsg = "";
            errorMsg = e.getMessage().indexOf("No such file") != -1 ? "El archivo " + this.archivo.getName() + " no existe." : "Error al procesar el archivo " + this.archivo.getName() + ", error: " + e.getMessage();
            Map parametrosCorreo = new HashMap();
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            int idReporte = 2026;
            parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreo(idReporte);
            Utils.enviaCorreo(parametrosCorreo, (String)("Error al transferir archivo carga masiva FTP, archivo: " + this.archivo.getName()), (String)errorMsg, (List)null);
        }
    }
}

