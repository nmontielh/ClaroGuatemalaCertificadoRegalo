/*     */ package com.claro.dao;
/*     */ 
/*     */ import com.claro.exception.CAException;
/*     */ import com.claro.util.ServiceLocator;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TranasferenciaDAO
/*     */ {
/*  16 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/*  17 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*     */   
/*     */   protected ConsultaM2KDAO m2kDAO;
/*     */   
/*     */   protected ConsultasDAO consultasPuntosDAO;
/*     */   
/*     */   protected String schema_database;
/*     */   
/*     */   public TranasferenciaDAO()
/*     */   {
/*  27 */     this.m2kDAO = new ConsultaM2KDAO();
/*  28 */     this.consultasPuntosDAO = new ConsultasDAO();
/*     */     try {
/*  30 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*     */     } catch (Exception e) {
/*  32 */       this.error.error("TranasferenciaDAO", e);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   protected boolean guardarDetalleLinea(java.sql.Connection _cnx, com.claro.transfer.transpuntos.TransferenciaTO _transfTO, int _tipo, boolean esCancelacion)
/*     */     throws CAException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore 5
/*     */     //   3: new 75	java/lang/StringBuffer
/*     */     //   6: dup
/*     */     //   7: sipush 255
/*     */     //   10: invokespecial 77	java/lang/StringBuffer:<init>	(I)V
/*     */     //   13: astore 6
/*     */     //   15: ldc 80
/*     */     //   17: astore 7
/*     */     //   19: iconst_0
/*     */     //   20: istore 8
/*     */     //   22: iconst_0
/*     */     //   23: istore 9
/*     */     //   25: iconst_0
/*     */     //   26: istore 10
/*     */     //   28: aload 6
/*     */     //   30: ldc 82
/*     */     //   32: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   35: aload_0
/*     */     //   36: getfield 55	com/claro/dao/TranasferenciaDAO:schema_database	Ljava/lang/String;
/*     */     //   39: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   42: ldc 88
/*     */     //   44: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   47: pop
/*     */     //   48: aload 6
/*     */     //   50: ldc 90
/*     */     //   52: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   55: pop
/*     */     //   56: aload 6
/*     */     //   58: ldc 92
/*     */     //   60: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   63: pop
/*     */     //   64: aload 6
/*     */     //   66: ldc 94
/*     */     //   68: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   71: ldc 96
/*     */     //   73: invokevirtual 84	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   76: pop
/*     */     //   77: iload_3
/*     */     //   78: iconst_1
/*     */     //   79: if_icmpne +304 -> 383
/*     */     //   82: iload 4
/*     */     //   84: ifeq +37 -> 121
/*     */     //   87: aload_2
/*     */     //   88: invokevirtual 98	com/claro/transfer/transpuntos/TransferenciaTO:getPuntosTrasnferidos	()I
/*     */     //   91: istore 9
/*     */     //   93: bipush 64
/*     */     //   95: istore 10
/*     */     //   97: new 104	java/lang/StringBuilder
/*     */     //   100: dup
/*     */     //   101: ldc 106
/*     */     //   103: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   106: aload_2
/*     */     //   107: invokevirtual 111	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaDestino	()Ljava/lang/String;
/*     */     //   110: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   113: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   116: astore 7
/*     */     //   118: goto +36 -> 154
/*     */     //   121: aload_2
/*     */     //   122: invokevirtual 98	com/claro/transfer/transpuntos/TransferenciaTO:getPuntosTrasnferidos	()I
/*     */     //   125: iconst_m1
/*     */     //   126: imul
/*     */     //   127: istore 9
/*     */     //   129: bipush 13
/*     */     //   131: istore 10
/*     */     //   133: new 104	java/lang/StringBuilder
/*     */     //   136: dup
/*     */     //   137: ldc 121
/*     */     //   139: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   142: aload_2
/*     */     //   143: invokevirtual 111	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaDestino	()Ljava/lang/String;
/*     */     //   146: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   149: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   152: astore 7
/*     */     //   154: new 104	java/lang/StringBuilder
/*     */     //   157: dup
/*     */     //   158: aload 7
/*     */     //   160: invokestatic 123	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   163: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   166: ldc -127
/*     */     //   168: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   171: aload_2
/*     */     //   172: invokevirtual 131	com/claro/transfer/transpuntos/TransferenciaTO:getIdUsuario	()Ljava/lang/String;
/*     */     //   175: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   178: ldc -122
/*     */     //   180: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   183: aload_2
/*     */     //   184: invokevirtual 136	com/claro/transfer/transpuntos/TransferenciaTO:getComentario	()Ljava/lang/String;
/*     */     //   187: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   190: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   193: astore 7
/*     */     //   195: aload 7
/*     */     //   197: invokevirtual 139	java/lang/String:length	()I
/*     */     //   200: bipush 100
/*     */     //   202: if_icmple +13 -> 215
/*     */     //   205: aload 7
/*     */     //   207: iconst_0
/*     */     //   208: bipush 100
/*     */     //   210: invokevirtual 142	java/lang/String:substring	(II)Ljava/lang/String;
/*     */     //   213: astore 7
/*     */     //   215: aload_1
/*     */     //   216: aload 6
/*     */     //   218: invokevirtual 146	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   221: invokeinterface 147 2 0
/*     */     //   226: astore 5
/*     */     //   228: aload 5
/*     */     //   230: iconst_1
/*     */     //   231: aload_2
/*     */     //   232: invokevirtual 153	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaOrigen	()Ljava/lang/String;
/*     */     //   235: invokeinterface 156 3 0
/*     */     //   240: aload 5
/*     */     //   242: iconst_2
/*     */     //   243: aload_2
/*     */     //   244: invokevirtual 162	com/claro/transfer/transpuntos/TransferenciaTO:getSecuenciaOrigen	()I
/*     */     //   247: invokeinterface 165 3 0
/*     */     //   252: aload 5
/*     */     //   254: iconst_3
/*     */     //   255: aload_2
/*     */     //   256: invokevirtual 169	com/claro/transfer/transpuntos/TransferenciaTO:getTelefonoOrigen	()Ljava/lang/String;
/*     */     //   259: invokeinterface 156 3 0
/*     */     //   264: aload 5
/*     */     //   266: iconst_4
/*     */     //   267: new 172	java/sql/Date
/*     */     //   270: dup
/*     */     //   271: invokestatic 174	java/lang/System:currentTimeMillis	()J
/*     */     //   274: invokespecial 180	java/sql/Date:<init>	(J)V
/*     */     //   277: invokeinterface 183 3 0
/*     */     //   282: aload 5
/*     */     //   284: iconst_5
/*     */     //   285: new 187	java/sql/Timestamp
/*     */     //   288: dup
/*     */     //   289: invokestatic 174	java/lang/System:currentTimeMillis	()J
/*     */     //   292: invokespecial 189	java/sql/Timestamp:<init>	(J)V
/*     */     //   295: invokeinterface 190 3 0
/*     */     //   300: aload 5
/*     */     //   302: bipush 6
/*     */     //   304: iload 10
/*     */     //   306: invokeinterface 165 3 0
/*     */     //   311: aload 5
/*     */     //   313: bipush 7
/*     */     //   315: aload_2
/*     */     //   316: invokevirtual 131	com/claro/transfer/transpuntos/TransferenciaTO:getIdUsuario	()Ljava/lang/String;
/*     */     //   319: invokeinterface 156 3 0
/*     */     //   324: aload 5
/*     */     //   326: bipush 8
/*     */     //   328: iconst_0
/*     */     //   329: invokeinterface 165 3 0
/*     */     //   334: aload 5
/*     */     //   336: bipush 9
/*     */     //   338: iconst_0
/*     */     //   339: invokeinterface 165 3 0
/*     */     //   344: aload 5
/*     */     //   346: bipush 10
/*     */     //   348: iload 9
/*     */     //   350: invokeinterface 165 3 0
/*     */     //   355: aload 5
/*     */     //   357: bipush 11
/*     */     //   359: bipush 12
/*     */     //   361: invokeinterface 194 3 0
/*     */     //   366: aload 5
/*     */     //   368: bipush 12
/*     */     //   370: aload 7
/*     */     //   372: invokevirtual 197	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   375: invokeinterface 156 3 0
/*     */     //   380: goto +306 -> 686
/*     */     //   383: iload_3
/*     */     //   384: iconst_2
/*     */     //   385: if_icmpne +301 -> 686
/*     */     //   388: iload 4
/*     */     //   390: ifeq +39 -> 429
/*     */     //   393: aload_2
/*     */     //   394: invokevirtual 98	com/claro/transfer/transpuntos/TransferenciaTO:getPuntosTrasnferidos	()I
/*     */     //   397: iconst_m1
/*     */     //   398: imul
/*     */     //   399: istore 9
/*     */     //   401: bipush 64
/*     */     //   403: istore 10
/*     */     //   405: new 104	java/lang/StringBuilder
/*     */     //   408: dup
/*     */     //   409: ldc -58
/*     */     //   411: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   414: aload_2
/*     */     //   415: invokevirtual 153	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaOrigen	()Ljava/lang/String;
/*     */     //   418: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   421: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   424: astore 7
/*     */     //   426: goto +34 -> 460
/*     */     //   429: aload_2
/*     */     //   430: invokevirtual 98	com/claro/transfer/transpuntos/TransferenciaTO:getPuntosTrasnferidos	()I
/*     */     //   433: istore 9
/*     */     //   435: bipush 13
/*     */     //   437: istore 10
/*     */     //   439: new 104	java/lang/StringBuilder
/*     */     //   442: dup
/*     */     //   443: ldc -56
/*     */     //   445: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   448: aload_2
/*     */     //   449: invokevirtual 153	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaOrigen	()Ljava/lang/String;
/*     */     //   452: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   455: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   458: astore 7
/*     */     //   460: new 104	java/lang/StringBuilder
/*     */     //   463: dup
/*     */     //   464: aload 7
/*     */     //   466: invokestatic 123	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   469: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   472: ldc -127
/*     */     //   474: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   477: aload_2
/*     */     //   478: invokevirtual 131	com/claro/transfer/transpuntos/TransferenciaTO:getIdUsuario	()Ljava/lang/String;
/*     */     //   481: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   484: ldc -122
/*     */     //   486: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   489: aload_2
/*     */     //   490: invokevirtual 136	com/claro/transfer/transpuntos/TransferenciaTO:getComentario	()Ljava/lang/String;
/*     */     //   493: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   496: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   499: astore 7
/*     */     //   501: aload 7
/*     */     //   503: invokevirtual 139	java/lang/String:length	()I
/*     */     //   506: bipush 100
/*     */     //   508: if_icmple +13 -> 521
/*     */     //   511: aload 7
/*     */     //   513: iconst_0
/*     */     //   514: bipush 100
/*     */     //   516: invokevirtual 142	java/lang/String:substring	(II)Ljava/lang/String;
/*     */     //   519: astore 7
/*     */     //   521: aload_1
/*     */     //   522: aload 6
/*     */     //   524: invokevirtual 146	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   527: invokeinterface 147 2 0
/*     */     //   532: astore 5
/*     */     //   534: aload 5
/*     */     //   536: iconst_1
/*     */     //   537: aload_2
/*     */     //   538: invokevirtual 111	com/claro/transfer/transpuntos/TransferenciaTO:getCuentaDestino	()Ljava/lang/String;
/*     */     //   541: invokeinterface 156 3 0
/*     */     //   546: aload 5
/*     */     //   548: iconst_2
/*     */     //   549: aload_2
/*     */     //   550: invokevirtual 202	com/claro/transfer/transpuntos/TransferenciaTO:getSecuenciaDestino	()I
/*     */     //   553: invokeinterface 165 3 0
/*     */     //   558: aload 5
/*     */     //   560: iconst_3
/*     */     //   561: aload_2
/*     */     //   562: invokevirtual 205	com/claro/transfer/transpuntos/TransferenciaTO:getTelefonoDestino	()Ljava/lang/String;
/*     */     //   565: invokeinterface 156 3 0
/*     */     //   570: aload 5
/*     */     //   572: iconst_4
/*     */     //   573: new 172	java/sql/Date
/*     */     //   576: dup
/*     */     //   577: invokestatic 174	java/lang/System:currentTimeMillis	()J
/*     */     //   580: invokespecial 180	java/sql/Date:<init>	(J)V
/*     */     //   583: invokeinterface 183 3 0
/*     */     //   588: aload 5
/*     */     //   590: iconst_5
/*     */     //   591: new 187	java/sql/Timestamp
/*     */     //   594: dup
/*     */     //   595: invokestatic 174	java/lang/System:currentTimeMillis	()J
/*     */     //   598: invokespecial 189	java/sql/Timestamp:<init>	(J)V
/*     */     //   601: invokeinterface 190 3 0
/*     */     //   606: aload 5
/*     */     //   608: bipush 6
/*     */     //   610: iload 10
/*     */     //   612: invokeinterface 165 3 0
/*     */     //   617: aload 5
/*     */     //   619: bipush 7
/*     */     //   621: aload_2
/*     */     //   622: invokevirtual 131	com/claro/transfer/transpuntos/TransferenciaTO:getIdUsuario	()Ljava/lang/String;
/*     */     //   625: invokeinterface 156 3 0
/*     */     //   630: aload 5
/*     */     //   632: bipush 8
/*     */     //   634: iconst_0
/*     */     //   635: invokeinterface 165 3 0
/*     */     //   640: aload 5
/*     */     //   642: bipush 9
/*     */     //   644: iconst_0
/*     */     //   645: invokeinterface 165 3 0
/*     */     //   650: aload 5
/*     */     //   652: bipush 10
/*     */     //   654: iload 9
/*     */     //   656: invokeinterface 165 3 0
/*     */     //   661: aload 5
/*     */     //   663: bipush 11
/*     */     //   665: bipush 12
/*     */     //   667: invokeinterface 194 3 0
/*     */     //   672: aload 5
/*     */     //   674: bipush 12
/*     */     //   676: aload 7
/*     */     //   678: invokevirtual 197	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   681: invokeinterface 156 3 0
/*     */     //   686: aload 5
/*     */     //   688: invokeinterface 208 1 0
/*     */     //   693: istore 8
/*     */     //   695: goto +136 -> 831
/*     */     //   698: astore 11
/*     */     //   700: aload 11
/*     */     //   702: invokevirtual 211	java/sql/SQLException:printStackTrace	()V
/*     */     //   705: aload_0
/*     */     //   706: getfield 31	com/claro/dao/TranasferenciaDAO:error	Lorg/apache/log4j/Logger;
/*     */     //   709: ldc -40
/*     */     //   711: aload 11
/*     */     //   713: invokevirtual 218	org/apache/log4j/Logger:info	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   716: new 73	com/claro/exception/CAException
/*     */     //   719: dup
/*     */     //   720: iconst_m1
/*     */     //   721: new 104	java/lang/StringBuilder
/*     */     //   724: dup
/*     */     //   725: ldc -35
/*     */     //   727: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   730: aload 11
/*     */     //   732: invokevirtual 223	java/sql/SQLException:toString	()Ljava/lang/String;
/*     */     //   735: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   738: ldc -32
/*     */     //   740: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   743: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   746: aload 11
/*     */     //   748: invokespecial 226	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*     */     //   751: athrow
/*     */     //   752: astore 11
/*     */     //   754: aload 11
/*     */     //   756: invokevirtual 229	java/lang/Exception:printStackTrace	()V
/*     */     //   759: aload_0
/*     */     //   760: getfield 31	com/claro/dao/TranasferenciaDAO:error	Lorg/apache/log4j/Logger;
/*     */     //   763: ldc -40
/*     */     //   765: aload 11
/*     */     //   767: invokevirtual 218	org/apache/log4j/Logger:info	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   770: new 73	com/claro/exception/CAException
/*     */     //   773: dup
/*     */     //   774: iconst_m1
/*     */     //   775: new 104	java/lang/StringBuilder
/*     */     //   778: dup
/*     */     //   779: ldc -35
/*     */     //   781: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   784: aload 11
/*     */     //   786: invokevirtual 230	java/lang/Exception:toString	()Ljava/lang/String;
/*     */     //   789: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   792: ldc -32
/*     */     //   794: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   797: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   800: aload 11
/*     */     //   802: invokespecial 226	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*     */     //   805: athrow
/*     */     //   806: astore 12
/*     */     //   808: aload 5
/*     */     //   810: ifnull +18 -> 828
/*     */     //   813: aload 5
/*     */     //   815: invokeinterface 231 1 0
/*     */     //   820: aconst_null
/*     */     //   821: astore 5
/*     */     //   823: goto +5 -> 828
/*     */     //   826: astore 13
/*     */     //   828: aload 12
/*     */     //   830: athrow
/*     */     //   831: aload 5
/*     */     //   833: ifnull +18 -> 851
/*     */     //   836: aload 5
/*     */     //   838: invokeinterface 231 1 0
/*     */     //   843: aconst_null
/*     */     //   844: astore 5
/*     */     //   846: goto +5 -> 851
/*     */     //   849: astore 13
/*     */     //   851: iload 8
/*     */     //   853: ifle +5 -> 858
/*     */     //   856: iconst_1
/*     */     //   857: ireturn
/*     */     //   858: iconst_0
/*     */     //   859: ireturn
/*     */     // Line number table:
/*     */     //   Java source line #48	-> byte code offset #0
/*     */     //   Java source line #49	-> byte code offset #3
/*     */     //   Java source line #50	-> byte code offset #15
/*     */     //   Java source line #51	-> byte code offset #19
/*     */     //   Java source line #54	-> byte code offset #28
/*     */     //   Java source line #55	-> byte code offset #48
/*     */     //   Java source line #56	-> byte code offset #56
/*     */     //   Java source line #57	-> byte code offset #64
/*     */     //   Java source line #59	-> byte code offset #77
/*     */     //   Java source line #60	-> byte code offset #82
/*     */     //   Java source line #61	-> byte code offset #87
/*     */     //   Java source line #62	-> byte code offset #93
/*     */     //   Java source line #63	-> byte code offset #97
/*     */     //   Java source line #65	-> byte code offset #121
/*     */     //   Java source line #66	-> byte code offset #129
/*     */     //   Java source line #67	-> byte code offset #133
/*     */     //   Java source line #69	-> byte code offset #154
/*     */     //   Java source line #70	-> byte code offset #195
/*     */     //   Java source line #71	-> byte code offset #205
/*     */     //   Java source line #73	-> byte code offset #215
/*     */     //   Java source line #75	-> byte code offset #228
/*     */     //   Java source line #76	-> byte code offset #240
/*     */     //   Java source line #77	-> byte code offset #252
/*     */     //   Java source line #78	-> byte code offset #264
/*     */     //   Java source line #79	-> byte code offset #282
/*     */     //   Java source line #80	-> byte code offset #300
/*     */     //   Java source line #81	-> byte code offset #311
/*     */     //   Java source line #82	-> byte code offset #324
/*     */     //   Java source line #83	-> byte code offset #334
/*     */     //   Java source line #84	-> byte code offset #344
/*     */     //   Java source line #85	-> byte code offset #355
/*     */     //   Java source line #86	-> byte code offset #366
/*     */     //   Java source line #88	-> byte code offset #383
/*     */     //   Java source line #89	-> byte code offset #388
/*     */     //   Java source line #90	-> byte code offset #393
/*     */     //   Java source line #91	-> byte code offset #401
/*     */     //   Java source line #92	-> byte code offset #405
/*     */     //   Java source line #94	-> byte code offset #429
/*     */     //   Java source line #95	-> byte code offset #435
/*     */     //   Java source line #96	-> byte code offset #439
/*     */     //   Java source line #98	-> byte code offset #460
/*     */     //   Java source line #99	-> byte code offset #501
/*     */     //   Java source line #100	-> byte code offset #511
/*     */     //   Java source line #102	-> byte code offset #521
/*     */     //   Java source line #104	-> byte code offset #534
/*     */     //   Java source line #106	-> byte code offset #546
/*     */     //   Java source line #107	-> byte code offset #558
/*     */     //   Java source line #108	-> byte code offset #570
/*     */     //   Java source line #109	-> byte code offset #588
/*     */     //   Java source line #110	-> byte code offset #606
/*     */     //   Java source line #111	-> byte code offset #617
/*     */     //   Java source line #112	-> byte code offset #630
/*     */     //   Java source line #113	-> byte code offset #640
/*     */     //   Java source line #114	-> byte code offset #650
/*     */     //   Java source line #115	-> byte code offset #661
/*     */     //   Java source line #116	-> byte code offset #672
/*     */     //   Java source line #119	-> byte code offset #686
/*     */     //   Java source line #121	-> byte code offset #698
/*     */     //   Java source line #122	-> byte code offset #700
/*     */     //   Java source line #123	-> byte code offset #705
/*     */     //   Java source line #124	-> byte code offset #716
/*     */     //   Java source line #125	-> byte code offset #721
/*     */     //   Java source line #124	-> byte code offset #748
/*     */     //   Java source line #126	-> byte code offset #752
/*     */     //   Java source line #127	-> byte code offset #754
/*     */     //   Java source line #128	-> byte code offset #759
/*     */     //   Java source line #129	-> byte code offset #770
/*     */     //   Java source line #130	-> byte code offset #775
/*     */     //   Java source line #129	-> byte code offset #802
/*     */     //   Java source line #131	-> byte code offset #806
/*     */     //   Java source line #132	-> byte code offset #808
/*     */     //   Java source line #133	-> byte code offset #828
/*     */     //   Java source line #132	-> byte code offset #831
/*     */     //   Java source line #135	-> byte code offset #851
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	860	0	this	TranasferenciaDAO
/*     */     //   0	860	1	_cnx	java.sql.Connection
/*     */     //   0	860	2	_transfTO	com.claro.transfer.transpuntos.TransferenciaTO
/*     */     //   0	860	3	_tipo	int
/*     */     //   0	860	4	esCancelacion	boolean
/*     */     //   1	844	5	ps	java.sql.PreparedStatement
/*     */     //   13	510	6	qry	StringBuffer
/*     */     //   17	660	7	referencia	String
/*     */     //   20	832	8	rows	int
/*     */     //   23	632	9	ptsTransf	int
/*     */     //   26	585	10	idMvto	int
/*     */     //   698	49	11	se	java.sql.SQLException
/*     */     //   752	49	11	e	Exception
/*     */     //   806	23	12	localObject	Object
/*     */     //   826	1	13	localException1	Exception
/*     */     //   849	1	13	localException2	Exception
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   28	695	698	java/sql/SQLException
/*     */     //   28	695	752	java/lang/Exception
/*     */     //   28	806	806	finally
/*     */     //   813	823	826	java/lang/Exception
/*     */     //   836	846	849	java/lang/Exception
/*     */   }
/*     */   
/*     */   protected String crearComentario(int _ptsTransf, String _ctaOrigen, String _ctaDestino, String _usuario, String _referencia, int _tipo, boolean esCancelacion)
/*     */     throws CAException
/*     */   {
/* 151 */     StringBuffer referencia = new StringBuffer(255);
/*     */     
/* 153 */     if (_tipo == 1) {
/* 154 */       referencia.append("CIR - ");
/* 155 */       if (esCancelacion)
/* 156 */         referencia.append("CANCELA");
/* 157 */       referencia.append(" TRASPASO DE [").append(_ptsTransf);
/* 158 */       referencia.append("] PUNTOS A LA CUENTA [").append(_ctaDestino);
/* 159 */       referencia.append("], ATENDIO [").append(_usuario);
/* 160 */       referencia.append("] ").append(_referencia);
/*     */     }
/* 162 */     else if (_tipo == 2) {
/* 163 */       referencia.append("CIR - ");
/* 164 */       if (esCancelacion)
/* 165 */         referencia.append("CANCELA");
/* 166 */       referencia.append(" RECEPCION DE [").append(_ptsTransf);
/* 167 */       referencia.append("] PUNTOS DE LA CUENTA [" + _ctaOrigen);
/* 168 */       referencia.append("], ATENDIO [").append(_usuario);
/* 169 */       referencia.append("] ").append(_referencia);
/*     */     }
/*     */     
/* 172 */     return referencia.toString();
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/TranasferenciaDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */