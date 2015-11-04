/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJBHome
 *  javax.ejb.EJBLocalHome
 *  javax.jms.Destination
 */
package com.claro.util;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

public final class ServiceLocator {
    public static String jdbcCirculoAzul = "jdbc/PUNTOS";
    public static String jdbcMobile = "jdbc/M2K2";
    public static String jdbcMobile459 = "jdbc/M2K1";
    public static String jdbcGap = "jdbc/gap";
    public static String schema_database = "java:comp/env/database.schema";
    public static String schemaGap_database = "java:comp/env/database.schema.gap";
    public static String ejbCirculoAzul = "java:comp/env/ejb/com/claro/ejb/CirculoAzul";
    public static String ejbProcesaCatalogos = "java:comp/env/ejb/com/claro/ejb/ProcesaCatalogos";
    private static ServiceLocator serviceLocator;
    private Map<String, Object> cache;
    private InitialContext context = null;

    private ServiceLocator() throws Exception {
        try {
            this.context = new InitialContext();
            this.cache = Collections.synchronizedMap(new HashMap());
        }
        catch (NamingException ne) {
            throw new Exception("No se pudo inicializar el contexto en ServiceLocator");
        }
    }

    public static ServiceLocator getInstance() throws Exception {
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }

    public Connection getConnection(String jndi) throws Exception {
        Object objref = null;
        try {
            if (this.cache.containsKey(jndi)) {
                objref = this.cache.get(jndi);
            } else {
                objref = this.context.lookup(jndi);
                this.cache.put(jndi, objref);
            }
        }
        catch (NamingException ex) {
            throw new Exception("Error al buscar la connecion: " + jndi);
        }
        return ((DataSource)objref).getConnection();
    }

    public EJBHome getHome(String name, Class<?> clase) throws Exception {
        EJBHome home = null;
        try {
            if (this.cache.containsKey(name)) {
                home = (EJBHome)this.cache.get(name);
            } else {
                Object objref = this.context.lookup(name);
                home = (EJBHome)PortableRemoteObject.narrow(objref, clase);
                this.cache.put(name, (Object)home);
            }
        }
        catch (NamingException ex) {
            throw new Exception("Error al buscar la referencia en ServiceLocator - " + name);
        }
        return home;
    }

    public EJBLocalHome getLocalHome(String jndiHomeName) throws Exception {
        EJBLocalHome home = null;
        try {
            if (this.cache.containsKey(jndiHomeName)) {
                home = (EJBLocalHome)this.cache.get(jndiHomeName);
            } else {
                home = (EJBLocalHome)this.context.lookup(jndiHomeName);
                this.cache.put(jndiHomeName, (Object)home);
            }
        }
        catch (NamingException ne) {
            throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiHomeName);
        }
        catch (Exception e) {
            throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiHomeName);
        }
        return home;
    }

    public Destination getDestination(String jndiName) throws Exception {
        Destination destination = null;
        try {
            if (this.cache.containsKey(jndiName)) {
                destination = (Destination)this.cache.get(jndiName);
            } else {
                destination = (Destination)this.context.lookup(jndiName);
                this.cache.put(jndiName, (Object)destination);
            }
        }
        catch (Exception e) {
            throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiName, e);
        }
        return destination;
    }

    public String getVariable(String jndiName) throws Exception {
        String valor = null;
        try {
            if (this.cache.containsKey(jndiName)) {
                valor = (String)this.cache.get(jndiName);
            } else {
                valor = (String)this.context.lookup(jndiName);
                this.cache.put(jndiName, valor);
            }
        }
        catch (Exception e) {
            throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiName, e);
        }
        return valor;
    }
}

