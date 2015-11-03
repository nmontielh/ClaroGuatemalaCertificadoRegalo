/*    */ package com.claro.dao;
/*    */ 
/*    */ import com.claro.exception.CAException;
/*    */ import com.claro.transfer.BeneficioTO;
/*    */ import com.claro.transfer.MensajeTO;
/*    */ import com.claro.transfer.TelefonoTO;
/*    */ import com.claro.util.ServiceLocator;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.Timestamp;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeneficioDAO
/*    */ {
/* 18 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/* 19 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*    */   private String schema_database;
/*    */   
/*    */   public BeneficioDAO()
/*    */   {
/*    */     try {
/* 25 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*    */     } catch (Exception e) {
/* 27 */       this.error.error("RedencionDAO", e);
/*    */     }
/*    */   }
/*    */   
/*    */   public MensajeTO guardaBeneficioSeleccionado(Connection connection, TelefonoTO telefonoTO, BeneficioTO beneficioTO, String idUsuario)
/*    */     throws CAException
/*    */   {
/* 34 */     PreparedStatement statement = null;
/* 35 */     MensajeTO mensajeTO = new MensajeTO();
/*    */     try
/*    */     {
/* 38 */       if (connection.isClosed()) {
/* 39 */         connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*    */       }
/* 41 */       String query = "INSERT INTO " + this.schema_database + "PTO_TBLHISTOPROM (CUENTA, SECUENCIA, TELEFONO, " + 
/* 42 */         " FOLIO, FECHAMOVIMIENTO, USUARIO, IDBENEFICIO, IDGPOBENEF, IDMOTIVO, IDREGION) " + 
/* 43 */         " VALUES(?,?,?,?,?,?,?,?,?,?)";
/*    */       
/* 45 */       statement = connection.prepareStatement(query);
/* 46 */       Timestamp fechaMovimiento = new Timestamp(System.currentTimeMillis());
/*    */       
/* 48 */       statement.setString(1, telefonoTO.getCuenta());
/* 49 */       statement.setInt(2, Integer.parseInt(telefonoTO.getSecuencia()));
/* 50 */       statement.setString(3, telefonoTO.getTelefono());
/* 51 */       statement.setString(4, beneficioTO.getFolio());
/* 52 */       statement.setTimestamp(5, fechaMovimiento);
/* 53 */       statement.setString(6, idUsuario);
/*    */       
/* 55 */       if ((beneficioTO.getIdGpoBeneficio() != null) && (Integer.valueOf(beneficioTO.getIdGpoBeneficio().trim()).intValue() != 0)) {
/* 56 */         statement.setString(7, "");
/* 57 */         statement.setInt(8, Integer.parseInt(beneficioTO.getIdGpoBeneficio()));
/* 58 */         statement.setInt(9, Integer.parseInt(beneficioTO.getIdMotivo()));
/* 59 */         statement.setInt(10, telefonoTO.getRegion());
/*    */       } else {
/* 61 */         statement.setString(7, beneficioTO.getIdBeneficio());
/* 62 */         statement.setInt(8, 0);
/* 63 */         statement.setInt(9, Integer.parseInt(beneficioTO.getIdMotivo()));
/* 64 */         statement.setInt(10, telefonoTO.getRegion());
/*    */       }
/*    */       
/* 67 */       if (statement.executeUpdate() > 0) {
/* 68 */         mensajeTO.agregaMensaje(0, "Proceso Exitoso");
/*    */       } else {
/* 70 */         mensajeTO.agregaMensaje(-1, "No se inserto el beneficio");
/*    */       }
/* 72 */       return mensajeTO;
/*    */     } catch (Exception e) {
/* 74 */       throw new CAException(-1, "CatalogoDAO.guardaBeneficioSeleccionado[" + e.toString() + "]");
/*    */     } finally {
/* 76 */       if (statement != null) try { statement.close();statement = null;
/*    */         }
/*    */         catch (Exception localException2) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/BeneficioDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */