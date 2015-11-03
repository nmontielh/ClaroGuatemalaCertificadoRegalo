/*     */ package com.claro.dao;
/*     */ 
/*     */ import com.claro.exception.CAException;
/*     */ import com.claro.transfer.TelefonoTO;
/*     */ import com.claro.util.ServiceLocator;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimCardDAO
/*     */ {
/*  18 */   protected final Logger logger2 = Logger.getLogger("loggerCirculoAzul");
/*  19 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*     */   private String schema_database;
/*     */   private PuntosDAO puntosDAO;
/*     */   private ConsultasDAO consultasDAO;
/*     */   
/*     */   public SimCardDAO() {
/*  25 */     this.puntosDAO = new PuntosDAO();
/*  26 */     this.consultasDAO = new ConsultasDAO();
/*     */     try {
/*  28 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*     */     } catch (Exception e) {
/*  30 */       this.error.error("SimCardDAO", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public TelefonoTO consultaDatosSIM(String telefono) throws CAException {
/*  35 */     Connection connection = null;
/*  36 */     ResultSet resultSet = null;
/*  37 */     PreparedStatement preparedStatement = null;
/*     */     
/*  39 */     StringBuffer query = new StringBuffer();
/*  40 */     query.append(" SELECT A.CUENTA, A.SECUENCIA, A.IDREGION, A.ADDENDUM, B.BBONO, A.FECHAADD");
/*  41 */     query.append(" FROM ").append(this.schema_database).append("PTO_TBLLINEAS A,");
/*  42 */     query.append(this.schema_database).append("PTO_TBLTOTALES B");
/*  43 */     query.append(" WHERE A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA AND A.LINEA = ?");
/*  44 */     query.append(" AND STATUSTEL <> 'AN'");
/*  45 */     query.append(" GROUP BY A.CUENTA, A.SECUENCIA, A.IDREGION, A.ADDENDUM, B.BBONO, A.FECHAADD");
/*  46 */     query.append(" ORDER BY A.FECHAADD DESC");
/*     */     try
/*     */     {
/*  49 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  50 */       preparedStatement = connection.prepareStatement(query.toString());
/*  51 */       preparedStatement.setString(1, telefono);
/*  52 */       resultSet = preparedStatement.executeQuery();
/*  53 */       TelefonoTO telefonoTO = new TelefonoTO();
/*     */       
/*  55 */       if (resultSet.next()) {
/*  56 */         telefonoTO.setCuenta(resultSet.getString("CUENTA"));
/*  57 */         telefonoTO.setTelefono(telefono);
/*  58 */         telefonoTO.setSecuencia(resultSet.getString("SECUENCIA"));
/*  59 */         telefonoTO.setRegion(resultSet.getInt("IDREGION"));
/*     */       } else {
/*  61 */         telefonoTO.agregaMensaje(-1, "INVALIDO");
/*     */       }
/*  63 */       return telefonoTO;
/*     */     } catch (SQLException e) {
/*  65 */       throw new CAException(-1, "SQLException.consultaDatosSIM[" + e.toString() + "]", e);
/*     */     } catch (Exception e) {
/*  67 */       throw new CAException(-1, "ConsultasDAO.consultaDatosSIM[" + e.toString() + "]", e);
/*     */     } finally {
/*  69 */       try { if (resultSet != null) { resultSet.close();resultSet = null; } } catch (Exception localException4) {}
/*  70 */       try { if (preparedStatement != null) { preparedStatement.close();preparedStatement = null; } } catch (Exception localException5) {}
/*  71 */       try { if (connection != null) { connection.close();connection = null;
/*     */         }
/*     */       } catch (Exception localException6) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public String getPromoSIM(String estatus, String segmento, String tipoProd, int region) throws CAException {
/*  78 */     Connection connection = null;
/*  79 */     ResultSet resultSet = null;
/*  80 */     PreparedStatement preparedStatement = null;
/*     */     
/*  82 */     TelefonoTO telefonoTO = new TelefonoTO();
/*  83 */     StringBuffer query = new StringBuffer();
/*  84 */     query.append(" SELECT A.DESCRIP FROM ").append(this.schema_database).append("PTO_CTLPRODUCTOS A");
/*  85 */     query.append(" WHERE A.IDREGION= ? AND A.STATUS= ? AND A.IDSEGMENTO = ? AND A.TIPOREG=?");
/*  86 */     query.append(" ORDER BY A.DESCRIP");
/*     */     try {
/*  88 */       telefonoTO.setPromocionSim("");
/*     */       
/*  90 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  91 */       preparedStatement = connection.prepareStatement(query.toString());
/*  92 */       preparedStatement.setInt(1, region);
/*  93 */       preparedStatement.setString(2, estatus);
/*  94 */       preparedStatement.setString(3, segmento);
/*  95 */       preparedStatement.setString(4, tipoProd);
/*  96 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  98 */       while (resultSet.next()) {
/*  99 */         telefonoTO.setPromocionSim(resultSet.getString("DESCRIP"));
/*     */       }
/* 101 */       if ("".equals(telefonoTO.getPromocionSim())) {
/* 102 */         telefonoTO.setPromocionSim("NO HAY PROMOCIONES");
/*     */       }
/*     */       
/* 105 */       return telefonoTO.getPromocionSim();
/*     */     } catch (SQLException e) {
/* 107 */       throw new CAException(-1, "SQLException.getPromoSIM[" + e.toString() + "]", e);
/*     */     } catch (Exception e) {
/* 109 */       throw new CAException(-1, "ConsultasDAO.getPromoSIM[" + e.toString() + "]", e);
/*     */     } finally {
/* 111 */       try { if (resultSet != null) { resultSet.close();resultSet = null; } } catch (Exception localException4) {}
/* 112 */       try { if (preparedStatement != null) { preparedStatement.close();preparedStatement = null; } } catch (Exception localException5) {}
/* 113 */       try { if (connection != null) { connection.close();connection = null;
/*     */         }
/*     */       }
/*     */       catch (Exception localException6) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/SimCardDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */