/*      */ package com.claro.dao;
/*      */ 
/*      */ import com.claro.catalogo.Catalogo;
/*      */ import com.claro.exception.CAException;
/*      */ import com.claro.services.ClienteConsultas;
/*      */ import com.claro.transfer.CicloFacturacionTO;
/*      */ import com.claro.transfer.CuentaPadreTO;
/*      */ import com.claro.transfer.MensajeTO;
/*      */ import com.claro.transfer.MobileTO;
/*      */ import com.claro.transfer.ParametrosTO;
/*      */ import com.claro.transfer.PerfilTO;
/*      */ import com.claro.transfer.PlanTO;
/*      */ import com.claro.transfer.ProductosTO;
/*      */ import com.claro.transfer.PuntoVentaTO;
/*      */ import com.claro.transfer.PuntosTO;
/*      */ import com.claro.transfer.ReservacionTO;
/*      */ import com.claro.transfer.TelefonoSimpleTO;
/*      */ import com.claro.transfer.TelefonoTO;
/*      */ import com.claro.transfer.UsuarioTO;
/*      */ import com.claro.transfer.service.TelefonoServiceTO;
/*      */ import com.claro.util.Constantes;
/*      */ import com.claro.util.Redencion;
/*      */ import com.claro.util.ServiceLocator;
/*      */ import com.claro.util.Utils;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.GregorianCalendar;
/*      */ import org.apache.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConsultasDAO
/*      */ {
/*   49 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/*   50 */   protected final Logger loggerPuntos = Logger.getLogger("loggerConsultaPuntos");
/*   51 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*      */   
/*      */ 
/*      */ 
/*      */   private ConsultaM2KDAO consultaM2KDAO;
/*      */   
/*      */ 
/*      */ 
/*      */   private PuntosDAO puntosDAO;
/*      */   
/*      */ 
/*      */ 
/*      */   private ConsultasGapDAO consultasGapDAO;
/*      */   
/*      */ 
/*      */   private String schema_database;
/*      */   
/*      */ 
/*      */ 
/*      */   public ConsultasDAO()
/*      */   {
/*   72 */     this.consultaM2KDAO = new ConsultaM2KDAO();
/*   73 */     this.puntosDAO = new PuntosDAO();
/*   74 */     this.consultasGapDAO = new ConsultasGapDAO();
/*      */     try {
/*   76 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*      */     } catch (Exception e) {
/*   78 */       this.error.error("ConsultasDAO", e);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.RedencionTO> detalleRedencion(String cuenta, int secuencia, String fecha)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 4
/*      */     //   3: aconst_null
/*      */     //   4: astore 5
/*      */     //   6: aconst_null
/*      */     //   7: astore 6
/*      */     //   9: lconst_0
/*      */     //   10: lstore 7
/*      */     //   12: aconst_null
/*      */     //   13: astore 9
/*      */     //   15: ldc 89
/*      */     //   17: astore 10
/*      */     //   19: aload_3
/*      */     //   20: ifnull +39 -> 59
/*      */     //   23: aload_3
/*      */     //   24: ldc 91
/*      */     //   26: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
/*      */     //   29: ifeq +14 -> 43
/*      */     //   32: new 85	com/claro/exception/CAException
/*      */     //   35: dup
/*      */     //   36: iconst_m1
/*      */     //   37: ldc 99
/*      */     //   39: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   42: athrow
/*      */     //   43: aload_3
/*      */     //   44: invokevirtual 104	java/lang/String:trim	()Ljava/lang/String;
/*      */     //   47: ldc 89
/*      */     //   49: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   52: ifne +7 -> 59
/*      */     //   55: ldc 112
/*      */     //   57: astore 10
/*      */     //   59: new 114	java/lang/StringBuffer
/*      */     //   62: dup
/*      */     //   63: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   66: astore 11
/*      */     //   68: aload 11
/*      */     //   70: ldc 117
/*      */     //   72: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   75: pop
/*      */     //   76: aload 11
/*      */     //   78: ldc 123
/*      */     //   80: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   83: pop
/*      */     //   84: aload 11
/*      */     //   86: ldc 125
/*      */     //   88: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   91: pop
/*      */     //   92: aload 11
/*      */     //   94: ldc 127
/*      */     //   96: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   99: pop
/*      */     //   100: aload 11
/*      */     //   102: ldc -127
/*      */     //   104: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   107: aload_0
/*      */     //   108: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   111: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   114: ldc -125
/*      */     //   116: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   119: aload_0
/*      */     //   120: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   123: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   126: ldc -123
/*      */     //   128: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   131: pop
/*      */     //   132: aload 11
/*      */     //   134: ldc -121
/*      */     //   136: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   139: pop
/*      */     //   140: aload 11
/*      */     //   142: ldc -119
/*      */     //   144: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   147: pop
/*      */     //   148: aload 11
/*      */     //   150: ldc -117
/*      */     //   152: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   155: pop
/*      */     //   156: aload 11
/*      */     //   158: ldc -115
/*      */     //   160: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   163: pop
/*      */     //   164: aload 11
/*      */     //   166: aload 10
/*      */     //   168: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   171: pop
/*      */     //   172: aload 11
/*      */     //   174: ldc -113
/*      */     //   176: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   179: pop
/*      */     //   180: new 145	java/util/ArrayList
/*      */     //   183: dup
/*      */     //   184: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   187: astore 12
/*      */     //   189: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   192: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   195: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   198: astore 4
/*      */     //   200: aload 4
/*      */     //   202: aload 11
/*      */     //   204: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   207: invokeinterface 158 2 0
/*      */     //   212: astore 5
/*      */     //   214: aload 5
/*      */     //   216: iconst_1
/*      */     //   217: aload_1
/*      */     //   218: invokeinterface 164 3 0
/*      */     //   223: aload 5
/*      */     //   225: iconst_2
/*      */     //   226: iload_2
/*      */     //   227: invokeinterface 169 3 0
/*      */     //   232: aload 10
/*      */     //   234: invokevirtual 104	java/lang/String:trim	()Ljava/lang/String;
/*      */     //   237: ldc 89
/*      */     //   239: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   242: ifne +36 -> 278
/*      */     //   245: getstatic 173	com/claro/util/Constantes:DATEFORMATyyyy_MM_dd	Ljava/text/SimpleDateFormat;
/*      */     //   248: aload_3
/*      */     //   249: invokevirtual 179	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
/*      */     //   252: astore 9
/*      */     //   254: aload 9
/*      */     //   256: invokevirtual 185	java/util/Date:getTime	()J
/*      */     //   259: lstore 7
/*      */     //   261: aload 5
/*      */     //   263: iconst_3
/*      */     //   264: new 191	java/sql/Date
/*      */     //   267: dup
/*      */     //   268: lload 7
/*      */     //   270: invokespecial 193	java/sql/Date:<init>	(J)V
/*      */     //   273: invokeinterface 196 3 0
/*      */     //   278: aload 5
/*      */     //   280: invokeinterface 200 1 0
/*      */     //   285: astore 6
/*      */     //   287: goto +463 -> 750
/*      */     //   290: new 204	com/claro/transfer/RedencionTO
/*      */     //   293: dup
/*      */     //   294: invokespecial 206	com/claro/transfer/RedencionTO:<init>	()V
/*      */     //   297: astore 13
/*      */     //   299: new 207	com/claro/transfer/TelefonoSimpleTO
/*      */     //   302: dup
/*      */     //   303: invokespecial 209	com/claro/transfer/TelefonoSimpleTO:<init>	()V
/*      */     //   306: astore 14
/*      */     //   308: aload 14
/*      */     //   310: aload 6
/*      */     //   312: ldc -46
/*      */     //   314: invokeinterface 212 2 0
/*      */     //   319: invokevirtual 217	com/claro/transfer/TelefonoSimpleTO:setCuenta	(Ljava/lang/String;)V
/*      */     //   322: aload 14
/*      */     //   324: aload 6
/*      */     //   326: ldc -35
/*      */     //   328: invokeinterface 212 2 0
/*      */     //   333: invokevirtual 223	com/claro/transfer/TelefonoSimpleTO:setLinea	(Ljava/lang/String;)V
/*      */     //   336: aload 13
/*      */     //   338: aload 14
/*      */     //   340: invokevirtual 226	com/claro/transfer/RedencionTO:setTelefonoSimpleTO	(Lcom/claro/transfer/TelefonoSimpleTO;)V
/*      */     //   343: new 230	com/claro/transfer/PuntosRedimidosTO
/*      */     //   346: dup
/*      */     //   347: invokespecial 232	com/claro/transfer/PuntosRedimidosTO:<init>	()V
/*      */     //   350: astore 15
/*      */     //   352: aload 15
/*      */     //   354: aload 6
/*      */     //   356: ldc -23
/*      */     //   358: invokeinterface 235 2 0
/*      */     //   363: invokevirtual 239	com/claro/transfer/PuntosRedimidosTO:setPtsTotaltes	(I)V
/*      */     //   366: aload 15
/*      */     //   368: aload 6
/*      */     //   370: ldc -23
/*      */     //   372: invokeinterface 235 2 0
/*      */     //   377: invokestatic 243	com/claro/util/Utils:setFormatoPtos	(I)Ljava/lang/String;
/*      */     //   380: invokevirtual 249	com/claro/transfer/PuntosRedimidosTO:setPtsTotaltesconFormato	(Ljava/lang/String;)V
/*      */     //   383: aload 13
/*      */     //   385: aload 15
/*      */     //   387: invokevirtual 252	com/claro/transfer/RedencionTO:setPuntosRedimidosTO	(Lcom/claro/transfer/PuntosRedimidosTO;)V
/*      */     //   390: aload 13
/*      */     //   392: aload 6
/*      */     //   394: ldc_w 256
/*      */     //   397: invokeinterface 258 2 0
/*      */     //   402: invokevirtual 262	com/claro/transfer/RedencionTO:setFechaOperacion	(Ljava/util/Date;)V
/*      */     //   405: new 266	com/claro/transfer/ProductosTO
/*      */     //   408: dup
/*      */     //   409: invokespecial 268	com/claro/transfer/ProductosTO:<init>	()V
/*      */     //   412: astore 16
/*      */     //   414: aload 16
/*      */     //   416: aload 6
/*      */     //   418: ldc_w 269
/*      */     //   421: invokeinterface 212 2 0
/*      */     //   426: invokevirtual 271	com/claro/transfer/ProductosTO:setMaterial	(Ljava/lang/String;)V
/*      */     //   429: aload 16
/*      */     //   431: aload 6
/*      */     //   433: ldc_w 274
/*      */     //   436: invokeinterface 212 2 0
/*      */     //   441: invokevirtual 276	com/claro/transfer/ProductosTO:setMarca	(Ljava/lang/String;)V
/*      */     //   444: aload 16
/*      */     //   446: aload 6
/*      */     //   448: ldc_w 279
/*      */     //   451: invokeinterface 212 2 0
/*      */     //   456: invokevirtual 281	com/claro/transfer/ProductosTO:setModelo	(Ljava/lang/String;)V
/*      */     //   459: aload 16
/*      */     //   461: aload 6
/*      */     //   463: ldc_w 284
/*      */     //   466: invokeinterface 286 2 0
/*      */     //   471: invokevirtual 290	com/claro/transfer/ProductosTO:setPrecioIva	(Ljava/math/BigDecimal;)V
/*      */     //   474: aload 16
/*      */     //   476: aload 6
/*      */     //   478: ldc_w 294
/*      */     //   481: invokeinterface 286 2 0
/*      */     //   486: invokevirtual 296	com/claro/transfer/ProductosTO:setPrecio	(Ljava/math/BigDecimal;)V
/*      */     //   489: aload 16
/*      */     //   491: aload 6
/*      */     //   493: ldc_w 299
/*      */     //   496: invokeinterface 286 2 0
/*      */     //   501: invokevirtual 301	com/claro/transfer/ProductosTO:setDescuento	(Ljava/math/BigDecimal;)V
/*      */     //   504: aload 16
/*      */     //   506: aload 6
/*      */     //   508: ldc_w 294
/*      */     //   511: invokeinterface 212 2 0
/*      */     //   516: invokestatic 304	com/claro/util/Utils:setFormatoDecimal	(Ljava/lang/String;)Ljava/lang/String;
/*      */     //   519: invokevirtual 307	com/claro/transfer/ProductosTO:setPrecioConFormato	(Ljava/lang/String;)V
/*      */     //   522: aload 16
/*      */     //   524: aload 6
/*      */     //   526: ldc_w 284
/*      */     //   529: invokeinterface 212 2 0
/*      */     //   534: invokestatic 304	com/claro/util/Utils:setFormatoDecimal	(Ljava/lang/String;)Ljava/lang/String;
/*      */     //   537: invokevirtual 310	com/claro/transfer/ProductosTO:setPrecioIvaConFormato	(Ljava/lang/String;)V
/*      */     //   540: aload 16
/*      */     //   542: aload 6
/*      */     //   544: ldc_w 313
/*      */     //   547: invokeinterface 212 2 0
/*      */     //   552: invokevirtual 315	com/claro/transfer/ProductosTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   555: aload 16
/*      */     //   557: aload 6
/*      */     //   559: ldc_w 269
/*      */     //   562: invokeinterface 212 2 0
/*      */     //   567: invokevirtual 318	com/claro/transfer/ProductosTO:setIdProducto	(Ljava/lang/String;)V
/*      */     //   570: new 321	com/claro/transfer/UsuarioTO
/*      */     //   573: dup
/*      */     //   574: invokespecial 323	com/claro/transfer/UsuarioTO:<init>	()V
/*      */     //   577: astore 17
/*      */     //   579: aload 17
/*      */     //   581: aload 6
/*      */     //   583: ldc_w 324
/*      */     //   586: invokeinterface 212 2 0
/*      */     //   591: invokevirtual 326	com/claro/transfer/UsuarioTO:setIdUsuario	(Ljava/lang/String;)V
/*      */     //   594: aload 17
/*      */     //   596: new 329	com/claro/transfer/PuntoVentaTO
/*      */     //   599: dup
/*      */     //   600: invokespecial 331	com/claro/transfer/PuntoVentaTO:<init>	()V
/*      */     //   603: invokevirtual 332	com/claro/transfer/UsuarioTO:setPuntoVentaTO	(Lcom/claro/transfer/PuntoVentaTO;)V
/*      */     //   606: aload 13
/*      */     //   608: aload 6
/*      */     //   610: ldc_w 336
/*      */     //   613: invokeinterface 212 2 0
/*      */     //   618: invokevirtual 338	com/claro/transfer/RedencionTO:setComentario	(Ljava/lang/String;)V
/*      */     //   621: aload 13
/*      */     //   623: aload 6
/*      */     //   625: ldc_w 341
/*      */     //   628: invokeinterface 212 2 0
/*      */     //   633: invokevirtual 343	com/claro/transfer/RedencionTO:setFechaAdendum	(Ljava/lang/String;)V
/*      */     //   636: aload 13
/*      */     //   638: aload 6
/*      */     //   640: ldc_w 346
/*      */     //   643: invokeinterface 348 2 0
/*      */     //   648: invokevirtual 352	com/claro/transfer/RedencionTO:setFechaFolio	(Ljava/sql/Timestamp;)V
/*      */     //   651: aload 13
/*      */     //   653: aload 6
/*      */     //   655: ldc_w 356
/*      */     //   658: invokeinterface 212 2 0
/*      */     //   663: invokevirtual 358	com/claro/transfer/RedencionTO:setTipoRedencion	(Ljava/lang/String;)V
/*      */     //   666: aload 13
/*      */     //   668: aload 6
/*      */     //   670: ldc_w 294
/*      */     //   673: invokeinterface 212 2 0
/*      */     //   678: invokestatic 304	com/claro/util/Utils:setFormatoDecimal	(Ljava/lang/String;)Ljava/lang/String;
/*      */     //   681: invokevirtual 361	com/claro/transfer/RedencionTO:setPrecioConFormato	(Ljava/lang/String;)V
/*      */     //   684: aload 13
/*      */     //   686: aload 6
/*      */     //   688: ldc_w 362
/*      */     //   691: invokeinterface 212 2 0
/*      */     //   696: invokevirtual 364	com/claro/transfer/RedencionTO:setFolio	(Ljava/lang/String;)V
/*      */     //   699: aload 13
/*      */     //   701: aload 6
/*      */     //   703: ldc_w 256
/*      */     //   706: invokeinterface 258 2 0
/*      */     //   711: invokevirtual 262	com/claro/transfer/RedencionTO:setFechaOperacion	(Ljava/util/Date;)V
/*      */     //   714: aload 13
/*      */     //   716: aload 17
/*      */     //   718: invokevirtual 367	com/claro/transfer/RedencionTO:setUsuarioTO	(Lcom/claro/transfer/UsuarioTO;)V
/*      */     //   721: aload 13
/*      */     //   723: aload 16
/*      */     //   725: invokevirtual 371	com/claro/transfer/RedencionTO:setProductosTO	(Lcom/claro/transfer/ProductosTO;)V
/*      */     //   728: aload 13
/*      */     //   730: aload 14
/*      */     //   732: invokevirtual 226	com/claro/transfer/RedencionTO:setTelefonoSimpleTO	(Lcom/claro/transfer/TelefonoSimpleTO;)V
/*      */     //   735: aload 13
/*      */     //   737: aload 15
/*      */     //   739: invokevirtual 252	com/claro/transfer/RedencionTO:setPuntosRedimidosTO	(Lcom/claro/transfer/PuntosRedimidosTO;)V
/*      */     //   742: aload 12
/*      */     //   744: aload 13
/*      */     //   746: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   749: pop
/*      */     //   750: aload 6
/*      */     //   752: invokeinterface 378 1 0
/*      */     //   757: ifne -467 -> 290
/*      */     //   760: goto +148 -> 908
/*      */     //   763: astore 13
/*      */     //   765: new 85	com/claro/exception/CAException
/*      */     //   768: dup
/*      */     //   769: iconst_m1
/*      */     //   770: new 382	java/lang/StringBuilder
/*      */     //   773: dup
/*      */     //   774: ldc_w 384
/*      */     //   777: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   780: aload 13
/*      */     //   782: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   785: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   788: ldc_w 394
/*      */     //   791: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   794: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   797: aload 13
/*      */     //   799: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   802: athrow
/*      */     //   803: astore 13
/*      */     //   805: new 85	com/claro/exception/CAException
/*      */     //   808: dup
/*      */     //   809: iconst_m1
/*      */     //   810: new 382	java/lang/StringBuilder
/*      */     //   813: dup
/*      */     //   814: ldc_w 400
/*      */     //   817: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   820: aload 13
/*      */     //   822: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   825: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   828: ldc_w 394
/*      */     //   831: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   834: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   837: aload 13
/*      */     //   839: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   842: athrow
/*      */     //   843: astore 18
/*      */     //   845: aload 6
/*      */     //   847: ifnull +18 -> 865
/*      */     //   850: aload 6
/*      */     //   852: invokeinterface 403 1 0
/*      */     //   857: aconst_null
/*      */     //   858: astore 6
/*      */     //   860: goto +5 -> 865
/*      */     //   863: astore 19
/*      */     //   865: aload 5
/*      */     //   867: ifnull +18 -> 885
/*      */     //   870: aload 5
/*      */     //   872: invokeinterface 406 1 0
/*      */     //   877: aconst_null
/*      */     //   878: astore 5
/*      */     //   880: goto +5 -> 885
/*      */     //   883: astore 19
/*      */     //   885: aload 4
/*      */     //   887: ifnull +18 -> 905
/*      */     //   890: aload 4
/*      */     //   892: invokeinterface 407 1 0
/*      */     //   897: aconst_null
/*      */     //   898: astore 4
/*      */     //   900: goto +5 -> 905
/*      */     //   903: astore 19
/*      */     //   905: aload 18
/*      */     //   907: athrow
/*      */     //   908: aload 6
/*      */     //   910: ifnull +18 -> 928
/*      */     //   913: aload 6
/*      */     //   915: invokeinterface 403 1 0
/*      */     //   920: aconst_null
/*      */     //   921: astore 6
/*      */     //   923: goto +5 -> 928
/*      */     //   926: astore 19
/*      */     //   928: aload 5
/*      */     //   930: ifnull +18 -> 948
/*      */     //   933: aload 5
/*      */     //   935: invokeinterface 406 1 0
/*      */     //   940: aconst_null
/*      */     //   941: astore 5
/*      */     //   943: goto +5 -> 948
/*      */     //   946: astore 19
/*      */     //   948: aload 4
/*      */     //   950: ifnull +18 -> 968
/*      */     //   953: aload 4
/*      */     //   955: invokeinterface 407 1 0
/*      */     //   960: aconst_null
/*      */     //   961: astore 4
/*      */     //   963: goto +5 -> 968
/*      */     //   966: astore 19
/*      */     //   968: aload 12
/*      */     //   970: areturn
/*      */     // Line number table:
/*      */     //   Java source line #90	-> byte code offset #0
/*      */     //   Java source line #91	-> byte code offset #3
/*      */     //   Java source line #92	-> byte code offset #6
/*      */     //   Java source line #93	-> byte code offset #9
/*      */     //   Java source line #94	-> byte code offset #12
/*      */     //   Java source line #96	-> byte code offset #15
/*      */     //   Java source line #97	-> byte code offset #19
/*      */     //   Java source line #98	-> byte code offset #23
/*      */     //   Java source line #99	-> byte code offset #32
/*      */     //   Java source line #100	-> byte code offset #43
/*      */     //   Java source line #101	-> byte code offset #55
/*      */     //   Java source line #104	-> byte code offset #59
/*      */     //   Java source line #105	-> byte code offset #68
/*      */     //   Java source line #106	-> byte code offset #76
/*      */     //   Java source line #107	-> byte code offset #84
/*      */     //   Java source line #108	-> byte code offset #92
/*      */     //   Java source line #109	-> byte code offset #100
/*      */     //   Java source line #110	-> byte code offset #132
/*      */     //   Java source line #111	-> byte code offset #140
/*      */     //   Java source line #112	-> byte code offset #148
/*      */     //   Java source line #113	-> byte code offset #156
/*      */     //   Java source line #114	-> byte code offset #164
/*      */     //   Java source line #115	-> byte code offset #172
/*      */     //   Java source line #117	-> byte code offset #180
/*      */     //   Java source line #119	-> byte code offset #189
/*      */     //   Java source line #120	-> byte code offset #200
/*      */     //   Java source line #122	-> byte code offset #214
/*      */     //   Java source line #123	-> byte code offset #223
/*      */     //   Java source line #124	-> byte code offset #232
/*      */     //   Java source line #125	-> byte code offset #245
/*      */     //   Java source line #126	-> byte code offset #254
/*      */     //   Java source line #127	-> byte code offset #261
/*      */     //   Java source line #129	-> byte code offset #278
/*      */     //   Java source line #130	-> byte code offset #287
/*      */     //   Java source line #131	-> byte code offset #290
/*      */     //   Java source line #133	-> byte code offset #299
/*      */     //   Java source line #134	-> byte code offset #308
/*      */     //   Java source line #135	-> byte code offset #322
/*      */     //   Java source line #137	-> byte code offset #336
/*      */     //   Java source line #139	-> byte code offset #343
/*      */     //   Java source line #140	-> byte code offset #352
/*      */     //   Java source line #141	-> byte code offset #366
/*      */     //   Java source line #142	-> byte code offset #383
/*      */     //   Java source line #144	-> byte code offset #390
/*      */     //   Java source line #145	-> byte code offset #405
/*      */     //   Java source line #146	-> byte code offset #414
/*      */     //   Java source line #147	-> byte code offset #429
/*      */     //   Java source line #148	-> byte code offset #444
/*      */     //   Java source line #149	-> byte code offset #459
/*      */     //   Java source line #150	-> byte code offset #474
/*      */     //   Java source line #151	-> byte code offset #489
/*      */     //   Java source line #152	-> byte code offset #504
/*      */     //   Java source line #153	-> byte code offset #522
/*      */     //   Java source line #154	-> byte code offset #540
/*      */     //   Java source line #155	-> byte code offset #555
/*      */     //   Java source line #156	-> byte code offset #570
/*      */     //   Java source line #157	-> byte code offset #579
/*      */     //   Java source line #158	-> byte code offset #594
/*      */     //   Java source line #160	-> byte code offset #606
/*      */     //   Java source line #161	-> byte code offset #621
/*      */     //   Java source line #162	-> byte code offset #636
/*      */     //   Java source line #163	-> byte code offset #651
/*      */     //   Java source line #164	-> byte code offset #666
/*      */     //   Java source line #166	-> byte code offset #684
/*      */     //   Java source line #167	-> byte code offset #699
/*      */     //   Java source line #168	-> byte code offset #714
/*      */     //   Java source line #169	-> byte code offset #721
/*      */     //   Java source line #170	-> byte code offset #728
/*      */     //   Java source line #171	-> byte code offset #735
/*      */     //   Java source line #173	-> byte code offset #742
/*      */     //   Java source line #130	-> byte code offset #750
/*      */     //   Java source line #175	-> byte code offset #763
/*      */     //   Java source line #176	-> byte code offset #765
/*      */     //   Java source line #177	-> byte code offset #803
/*      */     //   Java source line #178	-> byte code offset #805
/*      */     //   Java source line #179	-> byte code offset #843
/*      */     //   Java source line #180	-> byte code offset #845
/*      */     //   Java source line #181	-> byte code offset #865
/*      */     //   Java source line #182	-> byte code offset #885
/*      */     //   Java source line #183	-> byte code offset #905
/*      */     //   Java source line #180	-> byte code offset #908
/*      */     //   Java source line #181	-> byte code offset #928
/*      */     //   Java source line #182	-> byte code offset #948
/*      */     //   Java source line #184	-> byte code offset #968
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	971	0	this	ConsultasDAO
/*      */     //   0	971	1	cuenta	String
/*      */     //   0	971	2	secuencia	int
/*      */     //   0	971	3	fecha	String
/*      */     //   1	961	4	connection	Connection
/*      */     //   4	938	5	preparedStatement	PreparedStatement
/*      */     //   7	915	6	resultSet	ResultSet
/*      */     //   10	259	7	fechaOperacion	long
/*      */     //   13	242	9	fechaOper	java.util.Date
/*      */     //   17	216	10	busqueda	String
/*      */     //   66	137	11	query	StringBuffer
/*      */     //   187	782	12	redenciones	ArrayList<com.claro.transfer.RedencionTO>
/*      */     //   297	448	13	redencionTO	com.claro.transfer.RedencionTO
/*      */     //   763	35	13	e	SQLException
/*      */     //   803	35	13	e	Exception
/*      */     //   306	425	14	telefonoSimpleTO	TelefonoSimpleTO
/*      */     //   350	388	15	puntosRedimidosTO	com.claro.transfer.PuntosRedimidosTO
/*      */     //   412	312	16	productosTO	ProductosTO
/*      */     //   577	140	17	usuarioTO	UsuarioTO
/*      */     //   843	63	18	localObject	Object
/*      */     //   863	1	19	localException1	Exception
/*      */     //   883	1	19	localException2	Exception
/*      */     //   903	1	19	localException3	Exception
/*      */     //   926	1	19	localException4	Exception
/*      */     //   946	1	19	localException5	Exception
/*      */     //   966	1	19	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   189	760	763	java/sql/SQLException
/*      */     //   189	760	803	java/lang/Exception
/*      */     //   189	843	843	finally
/*      */     //   850	860	863	java/lang/Exception
/*      */     //   870	880	883	java/lang/Exception
/*      */     //   890	900	903	java/lang/Exception
/*      */     //   913	923	926	java/lang/Exception
/*      */     //   933	943	946	java/lang/Exception
/*      */     //   953	963	966	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.MovimientoTO> detalleMovimientos(String cuenta, String secuencia)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aconst_null
/*      */     //   3: astore 4
/*      */     //   5: aconst_null
/*      */     //   6: astore 5
/*      */     //   8: new 114	java/lang/StringBuffer
/*      */     //   11: dup
/*      */     //   12: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   15: astore 6
/*      */     //   17: aload 6
/*      */     //   19: ldc_w 445
/*      */     //   22: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   25: pop
/*      */     //   26: aload 6
/*      */     //   28: ldc_w 447
/*      */     //   31: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   34: pop
/*      */     //   35: aload 6
/*      */     //   37: ldc_w 449
/*      */     //   40: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   43: pop
/*      */     //   44: aload 6
/*      */     //   46: ldc_w 451
/*      */     //   49: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   52: pop
/*      */     //   53: aload 6
/*      */     //   55: ldc_w 453
/*      */     //   58: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   61: aload_0
/*      */     //   62: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   65: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   68: ldc_w 455
/*      */     //   71: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   74: aload_0
/*      */     //   75: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   78: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   81: ldc_w 457
/*      */     //   84: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   87: pop
/*      */     //   88: aload 6
/*      */     //   90: ldc_w 459
/*      */     //   93: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   96: aload_0
/*      */     //   97: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   100: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   103: ldc_w 461
/*      */     //   106: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   109: pop
/*      */     //   110: aload 6
/*      */     //   112: ldc_w 463
/*      */     //   115: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   118: pop
/*      */     //   119: aload 6
/*      */     //   121: ldc_w 465
/*      */     //   124: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   127: pop
/*      */     //   128: aload 6
/*      */     //   130: ldc_w 467
/*      */     //   133: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   136: pop
/*      */     //   137: aload 6
/*      */     //   139: ldc_w 469
/*      */     //   142: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   145: pop
/*      */     //   146: aload 6
/*      */     //   148: ldc_w 471
/*      */     //   151: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   154: pop
/*      */     //   155: aload 6
/*      */     //   157: ldc_w 473
/*      */     //   160: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   163: pop
/*      */     //   164: new 145	java/util/ArrayList
/*      */     //   167: dup
/*      */     //   168: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   171: astore 7
/*      */     //   173: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   176: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   179: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   182: astore 5
/*      */     //   184: aload 5
/*      */     //   186: aload 6
/*      */     //   188: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   191: invokeinterface 158 2 0
/*      */     //   196: astore_3
/*      */     //   197: aload_3
/*      */     //   198: iconst_1
/*      */     //   199: aload_1
/*      */     //   200: invokeinterface 164 3 0
/*      */     //   205: aload_3
/*      */     //   206: iconst_2
/*      */     //   207: aload_2
/*      */     //   208: invokeinterface 164 3 0
/*      */     //   213: aload_3
/*      */     //   214: invokeinterface 200 1 0
/*      */     //   219: astore 4
/*      */     //   221: goto +213 -> 434
/*      */     //   224: new 475	com/claro/transfer/MovimientoTO
/*      */     //   227: dup
/*      */     //   228: invokespecial 477	com/claro/transfer/MovimientoTO:<init>	()V
/*      */     //   231: astore 8
/*      */     //   233: aload 8
/*      */     //   235: aload 4
/*      */     //   237: ldc -46
/*      */     //   239: invokeinterface 212 2 0
/*      */     //   244: invokevirtual 478	com/claro/transfer/MovimientoTO:setCuenta	(Ljava/lang/String;)V
/*      */     //   247: aload 8
/*      */     //   249: aload 4
/*      */     //   251: ldc_w 479
/*      */     //   254: invokeinterface 212 2 0
/*      */     //   259: invokevirtual 481	com/claro/transfer/MovimientoTO:setSecuencia	(Ljava/lang/String;)V
/*      */     //   262: aload 8
/*      */     //   264: aload 4
/*      */     //   266: ldc -35
/*      */     //   268: invokeinterface 212 2 0
/*      */     //   273: invokevirtual 484	com/claro/transfer/MovimientoTO:setLinea	(Ljava/lang/String;)V
/*      */     //   276: aload 8
/*      */     //   278: aload 4
/*      */     //   280: ldc_w 485
/*      */     //   283: invokeinterface 258 2 0
/*      */     //   288: invokevirtual 487	com/claro/transfer/MovimientoTO:setFechaOperacion	(Ljava/sql/Date;)V
/*      */     //   291: aload 8
/*      */     //   293: aload 4
/*      */     //   295: ldc_w 490
/*      */     //   298: invokeinterface 258 2 0
/*      */     //   303: invokevirtual 492	com/claro/transfer/MovimientoTO:setFacturacion	(Ljava/sql/Date;)V
/*      */     //   306: aload 8
/*      */     //   308: aload 4
/*      */     //   310: ldc_w 324
/*      */     //   313: invokeinterface 212 2 0
/*      */     //   318: invokevirtual 495	com/claro/transfer/MovimientoTO:setUsuario	(Ljava/lang/String;)V
/*      */     //   321: aload 8
/*      */     //   323: aload 4
/*      */     //   325: ldc_w 498
/*      */     //   328: invokeinterface 212 2 0
/*      */     //   333: invokevirtual 500	com/claro/transfer/MovimientoTO:setMovimiento	(Ljava/lang/String;)V
/*      */     //   336: aload 8
/*      */     //   338: aload 4
/*      */     //   340: ldc_w 503
/*      */     //   343: invokeinterface 235 2 0
/*      */     //   348: invokevirtual 505	com/claro/transfer/MovimientoTO:setNumPuntos	(I)V
/*      */     //   351: aload 8
/*      */     //   353: aload 4
/*      */     //   355: ldc_w 508
/*      */     //   358: invokeinterface 235 2 0
/*      */     //   363: invokevirtual 510	com/claro/transfer/MovimientoTO:setNumPuntosExc	(I)V
/*      */     //   366: aload 8
/*      */     //   368: aload 4
/*      */     //   370: ldc_w 513
/*      */     //   373: invokeinterface 235 2 0
/*      */     //   378: invokevirtual 515	com/claro/transfer/MovimientoTO:setTotalAjustes	(I)V
/*      */     //   381: aload 8
/*      */     //   383: aload 4
/*      */     //   385: ldc_w 518
/*      */     //   388: invokeinterface 212 2 0
/*      */     //   393: invokevirtual 520	com/claro/transfer/MovimientoTO:setBonoPromocion	(Ljava/lang/String;)V
/*      */     //   396: aload 8
/*      */     //   398: aload 4
/*      */     //   400: ldc_w 523
/*      */     //   403: invokeinterface 212 2 0
/*      */     //   408: invokevirtual 525	com/claro/transfer/MovimientoTO:setReferencia	(Ljava/lang/String;)V
/*      */     //   411: aload 8
/*      */     //   413: aload 4
/*      */     //   415: ldc_w 341
/*      */     //   418: invokeinterface 258 2 0
/*      */     //   423: invokevirtual 528	com/claro/transfer/MovimientoTO:setFechaAdendum	(Ljava/sql/Date;)V
/*      */     //   426: aload 7
/*      */     //   428: aload 8
/*      */     //   430: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   433: pop
/*      */     //   434: aload 4
/*      */     //   436: invokeinterface 378 1 0
/*      */     //   441: ifne -217 -> 224
/*      */     //   444: goto +145 -> 589
/*      */     //   447: astore 8
/*      */     //   449: new 85	com/claro/exception/CAException
/*      */     //   452: dup
/*      */     //   453: iconst_m1
/*      */     //   454: new 382	java/lang/StringBuilder
/*      */     //   457: dup
/*      */     //   458: ldc_w 530
/*      */     //   461: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   464: aload 8
/*      */     //   466: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   469: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   472: ldc_w 394
/*      */     //   475: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   478: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   481: aload 8
/*      */     //   483: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   486: athrow
/*      */     //   487: astore 8
/*      */     //   489: new 85	com/claro/exception/CAException
/*      */     //   492: dup
/*      */     //   493: iconst_m1
/*      */     //   494: new 382	java/lang/StringBuilder
/*      */     //   497: dup
/*      */     //   498: ldc_w 532
/*      */     //   501: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   504: aload 8
/*      */     //   506: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   509: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   512: ldc_w 394
/*      */     //   515: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   518: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   521: aload 8
/*      */     //   523: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   526: athrow
/*      */     //   527: astore 9
/*      */     //   529: aload 4
/*      */     //   531: ifnull +18 -> 549
/*      */     //   534: aload 4
/*      */     //   536: invokeinterface 403 1 0
/*      */     //   541: aconst_null
/*      */     //   542: astore 4
/*      */     //   544: goto +5 -> 549
/*      */     //   547: astore 10
/*      */     //   549: aload_3
/*      */     //   550: ifnull +16 -> 566
/*      */     //   553: aload_3
/*      */     //   554: invokeinterface 406 1 0
/*      */     //   559: aconst_null
/*      */     //   560: astore_3
/*      */     //   561: goto +5 -> 566
/*      */     //   564: astore 10
/*      */     //   566: aload 5
/*      */     //   568: ifnull +18 -> 586
/*      */     //   571: aload 5
/*      */     //   573: invokeinterface 407 1 0
/*      */     //   578: aconst_null
/*      */     //   579: astore 5
/*      */     //   581: goto +5 -> 586
/*      */     //   584: astore 10
/*      */     //   586: aload 9
/*      */     //   588: athrow
/*      */     //   589: aload 4
/*      */     //   591: ifnull +18 -> 609
/*      */     //   594: aload 4
/*      */     //   596: invokeinterface 403 1 0
/*      */     //   601: aconst_null
/*      */     //   602: astore 4
/*      */     //   604: goto +5 -> 609
/*      */     //   607: astore 10
/*      */     //   609: aload_3
/*      */     //   610: ifnull +16 -> 626
/*      */     //   613: aload_3
/*      */     //   614: invokeinterface 406 1 0
/*      */     //   619: aconst_null
/*      */     //   620: astore_3
/*      */     //   621: goto +5 -> 626
/*      */     //   624: astore 10
/*      */     //   626: aload 5
/*      */     //   628: ifnull +18 -> 646
/*      */     //   631: aload 5
/*      */     //   633: invokeinterface 407 1 0
/*      */     //   638: aconst_null
/*      */     //   639: astore 5
/*      */     //   641: goto +5 -> 646
/*      */     //   644: astore 10
/*      */     //   646: aload 7
/*      */     //   648: areturn
/*      */     // Line number table:
/*      */     //   Java source line #190	-> byte code offset #0
/*      */     //   Java source line #191	-> byte code offset #2
/*      */     //   Java source line #192	-> byte code offset #5
/*      */     //   Java source line #196	-> byte code offset #8
/*      */     //   Java source line #198	-> byte code offset #17
/*      */     //   Java source line #199	-> byte code offset #26
/*      */     //   Java source line #200	-> byte code offset #35
/*      */     //   Java source line #201	-> byte code offset #44
/*      */     //   Java source line #202	-> byte code offset #53
/*      */     //   Java source line #203	-> byte code offset #88
/*      */     //   Java source line #204	-> byte code offset #110
/*      */     //   Java source line #205	-> byte code offset #119
/*      */     //   Java source line #207	-> byte code offset #128
/*      */     //   Java source line #208	-> byte code offset #137
/*      */     //   Java source line #209	-> byte code offset #146
/*      */     //   Java source line #210	-> byte code offset #155
/*      */     //   Java source line #211	-> byte code offset #164
/*      */     //   Java source line #213	-> byte code offset #173
/*      */     //   Java source line #214	-> byte code offset #184
/*      */     //   Java source line #215	-> byte code offset #197
/*      */     //   Java source line #216	-> byte code offset #205
/*      */     //   Java source line #219	-> byte code offset #213
/*      */     //   Java source line #221	-> byte code offset #221
/*      */     //   Java source line #222	-> byte code offset #224
/*      */     //   Java source line #223	-> byte code offset #233
/*      */     //   Java source line #224	-> byte code offset #247
/*      */     //   Java source line #225	-> byte code offset #262
/*      */     //   Java source line #226	-> byte code offset #276
/*      */     //   Java source line #227	-> byte code offset #291
/*      */     //   Java source line #228	-> byte code offset #306
/*      */     //   Java source line #229	-> byte code offset #321
/*      */     //   Java source line #230	-> byte code offset #336
/*      */     //   Java source line #231	-> byte code offset #351
/*      */     //   Java source line #232	-> byte code offset #366
/*      */     //   Java source line #233	-> byte code offset #381
/*      */     //   Java source line #234	-> byte code offset #396
/*      */     //   Java source line #235	-> byte code offset #411
/*      */     //   Java source line #236	-> byte code offset #426
/*      */     //   Java source line #221	-> byte code offset #434
/*      */     //   Java source line #239	-> byte code offset #447
/*      */     //   Java source line #240	-> byte code offset #449
/*      */     //   Java source line #241	-> byte code offset #487
/*      */     //   Java source line #242	-> byte code offset #489
/*      */     //   Java source line #243	-> byte code offset #527
/*      */     //   Java source line #244	-> byte code offset #529
/*      */     //   Java source line #245	-> byte code offset #549
/*      */     //   Java source line #246	-> byte code offset #566
/*      */     //   Java source line #247	-> byte code offset #586
/*      */     //   Java source line #244	-> byte code offset #589
/*      */     //   Java source line #245	-> byte code offset #609
/*      */     //   Java source line #246	-> byte code offset #626
/*      */     //   Java source line #248	-> byte code offset #646
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	649	0	this	ConsultasDAO
/*      */     //   0	649	1	cuenta	String
/*      */     //   0	649	2	secuencia	String
/*      */     //   1	620	3	statement	PreparedStatement
/*      */     //   3	600	4	resultSet	ResultSet
/*      */     //   6	634	5	connection	Connection
/*      */     //   15	172	6	query	StringBuffer
/*      */     //   171	476	7	movimietos	ArrayList<com.claro.transfer.MovimientoTO>
/*      */     //   231	198	8	movimientoTO	com.claro.transfer.MovimientoTO
/*      */     //   447	35	8	e	SQLException
/*      */     //   487	35	8	e	Exception
/*      */     //   527	60	9	localObject	Object
/*      */     //   547	1	10	localException1	Exception
/*      */     //   564	1	10	localException2	Exception
/*      */     //   584	1	10	localException3	Exception
/*      */     //   607	1	10	localException4	Exception
/*      */     //   624	1	10	localException5	Exception
/*      */     //   644	1	10	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   173	444	447	java/sql/SQLException
/*      */     //   173	444	487	java/lang/Exception
/*      */     //   173	527	527	finally
/*      */     //   534	544	547	java/lang/Exception
/*      */     //   553	561	564	java/lang/Exception
/*      */     //   571	581	584	java/lang/Exception
/*      */     //   594	604	607	java/lang/Exception
/*      */     //   613	621	624	java/lang/Exception
/*      */     //   631	641	644	java/lang/Exception
/*      */   }
/*      */   
/*      */   public PuntoVentaTO obtienePuntoVenta(String sIP, Connection connection)
/*      */     throws CAException
/*      */   {
/*  259 */     PreparedStatement preparedStatement = null;
/*  260 */     ResultSet resultSet = null;
/*  261 */     Connection lConn = null;
/*      */     try {
/*  263 */       if (connection != null) lConn = connection; else
/*  264 */         lConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  265 */       String sQuery = "SELECT IDPUNTOVTA, IVA_PORCENTAJE, IDREGION, TIPOPUNTOVTA FROM " + this.schema_database + "PTO_CTLPUNTOSVTA " + 
/*  266 */         " WHERE SEGMENTOIP = ? AND RANGOINF <= ? AND RANGOSUP >=?";
/*  267 */       int nPos = sIP.lastIndexOf(".");
/*  268 */       String sRango = sIP.substring(nPos + 1);
/*      */       
/*  270 */       preparedStatement = lConn.prepareStatement(sQuery);
/*  271 */       preparedStatement.setString(1, sIP.substring(0, nPos));
/*  272 */       preparedStatement.setString(2, sRango);
/*  273 */       preparedStatement.setString(3, sRango);
/*  274 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  276 */       if (resultSet.next()) {
/*  277 */         PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
/*  278 */         puntoVentaTO.setPtoVenta(resultSet.getString(1));
/*  279 */         puntoVentaTO.setPorcentajeIva(resultSet.getString(2));
/*  280 */         puntoVentaTO.setIdRegion(resultSet.getInt(3));
/*  281 */         puntoVentaTO.setTipoPuntovta(resultSet.getString(4));
/*  282 */         return puntoVentaTO; }
/*  283 */       throw new CAException(-1, "El punto de venta no se encuentra dado de alta en el sistema de puntos.");
/*      */     }
/*      */     catch (SQLException e)
/*      */     {
/*  287 */       throw new CAException(-1, "SQLException.obtienePuntoVenta[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  289 */       throw new CAException(-1, "ConsultasDAO.obtienePuntoVenta[" + e.toString() + "]", e);
/*      */     } finally {
/*  291 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException4) {}
/*  292 */       if (preparedStatement != null) try { preparedStatement.close();preparedStatement = null; } catch (Exception localException5) {}
/*  293 */       if ((connection == null) && (lConn != null)) { try { lConn.close();lConn = null;
/*      */         }
/*      */         catch (Exception localException6) {}
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TelefonoTO procedimientoGeneral(ParametrosTO parametrosTO, PerfilTO perfilTO, String fzaVentas)
/*      */     throws CAException
/*      */   {
/*  308 */     Connection connection = null;
/*  309 */     TelefonoTO telefonoTO = new TelefonoTO();
/*  310 */     CatalogoDAO catalogoDAO = new CatalogoDAO();
/*  311 */     PlanTO planTO = null;
/*      */     
/*  313 */     telefonoTO.setAplicaRedencion(true);
/*  314 */     boolean lineaNueva = false;
/*      */     try {
/*  316 */       telefonoTO.agregaMensaje(0, "Proceso Exitoso");
/*  317 */       long inicioProceso = System.currentTimeMillis();
/*  318 */       this.logger.info("procedimientoGeneral|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso);
/*      */       
/*      */       TelefonoTO localTelefonoTO1;
/*  321 */       if ((parametrosTO.getCuenta() == null) && (parametrosTO.getTelefono() == null)) {
/*  322 */         telefonoTO.agregaMensaje(1, "Debe especificar el telefono a consultar.");
/*  323 */         return telefonoTO;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  328 */       if (parametrosTO.getRegion() == 0)
/*      */       {
/*  330 */         Catalogo properties = new Catalogo();
/*  331 */         properties.setTabla("propiedades");
/*  332 */         properties.cargaCatalogo();
/*      */         
/*      */ 
/*  335 */         lineaNueva = true;
/*  336 */         ClienteConsultas consulta = new ClienteConsultas();
/*      */         
/*      */ 
/*  339 */         if (parametrosTO.getTelefono() != null) {
/*  340 */           parametrosTO.setRegion(consulta.consultaRegionPorLinea(properties.getPropiedad("endpoint.consultas.m2k.crm"), parametrosTO.getTelefono()));
/*      */         } else {
/*  342 */           parametrosTO.setRegion(consulta.consultaRegionPorCuenta(properties.getPropiedad("endpoint.consultas.m2k.crm"), parametrosTO.getCuenta()));
/*      */         }
/*  344 */         telefonoTO.setRegion(parametrosTO.getRegion());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  349 */       MobileTO mobileTO = this.consultaM2KDAO.consultaDatosM2K(parametrosTO);
/*      */       
/*      */ 
/*  352 */       if (mobileTO.getIdMensaje() == -1) {
/*  353 */         telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
/*  354 */         return telefonoTO;
/*      */       }
/*      */       
/*  357 */       if (parametrosTO.getMesCareg() != null) {
/*  358 */         mobileTO.setMesesCareg(parametrosTO.getMesCareg());
/*      */       } else {
/*  360 */         mobileTO.setMesesCareg("0");
/*      */       }
/*      */       
/*  363 */       if (parametrosTO.getAddCareg() != null) {
/*  364 */         mobileTO.setAddCareg(parametrosTO.getAddCareg());
/*      */       } else {
/*  366 */         mobileTO.setAddCareg("0");
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*  374 */         planTO = catalogoDAO.consultaPlan(mobileTO.getPlanM2K(), parametrosTO.getRegion());
/*  375 */         if ((planTO != null) && (planTO.getIdMensaje() == 0) && (fzaVentas == null)) {
/*  376 */           this.consultasGapDAO.consultaAnacrGap(mobileTO, telefonoTO, planTO, parametrosTO);
/*      */         } else {
/*  378 */           telefonoTO.setSAnacr("0.9");
/*      */         }
/*      */       } catch (CAException exception) {
/*  381 */         this.error.info("Exception.procedimientoGeneral:", exception);
/*      */       }
/*      */       
/*      */ 
/*  385 */       long tiempoConexionCA = System.currentTimeMillis();
/*      */       
/*  387 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  392 */       if (lineaNueva)
/*      */       {
/*  394 */         validaDatosLineaM2K(parametrosTO, mobileTO, connection, telefonoTO);
/*      */       }
/*      */       else {
/*  397 */         this.puntosDAO.actualizaDatosLineaM2K(mobileTO, connection, telefonoTO);
/*      */       }
/*      */       
/*  400 */       if (mobileTO.getIdMensaje() == -1) {
/*  401 */         telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
/*  402 */         return telefonoTO;
/*      */       }
/*      */       
/*      */ 
/*  406 */       if (parametrosTO.getTipoRed() != null) {
/*  407 */         telefonoTO.setTipoRedencion(parametrosTO.getTipoRed());
/*      */       }
/*      */       
/*      */ 
/*  411 */       telefonoTO.setMobileTO(mobileTO);
/*      */       
/*      */ 
/*  414 */       consultaDatosPuntos(parametrosTO, telefonoTO, mobileTO, connection);
/*      */       
/*      */ 
/*  417 */       if (telefonoTO.getIdMensaje() != 0) {
/*  418 */         return telefonoTO;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  426 */       if ((parametrosTO.getTipoRed() == null) || ((!parametrosTO.getTipoRed().trim().equals("PTS")) && (!parametrosTO.getTipoRed().trim().equals("CAN")) && (!parametrosTO.getTipoRed().trim().equals("IMP")))) {
/*  427 */         if (Utils.getValEstatusTel(telefonoTO.getMobileTO().getStatus(), telefonoTO.getMobileTO().getMotivo())) {
/*  428 */           telefonoTO.setAplicaRedencion(false);
/*  429 */           telefonoTO.agregaMensaje(2, "El Estatus del telefono es Invalido -- " + telefonoTO.getMobileTO().getStatus() + 
/*  430 */             " -- Motivo: " + telefonoTO.getMobileTO().getMotivo());
/*  431 */         } else if (!Utils.getValEstatusCobranza(telefonoTO.getMobileTO().getEstCobranza(), telefonoTO.getRegion(), "Reden", telefonoTO.getMobileTO().getMotivo(), null)) {
/*  432 */           telefonoTO.setAplicaRedencion(false);
/*  433 */           telefonoTO.agregaMensaje(2, "Estatus de cobranza invalido para realizar tramites.  " + telefonoTO.getMobileTO().getEstCobranza());
/*  434 */         } else if (telefonoTO.getPuntosTO().getPtosStatus().equals("R")) {
/*  435 */           telefonoTO.setAplicaRedencion(false);
/*  436 */           telefonoTO.agregaMensaje(2, "Los Puntos estan " + telefonoTO.getPuntosTO().getDescPtsReservados());
/*  437 */         } else if (telefonoTO.getPuntosTO().getPtosStatus().trim().equals("C")) {
/*  438 */           telefonoTO.setAplicaRedencion(false);
/*  439 */           telefonoTO.agregaMensaje(2, "Los puntos de la linea se encuentran congelados");
/*  440 */         } else if ((fzaVentas == null) && (!Utils.ValidaCredito(telefonoTO.getMobileTO().getClaseCredit(), telefonoTO.getRegion(), perfilTO))) {
/*  441 */           telefonoTO.setAplicaRedencion(false);
/*  442 */           telefonoTO.agregaMensaje(2, "El perfil del usuario no esta autorizado para realizar el tramite (Clase de credito AR).");
/*      */         }
/*      */         else {
/*  445 */           boolean fzaVentasAutorizada = false;
/*  446 */           String claseCredito = telefonoTO.getMobileTO().getClaseCredit();
/*      */           
/*      */ 
/*  449 */           if (fzaVentas != null) {
/*  450 */             if ((claseCredito.trim().equals("IM")) || (claseCredito.trim().equals("FO"))) {
/*  451 */               DistribuidoresDAO distribuidoresDAO = new DistribuidoresDAO();
/*  452 */               fzaVentasAutorizada = distribuidoresDAO.validaFzaVentasImssFonacot(fzaVentas, telefonoTO.getMobileTO().getClaseCredit(), connection);
/*      */               
/*  454 */               if (!fzaVentasAutorizada) {
/*  455 */                 throw new CAException(-1, "La cuenta tiene una clase de credito " + 
/*  456 */                   telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + 
/*  457 */                   " la redencion de puntos el Distribuidor Autorizado.");
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*  462 */           else if ((claseCredito != null) && (!"".equals(claseCredito)) && 
/*  463 */             (telefonoTO.getRegion() == 9) && (
/*  464 */             (claseCredito.trim().equals("IM")) || (claseCredito.trim().equals("FO")))) {
/*  465 */             telefonoTO.setAplicaRedencion(false);
/*  466 */             telefonoTO.agregaMensaje(2, "La cuenta tiene una clase de credito " + 
/*  467 */               telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + 
/*  468 */               " la redencion de puntos el Distribuidor Autorizado.");
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  476 */         if ((parametrosTO.getTipoRed() == null) && (fzaVentas == null)) {
/*  477 */           AlianzasDAO consultaAmex = new AlianzasDAO();
/*  478 */           consultaAmex.consultaConfirmaCanje(telefonoTO, connection, parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */       }
/*  481 */       this.logger.info("procedimientoGeneral|FinProceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso));
/*      */     }
/*      */     catch (Exception e) {
/*  484 */       e.printStackTrace();
/*  485 */       this.error.info("Exception.procedimientoGeneral:", e);
/*  486 */       throw new CAException(-1, "ConsultaDAO.procedimientoGeneral[" + e.toString() + "]", e);
/*      */     } finally {
/*  488 */       if (connection != null) try { connection.close();connection = null; } catch (Exception localException5) {} } if (connection != null) try { connection.close();connection = null;
/*      */       } catch (Exception localException6) {}
/*  490 */     return telefonoTO;
/*      */   }
/*      */   
/*      */   private void validaDatosLineaM2K(ParametrosTO parametrosTO, MobileTO mobileTO, Connection connection, TelefonoTO telefonoTO)
/*      */     throws CAException
/*      */   {
/*      */     try
/*      */     {
/*  498 */       connection.setAutoCommit(false);
/*  499 */       this.puntosDAO.insertaDatosLineaM2K(mobileTO, connection, telefonoTO);
/*  500 */       this.puntosDAO.insertaTotales(mobileTO, connection);
/*  501 */       consultaDatosPuntos(parametrosTO, telefonoTO, mobileTO, connection);
/*  502 */       if (telefonoTO.getIdMensaje() == 0) {
/*  503 */         connection.commit();
/*      */       } else
/*  505 */         throw new CAException(-1, "La linea no participa en Circulo Azul");
/*  506 */     } catch (SQLException e) { e = e;
/*  507 */       if (connection != null) try { connection.rollback(); } catch (Exception localException1) {}
/*  508 */       throw new CAException(-1, e.toString());
/*  509 */     } catch (Exception e) { e = e;
/*  510 */       if (connection != null) try { connection.rollback(); } catch (Exception localException2) {}
/*  511 */       throw new CAException(-1, e.toString());
/*  512 */     } finally { localObject = finally;
/*  513 */       if (connection != null) try { connection.setAutoCommit(true); } catch (Exception localException3) {}
/*  514 */       throw ((Throwable)localObject);
/*      */     }
/*  513 */     if (connection != null) { try { connection.setAutoCommit(true);
/*      */       }
/*      */       catch (Exception localException4) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void consultaDatosPuntos(ParametrosTO parametrosTO, TelefonoTO telefonoTO, MobileTO mobileTO, Connection connection)
/*      */     throws CAException
/*      */   {
/*  530 */     String sBusqueda = null;
/*  531 */     StringBuffer query = new StringBuffer();
/*  532 */     CicloFacturacionTO cicloFacturacionTO = null;
/*      */     
/*  534 */     if ((mobileTO.getCuenta() != null) && (parametrosTO.getTelefono() != null) && (!mobileTO.getCuenta().trim().equals("")) && (!parametrosTO.getTelefono().trim().equals(""))) {
/*  535 */       sBusqueda = "a.Linea = '" + parametrosTO.getTelefono() + "' AND " + "a.Cuenta = '" + mobileTO.getCuenta() + "' ";
/*      */     } else {
/*  537 */       if (mobileTO.getCuenta() != null) sBusqueda = "a.Cuenta = '" + mobileTO.getCuenta() + "' ";
/*  538 */       if (parametrosTO.getTelefono() != null) { sBusqueda = "a.Linea = '" + parametrosTO.getTelefono() + "' ";
/*      */       }
/*      */     }
/*  541 */     query.append(" SELECT A.CUENTA, A.SECUENCIA, A.CTAPADRE, A.LINEA, ");
/*  542 */     query.append("        A.FECHAALTA, A.SISTEMA, A.STATUSPUNTOS,A.IDREGION,");
/*  543 */     query.append("        A.CICLOFACT,A.MODSUBASTA, A.PLAN, A.ADDENDUM,");
/*  544 */     query.append("        B.FECHAFAC, B.PUNTOSREDIM, B.PUNTOSTRANSF, ");
/*  545 */     query.append("        B.PUNTOSCADUC, B.PUNTOSACAD,B.PUNTOSACAD1, ");
/*  546 */     query.append("        B.PUNTOSACAD2, B.PUNTOSRENTA, B.PUNTOSEXCEDENTES,");
/*  547 */     query.append("        B.PUNTOSANTIGUEDAD, B.PUNTOSPROMOCION,B.FECHACAD,");
/*  548 */     query.append("        B.FECHACAD2,B.FECHACAD1,B.FECHACADU, B.SALDOANT,");
/*  549 */     query.append("        B.PUNTOSANTIG, C.TECNOLOGIA,");
/*  550 */     query.append("        B.BONOEQUIPO, C.RENTA,C.IDSEGMENTO,D.SEGMENTO,");
/*  551 */     query.append("        A.FECHAADD,A.ANACR,C.DESCRIPCION, C.BMIXTO, C.MODALIDAD ");
/*  552 */     query.append(" FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLTOTALES B,");
/*  553 */     query.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C,  ").append(this.schema_database).append("PTO_CTLSEGMENTOS D ");
/*  554 */     query.append(" WHERE ").append(sBusqueda);
/*  555 */     query.append("   AND C.ESTATUS = 'A'  ");
/*  556 */     query.append("   AND B.CUENTA = A.CUENTA AND B.SECUENCIA = A.SECUENCIA ");
/*  557 */     query.append("   AND C.IDPLAN = A.PLAN AND C.IDSEGMENTO = D.IDSEGMENTO ");
/*  558 */     query.append("   AND C.IDREGION = A.IDREGION ");
/*  559 */     query.append(" GROUP BY A.CUENTA, A.SECUENCIA, A.CTAPADRE, A.LINEA, ");
/*  560 */     query.append("   A.FECHAALTA, A.SISTEMA, A.STATUSPUNTOS, A.IDREGION, ");
/*  561 */     query.append("   A.CICLOFACT,A.MODSUBASTA, A.PLAN,A.ADDENDUM, ");
/*  562 */     query.append("   B.FECHAFAC, B.PUNTOSREDIM, B.PUNTOSTRANSF, ");
/*  563 */     query.append("   B.PUNTOSCADUC, B.PUNTOSACAD, B.PUNTOSACAD1,");
/*  564 */     query.append("   B.PUNTOSACAD2, B.PUNTOSRENTA, B.PUNTOSEXCEDENTES,");
/*  565 */     query.append("   B.PUNTOSANTIGUEDAD, B.PUNTOSPROMOCION, B.FECHACAD, ");
/*  566 */     query.append("   B.FECHACAD2, B.FECHACAD1, B.FECHACADU, B.SALDOANT, ");
/*  567 */     query.append("   B.PUNTOSANTIG, C.TECNOLOGIA, ");
/*  568 */     query.append("   B.BONOEQUIPO, C.RENTA,C.IDSEGMENTO, D.SEGMENTO, ");
/*  569 */     query.append("   A.FECHAADD, A.ANACR,C.DESCRIPCION,  C.BMIXTO, C.MODALIDAD ");
/*  570 */     query.append(" ORDER BY A.FECHAADD DESC, A.FECHAALTA DESC ");
/*      */     
/*  572 */     Statement statement = null;Statement statementFvta = null;
/*  573 */     ResultSet resultSet = null;ResultSet resultSetFvta = null;
/*  574 */     PuntosTO oPuntos = null;
/*      */     try {
/*  576 */       long inicioConsulta = System.currentTimeMillis();
/*  577 */       this.logger.info("consultaDatosPuntos|InicioConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioConsulta);
/*  578 */       statement = connection.createStatement(1004, 1007);
/*      */       
/*  580 */       resultSet = statement.executeQuery(query.toString());
/*  581 */       if (resultSet.next())
/*      */       {
/*  583 */         telefonoTO.setCuenta(resultSet.getString("CUENTA").trim());
/*  584 */         telefonoTO.setSecuencia(resultSet.getString("SECUENCIA"));
/*      */         
/*  586 */         telefonoTO.setCtaPadre(resultSet.getString("CTAPADRE").trim());
/*  587 */         telefonoTO.setTelefono(resultSet.getString("LINEA"));
/*  588 */         telefonoTO.setFechaAlta(resultSet.getDate("FECHAALTA"));
/*  589 */         telefonoTO.setSistema(resultSet.getString("SISTEMA"));
/*  590 */         telefonoTO.setRegion(resultSet.getInt("IDREGION"));
/*  591 */         telefonoTO.setCiclo(resultSet.getString("CICLOFACT"));
/*  592 */         telefonoTO.setPlan(resultSet.getString("PLAN"));
/*  593 */         telefonoTO.setAddendum(resultSet.getInt("ADDENDUM"));
/*  594 */         telefonoTO.setFecFactura(resultSet.getDate("FECHAFAC"));
/*  595 */         telefonoTO.setTecnologia(resultSet.getString("TECNOLOGIA"));
/*  596 */         telefonoTO.setBonoEquipo(resultSet.getInt("BONOEQUIPO"));
/*  597 */         telefonoTO.setRenta(resultSet.getInt("RENTA"));
/*  598 */         telefonoTO.setIdSegmento(resultSet.getInt("IDSEGMENTO"));
/*  599 */         telefonoTO.setSegmento(resultSet.getString("SEGMENTO"));
/*  600 */         telefonoTO.setSAnacr(resultSet.getString("ANACR"));
/*  601 */         telefonoTO.setBanSubasta(resultSet.getInt("MODSUBASTA"));
/*  602 */         telefonoTO.setDescripcionPlan(resultSet.getString("DESCRIPCION"));
/*      */         
/*  604 */         if (telefonoTO.getPlanTO() == null) {
/*  605 */           PlanTO planTO = new PlanTO();
/*  606 */           planTO.setBanMixto(resultSet.getString("BMIXTO"));
/*  607 */           planTO.setModalidad(resultSet.getString("MODALIDAD"));
/*      */           
/*  609 */           telefonoTO.setPlanTO(planTO);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  614 */         cicloFacturacionTO = consultaCicloFac(Integer.parseInt(telefonoTO.getCiclo() != null ? telefonoTO.getCiclo().trim() : "0"), telefonoTO.getRegion());
/*      */         
/*  616 */         if ((cicloFacturacionTO != null) && 
/*  617 */           (cicloFacturacionTO.getDescripcion() != null) && ("CORPORATIVO".equals(cicloFacturacionTO.getDescripcion().trim())))
/*  618 */           telefonoTO.agregaMensaje(-1, "Cuenta Corporativa, no participa en Puntos."); }
/*  619 */       for (;;) { return;
/*      */         
/*      */ 
/*      */ 
/*  623 */         oPuntos = new PuntosTO();
/*      */         
/*  625 */         oPuntos.setPtosStatus(resultSet.getString("STATUSPUNTOS"));
/*  626 */         oPuntos.setPtsRedimidos(resultSet.getInt("PUNTOSREDIM"));
/*  627 */         oPuntos.setPtsTransferidos(resultSet.getInt("PUNTOSTRANSF"));
/*  628 */         oPuntos.setPtsVencidos(resultSet.getInt("PUNTOSCADUC"));
/*  629 */         oPuntos.setPtsPorVencer(resultSet.getInt("PUNTOSACAD"));
/*  630 */         oPuntos.setPtsPorVencer1(resultSet.getInt("PUNTOSACAD1"));
/*  631 */         oPuntos.setPtsPorVencer2(resultSet.getInt("PUNTOSACAD2"));
/*  632 */         oPuntos.setPtsRenta(resultSet.getInt("PUNTOSRENTA"));
/*  633 */         oPuntos.setPtsExcedentes(resultSet.getInt("PUNTOSEXCEDENTES"));
/*  634 */         oPuntos.setPtsAntiguedad(resultSet.getInt("PUNTOSANTIGUEDAD"));
/*  635 */         oPuntos.setPtsPromocion(resultSet.getInt("PUNTOSPROMOCION"));
/*  636 */         oPuntos.setFecVencer(resultSet.getDate("FECHACAD"));
/*  637 */         oPuntos.setFecVencer2(resultSet.getDate("FECHACAD2"));
/*  638 */         oPuntos.setFecVencer1(resultSet.getDate("FECHACAD1"));
/*  639 */         oPuntos.setFecVencidos(resultSet.getDate("FECHACADU"));
/*  640 */         oPuntos.setPtsSaldoAnt(resultSet.getInt("SALDOANT"));
/*  641 */         oPuntos.setBonoEquipo(resultSet.getInt("BONOEQUIPO"));
/*      */         
/*  643 */         if ((telefonoTO.getSAnacr() != null) && (telefonoTO.getSAnacr().equals("0.0"))) {
/*  644 */           this.consultaM2KDAO.obtenPromedioFacturaciones(mobileTO, telefonoTO.getRegion(), telefonoTO.getCuenta());
/*      */         }
/*  646 */         if (mobileTO.getIdMensaje() != 0) {
/*  647 */           telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
/*      */         }
/*      */         else
/*      */         {
/*  651 */           if (parametrosTO.getTipoRed() == null) {
/*  652 */             String sFechaVenc = "";
/*  653 */             if (resultSet.getDate(24) != null) {
/*  654 */               sFechaVenc = resultSet.getDate(24).toString();
/*      */             } else
/*  656 */               sFechaVenc = null;
/*  657 */             String sFechaActual = Constantes.DATEFORMATyyyy_MM_dd.format(new java.util.Date());
/*  658 */             if ((sFechaVenc == null) || (resultSet.getInt("PUNTOSACAD") == 0)) break;
/*  659 */             if (sFechaActual.equals(sFechaVenc)) {
/*  660 */               oPuntos.setBandVencer60Dias(true); break;
/*      */             }
/*  662 */             ArrayList<String> vecFechas = new ArrayList();
/*  663 */             vecFechas.add(sFechaActual);
/*  664 */             vecFechas.add(sFechaVenc);
/*  665 */             int indMenor = Utils.comparaFechas(vecFechas, 0, 1);
/*  666 */             if ((indMenor == -1) || 
/*  667 */               (indMenor != 0)) break;
/*  668 */             long difDias = 0L;
/*  669 */             difDias = Utils.diferenciaEnDias(new GregorianCalendar(Integer.parseInt(sFechaActual.substring(0, 4)), Integer.parseInt(sFechaActual.substring(5, 7)) - 1, Integer.parseInt(sFechaActual.substring(8, 10))), 
/*  670 */               new GregorianCalendar(Integer.parseInt(sFechaVenc.substring(0, 4)), Integer.parseInt(sFechaVenc.substring(5, 7)) - 1, Integer.parseInt(sFechaVenc.substring(8, 10))));
/*  671 */             if ((difDias < -60L) || (difDias > 60L)) break;
/*  672 */             oPuntos.setBandVencer60Dias(true); break;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  679 */           if ((parametrosTO.getTipoRed() != null) && ((parametrosTO.getTipoRed().equals("CON")) || (parametrosTO.getTipoRed().equals("CAREG")))) {
/*  680 */             if ((parametrosTO.getPlanNvo() == null) || ("".equals(parametrosTO.getPlanNvo())))
/*      */             {
/*  682 */               if (parametrosTO.isConsultaGeneral()) {
/*  683 */                 parametrosTO.setPlanNvo(telefonoTO.getPlan());
/*      */               } else {
/*  685 */                 telefonoTO.agregaMensaje(1, "Es necesario capturar el plan en el que desea renovar el contrato.");
/*  686 */                 continue;
/*      */               }
/*      */             }
/*      */             
/*  690 */             datosPlan(parametrosTO.getPlanNvo(), parametrosTO.getRegion(), connection, telefonoTO);
/*      */             
/*  692 */             if (telefonoTO.getIdMensaje() == 0)
/*      */             {
/*      */ 
/*  695 */               parametrosTO.setBRedencionAnct(telefonoTO.getPlanTO().getBanRedencionAnct());
/*  696 */               telefonoTO.setIdGrupo(String.valueOf(telefonoTO.getPlanTO().getIdGrupoPromocion()));
/*      */             }
/*      */           } else {
/*  699 */             Redencion.validaRedencion(parametrosTO, telefonoTO, mobileTO);
/*      */             
/*  701 */             telefonoTO.setFormaRedencion(parametrosTO.getFormaRed());
/*      */             
/*      */ 
/*  704 */             if (telefonoTO.getIdMensaje() == 0)
/*      */             {
/*      */ 
/*  707 */               if ((parametrosTO.getTipoRed().equals("CON")) || (parametrosTO.getTipoRed().equals("CAREG")))
/*      */               {
/*  709 */                 Redencion.validaPlan(parametrosTO, telefonoTO);
/*      */               }
/*      */               
/*  712 */               if (telefonoTO.getIdMensaje() == 0)
/*      */               {
/*      */ 
/*  715 */                 if (parametrosTO.getTipoRed().trim().equals("ACA")) {
/*  716 */                   if (parametrosTO.getRegion() == 9) telefonoTO.setIdGrupo(String.valueOf(1178)); else
/*  717 */                     telefonoTO.setIdGrupo(String.valueOf(1084));
/*      */                 }
/*  719 */                 if (parametrosTO.getTipoRed().trim().equals("SIN")) {
/*  720 */                   if (parametrosTO.getRegion() == 9) telefonoTO.setIdGrupo(String.valueOf(1048)); else
/*  721 */                     telefonoTO.setIdGrupo(String.valueOf(1082));
/*      */                 }
/*  723 */                 if (!parametrosTO.getTipoRed().trim().equals("T3G")) break;
/*  724 */                 if (parametrosTO.getRegion() == 9) { telefonoTO.setIdGrupo(String.valueOf(1347)); break; }
/*  725 */                 telefonoTO.setIdGrupo(String.valueOf(1346)); break;
/*      */                 
/*      */ 
/*      */ 
/*  729 */                 telefonoTO.agregaMensaje(-1, "La Cuenta/Telefono no existe o el estatus no es valido.");
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  735 */       if (oPuntos.getPtosStatus().trim().equals("R"))
/*      */       {
/*      */ 
/*  738 */         StringBuffer sql = new StringBuffer();
/*  739 */         sql.append(" SELECT B.FZAVTA, B.FECHAOPER ");
/*  740 */         sql.append("   FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLRESERVACIONES B ");
/*  741 */         sql.append("  WHERE ").append(sBusqueda);
/*  742 */         sql.append("   AND A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA ");
/*  743 */         sql.append("   AND A.IDREGION = B.IDREGION AND A.STATUSPUNTOS='R' ");
/*  744 */         sql.append(" ORDER BY B.FECHAOPER DESC ");
/*      */         
/*  746 */         statementFvta = connection.createStatement(1004, 1007);
/*      */         
/*  748 */         resultSetFvta = statementFvta.executeQuery(sql.toString());
/*      */         
/*      */ 
/*  751 */         if (resultSetFvta.next()) {
/*  752 */           String distReserva = resultSetFvta.getString(1);
/*  753 */           java.sql.Date fechaReservacion = resultSetFvta.getDate(2);
/*  754 */           oPuntos.setDescPtsReservados("Reservados por el Distribuidor: " + distReserva + " el dia:" + Constantes.DATEFORMATdd_MM_YYYY.format(new java.util.Date(fechaReservacion.getTime())));
/*  755 */           oPuntos.setDistribuidorReserva(distReserva);
/*  756 */           oPuntos.setFecReservacion(fechaReservacion);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  762 */       telefonoTO.setPuntosTO(oPuntos);
/*  763 */       this.logger.info("consultaDatosPuntos|FinConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioConsulta));
/*      */     } catch (SQLException e) {
/*  765 */       throw new CAException(-1, "SQLException.consultaDatosPuntos[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  767 */       throw new CAException(-1, "ConsultasDAO.consultaDatosPuntos[" + e.toString() + "]", e);
/*      */     } finally {
/*  769 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException5) {}
/*  770 */       if (resultSetFvta != null) try { resultSetFvta.close();resultSetFvta = null; } catch (Exception localException6) {}
/*  771 */       if (statement != null) try { statement.close();statement = null; } catch (Exception localException7) {}
/*  772 */       if (statementFvta != null) try { statementFvta.close();statementFvta = null;
/*      */         }
/*      */         catch (Exception localException8) {}
/*      */     }
/*  769 */     if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException9) {}
/*  770 */     if (resultSetFvta != null) try { resultSetFvta.close();resultSetFvta = null; } catch (Exception localException10) {}
/*  771 */     if (statement != null) try { statement.close();statement = null; } catch (Exception localException11) {}
/*  772 */     if (statementFvta != null) { try { statementFvta.close();statementFvta = null;
/*      */       }
/*      */       catch (Exception localException12) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void datosPlan(String sPlan, int region, Connection connection, TelefonoTO telefonoTO)
/*      */     throws CAException
/*      */   {
/*  782 */     PreparedStatement statement = null;
/*  783 */     ResultSet resultSet = null;
/*  784 */     StringBuffer query = new StringBuffer();
/*  785 */     Connection lConn = null;
/*  786 */     PlanTO planTO = new PlanTO();
/*  787 */     telefonoTO.setPlanTO(planTO);
/*      */     
/*      */ 
/*  790 */     query.append(" SELECT B.TIPOPROMOCION, BREDENCION, A.IDGRUPOPROMOCION,");
/*  791 */     query.append(" A.ADENDUM, A.BREDENCIONANTC, A.IDSEGMENTO , A.TIPO_PLAN ");
/*  792 */     query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS A,");
/*  793 */     query.append("       ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B ");
/*  794 */     query.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
/*  795 */     query.append(" AND A.IDPLAN = ?");
/*  796 */     query.append(" AND A.IDREGION =?");
/*  797 */     query.append(" AND A.ESTATUS = 'A' AND B.ESTATUS = 'A' ");
/*      */     try {
/*  799 */       if (connection == null) lConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul); else
/*  800 */         lConn = connection;
/*  801 */       statement = lConn.prepareStatement(query.toString());
/*  802 */       statement.setString(1, sPlan);
/*  803 */       statement.setInt(2, region);
/*  804 */       resultSet = statement.executeQuery();
/*  805 */       if (resultSet.next()) {
/*  806 */         planTO.setTipoPromocion(resultSet.getString(1));
/*  807 */         planTO.setBanRedencion(resultSet.getInt(2));
/*  808 */         planTO.setIdGrupoPromocion(resultSet.getInt(3));
/*  809 */         planTO.setAdendumNvo(resultSet.getInt(4));
/*  810 */         planTO.setBanRedencionAnct(resultSet.getString(5));
/*  811 */         planTO.setIdPlanNuevo(sPlan);
/*  812 */         planTO.setSegmento(resultSet.getInt("IDSEGMENTO"));
/*  813 */         planTO.setTipoPlan(resultSet.getString(7));
/*  814 */         telefonoTO.setPlanTO(planTO);
/*      */       } else {
/*  816 */         telefonoTO.agregaMensaje(1, "El Plan no se encuentra dado de alta en la base de datos."); }
/*  817 */       for (;;) { return;
/*      */         
/*  819 */         if (telefonoTO.getPlanTO().getBanRedencion() == 0) {
/*  820 */           telefonoTO.agregaMensaje(1, "La clave del plan capturado es obsoleta, favor de ingresar otra \nclave de plan que se comercialice para continuar.");
/*      */         }
/*      */         else
/*      */         {
/*  824 */           if (telefonoTO.getPlanTO().getAdendumNvo() != 0) break;
/*  825 */           telefonoTO.agregaMensaje(1, "El plan capturado tiene Adendum 0, para realizar una Redencion con renovacion de Adendum seleccione un Plan con adendum valido.");
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  834 */       if ((telefonoTO.getPlanTO().getTipoPlan() != null) && (!telefonoTO.getPlanTO().getTipoPlan().trim().equals("MASIVO"))) {
/*  835 */         String tipoPlan = telefonoTO.getPlanTO().getTipoPlan().trim();
/*  836 */         if ("IMFO".equals(tipoPlan)) {
/*  837 */           telefonoTO.agregaMensaje(1, "No se puede redimir por el plan indicado, el plan es exclusivo para lineas IMSS-FONACOT.");
/*  838 */         } else if ("LIV".equals(tipoPlan)) {
/*  839 */           telefonoTO.agregaMensaje(1, "No se puede redimir por el plan indicado, el plan es exclusivo para lineas LIVERPOOL.");
/*      */         }
/*  841 */         return;
/*      */       }
/*      */     } catch (SQLException e) {
/*  844 */       throw new CAException(-1, "SQLException.datosPlan[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  846 */       throw new CAException(-1, "ConsultasDAO.datosPlan[" + e.toString() + "]", e);
/*      */     } finally {
/*  848 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException7) {}
/*  849 */       if (statement != null) try { statement.close();statement = null; } catch (Exception localException8) {}
/*  850 */       if ((connection == null) && (lConn != null)) try { lConn.close();lConn = null;
/*      */         }
/*      */         catch (Exception localException9) {}
/*      */     }
/*  848 */     if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException10) {}
/*  849 */     if (statement != null) try { statement.close();statement = null; } catch (Exception localException11) {}
/*  850 */     if ((connection == null) && (lConn != null)) { try { lConn.close();lConn = null;
/*      */       }
/*      */       catch (Exception localException12) {}
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ArrayList<CuentaPadreTO> consultaPadre(String cuenta)
/*      */     throws CAException
/*      */   {
/*  861 */     PreparedStatement preparedstatement = null;
/*  862 */     ResultSet resultSet = null;
/*  863 */     Connection connection = null;
/*  864 */     StringBuffer query = new StringBuffer();
/*  865 */     query.append(" SELECT CUENTA, LINEA FROM  ").append(this.schema_database).append("PTO_TBLLINEAS WHERE CTAPADRE = ?");
/*      */     try {
/*  867 */       long inicioProceso = System.currentTimeMillis();
/*  868 */       this.logger.info("consultaPadre|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso);
/*  869 */       this.logger.info("consultaPadre|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso);
/*  870 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  871 */       this.logger.info("consultaPadre|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso));
/*  872 */       long inicioConsulta = System.currentTimeMillis();
/*  873 */       this.logger.info("consultaPadre|InicioConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioConsulta);
/*  874 */       preparedstatement = connection.prepareStatement(query.toString());
/*  875 */       preparedstatement.setString(1, cuenta);
/*  876 */       resultSet = preparedstatement.executeQuery();
/*  877 */       ArrayList<CuentaPadreTO> cuentasHijas = new ArrayList();
/*  878 */       while (resultSet.next()) {
/*  879 */         CuentaPadreTO cuentaPadreTO = new CuentaPadreTO();
/*  880 */         cuentaPadreTO.setCuenta(resultSet.getString("CUENTA"));
/*  881 */         cuentaPadreTO.setLinea(resultSet.getString("LINEA"));
/*  882 */         cuentaPadreTO.setCuentaPadre(cuenta);
/*  883 */         cuentasHijas.add(cuentaPadreTO);
/*      */       }
/*  885 */       this.logger.info("consultaPadre|FinConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioConsulta));
/*  886 */       this.logger.info("consultaPadre|FinProceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso));
/*  887 */       return cuentasHijas;
/*      */     } catch (SQLException e) {
/*  889 */       this.error.info("SQLException.consultaPadre:", e);
/*  890 */       throw new CAException(-1, "SQLException.consultaPadre[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  892 */       this.error.info("Exception.consultaPadre:", e);
/*  893 */       throw new CAException(-1, "ConsultasDAO.consultaPadre[" + e.toString() + "]", e);
/*      */     } finally {
/*  895 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException4) {}
/*  896 */       if (preparedstatement != null) try { preparedstatement.close();preparedstatement = null; } catch (Exception localException5) {}
/*  897 */       if (connection != null) { try { connection.close();connection = null;
/*      */         }
/*      */         catch (Exception localException6) {}
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String consultaPrecioEquipo(String plan, int region, String marca, String modelo, String estatus)
/*      */     throws CAException
/*      */   {
/*  913 */     PreparedStatement preparedStatement = null;
/*  914 */     ResultSet resultSet = null;
/*  915 */     Connection connection = null;
/*  916 */     StringBuffer sQuery = new StringBuffer();
/*  917 */     String precio = "";
/*      */     try {
/*  919 */       if ((plan != null) && (marca != null) && (modelo != null))
/*      */       {
/*  921 */         sQuery.append(" SELECT PRECIOACTIVACION");
/*  922 */         sQuery.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES A ,  ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B,");
/*  923 */         sQuery.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C");
/*  924 */         sQuery.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION");
/*  925 */         sQuery.append(" AND B.IDGRUPOPROMOCION = C.IDGRUPOPROMOCION");
/*  926 */         sQuery.append(" AND C.IDPLAN = ? ");
/*  927 */         sQuery.append(" AND A.ADENDUM = C.ADENDUM AND A.IDREGION = C.IDREGION");
/*  928 */         sQuery.append(" AND A.IDREGION =? ");
/*  929 */         sQuery.append(" AND A.MARCA =? ");
/*  930 */         sQuery.append(" AND A.MODELO =?");
/*  931 */         sQuery.append(" AND A.ESTATUS = ?");
/*      */         
/*  933 */         connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  934 */         preparedStatement = connection.prepareStatement(sQuery.toString());
/*  935 */         preparedStatement.setString(1, plan);
/*  936 */         preparedStatement.setInt(2, region);
/*  937 */         preparedStatement.setString(3, marca);
/*  938 */         preparedStatement.setString(4, modelo);
/*  939 */         preparedStatement.setString(5, estatus);
/*  940 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  942 */         if (resultSet.next()) {
/*  943 */           precio = resultSet.getBigDecimal("PRECIOACTIVACION").toString();
/*      */         } else {
/*  945 */           throw new CAException(-1, "No existen promociones que correspondan a los datos enviados");
/*      */         }
/*      */       } else {
/*  948 */         throw new CAException(-1, "Los datos de consulta son nulos.");
/*  949 */       } } catch (SQLException e) { e = e;
/*  950 */       throw new CAException(-1, "SQLException.consultaPrecioEquipo[" + e.toString() + "]", e);
/*  951 */     } catch (Exception e) { e = e;
/*  952 */       throw new CAException(-1, "ConsultasDAO.consultaPrecioEquipo[" + e.toString() + "]", e);
/*  953 */     } finally { localObject = finally;
/*  954 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException1) {}
/*  955 */       if (preparedStatement != null) try { preparedStatement.close();preparedStatement = null; } catch (Exception localException2) {}
/*  956 */       if (connection != null) try { connection.close();connection = null; } catch (Exception localException3) {}
/*  957 */       throw ((Throwable)localObject);
/*      */     }
/*  954 */     if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException4) {}
/*  955 */     if (preparedStatement != null) try { preparedStatement.close();preparedStatement = null; } catch (Exception localException5) {}
/*  956 */     if (connection != null) try { connection.close();connection = null;
/*      */       } catch (Exception localException6) {}
/*  958 */     return precio;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int consultaRegion(String lTelefono)
/*      */     throws CAException
/*      */   {
/*  967 */     PreparedStatement preparedStatement = null;
/*  968 */     ResultSet resultSet = null;
/*  969 */     Connection connection = null;
/*  970 */     StringBuffer sQuery = new StringBuffer();
/*      */     try
/*      */     {
/*  973 */       if (lTelefono != null) {
/*  974 */         sQuery.append(" SELECT A.IDREGION ");
/*  975 */         sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A ");
/*  976 */         sQuery.append(" WHERE A.LINEA = ? ");
/*  977 */         sQuery.append(" GROUP BY A.LINEA, A.CUENTA, A.SECUENCIA, A.IDREGION, A.FECHAADD ");
/*  978 */         sQuery.append(" ORDER BY A.FECHAADD DESC");
/*  979 */         connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  980 */         preparedStatement = connection.prepareStatement(sQuery.toString(), 1004, 1007);
/*  981 */         preparedStatement.setString(1, lTelefono);
/*  982 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  984 */         if (resultSet.next()) {
/*  985 */           return resultSet.getInt("IDREGION");
/*      */         }
/*  987 */         throw new CAException(-1, "La linea no se encuentra dada de alta en el sistema de Puntos.");
/*      */       }
/*      */       
/*  990 */       throw new CAException(-1, "Debe especificar el telfono a consultar.");
/*      */     } catch (SQLException e) {
/*  992 */       throw new CAException(-1, "SQLException.consultaRegion[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  994 */       throw new CAException(-1, "ConsultasDAO.consultaRegion[" + e.toString() + "]", e);
/*      */     } finally {
/*  996 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException4) {}
/*  997 */       if (preparedStatement != null) try { preparedStatement.close();preparedStatement = null; } catch (Exception localException5) {}
/*  998 */       if (connection != null) try { connection.close();connection = null;
/*      */         }
/*      */         catch (Exception localException6) {}
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.RedencionTO> consultaFolioSAP(String folio, String tipoRedencion, String estatus, int secuencia, String cuenta, int region, boolean consultaM2K)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 382	java/lang/StringBuilder
/*      */     //   3: dup
/*      */     //   4: ldc_w 1513
/*      */     //   7: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   10: aload_0
/*      */     //   11: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   14: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   17: ldc_w 1515
/*      */     //   20: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   23: aload_0
/*      */     //   24: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   27: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   30: ldc_w 1517
/*      */     //   33: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   36: aload_0
/*      */     //   37: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   40: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   43: ldc_w 1519
/*      */     //   46: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   49: aload_0
/*      */     //   50: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   53: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   56: ldc_w 1521
/*      */     //   59: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   62: aload_0
/*      */     //   63: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   66: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   69: ldc_w 1523
/*      */     //   72: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   75: ldc_w 1525
/*      */     //   78: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   81: ldc_w 1527
/*      */     //   84: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   87: ldc_w 1529
/*      */     //   90: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   93: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   96: astore 8
/*      */     //   98: aload_3
/*      */     //   99: ifnull +26 -> 125
/*      */     //   102: new 382	java/lang/StringBuilder
/*      */     //   105: dup
/*      */     //   106: aload 8
/*      */     //   108: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   111: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   114: ldc_w 1534
/*      */     //   117: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   120: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   123: astore 8
/*      */     //   125: aload 5
/*      */     //   127: ifnull +26 -> 153
/*      */     //   130: new 382	java/lang/StringBuilder
/*      */     //   133: dup
/*      */     //   134: aload 8
/*      */     //   136: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   139: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   142: ldc_w 1536
/*      */     //   145: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   148: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   151: astore 8
/*      */     //   153: iload 4
/*      */     //   155: ifeq +26 -> 181
/*      */     //   158: new 382	java/lang/StringBuilder
/*      */     //   161: dup
/*      */     //   162: aload 8
/*      */     //   164: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   167: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   170: ldc_w 1538
/*      */     //   173: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   176: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   179: astore 8
/*      */     //   181: aload_1
/*      */     //   182: ifnull +26 -> 208
/*      */     //   185: new 382	java/lang/StringBuilder
/*      */     //   188: dup
/*      */     //   189: aload 8
/*      */     //   191: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   194: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   197: ldc_w 1540
/*      */     //   200: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   203: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   206: astore 8
/*      */     //   208: aload_2
/*      */     //   209: ifnull +26 -> 235
/*      */     //   212: new 382	java/lang/StringBuilder
/*      */     //   215: dup
/*      */     //   216: aload 8
/*      */     //   218: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   221: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   224: ldc_w 1542
/*      */     //   227: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   230: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   233: astore 8
/*      */     //   235: new 382	java/lang/StringBuilder
/*      */     //   238: dup
/*      */     //   239: aload 8
/*      */     //   241: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   244: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   247: ldc_w 1544
/*      */     //   250: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   253: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   256: astore 8
/*      */     //   258: aconst_null
/*      */     //   259: astore 9
/*      */     //   261: aconst_null
/*      */     //   262: astore 10
/*      */     //   264: aconst_null
/*      */     //   265: astore 11
/*      */     //   267: new 145	java/util/ArrayList
/*      */     //   270: dup
/*      */     //   271: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   274: astore 12
/*      */     //   276: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   279: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   282: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   285: astore 9
/*      */     //   287: aload 9
/*      */     //   289: aload 8
/*      */     //   291: invokeinterface 158 2 0
/*      */     //   296: astore 10
/*      */     //   298: iconst_0
/*      */     //   299: istore 13
/*      */     //   301: aload_3
/*      */     //   302: ifnull +16 -> 318
/*      */     //   305: aload 10
/*      */     //   307: iinc 13 1
/*      */     //   310: iload 13
/*      */     //   312: aload_3
/*      */     //   313: invokeinterface 164 3 0
/*      */     //   318: aload 5
/*      */     //   320: ifnull +17 -> 337
/*      */     //   323: aload 10
/*      */     //   325: iinc 13 1
/*      */     //   328: iload 13
/*      */     //   330: aload 5
/*      */     //   332: invokeinterface 164 3 0
/*      */     //   337: iload 4
/*      */     //   339: ifeq +17 -> 356
/*      */     //   342: aload 10
/*      */     //   344: iinc 13 1
/*      */     //   347: iload 13
/*      */     //   349: iload 4
/*      */     //   351: invokeinterface 169 3 0
/*      */     //   356: aload_1
/*      */     //   357: ifnull +16 -> 373
/*      */     //   360: aload 10
/*      */     //   362: iinc 13 1
/*      */     //   365: iload 13
/*      */     //   367: aload_1
/*      */     //   368: invokeinterface 164 3 0
/*      */     //   373: aload_2
/*      */     //   374: ifnull +16 -> 390
/*      */     //   377: aload 10
/*      */     //   379: iinc 13 1
/*      */     //   382: iload 13
/*      */     //   384: aload_2
/*      */     //   385: invokeinterface 164 3 0
/*      */     //   390: aload 10
/*      */     //   392: invokeinterface 200 1 0
/*      */     //   397: astore 11
/*      */     //   399: goto +598 -> 997
/*      */     //   402: new 204	com/claro/transfer/RedencionTO
/*      */     //   405: dup
/*      */     //   406: invokespecial 206	com/claro/transfer/RedencionTO:<init>	()V
/*      */     //   409: astore 14
/*      */     //   411: new 266	com/claro/transfer/ProductosTO
/*      */     //   414: dup
/*      */     //   415: invokespecial 268	com/claro/transfer/ProductosTO:<init>	()V
/*      */     //   418: astore 15
/*      */     //   420: aload 14
/*      */     //   422: aload 11
/*      */     //   424: iconst_1
/*      */     //   425: invokeinterface 558 2 0
/*      */     //   430: invokevirtual 364	com/claro/transfer/RedencionTO:setFolio	(Ljava/lang/String;)V
/*      */     //   433: aload 14
/*      */     //   435: aload 11
/*      */     //   437: iconst_2
/*      */     //   438: invokeinterface 1184 2 0
/*      */     //   443: invokevirtual 262	com/claro/transfer/RedencionTO:setFechaOperacion	(Ljava/util/Date;)V
/*      */     //   446: aload 15
/*      */     //   448: aload 11
/*      */     //   450: iconst_3
/*      */     //   451: invokeinterface 558 2 0
/*      */     //   456: invokevirtual 315	com/claro/transfer/ProductosTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   459: aload 15
/*      */     //   461: aload 11
/*      */     //   463: iconst_4
/*      */     //   464: invokeinterface 558 2 0
/*      */     //   469: invokevirtual 1546	com/claro/transfer/ProductosTO:setNumeroSerieT	(Ljava/lang/String;)V
/*      */     //   472: aload 15
/*      */     //   474: aload 11
/*      */     //   476: iconst_5
/*      */     //   477: invokeinterface 558 2 0
/*      */     //   482: invokevirtual 1549	com/claro/transfer/ProductosTO:setNumeroSerieP	(Ljava/lang/String;)V
/*      */     //   485: aload 15
/*      */     //   487: aload 11
/*      */     //   489: bipush 6
/*      */     //   491: invokeinterface 558 2 0
/*      */     //   496: invokevirtual 1552	com/claro/transfer/ProductosTO:setIccid	(Ljava/lang/String;)V
/*      */     //   499: aload 15
/*      */     //   501: aload 11
/*      */     //   503: bipush 7
/*      */     //   505: invokeinterface 558 2 0
/*      */     //   510: invokevirtual 1555	com/claro/transfer/ProductosTO:setTipoPromocion	(Ljava/lang/String;)V
/*      */     //   513: aload 15
/*      */     //   515: aload 11
/*      */     //   517: bipush 8
/*      */     //   519: invokeinterface 558 2 0
/*      */     //   524: invokevirtual 1556	com/claro/transfer/ProductosTO:setTecnologia	(Ljava/lang/String;)V
/*      */     //   527: aload 14
/*      */     //   529: aload 11
/*      */     //   531: bipush 9
/*      */     //   533: invokeinterface 558 2 0
/*      */     //   538: invokevirtual 358	com/claro/transfer/RedencionTO:setTipoRedencion	(Ljava/lang/String;)V
/*      */     //   541: aload 15
/*      */     //   543: aload 11
/*      */     //   545: bipush 10
/*      */     //   547: invokeinterface 558 2 0
/*      */     //   552: invokevirtual 276	com/claro/transfer/ProductosTO:setMarca	(Ljava/lang/String;)V
/*      */     //   555: aload 15
/*      */     //   557: aload 11
/*      */     //   559: bipush 11
/*      */     //   561: invokeinterface 558 2 0
/*      */     //   566: invokevirtual 281	com/claro/transfer/ProductosTO:setModelo	(Ljava/lang/String;)V
/*      */     //   569: aload 15
/*      */     //   571: aload 11
/*      */     //   573: bipush 12
/*      */     //   575: invokeinterface 566 2 0
/*      */     //   580: invokevirtual 1557	com/claro/transfer/ProductosTO:setValorPuntos	(I)V
/*      */     //   583: aload 15
/*      */     //   585: aload 11
/*      */     //   587: bipush 13
/*      */     //   589: invokeinterface 566 2 0
/*      */     //   594: invokevirtual 1560	com/claro/transfer/ProductosTO:setDifPesos	(I)V
/*      */     //   597: aload 15
/*      */     //   599: aload 11
/*      */     //   601: bipush 21
/*      */     //   603: invokeinterface 558 2 0
/*      */     //   608: invokevirtual 315	com/claro/transfer/ProductosTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   611: aload 15
/*      */     //   613: aload 11
/*      */     //   615: bipush 23
/*      */     //   617: invokeinterface 1563 2 0
/*      */     //   622: invokevirtual 296	com/claro/transfer/ProductosTO:setPrecio	(Ljava/math/BigDecimal;)V
/*      */     //   625: aload 15
/*      */     //   627: aload 11
/*      */     //   629: bipush 27
/*      */     //   631: invokeinterface 1563 2 0
/*      */     //   636: invokevirtual 290	com/claro/transfer/ProductosTO:setPrecioIva	(Ljava/math/BigDecimal;)V
/*      */     //   639: aload 15
/*      */     //   641: aload 11
/*      */     //   643: bipush 28
/*      */     //   645: invokeinterface 1563 2 0
/*      */     //   650: invokevirtual 301	com/claro/transfer/ProductosTO:setDescuento	(Ljava/math/BigDecimal;)V
/*      */     //   653: aload 15
/*      */     //   655: aload 11
/*      */     //   657: bipush 29
/*      */     //   659: invokeinterface 566 2 0
/*      */     //   664: invokevirtual 1566	com/claro/transfer/ProductosTO:setBonosRoext	(I)V
/*      */     //   667: aload 15
/*      */     //   669: aload 11
/*      */     //   671: bipush 30
/*      */     //   673: invokeinterface 566 2 0
/*      */     //   678: invokevirtual 1569	com/claro/transfer/ProductosTO:setBonosAltoValor	(I)V
/*      */     //   681: aload 15
/*      */     //   683: aload 11
/*      */     //   685: bipush 31
/*      */     //   687: invokeinterface 558 2 0
/*      */     //   692: invokevirtual 318	com/claro/transfer/ProductosTO:setIdProducto	(Ljava/lang/String;)V
/*      */     //   695: aload 15
/*      */     //   697: aload 11
/*      */     //   699: bipush 32
/*      */     //   701: invokeinterface 566 2 0
/*      */     //   706: invokevirtual 1572	com/claro/transfer/ProductosTO:setBonosGap	(I)V
/*      */     //   709: new 207	com/claro/transfer/TelefonoSimpleTO
/*      */     //   712: dup
/*      */     //   713: invokespecial 209	com/claro/transfer/TelefonoSimpleTO:<init>	()V
/*      */     //   716: astore 16
/*      */     //   718: aload 16
/*      */     //   720: aload 11
/*      */     //   722: bipush 14
/*      */     //   724: invokeinterface 558 2 0
/*      */     //   729: invokevirtual 223	com/claro/transfer/TelefonoSimpleTO:setLinea	(Ljava/lang/String;)V
/*      */     //   732: aload 16
/*      */     //   734: aload 11
/*      */     //   736: bipush 15
/*      */     //   738: invokeinterface 558 2 0
/*      */     //   743: invokevirtual 217	com/claro/transfer/TelefonoSimpleTO:setCuenta	(Ljava/lang/String;)V
/*      */     //   746: new 329	com/claro/transfer/PuntoVentaTO
/*      */     //   749: dup
/*      */     //   750: invokespecial 331	com/claro/transfer/PuntoVentaTO:<init>	()V
/*      */     //   753: astore 17
/*      */     //   755: aload 17
/*      */     //   757: aload 11
/*      */     //   759: bipush 16
/*      */     //   761: invokeinterface 558 2 0
/*      */     //   766: invokevirtual 560	com/claro/transfer/PuntoVentaTO:setPtoVenta	(Ljava/lang/String;)V
/*      */     //   769: aload 14
/*      */     //   771: aload 11
/*      */     //   773: bipush 17
/*      */     //   775: invokeinterface 1575 2 0
/*      */     //   780: invokevirtual 352	com/claro/transfer/RedencionTO:setFechaFolio	(Ljava/sql/Timestamp;)V
/*      */     //   783: aload 14
/*      */     //   785: aload 11
/*      */     //   787: bipush 18
/*      */     //   789: invokeinterface 558 2 0
/*      */     //   794: invokevirtual 1578	com/claro/transfer/RedencionTO:setPlanNuevo	(Ljava/lang/String;)V
/*      */     //   797: aload 14
/*      */     //   799: aload 11
/*      */     //   801: bipush 19
/*      */     //   803: invokeinterface 566 2 0
/*      */     //   808: invokevirtual 1581	com/claro/transfer/RedencionTO:setAddendumNuevo	(I)V
/*      */     //   811: aload 14
/*      */     //   813: aload 11
/*      */     //   815: bipush 20
/*      */     //   817: invokeinterface 1184 2 0
/*      */     //   822: invokevirtual 1584	com/claro/transfer/RedencionTO:setFechaPlazoSeg	(Ljava/util/Date;)V
/*      */     //   825: aload 14
/*      */     //   827: aload 11
/*      */     //   829: bipush 22
/*      */     //   831: invokeinterface 566 2 0
/*      */     //   836: invokevirtual 1587	com/claro/transfer/RedencionTO:setRegion	(I)V
/*      */     //   839: aload 14
/*      */     //   841: aload 11
/*      */     //   843: bipush 24
/*      */     //   845: invokeinterface 558 2 0
/*      */     //   850: invokevirtual 1588	com/claro/transfer/RedencionTO:setEstatus	(Ljava/lang/String;)V
/*      */     //   853: new 321	com/claro/transfer/UsuarioTO
/*      */     //   856: dup
/*      */     //   857: invokespecial 323	com/claro/transfer/UsuarioTO:<init>	()V
/*      */     //   860: astore 18
/*      */     //   862: aload 18
/*      */     //   864: aload 11
/*      */     //   866: bipush 25
/*      */     //   868: invokeinterface 558 2 0
/*      */     //   873: invokevirtual 326	com/claro/transfer/UsuarioTO:setIdUsuario	(Ljava/lang/String;)V
/*      */     //   876: aload 18
/*      */     //   878: aload 11
/*      */     //   880: bipush 26
/*      */     //   882: invokeinterface 558 2 0
/*      */     //   887: invokevirtual 1591	com/claro/transfer/UsuarioTO:setNombre	(Ljava/lang/String;)V
/*      */     //   890: aload 18
/*      */     //   892: aload 17
/*      */     //   894: invokevirtual 332	com/claro/transfer/UsuarioTO:setPuntoVentaTO	(Lcom/claro/transfer/PuntoVentaTO;)V
/*      */     //   897: aload 14
/*      */     //   899: aload 18
/*      */     //   901: invokevirtual 367	com/claro/transfer/RedencionTO:setUsuarioTO	(Lcom/claro/transfer/UsuarioTO;)V
/*      */     //   904: aload 14
/*      */     //   906: aload 16
/*      */     //   908: invokevirtual 226	com/claro/transfer/RedencionTO:setTelefonoSimpleTO	(Lcom/claro/transfer/TelefonoSimpleTO;)V
/*      */     //   911: aload 14
/*      */     //   913: aload 15
/*      */     //   915: invokevirtual 371	com/claro/transfer/RedencionTO:setProductosTO	(Lcom/claro/transfer/ProductosTO;)V
/*      */     //   918: iload 7
/*      */     //   920: ifeq +60 -> 980
/*      */     //   923: new 630	com/claro/transfer/ParametrosTO
/*      */     //   926: dup
/*      */     //   927: invokespecial 1594	com/claro/transfer/ParametrosTO:<init>	()V
/*      */     //   930: astore 19
/*      */     //   932: aload 19
/*      */     //   934: iload 6
/*      */     //   936: invokevirtual 666	com/claro/transfer/ParametrosTO:setRegion	(I)V
/*      */     //   939: aload 19
/*      */     //   941: aload 16
/*      */     //   943: invokevirtual 1595	com/claro/transfer/TelefonoSimpleTO:getLinea	()Ljava/lang/String;
/*      */     //   946: invokevirtual 1598	com/claro/transfer/ParametrosTO:setTelefono	(Ljava/lang/String;)V
/*      */     //   949: aload 14
/*      */     //   951: aload_0
/*      */     //   952: getfield 43	com/claro/dao/ConsultasDAO:consultaM2KDAO	Lcom/claro/dao/ConsultaM2KDAO;
/*      */     //   955: aload 19
/*      */     //   957: invokevirtual 673	com/claro/dao/ConsultaM2KDAO:consultaDatosM2K	(Lcom/claro/transfer/ParametrosTO;)Lcom/claro/transfer/MobileTO;
/*      */     //   960: invokevirtual 1599	com/claro/transfer/RedencionTO:setMobileTO	(Lcom/claro/transfer/MobileTO;)V
/*      */     //   963: goto +17 -> 980
/*      */     //   966: astore 20
/*      */     //   968: new 85	com/claro/exception/CAException
/*      */     //   971: dup
/*      */     //   972: iconst_1
/*      */     //   973: ldc_w 1600
/*      */     //   976: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   979: athrow
/*      */     //   980: aload 14
/*      */     //   982: iconst_0
/*      */     //   983: ldc_w 1602
/*      */     //   986: invokevirtual 1604	com/claro/transfer/RedencionTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   989: aload 12
/*      */     //   991: aload 14
/*      */     //   993: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   996: pop
/*      */     //   997: aload 11
/*      */     //   999: invokeinterface 378 1 0
/*      */     //   1004: ifne -602 -> 402
/*      */     //   1007: goto +148 -> 1155
/*      */     //   1010: astore 13
/*      */     //   1012: new 85	com/claro/exception/CAException
/*      */     //   1015: dup
/*      */     //   1016: iconst_m1
/*      */     //   1017: new 382	java/lang/StringBuilder
/*      */     //   1020: dup
/*      */     //   1021: ldc_w 1605
/*      */     //   1024: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   1027: aload 13
/*      */     //   1029: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   1032: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   1035: ldc_w 394
/*      */     //   1038: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   1041: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   1044: aload 13
/*      */     //   1046: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   1049: athrow
/*      */     //   1050: astore 13
/*      */     //   1052: new 85	com/claro/exception/CAException
/*      */     //   1055: dup
/*      */     //   1056: iconst_m1
/*      */     //   1057: new 382	java/lang/StringBuilder
/*      */     //   1060: dup
/*      */     //   1061: ldc_w 1607
/*      */     //   1064: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   1067: aload 13
/*      */     //   1069: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   1072: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   1075: ldc_w 394
/*      */     //   1078: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   1081: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   1084: aload 13
/*      */     //   1086: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   1089: athrow
/*      */     //   1090: astore 21
/*      */     //   1092: aload 11
/*      */     //   1094: ifnull +18 -> 1112
/*      */     //   1097: aload 11
/*      */     //   1099: invokeinterface 403 1 0
/*      */     //   1104: aconst_null
/*      */     //   1105: astore 11
/*      */     //   1107: goto +5 -> 1112
/*      */     //   1110: astore 22
/*      */     //   1112: aload 10
/*      */     //   1114: ifnull +18 -> 1132
/*      */     //   1117: aload 10
/*      */     //   1119: invokeinterface 406 1 0
/*      */     //   1124: aconst_null
/*      */     //   1125: astore 10
/*      */     //   1127: goto +5 -> 1132
/*      */     //   1130: astore 22
/*      */     //   1132: aload 9
/*      */     //   1134: ifnull +18 -> 1152
/*      */     //   1137: aload 9
/*      */     //   1139: invokeinterface 407 1 0
/*      */     //   1144: aconst_null
/*      */     //   1145: astore 9
/*      */     //   1147: goto +5 -> 1152
/*      */     //   1150: astore 22
/*      */     //   1152: aload 21
/*      */     //   1154: athrow
/*      */     //   1155: aload 11
/*      */     //   1157: ifnull +18 -> 1175
/*      */     //   1160: aload 11
/*      */     //   1162: invokeinterface 403 1 0
/*      */     //   1167: aconst_null
/*      */     //   1168: astore 11
/*      */     //   1170: goto +5 -> 1175
/*      */     //   1173: astore 22
/*      */     //   1175: aload 10
/*      */     //   1177: ifnull +18 -> 1195
/*      */     //   1180: aload 10
/*      */     //   1182: invokeinterface 406 1 0
/*      */     //   1187: aconst_null
/*      */     //   1188: astore 10
/*      */     //   1190: goto +5 -> 1195
/*      */     //   1193: astore 22
/*      */     //   1195: aload 9
/*      */     //   1197: ifnull +18 -> 1215
/*      */     //   1200: aload 9
/*      */     //   1202: invokeinterface 407 1 0
/*      */     //   1207: aconst_null
/*      */     //   1208: astore 9
/*      */     //   1210: goto +5 -> 1215
/*      */     //   1213: astore 22
/*      */     //   1215: aload 12
/*      */     //   1217: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1014	-> byte code offset #0
/*      */     //   Java source line #1018	-> byte code offset #10
/*      */     //   Java source line #1019	-> byte code offset #36
/*      */     //   Java source line #1020	-> byte code offset #75
/*      */     //   Java source line #1021	-> byte code offset #81
/*      */     //   Java source line #1022	-> byte code offset #87
/*      */     //   Java source line #1014	-> byte code offset #93
/*      */     //   Java source line #1023	-> byte code offset #98
/*      */     //   Java source line #1024	-> byte code offset #102
/*      */     //   Java source line #1025	-> byte code offset #125
/*      */     //   Java source line #1026	-> byte code offset #130
/*      */     //   Java source line #1027	-> byte code offset #153
/*      */     //   Java source line #1028	-> byte code offset #158
/*      */     //   Java source line #1029	-> byte code offset #181
/*      */     //   Java source line #1030	-> byte code offset #185
/*      */     //   Java source line #1031	-> byte code offset #208
/*      */     //   Java source line #1032	-> byte code offset #212
/*      */     //   Java source line #1034	-> byte code offset #235
/*      */     //   Java source line #1036	-> byte code offset #258
/*      */     //   Java source line #1037	-> byte code offset #261
/*      */     //   Java source line #1038	-> byte code offset #264
/*      */     //   Java source line #1039	-> byte code offset #267
/*      */     //   Java source line #1041	-> byte code offset #276
/*      */     //   Java source line #1042	-> byte code offset #287
/*      */     //   Java source line #1043	-> byte code offset #298
/*      */     //   Java source line #1044	-> byte code offset #301
/*      */     //   Java source line #1045	-> byte code offset #305
/*      */     //   Java source line #1046	-> byte code offset #318
/*      */     //   Java source line #1047	-> byte code offset #323
/*      */     //   Java source line #1048	-> byte code offset #337
/*      */     //   Java source line #1049	-> byte code offset #342
/*      */     //   Java source line #1050	-> byte code offset #356
/*      */     //   Java source line #1051	-> byte code offset #360
/*      */     //   Java source line #1052	-> byte code offset #373
/*      */     //   Java source line #1053	-> byte code offset #377
/*      */     //   Java source line #1056	-> byte code offset #390
/*      */     //   Java source line #1057	-> byte code offset #399
/*      */     //   Java source line #1058	-> byte code offset #402
/*      */     //   Java source line #1059	-> byte code offset #411
/*      */     //   Java source line #1060	-> byte code offset #420
/*      */     //   Java source line #1061	-> byte code offset #433
/*      */     //   Java source line #1062	-> byte code offset #446
/*      */     //   Java source line #1063	-> byte code offset #459
/*      */     //   Java source line #1064	-> byte code offset #472
/*      */     //   Java source line #1065	-> byte code offset #485
/*      */     //   Java source line #1066	-> byte code offset #499
/*      */     //   Java source line #1067	-> byte code offset #513
/*      */     //   Java source line #1068	-> byte code offset #527
/*      */     //   Java source line #1069	-> byte code offset #541
/*      */     //   Java source line #1070	-> byte code offset #555
/*      */     //   Java source line #1071	-> byte code offset #569
/*      */     //   Java source line #1072	-> byte code offset #583
/*      */     //   Java source line #1073	-> byte code offset #597
/*      */     //   Java source line #1074	-> byte code offset #611
/*      */     //   Java source line #1075	-> byte code offset #625
/*      */     //   Java source line #1076	-> byte code offset #639
/*      */     //   Java source line #1077	-> byte code offset #653
/*      */     //   Java source line #1078	-> byte code offset #667
/*      */     //   Java source line #1079	-> byte code offset #681
/*      */     //   Java source line #1080	-> byte code offset #695
/*      */     //   Java source line #1081	-> byte code offset #709
/*      */     //   Java source line #1082	-> byte code offset #718
/*      */     //   Java source line #1083	-> byte code offset #732
/*      */     //   Java source line #1085	-> byte code offset #746
/*      */     //   Java source line #1086	-> byte code offset #755
/*      */     //   Java source line #1088	-> byte code offset #769
/*      */     //   Java source line #1089	-> byte code offset #783
/*      */     //   Java source line #1090	-> byte code offset #797
/*      */     //   Java source line #1091	-> byte code offset #811
/*      */     //   Java source line #1092	-> byte code offset #825
/*      */     //   Java source line #1093	-> byte code offset #839
/*      */     //   Java source line #1094	-> byte code offset #853
/*      */     //   Java source line #1095	-> byte code offset #862
/*      */     //   Java source line #1096	-> byte code offset #876
/*      */     //   Java source line #1097	-> byte code offset #890
/*      */     //   Java source line #1098	-> byte code offset #897
/*      */     //   Java source line #1099	-> byte code offset #904
/*      */     //   Java source line #1100	-> byte code offset #911
/*      */     //   Java source line #1102	-> byte code offset #918
/*      */     //   Java source line #1103	-> byte code offset #923
/*      */     //   Java source line #1104	-> byte code offset #932
/*      */     //   Java source line #1105	-> byte code offset #939
/*      */     //   Java source line #1108	-> byte code offset #949
/*      */     //   Java source line #1109	-> byte code offset #966
/*      */     //   Java source line #1110	-> byte code offset #968
/*      */     //   Java source line #1113	-> byte code offset #980
/*      */     //   Java source line #1114	-> byte code offset #989
/*      */     //   Java source line #1057	-> byte code offset #997
/*      */     //   Java source line #1116	-> byte code offset #1010
/*      */     //   Java source line #1117	-> byte code offset #1012
/*      */     //   Java source line #1118	-> byte code offset #1050
/*      */     //   Java source line #1119	-> byte code offset #1052
/*      */     //   Java source line #1120	-> byte code offset #1090
/*      */     //   Java source line #1121	-> byte code offset #1092
/*      */     //   Java source line #1122	-> byte code offset #1112
/*      */     //   Java source line #1123	-> byte code offset #1132
/*      */     //   Java source line #1124	-> byte code offset #1152
/*      */     //   Java source line #1121	-> byte code offset #1155
/*      */     //   Java source line #1122	-> byte code offset #1175
/*      */     //   Java source line #1123	-> byte code offset #1195
/*      */     //   Java source line #1125	-> byte code offset #1215
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	1218	0	this	ConsultasDAO
/*      */     //   0	1218	1	folio	String
/*      */     //   0	1218	2	tipoRedencion	String
/*      */     //   0	1218	3	estatus	String
/*      */     //   0	1218	4	secuencia	int
/*      */     //   0	1218	5	cuenta	String
/*      */     //   0	1218	6	region	int
/*      */     //   0	1218	7	consultaM2K	boolean
/*      */     //   96	194	8	query	String
/*      */     //   259	950	9	connection	Connection
/*      */     //   262	927	10	statement	PreparedStatement
/*      */     //   265	904	11	resultSet	ResultSet
/*      */     //   274	942	12	folios	ArrayList<com.claro.transfer.RedencionTO>
/*      */     //   299	84	13	nIndice	int
/*      */     //   1010	35	13	e	SQLException
/*      */     //   1050	35	13	e	Exception
/*      */     //   409	583	14	redencionTO	com.claro.transfer.RedencionTO
/*      */     //   418	496	15	productosTO	ProductosTO
/*      */     //   716	226	16	telefonoSimpleTO	TelefonoSimpleTO
/*      */     //   753	140	17	puntoVentaTO	PuntoVentaTO
/*      */     //   860	40	18	usuarioTO	UsuarioTO
/*      */     //   930	26	19	parametrosTO	ParametrosTO
/*      */     //   966	3	20	e	Exception
/*      */     //   1090	63	21	localObject	Object
/*      */     //   1110	1	22	localException1	Exception
/*      */     //   1130	1	22	localException2	Exception
/*      */     //   1150	1	22	localException3	Exception
/*      */     //   1173	1	22	localException4	Exception
/*      */     //   1193	1	22	localException5	Exception
/*      */     //   1213	1	22	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   949	963	966	java/lang/Exception
/*      */     //   276	1007	1010	java/sql/SQLException
/*      */     //   276	1007	1050	java/lang/Exception
/*      */     //   276	1090	1090	finally
/*      */     //   1097	1107	1110	java/lang/Exception
/*      */     //   1117	1127	1130	java/lang/Exception
/*      */     //   1137	1147	1150	java/lang/Exception
/*      */     //   1160	1170	1173	java/lang/Exception
/*      */     //   1180	1190	1193	java/lang/Exception
/*      */     //   1200	1210	1213	java/lang/Exception
/*      */   }
/*      */   
/*      */   public ReservacionTO obtieneReservacion(String folio, String estatus, boolean cancelaApartado)
/*      */     throws CAException
/*      */   {
/* 1141 */     Connection connection = null;
/* 1142 */     PreparedStatement statement = null;
/* 1143 */     ResultSet resultSet = null;
/* 1144 */     ReservacionTO reservacionTO = new ReservacionTO();
/*      */     try
/*      */     {
/* 1147 */       reservacionTO.agregaMensaje(0, "Proceso Exitoso");
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1152 */       StringBuffer query1 = new StringBuffer(" SELECT R.FOLIO,R.CUENTA,R.SECUENCIA,R.IDPRODUCTO,");
/* 1153 */       query1.append("R.IDPUNTOVTA,R.IDREGION,R.VALORPUNTOS,R.DIFERENCIAPESOS,");
/* 1154 */       query1.append("R.IDPLAN,R.COMENTARIO,R.PLANNVO,R.DESCRIPCION,R.MARCA,");
/* 1155 */       query1.append("R.MODELO,R.TIPOREDEN,R.ADDEANT,R.TIPOPROM,");
/* 1156 */       query1.append("R.ADDNVO,R.PLAZOANT,R.FZAVTA,");
/* 1157 */       query1.append("R.PLAZONVO,R.FORMARED,R.PRECIO,R.PRECIOIVA,R.LINEA, ");
/* 1158 */       query1.append("R.SOBRANTESBONO,R.FZAVTA,R.STATUS,R.BONOROEXT,R.DESCUENTO,R.BONOALTOVALOR,");
/* 1159 */       query1.append("P.IDPUESTO,");
/* 1160 */       query1.append("R.IDUSUARIO,R.FECHAOPER,R.FECHAEXPIRA, ");
/* 1161 */       query1.append("R.APLICAPROMOGAP,R.BONOGAP,R.IDPROMOCIONGAP,R.IDPROMOCA,R.VERPROMOGAP,R.APLICAEP, R.PTOSMIN ");
/* 1162 */       query1.append(", U.NUMEMPLEADO ");
/* 1163 */       query1.append(" FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES R ");
/*      */       
/* 1165 */       query1.append(",").append(this.schema_database).append("PTO_CTLUSUARIOS U");
/* 1166 */       query1.append(",").append(this.schema_database).append("PTO_CTLPERFILN P ");
/* 1167 */       query1.append(" WHERE R.IDUSUARIO = U.IDUSUARIO AND U.IDPERFILN=P.IDPERFILN AND R.FOLIO = ? ");
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1174 */       if ((estatus != null) && (estatus.equals("C"))) { query1.append(" AND  R.STATUS IN ('A','P','R','C')");
/* 1175 */       } else if ((estatus != null) && (estatus.equals("P"))) { query1.append(" AND  R.STATUS IN ('A')");
/*      */       }
/* 1177 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 1178 */       statement = connection.prepareStatement(query1.toString());
/* 1179 */       statement.setString(1, folio);
/* 1180 */       resultSet = statement.executeQuery();
/* 1181 */       if (resultSet.next()) {
/* 1182 */         String statusTmp = resultSet.getString("STATUS");
/*      */         
/*      */ 
/* 1185 */         reservacionTO.setFolio(resultSet.getString("FOLIO"));
/* 1186 */         ProductosTO productosTO = new ProductosTO();
/* 1187 */         PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
/*      */         
/* 1189 */         TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
/* 1190 */         telefonoSimpleTO.setCuenta(resultSet.getString("CUENTA"));
/* 1191 */         telefonoSimpleTO.setSecuencia(resultSet.getInt("SECUENCIA"));
/* 1192 */         telefonoSimpleTO.setLinea(resultSet.getString("LINEA"));
/* 1193 */         telefonoSimpleTO.setRegion(resultSet.getInt("IDREGION"));
/*      */         
/* 1195 */         reservacionTO.setTelefonoSimpleTO(telefonoSimpleTO);
/*      */         
/* 1197 */         productosTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
/* 1198 */         puntoVentaTO.setPtoVenta(resultSet.getString("IDPUNTOVTA"));
/* 1199 */         reservacionTO.setRegion(resultSet.getInt("IDREGION"));
/* 1200 */         productosTO.setValorPuntos(resultSet.getInt("VALORPUNTOS"));
/* 1201 */         productosTO.setDifPesos(resultSet.getInt("DIFERENCIAPESOS"));
/* 1202 */         reservacionTO.setPlanAnterior(resultSet.getString("IDPLAN"));
/* 1203 */         reservacionTO.setComentario(resultSet.getString("COMENTARIO"));
/* 1204 */         reservacionTO.setPlanNuevo(resultSet.getString("PLANNVO"));
/* 1205 */         productosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
/* 1206 */         productosTO.setMarca(resultSet.getString("MARCA"));
/* 1207 */         productosTO.setModelo(resultSet.getString("MODELO"));
/* 1208 */         reservacionTO.setTipoRedencion(resultSet.getString("TIPOREDEN"));
/* 1209 */         reservacionTO.setFechaAdendumAnterior(resultSet.getDate("ADDEANT"));
/* 1210 */         productosTO.setTipoPromocion(resultSet.getString("TIPOPROM"));
/* 1211 */         reservacionTO.setFechaAdendumNuevo(resultSet.getDate("ADDNVO"));
/* 1212 */         reservacionTO.setPlazoAnterior(resultSet.getString("PLAZOANT"));
/* 1213 */         reservacionTO.setPlazoNuevo(resultSet.getString("PLAZONVO"));
/* 1214 */         reservacionTO.setFormaRedencion(resultSet.getString("FORMARED"));
/* 1215 */         productosTO.setPrecio(resultSet.getBigDecimal("PRECIO"));
/* 1216 */         productosTO.setPrecioIva(resultSet.getBigDecimal("PRECIOIVA"));
/* 1217 */         reservacionTO.setSobrantesBono(resultSet.getInt("SOBRANTESBONO"));
/* 1218 */         reservacionTO.setFuerzaVenta(resultSet.getString("FZAVTA"));
/* 1219 */         productosTO.setBonosRoext(resultSet.getInt("BONOROEXT"));
/* 1220 */         productosTO.setDescuento(resultSet.getBigDecimal("DESCUENTO"));
/* 1221 */         productosTO.setBonosAltoValor(resultSet.getInt("BONOALTOVALOR"));
/* 1222 */         productosTO.setBonosGap(resultSet.getInt("BONOGAP"));
/* 1223 */         UsuarioTO usuarioTO = new UsuarioTO();
/*      */         
/* 1225 */         usuarioTO.getPerfilTO().setIdPuesto(resultSet.getString("IDPUESTO"));
/*      */         
/* 1227 */         usuarioTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
/*      */         
/* 1229 */         usuarioTO.setNumEmpleado(resultSet.getString("NUMEMPLEADO"));
/* 1230 */         reservacionTO.setFechaOperacion(resultSet.getDate("FECHAOPER"));
/* 1231 */         reservacionTO.setFechaExpiracion(resultSet.getDate("FECHAEXPIRA"));
/* 1232 */         reservacionTO.setEstatus(statusTmp);
/* 1233 */         reservacionTO.setFuerzaVenta(resultSet.getString("FZAVTA"));
/* 1234 */         productosTO.setAplicaPromocionGap(resultSet.getString("APLICAPROMOGAP") != null ? resultSet.getString("APLICAPROMOGAP") : "");
/* 1235 */         productosTO.setIdPromocionGap(resultSet.getInt("IDPROMOCIONGAP"));
/* 1236 */         productosTO.setIdPromocionGapCA(resultSet.getInt("IDPROMOCA"));
/* 1237 */         productosTO.setVerPromocionGap(resultSet.getInt("VERPROMOGAP"));
/* 1238 */         productosTO.setAplicaEP(resultSet.getString("APLICAEP"));
/*      */         
/* 1240 */         reservacionTO.setPtsMinimos(resultSet.getInt("PTOSMIN"));
/*      */         
/*      */ 
/*      */ 
/* 1244 */         if ((productosTO.getAplicaPromocionGap() != null) && ("SI".equals(productosTO.getAplicaPromocionGap()))) {
/* 1245 */           if (productosTO.getBonosGap() != 0) {
/* 1246 */             productosTO.setBonoDescuentoGap("SI");
/*      */           } else {
/* 1248 */             productosTO.setProductoM2KGap("SI");
/*      */           }
/*      */         }
/*      */         
/* 1252 */         if (!cancelaApartado) {
/* 1253 */           if (statusTmp.equals("R")) { reservacionTO.agregaMensaje(1, "R2. El folio de la reservacion ya fue aplicado.");
/* 1254 */           } else if (statusTmp.equals("C")) { reservacionTO.agregaMensaje(1, "R3. El folio de la reservacion ya fue cancelado.");
/*      */           }
/*      */         }
/* 1257 */         reservacionTO.setProductosTO(productosTO);
/* 1258 */         reservacionTO.setUsuarioTO(usuarioTO);
/* 1259 */         reservacionTO.getUsuarioTO().setPuntoVentaTO(puntoVentaTO);
/*      */       }
/*      */       else {
/* 1262 */         throw new CAException(1, "R1. El folio de la reservacion no se encuentra.");
/*      */       }
/*      */     } catch (SQLException e) {
/* 1265 */       e = e;
/* 1266 */       throw new CAException(-1, "RedencionDAO.obtieneReservacion.SQLException[" + e.toString() + "]", e);
/* 1267 */     } catch (Exception e) { e = e;
/* 1268 */       throw new CAException(-1, "RedencionDAO.obtieneReservacion.Error[" + e.toString() + "]", e);
/* 1269 */     } finally { localObject = finally;
/* 1270 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException1) {}
/* 1271 */       if (statement != null) try { statement.close();statement = null; } catch (Exception localException2) {}
/* 1272 */       if (connection != null) try { connection.close();connection = null; } catch (Exception localException3) {}
/* 1273 */       throw ((Throwable)localObject);
/*      */     }
/* 1270 */     if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException4) {}
/* 1271 */     if (statement != null) try { statement.close();statement = null; } catch (Exception localException5) {}
/* 1272 */     if (connection != null) try { connection.close();connection = null;
/*      */       } catch (Exception localException6) {}
/* 1274 */     return reservacionTO;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public com.claro.transfer.RedencionTO ultimaRedencion(String cuenta, int secuencia)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aconst_null
/*      */     //   3: astore 4
/*      */     //   5: aconst_null
/*      */     //   6: astore 5
/*      */     //   8: new 114	java/lang/StringBuffer
/*      */     //   11: dup
/*      */     //   12: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   15: astore 6
/*      */     //   17: aload 6
/*      */     //   19: ldc_w 1814
/*      */     //   22: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   25: aload_0
/*      */     //   26: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   29: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   32: ldc_w 1816
/*      */     //   35: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   38: pop
/*      */     //   39: aload 6
/*      */     //   41: ldc_w 1818
/*      */     //   44: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   47: pop
/*      */     //   48: aload 6
/*      */     //   50: ldc_w 1820
/*      */     //   53: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   56: pop
/*      */     //   57: aload 6
/*      */     //   59: ldc_w 1822
/*      */     //   62: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   65: aload_0
/*      */     //   66: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   69: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   72: ldc_w 1824
/*      */     //   75: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   78: pop
/*      */     //   79: aload 6
/*      */     //   81: aload_0
/*      */     //   82: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   85: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   88: ldc -123
/*      */     //   90: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   93: pop
/*      */     //   94: aload 6
/*      */     //   96: ldc_w 1826
/*      */     //   99: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   102: pop
/*      */     //   103: aload 6
/*      */     //   105: ldc_w 1828
/*      */     //   108: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   111: pop
/*      */     //   112: aload 6
/*      */     //   114: ldc_w 1830
/*      */     //   117: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   120: pop
/*      */     //   121: aload 6
/*      */     //   123: ldc_w 1832
/*      */     //   126: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   129: pop
/*      */     //   130: aload 6
/*      */     //   132: ldc_w 1834
/*      */     //   135: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   138: pop
/*      */     //   139: new 204	com/claro/transfer/RedencionTO
/*      */     //   142: dup
/*      */     //   143: invokespecial 206	com/claro/transfer/RedencionTO:<init>	()V
/*      */     //   146: astore 7
/*      */     //   148: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   151: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   154: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   157: astore_3
/*      */     //   158: aload_3
/*      */     //   159: aload 6
/*      */     //   161: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   164: invokeinterface 158 2 0
/*      */     //   169: astore 4
/*      */     //   171: aload 4
/*      */     //   173: iconst_1
/*      */     //   174: aload_1
/*      */     //   175: invokeinterface 164 3 0
/*      */     //   180: aload 4
/*      */     //   182: iconst_2
/*      */     //   183: iload_2
/*      */     //   184: invokeinterface 169 3 0
/*      */     //   189: aload 4
/*      */     //   191: invokeinterface 200 1 0
/*      */     //   196: astore 5
/*      */     //   198: aload 5
/*      */     //   200: invokeinterface 378 1 0
/*      */     //   205: ifeq +41 -> 246
/*      */     //   208: aload 7
/*      */     //   210: aload 5
/*      */     //   212: iconst_1
/*      */     //   213: invokeinterface 1575 2 0
/*      */     //   218: invokevirtual 352	com/claro/transfer/RedencionTO:setFechaFolio	(Ljava/sql/Timestamp;)V
/*      */     //   221: aload 7
/*      */     //   223: aload 5
/*      */     //   225: iconst_2
/*      */     //   226: invokeinterface 558 2 0
/*      */     //   231: invokevirtual 364	com/claro/transfer/RedencionTO:setFolio	(Ljava/lang/String;)V
/*      */     //   234: aload 7
/*      */     //   236: iconst_0
/*      */     //   237: ldc_w 1602
/*      */     //   240: invokevirtual 1604	com/claro/transfer/RedencionTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   243: goto +157 -> 400
/*      */     //   246: aload 7
/*      */     //   248: iconst_1
/*      */     //   249: ldc_w 1602
/*      */     //   252: invokevirtual 1604	com/claro/transfer/RedencionTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   255: goto +145 -> 400
/*      */     //   258: astore 8
/*      */     //   260: new 85	com/claro/exception/CAException
/*      */     //   263: dup
/*      */     //   264: iconst_m1
/*      */     //   265: new 382	java/lang/StringBuilder
/*      */     //   268: dup
/*      */     //   269: ldc_w 1836
/*      */     //   272: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   275: aload 8
/*      */     //   277: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   280: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   283: ldc_w 394
/*      */     //   286: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   289: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   292: aload 8
/*      */     //   294: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   297: athrow
/*      */     //   298: astore 8
/*      */     //   300: new 85	com/claro/exception/CAException
/*      */     //   303: dup
/*      */     //   304: iconst_m1
/*      */     //   305: new 382	java/lang/StringBuilder
/*      */     //   308: dup
/*      */     //   309: ldc_w 1838
/*      */     //   312: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   315: aload 8
/*      */     //   317: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   320: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   323: ldc_w 394
/*      */     //   326: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   329: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   332: aload 8
/*      */     //   334: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   337: athrow
/*      */     //   338: astore 9
/*      */     //   340: aload 5
/*      */     //   342: ifnull +18 -> 360
/*      */     //   345: aload 5
/*      */     //   347: invokeinterface 403 1 0
/*      */     //   352: aconst_null
/*      */     //   353: astore 5
/*      */     //   355: goto +5 -> 360
/*      */     //   358: astore 10
/*      */     //   360: aload 4
/*      */     //   362: ifnull +18 -> 380
/*      */     //   365: aload 4
/*      */     //   367: invokeinterface 406 1 0
/*      */     //   372: aconst_null
/*      */     //   373: astore 4
/*      */     //   375: goto +5 -> 380
/*      */     //   378: astore 10
/*      */     //   380: aload_3
/*      */     //   381: ifnull +16 -> 397
/*      */     //   384: aload_3
/*      */     //   385: invokeinterface 407 1 0
/*      */     //   390: aconst_null
/*      */     //   391: astore_3
/*      */     //   392: goto +5 -> 397
/*      */     //   395: astore 10
/*      */     //   397: aload 9
/*      */     //   399: athrow
/*      */     //   400: aload 5
/*      */     //   402: ifnull +18 -> 420
/*      */     //   405: aload 5
/*      */     //   407: invokeinterface 403 1 0
/*      */     //   412: aconst_null
/*      */     //   413: astore 5
/*      */     //   415: goto +5 -> 420
/*      */     //   418: astore 10
/*      */     //   420: aload 4
/*      */     //   422: ifnull +18 -> 440
/*      */     //   425: aload 4
/*      */     //   427: invokeinterface 406 1 0
/*      */     //   432: aconst_null
/*      */     //   433: astore 4
/*      */     //   435: goto +5 -> 440
/*      */     //   438: astore 10
/*      */     //   440: aload_3
/*      */     //   441: ifnull +16 -> 457
/*      */     //   444: aload_3
/*      */     //   445: invokeinterface 407 1 0
/*      */     //   450: aconst_null
/*      */     //   451: astore_3
/*      */     //   452: goto +5 -> 457
/*      */     //   455: astore 10
/*      */     //   457: aload 7
/*      */     //   459: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1287	-> byte code offset #0
/*      */     //   Java source line #1288	-> byte code offset #2
/*      */     //   Java source line #1289	-> byte code offset #5
/*      */     //   Java source line #1292	-> byte code offset #8
/*      */     //   Java source line #1293	-> byte code offset #17
/*      */     //   Java source line #1294	-> byte code offset #39
/*      */     //   Java source line #1295	-> byte code offset #48
/*      */     //   Java source line #1296	-> byte code offset #57
/*      */     //   Java source line #1297	-> byte code offset #79
/*      */     //   Java source line #1298	-> byte code offset #94
/*      */     //   Java source line #1299	-> byte code offset #103
/*      */     //   Java source line #1300	-> byte code offset #112
/*      */     //   Java source line #1301	-> byte code offset #121
/*      */     //   Java source line #1302	-> byte code offset #130
/*      */     //   Java source line #1305	-> byte code offset #139
/*      */     //   Java source line #1307	-> byte code offset #148
/*      */     //   Java source line #1308	-> byte code offset #158
/*      */     //   Java source line #1310	-> byte code offset #171
/*      */     //   Java source line #1311	-> byte code offset #180
/*      */     //   Java source line #1312	-> byte code offset #189
/*      */     //   Java source line #1313	-> byte code offset #198
/*      */     //   Java source line #1314	-> byte code offset #208
/*      */     //   Java source line #1315	-> byte code offset #221
/*      */     //   Java source line #1316	-> byte code offset #234
/*      */     //   Java source line #1318	-> byte code offset #246
/*      */     //   Java source line #1319	-> byte code offset #258
/*      */     //   Java source line #1320	-> byte code offset #260
/*      */     //   Java source line #1321	-> byte code offset #298
/*      */     //   Java source line #1322	-> byte code offset #300
/*      */     //   Java source line #1323	-> byte code offset #338
/*      */     //   Java source line #1324	-> byte code offset #340
/*      */     //   Java source line #1325	-> byte code offset #360
/*      */     //   Java source line #1326	-> byte code offset #380
/*      */     //   Java source line #1327	-> byte code offset #397
/*      */     //   Java source line #1324	-> byte code offset #400
/*      */     //   Java source line #1325	-> byte code offset #420
/*      */     //   Java source line #1326	-> byte code offset #440
/*      */     //   Java source line #1328	-> byte code offset #457
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	460	0	this	ConsultasDAO
/*      */     //   0	460	1	cuenta	String
/*      */     //   0	460	2	secuencia	int
/*      */     //   1	451	3	connection	Connection
/*      */     //   3	431	4	preparedStatement	PreparedStatement
/*      */     //   6	408	5	resultSet	ResultSet
/*      */     //   15	145	6	query	StringBuffer
/*      */     //   146	312	7	redencionTO	com.claro.transfer.RedencionTO
/*      */     //   258	35	8	e	SQLException
/*      */     //   298	35	8	e	Exception
/*      */     //   338	60	9	localObject	Object
/*      */     //   358	1	10	localException1	Exception
/*      */     //   378	1	10	localException2	Exception
/*      */     //   395	1	10	localException3	Exception
/*      */     //   418	1	10	localException4	Exception
/*      */     //   438	1	10	localException5	Exception
/*      */     //   455	1	10	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   148	255	258	java/sql/SQLException
/*      */     //   148	255	298	java/lang/Exception
/*      */     //   148	338	338	finally
/*      */     //   345	355	358	java/lang/Exception
/*      */     //   365	375	378	java/lang/Exception
/*      */     //   384	392	395	java/lang/Exception
/*      */     //   405	415	418	java/lang/Exception
/*      */     //   425	435	438	java/lang/Exception
/*      */     //   444	452	455	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.service.ReservacionServiceTO> consultaFolioDistribuidores(String telefono, String cuenta, String fzaVenta)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 4
/*      */     //   3: aconst_null
/*      */     //   4: astore 5
/*      */     //   6: aconst_null
/*      */     //   7: astore 6
/*      */     //   9: new 145	java/util/ArrayList
/*      */     //   12: dup
/*      */     //   13: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   16: astore 7
/*      */     //   18: new 114	java/lang/StringBuffer
/*      */     //   21: dup
/*      */     //   22: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   25: astore 8
/*      */     //   27: aload 8
/*      */     //   29: ldc_w 1843
/*      */     //   32: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   35: pop
/*      */     //   36: aload 8
/*      */     //   38: ldc_w 1845
/*      */     //   41: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   44: aload_0
/*      */     //   45: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   48: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   51: ldc_w 1847
/*      */     //   54: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   57: pop
/*      */     //   58: aload 8
/*      */     //   60: ldc_w 1849
/*      */     //   63: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   66: pop
/*      */     //   67: aload 8
/*      */     //   69: ldc_w 1851
/*      */     //   72: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   75: pop
/*      */     //   76: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   79: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   82: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   85: astore 4
/*      */     //   87: aload 4
/*      */     //   89: aload 8
/*      */     //   91: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   94: invokeinterface 158 2 0
/*      */     //   99: astore 5
/*      */     //   101: aload 5
/*      */     //   103: iconst_1
/*      */     //   104: aload_2
/*      */     //   105: invokeinterface 164 3 0
/*      */     //   110: aload 5
/*      */     //   112: iconst_2
/*      */     //   113: aload_1
/*      */     //   114: invokeinterface 164 3 0
/*      */     //   119: aload 5
/*      */     //   121: iconst_3
/*      */     //   122: aload_3
/*      */     //   123: invokeinterface 164 3 0
/*      */     //   128: aload 5
/*      */     //   130: invokeinterface 200 1 0
/*      */     //   135: astore 6
/*      */     //   137: goto +116 -> 253
/*      */     //   140: new 1853	com/claro/transfer/service/ReservacionServiceTO
/*      */     //   143: dup
/*      */     //   144: invokespecial 1855	com/claro/transfer/service/ReservacionServiceTO:<init>	()V
/*      */     //   147: astore 9
/*      */     //   149: aload 9
/*      */     //   151: aload 6
/*      */     //   153: iconst_1
/*      */     //   154: invokeinterface 1856 2 0
/*      */     //   159: invokestatic 1860	java/lang/Long:toHexString	(J)Ljava/lang/String;
/*      */     //   162: invokevirtual 1866	com/claro/transfer/service/ReservacionServiceTO:setFolio	(Ljava/lang/String;)V
/*      */     //   165: aload 9
/*      */     //   167: aload 6
/*      */     //   169: iconst_2
/*      */     //   170: invokeinterface 558 2 0
/*      */     //   175: invokevirtual 1867	com/claro/transfer/service/ReservacionServiceTO:setTelefono	(Ljava/lang/String;)V
/*      */     //   178: aload 9
/*      */     //   180: aload 6
/*      */     //   182: iconst_3
/*      */     //   183: invokeinterface 558 2 0
/*      */     //   188: invokevirtual 1868	com/claro/transfer/service/ReservacionServiceTO:setCuenta	(Ljava/lang/String;)V
/*      */     //   191: aload 9
/*      */     //   193: aload 6
/*      */     //   195: iconst_4
/*      */     //   196: invokeinterface 558 2 0
/*      */     //   201: invokevirtual 1869	com/claro/transfer/service/ReservacionServiceTO:setFechaOperacion	(Ljava/lang/String;)V
/*      */     //   204: aload 9
/*      */     //   206: aload 6
/*      */     //   208: iconst_5
/*      */     //   209: invokeinterface 558 2 0
/*      */     //   214: invokevirtual 1871	com/claro/transfer/service/ReservacionServiceTO:setFechaExpiracion	(Ljava/lang/String;)V
/*      */     //   217: aload 9
/*      */     //   219: aload 6
/*      */     //   221: bipush 6
/*      */     //   223: invokeinterface 558 2 0
/*      */     //   228: invokevirtual 1873	com/claro/transfer/service/ReservacionServiceTO:setEstatus	(Ljava/lang/String;)V
/*      */     //   231: aload 9
/*      */     //   233: aload 6
/*      */     //   235: bipush 7
/*      */     //   237: invokeinterface 558 2 0
/*      */     //   242: invokevirtual 1874	com/claro/transfer/service/ReservacionServiceTO:setFuerzaVenta	(Ljava/lang/String;)V
/*      */     //   245: aload 7
/*      */     //   247: aload 9
/*      */     //   249: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   252: pop
/*      */     //   253: aload 6
/*      */     //   255: invokeinterface 378 1 0
/*      */     //   260: ifne -120 -> 140
/*      */     //   263: goto +148 -> 411
/*      */     //   266: astore 9
/*      */     //   268: new 85	com/claro/exception/CAException
/*      */     //   271: dup
/*      */     //   272: iconst_m1
/*      */     //   273: new 382	java/lang/StringBuilder
/*      */     //   276: dup
/*      */     //   277: ldc_w 1836
/*      */     //   280: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   283: aload 9
/*      */     //   285: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   288: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   291: ldc_w 394
/*      */     //   294: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   297: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   300: aload 9
/*      */     //   302: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   305: athrow
/*      */     //   306: astore 9
/*      */     //   308: new 85	com/claro/exception/CAException
/*      */     //   311: dup
/*      */     //   312: iconst_m1
/*      */     //   313: new 382	java/lang/StringBuilder
/*      */     //   316: dup
/*      */     //   317: ldc_w 1838
/*      */     //   320: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   323: aload 9
/*      */     //   325: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   328: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   331: ldc_w 394
/*      */     //   334: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   337: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   340: aload 9
/*      */     //   342: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   345: athrow
/*      */     //   346: astore 10
/*      */     //   348: aload 6
/*      */     //   350: ifnull +18 -> 368
/*      */     //   353: aload 6
/*      */     //   355: invokeinterface 403 1 0
/*      */     //   360: aconst_null
/*      */     //   361: astore 6
/*      */     //   363: goto +5 -> 368
/*      */     //   366: astore 11
/*      */     //   368: aload 5
/*      */     //   370: ifnull +18 -> 388
/*      */     //   373: aload 5
/*      */     //   375: invokeinterface 406 1 0
/*      */     //   380: aconst_null
/*      */     //   381: astore 5
/*      */     //   383: goto +5 -> 388
/*      */     //   386: astore 11
/*      */     //   388: aload 4
/*      */     //   390: ifnull +18 -> 408
/*      */     //   393: aload 4
/*      */     //   395: invokeinterface 407 1 0
/*      */     //   400: aconst_null
/*      */     //   401: astore 4
/*      */     //   403: goto +5 -> 408
/*      */     //   406: astore 11
/*      */     //   408: aload 10
/*      */     //   410: athrow
/*      */     //   411: aload 6
/*      */     //   413: ifnull +18 -> 431
/*      */     //   416: aload 6
/*      */     //   418: invokeinterface 403 1 0
/*      */     //   423: aconst_null
/*      */     //   424: astore 6
/*      */     //   426: goto +5 -> 431
/*      */     //   429: astore 11
/*      */     //   431: aload 5
/*      */     //   433: ifnull +18 -> 451
/*      */     //   436: aload 5
/*      */     //   438: invokeinterface 406 1 0
/*      */     //   443: aconst_null
/*      */     //   444: astore 5
/*      */     //   446: goto +5 -> 451
/*      */     //   449: astore 11
/*      */     //   451: aload 4
/*      */     //   453: ifnull +18 -> 471
/*      */     //   456: aload 4
/*      */     //   458: invokeinterface 407 1 0
/*      */     //   463: aconst_null
/*      */     //   464: astore 4
/*      */     //   466: goto +5 -> 471
/*      */     //   469: astore 11
/*      */     //   471: aload 7
/*      */     //   473: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1340	-> byte code offset #0
/*      */     //   Java source line #1341	-> byte code offset #3
/*      */     //   Java source line #1342	-> byte code offset #6
/*      */     //   Java source line #1343	-> byte code offset #9
/*      */     //   Java source line #1345	-> byte code offset #18
/*      */     //   Java source line #1346	-> byte code offset #27
/*      */     //   Java source line #1347	-> byte code offset #36
/*      */     //   Java source line #1348	-> byte code offset #58
/*      */     //   Java source line #1349	-> byte code offset #67
/*      */     //   Java source line #1352	-> byte code offset #76
/*      */     //   Java source line #1353	-> byte code offset #87
/*      */     //   Java source line #1355	-> byte code offset #101
/*      */     //   Java source line #1356	-> byte code offset #110
/*      */     //   Java source line #1357	-> byte code offset #119
/*      */     //   Java source line #1358	-> byte code offset #128
/*      */     //   Java source line #1360	-> byte code offset #137
/*      */     //   Java source line #1361	-> byte code offset #140
/*      */     //   Java source line #1362	-> byte code offset #149
/*      */     //   Java source line #1363	-> byte code offset #165
/*      */     //   Java source line #1364	-> byte code offset #178
/*      */     //   Java source line #1365	-> byte code offset #191
/*      */     //   Java source line #1366	-> byte code offset #204
/*      */     //   Java source line #1367	-> byte code offset #217
/*      */     //   Java source line #1368	-> byte code offset #231
/*      */     //   Java source line #1369	-> byte code offset #245
/*      */     //   Java source line #1360	-> byte code offset #253
/*      */     //   Java source line #1371	-> byte code offset #266
/*      */     //   Java source line #1372	-> byte code offset #268
/*      */     //   Java source line #1373	-> byte code offset #306
/*      */     //   Java source line #1374	-> byte code offset #308
/*      */     //   Java source line #1375	-> byte code offset #346
/*      */     //   Java source line #1376	-> byte code offset #348
/*      */     //   Java source line #1377	-> byte code offset #368
/*      */     //   Java source line #1378	-> byte code offset #388
/*      */     //   Java source line #1379	-> byte code offset #408
/*      */     //   Java source line #1376	-> byte code offset #411
/*      */     //   Java source line #1377	-> byte code offset #431
/*      */     //   Java source line #1378	-> byte code offset #451
/*      */     //   Java source line #1380	-> byte code offset #471
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	474	0	this	ConsultasDAO
/*      */     //   0	474	1	telefono	String
/*      */     //   0	474	2	cuenta	String
/*      */     //   0	474	3	fzaVenta	String
/*      */     //   1	464	4	connection	Connection
/*      */     //   4	441	5	preparedStatement	PreparedStatement
/*      */     //   7	418	6	resultSet	ResultSet
/*      */     //   16	456	7	folios	ArrayList<com.claro.transfer.service.ReservacionServiceTO>
/*      */     //   25	65	8	query	StringBuffer
/*      */     //   147	101	9	reservacion	com.claro.transfer.service.ReservacionServiceTO
/*      */     //   266	35	9	e	SQLException
/*      */     //   306	35	9	e	Exception
/*      */     //   346	63	10	localObject	Object
/*      */     //   366	1	11	localException1	Exception
/*      */     //   386	1	11	localException2	Exception
/*      */     //   406	1	11	localException3	Exception
/*      */     //   429	1	11	localException4	Exception
/*      */     //   449	1	11	localException5	Exception
/*      */     //   469	1	11	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   76	263	266	java/sql/SQLException
/*      */     //   76	263	306	java/lang/Exception
/*      */     //   76	346	346	finally
/*      */     //   353	363	366	java/lang/Exception
/*      */     //   373	383	386	java/lang/Exception
/*      */     //   393	403	406	java/lang/Exception
/*      */     //   416	426	429	java/lang/Exception
/*      */     //   436	446	449	java/lang/Exception
/*      */     //   456	466	469	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public PuntosTO obtienePuntos(String cuenta, int secuencia)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aconst_null
/*      */     //   3: astore 4
/*      */     //   5: aconst_null
/*      */     //   6: astore 5
/*      */     //   8: new 784	com/claro/transfer/PuntosTO
/*      */     //   11: dup
/*      */     //   12: invokespecial 1092	com/claro/transfer/PuntosTO:<init>	()V
/*      */     //   15: astore 6
/*      */     //   17: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   20: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   23: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   26: astore_3
/*      */     //   27: new 114	java/lang/StringBuffer
/*      */     //   30: dup
/*      */     //   31: ldc_w 1882
/*      */     //   34: invokespecial 1622	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
/*      */     //   37: astore 7
/*      */     //   39: aload 7
/*      */     //   41: ldc_w 1884
/*      */     //   44: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   47: pop
/*      */     //   48: aload 7
/*      */     //   50: ldc_w 1886
/*      */     //   53: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   56: pop
/*      */     //   57: aload 7
/*      */     //   59: ldc_w 1643
/*      */     //   62: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   65: aload_0
/*      */     //   66: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   69: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   72: ldc_w 1888
/*      */     //   75: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   78: pop
/*      */     //   79: aload 7
/*      */     //   81: aload_0
/*      */     //   82: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   85: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   88: ldc_w 1890
/*      */     //   91: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   94: pop
/*      */     //   95: aload 7
/*      */     //   97: ldc_w 1892
/*      */     //   100: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   103: pop
/*      */     //   104: aload 7
/*      */     //   106: ldc_w 1894
/*      */     //   109: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   112: pop
/*      */     //   113: aload_3
/*      */     //   114: aload 7
/*      */     //   116: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   119: invokeinterface 158 2 0
/*      */     //   124: astore 5
/*      */     //   126: aload 5
/*      */     //   128: iconst_1
/*      */     //   129: aload_1
/*      */     //   130: invokeinterface 164 3 0
/*      */     //   135: aload 5
/*      */     //   137: iconst_2
/*      */     //   138: iload_2
/*      */     //   139: invokeinterface 169 3 0
/*      */     //   144: aload 5
/*      */     //   146: invokeinterface 200 1 0
/*      */     //   151: astore 4
/*      */     //   153: aload 4
/*      */     //   155: invokeinterface 378 1 0
/*      */     //   160: ifeq +248 -> 408
/*      */     //   163: aload 6
/*      */     //   165: aload 4
/*      */     //   167: iconst_1
/*      */     //   168: invokeinterface 566 2 0
/*      */     //   173: invokevirtual 1130	com/claro/transfer/PuntosTO:setPtsRenta	(I)V
/*      */     //   176: aload 6
/*      */     //   178: aload 4
/*      */     //   180: iconst_2
/*      */     //   181: invokeinterface 566 2 0
/*      */     //   186: invokevirtual 1135	com/claro/transfer/PuntosTO:setPtsExcedentes	(I)V
/*      */     //   189: aload 6
/*      */     //   191: aload 4
/*      */     //   193: iconst_3
/*      */     //   194: invokeinterface 566 2 0
/*      */     //   199: invokevirtual 1125	com/claro/transfer/PuntosTO:setPtsPorVencer2	(I)V
/*      */     //   202: aload 6
/*      */     //   204: aload 4
/*      */     //   206: iconst_4
/*      */     //   207: invokeinterface 566 2 0
/*      */     //   212: invokevirtual 1120	com/claro/transfer/PuntosTO:setPtsPorVencer1	(I)V
/*      */     //   215: aload 6
/*      */     //   217: aload 4
/*      */     //   219: iconst_5
/*      */     //   220: invokeinterface 566 2 0
/*      */     //   225: invokevirtual 1115	com/claro/transfer/PuntosTO:setPtsPorVencer	(I)V
/*      */     //   228: aload 6
/*      */     //   230: aload 4
/*      */     //   232: bipush 6
/*      */     //   234: invokeinterface 566 2 0
/*      */     //   239: invokevirtual 1100	com/claro/transfer/PuntosTO:setPtsRedimidos	(I)V
/*      */     //   242: aload 6
/*      */     //   244: aload 4
/*      */     //   246: bipush 7
/*      */     //   248: invokeinterface 566 2 0
/*      */     //   253: invokevirtual 1145	com/claro/transfer/PuntosTO:setPtsPromocion	(I)V
/*      */     //   256: aload 6
/*      */     //   258: aload 4
/*      */     //   260: bipush 8
/*      */     //   262: invokeinterface 566 2 0
/*      */     //   267: invokevirtual 1140	com/claro/transfer/PuntosTO:setPtsAntiguedad	(I)V
/*      */     //   270: aload 6
/*      */     //   272: aload 4
/*      */     //   274: bipush 9
/*      */     //   276: invokeinterface 558 2 0
/*      */     //   281: invokevirtual 1896	com/claro/transfer/PuntosTO:setBBono	(Ljava/lang/String;)V
/*      */     //   284: aload 6
/*      */     //   286: aload 4
/*      */     //   288: bipush 10
/*      */     //   290: invokeinterface 566 2 0
/*      */     //   295: invokevirtual 1173	com/claro/transfer/PuntosTO:setBonoEquipo	(I)V
/*      */     //   298: aload 6
/*      */     //   300: aload 4
/*      */     //   302: bipush 11
/*      */     //   304: invokeinterface 558 2 0
/*      */     //   309: invokevirtual 1899	com/claro/transfer/PuntosTO:setEstatusPuntos	(Ljava/lang/String;)V
/*      */     //   312: aload 6
/*      */     //   314: aload 4
/*      */     //   316: bipush 12
/*      */     //   318: invokeinterface 1184 2 0
/*      */     //   323: invokevirtual 1150	com/claro/transfer/PuntosTO:setFecVencer	(Ljava/sql/Date;)V
/*      */     //   326: aload 6
/*      */     //   328: aload 4
/*      */     //   330: bipush 13
/*      */     //   332: invokeinterface 1184 2 0
/*      */     //   337: invokevirtual 1160	com/claro/transfer/PuntosTO:setFecVencer1	(Ljava/sql/Date;)V
/*      */     //   340: aload 6
/*      */     //   342: aload 4
/*      */     //   344: bipush 14
/*      */     //   346: invokeinterface 1184 2 0
/*      */     //   351: invokevirtual 1155	com/claro/transfer/PuntosTO:setFecVencer2	(Ljava/sql/Date;)V
/*      */     //   354: aload 6
/*      */     //   356: aload 4
/*      */     //   358: bipush 15
/*      */     //   360: invokeinterface 566 2 0
/*      */     //   365: invokevirtual 1105	com/claro/transfer/PuntosTO:setPtsTransferidos	(I)V
/*      */     //   368: aload 6
/*      */     //   370: aload 4
/*      */     //   372: bipush 16
/*      */     //   374: invokeinterface 566 2 0
/*      */     //   379: invokevirtual 1110	com/claro/transfer/PuntosTO:setPtsVencidos	(I)V
/*      */     //   382: aload 6
/*      */     //   384: aload 4
/*      */     //   386: bipush 17
/*      */     //   388: invokeinterface 1184 2 0
/*      */     //   393: invokevirtual 1902	com/claro/transfer/PuntosTO:setFecFactura	(Ljava/sql/Date;)V
/*      */     //   396: aload 6
/*      */     //   398: iconst_0
/*      */     //   399: ldc_w 600
/*      */     //   402: invokevirtual 1903	com/claro/transfer/PuntosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   405: goto +157 -> 562
/*      */     //   408: aload 6
/*      */     //   410: iconst_1
/*      */     //   411: ldc_w 1904
/*      */     //   414: invokevirtual 1903	com/claro/transfer/PuntosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   417: goto +145 -> 562
/*      */     //   420: astore 7
/*      */     //   422: new 85	com/claro/exception/CAException
/*      */     //   425: dup
/*      */     //   426: iconst_m1
/*      */     //   427: new 382	java/lang/StringBuilder
/*      */     //   430: dup
/*      */     //   431: ldc_w 1906
/*      */     //   434: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   437: aload 7
/*      */     //   439: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   442: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   445: ldc_w 394
/*      */     //   448: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   451: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   454: aload 7
/*      */     //   456: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   459: athrow
/*      */     //   460: astore 7
/*      */     //   462: new 85	com/claro/exception/CAException
/*      */     //   465: dup
/*      */     //   466: iconst_m1
/*      */     //   467: new 382	java/lang/StringBuilder
/*      */     //   470: dup
/*      */     //   471: ldc_w 1908
/*      */     //   474: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   477: aload 7
/*      */     //   479: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   482: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   485: ldc_w 394
/*      */     //   488: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   491: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   494: aload 7
/*      */     //   496: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   499: athrow
/*      */     //   500: astore 8
/*      */     //   502: aload 4
/*      */     //   504: ifnull +18 -> 522
/*      */     //   507: aload 4
/*      */     //   509: invokeinterface 403 1 0
/*      */     //   514: aconst_null
/*      */     //   515: astore 4
/*      */     //   517: goto +5 -> 522
/*      */     //   520: astore 9
/*      */     //   522: aload 5
/*      */     //   524: ifnull +18 -> 542
/*      */     //   527: aload 5
/*      */     //   529: invokeinterface 406 1 0
/*      */     //   534: aconst_null
/*      */     //   535: astore 5
/*      */     //   537: goto +5 -> 542
/*      */     //   540: astore 9
/*      */     //   542: aload_3
/*      */     //   543: ifnull +16 -> 559
/*      */     //   546: aload_3
/*      */     //   547: invokeinterface 407 1 0
/*      */     //   552: aconst_null
/*      */     //   553: astore_3
/*      */     //   554: goto +5 -> 559
/*      */     //   557: astore 9
/*      */     //   559: aload 8
/*      */     //   561: athrow
/*      */     //   562: aload 4
/*      */     //   564: ifnull +18 -> 582
/*      */     //   567: aload 4
/*      */     //   569: invokeinterface 403 1 0
/*      */     //   574: aconst_null
/*      */     //   575: astore 4
/*      */     //   577: goto +5 -> 582
/*      */     //   580: astore 9
/*      */     //   582: aload 5
/*      */     //   584: ifnull +18 -> 602
/*      */     //   587: aload 5
/*      */     //   589: invokeinterface 406 1 0
/*      */     //   594: aconst_null
/*      */     //   595: astore 5
/*      */     //   597: goto +5 -> 602
/*      */     //   600: astore 9
/*      */     //   602: aload_3
/*      */     //   603: ifnull +16 -> 619
/*      */     //   606: aload_3
/*      */     //   607: invokeinterface 407 1 0
/*      */     //   612: aconst_null
/*      */     //   613: astore_3
/*      */     //   614: goto +5 -> 619
/*      */     //   617: astore 9
/*      */     //   619: aload 6
/*      */     //   621: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1392	-> byte code offset #0
/*      */     //   Java source line #1393	-> byte code offset #2
/*      */     //   Java source line #1394	-> byte code offset #5
/*      */     //   Java source line #1395	-> byte code offset #8
/*      */     //   Java source line #1397	-> byte code offset #17
/*      */     //   Java source line #1398	-> byte code offset #27
/*      */     //   Java source line #1399	-> byte code offset #39
/*      */     //   Java source line #1400	-> byte code offset #48
/*      */     //   Java source line #1401	-> byte code offset #57
/*      */     //   Java source line #1402	-> byte code offset #79
/*      */     //   Java source line #1403	-> byte code offset #95
/*      */     //   Java source line #1404	-> byte code offset #104
/*      */     //   Java source line #1405	-> byte code offset #113
/*      */     //   Java source line #1406	-> byte code offset #126
/*      */     //   Java source line #1407	-> byte code offset #135
/*      */     //   Java source line #1408	-> byte code offset #144
/*      */     //   Java source line #1409	-> byte code offset #153
/*      */     //   Java source line #1410	-> byte code offset #163
/*      */     //   Java source line #1411	-> byte code offset #176
/*      */     //   Java source line #1412	-> byte code offset #189
/*      */     //   Java source line #1413	-> byte code offset #202
/*      */     //   Java source line #1414	-> byte code offset #215
/*      */     //   Java source line #1415	-> byte code offset #228
/*      */     //   Java source line #1416	-> byte code offset #242
/*      */     //   Java source line #1417	-> byte code offset #256
/*      */     //   Java source line #1418	-> byte code offset #270
/*      */     //   Java source line #1419	-> byte code offset #284
/*      */     //   Java source line #1420	-> byte code offset #298
/*      */     //   Java source line #1421	-> byte code offset #312
/*      */     //   Java source line #1422	-> byte code offset #326
/*      */     //   Java source line #1423	-> byte code offset #340
/*      */     //   Java source line #1424	-> byte code offset #354
/*      */     //   Java source line #1425	-> byte code offset #368
/*      */     //   Java source line #1426	-> byte code offset #382
/*      */     //   Java source line #1427	-> byte code offset #396
/*      */     //   Java source line #1428	-> byte code offset #408
/*      */     //   Java source line #1430	-> byte code offset #420
/*      */     //   Java source line #1431	-> byte code offset #422
/*      */     //   Java source line #1432	-> byte code offset #460
/*      */     //   Java source line #1433	-> byte code offset #462
/*      */     //   Java source line #1434	-> byte code offset #500
/*      */     //   Java source line #1435	-> byte code offset #502
/*      */     //   Java source line #1436	-> byte code offset #522
/*      */     //   Java source line #1437	-> byte code offset #542
/*      */     //   Java source line #1438	-> byte code offset #559
/*      */     //   Java source line #1435	-> byte code offset #562
/*      */     //   Java source line #1436	-> byte code offset #582
/*      */     //   Java source line #1437	-> byte code offset #602
/*      */     //   Java source line #1439	-> byte code offset #619
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	622	0	this	ConsultasDAO
/*      */     //   0	622	1	cuenta	String
/*      */     //   0	622	2	secuencia	int
/*      */     //   1	613	3	connection	Connection
/*      */     //   3	573	4	resultSet	ResultSet
/*      */     //   6	590	5	preparedStatement	PreparedStatement
/*      */     //   15	605	6	puntosTO	PuntosTO
/*      */     //   37	78	7	query	StringBuffer
/*      */     //   420	35	7	e	SQLException
/*      */     //   460	35	7	e	Exception
/*      */     //   500	60	8	localObject	Object
/*      */     //   520	1	9	localException1	Exception
/*      */     //   540	1	9	localException2	Exception
/*      */     //   557	1	9	localException3	Exception
/*      */     //   580	1	9	localException4	Exception
/*      */     //   600	1	9	localException5	Exception
/*      */     //   617	1	9	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   17	417	420	java/sql/SQLException
/*      */     //   17	417	460	java/lang/Exception
/*      */     //   17	500	500	finally
/*      */     //   507	517	520	java/lang/Exception
/*      */     //   527	537	540	java/lang/Exception
/*      */     //   546	554	557	java/lang/Exception
/*      */     //   567	577	580	java/lang/Exception
/*      */     //   587	597	600	java/lang/Exception
/*      */     //   606	614	617	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public com.claro.transfer.PuntosRedimidosTO obtienePuntosRedimidos(String cuenta, java.sql.Timestamp fechaFolio)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 230	com/claro/transfer/PuntosRedimidosTO
/*      */     //   3: dup
/*      */     //   4: invokespecial 232	com/claro/transfer/PuntosRedimidosTO:<init>	()V
/*      */     //   7: astore_3
/*      */     //   8: aconst_null
/*      */     //   9: astore 4
/*      */     //   11: aconst_null
/*      */     //   12: astore 5
/*      */     //   14: aconst_null
/*      */     //   15: astore 6
/*      */     //   17: new 114	java/lang/StringBuffer
/*      */     //   20: dup
/*      */     //   21: ldc_w 1913
/*      */     //   24: invokespecial 1622	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
/*      */     //   27: astore 7
/*      */     //   29: aload 7
/*      */     //   31: ldc_w 1915
/*      */     //   34: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   37: pop
/*      */     //   38: aload 7
/*      */     //   40: ldc_w 1917
/*      */     //   43: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   46: pop
/*      */     //   47: aload 7
/*      */     //   49: ldc_w 1919
/*      */     //   52: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   55: pop
/*      */     //   56: aload 7
/*      */     //   58: ldc_w 1845
/*      */     //   61: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   64: aload_0
/*      */     //   65: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   68: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   71: ldc_w 1816
/*      */     //   74: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   77: pop
/*      */     //   78: aload 7
/*      */     //   80: ldc_w 1921
/*      */     //   83: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   86: pop
/*      */     //   87: aload 7
/*      */     //   89: ldc_w 1923
/*      */     //   92: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   95: pop
/*      */     //   96: aload 7
/*      */     //   98: ldc_w 1925
/*      */     //   101: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   104: pop
/*      */     //   105: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   108: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   111: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   114: astore 4
/*      */     //   116: aload 4
/*      */     //   118: aload 7
/*      */     //   120: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   123: invokeinterface 158 2 0
/*      */     //   128: astore 6
/*      */     //   130: aload 6
/*      */     //   132: iconst_1
/*      */     //   133: aload_1
/*      */     //   134: invokeinterface 164 3 0
/*      */     //   139: aload 6
/*      */     //   141: iconst_2
/*      */     //   142: aload_2
/*      */     //   143: invokeinterface 1927 3 0
/*      */     //   148: aload 6
/*      */     //   150: invokeinterface 200 1 0
/*      */     //   155: astore 5
/*      */     //   157: aload 5
/*      */     //   159: invokeinterface 378 1 0
/*      */     //   164: ifeq +256 -> 420
/*      */     //   167: aload_3
/*      */     //   168: aload 5
/*      */     //   170: iconst_1
/*      */     //   171: invokeinterface 566 2 0
/*      */     //   176: invokevirtual 1931	com/claro/transfer/PuntosRedimidosTO:setPtsRenta	(I)V
/*      */     //   179: aload_3
/*      */     //   180: aload 5
/*      */     //   182: iconst_2
/*      */     //   183: invokeinterface 566 2 0
/*      */     //   188: invokevirtual 1932	com/claro/transfer/PuntosRedimidosTO:setPtsRentaRedimidos	(I)V
/*      */     //   191: aload_3
/*      */     //   192: aload 5
/*      */     //   194: iconst_3
/*      */     //   195: invokeinterface 566 2 0
/*      */     //   200: invokevirtual 1935	com/claro/transfer/PuntosRedimidosTO:setPtsExcedentesRedimidos	(I)V
/*      */     //   203: aload_3
/*      */     //   204: aload 5
/*      */     //   206: iconst_4
/*      */     //   207: invokeinterface 566 2 0
/*      */     //   212: invokevirtual 1938	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer2	(I)V
/*      */     //   215: aload_3
/*      */     //   216: aload 5
/*      */     //   218: iconst_5
/*      */     //   219: invokeinterface 566 2 0
/*      */     //   224: invokevirtual 1939	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer2Redimidos	(I)V
/*      */     //   227: aload_3
/*      */     //   228: aload 5
/*      */     //   230: bipush 6
/*      */     //   232: invokeinterface 1184 2 0
/*      */     //   237: invokevirtual 1942	com/claro/transfer/PuntosRedimidosTO:setFecVencer2	(Ljava/sql/Date;)V
/*      */     //   240: aload_3
/*      */     //   241: aload 5
/*      */     //   243: bipush 7
/*      */     //   245: invokeinterface 566 2 0
/*      */     //   250: invokevirtual 1943	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer1	(I)V
/*      */     //   253: aload_3
/*      */     //   254: aload 5
/*      */     //   256: bipush 8
/*      */     //   258: invokeinterface 566 2 0
/*      */     //   263: invokevirtual 1944	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer1Redimidos	(I)V
/*      */     //   266: aload_3
/*      */     //   267: aload 5
/*      */     //   269: bipush 9
/*      */     //   271: invokeinterface 1184 2 0
/*      */     //   276: invokevirtual 1947	com/claro/transfer/PuntosRedimidosTO:setFecVencer1	(Ljava/sql/Date;)V
/*      */     //   279: aload_3
/*      */     //   280: aload 5
/*      */     //   282: bipush 10
/*      */     //   284: invokeinterface 566 2 0
/*      */     //   289: invokevirtual 1948	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer	(I)V
/*      */     //   292: aload_3
/*      */     //   293: aload 5
/*      */     //   295: bipush 11
/*      */     //   297: invokeinterface 566 2 0
/*      */     //   302: invokevirtual 1949	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencerRedimidos	(I)V
/*      */     //   305: aload_3
/*      */     //   306: aload 5
/*      */     //   308: bipush 12
/*      */     //   310: invokeinterface 1184 2 0
/*      */     //   315: invokevirtual 1952	com/claro/transfer/PuntosRedimidosTO:setFecVencer	(Ljava/sql/Date;)V
/*      */     //   318: aload_3
/*      */     //   319: aload 5
/*      */     //   321: bipush 13
/*      */     //   323: invokeinterface 566 2 0
/*      */     //   328: invokevirtual 1953	com/claro/transfer/PuntosRedimidosTO:setPtsPromocionRedimidos	(I)V
/*      */     //   331: aload_3
/*      */     //   332: aload 5
/*      */     //   334: bipush 14
/*      */     //   336: invokeinterface 566 2 0
/*      */     //   341: invokevirtual 1956	com/claro/transfer/PuntosRedimidosTO:setPtsPorAntiguedadRedimidos	(I)V
/*      */     //   344: aload_3
/*      */     //   345: aload 5
/*      */     //   347: bipush 15
/*      */     //   349: invokeinterface 566 2 0
/*      */     //   354: invokevirtual 1959	com/claro/transfer/PuntosRedimidosTO:setPtsRedimidos	(I)V
/*      */     //   357: aload_3
/*      */     //   358: aload 5
/*      */     //   360: bipush 16
/*      */     //   362: invokeinterface 566 2 0
/*      */     //   367: invokevirtual 1960	com/claro/transfer/PuntosRedimidosTO:setPtsSobrantes	(I)V
/*      */     //   370: aload_3
/*      */     //   371: aload 5
/*      */     //   373: bipush 17
/*      */     //   375: invokeinterface 566 2 0
/*      */     //   380: invokevirtual 1963	com/claro/transfer/PuntosRedimidosTO:setPtsSobrantes1	(I)V
/*      */     //   383: aload_3
/*      */     //   384: aload 5
/*      */     //   386: bipush 18
/*      */     //   388: invokeinterface 566 2 0
/*      */     //   393: invokevirtual 1966	com/claro/transfer/PuntosRedimidosTO:setPtsMinimos	(I)V
/*      */     //   396: aload_3
/*      */     //   397: aload 5
/*      */     //   399: bipush 19
/*      */     //   401: invokeinterface 566 2 0
/*      */     //   406: invokevirtual 1967	com/claro/transfer/PuntosRedimidosTO:setBonoProrrateo	(I)V
/*      */     //   409: aload_3
/*      */     //   410: iconst_0
/*      */     //   411: ldc_w 1602
/*      */     //   414: invokevirtual 1970	com/claro/transfer/PuntosRedimidosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   417: goto +159 -> 576
/*      */     //   420: aload_3
/*      */     //   421: iconst_1
/*      */     //   422: ldc_w 1971
/*      */     //   425: invokevirtual 1970	com/claro/transfer/PuntosRedimidosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   428: goto +148 -> 576
/*      */     //   431: astore 7
/*      */     //   433: new 85	com/claro/exception/CAException
/*      */     //   436: dup
/*      */     //   437: iconst_m1
/*      */     //   438: new 382	java/lang/StringBuilder
/*      */     //   441: dup
/*      */     //   442: ldc_w 1973
/*      */     //   445: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   448: aload 7
/*      */     //   450: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   453: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   456: ldc_w 394
/*      */     //   459: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   462: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   465: aload 7
/*      */     //   467: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   470: athrow
/*      */     //   471: astore 7
/*      */     //   473: new 85	com/claro/exception/CAException
/*      */     //   476: dup
/*      */     //   477: iconst_m1
/*      */     //   478: new 382	java/lang/StringBuilder
/*      */     //   481: dup
/*      */     //   482: ldc_w 1975
/*      */     //   485: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   488: aload 7
/*      */     //   490: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   493: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   496: ldc_w 394
/*      */     //   499: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   502: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   505: aload 7
/*      */     //   507: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   510: athrow
/*      */     //   511: astore 8
/*      */     //   513: aload 5
/*      */     //   515: ifnull +18 -> 533
/*      */     //   518: aload 5
/*      */     //   520: invokeinterface 403 1 0
/*      */     //   525: aconst_null
/*      */     //   526: astore 5
/*      */     //   528: goto +5 -> 533
/*      */     //   531: astore 9
/*      */     //   533: aload 6
/*      */     //   535: ifnull +18 -> 553
/*      */     //   538: aload 6
/*      */     //   540: invokeinterface 406 1 0
/*      */     //   545: aconst_null
/*      */     //   546: astore 6
/*      */     //   548: goto +5 -> 553
/*      */     //   551: astore 9
/*      */     //   553: aload 4
/*      */     //   555: ifnull +18 -> 573
/*      */     //   558: aload 4
/*      */     //   560: invokeinterface 407 1 0
/*      */     //   565: aconst_null
/*      */     //   566: astore 4
/*      */     //   568: goto +5 -> 573
/*      */     //   571: astore 9
/*      */     //   573: aload 8
/*      */     //   575: athrow
/*      */     //   576: aload 5
/*      */     //   578: ifnull +18 -> 596
/*      */     //   581: aload 5
/*      */     //   583: invokeinterface 403 1 0
/*      */     //   588: aconst_null
/*      */     //   589: astore 5
/*      */     //   591: goto +5 -> 596
/*      */     //   594: astore 9
/*      */     //   596: aload 6
/*      */     //   598: ifnull +18 -> 616
/*      */     //   601: aload 6
/*      */     //   603: invokeinterface 406 1 0
/*      */     //   608: aconst_null
/*      */     //   609: astore 6
/*      */     //   611: goto +5 -> 616
/*      */     //   614: astore 9
/*      */     //   616: aload 4
/*      */     //   618: ifnull +18 -> 636
/*      */     //   621: aload 4
/*      */     //   623: invokeinterface 407 1 0
/*      */     //   628: aconst_null
/*      */     //   629: astore 4
/*      */     //   631: goto +5 -> 636
/*      */     //   634: astore 9
/*      */     //   636: aload_3
/*      */     //   637: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1449	-> byte code offset #0
/*      */     //   Java source line #1450	-> byte code offset #8
/*      */     //   Java source line #1451	-> byte code offset #11
/*      */     //   Java source line #1452	-> byte code offset #14
/*      */     //   Java source line #1456	-> byte code offset #17
/*      */     //   Java source line #1457	-> byte code offset #29
/*      */     //   Java source line #1458	-> byte code offset #38
/*      */     //   Java source line #1459	-> byte code offset #47
/*      */     //   Java source line #1460	-> byte code offset #56
/*      */     //   Java source line #1461	-> byte code offset #78
/*      */     //   Java source line #1462	-> byte code offset #87
/*      */     //   Java source line #1463	-> byte code offset #96
/*      */     //   Java source line #1464	-> byte code offset #105
/*      */     //   Java source line #1467	-> byte code offset #116
/*      */     //   Java source line #1468	-> byte code offset #130
/*      */     //   Java source line #1469	-> byte code offset #139
/*      */     //   Java source line #1470	-> byte code offset #148
/*      */     //   Java source line #1471	-> byte code offset #157
/*      */     //   Java source line #1472	-> byte code offset #167
/*      */     //   Java source line #1473	-> byte code offset #179
/*      */     //   Java source line #1474	-> byte code offset #191
/*      */     //   Java source line #1475	-> byte code offset #203
/*      */     //   Java source line #1476	-> byte code offset #215
/*      */     //   Java source line #1477	-> byte code offset #227
/*      */     //   Java source line #1478	-> byte code offset #240
/*      */     //   Java source line #1479	-> byte code offset #253
/*      */     //   Java source line #1480	-> byte code offset #266
/*      */     //   Java source line #1481	-> byte code offset #279
/*      */     //   Java source line #1482	-> byte code offset #292
/*      */     //   Java source line #1483	-> byte code offset #305
/*      */     //   Java source line #1484	-> byte code offset #318
/*      */     //   Java source line #1485	-> byte code offset #331
/*      */     //   Java source line #1486	-> byte code offset #344
/*      */     //   Java source line #1487	-> byte code offset #357
/*      */     //   Java source line #1488	-> byte code offset #370
/*      */     //   Java source line #1489	-> byte code offset #383
/*      */     //   Java source line #1490	-> byte code offset #396
/*      */     //   Java source line #1491	-> byte code offset #409
/*      */     //   Java source line #1493	-> byte code offset #420
/*      */     //   Java source line #1495	-> byte code offset #431
/*      */     //   Java source line #1496	-> byte code offset #433
/*      */     //   Java source line #1497	-> byte code offset #471
/*      */     //   Java source line #1498	-> byte code offset #473
/*      */     //   Java source line #1499	-> byte code offset #511
/*      */     //   Java source line #1500	-> byte code offset #513
/*      */     //   Java source line #1501	-> byte code offset #533
/*      */     //   Java source line #1502	-> byte code offset #553
/*      */     //   Java source line #1503	-> byte code offset #573
/*      */     //   Java source line #1500	-> byte code offset #576
/*      */     //   Java source line #1501	-> byte code offset #596
/*      */     //   Java source line #1502	-> byte code offset #616
/*      */     //   Java source line #1504	-> byte code offset #636
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	638	0	this	ConsultasDAO
/*      */     //   0	638	1	cuenta	String
/*      */     //   0	638	2	fechaFolio	java.sql.Timestamp
/*      */     //   7	630	3	puntosRedimidosTO	com.claro.transfer.PuntosRedimidosTO
/*      */     //   9	621	4	connection	Connection
/*      */     //   12	578	5	resultSet	ResultSet
/*      */     //   15	595	6	preparedStatement	PreparedStatement
/*      */     //   27	92	7	query	StringBuffer
/*      */     //   431	35	7	e	SQLException
/*      */     //   471	35	7	e	Exception
/*      */     //   511	63	8	localObject	Object
/*      */     //   531	1	9	localException1	Exception
/*      */     //   551	1	9	localException2	Exception
/*      */     //   571	1	9	localException3	Exception
/*      */     //   594	1	9	localException4	Exception
/*      */     //   614	1	9	localException5	Exception
/*      */     //   634	1	9	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   17	428	431	java/sql/SQLException
/*      */     //   17	428	471	java/lang/Exception
/*      */     //   17	511	511	finally
/*      */     //   518	528	531	java/lang/Exception
/*      */     //   538	548	551	java/lang/Exception
/*      */     //   558	568	571	java/lang/Exception
/*      */     //   581	591	594	java/lang/Exception
/*      */     //   601	611	614	java/lang/Exception
/*      */     //   621	631	634	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.AlianzasTO> consultaCancelaCanje(String telefono, String cuentaAlianza, int opcion)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 4
/*      */     //   3: aconst_null
/*      */     //   4: astore 5
/*      */     //   6: aconst_null
/*      */     //   7: astore 6
/*      */     //   9: new 145	java/util/ArrayList
/*      */     //   12: dup
/*      */     //   13: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   16: astore 7
/*      */     //   18: ldc_w 1984
/*      */     //   21: astore 8
/*      */     //   23: new 382	java/lang/StringBuilder
/*      */     //   26: dup
/*      */     //   27: aload 8
/*      */     //   29: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   32: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   35: ldc_w 1643
/*      */     //   38: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   41: aload_0
/*      */     //   42: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   45: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   48: ldc_w 1986
/*      */     //   51: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   54: aload_0
/*      */     //   55: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   58: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   61: ldc_w 1988
/*      */     //   64: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   67: ldc_w 1990
/*      */     //   70: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   73: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   76: astore 8
/*      */     //   78: iload_3
/*      */     //   79: iconst_1
/*      */     //   80: if_icmpne +30 -> 110
/*      */     //   83: aload_2
/*      */     //   84: ifnull +26 -> 110
/*      */     //   87: new 382	java/lang/StringBuilder
/*      */     //   90: dup
/*      */     //   91: aload 8
/*      */     //   93: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   96: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   99: ldc_w 1992
/*      */     //   102: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   105: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   108: astore 8
/*      */     //   110: new 382	java/lang/StringBuilder
/*      */     //   113: dup
/*      */     //   114: aload 8
/*      */     //   116: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   119: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   122: ldc_w 1994
/*      */     //   125: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   128: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   131: astore 8
/*      */     //   133: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   136: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   139: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   142: astore 4
/*      */     //   144: aload 4
/*      */     //   146: aload 8
/*      */     //   148: invokeinterface 158 2 0
/*      */     //   153: astore 5
/*      */     //   155: aload 5
/*      */     //   157: iconst_1
/*      */     //   158: aload_1
/*      */     //   159: invokeinterface 164 3 0
/*      */     //   164: aload 5
/*      */     //   166: iconst_2
/*      */     //   167: iload_3
/*      */     //   168: invokeinterface 169 3 0
/*      */     //   173: iload_3
/*      */     //   174: iconst_1
/*      */     //   175: if_icmpne +16 -> 191
/*      */     //   178: aload_2
/*      */     //   179: ifnull +12 -> 191
/*      */     //   182: aload 5
/*      */     //   184: iconst_3
/*      */     //   185: aload_2
/*      */     //   186: invokeinterface 164 3 0
/*      */     //   191: aload 5
/*      */     //   193: invokeinterface 200 1 0
/*      */     //   198: astore 6
/*      */     //   200: invokestatic 605	java/lang/System:currentTimeMillis	()J
/*      */     //   203: lstore 9
/*      */     //   205: goto +125 -> 330
/*      */     //   208: new 1996	com/claro/transfer/AlianzasTO
/*      */     //   211: dup
/*      */     //   212: invokespecial 1998	com/claro/transfer/AlianzasTO:<init>	()V
/*      */     //   215: astore 11
/*      */     //   217: aload 11
/*      */     //   219: aload 6
/*      */     //   221: iconst_1
/*      */     //   222: invokeinterface 558 2 0
/*      */     //   227: invokevirtual 1999	com/claro/transfer/AlianzasTO:setFolio	(Ljava/lang/String;)V
/*      */     //   230: aload 11
/*      */     //   232: aload 6
/*      */     //   234: iconst_2
/*      */     //   235: invokeinterface 1184 2 0
/*      */     //   240: invokevirtual 2000	com/claro/transfer/AlianzasTO:setFechaOperacion	(Ljava/sql/Date;)V
/*      */     //   243: aload 11
/*      */     //   245: aload 6
/*      */     //   247: iconst_3
/*      */     //   248: invokeinterface 566 2 0
/*      */     //   253: invokevirtual 2001	com/claro/transfer/AlianzasTO:setPtsTransferidos	(I)V
/*      */     //   256: aload 11
/*      */     //   258: aload 6
/*      */     //   260: iconst_4
/*      */     //   261: invokeinterface 558 2 0
/*      */     //   266: invokevirtual 2002	com/claro/transfer/AlianzasTO:setCuentaAlianza	(Ljava/lang/String;)V
/*      */     //   269: aload 11
/*      */     //   271: aload 6
/*      */     //   273: iconst_5
/*      */     //   274: invokeinterface 566 2 0
/*      */     //   279: invokevirtual 2005	com/claro/transfer/AlianzasTO:setStatusTrans	(I)V
/*      */     //   282: iload_3
/*      */     //   283: iconst_2
/*      */     //   284: if_icmpne +38 -> 322
/*      */     //   287: aload 11
/*      */     //   289: invokevirtual 2008	com/claro/transfer/AlianzasTO:getFechaOperacion	()Ljava/sql/Date;
/*      */     //   292: invokevirtual 1290	java/sql/Date:getTime	()J
/*      */     //   295: lload 9
/*      */     //   297: invokestatic 2012	com/claro/util/Utils:calcularDiasEntreFechas	(JJ)J
/*      */     //   300: lstore 12
/*      */     //   302: lload 12
/*      */     //   304: ldc2_w 2016
/*      */     //   307: lcmp
/*      */     //   308: ifgt +22 -> 330
/*      */     //   311: aload 7
/*      */     //   313: aload 11
/*      */     //   315: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   318: pop
/*      */     //   319: goto +11 -> 330
/*      */     //   322: aload 7
/*      */     //   324: aload 11
/*      */     //   326: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   329: pop
/*      */     //   330: aload 6
/*      */     //   332: invokeinterface 378 1 0
/*      */     //   337: ifne -129 -> 208
/*      */     //   340: goto +148 -> 488
/*      */     //   343: astore 8
/*      */     //   345: new 85	com/claro/exception/CAException
/*      */     //   348: dup
/*      */     //   349: iconst_m1
/*      */     //   350: new 382	java/lang/StringBuilder
/*      */     //   353: dup
/*      */     //   354: ldc_w 1973
/*      */     //   357: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   360: aload 8
/*      */     //   362: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   365: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   368: ldc_w 394
/*      */     //   371: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   374: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   377: aload 8
/*      */     //   379: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   382: athrow
/*      */     //   383: astore 8
/*      */     //   385: new 85	com/claro/exception/CAException
/*      */     //   388: dup
/*      */     //   389: iconst_m1
/*      */     //   390: new 382	java/lang/StringBuilder
/*      */     //   393: dup
/*      */     //   394: ldc_w 1975
/*      */     //   397: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   400: aload 8
/*      */     //   402: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   405: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   408: ldc_w 394
/*      */     //   411: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   414: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   417: aload 8
/*      */     //   419: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   422: athrow
/*      */     //   423: astore 14
/*      */     //   425: aload 6
/*      */     //   427: ifnull +18 -> 445
/*      */     //   430: aload 6
/*      */     //   432: invokeinterface 403 1 0
/*      */     //   437: aconst_null
/*      */     //   438: astore 6
/*      */     //   440: goto +5 -> 445
/*      */     //   443: astore 15
/*      */     //   445: aload 5
/*      */     //   447: ifnull +18 -> 465
/*      */     //   450: aload 5
/*      */     //   452: invokeinterface 406 1 0
/*      */     //   457: aconst_null
/*      */     //   458: astore 5
/*      */     //   460: goto +5 -> 465
/*      */     //   463: astore 15
/*      */     //   465: aload 4
/*      */     //   467: ifnull +18 -> 485
/*      */     //   470: aload 4
/*      */     //   472: invokeinterface 407 1 0
/*      */     //   477: aconst_null
/*      */     //   478: astore 4
/*      */     //   480: goto +5 -> 485
/*      */     //   483: astore 15
/*      */     //   485: aload 14
/*      */     //   487: athrow
/*      */     //   488: aload 6
/*      */     //   490: ifnull +18 -> 508
/*      */     //   493: aload 6
/*      */     //   495: invokeinterface 403 1 0
/*      */     //   500: aconst_null
/*      */     //   501: astore 6
/*      */     //   503: goto +5 -> 508
/*      */     //   506: astore 15
/*      */     //   508: aload 5
/*      */     //   510: ifnull +18 -> 528
/*      */     //   513: aload 5
/*      */     //   515: invokeinterface 406 1 0
/*      */     //   520: aconst_null
/*      */     //   521: astore 5
/*      */     //   523: goto +5 -> 528
/*      */     //   526: astore 15
/*      */     //   528: aload 4
/*      */     //   530: ifnull +18 -> 548
/*      */     //   533: aload 4
/*      */     //   535: invokeinterface 407 1 0
/*      */     //   540: aconst_null
/*      */     //   541: astore 4
/*      */     //   543: goto +5 -> 548
/*      */     //   546: astore 15
/*      */     //   548: aload 7
/*      */     //   550: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1511	-> byte code offset #0
/*      */     //   Java source line #1512	-> byte code offset #3
/*      */     //   Java source line #1513	-> byte code offset #6
/*      */     //   Java source line #1514	-> byte code offset #9
/*      */     //   Java source line #1516	-> byte code offset #18
/*      */     //   Java source line #1518	-> byte code offset #23
/*      */     //   Java source line #1519	-> byte code offset #67
/*      */     //   Java source line #1518	-> byte code offset #73
/*      */     //   Java source line #1522	-> byte code offset #78
/*      */     //   Java source line #1523	-> byte code offset #87
/*      */     //   Java source line #1525	-> byte code offset #110
/*      */     //   Java source line #1529	-> byte code offset #133
/*      */     //   Java source line #1530	-> byte code offset #144
/*      */     //   Java source line #1532	-> byte code offset #155
/*      */     //   Java source line #1533	-> byte code offset #164
/*      */     //   Java source line #1534	-> byte code offset #173
/*      */     //   Java source line #1535	-> byte code offset #182
/*      */     //   Java source line #1537	-> byte code offset #191
/*      */     //   Java source line #1538	-> byte code offset #200
/*      */     //   Java source line #1539	-> byte code offset #205
/*      */     //   Java source line #1540	-> byte code offset #208
/*      */     //   Java source line #1541	-> byte code offset #217
/*      */     //   Java source line #1542	-> byte code offset #230
/*      */     //   Java source line #1543	-> byte code offset #243
/*      */     //   Java source line #1544	-> byte code offset #256
/*      */     //   Java source line #1545	-> byte code offset #269
/*      */     //   Java source line #1547	-> byte code offset #282
/*      */     //   Java source line #1548	-> byte code offset #287
/*      */     //   Java source line #1549	-> byte code offset #302
/*      */     //   Java source line #1550	-> byte code offset #311
/*      */     //   Java source line #1552	-> byte code offset #322
/*      */     //   Java source line #1539	-> byte code offset #330
/*      */     //   Java source line #1556	-> byte code offset #343
/*      */     //   Java source line #1557	-> byte code offset #345
/*      */     //   Java source line #1558	-> byte code offset #383
/*      */     //   Java source line #1559	-> byte code offset #385
/*      */     //   Java source line #1560	-> byte code offset #423
/*      */     //   Java source line #1561	-> byte code offset #425
/*      */     //   Java source line #1562	-> byte code offset #445
/*      */     //   Java source line #1563	-> byte code offset #465
/*      */     //   Java source line #1564	-> byte code offset #485
/*      */     //   Java source line #1561	-> byte code offset #488
/*      */     //   Java source line #1562	-> byte code offset #508
/*      */     //   Java source line #1563	-> byte code offset #528
/*      */     //   Java source line #1565	-> byte code offset #548
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	551	0	this	ConsultasDAO
/*      */     //   0	551	1	telefono	String
/*      */     //   0	551	2	cuentaAlianza	String
/*      */     //   0	551	3	opcion	int
/*      */     //   1	541	4	connection	Connection
/*      */     //   4	518	5	preparedStatement	PreparedStatement
/*      */     //   7	495	6	resultSet	ResultSet
/*      */     //   16	533	7	alianzas	ArrayList<com.claro.transfer.AlianzasTO>
/*      */     //   21	126	8	sQuery	String
/*      */     //   343	35	8	e	SQLException
/*      */     //   383	35	8	e	Exception
/*      */     //   203	93	9	diaActual	long
/*      */     //   215	110	11	alianzasTO	com.claro.transfer.AlianzasTO
/*      */     //   300	3	12	dias	long
/*      */     //   423	63	14	localObject	Object
/*      */     //   443	1	15	localException1	Exception
/*      */     //   463	1	15	localException2	Exception
/*      */     //   483	1	15	localException3	Exception
/*      */     //   506	1	15	localException4	Exception
/*      */     //   526	1	15	localException5	Exception
/*      */     //   546	1	15	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   18	340	343	java/sql/SQLException
/*      */     //   18	340	383	java/lang/Exception
/*      */     //   18	423	423	finally
/*      */     //   430	440	443	java/lang/Exception
/*      */     //   450	460	463	java/lang/Exception
/*      */     //   470	480	483	java/lang/Exception
/*      */     //   493	503	506	java/lang/Exception
/*      */     //   513	523	526	java/lang/Exception
/*      */     //   533	543	546	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public com.claro.transfer.PuntosRedimidosTO consultaPuntosRedimAlianza(String folio)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_2
/*      */     //   2: aconst_null
/*      */     //   3: astore_3
/*      */     //   4: aconst_null
/*      */     //   5: astore 4
/*      */     //   7: new 230	com/claro/transfer/PuntosRedimidosTO
/*      */     //   10: dup
/*      */     //   11: invokespecial 232	com/claro/transfer/PuntosRedimidosTO:<init>	()V
/*      */     //   14: astore 5
/*      */     //   16: new 382	java/lang/StringBuilder
/*      */     //   19: dup
/*      */     //   20: ldc_w 2028
/*      */     //   23: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   26: aload_0
/*      */     //   27: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   30: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   33: ldc_w 2030
/*      */     //   36: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   39: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   42: astore 6
/*      */     //   44: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   47: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   50: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   53: astore_2
/*      */     //   54: aload_2
/*      */     //   55: aload 6
/*      */     //   57: invokeinterface 158 2 0
/*      */     //   62: astore_3
/*      */     //   63: aload_3
/*      */     //   64: iconst_1
/*      */     //   65: aload_1
/*      */     //   66: invokeinterface 164 3 0
/*      */     //   71: aload_3
/*      */     //   72: invokeinterface 200 1 0
/*      */     //   77: astore 4
/*      */     //   79: aload 4
/*      */     //   81: invokeinterface 378 1 0
/*      */     //   86: ifeq +122 -> 208
/*      */     //   89: aload 5
/*      */     //   91: aload 4
/*      */     //   93: iconst_1
/*      */     //   94: invokeinterface 566 2 0
/*      */     //   99: invokevirtual 1932	com/claro/transfer/PuntosRedimidosTO:setPtsRentaRedimidos	(I)V
/*      */     //   102: aload 5
/*      */     //   104: aload 4
/*      */     //   106: iconst_2
/*      */     //   107: invokeinterface 566 2 0
/*      */     //   112: invokevirtual 1949	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencerRedimidos	(I)V
/*      */     //   115: aload 5
/*      */     //   117: aload 4
/*      */     //   119: iconst_3
/*      */     //   120: invokeinterface 566 2 0
/*      */     //   125: invokevirtual 1944	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer1Redimidos	(I)V
/*      */     //   128: aload 5
/*      */     //   130: aload 4
/*      */     //   132: iconst_4
/*      */     //   133: invokeinterface 566 2 0
/*      */     //   138: invokevirtual 1939	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer2Redimidos	(I)V
/*      */     //   141: aload 5
/*      */     //   143: aload 4
/*      */     //   145: iconst_5
/*      */     //   146: invokeinterface 566 2 0
/*      */     //   151: invokevirtual 1935	com/claro/transfer/PuntosRedimidosTO:setPtsExcedentesRedimidos	(I)V
/*      */     //   154: aload 5
/*      */     //   156: aload 4
/*      */     //   158: bipush 6
/*      */     //   160: invokeinterface 566 2 0
/*      */     //   165: invokevirtual 1956	com/claro/transfer/PuntosRedimidosTO:setPtsPorAntiguedadRedimidos	(I)V
/*      */     //   168: aload 5
/*      */     //   170: aload 4
/*      */     //   172: bipush 7
/*      */     //   174: invokeinterface 566 2 0
/*      */     //   179: invokevirtual 1953	com/claro/transfer/PuntosRedimidosTO:setPtsPromocionRedimidos	(I)V
/*      */     //   182: aload 5
/*      */     //   184: aload 4
/*      */     //   186: bipush 8
/*      */     //   188: invokeinterface 566 2 0
/*      */     //   193: invokevirtual 2032	com/claro/transfer/PuntosRedimidosTO:setPtsTransferidos	(I)V
/*      */     //   196: aload 5
/*      */     //   198: iconst_0
/*      */     //   199: ldc_w 1602
/*      */     //   202: invokevirtual 1970	com/claro/transfer/PuntosRedimidosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   205: goto +154 -> 359
/*      */     //   208: aload 5
/*      */     //   210: iconst_1
/*      */     //   211: ldc_w 2033
/*      */     //   214: invokevirtual 1970	com/claro/transfer/PuntosRedimidosTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   217: goto +142 -> 359
/*      */     //   220: astore 6
/*      */     //   222: new 85	com/claro/exception/CAException
/*      */     //   225: dup
/*      */     //   226: iconst_m1
/*      */     //   227: new 382	java/lang/StringBuilder
/*      */     //   230: dup
/*      */     //   231: ldc_w 2035
/*      */     //   234: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   237: aload 6
/*      */     //   239: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   242: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   245: ldc_w 394
/*      */     //   248: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   251: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   254: aload 6
/*      */     //   256: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   259: athrow
/*      */     //   260: astore 6
/*      */     //   262: new 85	com/claro/exception/CAException
/*      */     //   265: dup
/*      */     //   266: iconst_m1
/*      */     //   267: new 382	java/lang/StringBuilder
/*      */     //   270: dup
/*      */     //   271: ldc_w 2037
/*      */     //   274: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   277: aload 6
/*      */     //   279: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   282: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   285: ldc_w 394
/*      */     //   288: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   291: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   294: aload 6
/*      */     //   296: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   299: athrow
/*      */     //   300: astore 7
/*      */     //   302: aload 4
/*      */     //   304: ifnull +18 -> 322
/*      */     //   307: aload 4
/*      */     //   309: invokeinterface 403 1 0
/*      */     //   314: aconst_null
/*      */     //   315: astore 4
/*      */     //   317: goto +5 -> 322
/*      */     //   320: astore 8
/*      */     //   322: aload_3
/*      */     //   323: ifnull +16 -> 339
/*      */     //   326: aload_3
/*      */     //   327: invokeinterface 406 1 0
/*      */     //   332: aconst_null
/*      */     //   333: astore_3
/*      */     //   334: goto +5 -> 339
/*      */     //   337: astore 8
/*      */     //   339: aload_2
/*      */     //   340: ifnull +16 -> 356
/*      */     //   343: aload_2
/*      */     //   344: invokeinterface 407 1 0
/*      */     //   349: aconst_null
/*      */     //   350: astore_2
/*      */     //   351: goto +5 -> 356
/*      */     //   354: astore 8
/*      */     //   356: aload 7
/*      */     //   358: athrow
/*      */     //   359: aload 4
/*      */     //   361: ifnull +18 -> 379
/*      */     //   364: aload 4
/*      */     //   366: invokeinterface 403 1 0
/*      */     //   371: aconst_null
/*      */     //   372: astore 4
/*      */     //   374: goto +5 -> 379
/*      */     //   377: astore 8
/*      */     //   379: aload_3
/*      */     //   380: ifnull +16 -> 396
/*      */     //   383: aload_3
/*      */     //   384: invokeinterface 406 1 0
/*      */     //   389: aconst_null
/*      */     //   390: astore_3
/*      */     //   391: goto +5 -> 396
/*      */     //   394: astore 8
/*      */     //   396: aload_2
/*      */     //   397: ifnull +16 -> 413
/*      */     //   400: aload_2
/*      */     //   401: invokeinterface 407 1 0
/*      */     //   406: aconst_null
/*      */     //   407: astore_2
/*      */     //   408: goto +5 -> 413
/*      */     //   411: astore 8
/*      */     //   413: aload 5
/*      */     //   415: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1575	-> byte code offset #0
/*      */     //   Java source line #1576	-> byte code offset #2
/*      */     //   Java source line #1577	-> byte code offset #4
/*      */     //   Java source line #1578	-> byte code offset #7
/*      */     //   Java source line #1580	-> byte code offset #16
/*      */     //   Java source line #1582	-> byte code offset #26
/*      */     //   Java source line #1580	-> byte code offset #39
/*      */     //   Java source line #1583	-> byte code offset #44
/*      */     //   Java source line #1584	-> byte code offset #54
/*      */     //   Java source line #1585	-> byte code offset #63
/*      */     //   Java source line #1586	-> byte code offset #71
/*      */     //   Java source line #1587	-> byte code offset #79
/*      */     //   Java source line #1588	-> byte code offset #89
/*      */     //   Java source line #1589	-> byte code offset #102
/*      */     //   Java source line #1590	-> byte code offset #115
/*      */     //   Java source line #1591	-> byte code offset #128
/*      */     //   Java source line #1592	-> byte code offset #141
/*      */     //   Java source line #1593	-> byte code offset #154
/*      */     //   Java source line #1594	-> byte code offset #168
/*      */     //   Java source line #1595	-> byte code offset #182
/*      */     //   Java source line #1596	-> byte code offset #196
/*      */     //   Java source line #1598	-> byte code offset #208
/*      */     //   Java source line #1601	-> byte code offset #220
/*      */     //   Java source line #1602	-> byte code offset #222
/*      */     //   Java source line #1603	-> byte code offset #260
/*      */     //   Java source line #1604	-> byte code offset #262
/*      */     //   Java source line #1605	-> byte code offset #300
/*      */     //   Java source line #1606	-> byte code offset #302
/*      */     //   Java source line #1607	-> byte code offset #322
/*      */     //   Java source line #1608	-> byte code offset #339
/*      */     //   Java source line #1609	-> byte code offset #356
/*      */     //   Java source line #1606	-> byte code offset #359
/*      */     //   Java source line #1607	-> byte code offset #379
/*      */     //   Java source line #1608	-> byte code offset #396
/*      */     //   Java source line #1610	-> byte code offset #413
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	416	0	this	ConsultasDAO
/*      */     //   0	416	1	folio	String
/*      */     //   1	407	2	connection	Connection
/*      */     //   3	388	3	statement	PreparedStatement
/*      */     //   5	368	4	resultSet	ResultSet
/*      */     //   14	400	5	redimidosTO	com.claro.transfer.PuntosRedimidosTO
/*      */     //   42	14	6	sQuery	String
/*      */     //   220	35	6	e	SQLException
/*      */     //   260	35	6	e	Exception
/*      */     //   300	57	7	localObject	Object
/*      */     //   320	1	8	localException1	Exception
/*      */     //   337	1	8	localException2	Exception
/*      */     //   354	1	8	localException3	Exception
/*      */     //   377	1	8	localException4	Exception
/*      */     //   394	1	8	localException5	Exception
/*      */     //   411	1	8	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   16	217	220	java/sql/SQLException
/*      */     //   16	217	260	java/lang/Exception
/*      */     //   16	300	300	finally
/*      */     //   307	317	320	java/lang/Exception
/*      */     //   326	334	337	java/lang/Exception
/*      */     //   343	351	354	java/lang/Exception
/*      */     //   364	374	377	java/lang/Exception
/*      */     //   383	391	394	java/lang/Exception
/*      */     //   400	408	411	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public CicloFacturacionTO consultaCicloFac(int ciclo, int region)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aconst_null
/*      */     //   3: astore 4
/*      */     //   5: new 114	java/lang/StringBuffer
/*      */     //   8: dup
/*      */     //   9: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   12: astore 5
/*      */     //   14: aconst_null
/*      */     //   15: astore 6
/*      */     //   17: aconst_null
/*      */     //   18: astore 7
/*      */     //   20: aload 5
/*      */     //   22: ldc_w 2040
/*      */     //   25: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   28: pop
/*      */     //   29: aload 5
/*      */     //   31: ldc -127
/*      */     //   33: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   36: aload_0
/*      */     //   37: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   40: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   43: ldc_w 2042
/*      */     //   46: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   49: pop
/*      */     //   50: aload 5
/*      */     //   52: ldc_w 2044
/*      */     //   55: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   58: pop
/*      */     //   59: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   62: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   65: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   68: astore 6
/*      */     //   70: aload 6
/*      */     //   72: aload 5
/*      */     //   74: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   77: invokeinterface 158 2 0
/*      */     //   82: astore_3
/*      */     //   83: aload_3
/*      */     //   84: iconst_1
/*      */     //   85: iload_1
/*      */     //   86: invokeinterface 169 3 0
/*      */     //   91: aload_3
/*      */     //   92: iconst_2
/*      */     //   93: iload_2
/*      */     //   94: bipush 9
/*      */     //   96: if_icmpeq +7 -> 103
/*      */     //   99: iconst_1
/*      */     //   100: goto +4 -> 104
/*      */     //   103: iload_2
/*      */     //   104: invokeinterface 169 3 0
/*      */     //   109: aload_3
/*      */     //   110: invokeinterface 200 1 0
/*      */     //   115: astore 4
/*      */     //   117: aload 4
/*      */     //   119: invokeinterface 378 1 0
/*      */     //   124: ifeq +235 -> 359
/*      */     //   127: new 1083	com/claro/transfer/CicloFacturacionTO
/*      */     //   130: dup
/*      */     //   131: invokespecial 2046	com/claro/transfer/CicloFacturacionTO:<init>	()V
/*      */     //   134: astore 7
/*      */     //   136: aload 7
/*      */     //   138: aload 4
/*      */     //   140: ldc_w 2047
/*      */     //   143: invokeinterface 235 2 0
/*      */     //   148: invokevirtual 2049	com/claro/transfer/CicloFacturacionTO:setCicloFac	(I)V
/*      */     //   151: aload 7
/*      */     //   153: aload 4
/*      */     //   155: ldc_w 996
/*      */     //   158: invokeinterface 235 2 0
/*      */     //   163: invokevirtual 2052	com/claro/transfer/CicloFacturacionTO:setIdRegion	(I)V
/*      */     //   166: aload 7
/*      */     //   168: aload 4
/*      */     //   170: ldc_w 2053
/*      */     //   173: invokeinterface 258 2 0
/*      */     //   178: invokevirtual 2055	com/claro/transfer/CicloFacturacionTO:setFechaCorte	(Ljava/util/Date;)V
/*      */     //   181: aload 7
/*      */     //   183: aload 4
/*      */     //   185: ldc_w 313
/*      */     //   188: invokeinterface 212 2 0
/*      */     //   193: ifnull +16 -> 209
/*      */     //   196: aload 4
/*      */     //   198: ldc_w 313
/*      */     //   201: invokeinterface 212 2 0
/*      */     //   206: goto +5 -> 211
/*      */     //   209: ldc 89
/*      */     //   211: invokevirtual 2058	com/claro/transfer/CicloFacturacionTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   214: goto +145 -> 359
/*      */     //   217: astore 8
/*      */     //   219: new 85	com/claro/exception/CAException
/*      */     //   222: dup
/*      */     //   223: iconst_m1
/*      */     //   224: new 382	java/lang/StringBuilder
/*      */     //   227: dup
/*      */     //   228: ldc_w 2059
/*      */     //   231: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   234: aload 8
/*      */     //   236: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   239: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   242: ldc_w 394
/*      */     //   245: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   248: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   251: aload 8
/*      */     //   253: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   256: athrow
/*      */     //   257: astore 8
/*      */     //   259: new 85	com/claro/exception/CAException
/*      */     //   262: dup
/*      */     //   263: iconst_m1
/*      */     //   264: new 382	java/lang/StringBuilder
/*      */     //   267: dup
/*      */     //   268: ldc_w 2061
/*      */     //   271: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   274: aload 8
/*      */     //   276: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   279: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   282: ldc_w 394
/*      */     //   285: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   288: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   291: aload 8
/*      */     //   293: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   296: athrow
/*      */     //   297: astore 9
/*      */     //   299: aload 4
/*      */     //   301: ifnull +18 -> 319
/*      */     //   304: aload 4
/*      */     //   306: invokeinterface 403 1 0
/*      */     //   311: aconst_null
/*      */     //   312: astore 4
/*      */     //   314: goto +5 -> 319
/*      */     //   317: astore 10
/*      */     //   319: aload_3
/*      */     //   320: ifnull +16 -> 336
/*      */     //   323: aload_3
/*      */     //   324: invokeinterface 406 1 0
/*      */     //   329: aconst_null
/*      */     //   330: astore_3
/*      */     //   331: goto +5 -> 336
/*      */     //   334: astore 10
/*      */     //   336: aload 6
/*      */     //   338: ifnull +18 -> 356
/*      */     //   341: aload 6
/*      */     //   343: invokeinterface 407 1 0
/*      */     //   348: aconst_null
/*      */     //   349: astore 6
/*      */     //   351: goto +5 -> 356
/*      */     //   354: astore 10
/*      */     //   356: aload 9
/*      */     //   358: athrow
/*      */     //   359: aload 4
/*      */     //   361: ifnull +18 -> 379
/*      */     //   364: aload 4
/*      */     //   366: invokeinterface 403 1 0
/*      */     //   371: aconst_null
/*      */     //   372: astore 4
/*      */     //   374: goto +5 -> 379
/*      */     //   377: astore 10
/*      */     //   379: aload_3
/*      */     //   380: ifnull +16 -> 396
/*      */     //   383: aload_3
/*      */     //   384: invokeinterface 406 1 0
/*      */     //   389: aconst_null
/*      */     //   390: astore_3
/*      */     //   391: goto +5 -> 396
/*      */     //   394: astore 10
/*      */     //   396: aload 6
/*      */     //   398: ifnull +18 -> 416
/*      */     //   401: aload 6
/*      */     //   403: invokeinterface 407 1 0
/*      */     //   408: aconst_null
/*      */     //   409: astore 6
/*      */     //   411: goto +5 -> 416
/*      */     //   414: astore 10
/*      */     //   416: aload 7
/*      */     //   418: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1620	-> byte code offset #0
/*      */     //   Java source line #1621	-> byte code offset #2
/*      */     //   Java source line #1622	-> byte code offset #5
/*      */     //   Java source line #1623	-> byte code offset #14
/*      */     //   Java source line #1624	-> byte code offset #17
/*      */     //   Java source line #1625	-> byte code offset #20
/*      */     //   Java source line #1626	-> byte code offset #29
/*      */     //   Java source line #1627	-> byte code offset #50
/*      */     //   Java source line #1629	-> byte code offset #59
/*      */     //   Java source line #1630	-> byte code offset #70
/*      */     //   Java source line #1631	-> byte code offset #83
/*      */     //   Java source line #1632	-> byte code offset #91
/*      */     //   Java source line #1633	-> byte code offset #109
/*      */     //   Java source line #1634	-> byte code offset #117
/*      */     //   Java source line #1635	-> byte code offset #127
/*      */     //   Java source line #1636	-> byte code offset #136
/*      */     //   Java source line #1637	-> byte code offset #151
/*      */     //   Java source line #1638	-> byte code offset #166
/*      */     //   Java source line #1639	-> byte code offset #181
/*      */     //   Java source line #1641	-> byte code offset #217
/*      */     //   Java source line #1642	-> byte code offset #219
/*      */     //   Java source line #1643	-> byte code offset #257
/*      */     //   Java source line #1644	-> byte code offset #259
/*      */     //   Java source line #1645	-> byte code offset #297
/*      */     //   Java source line #1646	-> byte code offset #299
/*      */     //   Java source line #1647	-> byte code offset #319
/*      */     //   Java source line #1648	-> byte code offset #336
/*      */     //   Java source line #1649	-> byte code offset #356
/*      */     //   Java source line #1646	-> byte code offset #359
/*      */     //   Java source line #1647	-> byte code offset #379
/*      */     //   Java source line #1648	-> byte code offset #396
/*      */     //   Java source line #1650	-> byte code offset #416
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	419	0	this	ConsultasDAO
/*      */     //   0	419	1	ciclo	int
/*      */     //   0	419	2	region	int
/*      */     //   1	390	3	preparedStatement	PreparedStatement
/*      */     //   3	370	4	resultSet	ResultSet
/*      */     //   12	61	5	query	StringBuffer
/*      */     //   15	395	6	lConn	Connection
/*      */     //   18	399	7	cicloFacTO	CicloFacturacionTO
/*      */     //   217	35	8	e	SQLException
/*      */     //   257	35	8	e	Exception
/*      */     //   297	60	9	localObject	Object
/*      */     //   317	1	10	localException1	Exception
/*      */     //   334	1	10	localException2	Exception
/*      */     //   354	1	10	localException3	Exception
/*      */     //   377	1	10	localException4	Exception
/*      */     //   394	1	10	localException5	Exception
/*      */     //   414	1	10	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   59	214	217	java/sql/SQLException
/*      */     //   59	214	257	java/lang/Exception
/*      */     //   59	297	297	finally
/*      */     //   304	314	317	java/lang/Exception
/*      */     //   323	331	334	java/lang/Exception
/*      */     //   341	351	354	java/lang/Exception
/*      */     //   364	374	377	java/lang/Exception
/*      */     //   383	391	394	java/lang/Exception
/*      */     //   401	411	414	java/lang/Exception
/*      */   }
/*      */   
/*      */   public ArrayList<PlanTO> planesRedencionByRegion(int region)
/*      */     throws CAException
/*      */   {
/* 1656 */     return planesRedencion(region, null, null, null, null, null, null, false);
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayList<PlanTO> planesSisact(int region, String tecnologia, String modalidad, String mixto, String banSisact, String tipoPlan, String fzaVenta)
/*      */     throws CAException
/*      */   {
/* 1663 */     return planesRedencion(region, tecnologia, modalidad, mixto, banSisact, tipoPlan, fzaVenta, true);
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<PlanTO> planesRedencion(int region, String tecnologia, String modalidad, String mixto, String banSisact, String tipoPlan, String fzaVenta, boolean esSisact)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 9
/*      */     //   3: aconst_null
/*      */     //   4: astore 10
/*      */     //   6: new 114	java/lang/StringBuffer
/*      */     //   9: dup
/*      */     //   10: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   13: astore 11
/*      */     //   15: aconst_null
/*      */     //   16: astore 12
/*      */     //   18: new 145	java/util/ArrayList
/*      */     //   21: dup
/*      */     //   22: invokespecial 147	java/util/ArrayList:<init>	()V
/*      */     //   25: astore 13
/*      */     //   27: aconst_null
/*      */     //   28: astore 14
/*      */     //   30: aload 11
/*      */     //   32: ldc_w 2080
/*      */     //   35: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   38: pop
/*      */     //   39: aload 11
/*      */     //   41: ldc_w 2082
/*      */     //   44: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   47: pop
/*      */     //   48: aload 11
/*      */     //   50: ldc -127
/*      */     //   52: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   55: aload_0
/*      */     //   56: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   59: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   62: ldc_w 1334
/*      */     //   65: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   68: pop
/*      */     //   69: aload 11
/*      */     //   71: ldc_w 1336
/*      */     //   74: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   77: aload_0
/*      */     //   78: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   81: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   84: ldc_w 2084
/*      */     //   87: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   90: pop
/*      */     //   91: aload 11
/*      */     //   93: ldc_w 1336
/*      */     //   96: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   99: aload_0
/*      */     //   100: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   103: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   106: ldc_w 2086
/*      */     //   109: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   112: pop
/*      */     //   113: aload 11
/*      */     //   115: ldc_w 2088
/*      */     //   118: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   121: pop
/*      */     //   122: iload_1
/*      */     //   123: bipush 10
/*      */     //   125: if_icmpne +15 -> 140
/*      */     //   128: aload 11
/*      */     //   130: ldc_w 2090
/*      */     //   133: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   136: pop
/*      */     //   137: goto +12 -> 149
/*      */     //   140: aload 11
/*      */     //   142: ldc_w 1344
/*      */     //   145: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   148: pop
/*      */     //   149: aload 11
/*      */     //   151: ldc_w 2092
/*      */     //   154: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   157: pop
/*      */     //   158: aload 11
/*      */     //   160: ldc_w 2094
/*      */     //   163: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   166: pop
/*      */     //   167: iload 8
/*      */     //   169: ifeq +118 -> 287
/*      */     //   172: aload 11
/*      */     //   174: ldc_w 2096
/*      */     //   177: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   180: pop
/*      */     //   181: aload 11
/*      */     //   183: ldc_w 2098
/*      */     //   186: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   189: pop
/*      */     //   190: aload 11
/*      */     //   192: ldc_w 2100
/*      */     //   195: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   198: pop
/*      */     //   199: aload 11
/*      */     //   201: ldc_w 2102
/*      */     //   204: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   207: pop
/*      */     //   208: aload 11
/*      */     //   210: ldc_w 2104
/*      */     //   213: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   216: pop
/*      */     //   217: aload 11
/*      */     //   219: ldc_w 2106
/*      */     //   222: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   225: pop
/*      */     //   226: aload 11
/*      */     //   228: ldc_w 2108
/*      */     //   231: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   234: aload_0
/*      */     //   235: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   238: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   241: ldc_w 2110
/*      */     //   244: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   247: pop
/*      */     //   248: aload 11
/*      */     //   250: ldc_w 2112
/*      */     //   253: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   256: pop
/*      */     //   257: aload 11
/*      */     //   259: ldc_w 2114
/*      */     //   262: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   265: pop
/*      */     //   266: aload 11
/*      */     //   268: ldc_w 2116
/*      */     //   271: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   274: pop
/*      */     //   275: aload 11
/*      */     //   277: ldc_w 2118
/*      */     //   280: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   283: pop
/*      */     //   284: goto +12 -> 296
/*      */     //   287: aload 11
/*      */     //   289: ldc_w 2120
/*      */     //   292: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   295: pop
/*      */     //   296: aload 11
/*      */     //   298: ldc_w 2122
/*      */     //   301: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   304: pop
/*      */     //   305: aload 11
/*      */     //   307: ldc_w 2124
/*      */     //   310: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   313: pop
/*      */     //   314: aload 11
/*      */     //   316: ldc_w 2126
/*      */     //   319: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   322: pop
/*      */     //   323: aload 11
/*      */     //   325: ldc_w 2128
/*      */     //   328: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   331: pop
/*      */     //   332: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   335: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   338: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   341: astore 12
/*      */     //   343: aload 12
/*      */     //   345: aload 11
/*      */     //   347: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   350: invokeinterface 158 2 0
/*      */     //   355: astore 9
/*      */     //   357: iload_1
/*      */     //   358: bipush 10
/*      */     //   360: if_icmpeq +12 -> 372
/*      */     //   363: aload 9
/*      */     //   365: iconst_1
/*      */     //   366: iload_1
/*      */     //   367: invokeinterface 169 3 0
/*      */     //   372: iload 8
/*      */     //   374: ifeq +65 -> 439
/*      */     //   377: aload 9
/*      */     //   379: iconst_2
/*      */     //   380: aload_2
/*      */     //   381: invokeinterface 164 3 0
/*      */     //   386: aload 9
/*      */     //   388: iconst_3
/*      */     //   389: aload_3
/*      */     //   390: invokeinterface 164 3 0
/*      */     //   395: aload 9
/*      */     //   397: iconst_4
/*      */     //   398: aload 4
/*      */     //   400: invokeinterface 164 3 0
/*      */     //   405: aload 9
/*      */     //   407: iconst_5
/*      */     //   408: ldc_w 2130
/*      */     //   411: invokeinterface 164 3 0
/*      */     //   416: aload 9
/*      */     //   418: bipush 6
/*      */     //   420: ldc_w 1386
/*      */     //   423: invokeinterface 164 3 0
/*      */     //   428: aload 9
/*      */     //   430: bipush 7
/*      */     //   432: aload 7
/*      */     //   434: invokeinterface 164 3 0
/*      */     //   439: aload 9
/*      */     //   441: invokeinterface 200 1 0
/*      */     //   446: astore 10
/*      */     //   448: goto +221 -> 669
/*      */     //   451: new 707	com/claro/transfer/PlanTO
/*      */     //   454: dup
/*      */     //   455: invokespecial 1055	com/claro/transfer/PlanTO:<init>	()V
/*      */     //   458: astore 14
/*      */     //   460: aload 14
/*      */     //   462: aload 10
/*      */     //   464: ldc_w 1675
/*      */     //   467: invokeinterface 212 2 0
/*      */     //   472: invokevirtual 1363	com/claro/transfer/PlanTO:setIdPlanNuevo	(Ljava/lang/String;)V
/*      */     //   475: aload 14
/*      */     //   477: aload 10
/*      */     //   479: ldc_w 1036
/*      */     //   482: invokeinterface 212 2 0
/*      */     //   487: invokevirtual 2132	com/claro/transfer/PlanTO:setDescSegmento	(Ljava/lang/String;)V
/*      */     //   490: aload 14
/*      */     //   492: aload 10
/*      */     //   494: ldc_w 2135
/*      */     //   497: invokeinterface 235 2 0
/*      */     //   502: invokevirtual 1354	com/claro/transfer/PlanTO:setIdGrupoPromocion	(I)V
/*      */     //   505: aload 14
/*      */     //   507: aload 10
/*      */     //   509: ldc_w 1016
/*      */     //   512: invokeinterface 212 2 0
/*      */     //   517: invokevirtual 2137	com/claro/transfer/PlanTO:setTecnologia	(Ljava/lang/String;)V
/*      */     //   520: aload 14
/*      */     //   522: aload 10
/*      */     //   524: ldc_w 2138
/*      */     //   527: invokeinterface 235 2 0
/*      */     //   532: invokevirtual 1357	com/claro/transfer/PlanTO:setAdendumNvo	(I)V
/*      */     //   535: iload 8
/*      */     //   537: ifeq +19 -> 556
/*      */     //   540: aload 14
/*      */     //   542: aload 10
/*      */     //   544: iconst_2
/*      */     //   545: invokeinterface 558 2 0
/*      */     //   550: invokevirtual 2140	com/claro/transfer/PlanTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   553: goto +63 -> 616
/*      */     //   556: aload 14
/*      */     //   558: new 382	java/lang/StringBuilder
/*      */     //   561: dup
/*      */     //   562: aload 10
/*      */     //   564: iconst_1
/*      */     //   565: invokeinterface 558 2 0
/*      */     //   570: invokestatic 1531	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   573: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   576: ldc_w 2141
/*      */     //   579: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   582: aload 10
/*      */     //   584: iconst_2
/*      */     //   585: invokeinterface 558 2 0
/*      */     //   590: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   593: ldc_w 2143
/*      */     //   596: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   599: aload 10
/*      */     //   601: iconst_5
/*      */     //   602: invokeinterface 558 2 0
/*      */     //   607: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   610: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   613: invokevirtual 2140	com/claro/transfer/PlanTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   616: aload 14
/*      */     //   618: aload 10
/*      */     //   620: ldc_w 996
/*      */     //   623: invokeinterface 235 2 0
/*      */     //   628: invokevirtual 2145	com/claro/transfer/PlanTO:setIdRegion	(I)V
/*      */     //   631: aload 14
/*      */     //   633: aload 10
/*      */     //   635: ldc_w 2146
/*      */     //   638: invokeinterface 212 2 0
/*      */     //   643: invokevirtual 1360	com/claro/transfer/PlanTO:setBanRedencionAnct	(Ljava/lang/String;)V
/*      */     //   646: aload 14
/*      */     //   648: aload 10
/*      */     //   650: ldc_w 2148
/*      */     //   653: invokeinterface 212 2 0
/*      */     //   658: invokevirtual 1348	com/claro/transfer/PlanTO:setTipoPromocion	(Ljava/lang/String;)V
/*      */     //   661: aload 13
/*      */     //   663: aload 14
/*      */     //   665: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   668: pop
/*      */     //   669: aload 10
/*      */     //   671: invokeinterface 378 1 0
/*      */     //   676: ifne -225 -> 451
/*      */     //   679: goto +148 -> 827
/*      */     //   682: astore 15
/*      */     //   684: new 85	com/claro/exception/CAException
/*      */     //   687: dup
/*      */     //   688: iconst_m1
/*      */     //   689: new 382	java/lang/StringBuilder
/*      */     //   692: dup
/*      */     //   693: ldc_w 2150
/*      */     //   696: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   699: aload 15
/*      */     //   701: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   704: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   707: ldc_w 394
/*      */     //   710: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   713: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   716: aload 15
/*      */     //   718: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   721: athrow
/*      */     //   722: astore 15
/*      */     //   724: new 85	com/claro/exception/CAException
/*      */     //   727: dup
/*      */     //   728: iconst_m1
/*      */     //   729: new 382	java/lang/StringBuilder
/*      */     //   732: dup
/*      */     //   733: ldc_w 2152
/*      */     //   736: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   739: aload 15
/*      */     //   741: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   744: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   747: ldc_w 394
/*      */     //   750: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   753: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   756: aload 15
/*      */     //   758: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   761: athrow
/*      */     //   762: astore 16
/*      */     //   764: aload 10
/*      */     //   766: ifnull +18 -> 784
/*      */     //   769: aload 10
/*      */     //   771: invokeinterface 403 1 0
/*      */     //   776: aconst_null
/*      */     //   777: astore 10
/*      */     //   779: goto +5 -> 784
/*      */     //   782: astore 17
/*      */     //   784: aload 9
/*      */     //   786: ifnull +18 -> 804
/*      */     //   789: aload 9
/*      */     //   791: invokeinterface 406 1 0
/*      */     //   796: aconst_null
/*      */     //   797: astore 9
/*      */     //   799: goto +5 -> 804
/*      */     //   802: astore 17
/*      */     //   804: aload 12
/*      */     //   806: ifnull +18 -> 824
/*      */     //   809: aload 12
/*      */     //   811: invokeinterface 407 1 0
/*      */     //   816: aconst_null
/*      */     //   817: astore 12
/*      */     //   819: goto +5 -> 824
/*      */     //   822: astore 17
/*      */     //   824: aload 16
/*      */     //   826: athrow
/*      */     //   827: aload 10
/*      */     //   829: ifnull +18 -> 847
/*      */     //   832: aload 10
/*      */     //   834: invokeinterface 403 1 0
/*      */     //   839: aconst_null
/*      */     //   840: astore 10
/*      */     //   842: goto +5 -> 847
/*      */     //   845: astore 17
/*      */     //   847: aload 9
/*      */     //   849: ifnull +18 -> 867
/*      */     //   852: aload 9
/*      */     //   854: invokeinterface 406 1 0
/*      */     //   859: aconst_null
/*      */     //   860: astore 9
/*      */     //   862: goto +5 -> 867
/*      */     //   865: astore 17
/*      */     //   867: aload 12
/*      */     //   869: ifnull +18 -> 887
/*      */     //   872: aload 12
/*      */     //   874: invokeinterface 407 1 0
/*      */     //   879: aconst_null
/*      */     //   880: astore 12
/*      */     //   882: goto +5 -> 887
/*      */     //   885: astore 17
/*      */     //   887: aload 13
/*      */     //   889: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1668	-> byte code offset #0
/*      */     //   Java source line #1669	-> byte code offset #3
/*      */     //   Java source line #1670	-> byte code offset #6
/*      */     //   Java source line #1671	-> byte code offset #15
/*      */     //   Java source line #1672	-> byte code offset #18
/*      */     //   Java source line #1673	-> byte code offset #27
/*      */     //   Java source line #1676	-> byte code offset #30
/*      */     //   Java source line #1677	-> byte code offset #39
/*      */     //   Java source line #1678	-> byte code offset #48
/*      */     //   Java source line #1679	-> byte code offset #69
/*      */     //   Java source line #1680	-> byte code offset #91
/*      */     //   Java source line #1684	-> byte code offset #113
/*      */     //   Java source line #1685	-> byte code offset #122
/*      */     //   Java source line #1686	-> byte code offset #128
/*      */     //   Java source line #1688	-> byte code offset #140
/*      */     //   Java source line #1693	-> byte code offset #149
/*      */     //   Java source line #1697	-> byte code offset #158
/*      */     //   Java source line #1701	-> byte code offset #167
/*      */     //   Java source line #1702	-> byte code offset #172
/*      */     //   Java source line #1703	-> byte code offset #181
/*      */     //   Java source line #1704	-> byte code offset #190
/*      */     //   Java source line #1705	-> byte code offset #199
/*      */     //   Java source line #1706	-> byte code offset #208
/*      */     //   Java source line #1707	-> byte code offset #217
/*      */     //   Java source line #1708	-> byte code offset #226
/*      */     //   Java source line #1709	-> byte code offset #248
/*      */     //   Java source line #1710	-> byte code offset #257
/*      */     //   Java source line #1711	-> byte code offset #266
/*      */     //   Java source line #1712	-> byte code offset #275
/*      */     //   Java source line #1718	-> byte code offset #287
/*      */     //   Java source line #1720	-> byte code offset #296
/*      */     //   Java source line #1721	-> byte code offset #305
/*      */     //   Java source line #1722	-> byte code offset #314
/*      */     //   Java source line #1733	-> byte code offset #323
/*      */     //   Java source line #1736	-> byte code offset #332
/*      */     //   Java source line #1737	-> byte code offset #343
/*      */     //   Java source line #1739	-> byte code offset #357
/*      */     //   Java source line #1740	-> byte code offset #363
/*      */     //   Java source line #1742	-> byte code offset #372
/*      */     //   Java source line #1743	-> byte code offset #377
/*      */     //   Java source line #1744	-> byte code offset #386
/*      */     //   Java source line #1745	-> byte code offset #395
/*      */     //   Java source line #1746	-> byte code offset #405
/*      */     //   Java source line #1747	-> byte code offset #416
/*      */     //   Java source line #1748	-> byte code offset #428
/*      */     //   Java source line #1751	-> byte code offset #439
/*      */     //   Java source line #1753	-> byte code offset #448
/*      */     //   Java source line #1754	-> byte code offset #451
/*      */     //   Java source line #1756	-> byte code offset #460
/*      */     //   Java source line #1757	-> byte code offset #475
/*      */     //   Java source line #1758	-> byte code offset #490
/*      */     //   Java source line #1759	-> byte code offset #505
/*      */     //   Java source line #1760	-> byte code offset #520
/*      */     //   Java source line #1761	-> byte code offset #535
/*      */     //   Java source line #1762	-> byte code offset #540
/*      */     //   Java source line #1764	-> byte code offset #556
/*      */     //   Java source line #1766	-> byte code offset #616
/*      */     //   Java source line #1767	-> byte code offset #631
/*      */     //   Java source line #1768	-> byte code offset #646
/*      */     //   Java source line #1771	-> byte code offset #661
/*      */     //   Java source line #1753	-> byte code offset #669
/*      */     //   Java source line #1773	-> byte code offset #682
/*      */     //   Java source line #1774	-> byte code offset #684
/*      */     //   Java source line #1775	-> byte code offset #722
/*      */     //   Java source line #1776	-> byte code offset #724
/*      */     //   Java source line #1777	-> byte code offset #762
/*      */     //   Java source line #1778	-> byte code offset #764
/*      */     //   Java source line #1779	-> byte code offset #784
/*      */     //   Java source line #1780	-> byte code offset #804
/*      */     //   Java source line #1781	-> byte code offset #824
/*      */     //   Java source line #1778	-> byte code offset #827
/*      */     //   Java source line #1779	-> byte code offset #847
/*      */     //   Java source line #1780	-> byte code offset #867
/*      */     //   Java source line #1782	-> byte code offset #887
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	890	0	this	ConsultasDAO
/*      */     //   0	890	1	region	int
/*      */     //   0	890	2	tecnologia	String
/*      */     //   0	890	3	modalidad	String
/*      */     //   0	890	4	mixto	String
/*      */     //   0	890	5	banSisact	String
/*      */     //   0	890	6	tipoPlan	String
/*      */     //   0	890	7	fzaVenta	String
/*      */     //   0	890	8	esSisact	boolean
/*      */     //   1	860	9	statement	PreparedStatement
/*      */     //   4	837	10	resultSet	ResultSet
/*      */     //   13	333	11	query	StringBuffer
/*      */     //   16	865	12	lConn	Connection
/*      */     //   25	863	13	planes	ArrayList<PlanTO>
/*      */     //   28	636	14	planTO	PlanTO
/*      */     //   682	35	15	e	SQLException
/*      */     //   722	35	15	e	Exception
/*      */     //   762	63	16	localObject	Object
/*      */     //   782	1	17	localException1	Exception
/*      */     //   802	1	17	localException2	Exception
/*      */     //   822	1	17	localException3	Exception
/*      */     //   845	1	17	localException4	Exception
/*      */     //   865	1	17	localException5	Exception
/*      */     //   885	1	17	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   332	679	682	java/sql/SQLException
/*      */     //   332	679	722	java/lang/Exception
/*      */     //   332	762	762	finally
/*      */     //   769	779	782	java/lang/Exception
/*      */     //   789	799	802	java/lang/Exception
/*      */     //   809	819	822	java/lang/Exception
/*      */     //   832	842	845	java/lang/Exception
/*      */     //   852	862	865	java/lang/Exception
/*      */     //   872	882	885	java/lang/Exception
/*      */   }
/*      */   
/*      */   public int consultaRegionLinea(String lTelefono, String cuenta)
/*      */     throws CAException
/*      */   {
/* 1789 */     PreparedStatement preparedStatement = null;
/* 1790 */     ResultSet resultSet = null;
/* 1791 */     Connection connection = null;
/* 1792 */     StringBuffer sQuery = new StringBuffer();
/* 1793 */     String consulta = "";
/*      */     
/*      */     try
/*      */     {
/* 1797 */       if ((lTelefono != null) && (lTelefono.trim().length() > 0)) {
/* 1798 */         consulta = " A.LINEA = ? ";
/*      */       } else {
/* 1800 */         consulta = " A.CUENTA = ? ";
/*      */       }
/*      */       
/* 1803 */       if ((lTelefono != null) || (cuenta != null)) {
/* 1804 */         sQuery.append(" SELECT A.IDREGION ");
/* 1805 */         sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A ");
/* 1806 */         sQuery.append(" WHERE " + consulta);
/* 1807 */         sQuery.append(" GROUP BY A.LINEA, A.CUENTA, A.SECUENCIA, A.IDREGION, A.FECHAADD ");
/* 1808 */         sQuery.append(" ORDER BY A.FECHAADD DESC");
/* 1809 */         connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 1810 */         preparedStatement = connection.prepareStatement(sQuery.toString(), 1004, 1007);
/*      */         
/* 1812 */         if ((lTelefono != null) && (lTelefono.trim().length() > 0)) {
/* 1813 */           preparedStatement.setString(1, lTelefono);
/*      */         } else {
/* 1815 */           preparedStatement.setString(1, cuenta);
/*      */         }
/*      */         
/* 1818 */         resultSet = preparedStatement.executeQuery();
/*      */         
/* 1820 */         if (resultSet.next()) {
/* 1821 */           return resultSet.getInt("IDREGION");
/*      */         }
/* 1823 */         return 0;
/*      */       }
/*      */       
/*      */ 
/* 1827 */       throw new CAException(-1, "Debe especificar el telfono/cuenta a consultar.");
/*      */     } catch (SQLException e) {
/* 1829 */       throw new CAException(-1, "SQLException.consultaRegionLinea[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/* 1831 */       throw new CAException(-1, "ConsultasDAO.consultaRegionLinea[" + e.toString() + "]", e);
/*      */     } finally {
/* 1833 */       if (resultSet != null) try { resultSet.close();resultSet = null; } catch (Exception localException7) {}
/* 1834 */       if (preparedStatement != null) try { preparedStatement.close();preparedStatement = null; } catch (Exception localException8) {}
/* 1835 */       if (connection != null) try { connection.close();connection = null;
/*      */         }
/*      */         catch (Exception localException9) {}
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ArrayList<com.claro.transfer.CatalogoTO> consultaAreasPromocion()
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_1
/*      */     //   2: aconst_null
/*      */     //   3: astore_2
/*      */     //   4: aconst_null
/*      */     //   5: astore_3
/*      */     //   6: new 114	java/lang/StringBuffer
/*      */     //   9: dup
/*      */     //   10: invokespecial 116	java/lang/StringBuffer:<init>	()V
/*      */     //   13: astore 4
/*      */     //   15: aload 4
/*      */     //   17: ldc_w 2174
/*      */     //   20: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   23: pop
/*      */     //   24: aload 4
/*      */     //   26: ldc -127
/*      */     //   28: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   31: aload_0
/*      */     //   32: getfield 67	com/claro/dao/ConsultasDAO:schema_database	Ljava/lang/String;
/*      */     //   35: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   38: ldc_w 2176
/*      */     //   41: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   44: pop
/*      */     //   45: aload 4
/*      */     //   47: ldc_w 2178
/*      */     //   50: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   53: pop
/*      */     //   54: aload 4
/*      */     //   56: ldc_w 2180
/*      */     //   59: invokevirtual 119	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   62: pop
/*      */     //   63: new 145	java/util/ArrayList
/*      */     //   66: dup
/*      */     //   67: iconst_0
/*      */     //   68: invokespecial 2182	java/util/ArrayList:<init>	(I)V
/*      */     //   71: astore 5
/*      */     //   73: aconst_null
/*      */     //   74: astore 6
/*      */     //   76: invokestatic 55	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   79: getstatic 148	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   82: invokevirtual 151	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   85: astore_1
/*      */     //   86: aload_1
/*      */     //   87: aload 4
/*      */     //   89: invokevirtual 155	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   92: invokeinterface 158 2 0
/*      */     //   97: astore_2
/*      */     //   98: aload_2
/*      */     //   99: iconst_1
/*      */     //   100: iconst_1
/*      */     //   101: invokeinterface 169 3 0
/*      */     //   106: aload_2
/*      */     //   107: invokeinterface 200 1 0
/*      */     //   112: astore_3
/*      */     //   113: goto +48 -> 161
/*      */     //   116: new 2184	com/claro/transfer/CatalogoTO
/*      */     //   119: dup
/*      */     //   120: invokespecial 2186	com/claro/transfer/CatalogoTO:<init>	()V
/*      */     //   123: astore 6
/*      */     //   125: aload 6
/*      */     //   127: aload_3
/*      */     //   128: ldc_w 2187
/*      */     //   131: invokeinterface 212 2 0
/*      */     //   136: invokevirtual 2189	com/claro/transfer/CatalogoTO:setIdVariable	(Ljava/lang/String;)V
/*      */     //   139: aload 6
/*      */     //   141: aload_3
/*      */     //   142: ldc_w 313
/*      */     //   145: invokeinterface 212 2 0
/*      */     //   150: invokevirtual 2192	com/claro/transfer/CatalogoTO:setDescripcion	(Ljava/lang/String;)V
/*      */     //   153: aload 5
/*      */     //   155: aload 6
/*      */     //   157: invokevirtual 375	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   160: pop
/*      */     //   161: aload_3
/*      */     //   162: invokeinterface 378 1 0
/*      */     //   167: ifne -51 -> 116
/*      */     //   170: goto +139 -> 309
/*      */     //   173: astore 7
/*      */     //   175: new 85	com/claro/exception/CAException
/*      */     //   178: dup
/*      */     //   179: iconst_m1
/*      */     //   180: new 382	java/lang/StringBuilder
/*      */     //   183: dup
/*      */     //   184: ldc_w 2193
/*      */     //   187: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   190: aload 7
/*      */     //   192: invokevirtual 388	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   195: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   198: ldc_w 394
/*      */     //   201: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   204: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   207: aload 7
/*      */     //   209: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   212: athrow
/*      */     //   213: astore 7
/*      */     //   215: new 85	com/claro/exception/CAException
/*      */     //   218: dup
/*      */     //   219: iconst_m1
/*      */     //   220: new 382	java/lang/StringBuilder
/*      */     //   223: dup
/*      */     //   224: ldc_w 2195
/*      */     //   227: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   230: aload 7
/*      */     //   232: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   235: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   238: ldc_w 394
/*      */     //   241: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   244: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   247: aload 7
/*      */     //   249: invokespecial 397	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   252: athrow
/*      */     //   253: astore 8
/*      */     //   255: aload_3
/*      */     //   256: ifnull +16 -> 272
/*      */     //   259: aload_3
/*      */     //   260: invokeinterface 403 1 0
/*      */     //   265: aconst_null
/*      */     //   266: astore_3
/*      */     //   267: goto +5 -> 272
/*      */     //   270: astore 9
/*      */     //   272: aload_2
/*      */     //   273: ifnull +16 -> 289
/*      */     //   276: aload_2
/*      */     //   277: invokeinterface 406 1 0
/*      */     //   282: aconst_null
/*      */     //   283: astore_2
/*      */     //   284: goto +5 -> 289
/*      */     //   287: astore 9
/*      */     //   289: aload_1
/*      */     //   290: ifnull +16 -> 306
/*      */     //   293: aload_1
/*      */     //   294: invokeinterface 407 1 0
/*      */     //   299: aconst_null
/*      */     //   300: astore_1
/*      */     //   301: goto +5 -> 306
/*      */     //   304: astore 9
/*      */     //   306: aload 8
/*      */     //   308: athrow
/*      */     //   309: aload_3
/*      */     //   310: ifnull +16 -> 326
/*      */     //   313: aload_3
/*      */     //   314: invokeinterface 403 1 0
/*      */     //   319: aconst_null
/*      */     //   320: astore_3
/*      */     //   321: goto +5 -> 326
/*      */     //   324: astore 9
/*      */     //   326: aload_2
/*      */     //   327: ifnull +16 -> 343
/*      */     //   330: aload_2
/*      */     //   331: invokeinterface 406 1 0
/*      */     //   336: aconst_null
/*      */     //   337: astore_2
/*      */     //   338: goto +5 -> 343
/*      */     //   341: astore 9
/*      */     //   343: aload_1
/*      */     //   344: ifnull +16 -> 360
/*      */     //   347: aload_1
/*      */     //   348: invokeinterface 407 1 0
/*      */     //   353: aconst_null
/*      */     //   354: astore_1
/*      */     //   355: goto +5 -> 360
/*      */     //   358: astore 9
/*      */     //   360: aload 5
/*      */     //   362: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1841	-> byte code offset #0
/*      */     //   Java source line #1842	-> byte code offset #2
/*      */     //   Java source line #1843	-> byte code offset #4
/*      */     //   Java source line #1845	-> byte code offset #6
/*      */     //   Java source line #1846	-> byte code offset #15
/*      */     //   Java source line #1847	-> byte code offset #24
/*      */     //   Java source line #1848	-> byte code offset #45
/*      */     //   Java source line #1849	-> byte code offset #54
/*      */     //   Java source line #1851	-> byte code offset #63
/*      */     //   Java source line #1852	-> byte code offset #73
/*      */     //   Java source line #1854	-> byte code offset #76
/*      */     //   Java source line #1855	-> byte code offset #86
/*      */     //   Java source line #1856	-> byte code offset #98
/*      */     //   Java source line #1858	-> byte code offset #106
/*      */     //   Java source line #1859	-> byte code offset #113
/*      */     //   Java source line #1860	-> byte code offset #116
/*      */     //   Java source line #1862	-> byte code offset #125
/*      */     //   Java source line #1863	-> byte code offset #139
/*      */     //   Java source line #1865	-> byte code offset #153
/*      */     //   Java source line #1859	-> byte code offset #161
/*      */     //   Java source line #1867	-> byte code offset #173
/*      */     //   Java source line #1868	-> byte code offset #175
/*      */     //   Java source line #1869	-> byte code offset #213
/*      */     //   Java source line #1870	-> byte code offset #215
/*      */     //   Java source line #1871	-> byte code offset #253
/*      */     //   Java source line #1872	-> byte code offset #255
/*      */     //   Java source line #1873	-> byte code offset #272
/*      */     //   Java source line #1874	-> byte code offset #289
/*      */     //   Java source line #1875	-> byte code offset #306
/*      */     //   Java source line #1872	-> byte code offset #309
/*      */     //   Java source line #1873	-> byte code offset #326
/*      */     //   Java source line #1874	-> byte code offset #343
/*      */     //   Java source line #1876	-> byte code offset #360
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	363	0	this	ConsultasDAO
/*      */     //   1	354	1	connection	Connection
/*      */     //   3	335	2	preparedStatement	PreparedStatement
/*      */     //   5	316	3	resultSet	ResultSet
/*      */     //   13	75	4	query	StringBuffer
/*      */     //   71	290	5	areasPromos	ArrayList<com.claro.transfer.CatalogoTO>
/*      */     //   74	82	6	areaPromoTO	com.claro.transfer.CatalogoTO
/*      */     //   173	35	7	e	SQLException
/*      */     //   213	35	7	e	Exception
/*      */     //   253	54	8	localObject	Object
/*      */     //   270	1	9	localException1	Exception
/*      */     //   287	1	9	localException2	Exception
/*      */     //   304	1	9	localException3	Exception
/*      */     //   324	1	9	localException4	Exception
/*      */     //   341	1	9	localException5	Exception
/*      */     //   358	1	9	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   76	170	173	java/sql/SQLException
/*      */     //   76	170	213	java/lang/Exception
/*      */     //   76	253	253	finally
/*      */     //   259	267	270	java/lang/Exception
/*      */     //   276	284	287	java/lang/Exception
/*      */     //   293	301	304	java/lang/Exception
/*      */     //   313	321	324	java/lang/Exception
/*      */     //   330	338	341	java/lang/Exception
/*      */     //   347	355	358	java/lang/Exception
/*      */   }
/*      */   
/*      */   public void cancelaReservacionSisact(String folio, String cuenta, int secuencia, String fzaVenta, Connection conexion)
/*      */     throws CAException
/*      */   {
/* 1881 */     PreparedStatement pStmt1 = null;
/* 1882 */     PreparedStatement pStmt2 = null;
/* 1883 */     ResultSet rset1 = null;
/* 1884 */     StringBuffer query = null;
/*      */     
/* 1886 */     MensajeTO mensajeTO = null;
/*      */     
/* 1888 */     mensajeTO = this.puntosDAO.actualizaReservacion(conexion, System.currentTimeMillis(), "SISACT", "C", null, folio, false);
/* 1889 */     if (mensajeTO.getIdMensaje() != 0) {
/* 1890 */       throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + mensajeTO.getMensaje() + "]");
/*      */     }
/* 1892 */     System.out.println("Se actualiza el Status en PTO_TBLRESERVACIONES");
/*      */     try
/*      */     {
/* 1895 */       query = new StringBuffer();
/*      */       
/* 1897 */       query = new StringBuffer();
/* 1898 */       query.append("SELECT Folio ");
/* 1899 */       query.append("FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES ");
/* 1900 */       query.append("WHERE Cuenta = ? ");
/* 1901 */       query.append("AND Status IN ('A','P') ");
/* 1902 */       query.append("AND Folio != ? ");
/*      */       
/* 1904 */       pStmt1 = conexion.prepareStatement(query.toString());
/* 1905 */       pStmt1.setString(1, cuenta);
/* 1906 */       pStmt1.setString(2, folio);
/*      */       
/* 1908 */       rset1 = pStmt1.executeQuery();
/*      */       
/* 1910 */       if (rset1.next()) {
/* 1911 */         System.out.println("No se puede liberar status de puntos, existe otra reservacion: " + 
/* 1912 */           Long.toHexString(Long.parseLong(rset1.getString(1))).toUpperCase());
/*      */       } else {
/* 1914 */         System.out.println("Se libera el status de puntos por vigencia, no existe otra reservacion.");
/*      */         
/*      */ 
/* 1917 */         mensajeTO = this.puntosDAO.actualizaLinea(conexion, cuenta, secuencia, "0");
/* 1918 */         if (mensajeTO.getIdMensaje() != 0) {
/* 1919 */           throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + mensajeTO.getMensaje() + "]");
/*      */         }
/*      */       }
/* 1922 */       throw new CAException(-1, "La fecha de vigencia de la reservacion ha expirado.");
/* 1923 */     } catch (SQLException e) { e = e;
/* 1924 */       throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + e.toString() + "]");
/* 1925 */     } catch (Exception e) { e = e;
/* 1926 */       throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + e.toString() + "]");
/* 1927 */     } finally { localObject = finally;
/*      */       try {
/* 1929 */         if (rset1 != null) { rset1.close();rset1 = null; }
/* 1930 */         if (pStmt1 != null) { pStmt1.close();pStmt1 = null; }
/* 1931 */         if (pStmt2 != null) { pStmt2.close();pStmt2 = null;
/*      */         } } catch (Exception localException1) {}
/* 1933 */       throw ((Throwable)localObject);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public void actualizaRedencion(String folio, String claveSisact, TelefonoServiceTO telefonoTO, ReservacionTO reservacionTO, ArrayList<int[]> puntosRedecionSisact, Connection conexion)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 7
/*      */     //   3: aconst_null
/*      */     //   4: astore 8
/*      */     //   6: aconst_null
/*      */     //   7: astore 9
/*      */     //   9: aload 4
/*      */     //   11: invokevirtual 2257	com/claro/transfer/ReservacionTO:getTipoRedencion	()Ljava/lang/String;
/*      */     //   14: astore 10
/*      */     //   16: invokestatic 605	java/lang/System:currentTimeMillis	()J
/*      */     //   19: lstore 11
/*      */     //   21: aload 5
/*      */     //   23: iconst_0
/*      */     //   24: invokevirtual 2260	java/util/ArrayList:get	(I)Ljava/lang/Object;
/*      */     //   27: checkcast 2264	[I
/*      */     //   30: astore 13
/*      */     //   32: aload 6
/*      */     //   34: invokeinterface 2266 1 0
/*      */     //   39: astore 8
/*      */     //   41: new 630	com/claro/transfer/ParametrosTO
/*      */     //   44: dup
/*      */     //   45: invokespecial 1594	com/claro/transfer/ParametrosTO:<init>	()V
/*      */     //   48: astore 14
/*      */     //   50: aload 14
/*      */     //   52: aload_3
/*      */     //   53: invokevirtual 2269	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   56: invokevirtual 2272	com/claro/transfer/ParametrosTO:setCuenta	(Ljava/lang/String;)V
/*      */     //   59: aload 14
/*      */     //   61: aload_3
/*      */     //   62: invokevirtual 2273	com/claro/transfer/service/TelefonoServiceTO:getTelefono	()Ljava/lang/String;
/*      */     //   65: invokevirtual 1598	com/claro/transfer/ParametrosTO:setTelefono	(Ljava/lang/String;)V
/*      */     //   68: aload 14
/*      */     //   70: aload_3
/*      */     //   71: invokevirtual 2274	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   74: invokevirtual 2277	com/claro/transfer/ParametrosTO:setSecuencia	(I)V
/*      */     //   77: aload 14
/*      */     //   79: aload 4
/*      */     //   81: invokevirtual 2278	com/claro/transfer/ReservacionTO:getPlanNuevo	()Ljava/lang/String;
/*      */     //   84: invokevirtual 1221	com/claro/transfer/ParametrosTO:setPlanNvo	(Ljava/lang/String;)V
/*      */     //   87: aload 14
/*      */     //   89: aload_3
/*      */     //   90: invokevirtual 2281	com/claro/transfer/service/TelefonoServiceTO:getPlanM2K	()Ljava/lang/String;
/*      */     //   93: invokevirtual 2282	com/claro/transfer/ParametrosTO:setPlanAnt	(Ljava/lang/String;)V
/*      */     //   96: new 230	com/claro/transfer/PuntosRedimidosTO
/*      */     //   99: dup
/*      */     //   100: invokespecial 232	com/claro/transfer/PuntosRedimidosTO:<init>	()V
/*      */     //   103: astore 15
/*      */     //   105: aload 15
/*      */     //   107: aload_3
/*      */     //   108: invokevirtual 2285	com/claro/transfer/service/TelefonoServiceTO:getPtsExcedentes	()I
/*      */     //   111: aload 4
/*      */     //   113: invokevirtual 2288	com/claro/transfer/ReservacionTO:getSobrantesBono	()I
/*      */     //   116: iadd
/*      */     //   117: invokevirtual 2291	com/claro/transfer/PuntosRedimidosTO:setPtsExcedentes	(I)V
/*      */     //   120: aload 15
/*      */     //   122: aload 4
/*      */     //   124: invokevirtual 2292	com/claro/transfer/ReservacionTO:getProductosTO	()Lcom/claro/transfer/ProductosTO;
/*      */     //   127: invokevirtual 2296	com/claro/transfer/ProductosTO:getValorPuntos	()I
/*      */     //   130: invokevirtual 1959	com/claro/transfer/PuntosRedimidosTO:setPtsRedimidos	(I)V
/*      */     //   133: aload 15
/*      */     //   135: aload 13
/*      */     //   137: bipush 6
/*      */     //   139: iaload
/*      */     //   140: invokevirtual 1932	com/claro/transfer/PuntosRedimidosTO:setPtsRentaRedimidos	(I)V
/*      */     //   143: aload 15
/*      */     //   145: aload 13
/*      */     //   147: iconst_5
/*      */     //   148: iaload
/*      */     //   149: invokevirtual 1935	com/claro/transfer/PuntosRedimidosTO:setPtsExcedentesRedimidos	(I)V
/*      */     //   152: aload_3
/*      */     //   153: invokevirtual 2299	com/claro/transfer/service/TelefonoServiceTO:getFecVencer2	()Ljava/lang/String;
/*      */     //   156: ifnull +49 -> 205
/*      */     //   159: aload_3
/*      */     //   160: invokevirtual 2299	com/claro/transfer/service/TelefonoServiceTO:getFecVencer2	()Ljava/lang/String;
/*      */     //   163: ldc 89
/*      */     //   165: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   168: ifne +37 -> 205
/*      */     //   171: aload 15
/*      */     //   173: new 191	java/sql/Date
/*      */     //   176: dup
/*      */     //   177: getstatic 2302	com/claro/util/Utils:DATEFORMATdd_MM_YYYY	Ljava/text/SimpleDateFormat;
/*      */     //   180: aload_3
/*      */     //   181: invokevirtual 2299	com/claro/transfer/service/TelefonoServiceTO:getFecVencer2	()Ljava/lang/String;
/*      */     //   184: invokevirtual 179	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
/*      */     //   187: invokevirtual 185	java/util/Date:getTime	()J
/*      */     //   190: invokespecial 193	java/sql/Date:<init>	(J)V
/*      */     //   193: invokevirtual 2303	com/claro/transfer/PuntosRedimidosTO:setFecVencer2Tmp	(Ljava/sql/Date;)V
/*      */     //   196: aload 15
/*      */     //   198: aload 13
/*      */     //   200: iconst_2
/*      */     //   201: iaload
/*      */     //   202: invokevirtual 1939	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer2Redimidos	(I)V
/*      */     //   205: aload_3
/*      */     //   206: invokevirtual 2306	com/claro/transfer/service/TelefonoServiceTO:getFecVencer1	()Ljava/lang/String;
/*      */     //   209: ifnull +49 -> 258
/*      */     //   212: aload_3
/*      */     //   213: invokevirtual 2306	com/claro/transfer/service/TelefonoServiceTO:getFecVencer1	()Ljava/lang/String;
/*      */     //   216: ldc 89
/*      */     //   218: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   221: ifne +37 -> 258
/*      */     //   224: aload 15
/*      */     //   226: new 191	java/sql/Date
/*      */     //   229: dup
/*      */     //   230: getstatic 2302	com/claro/util/Utils:DATEFORMATdd_MM_YYYY	Ljava/text/SimpleDateFormat;
/*      */     //   233: aload_3
/*      */     //   234: invokevirtual 2306	com/claro/transfer/service/TelefonoServiceTO:getFecVencer1	()Ljava/lang/String;
/*      */     //   237: invokevirtual 179	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
/*      */     //   240: invokevirtual 185	java/util/Date:getTime	()J
/*      */     //   243: invokespecial 193	java/sql/Date:<init>	(J)V
/*      */     //   246: invokevirtual 2309	com/claro/transfer/PuntosRedimidosTO:setFecVencer1Tmp	(Ljava/sql/Date;)V
/*      */     //   249: aload 15
/*      */     //   251: aload 13
/*      */     //   253: iconst_1
/*      */     //   254: iaload
/*      */     //   255: invokevirtual 1944	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencer1Redimidos	(I)V
/*      */     //   258: aload_3
/*      */     //   259: invokevirtual 2312	com/claro/transfer/service/TelefonoServiceTO:getFecVencer	()Ljava/lang/String;
/*      */     //   262: ifnull +49 -> 311
/*      */     //   265: aload_3
/*      */     //   266: invokevirtual 2312	com/claro/transfer/service/TelefonoServiceTO:getFecVencer	()Ljava/lang/String;
/*      */     //   269: ldc 89
/*      */     //   271: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   274: ifne +37 -> 311
/*      */     //   277: aload 15
/*      */     //   279: new 191	java/sql/Date
/*      */     //   282: dup
/*      */     //   283: getstatic 2302	com/claro/util/Utils:DATEFORMATdd_MM_YYYY	Ljava/text/SimpleDateFormat;
/*      */     //   286: aload_3
/*      */     //   287: invokevirtual 2312	com/claro/transfer/service/TelefonoServiceTO:getFecVencer	()Ljava/lang/String;
/*      */     //   290: invokevirtual 179	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
/*      */     //   293: invokevirtual 185	java/util/Date:getTime	()J
/*      */     //   296: invokespecial 193	java/sql/Date:<init>	(J)V
/*      */     //   299: invokevirtual 2315	com/claro/transfer/PuntosRedimidosTO:setFecVencerTmp	(Ljava/sql/Date;)V
/*      */     //   302: aload 15
/*      */     //   304: aload 13
/*      */     //   306: iconst_0
/*      */     //   307: iaload
/*      */     //   308: invokevirtual 1949	com/claro/transfer/PuntosRedimidosTO:setPtsPorVencerRedimidos	(I)V
/*      */     //   311: aload 15
/*      */     //   313: aload 13
/*      */     //   315: iconst_3
/*      */     //   316: iaload
/*      */     //   317: invokevirtual 1953	com/claro/transfer/PuntosRedimidosTO:setPtsPromocionRedimidos	(I)V
/*      */     //   320: aload 15
/*      */     //   322: aload 13
/*      */     //   324: iconst_4
/*      */     //   325: iaload
/*      */     //   326: invokevirtual 1956	com/claro/transfer/PuntosRedimidosTO:setPtsPorAntiguedadRedimidos	(I)V
/*      */     //   329: aload 4
/*      */     //   331: invokevirtual 2292	com/claro/transfer/ReservacionTO:getProductosTO	()Lcom/claro/transfer/ProductosTO;
/*      */     //   334: aload 4
/*      */     //   336: invokevirtual 2292	com/claro/transfer/ReservacionTO:getProductosTO	()Lcom/claro/transfer/ProductosTO;
/*      */     //   339: invokevirtual 2296	com/claro/transfer/ProductosTO:getValorPuntos	()I
/*      */     //   342: invokevirtual 2318	com/claro/transfer/ProductosTO:setValorPuntosTmp	(I)V
/*      */     //   345: aload 15
/*      */     //   347: aload_3
/*      */     //   348: invokevirtual 2321	com/claro/transfer/service/TelefonoServiceTO:getBonoEquipo	()I
/*      */     //   351: invokevirtual 2324	com/claro/transfer/PuntosRedimidosTO:setBonoEquipo	(I)V
/*      */     //   354: aload 4
/*      */     //   356: aload 15
/*      */     //   358: invokevirtual 2325	com/claro/transfer/ReservacionTO:setPuntosRedimidosTO	(Lcom/claro/transfer/PuntosRedimidosTO;)V
/*      */     //   361: aload_0
/*      */     //   362: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   365: aload 6
/*      */     //   367: aload 4
/*      */     //   369: aload 14
/*      */     //   371: lload 11
/*      */     //   373: invokevirtual 2326	com/claro/dao/PuntosDAO:insertaRedencion	(Ljava/sql/Connection;Lcom/claro/transfer/RedencionTO;Lcom/claro/transfer/ParametrosTO;J)Lcom/claro/transfer/MensajeTO;
/*      */     //   376: astore 16
/*      */     //   378: aload 16
/*      */     //   380: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   383: ifeq +39 -> 422
/*      */     //   386: new 85	com/claro/exception/CAException
/*      */     //   389: dup
/*      */     //   390: iconst_m1
/*      */     //   391: new 382	java/lang/StringBuilder
/*      */     //   394: dup
/*      */     //   395: ldc_w 2330
/*      */     //   398: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   401: aload 16
/*      */     //   403: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   406: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   409: ldc_w 394
/*      */     //   412: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   415: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   418: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   421: athrow
/*      */     //   422: aload 4
/*      */     //   424: invokevirtual 2292	com/claro/transfer/ReservacionTO:getProductosTO	()Lcom/claro/transfer/ProductosTO;
/*      */     //   427: invokevirtual 2332	com/claro/transfer/ProductosTO:getValorPuntosTmp	()I
/*      */     //   430: iconst_m1
/*      */     //   431: imul
/*      */     //   432: istore 17
/*      */     //   434: aload_0
/*      */     //   435: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   438: aload 6
/*      */     //   440: lload 11
/*      */     //   442: ldc_w 2335
/*      */     //   445: iconst_5
/*      */     //   446: iload 17
/*      */     //   448: aconst_null
/*      */     //   449: aload_3
/*      */     //   450: invokevirtual 2269	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   453: aload_3
/*      */     //   454: invokevirtual 2274	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   457: aload_3
/*      */     //   458: invokevirtual 2273	com/claro/transfer/service/TelefonoServiceTO:getTelefono	()Ljava/lang/String;
/*      */     //   461: aload_2
/*      */     //   462: invokevirtual 2337	com/claro/dao/PuntosDAO:insertaDetalle	(Ljava/sql/Connection;JLjava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)Lcom/claro/transfer/MensajeTO;
/*      */     //   465: astore 16
/*      */     //   467: aload 16
/*      */     //   469: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   472: ifeq +39 -> 511
/*      */     //   475: new 85	com/claro/exception/CAException
/*      */     //   478: dup
/*      */     //   479: iconst_m1
/*      */     //   480: new 382	java/lang/StringBuilder
/*      */     //   483: dup
/*      */     //   484: ldc_w 2330
/*      */     //   487: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   490: aload 16
/*      */     //   492: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   495: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   498: ldc_w 394
/*      */     //   501: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   504: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   507: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   510: athrow
/*      */     //   511: aload 4
/*      */     //   513: invokevirtual 2288	com/claro/transfer/ReservacionTO:getSobrantesBono	()I
/*      */     //   516: ifeq +84 -> 600
/*      */     //   519: aload_0
/*      */     //   520: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   523: aload 6
/*      */     //   525: lload 11
/*      */     //   527: ldc_w 2341
/*      */     //   530: bipush 52
/*      */     //   532: aload 4
/*      */     //   534: invokevirtual 2288	com/claro/transfer/ReservacionTO:getSobrantesBono	()I
/*      */     //   537: aconst_null
/*      */     //   538: aload_3
/*      */     //   539: invokevirtual 2269	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   542: aload_3
/*      */     //   543: invokevirtual 2274	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   546: aload_3
/*      */     //   547: invokevirtual 2273	com/claro/transfer/service/TelefonoServiceTO:getTelefono	()Ljava/lang/String;
/*      */     //   550: aload_2
/*      */     //   551: invokevirtual 2337	com/claro/dao/PuntosDAO:insertaDetalle	(Ljava/sql/Connection;JLjava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)Lcom/claro/transfer/MensajeTO;
/*      */     //   554: astore 16
/*      */     //   556: aload 16
/*      */     //   558: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   561: ifeq +39 -> 600
/*      */     //   564: new 85	com/claro/exception/CAException
/*      */     //   567: dup
/*      */     //   568: iconst_m1
/*      */     //   569: new 382	java/lang/StringBuilder
/*      */     //   572: dup
/*      */     //   573: ldc_w 2330
/*      */     //   576: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   579: aload 16
/*      */     //   581: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   584: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   587: ldc_w 394
/*      */     //   590: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   593: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   596: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   599: athrow
/*      */     //   600: aload_0
/*      */     //   601: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   604: aload 15
/*      */     //   606: aload 6
/*      */     //   608: aload 10
/*      */     //   610: aload_3
/*      */     //   611: invokevirtual 2269	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   614: aload_3
/*      */     //   615: invokevirtual 2274	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   618: invokestatic 2343	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
/*      */     //   621: invokevirtual 2346	java/lang/Integer:intValue	()I
/*      */     //   624: iconst_1
/*      */     //   625: invokevirtual 2349	com/claro/dao/PuntosDAO:actualizaPuntos	(Lcom/claro/transfer/PuntosRedimidosTO;Ljava/sql/Connection;Ljava/lang/String;Ljava/lang/String;IZ)Lcom/claro/transfer/MensajeTO;
/*      */     //   628: astore 16
/*      */     //   630: aload 16
/*      */     //   632: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   635: ifeq +39 -> 674
/*      */     //   638: new 85	com/claro/exception/CAException
/*      */     //   641: dup
/*      */     //   642: iconst_m1
/*      */     //   643: new 382	java/lang/StringBuilder
/*      */     //   646: dup
/*      */     //   647: ldc_w 2330
/*      */     //   650: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   653: aload 16
/*      */     //   655: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   658: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   661: ldc_w 394
/*      */     //   664: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   667: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   670: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   673: athrow
/*      */     //   674: aload_0
/*      */     //   675: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   678: aload 6
/*      */     //   680: lload 11
/*      */     //   682: aload_2
/*      */     //   683: ldc_w 788
/*      */     //   686: aconst_null
/*      */     //   687: aload_1
/*      */     //   688: iconst_0
/*      */     //   689: invokevirtual 2205	com/claro/dao/PuntosDAO:actualizaReservacion	(Ljava/sql/Connection;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/claro/transfer/MensajeTO;
/*      */     //   692: astore 16
/*      */     //   694: aload 16
/*      */     //   696: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   699: ifeq +39 -> 738
/*      */     //   702: new 85	com/claro/exception/CAException
/*      */     //   705: dup
/*      */     //   706: iconst_m1
/*      */     //   707: new 382	java/lang/StringBuilder
/*      */     //   710: dup
/*      */     //   711: ldc_w 2330
/*      */     //   714: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   717: aload 16
/*      */     //   719: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   722: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   725: ldc_w 394
/*      */     //   728: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   731: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   734: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   737: athrow
/*      */     //   738: aload_0
/*      */     //   739: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   742: aload 6
/*      */     //   744: aload_3
/*      */     //   745: invokevirtual 2269	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   748: aload_3
/*      */     //   749: invokevirtual 2274	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   752: ldc_w 691
/*      */     //   755: invokevirtual 2242	com/claro/dao/PuntosDAO:actualizaLinea	(Ljava/sql/Connection;Ljava/lang/String;ILjava/lang/String;)Lcom/claro/transfer/MensajeTO;
/*      */     //   758: astore 16
/*      */     //   760: aload 16
/*      */     //   762: invokevirtual 2209	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   765: ifeq +39 -> 804
/*      */     //   768: new 85	com/claro/exception/CAException
/*      */     //   771: dup
/*      */     //   772: iconst_m1
/*      */     //   773: new 382	java/lang/StringBuilder
/*      */     //   776: dup
/*      */     //   777: ldc_w 2330
/*      */     //   780: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   783: aload 16
/*      */     //   785: invokevirtual 2214	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   788: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   791: ldc_w 394
/*      */     //   794: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   797: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   800: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   803: athrow
/*      */     //   804: aload_0
/*      */     //   805: getfield 48	com/claro/dao/ConsultasDAO:puntosDAO	Lcom/claro/dao/PuntosDAO;
/*      */     //   808: aload 4
/*      */     //   810: aload 14
/*      */     //   812: aload 6
/*      */     //   814: lload 11
/*      */     //   816: ldc_w 2353
/*      */     //   819: invokevirtual 2355	com/claro/dao/PuntosDAO:insertaConstancia	(Lcom/claro/transfer/RedencionTO;Lcom/claro/transfer/ParametrosTO;Ljava/sql/Connection;JLjava/lang/String;)Lcom/claro/transfer/MensajeTO;
/*      */     //   822: pop
/*      */     //   823: goto +106 -> 929
/*      */     //   826: astore 14
/*      */     //   828: new 85	com/claro/exception/CAException
/*      */     //   831: dup
/*      */     //   832: iconst_m1
/*      */     //   833: new 382	java/lang/StringBuilder
/*      */     //   836: dup
/*      */     //   837: ldc_w 2330
/*      */     //   840: invokespecial 386	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   843: aload 14
/*      */     //   845: invokevirtual 402	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   848: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   851: ldc_w 394
/*      */     //   854: invokevirtual 391	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   857: invokevirtual 396	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   860: invokespecial 101	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   863: athrow
/*      */     //   864: astore 18
/*      */     //   866: aload 9
/*      */     //   868: ifnull +18 -> 886
/*      */     //   871: aload 9
/*      */     //   873: invokeinterface 403 1 0
/*      */     //   878: aconst_null
/*      */     //   879: astore 9
/*      */     //   881: goto +5 -> 886
/*      */     //   884: astore 19
/*      */     //   886: aload 8
/*      */     //   888: ifnull +18 -> 906
/*      */     //   891: aload 8
/*      */     //   893: invokeinterface 1091 1 0
/*      */     //   898: aconst_null
/*      */     //   899: astore 8
/*      */     //   901: goto +5 -> 906
/*      */     //   904: astore 19
/*      */     //   906: aload 7
/*      */     //   908: ifnull +18 -> 926
/*      */     //   911: aload 7
/*      */     //   913: invokeinterface 406 1 0
/*      */     //   918: aconst_null
/*      */     //   919: astore 7
/*      */     //   921: goto +5 -> 926
/*      */     //   924: astore 19
/*      */     //   926: aload 18
/*      */     //   928: athrow
/*      */     //   929: aload 9
/*      */     //   931: ifnull +18 -> 949
/*      */     //   934: aload 9
/*      */     //   936: invokeinterface 403 1 0
/*      */     //   941: aconst_null
/*      */     //   942: astore 9
/*      */     //   944: goto +5 -> 949
/*      */     //   947: astore 19
/*      */     //   949: aload 8
/*      */     //   951: ifnull +18 -> 969
/*      */     //   954: aload 8
/*      */     //   956: invokeinterface 1091 1 0
/*      */     //   961: aconst_null
/*      */     //   962: astore 8
/*      */     //   964: goto +5 -> 969
/*      */     //   967: astore 19
/*      */     //   969: aload 7
/*      */     //   971: ifnull +18 -> 989
/*      */     //   974: aload 7
/*      */     //   976: invokeinterface 406 1 0
/*      */     //   981: aconst_null
/*      */     //   982: astore 7
/*      */     //   984: goto +5 -> 989
/*      */     //   987: astore 19
/*      */     //   989: return
/*      */     // Line number table:
/*      */     //   Java source line #1944	-> byte code offset #0
/*      */     //   Java source line #1945	-> byte code offset #3
/*      */     //   Java source line #1946	-> byte code offset #6
/*      */     //   Java source line #1948	-> byte code offset #9
/*      */     //   Java source line #1949	-> byte code offset #16
/*      */     //   Java source line #1950	-> byte code offset #21
/*      */     //   Java source line #1953	-> byte code offset #32
/*      */     //   Java source line #1955	-> byte code offset #41
/*      */     //   Java source line #1956	-> byte code offset #50
/*      */     //   Java source line #1957	-> byte code offset #59
/*      */     //   Java source line #1958	-> byte code offset #68
/*      */     //   Java source line #1959	-> byte code offset #77
/*      */     //   Java source line #1960	-> byte code offset #87
/*      */     //   Java source line #1962	-> byte code offset #96
/*      */     //   Java source line #1965	-> byte code offset #105
/*      */     //   Java source line #1968	-> byte code offset #120
/*      */     //   Java source line #1976	-> byte code offset #133
/*      */     //   Java source line #1977	-> byte code offset #143
/*      */     //   Java source line #1979	-> byte code offset #152
/*      */     //   Java source line #1980	-> byte code offset #171
/*      */     //   Java source line #1981	-> byte code offset #196
/*      */     //   Java source line #1984	-> byte code offset #205
/*      */     //   Java source line #1985	-> byte code offset #224
/*      */     //   Java source line #1986	-> byte code offset #249
/*      */     //   Java source line #1989	-> byte code offset #258
/*      */     //   Java source line #1990	-> byte code offset #277
/*      */     //   Java source line #1991	-> byte code offset #302
/*      */     //   Java source line #1994	-> byte code offset #311
/*      */     //   Java source line #1995	-> byte code offset #320
/*      */     //   Java source line #1997	-> byte code offset #329
/*      */     //   Java source line #1999	-> byte code offset #345
/*      */     //   Java source line #2001	-> byte code offset #354
/*      */     //   Java source line #2003	-> byte code offset #361
/*      */     //   Java source line #2004	-> byte code offset #378
/*      */     //   Java source line #2005	-> byte code offset #386
/*      */     //   Java source line #2008	-> byte code offset #422
/*      */     //   Java source line #2010	-> byte code offset #434
/*      */     //   Java source line #2011	-> byte code offset #467
/*      */     //   Java source line #2012	-> byte code offset #475
/*      */     //   Java source line #2014	-> byte code offset #511
/*      */     //   Java source line #2015	-> byte code offset #519
/*      */     //   Java source line #2016	-> byte code offset #556
/*      */     //   Java source line #2017	-> byte code offset #564
/*      */     //   Java source line #2023	-> byte code offset #600
/*      */     //   Java source line #2024	-> byte code offset #630
/*      */     //   Java source line #2025	-> byte code offset #638
/*      */     //   Java source line #2028	-> byte code offset #674
/*      */     //   Java source line #2029	-> byte code offset #694
/*      */     //   Java source line #2030	-> byte code offset #702
/*      */     //   Java source line #2033	-> byte code offset #738
/*      */     //   Java source line #2034	-> byte code offset #760
/*      */     //   Java source line #2035	-> byte code offset #768
/*      */     //   Java source line #2038	-> byte code offset #804
/*      */     //   Java source line #2039	-> byte code offset #826
/*      */     //   Java source line #2040	-> byte code offset #828
/*      */     //   Java source line #2041	-> byte code offset #864
/*      */     //   Java source line #2042	-> byte code offset #866
/*      */     //   Java source line #2043	-> byte code offset #886
/*      */     //   Java source line #2044	-> byte code offset #906
/*      */     //   Java source line #2045	-> byte code offset #926
/*      */     //   Java source line #2042	-> byte code offset #929
/*      */     //   Java source line #2043	-> byte code offset #949
/*      */     //   Java source line #2044	-> byte code offset #969
/*      */     //   Java source line #2046	-> byte code offset #989
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	990	0	this	ConsultasDAO
/*      */     //   0	990	1	folio	String
/*      */     //   0	990	2	claveSisact	String
/*      */     //   0	990	3	telefonoTO	TelefonoServiceTO
/*      */     //   0	990	4	reservacionTO	ReservacionTO
/*      */     //   0	990	5	puntosRedecionSisact	ArrayList<int[]>
/*      */     //   0	990	6	conexion	Connection
/*      */     //   1	982	7	pStmt	PreparedStatement
/*      */     //   4	959	8	stmt	Statement
/*      */     //   7	936	9	rset	ResultSet
/*      */     //   14	595	10	gbTipoRed	String
/*      */     //   19	796	11	fechaTransaccion	long
/*      */     //   30	293	13	puntosAConsumir	int[]
/*      */     //   48	763	14	parametrosTO	ParametrosTO
/*      */     //   826	18	14	e	Exception
/*      */     //   103	502	15	puntosRedimidosTO	com.claro.transfer.PuntosRedimidosTO
/*      */     //   376	408	16	mensajeTO	MensajeTO
/*      */     //   432	15	17	totAjustes	int
/*      */     //   864	63	18	localObject	Object
/*      */     //   884	1	19	localException1	Exception
/*      */     //   904	1	19	localException2	Exception
/*      */     //   924	1	19	localException3	Exception
/*      */     //   947	1	19	localException4	Exception
/*      */     //   967	1	19	localException5	Exception
/*      */     //   987	1	19	localException6	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   32	823	826	java/lang/Exception
/*      */     //   32	864	864	finally
/*      */     //   871	881	884	java/lang/Exception
/*      */     //   891	901	904	java/lang/Exception
/*      */     //   911	921	924	java/lang/Exception
/*      */     //   934	944	947	java/lang/Exception
/*      */     //   954	964	967	java/lang/Exception
/*      */     //   974	984	987	java/lang/Exception
/*      */   }
/*      */   
/*      */   public ArrayList<int[]> calculaPuntos(ReservacionTO reservacionTO, TelefonoServiceTO telefonoTO)
/*      */   {
/* 2051 */     ArrayList<int[]> puntosRedecionSisact = new ArrayList(0);
/*      */     
/* 2053 */     int[] puntosConsumidos = new int[9];
/* 2054 */     int[] puntosAConsumir = new int[9];
/*      */     
/* 2056 */     int gbSaldoAnt = telefonoTO.getPtsDisponibles();
/* 2057 */     int gbPtosMin = reservacionTO.getPtsMinimos();
/* 2058 */     int variableLocal = gbSaldoAnt + gbPtosMin;
/* 2059 */     int dispRest = variableLocal - reservacionTO.getProductosTO().getValorPuntos();
/*      */     
/*      */ 
/*      */ 
/* 2063 */     telefonoTO.setPtsDisponiblesCF(String.valueOf(dispRest));
/* 2064 */     System.out.println("Puntos DispRest :" + telefonoTO.getPtsDisponiblesCF());
/*      */     
/*      */ 
/* 2067 */     puntosConsumidos[0] = telefonoTO.getPtsPorVencer();
/* 2068 */     puntosConsumidos[1] = telefonoTO.getPtsPorVencer1();
/* 2069 */     puntosConsumidos[2] = telefonoTO.getPtsPorVencer2();
/* 2070 */     puntosConsumidos[3] = telefonoTO.getPtsPromocion();
/* 2071 */     puntosConsumidos[4] = telefonoTO.getPtsAntiguedad();
/* 2072 */     puntosConsumidos[5] = telefonoTO.getPtsExcedentes();
/* 2073 */     puntosConsumidos[6] = telefonoTO.getPtsRenta();
/*      */     
/* 2075 */     int inPuntos = reservacionTO.getProductosTO().getValorPuntos();
/*      */     
/*      */ 
/* 2078 */     for (int i = 0; i <= 6; i++) {
/* 2079 */       if (puntosConsumidos[i] > 0) {
/* 2080 */         if (puntosConsumidos[i] <= inPuntos) {
/* 2081 */           inPuntos -= puntosConsumidos[i];
/* 2082 */           puntosAConsumir[i] = puntosConsumidos[i];
/* 2083 */           puntosConsumidos[i] = 0;
/*      */         } else {
/* 2085 */           puntosConsumidos[i] -= inPuntos;
/* 2086 */           puntosAConsumir[i] = inPuntos;
/* 2087 */           inPuntos = 0;
/*      */           
/* 2089 */           break;
/*      */         }
/*      */       } else {
/* 2092 */         puntosConsumidos[i] = 0;
/*      */       }
/*      */     }
/* 2095 */     puntosRedecionSisact.add(0, puntosAConsumir);
/* 2096 */     puntosRedecionSisact.add(1, puntosConsumidos);
/* 2097 */     System.out.println("<<< PUNTOS REDIMIDOS >>> " + reservacionTO.getProductosTO().getValorPuntos());
/* 2098 */     return puntosRedecionSisact;
/*      */   }
/*      */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/ConsultasDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */