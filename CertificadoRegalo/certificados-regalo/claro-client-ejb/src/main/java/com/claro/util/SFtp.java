/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.jcraft.jsch.Channel
 *  com.jcraft.jsch.ChannelSftp
 *  com.jcraft.jsch.JSch
 *  com.jcraft.jsch.Session
 */
package com.claro.util;

import com.claro.catalogo.Catalogo;
import com.claro.exception.CAException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SFtp {
    private static Session getSftpConnection(String server, String username, String password, int port) throws Exception {
        Session session = null;
        JSch jsch = new JSch();
        session = jsch.getSession(username, server, port);
        session.setPassword(password);
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);
        session.connect();
        return session;
    }

    public static void guardaArchivoCirculoAzul(String nombreArchivo, InputStream inputStream) throws Exception {
        Catalogo properties = new Catalogo();
        properties.setTabla("propiedades");
        properties.cargaCatalogo();
        String root = properties.getPropiedad("ftp.documentRoot");
        String server = properties.getPropiedad("ftp.hostname");
        int port = Integer.parseInt(properties.getPropiedad("ftp.puerto"));
        String username = properties.getPropiedad("ftp.usuario");
        String password = properties.getPropiedad("ftp.password");
        SFtp.guardaArchivo(nombreArchivo, inputStream, server, username, password, port, root);
    }

    public static void transfiereArchivoPromoDeur(String nombreArchivo, Catalogo propiedades) throws CAException {
        ChannelSftp chan = null;
        Session session = null;
        InputStream inputStream = null;
        try {
            try {
                String rootDeur = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.precios.root");
                String serverDeur = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.precios.host");
                int puertoDeur = propiedades.getPropiedadInt("ws.deur.replicaPromo.ftp.precios.port");
                String usernameDeur = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.precios.user");
                String passwordDeur = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.precios.pwd");
                session = SFtp.getSftpConnection(serverDeur, usernameDeur, passwordDeur, puertoDeur);
                chan = (ChannelSftp)session.openChannel("sftp");
                chan.connect();
                chan.cd(rootDeur);
                inputStream = chan.get(nombreArchivo);
                String serverCirculoAzul = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.circulo.host");
                String usernameCirculoAzul = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.circulo.user");
                String passwordCirculoAzul = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.circulo.pwd");
                int portCirculoAzul = propiedades.getPropiedadInt("ws.deur.replicaPromo.ftp.circulo.port");
                String rootCirculoAzul = propiedades.getPropiedad("ws.deur.replicaPromo.ftp.circulo.root");
                SFtp.guardaArchivo(nombreArchivo, inputStream, serverCirculoAzul, usernameCirculoAzul, passwordCirculoAzul, portCirculoAzul, rootCirculoAzul);
            }
            catch (Exception e) {
                throw new CAException(-1, e.getMessage());
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException var16_18) {}
            }
            chan.disconnect();
            session.disconnect();
        }
    }

    public static void guardaArchivo(String nombreArchivo, InputStream inputStream, String server, String username, String password, int port, String root) throws Exception {
        ChannelSftp chan = null;
        Session session = null;
        try {
            try {
                session = SFtp.getSftpConnection(server, username, password, port);
                chan = (ChannelSftp)session.openChannel("sftp");
                chan.connect();
                chan.cd(root);
                chan.put(inputStream, nombreArchivo);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (chan != null) {
                chan.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    public static void eliminaArchivo(String nombreArchivo) throws Exception {
        ChannelSftp chan = null;
        Session session = null;
        Catalogo properties = new Catalogo();
        properties.setTabla("propiedades");
        try {
            try {
                properties.cargaCatalogo();
                String root = properties.getPropiedad("ftp.documentRoot");
                String server = properties.getPropiedad("ftp.hostname");
                int puerto = Integer.parseInt(properties.getPropiedad("ftp.puerto"));
                String username = properties.getPropiedad("ftp.usuario");
                String password = properties.getPropiedad("ftp.password");
                session = SFtp.getSftpConnection(server, username, password, puerto);
                chan = (ChannelSftp)session.openChannel("sftp");
                chan.connect();
                chan.cd(root);
                chan.rm(nombreArchivo);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        finally {
            chan.disconnect();
            session.disconnect();
        }
    }

    public static void eliminaArchivo(String nombreArchivo, String server, String username, String password, int port, String root) throws Exception {
        ChannelSftp chan = null;
        Session session = null;
        try {
            try {
                session = SFtp.getSftpConnection(server, username, password, port);
                chan = (ChannelSftp)session.openChannel("sftp");
                chan.connect();
                chan.cd(root);
                chan.rm(nombreArchivo);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        finally {
            chan.disconnect();
            session.disconnect();
        }
    }
}

