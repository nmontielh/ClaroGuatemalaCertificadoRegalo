/*     */ package com.claro.util;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.ejb.EJBHome;
/*     */ import javax.ejb.EJBLocalHome;
/*     */ import javax.jms.Destination;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import javax.rmi.PortableRemoteObject;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ public final class ServiceLocator
/*     */ {
/*  16 */   public static String jdbcCirculoAzul = "jdbc/PUNTOS";
/*  17 */   public static String jdbcMobile = "jdbc/M2K2";
/*  18 */   public static String jdbcMobile459 = "jdbc/M2K1";
/*  19 */   public static String jdbcGap = "jdbc/gap";
/*  20 */   public static String schema_database = "java:comp/env/database.schema";
/*  21 */   public static String schemaGap_database = "java:comp/env/database.schema.gap";
/*  22 */   public static String ejbCirculoAzul = "java:comp/env/ejb/com/claro/ejb/CirculoAzul";
/*  23 */   public static String ejbProcesaCatalogos = "java:comp/env/ejb/com/claro/ejb/ProcesaCatalogos";
/*     */   
/*     */ 
/*     */   private static ServiceLocator serviceLocator;
/*     */   
/*     */   private Map<String, Object> cache;
/*     */   
/*  30 */   private InitialContext context = null;
/*     */   
/*     */ 
/*     */   private ServiceLocator()
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/*  38 */       this.context = new InitialContext();
/*  39 */       this.cache = Collections.synchronizedMap(new java.util.HashMap());
/*     */     } catch (NamingException ne) {
/*  41 */       throw new Exception("No se pudo inicializar el contexto en ServiceLocator");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ServiceLocator getInstance()
/*     */     throws Exception
/*     */   {
/*  51 */     if (serviceLocator == null) {
/*  52 */       serviceLocator = new ServiceLocator();
/*     */     }
/*  54 */     return serviceLocator;
/*     */   }
/*     */   
/*     */   public Connection getConnection(String jndi) throws Exception
/*     */   {
/*  59 */     Object objref = null;
/*     */     try {
/*  61 */       if (this.cache.containsKey(jndi)) {
/*  62 */         objref = this.cache.get(jndi);
/*     */       } else {
/*  64 */         objref = this.context.lookup(jndi);
/*  65 */         this.cache.put(jndi, objref);
/*     */       }
/*     */     } catch (NamingException ex) {
/*  68 */       throw new Exception("Error al buscar la connecion: " + jndi);
/*     */     }
/*  70 */     return ((DataSource)objref).getConnection();
/*     */   }
/*     */   
/*     */   public EJBHome getHome(String name, Class<?> clase) throws Exception {
/*  74 */     EJBHome home = null;
/*     */     try {
/*  76 */       if (this.cache.containsKey(name)) {
/*  77 */         home = (EJBHome)this.cache.get(name);
/*     */       } else {
/*  79 */         Object objref = this.context.lookup(name);
/*  80 */         home = (EJBHome)PortableRemoteObject.narrow(objref, clase);
/*  81 */         this.cache.put(name, home);
/*     */       }
/*     */     } catch (NamingException ex) {
/*  84 */       throw new Exception("Error al buscar la referencia en ServiceLocator - " + name);
/*     */     }
/*  86 */     return home;
/*     */   }
/*     */   
/*     */   public EJBLocalHome getLocalHome(String jndiHomeName) throws Exception
/*     */   {
/*  91 */     EJBLocalHome home = null;
/*     */     try {
/*  93 */       if (this.cache.containsKey(jndiHomeName)) {
/*  94 */         home = (EJBLocalHome)this.cache.get(jndiHomeName);
/*     */       } else {
/*  96 */         home = (EJBLocalHome)this.context.lookup(jndiHomeName);
/*  97 */         this.cache.put(jndiHomeName, home);
/*     */       }
/*     */     } catch (NamingException ne) {
/* 100 */       throw new Exception("Error al buscar la referencia en ServiceLocator - " + 
/* 101 */         jndiHomeName);
/*     */     } catch (Exception e) {
/* 103 */       throw new Exception("Error al buscar la referencia en ServiceLocator - " + 
/* 104 */         jndiHomeName);
/*     */     }
/* 106 */     return home;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Destination getDestination(String jndiName)
/*     */     throws Exception
/*     */   {
/* 116 */     Destination destination = null;
/*     */     try {
/* 118 */       if (this.cache.containsKey(jndiName)) {
/* 119 */         destination = (Destination)this.cache.get(jndiName);
/*     */       } else {
/* 121 */         destination = (Destination)this.context.lookup(jndiName);
/* 122 */         this.cache.put(jndiName, destination);
/*     */       }
/*     */     } catch (Exception e) {
/* 125 */       throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiName, e);
/*     */     }
/* 127 */     return destination;
/*     */   }
/*     */   
/*     */   public String getVariable(String jndiName) throws Exception {
/* 131 */     String valor = null;
/*     */     try {
/* 133 */       if (this.cache.containsKey(jndiName)) {
/* 134 */         valor = (String)this.cache.get(jndiName);
/*     */       } else {
/* 136 */         valor = (String)this.context.lookup(jndiName);
/* 137 */         this.cache.put(jndiName, valor);
/*     */       }
/*     */     } catch (Exception e) {
/* 140 */       throw new Exception("Error al buscar la referencia en ServiceLocator - " + jndiName, e);
/*     */     }
/* 142 */     return valor;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/util/ServiceLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */