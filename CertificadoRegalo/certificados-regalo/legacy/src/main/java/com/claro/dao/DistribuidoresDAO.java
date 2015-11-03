/*    */ package com.claro.dao;
/*    */ 
/*    */ import com.claro.util.ServiceLocator;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DistribuidoresDAO
/*    */ {
/* 12 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/* 13 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/*    */   private String schema_database;
/*    */   
/*    */   public DistribuidoresDAO()
/*    */   {
/*    */     try {
/* 19 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*    */     } catch (Exception e) {
/* 21 */       this.error.error("DistribuidoresDAO", e);
/*    */     }
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public boolean validaFzaVentasImssFonacot(String fuerzaVentas, String claseCredito, java.sql.Connection conexion)
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aconst_null
/*    */     //   1: astore 4
/*    */     //   3: new 58	java/lang/StringBuffer
/*    */     //   6: dup
/*    */     //   7: invokespecial 60	java/lang/StringBuffer:<init>	()V
/*    */     //   10: astore 5
/*    */     //   12: aconst_null
/*    */     //   13: astore 6
/*    */     //   15: iconst_0
/*    */     //   16: istore 7
/*    */     //   18: aload_2
/*    */     //   19: ldc 61
/*    */     //   21: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   24: ifne +12 -> 36
/*    */     //   27: aload_2
/*    */     //   28: ldc 69
/*    */     //   30: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   33: ifeq +31 -> 64
/*    */     //   36: aload 5
/*    */     //   38: ldc 71
/*    */     //   40: invokevirtual 73	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*    */     //   43: aload_0
/*    */     //   44: getfield 41	com/claro/dao/DistribuidoresDAO:schema_database	Ljava/lang/String;
/*    */     //   47: invokevirtual 73	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*    */     //   50: ldc 77
/*    */     //   52: invokevirtual 73	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*    */     //   55: pop
/*    */     //   56: aload 5
/*    */     //   58: ldc 79
/*    */     //   60: invokevirtual 73	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*    */     //   63: pop
/*    */     //   64: aload_3
/*    */     //   65: aload 5
/*    */     //   67: invokevirtual 81	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*    */     //   70: invokeinterface 85 2 0
/*    */     //   75: astore 4
/*    */     //   77: aload 4
/*    */     //   79: iconst_1
/*    */     //   80: aload_1
/*    */     //   81: invokeinterface 91 3 0
/*    */     //   86: aload 4
/*    */     //   88: invokeinterface 97 1 0
/*    */     //   93: astore 6
/*    */     //   95: goto +6 -> 101
/*    */     //   98: iconst_1
/*    */     //   99: istore 7
/*    */     //   101: aload 6
/*    */     //   103: invokeinterface 101 1 0
/*    */     //   108: ifne -10 -> 98
/*    */     //   111: goto +105 -> 216
/*    */     //   114: astore 8
/*    */     //   116: aload_0
/*    */     //   117: getfield 23	com/claro/dao/DistribuidoresDAO:error	Lorg/apache/log4j/Logger;
/*    */     //   120: new 107	java/lang/StringBuilder
/*    */     //   123: dup
/*    */     //   124: ldc 109
/*    */     //   126: invokespecial 111	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*    */     //   129: aload 8
/*    */     //   131: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*    */     //   134: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*    */     //   137: invokevirtual 118	org/apache/log4j/Logger:error	(Ljava/lang/Object;)V
/*    */     //   140: aload 4
/*    */     //   142: ifnull +15 -> 157
/*    */     //   145: aload 4
/*    */     //   147: invokeinterface 121 1 0
/*    */     //   152: goto +5 -> 157
/*    */     //   155: astore 10
/*    */     //   157: aload 6
/*    */     //   159: ifnull +91 -> 250
/*    */     //   162: aload 6
/*    */     //   164: invokeinterface 124 1 0
/*    */     //   169: goto +81 -> 250
/*    */     //   172: astore 10
/*    */     //   174: goto +76 -> 250
/*    */     //   177: astore 9
/*    */     //   179: aload 4
/*    */     //   181: ifnull +15 -> 196
/*    */     //   184: aload 4
/*    */     //   186: invokeinterface 121 1 0
/*    */     //   191: goto +5 -> 196
/*    */     //   194: astore 10
/*    */     //   196: aload 6
/*    */     //   198: ifnull +15 -> 213
/*    */     //   201: aload 6
/*    */     //   203: invokeinterface 124 1 0
/*    */     //   208: goto +5 -> 213
/*    */     //   211: astore 10
/*    */     //   213: aload 9
/*    */     //   215: athrow
/*    */     //   216: aload 4
/*    */     //   218: ifnull +15 -> 233
/*    */     //   221: aload 4
/*    */     //   223: invokeinterface 121 1 0
/*    */     //   228: goto +5 -> 233
/*    */     //   231: astore 10
/*    */     //   233: aload 6
/*    */     //   235: ifnull +15 -> 250
/*    */     //   238: aload 6
/*    */     //   240: invokeinterface 124 1 0
/*    */     //   245: goto +5 -> 250
/*    */     //   248: astore 10
/*    */     //   250: iload 7
/*    */     //   252: ireturn
/*    */     // Line number table:
/*    */     //   Java source line #28	-> byte code offset #0
/*    */     //   Java source line #29	-> byte code offset #3
/*    */     //   Java source line #30	-> byte code offset #12
/*    */     //   Java source line #31	-> byte code offset #15
/*    */     //   Java source line #35	-> byte code offset #18
/*    */     //   Java source line #36	-> byte code offset #36
/*    */     //   Java source line #37	-> byte code offset #56
/*    */     //   Java source line #40	-> byte code offset #64
/*    */     //   Java source line #41	-> byte code offset #77
/*    */     //   Java source line #42	-> byte code offset #86
/*    */     //   Java source line #44	-> byte code offset #95
/*    */     //   Java source line #45	-> byte code offset #98
/*    */     //   Java source line #44	-> byte code offset #101
/*    */     //   Java source line #48	-> byte code offset #114
/*    */     //   Java source line #49	-> byte code offset #116
/*    */     //   Java source line #51	-> byte code offset #140
/*    */     //   Java source line #52	-> byte code offset #157
/*    */     //   Java source line #50	-> byte code offset #177
/*    */     //   Java source line #51	-> byte code offset #179
/*    */     //   Java source line #52	-> byte code offset #196
/*    */     //   Java source line #53	-> byte code offset #213
/*    */     //   Java source line #51	-> byte code offset #216
/*    */     //   Java source line #52	-> byte code offset #233
/*    */     //   Java source line #54	-> byte code offset #250
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	253	0	this	DistribuidoresDAO
/*    */     //   0	253	1	fuerzaVentas	String
/*    */     //   0	253	2	claseCredito	String
/*    */     //   0	253	3	conexion	java.sql.Connection
/*    */     //   1	221	4	stmt	java.sql.PreparedStatement
/*    */     //   10	56	5	query	StringBuffer
/*    */     //   13	226	6	resultSet	java.sql.ResultSet
/*    */     //   16	235	7	fzaVentasValida	boolean
/*    */     //   114	16	8	e	java.sql.SQLException
/*    */     //   177	37	9	localObject	Object
/*    */     //   155	1	10	localSQLException1	java.sql.SQLException
/*    */     //   172	1	10	localSQLException2	java.sql.SQLException
/*    */     //   194	1	10	localSQLException3	java.sql.SQLException
/*    */     //   211	1	10	localSQLException4	java.sql.SQLException
/*    */     //   231	1	10	localSQLException5	java.sql.SQLException
/*    */     //   248	1	10	localSQLException6	java.sql.SQLException
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   18	111	114	java/sql/SQLException
/*    */     //   145	152	155	java/sql/SQLException
/*    */     //   162	169	172	java/sql/SQLException
/*    */     //   18	140	177	finally
/*    */     //   184	191	194	java/sql/SQLException
/*    */     //   201	208	211	java/sql/SQLException
/*    */     //   221	228	231	java/sql/SQLException
/*    */     //   238	245	248	java/sql/SQLException
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/DistribuidoresDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */