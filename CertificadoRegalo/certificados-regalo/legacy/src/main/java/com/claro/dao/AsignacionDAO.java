/*     */package com.claro.dao;

/*     */
/*     *//*     */
import java.sql.Connection;
/*     */
import java.sql.PreparedStatement;
/*     */
import java.sql.ResultSet;
/*     */
import java.sql.SQLException;
/*     */
import java.util.ArrayList;

/*     */
import org.apache.log4j.Logger;

import com.claro.exception.CAException;
/*     */
import com.claro.transfer.FolioLiberacionTO;
/*     */
import com.claro.transfer.MensajeTO;
/*     */
import com.claro.transfer.ParametrosTO;
/*     */
import com.claro.transfer.PuntosTO;
/*     */
import com.claro.util.Constantes;
/*     */
import com.claro.util.ServiceLocator;
/*     */
import com.claro.util.Utils;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */public class AsignacionDAO
/*     */{
	/* 25 */protected final Logger logger = Logger
			.getLogger("loggerCirculoAzul");
	/* 26 */protected final Logger error = Logger
			.getLogger("loggerCirculoAzulError");
	/*     */private String schema_database;

	/*     */
	/*     */public AsignacionDAO() {
		/*     */try {
			/* 31 */this.schema_database = ServiceLocator.getInstance()
					.getVariable(ServiceLocator.schema_database);
			/*     */} catch (Exception e) {
			/* 33 */this.error.error("AsignacionDAO", e);
			/*     */}
		/*     */}

	/*     */
	/*     */public MensajeTO procesaAsignacion(ParametrosTO parametrosTO,
			String usuario, int accion, int ptsAsignar, String comentario,
			String motivoAsig) throws CAException
	/*     */{
		/* 39 */Connection connection = null;
		/* 40 */MensajeTO mensajeTO = new MensajeTO();
		/* 41 */int inPuntos = ptsAsignar;
		/* 42 */int ptsExcedentes = 0;
		/* 43 */int[] puntosEliminar = new int[7];
		/*     */
		/*     */try
		/*     */{
			/* 47 */connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			/* 48 */connection.setAutoCommit(false);
			/* 49 */long fechaTransaccion = System.currentTimeMillis();
			/*     */
			/* 51 */PuntosDAO puntosDAO = new PuntosDAO();
			/* 52 */ConsultasDAO consultasDAO = new ConsultasDAO();
			/*     */
			/* 54 */PuntosTO puntosTO = consultasDAO.obtienePuntos(
					parametrosTO.getCuenta(), parametrosTO.getSecuencia());
			/* 55 */if (puntosTO.getIdMensaje() != 0) {
				/* 56 */return puntosTO.obtieneMensajeTO();
				/*     */}
			/* 58 */if (accion == Constantes.ASIGNAR_PUNTOS)
			/*     */{
				/* 60 */String referencia = "ASIGNA: " + usuario + " MOTIVO: "
						+ motivoAsig + " COMENT: " + comentario;
				/* 61 */if (referencia.length() > 100) {
					/* 62 */referencia = referencia.substring(0, 100);
					/*     */}
				/* 64 */mensajeTO = puntosDAO.insertaDetalle(connection,
						fechaTransaccion, referencia, 15, ptsAsignar, null,
						parametrosTO.getCuenta(), parametrosTO.getSecuencia(),
						parametrosTO.getTelefono(), usuario);
				/*     */
				/*     */
				/* 67 */referencia = "CIR - ASIGNA " + ptsAsignar
						+ " PTOS. REALIZO: " + usuario + " " + comentario;
				/*     */
				/* 69 */if (mensajeTO.getIdMensaje() == 0) {
					/* 70 */mensajeTO = puntosDAO.insertaComentarioTMP(
							connection, parametrosTO.getRegion(),
							parametrosTO.getCuenta(), usuario,
							fechaTransaccion, referencia);
					/*     */}
				/*     */
				/* 73 */if (mensajeTO.getIdMensaje() == 0)
					/* 74 */ptsExcedentes = puntosTO.getPtsExcedentes()
							+ ptsAsignar;
				/* 75 */mensajeTO = actualizaTotalesAsignacion(connection,
						ptsExcedentes, parametrosTO.getCuenta(),
						parametrosTO.getSecuencia());
				/*     */}
			/* 77 */else if (accion == Constantes.ELIMINAR_PUNTOS)
			/*     */{
				/*     */
				/* 80 */if (puntosTO.getPtsTotales() < ptsAsignar) {
					/* 81 */mensajeTO
							.agregaMensaje(-1,
									"La linea no cuenta con los suficientes puntos para eliminar.");
					/*     */}
				/*     */else {
					/* 84 */puntosEliminar[0] = puntosTO.getPtsPorVencer();
					/* 85 */puntosEliminar[1] = puntosTO.getPtsPorVencer1();
					/* 86 */puntosEliminar[2] = puntosTO.getPtsPorVencer2();
					/* 87 */puntosEliminar[3] = puntosTO.getPtsPromocion();
					/* 88 */puntosEliminar[4] = puntosTO.getPtsAntiguedad();
					/* 89 */puntosEliminar[5] = puntosTO.getPtsExcedentes();
					/* 90 */puntosEliminar[6] = puntosTO.getPtsRenta();
					/*     */
					/* 92 */for (int i = 0; i < puntosEliminar.length; i++) {
						/* 93 */if (puntosEliminar[i] > 0) {
							/* 94 */if (puntosEliminar[i] < inPuntos)
							/*     */{
								/* 96 */inPuntos -= puntosEliminar[i];
								/* 97 */puntosEliminar[i] = 0;
								/*     */}
							/*     */else {
								/* 100 */puntosEliminar[i] -= inPuntos;
								/* 101 */inPuntos = 0;
								/* 102 */break;
								/*     */}
							/*     */}
						/*     */}
					/*     */
					/* 107 */String referencia = "ELIMINA: " + usuario
							+ " COMENT: " + comentario;
					/* 108 */int ptsAsignarneg = -ptsAsignar;
					/* 109 */if (referencia.length() > 100)
						/* 110 */referencia = referencia.substring(0, 100);
					/* 111 */mensajeTO = puntosDAO.insertaDetalle(connection,
							fechaTransaccion, referencia, 18, ptsAsignarneg,
							null, parametrosTO.getCuenta(),
							parametrosTO.getSecuencia(),
							parametrosTO.getTelefono(), usuario);
					/*     */
					/*     */
					/* 114 */referencia = "CIR - ELIMINA " + ptsAsignar
							+ " PTOS. REALIZO: " + usuario + " " + comentario;
					/* 115 */if (mensajeTO.getIdMensaje() == 0) {
						/* 116 */mensajeTO = puntosDAO.insertaComentarioTMP(
								connection, parametrosTO.getRegion(),
								parametrosTO.getCuenta(), usuario,
								fechaTransaccion, referencia);
						/*     */}
					/*     */
					/* 119 */if (mensajeTO.getIdMensaje() == 0)
						/* 120 */mensajeTO = actualizaTotalesEliminacion(
								connection, puntosEliminar,
								parametrosTO.getCuenta(),
								parametrosTO.getSecuencia());
					/*     */}
				/*     */}
			/* 123 */if (mensajeTO.getIdMensaje() == 0)
				/* 124 */connection.commit();
			else {
				/* 125 */connection.rollback();
				/*     */}
			/*     */}
		/*     */catch (SQLException e) {
			/* 129 */if (connection != null)
				try {
					connection.rollback();
				} catch (Exception localException2) {
				}
			/* 130 */this.error.info("SQLException.procesaAsignacion:", e);
			/* 131 */throw new CAException(-1, "[procesaAsignacion] SQLError: "
					+ e.toString() + "Actualizar Inf", e);
			/*     */} catch (Exception e) {
			/* 133 */if (connection != null)
				try {
					connection.rollback();
				} catch (Exception localException3) {
				}
			/* 134 */this.error.info("Exception.procesaAsignacion:", e);
			/* 135 */throw new CAException(-1, "[procesaAsignacion] Error: "
					+ e.toString() + "Actualizar Inf", e);
			/*     */} finally {
			/* 137 */if (connection != null)
				try {
					connection.setAutoCommit(true);
					connection.close();
					connection = null;
				} catch (Exception localException4) {
				}
		}
		if (connection != null)
			try {
				connection.setAutoCommit(true);
				connection.close();
				connection = null;
				/*     */} catch (Exception localException5) {
			}
		/* 139 */return mensajeTO;
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public MensajeTO actualizaTotalesAsignacion(Connection connection,
			int ptsExcedentes, String sCuenta, int iSecuencia)
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 5
		/*     */// 3: new 61 com/claro/transfer/MensajeTO
		/*     */// 6: dup
		/*     */// 7: invokespecial 63 com/claro/transfer/MensajeTO:<init> ()V
		/*     */// 10: astore 6
		/*     */// 12: aconst_null
		/*     */// 13: astore 7
		/*     */// 15: new 121 java/lang/StringBuilder
		/*     */// 18: dup
		/*     */// 19: ldc_w 273
		/*     */// 22: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 25: aload_0
		/*     */// 26: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 29: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 32: ldc_w 275
		/*     */// 35: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 38: ldc_w 277
		/*     */// 41: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 44: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 47: astore 8
		/*     */// 49: aload_1
		/*     */// 50: ifnull +9 -> 59
		/*     */// 53: aload_1
		/*     */// 54: astore 5
		/*     */// 56: goto +14 -> 70
		/*     */// 59: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 62: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 65: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 68: astore 5
		/*     */// 70: aload 5
		/*     */// 72: aload 8
		/*     */// 74: invokeinterface 279 2 0
		/*     */// 79: astore 7
		/*     */// 81: aload 7
		/*     */// 83: iconst_1
		/*     */// 84: iload_2
		/*     */// 85: invokeinterface 283 3 0
		/*     */// 90: aload 7
		/*     */// 92: iconst_2
		/*     */// 93: aload_3
		/*     */// 94: invokeinterface 289 3 0
		/*     */// 99: aload 7
		/*     */// 101: iconst_3
		/*     */// 102: iload 4
		/*     */// 104: invokeinterface 283 3 0
		/*     */// 109: aload 7
		/*     */// 111: invokeinterface 292 1 0
		/*     */// 116: ifle +15 -> 131
		/*     */// 119: aload 6
		/*     */// 121: iconst_0
		/*     */// 122: ldc_w 295
		/*     */// 125: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 128: goto +161 -> 289
		/*     */// 131: aload 6
		/*     */// 133: iconst_1
		/*     */// 134: ldc_w 297
		/*     */// 137: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 140: goto +149 -> 289
		/*     */// 143: astore 8
		/*     */// 145: aload_1
		/*     */// 146: ifnull +14 -> 160
		/*     */// 149: aload_1
		/*     */// 150: invokeinterface 220 1 0
		/*     */// 155: goto +5 -> 160
		/*     */// 158: astore 9
		/*     */// 160: aload_0
		/*     */// 161: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 164: ldc_w 299
		/*     */// 167: aload 8
		/*     */// 169: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 172: new 59 com/claro/exception/CAException
		/*     */// 175: dup
		/*     */// 176: iconst_m1
		/*     */// 177: new 121 java/lang/StringBuilder
		/*     */// 180: dup
		/*     */// 181: ldc_w 301
		/*     */// 184: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 187: aload 8
		/*     */// 189: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 192: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 195: ldc -23
		/*     */// 197: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 200: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 203: aload 8
		/*     */// 205: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 208: athrow
		/*     */// 209: astore 8
		/*     */// 211: aload_0
		/*     */// 212: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 215: ldc_w 303
		/*     */// 218: aload 8
		/*     */// 220: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 223: new 59 com/claro/exception/CAException
		/*     */// 226: dup
		/*     */// 227: iconst_m1
		/*     */// 228: new 121 java/lang/StringBuilder
		/*     */// 231: dup
		/*     */// 232: ldc_w 305
		/*     */// 235: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 238: aload 8
		/*     */// 240: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 243: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 246: ldc -23
		/*     */// 248: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 251: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 254: aload 8
		/*     */// 256: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 259: athrow
		/*     */// 260: astore 10
		/*     */// 262: aload 5
		/*     */// 264: ifnull +22 -> 286
		/*     */// 267: aload_1
		/*     */// 268: ifnonnull +18 -> 286
		/*     */// 271: aload 5
		/*     */// 273: invokeinterface 112 1 0
		/*     */// 278: aconst_null
		/*     */// 279: astore 5
		/*     */// 281: goto +5 -> 286
		/*     */// 284: astore 11
		/*     */// 286: aload 10
		/*     */// 288: athrow
		/*     */// 289: aload 5
		/*     */// 291: ifnull +22 -> 313
		/*     */// 294: aload_1
		/*     */// 295: ifnonnull +18 -> 313
		/*     */// 298: aload 5
		/*     */// 300: invokeinterface 112 1 0
		/*     */// 305: aconst_null
		/*     */// 306: astore 5
		/*     */// 308: goto +5 -> 313
		/*     */// 311: astore 11
		/*     */// 313: aload 6
		/*     */// 315: areturn
		/*     */// Line number table:
		/*     */// Java source line #143 -> byte code offset #0
		/*     */// Java source line #144 -> byte code offset #3
		/*     */// Java source line #145 -> byte code offset #12
		/*     */// Java source line #148 -> byte code offset #15
		/*     */// Java source line #149 -> byte code offset #38
		/*     */// Java source line #148 -> byte code offset #44
		/*     */// Java source line #151 -> byte code offset #49
		/*     */// Java source line #152 -> byte code offset #59
		/*     */// Java source line #154 -> byte code offset #70
		/*     */// Java source line #155 -> byte code offset #81
		/*     */// Java source line #156 -> byte code offset #90
		/*     */// Java source line #157 -> byte code offset #99
		/*     */// Java source line #159 -> byte code offset #109
		/*     */// Java source line #160 -> byte code offset #131
		/*     */// Java source line #161 -> byte code offset #143
		/*     */// Java source line #162 -> byte code offset #145
		/*     */// Java source line #163 -> byte code offset #160
		/*     */// Java source line #164 -> byte code offset #172
		/*     */// Java source line #165 -> byte code offset #209
		/*     */// Java source line #166 -> byte code offset #211
		/*     */// Java source line #167 -> byte code offset #223
		/*     */// Java source line #168 -> byte code offset #260
		/*     */// Java source line #169 -> byte code offset #262
		/*     */// Java source line #170 -> byte code offset #286
		/*     */// Java source line #169 -> byte code offset #289
		/*     */// Java source line #171 -> byte code offset #313
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 316 0 this AsignacionDAO
		/*     */// 0 316 1 connection Connection
		/*     */// 0 316 2 ptsExcedentes int
		/*     */// 0 316 3 sCuenta String
		/*     */// 0 316 4 iSecuencia int
		/*     */// 1 306 5 lConn Connection
		/*     */// 10 304 6 mensajeTO MensajeTO
		/*     */// 13 97 7 statement PreparedStatement
		/*     */// 47 26 8 UpdateTotales String
		/*     */// 143 61 8 e SQLException
		/*     */// 209 46 8 e Exception
		/*     */// 158 1 9 localException1 Exception
		/*     */// 260 27 10 localObject Object
		/*     */// 284 1 11 localException2 Exception
		/*     */// 311 1 11 localException3 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 15 140 143 java/sql/SQLException
		/*     */// 149 155 158 java/lang/Exception
		/*     */// 15 140 209 java/lang/Exception
		/*     */// 15 260 260 finally
		/*     */// 271 281 284 java/lang/Exception
		/*     */// 298 308 311 java/lang/Exception
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */private MensajeTO actualizaTotalesEliminacion(Connection connection,
			int[] puntosEliminar, String sCuenta, int iSecuencia)
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 5
		/*     */// 3: aconst_null
		/*     */// 4: astore 6
		/*     */// 6: aconst_null
		/*     */// 7: astore 7
		/*     */// 9: aload_1
		/*     */// 10: ifnonnull +17 -> 27
		/*     */// 13: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 16: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 19: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 22: astore 5
		/*     */// 24: goto +6 -> 30
		/*     */// 27: aload_1
		/*     */// 28: astore 5
		/*     */// 30: new 313 java/lang/StringBuffer
		/*     */// 33: dup
		/*     */// 34: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 37: astore 8
		/*     */// 39: aload 8
		/*     */// 41: ldc_w 316
		/*     */// 44: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 47: aload_0
		/*     */// 48: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 51: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 54: ldc_w 321
		/*     */// 57: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 60: pop
		/*     */// 61: aload 8
		/*     */// 63: ldc_w 323
		/*     */// 66: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 69: pop
		/*     */// 70: aload 8
		/*     */// 72: ldc_w 325
		/*     */// 75: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 78: pop
		/*     */// 79: aload 8
		/*     */// 81: ldc_w 327
		/*     */// 84: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 87: pop
		/*     */// 88: aload 8
		/*     */// 90: ldc_w 329
		/*     */// 93: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 96: pop
		/*     */// 97: aload 8
		/*     */// 99: ldc_w 331
		/*     */// 102: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 105: pop
		/*     */// 106: aload 8
		/*     */// 108: ldc_w 333
		/*     */// 111: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 114: pop
		/*     */// 115: aload 8
		/*     */// 117: ldc_w 335
		/*     */// 120: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 123: pop
		/*     */// 124: aload 8
		/*     */// 126: ldc_w 337
		/*     */// 129: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 132: pop
		/*     */// 133: aload_2
		/*     */// 134: iconst_0
		/*     */// 135: iaload
		/*     */// 136: ifne +12 -> 148
		/*     */// 139: aload 8
		/*     */// 141: ldc_w 339
		/*     */// 144: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 147: pop
		/*     */// 148: aload_2
		/*     */// 149: iconst_1
		/*     */// 150: iaload
		/*     */// 151: ifne +12 -> 163
		/*     */// 154: aload 8
		/*     */// 156: ldc_w 341
		/*     */// 159: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 162: pop
		/*     */// 163: aload_2
		/*     */// 164: iconst_2
		/*     */// 165: iaload
		/*     */// 166: ifne +12 -> 178
		/*     */// 169: aload 8
		/*     */// 171: ldc_w 343
		/*     */// 174: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 177: pop
		/*     */// 178: aload 8
		/*     */// 180: ldc_w 345
		/*     */// 183: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 186: pop
		/*     */// 187: aload 8
		/*     */// 189: ldc_w 347
		/*     */// 192: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 195: pop
		/*     */// 196: aload 5
		/*     */// 198: aload 8
		/*     */// 200: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 203: invokeinterface 279 2 0
		/*     */// 208: astore 6
		/*     */// 210: aload 6
		/*     */// 212: iconst_1
		/*     */// 213: aload_2
		/*     */// 214: iconst_0
		/*     */// 215: iaload
		/*     */// 216: invokeinterface 283 3 0
		/*     */// 221: aload 6
		/*     */// 223: iconst_2
		/*     */// 224: aload_2
		/*     */// 225: iconst_0
		/*     */// 226: iaload
		/*     */// 227: invokeinterface 283 3 0
		/*     */// 232: aload 6
		/*     */// 234: iconst_3
		/*     */// 235: aload_2
		/*     */// 236: iconst_1
		/*     */// 237: iaload
		/*     */// 238: invokeinterface 283 3 0
		/*     */// 243: aload 6
		/*     */// 245: iconst_4
		/*     */// 246: aload_2
		/*     */// 247: iconst_2
		/*     */// 248: iaload
		/*     */// 249: invokeinterface 283 3 0
		/*     */// 254: aload 6
		/*     */// 256: iconst_5
		/*     */// 257: aload_2
		/*     */// 258: iconst_3
		/*     */// 259: iaload
		/*     */// 260: invokeinterface 283 3 0
		/*     */// 265: aload 6
		/*     */// 267: bipush 6
		/*     */// 269: aload_2
		/*     */// 270: iconst_4
		/*     */// 271: iaload
		/*     */// 272: invokeinterface 283 3 0
		/*     */// 277: aload 6
		/*     */// 279: bipush 7
		/*     */// 281: aload_2
		/*     */// 282: iconst_5
		/*     */// 283: iaload
		/*     */// 284: invokeinterface 283 3 0
		/*     */// 289: aload 6
		/*     */// 291: bipush 8
		/*     */// 293: aload_2
		/*     */// 294: bipush 6
		/*     */// 296: iaload
		/*     */// 297: invokeinterface 283 3 0
		/*     */// 302: aload 6
		/*     */// 304: bipush 9
		/*     */// 306: aload_3
		/*     */// 307: invokeinterface 289 3 0
		/*     */// 312: aload 6
		/*     */// 314: bipush 10
		/*     */// 316: iload 4
		/*     */// 318: invokeinterface 283 3 0
		/*     */// 323: new 61 com/claro/transfer/MensajeTO
		/*     */// 326: dup
		/*     */// 327: invokespecial 63 com/claro/transfer/MensajeTO:<init> ()V
		/*     */// 330: astore 7
		/*     */// 332: aload 6
		/*     */// 334: invokeinterface 292 1 0
		/*     */// 339: ifle +15 -> 354
		/*     */// 342: aload 7
		/*     */// 344: iconst_0
		/*     */// 345: ldc_w 350
		/*     */// 348: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 351: goto +168 -> 519
		/*     */// 354: aload 7
		/*     */// 356: iconst_m1
		/*     */// 357: ldc_w 297
		/*     */// 360: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 363: goto +156 -> 519
		/*     */// 366: astore 8
		/*     */// 368: aload_0
		/*     */// 369: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 372: ldc_w 352
		/*     */// 375: aload 8
		/*     */// 377: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 380: new 59 com/claro/exception/CAException
		/*     */// 383: dup
		/*     */// 384: iconst_m1
		/*     */// 385: new 121 java/lang/StringBuilder
		/*     */// 388: dup
		/*     */// 389: ldc_w 354
		/*     */// 392: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 395: aload 8
		/*     */// 397: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 400: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 403: ldc_w 356
		/*     */// 406: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 409: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 412: aload 8
		/*     */// 414: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 417: athrow
		/*     */// 418: astore 8
		/*     */// 420: aload_0
		/*     */// 421: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 424: ldc_w 358
		/*     */// 427: aload 8
		/*     */// 429: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 432: new 59 com/claro/exception/CAException
		/*     */// 435: dup
		/*     */// 436: iconst_m1
		/*     */// 437: new 121 java/lang/StringBuilder
		/*     */// 440: dup
		/*     */// 441: ldc_w 354
		/*     */// 444: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 447: aload 8
		/*     */// 449: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 452: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 455: ldc_w 356
		/*     */// 458: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 461: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 464: aload 8
		/*     */// 466: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 469: athrow
		/*     */// 470: astore 9
		/*     */// 472: aload 6
		/*     */// 474: ifnull +18 -> 492
		/*     */// 477: aload 6
		/*     */// 479: invokeinterface 360 1 0
		/*     */// 484: aconst_null
		/*     */// 485: astore 6
		/*     */// 487: goto +5 -> 492
		/*     */// 490: astore 10
		/*     */// 492: aload_1
		/*     */// 493: ifnonnull +23 -> 516
		/*     */// 496: aload 5
		/*     */// 498: ifnull +18 -> 516
		/*     */// 501: aload 5
		/*     */// 503: invokeinterface 112 1 0
		/*     */// 508: aconst_null
		/*     */// 509: astore 5
		/*     */// 511: goto +5 -> 516
		/*     */// 514: astore 10
		/*     */// 516: aload 9
		/*     */// 518: athrow
		/*     */// 519: aload 6
		/*     */// 521: ifnull +18 -> 539
		/*     */// 524: aload 6
		/*     */// 526: invokeinterface 360 1 0
		/*     */// 531: aconst_null
		/*     */// 532: astore 6
		/*     */// 534: goto +5 -> 539
		/*     */// 537: astore 10
		/*     */// 539: aload_1
		/*     */// 540: ifnonnull +23 -> 563
		/*     */// 543: aload 5
		/*     */// 545: ifnull +18 -> 563
		/*     */// 548: aload 5
		/*     */// 550: invokeinterface 112 1 0
		/*     */// 555: aconst_null
		/*     */// 556: astore 5
		/*     */// 558: goto +5 -> 563
		/*     */// 561: astore 10
		/*     */// 563: aload 7
		/*     */// 565: areturn
		/*     */// Line number table:
		/*     */// Java source line #176 -> byte code offset #0
		/*     */// Java source line #177 -> byte code offset #3
		/*     */// Java source line #178 -> byte code offset #6
		/*     */// Java source line #181 -> byte code offset #9
		/*     */// Java source line #182 -> byte code offset #27
		/*     */// Java source line #184 -> byte code offset #30
		/*     */// Java source line #186 -> byte code offset #39
		/*     */// Java source line #187 -> byte code offset #61
		/*     */// Java source line #188 -> byte code offset #70
		/*     */// Java source line #189 -> byte code offset #79
		/*     */// Java source line #190 -> byte code offset #88
		/*     */// Java source line #191 -> byte code offset #97
		/*     */// Java source line #192 -> byte code offset #106
		/*     */// Java source line #193 -> byte code offset #115
		/*     */// Java source line #194 -> byte code offset #124
		/*     */// Java source line #195 -> byte code offset #133
		/*     */// Java source line #196 -> byte code offset #139
		/*     */// Java source line #198 -> byte code offset #148
		/*     */// Java source line #199 -> byte code offset #154
		/*     */// Java source line #201 -> byte code offset #163
		/*     */// Java source line #202 -> byte code offset #169
		/*     */// Java source line #204 -> byte code offset #178
		/*     */// Java source line #205 -> byte code offset #187
		/*     */// Java source line #207 -> byte code offset #196
		/*     */// Java source line #209 -> byte code offset #210
		/*     */// Java source line #210 -> byte code offset #221
		/*     */// Java source line #211 -> byte code offset #232
		/*     */// Java source line #212 -> byte code offset #243
		/*     */// Java source line #213 -> byte code offset #254
		/*     */// Java source line #214 -> byte code offset #265
		/*     */// Java source line #215 -> byte code offset #277
		/*     */// Java source line #216 -> byte code offset #289
		/*     */// Java source line #217 -> byte code offset #302
		/*     */// Java source line #218 -> byte code offset #312
		/*     */// Java source line #220 -> byte code offset #323
		/*     */// Java source line #221 -> byte code offset #332
		/*     */// Java source line #222 -> byte code offset #342
		/*     */// Java source line #223 -> byte code offset #354
		/*     */// Java source line #225 -> byte code offset #366
		/*     */// Java source line #226 -> byte code offset #368
		/*     */// Java source line #227 -> byte code offset #380
		/*     */// Java source line #228 -> byte code offset #418
		/*     */// Java source line #229 -> byte code offset #420
		/*     */// Java source line #230 -> byte code offset #432
		/*     */// Java source line #231 -> byte code offset #470
		/*     */// Java source line #232 -> byte code offset #472
		/*     */// Java source line #233 -> byte code offset #492
		/*     */// Java source line #234 -> byte code offset #516
		/*     */// Java source line #232 -> byte code offset #519
		/*     */// Java source line #233 -> byte code offset #539
		/*     */// Java source line #235 -> byte code offset #563
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 566 0 this AsignacionDAO
		/*     */// 0 566 1 connection Connection
		/*     */// 0 566 2 puntosEliminar int[]
		/*     */// 0 566 3 sCuenta String
		/*     */// 0 566 4 iSecuencia int
		/*     */// 1 556 5 lConn Connection
		/*     */// 4 529 6 statement PreparedStatement
		/*     */// 7 557 7 mensajeTO MensajeTO
		/*     */// 37 162 8 UpdateTotales StringBuffer
		/*     */// 366 47 8 e SQLException
		/*     */// 418 47 8 e Exception
		/*     */// 470 47 9 localObject Object
		/*     */// 490 1 10 localException1 Exception
		/*     */// 514 1 10 localException2 Exception
		/*     */// 537 1 10 localException3 Exception
		/*     */// 561 1 10 localException4 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 9 363 366 java/sql/SQLException
		/*     */// 9 363 418 java/lang/Exception
		/*     */// 9 470 470 finally
		/*     */// 477 487 490 java/lang/Exception
		/*     */// 501 511 514 java/lang/Exception
		/*     */// 524 534 537 java/lang/Exception
		/*     */// 548 558 561 java/lang/Exception
		/*     */}

	/*     */
	/*     */public FolioLiberacionTO asignaPorAntiguedad(
			ParametrosTO parametrosTO, String lFecAltaM2K, String comentario,
			String usuario)
	/*     */throws CAException
	/*     */{
		/* 242 */MensajeTO mensajeTO = null;
		/* 243 */FolioLiberacionTO folioLiberacionTO = null;
		/* 244 */Connection connection = null;
		/* 245 */PreparedStatement statement = null;
		PreparedStatement statementTot = null;
		/* 246 */ResultSet resultSet = null;
		ResultSet resultSetTot = null;
		/* 247 */PuntosDAO puntosDAO = new PuntosDAO();
		/*     */
		/*     */
		/* 250 */long fechaTransaccion = System.currentTimeMillis();
		/* 251 */StringBuffer sQuery = new StringBuffer();
		/*     */
		/* 253 */sQuery
				.append(" SELECT FECHAOPERACION, IDUSUARIO, REFERENCIA ");
		/* 254 */sQuery.append("  FROM  ").append(this.schema_database)
				.append("PTO_TBLMSTRDETALLE ");
		/* 255 */sQuery.append(" WHERE CUENTA = ? AND SECUENCIA = ? ");
		/* 256 */sQuery.append(" AND IDMOVTO = ?  AND IDUSUARIO <> 'VIBPT01'");
		/*     */try
		/*     */{
			/* 259 */mensajeTO = new MensajeTO();
			/* 260 */folioLiberacionTO = new FolioLiberacionTO();
			/* 261 */connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			/* 262 */statement = connection.prepareStatement(sQuery.toString());
			/*     */
			/* 264 */statement.setString(1, parametrosTO.getCuenta());
			/* 265 */statement.setInt(2, parametrosTO.getSecuencia());
			/* 266 */statement.setInt(3, 24);
			/*     */
			/*     */
			/* 269 */resultSet = statement.executeQuery();
			/*     */FolioLiberacionTO localFolioLiberacionTO1;
			/* 271 */if (resultSet.next()) {
				/* 272 */folioLiberacionTO
						.agregaMensaje(
								-1,
								"Para esa cuenta ya hubo una asignacion de puntos por cambio de region.\tConsultela en el detalle de movimientos.");
				/* 273 */int nPtsAsignar;
				return folioLiberacionTO;
				/*     */}
			/*     */
			/* 276 */if (!Utils.validaFechaM2K(lFecAltaM2K)) {
				/* 277 */folioLiberacionTO
						.agregaMensaje(-1,
								"Error al consultar la fecha de alta en M2K. Intente otra vez.");
				/* 278 */int nPtsAsignar;
				return folioLiberacionTO;
				/*     */}
			/*     */
			/*     */
			/*     */
			/* 283 */mensajeTO = validaFechaAnt(connection,
					parametrosTO.getCuenta(), parametrosTO.getSecuencia(),
					lFecAltaM2K);
			/* 284 */if (mensajeTO.getIdMensaje() != 0) {
				/* 285 */folioLiberacionTO.agregaMensaje(-1,
						mensajeTO.getMensaje());
				/* 286 */int nPtsAsignar;
				return folioLiberacionTO;
				/*     */}
			/*     */
			/* 289 */int nPtsAsignar = Utils.calculaPuntos(lFecAltaM2K);
			/*     */
			/*     */
			/* 292 */if (nPtsAsignar == -1) {
				/* 293 */folioLiberacionTO
						.agregaMensaje(-1,
								"Ocurrio un error durante el calculo de puntos a asignar por antigüedad.");
				/* 294 */return folioLiberacionTO;
				/*     */}
			/*     */
			/*     */
			/* 298 */StringBuffer sQueryTot = new StringBuffer();
			/*     */
			/* 300 */sQueryTot.append(" SELECT TOTAJUSTES ");
			/* 301 */sQueryTot.append("  FROM  ").append(this.schema_database)
					.append("PTO_TBLMSTRDETALLE ");
			/* 302 */sQueryTot
					.append(" WHERE  CUENTA = ? AND SECUENCIA = ? AND IDMOVTO = ? ");
			/* 303 */sQueryTot.append(" AND IDUSUARIO = ? AND REFERENCIA =? ");
			/*     */
			/* 305 */statementTot = connection.prepareStatement(sQueryTot
					.toString());
			/*     */
			/* 307 */statementTot.setString(1, parametrosTO.getCuenta());
			/* 308 */statementTot.setInt(2, parametrosTO.getSecuencia());
			/* 309 */statementTot.setInt(3, 24);
			/* 310 */statementTot.setString(4, "VIBPT01");
			/* 311 */statementTot.setString(5,
					"Asignacion de puntos por antiguedad - CARGA INICIAL");
			/*     */
			/*     */
			/* 314 */resultSetTot = statementTot.executeQuery();
			/*     */
			/* 316 */if (resultSetTot.next()) {
				/* 317 */nPtsAsignar -= resultSetTot.getInt(1);
				/*     */}
			/* 319 */if (nPtsAsignar == 0) {
				/* 320 */folioLiberacionTO
						.agregaMensaje(
								-1,
								"Ya le fue asignado a la cuenta "
										+ parametrosTO.getCuenta()
										+ " (sec. "
										+ parametrosTO.getSecuencia()
										+
										/* 321 */") el numero total de puntos que le corresponde por "
										+ " concepto de antigüedad.");
				/* 322 */return folioLiberacionTO;
				/*     */}
			/*     */
			/* 325 */mensajeTO = actualizaFechaAnt(connection, lFecAltaM2K,
					parametrosTO.getCuenta(), parametrosTO.getSecuencia());
			/*     */
			/* 327 */if (mensajeTO.getIdMensaje() == 0) {
				/* 328 */mensajeTO = actualizaTotales(connection, nPtsAsignar,
						parametrosTO.getCuenta(), parametrosTO.getSecuencia());
				/*     */}
			/*     */
			/*     */
			/* 332 */if (mensajeTO.getIdMensaje() == 0) {
				/* 333 */mensajeTO = puntosDAO.insertaDetalle(connection,
						fechaTransaccion, comentario, 24, nPtsAsignar, null,
						parametrosTO.getCuenta(), parametrosTO.getSecuencia(),
						parametrosTO.getTelefono(), usuario);
				/*     */}
			/*     */
			/*     */
			/*     */
			/* 338 */if (mensajeTO.getIdMensaje() == 0) {
				/* 339 */mensajeTO = puntosDAO.insertaComentarioTMP(connection,
						parametrosTO.getRegion(), parametrosTO.getCuenta(),
						usuario, fechaTransaccion, comentario);
				/*     */}
			/*     */
			/* 342 */if (mensajeTO.getIdMensaje() == 0)
				/* 343 */connection.commit();
			else {
				/* 344 */connection.rollback();
				/*     */}
			/*     */} catch (SQLException e) {
			/* 347 */if (connection != null)
				try {
					connection.rollback();
				} catch (Exception localException26) {
				}
			/* 348 */this.error.info("SQLException.asignaPorAntiguedad:", e);
			/* 349 */throw new CAException(-1,
					"[asignaPorAntiguedad] SQLError: " + e.toString()
							+ "Actualizar Inf", e);
			/*     */} catch (Exception e) {
			/* 351 */this.error.info("Exception.asignaPorAntiguedad:", e);
			/* 352 */throw new CAException(-1, "[asignaPorAntiguedad] Error: "
					+ e.toString() + "Actualizar Inf", e);
			/*     */} finally {
			/* 354 */if (connection != null)
				try {
					connection.setAutoCommit(true);
					connection.close();
					connection = null;
				} catch (Exception localException27) {
				}
			/* 355 */if (resultSet != null)
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception localException28) {
				}
			/* 356 */if (resultSetTot != null)
				try {
					resultSetTot.close();
					resultSetTot = null;
				} catch (Exception localException29) {
				}
			/* 357 */if (statement != null)
				try {
					statement.close();
					statement = null;
				} catch (Exception localException30) {
				}
			/* 358 */if (statementTot != null)
				try {
					statementTot.close();
					statementTot = null;
					/*     */}
				/*     */catch (Exception localException31) {
				}
			/*     */}
		/*     */int nPtsAsignar;
		/* 354 */if (connection != null)
			try {
				connection.setAutoCommit(true);
				connection.close();
				connection = null;
			} catch (Exception localException32) {
			}
		/* 355 */if (resultSet != null)
			try {
				resultSet.close();
				resultSet = null;
			} catch (Exception localException33) {
			}
		/* 356 */if (resultSetTot != null)
			try {
				resultSetTot.close();
				resultSetTot = null;
			} catch (Exception localException34) {
			}
		/* 357 */if (statement != null)
			try {
				statement.close();
				statement = null;
			} catch (Exception localException35) {
			}
		/* 358 */if (statementTot != null)
			try {
				statementTot.close();
				statementTot = null;
				/*     */}
			/*     */catch (Exception localException36) {
			}
		/* 361 */return folioLiberacionTO;
		/*     */}

	/*     */
	/*     */private MensajeTO validaFechaAnt(Connection connection,
			String sCuenta, int iSecuencia, String lFecAltaM2K)
	/*     */throws CAException
	/*     */{
		/* 367 */MensajeTO mensajeTO = null;
		/* 368 */PreparedStatement statement = null;
		/* 369 */ResultSet resultSet = null;
		/*     */
		/* 371 */StringBuffer sQuery = new StringBuffer();
		/*     */
		/* 373 */sQuery.append(" SELECT FECHAANT ");
		/* 374 */sQuery.append("  FROM  ").append(this.schema_database)
				.append("PTO_TBLLINEAS ");
		/* 375 */sQuery.append(" WHERE CUENTA = ? AND SECUENCIA = ? ");
		/*     */try
		/*     */{
			/* 378 */mensajeTO = new MensajeTO();
			/* 379 */connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			/* 380 */statement = connection.prepareStatement(sQuery.toString());
			/*     */
			/* 382 */statement.setString(1, sCuenta);
			/* 383 */statement.setInt(2, iSecuencia);
			/*     */
			/* 385 */resultSet = statement.executeQuery();
			/*     */
			/* 387 */if (resultSet.next()) {
				/* 388 */if (resultSet.getString(1) != null)
				/*     */{
					/* 390 */if (!resultSet.getString(1).equals(lFecAltaM2K)) {
						/* 391 */ArrayList<String> lista = new ArrayList();
						/* 392 */lista.add(resultSet.getString(1));
						/* 393 */lista.add(lFecAltaM2K);
						/* 394 */int indMenor = Utils
								.comparaFechas(lista, 0, 1);
						/* 395 */if (indMenor != 1) {
							/* 396 */mensajeTO
									.agregaMensaje(
											2,
											"El tramite no procede porque la fecha de alta de la  cuenta proporcionada es mayor que la fecha de alta  de la cuenta vigente.");
							/*     */
							/*     */
							/* 399 */return mensajeTO;
							/*     */}
						/*     */}
					/*     */}
				/*     */
				/* 404 */mensajeTO.agregaMensaje(0, "Proceso exitoso");
				/*     */}
			/*     */}
		/*     */catch (SQLException e) {
			/* 408 */this.error.info("Asignacion.validaFechaAnt.SQLException:",
					e);
			/* 409 */throw new CAException(-1,
					"Asignacion.validaFechaAnt.SQLException[" + e.toString()
							+ "]", e);
			/*     */} catch (Exception e) {
			/* 411 */this.error.info("Asignacion.validaFechaAnt.Exception:", e);
			/* 412 */throw new CAException(-1,
					"Asignacion.validaFechaAnt.Error[" + e.toString() + "]", e);
			/*     */}
		/*     */
		/*     */
		/* 416 */return mensajeTO;
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */private MensajeTO actualizaFechaAnt(Connection connection,
			String lFecAltaM2K, String sCuenta, int iSecuencia)
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 5
		/*     */// 3: aconst_null
		/*     */// 4: astore 6
		/*     */// 6: new 61 com/claro/transfer/MensajeTO
		/*     */// 9: dup
		/*     */// 10: invokespecial 63 com/claro/transfer/MensajeTO:<init> ()V
		/*     */// 13: astore 7
		/*     */// 15: aconst_null
		/*     */// 16: astore 8
		/*     */// 18: aload_1
		/*     */// 19: ifnonnull +17 -> 36
		/*     */// 22: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 25: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 28: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 31: astore 5
		/*     */// 33: goto +6 -> 39
		/*     */// 36: aload_1
		/*     */// 37: astore 5
		/*     */// 39: new 121 java/lang/StringBuilder
		/*     */// 42: dup
		/*     */// 43: ldc_w 273
		/*     */// 46: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 49: aload_0
		/*     */// 50: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 53: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 56: ldc_w 498
		/*     */// 59: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 62: ldc_w 277
		/*     */// 65: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 68: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 71: astore 9
		/*     */// 73: new 500 java/sql/Date
		/*     */// 76: dup
		/*     */// 77: getstatic 502 com/claro/util/Constantes:DATEFORMATyyyy_MM_dd
				// Ljava/text/SimpleDateFormat;
		/*     */// 80: aload_2
		/*     */// 81: invokevirtual 506 java/text/SimpleDateFormat:parse
				// (Ljava/lang/String;)Ljava/util/Date;
		/*     */// 84: invokevirtual 512 java/util/Date:getTime ()J
		/*     */// 87: invokespecial 517 java/sql/Date:<init> (J)V
		/*     */// 90: astore 8
		/*     */// 92: aload 5
		/*     */// 94: aload 9
		/*     */// 96: invokeinterface 279 2 0
		/*     */// 101: astore 6
		/*     */// 103: aload 6
		/*     */// 105: iconst_1
		/*     */// 106: aload 8
		/*     */// 108: invokeinterface 520 3 0
		/*     */// 113: aload 6
		/*     */// 115: iconst_2
		/*     */// 116: aload_3
		/*     */// 117: invokeinterface 289 3 0
		/*     */// 122: aload 6
		/*     */// 124: iconst_3
		/*     */// 125: iload 4
		/*     */// 127: invokeinterface 283 3 0
		/*     */// 132: aload 6
		/*     */// 134: invokeinterface 292 1 0
		/*     */// 139: ifle +15 -> 154
		/*     */// 142: aload 7
		/*     */// 144: iconst_0
		/*     */// 145: ldc_w 350
		/*     */// 148: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 151: goto +142 -> 293
		/*     */// 154: aload 7
		/*     */// 156: iconst_1
		/*     */// 157: ldc_w 524
		/*     */// 160: invokevirtual 187
				// com/claro/transfer/MensajeTO:agregaMensaje
				// (ILjava/lang/String;)V
		/*     */// 163: goto +130 -> 293
		/*     */// 166: astore 9
		/*     */// 168: aload_0
		/*     */// 169: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 172: ldc_w 526
		/*     */// 175: aload 9
		/*     */// 177: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 180: new 59 com/claro/exception/CAException
		/*     */// 183: dup
		/*     */// 184: iconst_m1
		/*     */// 185: new 121 java/lang/StringBuilder
		/*     */// 188: dup
		/*     */// 189: ldc_w 528
		/*     */// 192: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 195: aload 9
		/*     */// 197: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 200: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 203: ldc -23
		/*     */// 205: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 208: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 211: aload 9
		/*     */// 213: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 216: athrow
		/*     */// 217: astore 9
		/*     */// 219: aload_0
		/*     */// 220: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 223: ldc_w 530
		/*     */// 226: aload 9
		/*     */// 228: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 231: new 59 com/claro/exception/CAException
		/*     */// 234: dup
		/*     */// 235: iconst_m1
		/*     */// 236: new 121 java/lang/StringBuilder
		/*     */// 239: dup
		/*     */// 240: ldc_w 532
		/*     */// 243: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 246: aload 9
		/*     */// 248: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 251: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 254: ldc -23
		/*     */// 256: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 259: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 262: aload 9
		/*     */// 264: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 267: athrow
		/*     */// 268: astore 10
		/*     */// 270: aload 6
		/*     */// 272: ifnull +18 -> 290
		/*     */// 275: aload 6
		/*     */// 277: invokeinterface 360 1 0
		/*     */// 282: aconst_null
		/*     */// 283: astore 6
		/*     */// 285: goto +5 -> 290
		/*     */// 288: astore 11
		/*     */// 290: aload 10
		/*     */// 292: athrow
		/*     */// 293: aload 6
		/*     */// 295: ifnull +18 -> 313
		/*     */// 298: aload 6
		/*     */// 300: invokeinterface 360 1 0
		/*     */// 305: aconst_null
		/*     */// 306: astore 6
		/*     */// 308: goto +5 -> 313
		/*     */// 311: astore 11
		/*     */// 313: aload 7
		/*     */// 315: areturn
		/*     */// Line number table:
		/*     */// Java source line #420 -> byte code offset #0
		/*     */// Java source line #421 -> byte code offset #3
		/*     */// Java source line #422 -> byte code offset #6
		/*     */// Java source line #423 -> byte code offset #15
		/*     */// Java source line #427 -> byte code offset #18
		/*     */// Java source line #428 -> byte code offset #36
		/*     */// Java source line #430 -> byte code offset #39
		/*     */// Java source line #431 -> byte code offset #62
		/*     */// Java source line #430 -> byte code offset #68
		/*     */// Java source line #433 -> byte code offset #73
		/*     */// Java source line #435 -> byte code offset #92
		/*     */// Java source line #436 -> byte code offset #103
		/*     */// Java source line #437 -> byte code offset #113
		/*     */// Java source line #438 -> byte code offset #122
		/*     */// Java source line #440 -> byte code offset #132
		/*     */// Java source line #441 -> byte code offset #154
		/*     */// Java source line #443 -> byte code offset #166
		/*     */// Java source line #444 -> byte code offset #168
		/*     */// Java source line #445 -> byte code offset #180
		/*     */// Java source line #446 -> byte code offset #217
		/*     */// Java source line #447 -> byte code offset #219
		/*     */// Java source line #448 -> byte code offset #231
		/*     */// Java source line #449 -> byte code offset #268
		/*     */// Java source line #450 -> byte code offset #270
		/*     */// Java source line #452 -> byte code offset #290
		/*     */// Java source line #450 -> byte code offset #293
		/*     */// Java source line #453 -> byte code offset #313
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 316 0 this AsignacionDAO
		/*     */// 0 316 1 connection Connection
		/*     */// 0 316 2 lFecAltaM2K String
		/*     */// 0 316 3 sCuenta String
		/*     */// 0 316 4 iSecuencia int
		/*     */// 1 92 5 lConn Connection
		/*     */// 4 303 6 statement PreparedStatement
		/*     */// 13 301 7 mensajeTO MensajeTO
		/*     */// 16 91 8 fechaAlta java.sql.Date
		/*     */// 71 24 9 UpdateLineas String
		/*     */// 166 46 9 e SQLException
		/*     */// 217 46 9 e Exception
		/*     */// 268 23 10 localObject Object
		/*     */// 288 1 11 localException1 Exception
		/*     */// 311 1 11 localException2 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 18 163 166 java/sql/SQLException
		/*     */// 18 163 217 java/lang/Exception
		/*     */// 18 268 268 finally
		/*     */// 275 285 288 java/lang/Exception
		/*     */// 298 308 311 java/lang/Exception
		/*     */}

	/*     */
	/*     */private MensajeTO actualizaTotales(Connection connection,
			int nPtsAsignar, String sCuenta, int iSecuencia)
	/*     */throws CAException
	/*     */{
		/* 457 */Connection lConn = null;
		/* 458 */MensajeTO mensajeTO = new MensajeTO();
		/* 459 */PreparedStatement statement = null;
		/*     */try
		/*     */{
			/* 462 */String UpdateTotales = "UPDATE " + this.schema_database
					+ "PTO_TBLTOTALES " +
					/* 463 */" SET PUNTOSEXCEDENTES = PUNTOSEXCEDENTES + ?, " +
					/* 464 */" PUNTOSANTIG = PUNTOSANTIG + ? " +
					/* 465 */" WHERE CUENTA = ? AND SECUENCIA = ? ";
			/*     */
			/* 467 */if (connection != null)
				lConn = connection;
			else {
				/* 468 */lConn = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				/*     */}
			/* 470 */statement = lConn.prepareStatement(UpdateTotales);
			/* 471 */statement.setInt(1, nPtsAsignar);
			/* 472 */statement.setInt(2, nPtsAsignar);
			/* 473 */statement.setString(3, sCuenta);
			/* 474 */statement.setInt(4, iSecuencia);
			/*     */
			/* 476 */if (statement.executeUpdate() > 0)
				mensajeTO.agregaMensaje(0, "Proceso Exitoso.");
			else {
				/* 477 */mensajeTO.agregaMensaje(1,
						"No se realizo la actualizacion de los puntos");
				/*     */}
			/*     */} catch (SQLException e) {
			/* 480 */if (connection != null)
				try {
					connection.rollback();
				} catch (Exception localException1) {
				}
			/* 481 */this.error.info("SQLException.actualizaTotales:", e);
			/* 482 */throw new CAException(-1, "[actualizaTotales] SQLError: "
					+ e.toString() + "Actualizar Inf", e);
			/*     */} catch (Exception e) {
			/* 484 */this.error.info("Exception.actualizaTotales:", e);
			/* 485 */throw new CAException(-1, "[actualizaTotales] Error: "
					+ e.toString() + "Actualizar Inf", e);
			/*     */}
		/*     */
		/*     */
		/* 489 */return mensajeTO;
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public boolean validaLineaAsignacion(String Cuenta, String Telefono,
			int region)
	/*     */throws CAException
	/*     */{
		return false;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 4
		/*     */// 3: aconst_null
		/*     */// 4: astore 5
		/*     */// 6: aconst_null
		/*     */// 7: astore 6
		/*     */// 9: iconst_0
		/*     */// 10: istore 7
		/*     */// 12: iconst_0
		/*     */// 13: istore 8
		/*     */// 15: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 18: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 21: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 24: astore 4
		/*     */// 26: new 313 java/lang/StringBuffer
		/*     */// 29: dup
		/*     */// 30: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 33: astore 9
		/*     */// 35: aload 9
		/*     */// 37: ldc_w 553
		/*     */// 40: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 43: aload_0
		/*     */// 44: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 47: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 50: ldc_w 555
		/*     */// 53: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 56: pop
		/*     */// 57: aload 4
		/*     */// 59: aload 9
		/*     */// 61: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 64: invokeinterface 279 2 0
		/*     */// 69: astore 5
		/*     */// 71: aload 5
		/*     */// 73: iconst_1
		/*     */// 74: aload_1
		/*     */// 75: invokeinterface 289 3 0
		/*     */// 80: aload 5
		/*     */// 82: invokeinterface 377 1 0
		/*     */// 87: astore 6
		/*     */// 89: iconst_0
		/*     */// 90: istore 10
		/*     */// 92: aload 6
		/*     */// 94: invokeinterface 381 1 0
		/*     */// 99: ifeq +6 -> 105
		/*     */// 102: iconst_1
		/*     */// 103: istore 10
		/*     */// 105: aload 6
		/*     */// 107: ifnull +13 -> 120
		/*     */// 110: aload 6
		/*     */// 112: invokeinterface 390 1 0
		/*     */// 117: aconst_null
		/*     */// 118: astore 6
		/*     */// 120: aload 5
		/*     */// 122: ifnull +13 -> 135
		/*     */// 125: aload 5
		/*     */// 127: invokeinterface 360 1 0
		/*     */// 132: aconst_null
		/*     */// 133: astore 5
		/*     */// 135: iload 10
		/*     */// 137: ifeq +9 -> 146
		/*     */// 140: iconst_1
		/*     */// 141: istore 7
		/*     */// 143: goto +354 -> 497
		/*     */// 146: new 313 java/lang/StringBuffer
		/*     */// 149: dup
		/*     */// 150: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 153: astore 9
		/*     */// 155: aload 9
		/*     */// 157: ldc_w 557
		/*     */// 160: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 163: pop
		/*     */// 164: aload 9
		/*     */// 166: aload_0
		/*     */// 167: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 170: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 173: ldc_w 368
		/*     */// 176: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 179: pop
		/*     */// 180: aload 9
		/*     */// 182: ldc_w 559
		/*     */// 185: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 188: pop
		/*     */// 189: aload 9
		/*     */// 191: ldc_w 561
		/*     */// 194: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 197: pop
		/*     */// 198: aload 9
		/*     */// 200: ldc_w 563
		/*     */// 203: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 206: pop
		/*     */// 207: aload 9
		/*     */// 209: ldc_w 565
		/*     */// 212: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 215: pop
		/*     */// 216: aload 9
		/*     */// 218: ldc_w 567
		/*     */// 221: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 224: pop
		/*     */// 225: aload 4
		/*     */// 227: aload 9
		/*     */// 229: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 232: invokeinterface 279 2 0
		/*     */// 237: astore 5
		/*     */// 239: aload 5
		/*     */// 241: iconst_1
		/*     */// 242: aload_1
		/*     */// 243: invokeinterface 289 3 0
		/*     */// 248: aload 5
		/*     */// 250: iconst_2
		/*     */// 251: aload_2
		/*     */// 252: invokeinterface 289 3 0
		/*     */// 257: aload 5
		/*     */// 259: iconst_3
		/*     */// 260: bipush 15
		/*     */// 262: invokeinterface 283 3 0
		/*     */// 267: aload 5
		/*     */// 269: invokeinterface 377 1 0
		/*     */// 274: astore 6
		/*     */// 276: aload 6
		/*     */// 278: invokeinterface 381 1 0
		/*     */// 283: ifeq +214 -> 497
		/*     */// 286: aload 6
		/*     */// 288: ldc_w 569
		/*     */// 291: invokeinterface 571 2 0
		/*     */// 296: istore 8
		/*     */// 298: iload_3
		/*     */// 299: bipush 9
		/*     */// 301: if_icmpne +15 -> 316
		/*     */// 304: iload 8
		/*     */// 306: iconst_1
		/*     */// 307: if_icmpge +190 -> 497
		/*     */// 310: iconst_1
		/*     */// 311: istore 7
		/*     */// 313: goto +184 -> 497
		/*     */// 316: iload 8
		/*     */// 318: iconst_3
		/*     */// 319: if_icmpge +178 -> 497
		/*     */// 322: iconst_1
		/*     */// 323: istore 7
		/*     */// 325: goto +172 -> 497
		/*     */// 328: astore 9
		/*     */// 330: aload_0
		/*     */// 331: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 334: ldc_w 573
		/*     */// 337: aload 9
		/*     */// 339: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 342: new 59 com/claro/exception/CAException
		/*     */// 345: dup
		/*     */// 346: iconst_m1
		/*     */// 347: new 121 java/lang/StringBuilder
		/*     */// 350: dup
		/*     */// 351: ldc_w 575
		/*     */// 354: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 357: aload 9
		/*     */// 359: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 362: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 365: ldc_w 356
		/*     */// 368: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 371: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 374: aload 9
		/*     */// 376: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 379: athrow
		/*     */// 380: astore 9
		/*     */// 382: aload_0
		/*     */// 383: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 386: ldc_w 577
		/*     */// 389: aload 9
		/*     */// 391: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 394: new 59 com/claro/exception/CAException
		/*     */// 397: dup
		/*     */// 398: iconst_m1
		/*     */// 399: new 121 java/lang/StringBuilder
		/*     */// 402: dup
		/*     */// 403: ldc_w 579
		/*     */// 406: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 409: aload 9
		/*     */// 411: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 414: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 417: ldc_w 356
		/*     */// 420: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 423: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 426: aload 9
		/*     */// 428: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 431: athrow
		/*     */// 432: astore 11
		/*     */// 434: aload 5
		/*     */// 436: ifnull +18 -> 454
		/*     */// 439: aload 5
		/*     */// 441: invokeinterface 360 1 0
		/*     */// 446: aconst_null
		/*     */// 447: astore 5
		/*     */// 449: goto +5 -> 454
		/*     */// 452: astore 12
		/*     */// 454: aload 6
		/*     */// 456: ifnull +18 -> 474
		/*     */// 459: aload 6
		/*     */// 461: invokeinterface 390 1 0
		/*     */// 466: aconst_null
		/*     */// 467: astore 6
		/*     */// 469: goto +5 -> 474
		/*     */// 472: astore 12
		/*     */// 474: aload 4
		/*     */// 476: ifnull +18 -> 494
		/*     */// 479: aload 4
		/*     */// 481: invokeinterface 112 1 0
		/*     */// 486: aconst_null
		/*     */// 487: astore 4
		/*     */// 489: goto +5 -> 494
		/*     */// 492: astore 12
		/*     */// 494: aload 11
		/*     */// 496: athrow
		/*     */// 497: aload 5
		/*     */// 499: ifnull +18 -> 517
		/*     */// 502: aload 5
		/*     */// 504: invokeinterface 360 1 0
		/*     */// 509: aconst_null
		/*     */// 510: astore 5
		/*     */// 512: goto +5 -> 517
		/*     */// 515: astore 12
		/*     */// 517: aload 6
		/*     */// 519: ifnull +18 -> 537
		/*     */// 522: aload 6
		/*     */// 524: invokeinterface 390 1 0
		/*     */// 529: aconst_null
		/*     */// 530: astore 6
		/*     */// 532: goto +5 -> 537
		/*     */// 535: astore 12
		/*     */// 537: aload 4
		/*     */// 539: ifnull +18 -> 557
		/*     */// 542: aload 4
		/*     */// 544: invokeinterface 112 1 0
		/*     */// 549: aconst_null
		/*     */// 550: astore 4
		/*     */// 552: goto +5 -> 557
		/*     */// 555: astore 12
		/*     */// 557: iload 7
		/*     */// 559: ireturn
		/*     */// Line number table:
		/*     */// Java source line #497 -> byte code offset #0
		/*     */// Java source line #498 -> byte code offset #3
		/*     */// Java source line #499 -> byte code offset #6
		/*     */// Java source line #500 -> byte code offset #9
		/*     */// Java source line #501 -> byte code offset #12
		/*     */// Java source line #503 -> byte code offset #15
		/*     */// Java source line #504 -> byte code offset #26
		/*     */// Java source line #507 -> byte code offset #35
		/*     */// Java source line #509 -> byte code offset #57
		/*     */// Java source line #510 -> byte code offset #71
		/*     */// Java source line #512 -> byte code offset #80
		/*     */// Java source line #514 -> byte code offset #89
		/*     */// Java source line #515 -> byte code offset #92
		/*     */// Java source line #516 -> byte code offset #102
		/*     */// Java source line #519 -> byte code offset #105
		/*     */// Java source line #520 -> byte code offset #120
		/*     */// Java source line #522 -> byte code offset #135
		/*     */// Java source line #523 -> byte code offset #140
		/*     */// Java source line #526 -> byte code offset #146
		/*     */// Java source line #528 -> byte code offset #155
		/*     */// Java source line #529 -> byte code offset #164
		/*     */// Java source line #530 -> byte code offset #180
		/*     */// Java source line #531 -> byte code offset #189
		/*     */// Java source line #532 -> byte code offset #198
		/*     */// Java source line #533 -> byte code offset #207
		/*     */// Java source line #534 -> byte code offset #216
		/*     */// Java source line #536 -> byte code offset #225
		/*     */// Java source line #537 -> byte code offset #239
		/*     */// Java source line #538 -> byte code offset #248
		/*     */// Java source line #539 -> byte code offset #257
		/*     */// Java source line #540 -> byte code offset #267
		/*     */// Java source line #542 -> byte code offset #276
		/*     */// Java source line #543 -> byte code offset #286
		/*     */// Java source line #545 -> byte code offset #298
		/*     */// Java source line #546 -> byte code offset #304
		/*     */// Java source line #547 -> byte code offset #310
		/*     */// Java source line #550 -> byte code offset #316
		/*     */// Java source line #551 -> byte code offset #322
		/*     */// Java source line #556 -> byte code offset #328
		/*     */// Java source line #557 -> byte code offset #330
		/*     */// Java source line #558 -> byte code offset #342
		/*     */// Java source line #559 -> byte code offset #380
		/*     */// Java source line #560 -> byte code offset #382
		/*     */// Java source line #561 -> byte code offset #394
		/*     */// Java source line #562 -> byte code offset #432
		/*     */// Java source line #563 -> byte code offset #434
		/*     */// Java source line #564 -> byte code offset #454
		/*     */// Java source line #565 -> byte code offset #474
		/*     */// Java source line #566 -> byte code offset #494
		/*     */// Java source line #563 -> byte code offset #497
		/*     */// Java source line #564 -> byte code offset #517
		/*     */// Java source line #565 -> byte code offset #537
		/*     */// Java source line #567 -> byte code offset #557
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 560 0 this AsignacionDAO
		/*     */// 0 560 1 Cuenta String
		/*     */// 0 560 2 Telefono String
		/*     */// 0 560 3 region int
		/*     */// 1 550 4 conn Connection
		/*     */// 4 507 5 statement PreparedStatement
		/*     */// 7 524 6 resultSet ResultSet
		/*     */// 10 548 7 bandera boolean
		/*     */// 13 304 8 numAsignaciones int
		/*     */// 33 195 9 query StringBuffer
		/*     */// 328 47 9 e SQLException
		/*     */// 380 47 9 e Exception
		/*     */// 90 46 10 isLineaPruebas boolean
		/*     */// 432 63 11 localObject Object
		/*     */// 452 1 12 localException1 Exception
		/*     */// 472 1 12 localException2 Exception
		/*     */// 492 1 12 localException3 Exception
		/*     */// 515 1 12 localException4 Exception
		/*     */// 535 1 12 localException5 Exception
		/*     */// 555 1 12 localException6 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 15 325 328 java/sql/SQLException
		/*     */// 15 325 380 java/lang/Exception
		/*     */// 15 432 432 finally
		/*     */// 439 449 452 java/lang/Exception
		/*     */// 459 469 472 java/lang/Exception
		/*     */// 479 489 492 java/lang/Exception
		/*     */// 502 512 515 java/lang/Exception
		/*     */// 522 532 535 java/lang/Exception
		/*     */// 542 552 555 java/lang/Exception
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public int obtienePuntosMaxAsignar(int idPerfilN)
	/*     */throws CAException
	/*     */{
		return idPerfilN;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_2
		/*     */// 2: aconst_null
		/*     */// 3: astore_3
		/*     */// 4: aconst_null
		/*     */// 5: astore 4
		/*     */// 7: iconst_0
		/*     */// 8: istore 5
		/*     */// 10: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 13: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 16: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 19: astore_2
		/*     */// 20: new 313 java/lang/StringBuffer
		/*     */// 23: dup
		/*     */// 24: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 27: astore 6
		/*     */// 29: aload 6
		/*     */// 31: ldc_w 591
		/*     */// 34: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 37: pop
		/*     */// 38: aload 6
		/*     */// 40: aload_0
		/*     */// 41: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 44: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 47: ldc_w 593
		/*     */// 50: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 53: pop
		/*     */// 54: aload 6
		/*     */// 56: ldc_w 595
		/*     */// 59: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 62: pop
		/*     */// 63: aload_2
		/*     */// 64: aload 6
		/*     */// 66: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 69: invokeinterface 279 2 0
		/*     */// 74: astore_3
		/*     */// 75: aload_3
		/*     */// 76: iconst_1
		/*     */// 77: iload_1
		/*     */// 78: invokeinterface 283 3 0
		/*     */// 83: aload_3
		/*     */// 84: invokeinterface 377 1 0
		/*     */// 89: astore 4
		/*     */// 91: aload 4
		/*     */// 93: invokeinterface 381 1 0
		/*     */// 98: ifeq +181 -> 279
		/*     */// 101: aload 4
		/*     */// 103: ldc_w 597
		/*     */// 106: invokeinterface 571 2 0
		/*     */// 111: istore 5
		/*     */// 113: goto +166 -> 279
		/*     */// 116: astore 6
		/*     */// 118: aload_0
		/*     */// 119: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 122: ldc_w 599
		/*     */// 125: aload 6
		/*     */// 127: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 130: new 59 com/claro/exception/CAException
		/*     */// 133: dup
		/*     */// 134: iconst_m1
		/*     */// 135: new 121 java/lang/StringBuilder
		/*     */// 138: dup
		/*     */// 139: ldc_w 601
		/*     */// 142: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 145: aload 6
		/*     */// 147: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 150: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 153: ldc_w 356
		/*     */// 156: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 159: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 162: aload 6
		/*     */// 164: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 167: athrow
		/*     */// 168: astore 6
		/*     */// 170: aload_0
		/*     */// 171: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 174: ldc_w 603
		/*     */// 177: aload 6
		/*     */// 179: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 182: new 59 com/claro/exception/CAException
		/*     */// 185: dup
		/*     */// 186: iconst_m1
		/*     */// 187: new 121 java/lang/StringBuilder
		/*     */// 190: dup
		/*     */// 191: ldc_w 605
		/*     */// 194: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 197: aload 6
		/*     */// 199: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 202: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 205: ldc_w 356
		/*     */// 208: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 211: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 214: aload 6
		/*     */// 216: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 219: athrow
		/*     */// 220: astore 7
		/*     */// 222: aload_3
		/*     */// 223: ifnull +16 -> 239
		/*     */// 226: aload_3
		/*     */// 227: invokeinterface 360 1 0
		/*     */// 232: aconst_null
		/*     */// 233: astore_3
		/*     */// 234: goto +5 -> 239
		/*     */// 237: astore 8
		/*     */// 239: aload 4
		/*     */// 241: ifnull +18 -> 259
		/*     */// 244: aload 4
		/*     */// 246: invokeinterface 390 1 0
		/*     */// 251: aconst_null
		/*     */// 252: astore 4
		/*     */// 254: goto +5 -> 259
		/*     */// 257: astore 8
		/*     */// 259: aload_2
		/*     */// 260: ifnull +16 -> 276
		/*     */// 263: aload_2
		/*     */// 264: invokeinterface 112 1 0
		/*     */// 269: aconst_null
		/*     */// 270: astore_2
		/*     */// 271: goto +5 -> 276
		/*     */// 274: astore 8
		/*     */// 276: aload 7
		/*     */// 278: athrow
		/*     */// 279: aload_3
		/*     */// 280: ifnull +16 -> 296
		/*     */// 283: aload_3
		/*     */// 284: invokeinterface 360 1 0
		/*     */// 289: aconst_null
		/*     */// 290: astore_3
		/*     */// 291: goto +5 -> 296
		/*     */// 294: astore 8
		/*     */// 296: aload 4
		/*     */// 298: ifnull +18 -> 316
		/*     */// 301: aload 4
		/*     */// 303: invokeinterface 390 1 0
		/*     */// 308: aconst_null
		/*     */// 309: astore 4
		/*     */// 311: goto +5 -> 316
		/*     */// 314: astore 8
		/*     */// 316: aload_2
		/*     */// 317: ifnull +16 -> 333
		/*     */// 320: aload_2
		/*     */// 321: invokeinterface 112 1 0
		/*     */// 326: aconst_null
		/*     */// 327: astore_2
		/*     */// 328: goto +5 -> 333
		/*     */// 331: astore 8
		/*     */// 333: iload 5
		/*     */// 335: ireturn
		/*     */// Line number table:
		/*     */// Java source line #575 -> byte code offset #0
		/*     */// Java source line #576 -> byte code offset #2
		/*     */// Java source line #577 -> byte code offset #4
		/*     */// Java source line #578 -> byte code offset #7
		/*     */// Java source line #581 -> byte code offset #10
		/*     */// Java source line #582 -> byte code offset #20
		/*     */// Java source line #583 -> byte code offset #29
		/*     */// Java source line #584 -> byte code offset #38
		/*     */// Java source line #585 -> byte code offset #54
		/*     */// Java source line #587 -> byte code offset #63
		/*     */// Java source line #588 -> byte code offset #75
		/*     */// Java source line #589 -> byte code offset #83
		/*     */// Java source line #591 -> byte code offset #91
		/*     */// Java source line #592 -> byte code offset #101
		/*     */// Java source line #594 -> byte code offset #116
		/*     */// Java source line #595 -> byte code offset #118
		/*     */// Java source line #596 -> byte code offset #130
		/*     */// Java source line #597 -> byte code offset #168
		/*     */// Java source line #598 -> byte code offset #170
		/*     */// Java source line #599 -> byte code offset #182
		/*     */// Java source line #600 -> byte code offset #220
		/*     */// Java source line #601 -> byte code offset #222
		/*     */// Java source line #602 -> byte code offset #239
		/*     */// Java source line #603 -> byte code offset #259
		/*     */// Java source line #604 -> byte code offset #276
		/*     */// Java source line #601 -> byte code offset #279
		/*     */// Java source line #602 -> byte code offset #296
		/*     */// Java source line #603 -> byte code offset #316
		/*     */// Java source line #605 -> byte code offset #333
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 336 0 this AsignacionDAO
		/*     */// 0 336 1 idPerfilN int
		/*     */// 1 327 2 conn Connection
		/*     */// 3 288 3 statement PreparedStatement
		/*     */// 5 305 4 resultSet ResultSet
		/*     */// 8 326 5 puntosMaximos int
		/*     */// 27 38 6 query StringBuffer
		/*     */// 116 47 6 e SQLException
		/*     */// 168 47 6 e Exception
		/*     */// 220 57 7 localObject Object
		/*     */// 237 1 8 localException1 Exception
		/*     */// 257 1 8 localException2 Exception
		/*     */// 274 1 8 localException3 Exception
		/*     */// 294 1 8 localException4 Exception
		/*     */// 314 1 8 localException5 Exception
		/*     */// 331 1 8 localException6 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 10 113 116 java/sql/SQLException
		/*     */// 10 113 168 java/lang/Exception
		/*     */// 10 220 220 finally
		/*     */// 226 234 237 java/lang/Exception
		/*     */// 244 254 257 java/lang/Exception
		/*     */// 263 271 274 java/lang/Exception
		/*     */// 283 291 294 java/lang/Exception
		/*     */// 301 311 314 java/lang/Exception
		/*     */// 320 328 331 java/lang/Exception
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public ArrayList<com.claro.transfer.PerfilTO> obtienePerfilesValidos()
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_1
		/*     */// 2: aconst_null
		/*     */// 3: astore_2
		/*     */// 4: aconst_null
		/*     */// 5: astore_3
		/*     */// 6: new 471 java/util/ArrayList
		/*     */// 9: dup
		/*     */// 10: iconst_0
		/*     */// 11: invokespecial 613 java/util/ArrayList:<init> (I)V
		/*     */// 14: astore 4
		/*     */// 16: aconst_null
		/*     */// 17: astore 5
		/*     */// 19: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 22: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 25: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 28: astore_1
		/*     */// 29: new 313 java/lang/StringBuffer
		/*     */// 32: dup
		/*     */// 33: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 36: astore 6
		/*     */// 38: aload 6
		/*     */// 40: ldc_w 616
		/*     */// 43: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 46: pop
		/*     */// 47: aload 6
		/*     */// 49: aload_0
		/*     */// 50: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 53: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 56: ldc_w 618
		/*     */// 59: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 62: pop
		/*     */// 63: aload 6
		/*     */// 65: aload_0
		/*     */// 66: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 69: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 72: ldc_w 620
		/*     */// 75: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 78: pop
		/*     */// 79: aload 6
		/*     */// 81: ldc_w 622
		/*     */// 84: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 87: pop
		/*     */// 88: aload 6
		/*     */// 90: ldc_w 624
		/*     */// 93: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 96: pop
		/*     */// 97: aload 6
		/*     */// 99: ldc_w 626
		/*     */// 102: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 105: pop
		/*     */// 106: aload 6
		/*     */// 108: ldc_w 628
		/*     */// 111: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 114: pop
		/*     */// 115: aload 6
		/*     */// 117: aload_0
		/*     */// 118: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 121: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 124: ldc_w 618
		/*     */// 127: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 130: pop
		/*     */// 131: aload 6
		/*     */// 133: aload_0
		/*     */// 134: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 137: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 140: ldc_w 620
		/*     */// 143: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 146: pop
		/*     */// 147: aload 6
		/*     */// 149: ldc_w 630
		/*     */// 152: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 155: pop
		/*     */// 156: aload 6
		/*     */// 158: ldc_w 624
		/*     */// 161: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 164: pop
		/*     */// 165: aload 6
		/*     */// 167: ldc_w 632
		/*     */// 170: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 173: pop
		/*     */// 174: aload 6
		/*     */// 176: ldc_w 634
		/*     */// 179: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 182: pop
		/*     */// 183: aload_1
		/*     */// 184: aload 6
		/*     */// 186: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 189: invokeinterface 279 2 0
		/*     */// 194: astore_2
		/*     */// 195: aload_2
		/*     */// 196: invokeinterface 377 1 0
		/*     */// 201: astore_3
		/*     */// 202: goto +76 -> 278
		/*     */// 205: new 636 com/claro/transfer/PerfilTO
		/*     */// 208: dup
		/*     */// 209: invokespecial 638 com/claro/transfer/PerfilTO:<init> ()V
		/*     */// 212: astore 5
		/*     */// 214: aload 5
		/*     */// 216: aload_3
		/*     */// 217: ldc_w 639
		/*     */// 220: invokeinterface 571 2 0
		/*     */// 225: invokevirtual 641 com/claro/transfer/PerfilTO:setIdPerfilN
				// (I)V
		/*     */// 228: aload 5
		/*     */// 230: aload_3
		/*     */// 231: ldc_w 644
		/*     */// 234: invokeinterface 646 2 0
		/*     */// 239: invokevirtual 648 com/claro/transfer/PerfilTO:setIdPuesto
				// (Ljava/lang/String;)V
		/*     */// 242: aload 5
		/*     */// 244: aload_3
		/*     */// 245: ldc_w 651
		/*     */// 248: invokeinterface 646 2 0
		/*     */// 253: invokevirtual 653
				// com/claro/transfer/PerfilTO:setDescripcion
				// (Ljava/lang/String;)V
		/*     */// 256: aload 5
		/*     */// 258: aload_3
		/*     */// 259: ldc_w 656
		/*     */// 262: invokeinterface 571 2 0
		/*     */// 267: invokevirtual 658 com/claro/transfer/PerfilTO:setRegion
				// (I)V
		/*     */// 270: aload 4
		/*     */// 272: aload 5
		/*     */// 274: invokevirtual 474 java/util/ArrayList:add
				// (Ljava/lang/Object;)Z
		/*     */// 277: pop
		/*     */// 278: aload_3
		/*     */// 279: invokeinterface 381 1 0
		/*     */// 284: ifne -79 -> 205
		/*     */// 287: goto +163 -> 450
		/*     */// 290: astore 6
		/*     */// 292: aload_0
		/*     */// 293: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 296: ldc_w 661
		/*     */// 299: aload 6
		/*     */// 301: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 304: new 59 com/claro/exception/CAException
		/*     */// 307: dup
		/*     */// 308: iconst_m1
		/*     */// 309: new 121 java/lang/StringBuilder
		/*     */// 312: dup
		/*     */// 313: ldc_w 663
		/*     */// 316: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 319: aload 6
		/*     */// 321: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 324: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 327: ldc_w 356
		/*     */// 330: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 333: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 336: aload 6
		/*     */// 338: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 341: athrow
		/*     */// 342: astore 6
		/*     */// 344: aload_0
		/*     */// 345: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 348: ldc_w 665
		/*     */// 351: aload 6
		/*     */// 353: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 356: new 59 com/claro/exception/CAException
		/*     */// 359: dup
		/*     */// 360: iconst_m1
		/*     */// 361: new 121 java/lang/StringBuilder
		/*     */// 364: dup
		/*     */// 365: ldc_w 667
		/*     */// 368: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 371: aload 6
		/*     */// 373: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 376: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 379: ldc_w 356
		/*     */// 382: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 385: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 388: aload 6
		/*     */// 390: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 393: athrow
		/*     */// 394: astore 7
		/*     */// 396: aload_2
		/*     */// 397: ifnull +16 -> 413
		/*     */// 400: aload_2
		/*     */// 401: invokeinterface 360 1 0
		/*     */// 406: aconst_null
		/*     */// 407: astore_2
		/*     */// 408: goto +5 -> 413
		/*     */// 411: astore 8
		/*     */// 413: aload_3
		/*     */// 414: ifnull +16 -> 430
		/*     */// 417: aload_3
		/*     */// 418: invokeinterface 390 1 0
		/*     */// 423: aconst_null
		/*     */// 424: astore_3
		/*     */// 425: goto +5 -> 430
		/*     */// 428: astore 8
		/*     */// 430: aload_1
		/*     */// 431: ifnull +16 -> 447
		/*     */// 434: aload_1
		/*     */// 435: invokeinterface 112 1 0
		/*     */// 440: aconst_null
		/*     */// 441: astore_1
		/*     */// 442: goto +5 -> 447
		/*     */// 445: astore 8
		/*     */// 447: aload 7
		/*     */// 449: athrow
		/*     */// 450: aload_2
		/*     */// 451: ifnull +16 -> 467
		/*     */// 454: aload_2
		/*     */// 455: invokeinterface 360 1 0
		/*     */// 460: aconst_null
		/*     */// 461: astore_2
		/*     */// 462: goto +5 -> 467
		/*     */// 465: astore 8
		/*     */// 467: aload_3
		/*     */// 468: ifnull +16 -> 484
		/*     */// 471: aload_3
		/*     */// 472: invokeinterface 390 1 0
		/*     */// 477: aconst_null
		/*     */// 478: astore_3
		/*     */// 479: goto +5 -> 484
		/*     */// 482: astore 8
		/*     */// 484: aload_1
		/*     */// 485: ifnull +16 -> 501
		/*     */// 488: aload_1
		/*     */// 489: invokeinterface 112 1 0
		/*     */// 494: aconst_null
		/*     */// 495: astore_1
		/*     */// 496: goto +5 -> 501
		/*     */// 499: astore 8
		/*     */// 501: aload 4
		/*     */// 503: areturn
		/*     */// Line number table:
		/*     */// Java source line #612 -> byte code offset #0
		/*     */// Java source line #613 -> byte code offset #2
		/*     */// Java source line #614 -> byte code offset #4
		/*     */// Java source line #616 -> byte code offset #6
		/*     */// Java source line #617 -> byte code offset #16
		/*     */// Java source line #620 -> byte code offset #19
		/*     */// Java source line #621 -> byte code offset #29
		/*     */// Java source line #622 -> byte code offset #38
		/*     */// Java source line #623 -> byte code offset #47
		/*     */// Java source line #624 -> byte code offset #63
		/*     */// Java source line #625 -> byte code offset #79
		/*     */// Java source line #626 -> byte code offset #88
		/*     */// Java source line #627 -> byte code offset #97
		/*     */// Java source line #628 -> byte code offset #106
		/*     */// Java source line #629 -> byte code offset #115
		/*     */// Java source line #630 -> byte code offset #131
		/*     */// Java source line #631 -> byte code offset #147
		/*     */// Java source line #632 -> byte code offset #156
		/*     */// Java source line #633 -> byte code offset #165
		/*     */// Java source line #634 -> byte code offset #174
		/*     */// Java source line #636 -> byte code offset #183
		/*     */// Java source line #637 -> byte code offset #195
		/*     */// Java source line #639 -> byte code offset #202
		/*     */// Java source line #640 -> byte code offset #205
		/*     */// Java source line #642 -> byte code offset #214
		/*     */// Java source line #643 -> byte code offset #228
		/*     */// Java source line #644 -> byte code offset #242
		/*     */// Java source line #645 -> byte code offset #256
		/*     */// Java source line #647 -> byte code offset #270
		/*     */// Java source line #639 -> byte code offset #278
		/*     */// Java source line #649 -> byte code offset #290
		/*     */// Java source line #650 -> byte code offset #292
		/*     */// Java source line #651 -> byte code offset #304
		/*     */// Java source line #652 -> byte code offset #342
		/*     */// Java source line #653 -> byte code offset #344
		/*     */// Java source line #654 -> byte code offset #356
		/*     */// Java source line #655 -> byte code offset #394
		/*     */// Java source line #656 -> byte code offset #396
		/*     */// Java source line #657 -> byte code offset #413
		/*     */// Java source line #658 -> byte code offset #430
		/*     */// Java source line #659 -> byte code offset #447
		/*     */// Java source line #656 -> byte code offset #450
		/*     */// Java source line #657 -> byte code offset #467
		/*     */// Java source line #658 -> byte code offset #484
		/*     */// Java source line #660 -> byte code offset #501
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 504 0 this AsignacionDAO
		/*     */// 1 495 1 conn Connection
		/*     */// 3 459 2 statement PreparedStatement
		/*     */// 5 474 3 resultSet ResultSet
		/*     */// 14 488 4 perfilesLst ArrayList<com.claro.transfer.PerfilTO>
		/*     */// 17 256 5 perfilTO com.claro.transfer.PerfilTO
		/*     */// 36 149 6 query StringBuffer
		/*     */// 290 47 6 e SQLException
		/*     */// 342 47 6 e Exception
		/*     */// 394 54 7 localObject Object
		/*     */// 411 1 8 localException1 Exception
		/*     */// 428 1 8 localException2 Exception
		/*     */// 445 1 8 localException3 Exception
		/*     */// 465 1 8 localException4 Exception
		/*     */// 482 1 8 localException5 Exception
		/*     */// 499 1 8 localException6 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 19 287 290 java/sql/SQLException
		/*     */// 19 287 342 java/lang/Exception
		/*     */// 19 394 394 finally
		/*     */// 400 408 411 java/lang/Exception
		/*     */// 417 425 428 java/lang/Exception
		/*     */// 434 442 445 java/lang/Exception
		/*     */// 454 462 465 java/lang/Exception
		/*     */// 471 479 482 java/lang/Exception
		/*     */// 488 496 499 java/lang/Exception
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public ArrayList<com.claro.transfer.PerfilTO> obtienePerfilesAsignacion(
			int idPerfilN, int numPtosMaximo)
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_3
		/*     */// 2: aconst_null
		/*     */// 3: astore 4
		/*     */// 5: aconst_null
		/*     */// 6: astore 5
		/*     */// 8: new 471 java/util/ArrayList
		/*     */// 11: dup
		/*     */// 12: iconst_0
		/*     */// 13: invokespecial 613 java/util/ArrayList:<init> (I)V
		/*     */// 16: astore 6
		/*     */// 18: aconst_null
		/*     */// 19: astore 7
		/*     */// 21: iconst_1
		/*     */// 22: istore 8
		/*     */// 24: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 27: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 30: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 33: astore_3
		/*     */// 34: new 313 java/lang/StringBuffer
		/*     */// 37: dup
		/*     */// 38: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 41: astore 9
		/*     */// 43: aload 9
		/*     */// 45: ldc_w 676
		/*     */// 48: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 51: pop
		/*     */// 52: aload 9
		/*     */// 54: aload_0
		/*     */// 55: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 58: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 61: ldc_w 678
		/*     */// 64: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 67: pop
		/*     */// 68: aload 9
		/*     */// 70: aload_0
		/*     */// 71: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 74: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 77: ldc_w 680
		/*     */// 80: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 83: pop
		/*     */// 84: aload 9
		/*     */// 86: ldc_w 682
		/*     */// 89: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 92: pop
		/*     */// 93: iload_1
		/*     */// 94: iconst_m1
		/*     */// 95: if_icmpeq +12 -> 107
		/*     */// 98: aload 9
		/*     */// 100: ldc_w 684
		/*     */// 103: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 106: pop
		/*     */// 107: iload_2
		/*     */// 108: ifeq +12 -> 120
		/*     */// 111: aload 9
		/*     */// 113: ldc_w 686
		/*     */// 116: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 119: pop
		/*     */// 120: aload 9
		/*     */// 122: ldc_w 634
		/*     */// 125: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 128: pop
		/*     */// 129: aload_3
		/*     */// 130: aload 9
		/*     */// 132: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 135: invokeinterface 279 2 0
		/*     */// 140: astore 4
		/*     */// 142: iload_1
		/*     */// 143: iconst_m1
		/*     */// 144: if_icmpeq +16 -> 160
		/*     */// 147: aload 4
		/*     */// 149: iload 8
		/*     */// 151: iload_1
		/*     */// 152: invokeinterface 283 3 0
		/*     */// 157: iinc 8 1
		/*     */// 160: iload_2
		/*     */// 161: ifeq +16 -> 177
		/*     */// 164: aload 4
		/*     */// 166: iload 8
		/*     */// 168: iload_2
		/*     */// 169: invokeinterface 283 3 0
		/*     */// 174: iinc 8 1
		/*     */// 177: aload 4
		/*     */// 179: invokeinterface 377 1 0
		/*     */// 184: astore 5
		/*     */// 186: goto +95 -> 281
		/*     */// 189: new 636 com/claro/transfer/PerfilTO
		/*     */// 192: dup
		/*     */// 193: invokespecial 638 com/claro/transfer/PerfilTO:<init> ()V
		/*     */// 196: astore 7
		/*     */// 198: aload 7
		/*     */// 200: aload 5
		/*     */// 202: ldc_w 639
		/*     */// 205: invokeinterface 571 2 0
		/*     */// 210: invokevirtual 641 com/claro/transfer/PerfilTO:setIdPerfilN
				// (I)V
		/*     */// 213: aload 7
		/*     */// 215: aload 5
		/*     */// 217: ldc_w 644
		/*     */// 220: invokeinterface 646 2 0
		/*     */// 225: invokevirtual 648 com/claro/transfer/PerfilTO:setIdPuesto
				// (Ljava/lang/String;)V
		/*     */// 228: aload 7
		/*     */// 230: aload 5
		/*     */// 232: ldc_w 651
		/*     */// 235: invokeinterface 646 2 0
		/*     */// 240: invokevirtual 653
				// com/claro/transfer/PerfilTO:setDescripcion
				// (Ljava/lang/String;)V
		/*     */// 243: aload 7
		/*     */// 245: aload 5
		/*     */// 247: ldc_w 656
		/*     */// 250: invokeinterface 571 2 0
		/*     */// 255: invokevirtual 658 com/claro/transfer/PerfilTO:setRegion
				// (I)V
		/*     */// 258: aload 7
		/*     */// 260: aload 5
		/*     */// 262: ldc_w 597
		/*     */// 265: invokeinterface 571 2 0
		/*     */// 270: invokevirtual 688
				// com/claro/transfer/PerfilTO:setNivelAutorizacion (I)V
		/*     */// 273: aload 6
		/*     */// 275: aload 7
		/*     */// 277: invokevirtual 474 java/util/ArrayList:add
				// (Ljava/lang/Object;)Z
		/*     */// 280: pop
		/*     */// 281: aload 5
		/*     */// 283: invokeinterface 381 1 0
		/*     */// 288: ifne -99 -> 189
		/*     */// 291: goto +169 -> 460
		/*     */// 294: astore 9
		/*     */// 296: aload_0
		/*     */// 297: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 300: ldc_w 691
		/*     */// 303: aload 9
		/*     */// 305: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 308: new 59 com/claro/exception/CAException
		/*     */// 311: dup
		/*     */// 312: iconst_m1
		/*     */// 313: new 121 java/lang/StringBuilder
		/*     */// 316: dup
		/*     */// 317: ldc_w 693
		/*     */// 320: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 323: aload 9
		/*     */// 325: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 328: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 331: ldc_w 356
		/*     */// 334: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 337: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 340: aload 9
		/*     */// 342: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 345: athrow
		/*     */// 346: astore 9
		/*     */// 348: aload_0
		/*     */// 349: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 352: ldc_w 695
		/*     */// 355: aload 9
		/*     */// 357: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 360: new 59 com/claro/exception/CAException
		/*     */// 363: dup
		/*     */// 364: iconst_m1
		/*     */// 365: new 121 java/lang/StringBuilder
		/*     */// 368: dup
		/*     */// 369: ldc_w 697
		/*     */// 372: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 375: aload 9
		/*     */// 377: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 380: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 383: ldc_w 356
		/*     */// 386: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 389: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 392: aload 9
		/*     */// 394: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 397: athrow
		/*     */// 398: astore 10
		/*     */// 400: aload 4
		/*     */// 402: ifnull +18 -> 420
		/*     */// 405: aload 4
		/*     */// 407: invokeinterface 360 1 0
		/*     */// 412: aconst_null
		/*     */// 413: astore 4
		/*     */// 415: goto +5 -> 420
		/*     */// 418: astore 11
		/*     */// 420: aload 5
		/*     */// 422: ifnull +18 -> 440
		/*     */// 425: aload 5
		/*     */// 427: invokeinterface 390 1 0
		/*     */// 432: aconst_null
		/*     */// 433: astore 5
		/*     */// 435: goto +5 -> 440
		/*     */// 438: astore 11
		/*     */// 440: aload_3
		/*     */// 441: ifnull +16 -> 457
		/*     */// 444: aload_3
		/*     */// 445: invokeinterface 112 1 0
		/*     */// 450: aconst_null
		/*     */// 451: astore_3
		/*     */// 452: goto +5 -> 457
		/*     */// 455: astore 11
		/*     */// 457: aload 10
		/*     */// 459: athrow
		/*     */// 460: aload 4
		/*     */// 462: ifnull +18 -> 480
		/*     */// 465: aload 4
		/*     */// 467: invokeinterface 360 1 0
		/*     */// 472: aconst_null
		/*     */// 473: astore 4
		/*     */// 475: goto +5 -> 480
		/*     */// 478: astore 11
		/*     */// 480: aload 5
		/*     */// 482: ifnull +18 -> 500
		/*     */// 485: aload 5
		/*     */// 487: invokeinterface 390 1 0
		/*     */// 492: aconst_null
		/*     */// 493: astore 5
		/*     */// 495: goto +5 -> 500
		/*     */// 498: astore 11
		/*     */// 500: aload_3
		/*     */// 501: ifnull +16 -> 517
		/*     */// 504: aload_3
		/*     */// 505: invokeinterface 112 1 0
		/*     */// 510: aconst_null
		/*     */// 511: astore_3
		/*     */// 512: goto +5 -> 517
		/*     */// 515: astore 11
		/*     */// 517: aload 6
		/*     */// 519: areturn
		/*     */// Line number table:
		/*     */// Java source line #666 -> byte code offset #0
		/*     */// Java source line #667 -> byte code offset #2
		/*     */// Java source line #668 -> byte code offset #5
		/*     */// Java source line #670 -> byte code offset #8
		/*     */// Java source line #671 -> byte code offset #18
		/*     */// Java source line #672 -> byte code offset #21
		/*     */// Java source line #675 -> byte code offset #24
		/*     */// Java source line #676 -> byte code offset #34
		/*     */// Java source line #677 -> byte code offset #43
		/*     */// Java source line #678 -> byte code offset #52
		/*     */// Java source line #679 -> byte code offset #68
		/*     */// Java source line #680 -> byte code offset #84
		/*     */// Java source line #681 -> byte code offset #93
		/*     */// Java source line #682 -> byte code offset #98
		/*     */// Java source line #683 -> byte code offset #107
		/*     */// Java source line #684 -> byte code offset #111
		/*     */// Java source line #685 -> byte code offset #120
		/*     */// Java source line #687 -> byte code offset #129
		/*     */// Java source line #688 -> byte code offset #142
		/*     */// Java source line #689 -> byte code offset #147
		/*     */// Java source line #690 -> byte code offset #157
		/*     */// Java source line #692 -> byte code offset #160
		/*     */// Java source line #693 -> byte code offset #164
		/*     */// Java source line #694 -> byte code offset #174
		/*     */// Java source line #696 -> byte code offset #177
		/*     */// Java source line #698 -> byte code offset #186
		/*     */// Java source line #699 -> byte code offset #189
		/*     */// Java source line #701 -> byte code offset #198
		/*     */// Java source line #702 -> byte code offset #213
		/*     */// Java source line #703 -> byte code offset #228
		/*     */// Java source line #704 -> byte code offset #243
		/*     */// Java source line #705 -> byte code offset #258
		/*     */// Java source line #707 -> byte code offset #273
		/*     */// Java source line #698 -> byte code offset #281
		/*     */// Java source line #709 -> byte code offset #294
		/*     */// Java source line #710 -> byte code offset #296
		/*     */// Java source line #711 -> byte code offset #308
		/*     */// Java source line #712 -> byte code offset #346
		/*     */// Java source line #713 -> byte code offset #348
		/*     */// Java source line #714 -> byte code offset #360
		/*     */// Java source line #715 -> byte code offset #398
		/*     */// Java source line #716 -> byte code offset #400
		/*     */// Java source line #717 -> byte code offset #420
		/*     */// Java source line #718 -> byte code offset #440
		/*     */// Java source line #719 -> byte code offset #457
		/*     */// Java source line #716 -> byte code offset #460
		/*     */// Java source line #717 -> byte code offset #480
		/*     */// Java source line #718 -> byte code offset #500
		/*     */// Java source line #720 -> byte code offset #517
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 520 0 this AsignacionDAO
		/*     */// 0 520 1 idPerfilN int
		/*     */// 0 520 2 numPtosMaximo int
		/*     */// 1 511 3 conn Connection
		/*     */// 3 471 4 statement PreparedStatement
		/*     */// 6 488 5 resultSet ResultSet
		/*     */// 16 502 6 perfilesLst ArrayList<com.claro.transfer.PerfilTO>
		/*     */// 19 257 7 perfilTO com.claro.transfer.PerfilTO
		/*     */// 22 153 8 numFiltro int
		/*     */// 41 90 9 query StringBuffer
		/*     */// 294 47 9 e SQLException
		/*     */// 346 47 9 e Exception
		/*     */// 398 60 10 localObject Object
		/*     */// 418 1 11 localException1 Exception
		/*     */// 438 1 11 localException2 Exception
		/*     */// 455 1 11 localException3 Exception
		/*     */// 478 1 11 localException4 Exception
		/*     */// 498 1 11 localException5 Exception
		/*     */// 515 1 11 localException6 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 24 291 294 java/sql/SQLException
		/*     */// 24 291 346 java/lang/Exception
		/*     */// 24 398 398 finally
		/*     */// 405 415 418 java/lang/Exception
		/*     */// 425 435 438 java/lang/Exception
		/*     */// 444 452 455 java/lang/Exception
		/*     */// 465 475 478 java/lang/Exception
		/*     */// 485 495 498 java/lang/Exception
		/*     */// 504 512 515 java/lang/Exception
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public ArrayList<com.claro.transfer.MotivoTO> obtieneMotivosAsignacion(
			String idMotivo, String descripcion, int estatus)
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 4
		/*     */// 3: aconst_null
		/*     */// 4: astore 5
		/*     */// 6: aconst_null
		/*     */// 7: astore 6
		/*     */// 9: new 471 java/util/ArrayList
		/*     */// 12: dup
		/*     */// 13: iconst_0
		/*     */// 14: invokespecial 613 java/util/ArrayList:<init> (I)V
		/*     */// 17: astore 7
		/*     */// 19: aconst_null
		/*     */// 20: astore 8
		/*     */// 22: iconst_1
		/*     */// 23: istore 9
		/*     */// 25: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 28: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 31: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 34: astore 4
		/*     */// 36: new 313 java/lang/StringBuffer
		/*     */// 39: dup
		/*     */// 40: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 43: astore 10
		/*     */// 45: aload 10
		/*     */// 47: ldc_w 704
		/*     */// 50: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 53: pop
		/*     */// 54: aload 10
		/*     */// 56: aload_0
		/*     */// 57: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 60: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 63: ldc_w 706
		/*     */// 66: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 69: pop
		/*     */// 70: aload 10
		/*     */// 72: ldc_w 708
		/*     */// 75: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 78: pop
		/*     */// 79: aload_1
		/*     */// 80: ifnull +25 -> 105
		/*     */// 83: aload_1
		/*     */// 84: invokevirtual 710 java/lang/String:trim ()Ljava/lang/String;
		/*     */// 87: ldc_w 713
		/*     */// 90: invokevirtual 467 java/lang/String:equals
				// (Ljava/lang/Object;)Z
		/*     */// 93: ifne +12 -> 105
		/*     */// 96: aload 10
		/*     */// 98: ldc_w 715
		/*     */// 101: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 104: pop
		/*     */// 105: aload_2
		/*     */// 106: ifnull +48 -> 154
		/*     */// 109: aload_2
		/*     */// 110: invokevirtual 710 java/lang/String:trim
				// ()Ljava/lang/String;
		/*     */// 113: ldc_w 713
		/*     */// 116: invokevirtual 467 java/lang/String:equals
				// (Ljava/lang/Object;)Z
		/*     */// 119: ifne +35 -> 154
		/*     */// 122: aload 10
		/*     */// 124: new 121 java/lang/StringBuilder
		/*     */// 127: dup
		/*     */// 128: ldc_w 717
		/*     */// 131: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 134: aload_2
		/*     */// 135: invokevirtual 719 java/lang/String:toUpperCase
				// ()Ljava/lang/String;
		/*     */// 138: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 141: ldc_w 722
		/*     */// 144: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 147: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 150: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 153: pop
		/*     */// 154: iload_3
		/*     */// 155: iconst_m1
		/*     */// 156: if_icmpeq +12 -> 168
		/*     */// 159: aload 10
		/*     */// 161: ldc_w 724
		/*     */// 164: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 167: pop
		/*     */// 168: aload 4
		/*     */// 170: aload 10
		/*     */// 172: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 175: invokeinterface 279 2 0
		/*     */// 180: astore 5
		/*     */// 182: aload_1
		/*     */// 183: ifnull +32 -> 215
		/*     */// 186: aload_1
		/*     */// 187: invokevirtual 710 java/lang/String:trim
				// ()Ljava/lang/String;
		/*     */// 190: ldc_w 713
		/*     */// 193: invokevirtual 467 java/lang/String:equals
				// (Ljava/lang/Object;)Z
		/*     */// 196: ifne +19 -> 215
		/*     */// 199: aload 5
		/*     */// 201: iload 9
		/*     */// 203: aload_1
		/*     */// 204: invokevirtual 719 java/lang/String:toUpperCase
				// ()Ljava/lang/String;
		/*     */// 207: invokeinterface 289 3 0
		/*     */// 212: iinc 9 1
		/*     */// 215: iload_3
		/*     */// 216: iconst_m1
		/*     */// 217: if_icmpeq +16 -> 233
		/*     */// 220: aload 5
		/*     */// 222: iload 9
		/*     */// 224: iload_3
		/*     */// 225: invokeinterface 283 3 0
		/*     */// 230: iinc 9 1
		/*     */// 233: aload 5
		/*     */// 235: invokeinterface 377 1 0
		/*     */// 240: astore 6
		/*     */// 242: goto +83 -> 325
		/*     */// 245: new 726 com/claro/transfer/MotivoTO
		/*     */// 248: dup
		/*     */// 249: invokespecial 728 com/claro/transfer/MotivoTO:<init> ()V
		/*     */// 252: astore 8
		/*     */// 254: aload 8
		/*     */// 256: aload 6
		/*     */// 258: ldc_w 729
		/*     */// 261: invokeinterface 646 2 0
		/*     */// 266: invokevirtual 731 com/claro/transfer/MotivoTO:setIdMotivo
				// (Ljava/lang/String;)V
		/*     */// 269: aload 8
		/*     */// 271: aload 6
		/*     */// 273: ldc_w 651
		/*     */// 276: invokeinterface 646 2 0
		/*     */// 281: invokevirtual 734
				// com/claro/transfer/MotivoTO:setDescripcion
				// (Ljava/lang/String;)V
		/*     */// 284: aload 8
		/*     */// 286: aload 6
		/*     */// 288: ldc_w 735
		/*     */// 291: invokeinterface 646 2 0
		/*     */// 296: ldc_w 737
		/*     */// 299: invokevirtual 467 java/lang/String:equals
				// (Ljava/lang/Object;)Z
		/*     */// 302: ifeq +9 -> 311
		/*     */// 305: ldc_w 739
		/*     */// 308: goto +6 -> 314
		/*     */// 311: ldc_w 741
		/*     */// 314: invokevirtual 743 com/claro/transfer/MotivoTO:setEstatus
				// (Ljava/lang/String;)V
		/*     */// 317: aload 7
		/*     */// 319: aload 8
		/*     */// 321: invokevirtual 474 java/util/ArrayList:add
				// (Ljava/lang/Object;)Z
		/*     */// 324: pop
		/*     */// 325: aload 6
		/*     */// 327: invokeinterface 381 1 0
		/*     */// 332: ifne -87 -> 245
		/*     */// 335: goto +172 -> 507
		/*     */// 338: astore 10
		/*     */// 340: aload_0
		/*     */// 341: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 344: ldc_w 691
		/*     */// 347: aload 10
		/*     */// 349: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 352: new 59 com/claro/exception/CAException
		/*     */// 355: dup
		/*     */// 356: iconst_m1
		/*     */// 357: new 121 java/lang/StringBuilder
		/*     */// 360: dup
		/*     */// 361: ldc_w 746
		/*     */// 364: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 367: aload 10
		/*     */// 369: invokevirtual 230 java/sql/SQLException:toString
				// ()Ljava/lang/String;
		/*     */// 372: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 375: ldc_w 356
		/*     */// 378: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 381: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 384: aload 10
		/*     */// 386: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 389: athrow
		/*     */// 390: astore 10
		/*     */// 392: aload_0
		/*     */// 393: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 396: ldc_w 695
		/*     */// 399: aload 10
		/*     */// 401: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 404: new 59 com/claro/exception/CAException
		/*     */// 407: dup
		/*     */// 408: iconst_m1
		/*     */// 409: new 121 java/lang/StringBuilder
		/*     */// 412: dup
		/*     */// 413: ldc_w 748
		/*     */// 416: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 419: aload 10
		/*     */// 421: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 424: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 427: ldc_w 356
		/*     */// 430: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 433: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 436: aload 10
		/*     */// 438: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 441: athrow
		/*     */// 442: astore 11
		/*     */// 444: aload 5
		/*     */// 446: ifnull +18 -> 464
		/*     */// 449: aload 5
		/*     */// 451: invokeinterface 360 1 0
		/*     */// 456: aconst_null
		/*     */// 457: astore 5
		/*     */// 459: goto +5 -> 464
		/*     */// 462: astore 12
		/*     */// 464: aload 6
		/*     */// 466: ifnull +18 -> 484
		/*     */// 469: aload 6
		/*     */// 471: invokeinterface 390 1 0
		/*     */// 476: aconst_null
		/*     */// 477: astore 6
		/*     */// 479: goto +5 -> 484
		/*     */// 482: astore 12
		/*     */// 484: aload 4
		/*     */// 486: ifnull +18 -> 504
		/*     */// 489: aload 4
		/*     */// 491: invokeinterface 112 1 0
		/*     */// 496: aconst_null
		/*     */// 497: astore 4
		/*     */// 499: goto +5 -> 504
		/*     */// 502: astore 12
		/*     */// 504: aload 11
		/*     */// 506: athrow
		/*     */// 507: aload 5
		/*     */// 509: ifnull +18 -> 527
		/*     */// 512: aload 5
		/*     */// 514: invokeinterface 360 1 0
		/*     */// 519: aconst_null
		/*     */// 520: astore 5
		/*     */// 522: goto +5 -> 527
		/*     */// 525: astore 12
		/*     */// 527: aload 6
		/*     */// 529: ifnull +18 -> 547
		/*     */// 532: aload 6
		/*     */// 534: invokeinterface 390 1 0
		/*     */// 539: aconst_null
		/*     */// 540: astore 6
		/*     */// 542: goto +5 -> 547
		/*     */// 545: astore 12
		/*     */// 547: aload 4
		/*     */// 549: ifnull +18 -> 567
		/*     */// 552: aload 4
		/*     */// 554: invokeinterface 112 1 0
		/*     */// 559: aconst_null
		/*     */// 560: astore 4
		/*     */// 562: goto +5 -> 567
		/*     */// 565: astore 12
		/*     */// 567: aload 7
		/*     */// 569: areturn
		/*     */// Line number table:
		/*     */// Java source line #726 -> byte code offset #0
		/*     */// Java source line #727 -> byte code offset #3
		/*     */// Java source line #728 -> byte code offset #6
		/*     */// Java source line #730 -> byte code offset #9
		/*     */// Java source line #731 -> byte code offset #19
		/*     */// Java source line #732 -> byte code offset #22
		/*     */// Java source line #735 -> byte code offset #25
		/*     */// Java source line #736 -> byte code offset #36
		/*     */// Java source line #737 -> byte code offset #45
		/*     */// Java source line #738 -> byte code offset #54
		/*     */// Java source line #739 -> byte code offset #70
		/*     */// Java source line #740 -> byte code offset #79
		/*     */// Java source line #741 -> byte code offset #96
		/*     */// Java source line #742 -> byte code offset #105
		/*     */// Java source line #743 -> byte code offset #122
		/*     */// Java source line #744 -> byte code offset #154
		/*     */// Java source line #745 -> byte code offset #159
		/*     */// Java source line #747 -> byte code offset #168
		/*     */// Java source line #748 -> byte code offset #182
		/*     */// Java source line #749 -> byte code offset #199
		/*     */// Java source line #750 -> byte code offset #212
		/*     */// Java source line #752 -> byte code offset #215
		/*     */// Java source line #753 -> byte code offset #220
		/*     */// Java source line #754 -> byte code offset #230
		/*     */// Java source line #757 -> byte code offset #233
		/*     */// Java source line #759 -> byte code offset #242
		/*     */// Java source line #760 -> byte code offset #245
		/*     */// Java source line #762 -> byte code offset #254
		/*     */// Java source line #763 -> byte code offset #269
		/*     */// Java source line #764 -> byte code offset #284
		/*     */// Java source line #767 -> byte code offset #317
		/*     */// Java source line #759 -> byte code offset #325
		/*     */// Java source line #769 -> byte code offset #338
		/*     */// Java source line #770 -> byte code offset #340
		/*     */// Java source line #771 -> byte code offset #352
		/*     */// Java source line #772 -> byte code offset #390
		/*     */// Java source line #773 -> byte code offset #392
		/*     */// Java source line #774 -> byte code offset #404
		/*     */// Java source line #775 -> byte code offset #442
		/*     */// Java source line #776 -> byte code offset #444
		/*     */// Java source line #777 -> byte code offset #464
		/*     */// Java source line #778 -> byte code offset #484
		/*     */// Java source line #779 -> byte code offset #504
		/*     */// Java source line #776 -> byte code offset #507
		/*     */// Java source line #777 -> byte code offset #527
		/*     */// Java source line #778 -> byte code offset #547
		/*     */// Java source line #780 -> byte code offset #567
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 570 0 this AsignacionDAO
		/*     */// 0 570 1 idMotivo String
		/*     */// 0 570 2 descripcion String
		/*     */// 0 570 3 estatus int
		/*     */// 1 560 4 conn Connection
		/*     */// 4 517 5 statement PreparedStatement
		/*     */// 7 534 6 resultSet ResultSet
		/*     */// 17 551 7 motivosLst ArrayList<com.claro.transfer.MotivoTO>
		/*     */// 20 300 8 motivoTO com.claro.transfer.MotivoTO
		/*     */// 23 208 9 numFiltros int
		/*     */// 43 128 10 query StringBuffer
		/*     */// 338 47 10 e SQLException
		/*     */// 390 47 10 e Exception
		/*     */// 442 63 11 localObject Object
		/*     */// 462 1 12 localException1 Exception
		/*     */// 482 1 12 localException2 Exception
		/*     */// 502 1 12 localException3 Exception
		/*     */// 525 1 12 localException4 Exception
		/*     */// 545 1 12 localException5 Exception
		/*     */// 565 1 12 localException6 Exception
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 25 335 338 java/sql/SQLException
		/*     */// 25 335 390 java/lang/Exception
		/*     */// 25 442 442 finally
		/*     */// 449 459 462 java/lang/Exception
		/*     */// 469 479 482 java/lang/Exception
		/*     */// 489 499 502 java/lang/Exception
		/*     */// 512 522 525 java/lang/Exception
		/*     */// 532 542 545 java/lang/Exception
		/*     */// 552 562 565 java/lang/Exception
		/*     */}

	/*     */
	/*     */public boolean insertaMotivoAsignacion(String idMotivo,
			String descripcion, int estatus)
	/*     */throws CAException
	/*     */{
		/* 785 */Connection conn = null;
		/* 786 */PreparedStatement stmt = null;
		/*     */
		/* 788 */StringBuffer query = new StringBuffer();
		/* 789 */query.append("INSERT INTO ").append(this.schema_database)
				.append("PTO_CTLMOTIVOSASIGNACION ");
		/* 790 */query.append("VALUES(?,?,?)");
		/*     */try
		/*     */{
			/* 793 */conn = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			/*     */
			/* 795 */stmt = conn.prepareStatement(query.toString());
			/* 796 */stmt.setString(1, idMotivo.trim().toUpperCase());
			/* 797 */stmt.setString(2, descripcion.trim().toUpperCase());
			/* 798 */stmt.setInt(3, estatus);
			/* 799 */if (stmt.executeUpdate() > 0) {
				/* 800 */return true;
				/*     */}
			/* 802 */return false;
			/*     */}
		/*     */catch (SQLException e) {
			/* 805 */this.error.info(
					"AsignacionDAO.insertaMotivoAsignacion.SQLException:", e);
			/* 806 */throw new CAException(-1,
					"AsignacionDAO.insertaMotivoAsignacion.Error["
							+ e.toString() + "]", e);
			/*     */} catch (Exception e) {
			/* 808 */this.error.info(
					"AsignacionDAO.insertaMotivoAsignacion.Exception:", e);
			/* 809 */throw new CAException(-1,
					"AsignacionDAO.insertaMotivoAsignacion.Error["
							+ e.toString() + "]", e);
			/*     */} finally {
			/* 811 */if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException localSQLException5) {
				}
			/* 812 */if (conn != null)
				try {
					conn.close();
					/*     */}
				/*     */catch (SQLException localSQLException6) {
				}
			/*     */}
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public boolean actualizaMotivoAsignacion(String idMotivo,
			String descripcion, int estatus)
	/*     */throws CAException
	/*     */{
		return false;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore 4
		/*     */// 3: aconst_null
		/*     */// 4: astore 5
		/*     */// 6: iconst_0
		/*     */// 7: istore 6
		/*     */// 9: new 313 java/lang/StringBuffer
		/*     */// 12: dup
		/*     */// 13: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 16: astore 7
		/*     */// 18: aload 7
		/*     */// 20: ldc_w 273
		/*     */// 23: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 26: aload_0
		/*     */// 27: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 30: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 33: ldc_w 761
		/*     */// 36: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 39: pop
		/*     */// 40: aload 7
		/*     */// 42: ldc_w 773
		/*     */// 45: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 48: pop
		/*     */// 49: aload 7
		/*     */// 51: ldc_w 775
		/*     */// 54: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 57: pop
		/*     */// 58: aload 7
		/*     */// 60: ldc_w 777
		/*     */// 63: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 66: pop
		/*     */// 67: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 70: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 73: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 76: astore 4
		/*     */// 78: aload 4
		/*     */// 80: aload 7
		/*     */// 82: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 85: invokeinterface 279 2 0
		/*     */// 90: astore 5
		/*     */// 92: aload 5
		/*     */// 94: iconst_1
		/*     */// 95: aload_2
		/*     */// 96: invokeinterface 289 3 0
		/*     */// 101: aload 5
		/*     */// 103: iconst_2
		/*     */// 104: iload_3
		/*     */// 105: invokeinterface 283 3 0
		/*     */// 110: aload 5
		/*     */// 112: iconst_3
		/*     */// 113: aload_1
		/*     */// 114: invokeinterface 289 3 0
		/*     */// 119: aload 5
		/*     */// 121: invokeinterface 292 1 0
		/*     */// 126: ifle +100 -> 226
		/*     */// 129: iconst_1
		/*     */// 130: istore 6
		/*     */// 132: goto +94 -> 226
		/*     */// 135: astore 8
		/*     */// 137: aload_0
		/*     */// 138: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 141: ldc_w 779
		/*     */// 144: aload 8
		/*     */// 146: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 149: new 59 com/claro/exception/CAException
		/*     */// 152: dup
		/*     */// 153: iconst_m1
		/*     */// 154: new 121 java/lang/StringBuilder
		/*     */// 157: dup
		/*     */// 158: ldc_w 781
		/*     */// 161: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 164: aload 8
		/*     */// 166: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 169: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 172: ldc_w 356
		/*     */// 175: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 178: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 181: aload 8
		/*     */// 183: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 186: athrow
		/*     */// 187: astore 9
		/*     */// 189: aload 5
		/*     */// 191: ifnull +15 -> 206
		/*     */// 194: aload 5
		/*     */// 196: invokeinterface 360 1 0
		/*     */// 201: goto +5 -> 206
		/*     */// 204: astore 10
		/*     */// 206: aload 4
		/*     */// 208: ifnull +15 -> 223
		/*     */// 211: aload 4
		/*     */// 213: invokeinterface 112 1 0
		/*     */// 218: goto +5 -> 223
		/*     */// 221: astore 10
		/*     */// 223: aload 9
		/*     */// 225: athrow
		/*     */// 226: aload 5
		/*     */// 228: ifnull +15 -> 243
		/*     */// 231: aload 5
		/*     */// 233: invokeinterface 360 1 0
		/*     */// 238: goto +5 -> 243
		/*     */// 241: astore 10
		/*     */// 243: aload 4
		/*     */// 245: ifnull +15 -> 260
		/*     */// 248: aload 4
		/*     */// 250: invokeinterface 112 1 0
		/*     */// 255: goto +5 -> 260
		/*     */// 258: astore 10
		/*     */// 260: iload 6
		/*     */// 262: ireturn
		/*     */// Line number table:
		/*     */// Java source line #819 -> byte code offset #0
		/*     */// Java source line #820 -> byte code offset #3
		/*     */// Java source line #821 -> byte code offset #6
		/*     */// Java source line #823 -> byte code offset #9
		/*     */// Java source line #824 -> byte code offset #18
		/*     */// Java source line #825 -> byte code offset #40
		/*     */// Java source line #826 -> byte code offset #49
		/*     */// Java source line #827 -> byte code offset #58
		/*     */// Java source line #830 -> byte code offset #67
		/*     */// Java source line #832 -> byte code offset #78
		/*     */// Java source line #833 -> byte code offset #92
		/*     */// Java source line #834 -> byte code offset #101
		/*     */// Java source line #835 -> byte code offset #110
		/*     */// Java source line #837 -> byte code offset #119
		/*     */// Java source line #838 -> byte code offset #129
		/*     */// Java source line #840 -> byte code offset #135
		/*     */// Java source line #841 -> byte code offset #137
		/*     */// Java source line #842 -> byte code offset #149
		/*     */// Java source line #843 -> byte code offset #187
		/*     */// Java source line #844 -> byte code offset #189
		/*     */// Java source line #845 -> byte code offset #206
		/*     */// Java source line #846 -> byte code offset #223
		/*     */// Java source line #844 -> byte code offset #226
		/*     */// Java source line #845 -> byte code offset #243
		/*     */// Java source line #847 -> byte code offset #260
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 263 0 this AsignacionDAO
		/*     */// 0 263 1 idMotivo String
		/*     */// 0 263 2 descripcion String
		/*     */// 0 263 3 estatus int
		/*     */// 1 248 4 conn Connection
		/*     */// 4 228 5 stmt PreparedStatement
		/*     */// 7 254 6 seActualizo boolean
		/*     */// 16 65 7 query StringBuffer
		/*     */// 135 47 8 e Exception
		/*     */// 187 37 9 localObject Object
		/*     */// 204 1 10 localSQLException SQLException
		/*     */// 221 1 10 localSQLException1 SQLException
		/*     */// 241 1 10 localSQLException2 SQLException
		/*     */// 258 1 10 localSQLException3 SQLException
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 67 132 135 java/lang/Exception
		/*     */// 67 187 187 finally
		/*     */// 194 201 204 java/sql/SQLException
		/*     */// 211 218 221 java/sql/SQLException
		/*     */// 231 238 241 java/sql/SQLException
		/*     */// 248 255 258 java/sql/SQLException
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public boolean eliminaMotivoAsignacion(String idMotivo)
	/*     */throws CAException
	/*     */{
		return false;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_2
		/*     */// 2: aconst_null
		/*     */// 3: astore_3
		/*     */// 4: iconst_0
		/*     */// 5: istore 4
		/*     */// 7: new 313 java/lang/StringBuffer
		/*     */// 10: dup
		/*     */// 11: invokespecial 315 java/lang/StringBuffer:<init> ()V
		/*     */// 14: astore 5
		/*     */// 16: aload 5
		/*     */// 18: ldc_w 785
		/*     */// 21: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 24: aload_0
		/*     */// 25: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 28: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 31: ldc_w 761
		/*     */// 34: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 37: pop
		/*     */// 38: aload 5
		/*     */// 40: ldc_w 777
		/*     */// 43: invokevirtual 318 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*     */// 46: pop
		/*     */// 47: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 50: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 53: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 56: astore_2
		/*     */// 57: aload_2
		/*     */// 58: aload 5
		/*     */// 60: invokevirtual 349 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*     */// 63: invokeinterface 279 2 0
		/*     */// 68: astore_3
		/*     */// 69: aload_3
		/*     */// 70: iconst_1
		/*     */// 71: aload_1
		/*     */// 72: invokeinterface 289 3 0
		/*     */// 77: aload_3
		/*     */// 78: invokeinterface 292 1 0
		/*     */// 83: ifle +96 -> 179
		/*     */// 86: iconst_1
		/*     */// 87: istore 4
		/*     */// 89: goto +90 -> 179
		/*     */// 92: astore 6
		/*     */// 94: aload_0
		/*     */// 95: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 98: ldc_w 787
		/*     */// 101: aload 6
		/*     */// 103: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 106: new 59 com/claro/exception/CAException
		/*     */// 109: dup
		/*     */// 110: iconst_m1
		/*     */// 111: new 121 java/lang/StringBuilder
		/*     */// 114: dup
		/*     */// 115: ldc_w 789
		/*     */// 118: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 121: aload 6
		/*     */// 123: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 126: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 129: ldc_w 356
		/*     */// 132: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 135: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 138: aload 6
		/*     */// 140: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 143: athrow
		/*     */// 144: astore 7
		/*     */// 146: aload_3
		/*     */// 147: ifnull +14 -> 161
		/*     */// 150: aload_3
		/*     */// 151: invokeinterface 360 1 0
		/*     */// 156: goto +5 -> 161
		/*     */// 159: astore 8
		/*     */// 161: aload_2
		/*     */// 162: ifnull +14 -> 176
		/*     */// 165: aload_2
		/*     */// 166: invokeinterface 112 1 0
		/*     */// 171: goto +5 -> 176
		/*     */// 174: astore 8
		/*     */// 176: aload 7
		/*     */// 178: athrow
		/*     */// 179: aload_3
		/*     */// 180: ifnull +14 -> 194
		/*     */// 183: aload_3
		/*     */// 184: invokeinterface 360 1 0
		/*     */// 189: goto +5 -> 194
		/*     */// 192: astore 8
		/*     */// 194: aload_2
		/*     */// 195: ifnull +14 -> 209
		/*     */// 198: aload_2
		/*     */// 199: invokeinterface 112 1 0
		/*     */// 204: goto +5 -> 209
		/*     */// 207: astore 8
		/*     */// 209: iload 4
		/*     */// 211: ireturn
		/*     */// Line number table:
		/*     */// Java source line #853 -> byte code offset #0
		/*     */// Java source line #854 -> byte code offset #2
		/*     */// Java source line #855 -> byte code offset #4
		/*     */// Java source line #857 -> byte code offset #7
		/*     */// Java source line #858 -> byte code offset #16
		/*     */// Java source line #859 -> byte code offset #38
		/*     */// Java source line #862 -> byte code offset #47
		/*     */// Java source line #864 -> byte code offset #57
		/*     */// Java source line #865 -> byte code offset #69
		/*     */// Java source line #867 -> byte code offset #77
		/*     */// Java source line #868 -> byte code offset #86
		/*     */// Java source line #870 -> byte code offset #92
		/*     */// Java source line #871 -> byte code offset #94
		/*     */// Java source line #872 -> byte code offset #106
		/*     */// Java source line #873 -> byte code offset #144
		/*     */// Java source line #874 -> byte code offset #146
		/*     */// Java source line #875 -> byte code offset #161
		/*     */// Java source line #876 -> byte code offset #176
		/*     */// Java source line #874 -> byte code offset #179
		/*     */// Java source line #875 -> byte code offset #194
		/*     */// Java source line #877 -> byte code offset #209
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 212 0 this AsignacionDAO
		/*     */// 0 212 1 idMotivo String
		/*     */// 1 198 2 conn Connection
		/*     */// 3 181 3 stmt PreparedStatement
		/*     */// 5 205 4 seElimino boolean
		/*     */// 14 45 5 query StringBuffer
		/*     */// 92 47 6 e Exception
		/*     */// 144 33 7 localObject Object
		/*     */// 159 1 8 localSQLException SQLException
		/*     */// 174 1 8 localSQLException1 SQLException
		/*     */// 192 1 8 localSQLException2 SQLException
		/*     */// 207 1 8 localSQLException3 SQLException
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 47 89 92 java/lang/Exception
		/*     */// 47 144 144 finally
		/*     */// 150 156 159 java/sql/SQLException
		/*     */// 165 171 174 java/sql/SQLException
		/*     */// 183 189 192 java/sql/SQLException
		/*     */// 198 204 207 java/sql/SQLException
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public java.util.List<com.claro.transfer.TelefonoTO> getLineasPrueba()
	/*     */throws CAException
	/*     */{
		return null;
		/*     */// Byte code:
		/*     */// 0: new 471 java/util/ArrayList
		/*     */// 3: dup
		/*     */// 4: invokespecial 473 java/util/ArrayList:<init> ()V
		/*     */// 7: astore_1
		/*     */// 8: aconst_null
		/*     */// 9: astore_2
		/*     */// 10: aconst_null
		/*     */// 11: astore_3
		/*     */// 12: aconst_null
		/*     */// 13: astore 4
		/*     */// 15: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 18: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 21: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 24: astore_2
		/*     */// 25: new 121 java/lang/StringBuilder
		/*     */// 28: dup
		/*     */// 29: invokespecial 795 java/lang/StringBuilder:<init> ()V
		/*     */// 32: astore 5
		/*     */// 34: aload 5
		/*     */// 36: ldc_w 796
		/*     */// 39: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 42: pop
		/*     */// 43: aload 5
		/*     */// 45: ldc_w 798
		/*     */// 48: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 51: aload_0
		/*     */// 52: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 55: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 58: ldc_w 800
		/*     */// 61: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 64: pop
		/*     */// 65: aload 5
		/*     */// 67: ldc_w 802
		/*     */// 70: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 73: pop
		/*     */// 74: aload 5
		/*     */// 76: ldc_w 567
		/*     */// 79: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 82: pop
		/*     */// 83: aload_2
		/*     */// 84: aload 5
		/*     */// 86: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 89: invokeinterface 279 2 0
		/*     */// 94: astore_3
		/*     */// 95: aload_3
		/*     */// 96: invokeinterface 377 1 0
		/*     */// 101: astore 4
		/*     */// 103: goto +66 -> 169
		/*     */// 106: new 804 com/claro/transfer/TelefonoTO
		/*     */// 109: dup
		/*     */// 110: invokespecial 806 com/claro/transfer/TelefonoTO:<init> ()V
		/*     */// 113: astore 6
		/*     */// 115: aload 6
		/*     */// 117: aload 4
		/*     */// 119: ldc_w 807
		/*     */// 122: invokeinterface 646 2 0
		/*     */// 127: invokevirtual 809 com/claro/transfer/TelefonoTO:setCuenta
				// (Ljava/lang/String;)V
		/*     */// 130: aload 6
		/*     */// 132: aload 4
		/*     */// 134: ldc_w 812
		/*     */// 137: invokeinterface 646 2 0
		/*     */// 142: invokevirtual 814 com/claro/transfer/TelefonoTO:setTelefono
				// (Ljava/lang/String;)V
		/*     */// 145: aload 6
		/*     */// 147: aload 4
		/*     */// 149: ldc_w 656
		/*     */// 152: invokeinterface 571 2 0
		/*     */// 157: invokevirtual 817 com/claro/transfer/TelefonoTO:setRegion
				// (I)V
		/*     */// 160: aload_1
		/*     */// 161: aload 6
		/*     */// 163: invokeinterface 818 2 0
		/*     */// 168: pop
		/*     */// 169: aload 4
		/*     */// 171: invokeinterface 381 1 0
		/*     */// 176: ifne -70 -> 106
		/*     */// 179: goto +107 -> 286
		/*     */// 182: astore 5
		/*     */// 184: aload_0
		/*     */// 185: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 188: ldc_w 821
		/*     */// 191: aload 5
		/*     */// 193: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 196: new 59 com/claro/exception/CAException
		/*     */// 199: dup
		/*     */// 200: iconst_m1
		/*     */// 201: new 121 java/lang/StringBuilder
		/*     */// 204: dup
		/*     */// 205: ldc_w 823
		/*     */// 208: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 211: aload 5
		/*     */// 213: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 216: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 219: ldc_w 356
		/*     */// 222: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 225: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 228: aload 5
		/*     */// 230: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 233: athrow
		/*     */// 234: astore 7
		/*     */// 236: aload 4
		/*     */// 238: ifnull +15 -> 253
		/*     */// 241: aload 4
		/*     */// 243: invokeinterface 390 1 0
		/*     */// 248: goto +5 -> 253
		/*     */// 251: astore 8
		/*     */// 253: aload_3
		/*     */// 254: ifnull +14 -> 268
		/*     */// 257: aload_3
		/*     */// 258: invokeinterface 360 1 0
		/*     */// 263: goto +5 -> 268
		/*     */// 266: astore 8
		/*     */// 268: aload_2
		/*     */// 269: ifnull +14 -> 283
		/*     */// 272: aload_2
		/*     */// 273: invokeinterface 112 1 0
		/*     */// 278: goto +5 -> 283
		/*     */// 281: astore 8
		/*     */// 283: aload 7
		/*     */// 285: athrow
		/*     */// 286: aload 4
		/*     */// 288: ifnull +15 -> 303
		/*     */// 291: aload 4
		/*     */// 293: invokeinterface 390 1 0
		/*     */// 298: goto +5 -> 303
		/*     */// 301: astore 8
		/*     */// 303: aload_3
		/*     */// 304: ifnull +14 -> 318
		/*     */// 307: aload_3
		/*     */// 308: invokeinterface 360 1 0
		/*     */// 313: goto +5 -> 318
		/*     */// 316: astore 8
		/*     */// 318: aload_2
		/*     */// 319: ifnull +14 -> 333
		/*     */// 322: aload_2
		/*     */// 323: invokeinterface 112 1 0
		/*     */// 328: goto +5 -> 333
		/*     */// 331: astore 8
		/*     */// 333: aload_1
		/*     */// 334: areturn
		/*     */// Line number table:
		/*     */// Java source line #882 -> byte code offset #0
		/*     */// Java source line #884 -> byte code offset #8
		/*     */// Java source line #885 -> byte code offset #10
		/*     */// Java source line #886 -> byte code offset #12
		/*     */// Java source line #889 -> byte code offset #15
		/*     */// Java source line #891 -> byte code offset #25
		/*     */// Java source line #892 -> byte code offset #34
		/*     */// Java source line #893 -> byte code offset #43
		/*     */// Java source line #894 -> byte code offset #65
		/*     */// Java source line #895 -> byte code offset #74
		/*     */// Java source line #897 -> byte code offset #83
		/*     */// Java source line #898 -> byte code offset #95
		/*     */// Java source line #900 -> byte code offset #103
		/*     */// Java source line #901 -> byte code offset #106
		/*     */// Java source line #902 -> byte code offset #115
		/*     */// Java source line #903 -> byte code offset #130
		/*     */// Java source line #904 -> byte code offset #145
		/*     */// Java source line #906 -> byte code offset #160
		/*     */// Java source line #900 -> byte code offset #169
		/*     */// Java source line #909 -> byte code offset #182
		/*     */// Java source line #910 -> byte code offset #184
		/*     */// Java source line #911 -> byte code offset #196
		/*     */// Java source line #912 -> byte code offset #234
		/*     */// Java source line #913 -> byte code offset #236
		/*     */// Java source line #914 -> byte code offset #253
		/*     */// Java source line #915 -> byte code offset #268
		/*     */// Java source line #916 -> byte code offset #283
		/*     */// Java source line #913 -> byte code offset #286
		/*     */// Java source line #914 -> byte code offset #303
		/*     */// Java source line #915 -> byte code offset #318
		/*     */// Java source line #917 -> byte code offset #333
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 335 0 this AsignacionDAO
		/*     */// 7 327 1 lineasPrueba
				// java.util.List<com.claro.transfer.TelefonoTO>
		/*     */// 9 314 2 conn Connection
		/*     */// 11 297 3 stmt PreparedStatement
		/*     */// 13 279 4 resultSet ResultSet
		/*     */// 32 53 5 query StringBuilder
		/*     */// 182 47 5 e Exception
		/*     */// 113 49 6 telefonoTO com.claro.transfer.TelefonoTO
		/*     */// 234 50 7 localObject Object
		/*     */// 251 1 8 localSQLException SQLException
		/*     */// 266 1 8 localSQLException1 SQLException
		/*     */// 281 1 8 localSQLException2 SQLException
		/*     */// 301 1 8 localSQLException3 SQLException
		/*     */// 316 1 8 localSQLException4 SQLException
		/*     */// 331 1 8 localSQLException5 SQLException
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 15 179 182 java/lang/Exception
		/*     */// 15 234 234 finally
		/*     */// 241 248 251 java/sql/SQLException
		/*     */// 257 263 266 java/sql/SQLException
		/*     */// 272 278 281 java/sql/SQLException
		/*     */// 291 298 301 java/sql/SQLException
		/*     */// 307 313 316 java/sql/SQLException
		/*     */// 322 328 331 java/sql/SQLException
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public MensajeTO insertaLineaPrueba(
			com.claro.transfer.TelefonoTO telefonoTO, String numeroEmpleado)
	/*     */throws CAException
	/*     */{
		return telefonoTO;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_3
		/*     */// 2: aconst_null
		/*     */// 3: astore 4
		/*     */// 5: aconst_null
		/*     */// 6: astore 5
		/*     */// 8: new 61 com/claro/transfer/MensajeTO
		/*     */// 11: dup
		/*     */// 12: invokespecial 63 com/claro/transfer/MensajeTO:<init> ()V
		/*     */// 15: astore 6
		/*     */// 17: new 121 java/lang/StringBuilder
		/*     */// 20: dup
		/*     */// 21: invokespecial 795 java/lang/StringBuilder:<init> ()V
		/*     */// 24: astore 7
		/*     */// 26: aload 7
		/*     */// 28: ldc_w 833
		/*     */// 31: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 34: aload_0
		/*     */// 35: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 38: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 41: ldc_w 800
		/*     */// 44: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 47: pop
		/*     */// 48: aload 7
		/*     */// 50: ldc_w 559
		/*     */// 53: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 56: pop
		/*     */// 57: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 60: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 63: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 66: astore_3
		/*     */// 67: aload_3
		/*     */// 68: aload 7
		/*     */// 70: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 73: invokeinterface 279 2 0
		/*     */// 78: astore 4
		/*     */// 80: aload 4
		/*     */// 82: iconst_1
		/*     */// 83: aload_1
		/*     */// 84: invokevirtual 835 com/claro/transfer/TelefonoTO:getCuenta
				// ()Ljava/lang/String;
		/*     */// 87: invokeinterface 289 3 0
		/*     */// 92: aload 4
		/*     */// 94: invokeinterface 377 1 0
		/*     */// 99: astore 5
		/*     */// 101: iconst_0
		/*     */// 102: istore 8
		/*     */// 104: aload 5
		/*     */// 106: invokeinterface 381 1 0
		/*     */// 111: ifeq +13 -> 124
		/*     */// 114: aload 5
		/*     */// 116: iconst_1
		/*     */// 117: invokeinterface 422 2 0
		/*     */// 122: istore 8
		/*     */// 124: aload 5
		/*     */// 126: ifnull +18 -> 144
		/*     */// 129: aload 5
		/*     */// 131: invokeinterface 390 1 0
		/*     */// 136: aconst_null
		/*     */// 137: astore 5
		/*     */// 139: goto +5 -> 144
		/*     */// 142: astore 9
		/*     */// 144: aload 4
		/*     */// 146: ifnull +18 -> 164
		/*     */// 149: aload 4
		/*     */// 151: invokeinterface 360 1 0
		/*     */// 156: aconst_null
		/*     */// 157: astore 4
		/*     */// 159: goto +5 -> 164
		/*     */// 162: astore 9
		/*     */// 164: iload 8
		/*     */// 166: ifne +182 -> 348
		/*     */// 169: new 121 java/lang/StringBuilder
		/*     */// 172: dup
		/*     */// 173: invokespecial 795 java/lang/StringBuilder:<init> ()V
		/*     */// 176: astore 7
		/*     */// 178: aload 7
		/*     */// 180: ldc_w 759
		/*     */// 183: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 186: aload_0
		/*     */// 187: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 190: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 193: ldc_w 836
		/*     */// 196: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 199: pop
		/*     */// 200: aload 7
		/*     */// 202: ldc_w 838
		/*     */// 205: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 208: pop
		/*     */// 209: aload 7
		/*     */// 211: ldc_w 840
		/*     */// 214: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 217: pop
		/*     */// 218: aload_3
		/*     */// 219: aload 7
		/*     */// 221: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 224: invokeinterface 279 2 0
		/*     */// 229: astore 4
		/*     */// 231: aload 4
		/*     */// 233: iconst_1
		/*     */// 234: aload_1
		/*     */// 235: invokevirtual 835 com/claro/transfer/TelefonoTO:getCuenta
				// ()Ljava/lang/String;
		/*     */// 238: invokeinterface 289 3 0
		/*     */// 243: aload 4
		/*     */// 245: iconst_2
		/*     */// 246: aload_1
		/*     */// 247: invokevirtual 842 com/claro/transfer/TelefonoTO:getTelefono
				// ()Ljava/lang/String;
		/*     */// 250: invokeinterface 289 3 0
		/*     */// 255: aload 4
		/*     */// 257: iconst_3
		/*     */// 258: aload_1
		/*     */// 259: invokevirtual 843 com/claro/transfer/TelefonoTO:getRegion
				// ()I
		/*     */// 262: invokeinterface 283 3 0
		/*     */// 267: aload 4
		/*     */// 269: iconst_4
		/*     */// 270: new 844 java/sql/Timestamp
		/*     */// 273: dup
		/*     */// 274: invokestatic 77 java/lang/System:currentTimeMillis ()J
		/*     */// 277: invokespecial 846 java/sql/Timestamp:<init> (J)V
		/*     */// 280: invokeinterface 847 3 0
		/*     */// 285: aload 4
		/*     */// 287: iconst_5
		/*     */// 288: aload_2
		/*     */// 289: invokeinterface 289 3 0
		/*     */// 294: aload 4
		/*     */// 296: bipush 6
		/*     */// 298: ldc_w 851
		/*     */// 301: invokeinterface 289 3 0
		/*     */// 306: aload 4
		/*     */// 308: invokeinterface 292 1 0
		/*     */// 313: pop
		/*     */// 314: aload 6
		/*     */// 316: iconst_0
		/*     */// 317: invokevirtual 853 com/claro/transfer/MensajeTO:setIdMensaje
				// (I)V
		/*     */// 320: aload 6
		/*     */// 322: new 121 java/lang/StringBuilder
		/*     */// 325: dup
		/*     */// 326: ldc_w 856
		/*     */// 329: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 332: aload_1
		/*     */// 333: invokevirtual 842 com/claro/transfer/TelefonoTO:getTelefono
				// ()Ljava/lang/String;
		/*     */// 336: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 339: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 342: invokevirtual 858 com/claro/transfer/MensajeTO:setMensaje
				// (Ljava/lang/String;)V
		/*     */// 345: goto +143 -> 488
		/*     */// 348: aload 6
		/*     */// 350: iconst_m1
		/*     */// 351: invokevirtual 853 com/claro/transfer/MensajeTO:setIdMensaje
				// (I)V
		/*     */// 354: aload 6
		/*     */// 356: new 121 java/lang/StringBuilder
		/*     */// 359: dup
		/*     */// 360: ldc_w 861
		/*     */// 363: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 366: aload_1
		/*     */// 367: invokevirtual 842 com/claro/transfer/TelefonoTO:getTelefono
				// ()Ljava/lang/String;
		/*     */// 370: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 373: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 376: invokevirtual 858 com/claro/transfer/MensajeTO:setMensaje
				// (Ljava/lang/String;)V
		/*     */// 379: goto +109 -> 488
		/*     */// 382: astore 7
		/*     */// 384: aload_0
		/*     */// 385: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 388: ldc_w 863
		/*     */// 391: aload 7
		/*     */// 393: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 396: new 59 com/claro/exception/CAException
		/*     */// 399: dup
		/*     */// 400: iconst_m1
		/*     */// 401: new 121 java/lang/StringBuilder
		/*     */// 404: dup
		/*     */// 405: ldc_w 865
		/*     */// 408: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 411: aload 7
		/*     */// 413: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 416: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 419: ldc_w 356
		/*     */// 422: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 425: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 428: aload 7
		/*     */// 430: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 433: athrow
		/*     */// 434: astore 10
		/*     */// 436: aload 5
		/*     */// 438: ifnull +15 -> 453
		/*     */// 441: aload 5
		/*     */// 443: invokeinterface 390 1 0
		/*     */// 448: goto +5 -> 453
		/*     */// 451: astore 11
		/*     */// 453: aload 4
		/*     */// 455: ifnull +15 -> 470
		/*     */// 458: aload 4
		/*     */// 460: invokeinterface 360 1 0
		/*     */// 465: goto +5 -> 470
		/*     */// 468: astore 11
		/*     */// 470: aload_3
		/*     */// 471: ifnull +14 -> 485
		/*     */// 474: aload_3
		/*     */// 475: invokeinterface 112 1 0
		/*     */// 480: goto +5 -> 485
		/*     */// 483: astore 11
		/*     */// 485: aload 10
		/*     */// 487: athrow
		/*     */// 488: aload 5
		/*     */// 490: ifnull +15 -> 505
		/*     */// 493: aload 5
		/*     */// 495: invokeinterface 390 1 0
		/*     */// 500: goto +5 -> 505
		/*     */// 503: astore 11
		/*     */// 505: aload 4
		/*     */// 507: ifnull +15 -> 522
		/*     */// 510: aload 4
		/*     */// 512: invokeinterface 360 1 0
		/*     */// 517: goto +5 -> 522
		/*     */// 520: astore 11
		/*     */// 522: aload_3
		/*     */// 523: ifnull +14 -> 537
		/*     */// 526: aload_3
		/*     */// 527: invokeinterface 112 1 0
		/*     */// 532: goto +5 -> 537
		/*     */// 535: astore 11
		/*     */// 537: aload 6
		/*     */// 539: areturn
		/*     */// Line number table:
		/*     */// Java source line #922 -> byte code offset #0
		/*     */// Java source line #923 -> byte code offset #2
		/*     */// Java source line #924 -> byte code offset #5
		/*     */// Java source line #925 -> byte code offset #8
		/*     */// Java source line #928 -> byte code offset #17
		/*     */// Java source line #929 -> byte code offset #26
		/*     */// Java source line #930 -> byte code offset #48
		/*     */// Java source line #932 -> byte code offset #57
		/*     */// Java source line #933 -> byte code offset #67
		/*     */// Java source line #934 -> byte code offset #80
		/*     */// Java source line #936 -> byte code offset #92
		/*     */// Java source line #938 -> byte code offset #101
		/*     */// Java source line #939 -> byte code offset #104
		/*     */// Java source line #940 -> byte code offset #114
		/*     */// Java source line #942 -> byte code offset #124
		/*     */// Java source line #943 -> byte code offset #144
		/*     */// Java source line #945 -> byte code offset #164
		/*     */// Java source line #946 -> byte code offset #169
		/*     */// Java source line #948 -> byte code offset #178
		/*     */// Java source line #949 -> byte code offset #200
		/*     */// Java source line #950 -> byte code offset #209
		/*     */// Java source line #952 -> byte code offset #218
		/*     */// Java source line #953 -> byte code offset #231
		/*     */// Java source line #954 -> byte code offset #243
		/*     */// Java source line #955 -> byte code offset #255
		/*     */// Java source line #956 -> byte code offset #267
		/*     */// Java source line #957 -> byte code offset #285
		/*     */// Java source line #958 -> byte code offset #294
		/*     */// Java source line #960 -> byte code offset #306
		/*     */// Java source line #962 -> byte code offset #314
		/*     */// Java source line #963 -> byte code offset #320
		/*     */// Java source line #966 -> byte code offset #348
		/*     */// Java source line #967 -> byte code offset #354
		/*     */// Java source line #970 -> byte code offset #382
		/*     */// Java source line #971 -> byte code offset #384
		/*     */// Java source line #972 -> byte code offset #396
		/*     */// Java source line #973 -> byte code offset #434
		/*     */// Java source line #974 -> byte code offset #436
		/*     */// Java source line #975 -> byte code offset #453
		/*     */// Java source line #976 -> byte code offset #470
		/*     */// Java source line #977 -> byte code offset #485
		/*     */// Java source line #974 -> byte code offset #488
		/*     */// Java source line #975 -> byte code offset #505
		/*     */// Java source line #976 -> byte code offset #522
		/*     */// Java source line #978 -> byte code offset #537
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 540 0 this AsignacionDAO
		/*     */// 0 540 1 telefonoTO com.claro.transfer.TelefonoTO
		/*     */// 0 540 2 numeroEmpleado String
		/*     */// 1 526 3 conn Connection
		/*     */// 3 508 4 stmt PreparedStatement
		/*     */// 6 488 5 resultSet ResultSet
		/*     */// 15 523 6 mensajeTO MensajeTO
		/*     */// 24 196 7 query StringBuilder
		/*     */// 382 47 7 e Exception
		/*     */// 102 63 8 total int
		/*     */// 142 1 9 localSQLException SQLException
		/*     */// 162 1 9 localSQLException1 SQLException
		/*     */// 434 52 10 localObject Object
		/*     */// 451 1 11 localSQLException2 SQLException
		/*     */// 468 1 11 localSQLException3 SQLException
		/*     */// 483 1 11 localSQLException4 SQLException
		/*     */// 503 1 11 localSQLException5 SQLException
		/*     */// 520 1 11 localSQLException6 SQLException
		/*     */// 535 1 11 localSQLException7 SQLException
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 129 139 142 java/sql/SQLException
		/*     */// 149 159 162 java/sql/SQLException
		/*     */// 17 379 382 java/lang/Exception
		/*     */// 17 434 434 finally
		/*     */// 441 448 451 java/sql/SQLException
		/*     */// 458 465 468 java/sql/SQLException
		/*     */// 474 480 483 java/sql/SQLException
		/*     */// 493 500 503 java/sql/SQLException
		/*     */// 510 517 520 java/sql/SQLException
		/*     */// 526 532 535 java/sql/SQLException
		/*     */}

	/*     */
	/*     *//* Error */
	/*     */public MensajeTO eliminaLineaPrueba(
			com.claro.transfer.TelefonoTO telefonoTO)
	/*     */throws CAException
	/*     */{
		return telefonoTO;
		/*     */// Byte code:
		/*     */// 0: aconst_null
		/*     */// 1: astore_2
		/*     */// 2: aconst_null
		/*     */// 3: astore_3
		/*     */// 4: new 61 com/claro/transfer/MensajeTO
		/*     */// 7: dup
		/*     */// 8: invokespecial 63 com/claro/transfer/MensajeTO:<init> ()V
		/*     */// 11: astore 4
		/*     */// 13: new 121 java/lang/StringBuilder
		/*     */// 16: dup
		/*     */// 17: invokespecial 795 java/lang/StringBuilder:<init> ()V
		/*     */// 20: astore 5
		/*     */// 22: aload 5
		/*     */// 24: ldc_w 871
		/*     */// 27: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 30: aload_0
		/*     */// 31: getfield 41 com/claro/dao/AsignacionDAO:schema_database
				// Ljava/lang/String;
		/*     */// 34: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 37: ldc_w 873
		/*     */// 40: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 43: pop
		/*     */// 44: invokestatic 29 com/claro/util/ServiceLocator:getInstance
				// ()Lcom/claro/util/ServiceLocator;
		/*     */// 47: getstatic 64 com/claro/util/ServiceLocator:jdbcCirculoAzul
				// Ljava/lang/String;
		/*     */// 50: invokevirtual 67 com/claro/util/ServiceLocator:getConnection
				// (Ljava/lang/String;)Ljava/sql/Connection;
		/*     */// 53: astore_2
		/*     */// 54: aload_2
		/*     */// 55: aload 5
		/*     */// 57: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 60: invokeinterface 279 2 0
		/*     */// 65: astore_3
		/*     */// 66: aload_3
		/*     */// 67: iconst_1
		/*     */// 68: aload_1
		/*     */// 69: invokevirtual 835 com/claro/transfer/TelefonoTO:getCuenta
				// ()Ljava/lang/String;
		/*     */// 72: invokeinterface 289 3 0
		/*     */// 77: aload_3
		/*     */// 78: invokeinterface 292 1 0
		/*     */// 83: pop
		/*     */// 84: aload 4
		/*     */// 86: iconst_0
		/*     */// 87: invokevirtual 853 com/claro/transfer/MensajeTO:setIdMensaje
				// (I)V
		/*     */// 90: aload 4
		/*     */// 92: new 121 java/lang/StringBuilder
		/*     */// 95: dup
		/*     */// 96: ldc_w 875
		/*     */// 99: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 102: aload_1
		/*     */// 103: invokevirtual 842 com/claro/transfer/TelefonoTO:getTelefono
				// ()Ljava/lang/String;
		/*     */// 106: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 109: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 112: invokevirtual 858 com/claro/transfer/MensajeTO:setMensaje
				// (Ljava/lang/String;)V
		/*     */// 115: goto +90 -> 205
		/*     */// 118: astore 5
		/*     */// 120: aload_0
		/*     */// 121: getfield 27 com/claro/dao/AsignacionDAO:error
				// Lorg/apache/log4j/Logger;
		/*     */// 124: ldc_w 877
		/*     */// 127: aload 5
		/*     */// 129: invokevirtual 225 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;Ljava/lang/Throwable;)V
		/*     */// 132: new 59 com/claro/exception/CAException
		/*     */// 135: dup
		/*     */// 136: iconst_m1
		/*     */// 137: new 121 java/lang/StringBuilder
		/*     */// 140: dup
		/*     */// 141: ldc_w 879
		/*     */// 144: invokespecial 125 java/lang/StringBuilder:<init>
				// (Ljava/lang/String;)V
		/*     */// 147: aload 5
		/*     */// 149: invokevirtual 242 java/lang/Exception:toString
				// ()Ljava/lang/String;
		/*     */// 152: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 155: ldc_w 356
		/*     */// 158: invokevirtual 128 java/lang/StringBuilder:append
				// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		/*     */// 161: invokevirtual 136 java/lang/StringBuilder:toString
				// ()Ljava/lang/String;
		/*     */// 164: aload 5
		/*     */// 166: invokespecial 235 com/claro/exception/CAException:<init>
				// (ILjava/lang/String;Ljava/lang/Exception;)V
		/*     */// 169: athrow
		/*     */// 170: astore 6
		/*     */// 172: aload_3
		/*     */// 173: ifnull +14 -> 187
		/*     */// 176: aload_3
		/*     */// 177: invokeinterface 360 1 0
		/*     */// 182: goto +5 -> 187
		/*     */// 185: astore 7
		/*     */// 187: aload_2
		/*     */// 188: ifnull +14 -> 202
		/*     */// 191: aload_2
		/*     */// 192: invokeinterface 112 1 0
		/*     */// 197: goto +5 -> 202
		/*     */// 200: astore 7
		/*     */// 202: aload 6
		/*     */// 204: athrow
		/*     */// 205: aload_3
		/*     */// 206: ifnull +14 -> 220
		/*     */// 209: aload_3
		/*     */// 210: invokeinterface 360 1 0
		/*     */// 215: goto +5 -> 220
		/*     */// 218: astore 7
		/*     */// 220: aload_2
		/*     */// 221: ifnull +14 -> 235
		/*     */// 224: aload_2
		/*     */// 225: invokeinterface 112 1 0
		/*     */// 230: goto +5 -> 235
		/*     */// 233: astore 7
		/*     */// 235: aload 4
		/*     */// 237: areturn
		/*     */// Line number table:
		/*     */// Java source line #983 -> byte code offset #0
		/*     */// Java source line #984 -> byte code offset #2
		/*     */// Java source line #985 -> byte code offset #4
		/*     */// Java source line #988 -> byte code offset #13
		/*     */// Java source line #989 -> byte code offset #22
		/*     */// Java source line #991 -> byte code offset #44
		/*     */// Java source line #992 -> byte code offset #54
		/*     */// Java source line #993 -> byte code offset #66
		/*     */// Java source line #995 -> byte code offset #77
		/*     */// Java source line #997 -> byte code offset #84
		/*     */// Java source line #998 -> byte code offset #90
		/*     */// Java source line #1000 -> byte code offset #118
		/*     */// Java source line #1001 -> byte code offset #120
		/*     */// Java source line #1002 -> byte code offset #132
		/*     */// Java source line #1003 -> byte code offset #170
		/*     */// Java source line #1004 -> byte code offset #172
		/*     */// Java source line #1005 -> byte code offset #187
		/*     */// Java source line #1006 -> byte code offset #202
		/*     */// Java source line #1004 -> byte code offset #205
		/*     */// Java source line #1005 -> byte code offset #220
		/*     */// Java source line #1007 -> byte code offset #235
		/*     */// Local variable table:
		/*     */// start length slot name signature
		/*     */// 0 238 0 this AsignacionDAO
		/*     */// 0 238 1 telefonoTO com.claro.transfer.TelefonoTO
		/*     */// 1 224 2 conn Connection
		/*     */// 3 207 3 stmt PreparedStatement
		/*     */// 11 225 4 mensajeTO MensajeTO
		/*     */// 20 36 5 query StringBuilder
		/*     */// 118 47 5 e Exception
		/*     */// 170 33 6 localObject Object
		/*     */// 185 1 7 localSQLException SQLException
		/*     */// 200 1 7 localSQLException1 SQLException
		/*     */// 218 1 7 localSQLException2 SQLException
		/*     */// 233 1 7 localSQLException3 SQLException
		/*     */// Exception table:
		/*     */// from to target type
		/*     */// 13 115 118 java/lang/Exception
		/*     */// 13 170 170 finally
		/*     */// 176 182 185 java/sql/SQLException
		/*     */// 191 197 200 java/sql/SQLException
		/*     */// 209 215 218 java/sql/SQLException
		/*     */// 224 230 233 java/sql/SQLException
		/*     */}
	/*     */
}

/*
 * Location:
 * /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/
 * com/claro/dao/AsignacionDAO.class Java compiler version: 6 (50.0) JD-Core
 * Version: 0.7.1
 */