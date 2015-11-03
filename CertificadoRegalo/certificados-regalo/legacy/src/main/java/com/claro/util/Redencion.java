/*     */ package com.claro.util;
/*     */ 
/*     */ import com.claro.exception.CAException;
/*     */ import com.claro.transfer.MobileTO;
/*     */ import com.claro.transfer.ParametrosTO;
/*     */ import com.claro.transfer.PlanTO;
/*     */ import com.claro.transfer.ProductosTO;
/*     */ import com.claro.transfer.PuntosRedimidosTO;
/*     */ import com.claro.transfer.RedencionTO;
/*     */ import com.claro.transfer.TelefonoTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Redencion
/*     */ {
/*     */   public static void validaRedencion(ParametrosTO parametrosTO, TelefonoTO telefonoTO, MobileTO mobileTO)
/*     */   {
/*  24 */     validaRedencion(parametrosTO, telefonoTO, mobileTO, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void validaPlan(ParametrosTO parametrosTO, TelefonoTO telefonoTO)
/*     */   {
/*  35 */     if ((telefonoTO.getPlanTO().getTipoPromocion().trim().equals("AX")) && (!parametrosTO.getFormaRed().trim().equals("PM"))) {
/*  36 */       telefonoTO.agregaMensaje(1, "El Plan Anexo no puede Renovar antes de Finalizar Addendum");
/*  37 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  42 */     if ((telefonoTO.getPlanTO().getTipoPromocion().trim().equals("RE")) && (!parametrosTO.getFormaRed().trim().equals("PM"))) {
/*  43 */       telefonoTO.agregaMensaje(1, "El Plan Retencion no puede Renovar antes de Finalizar Addendum");
/*  44 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void aplicaRedencion(RedencionTO redencionTO, int rubros)
/*     */   {
/*  52 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsPorVencer() > 0)) {
/*  53 */       if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/*  56 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsPorVencer());
/*  57 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencerRedimidos(redencionTO.getPuntosRedimidosTO().getPtsPorVencer());
/*  58 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer(0);
/*     */       }
/*     */       else
/*     */       {
/*  62 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer(redencionTO.getPuntosRedimidosTO().getPtsPorVencer() - redencionTO.getProductosTO().getValorPuntos());
/*  63 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencerRedimidos(redencionTO.getProductosTO().getValorPuntos());
/*  64 */         redencionTO.getProductosTO().setValorPuntos(0);
/*  65 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  71 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsPorVencer1() > 0)) {
/*  72 */       if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer1() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/*  75 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsPorVencer1());
/*  76 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer1Redimidos(redencionTO.getPuntosRedimidosTO().getPtsPorVencer1());
/*  77 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer1(0);
/*     */       }
/*     */       else
/*     */       {
/*  81 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer1(redencionTO.getPuntosRedimidosTO().getPtsPorVencer1() - redencionTO.getProductosTO().getValorPuntos());
/*  82 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer1Redimidos(redencionTO.getProductosTO().getValorPuntos());
/*  83 */         redencionTO.getProductosTO().setValorPuntos(0);
/*  84 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  89 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsPorVencer2() > 0)) {
/*  90 */       if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer2() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/*  93 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsPorVencer2());
/*  94 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer2Redimidos(redencionTO.getPuntosRedimidosTO().getPtsPorVencer2());
/*  95 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer2(0);
/*     */       }
/*     */       else
/*     */       {
/*  99 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer2(redencionTO.getPuntosRedimidosTO().getPtsPorVencer2() - redencionTO.getProductosTO().getValorPuntos());
/* 100 */         redencionTO.getPuntosRedimidosTO().setPtsPorVencer2Redimidos(redencionTO.getProductosTO().getValorPuntos());
/* 101 */         redencionTO.getProductosTO().setValorPuntos(0);
/* 102 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 107 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsPromocion() > 0)) {
/* 108 */       if (redencionTO.getPuntosRedimidosTO().getPtsPromocion() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/* 111 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsPromocion());
/* 112 */         redencionTO.getPuntosRedimidosTO().setPtsPromocionRedimidos(redencionTO.getPuntosRedimidosTO().getPtsPromocion());
/* 113 */         redencionTO.getPuntosRedimidosTO().setPtsPromocion(0);
/*     */       }
/*     */       else
/*     */       {
/* 117 */         redencionTO.getPuntosRedimidosTO().setPtsPromocion(redencionTO.getPuntosRedimidosTO().getPtsPromocion() - redencionTO.getProductosTO().getValorPuntos());
/* 118 */         redencionTO.getPuntosRedimidosTO().setPtsPromocionRedimidos(redencionTO.getProductosTO().getValorPuntos());
/* 119 */         redencionTO.getProductosTO().setValorPuntos(0);
/* 120 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 125 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsAntiguedad() > 0)) {
/* 126 */       if (redencionTO.getPuntosRedimidosTO().getPtsAntiguedad() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/* 129 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsAntiguedad());
/* 130 */         redencionTO.getPuntosRedimidosTO().setPtsPorAntiguedadRedimidos(redencionTO.getPuntosRedimidosTO().getPtsAntiguedad());
/* 131 */         redencionTO.getPuntosRedimidosTO().setPtsAntiguedad(0);
/*     */       }
/*     */       else
/*     */       {
/* 135 */         redencionTO.getPuntosRedimidosTO().setPtsAntiguedad(redencionTO.getPuntosRedimidosTO().getPtsAntiguedad() - redencionTO.getProductosTO().getValorPuntos());
/* 136 */         redencionTO.getPuntosRedimidosTO().setPtsPorAntiguedadRedimidos(redencionTO.getProductosTO().getValorPuntos());
/* 137 */         redencionTO.getProductosTO().setValorPuntos(0);
/* 138 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 143 */     if ((rubros <= 7) && (redencionTO.getPuntosRedimidosTO().getPtsExcedentes() > 0)) {
/* 144 */       if (redencionTO.getPuntosRedimidosTO().getPtsExcedentes() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/* 147 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsExcedentes());
/* 148 */         redencionTO.getPuntosRedimidosTO().setPtsExcedentesRedimidos(redencionTO.getPuntosRedimidosTO().getPtsExcedentes());
/* 149 */         redencionTO.getPuntosRedimidosTO().setPtsExcedentes(0);
/*     */       }
/*     */       else
/*     */       {
/* 153 */         redencionTO.getPuntosRedimidosTO().setPtsExcedentes(redencionTO.getPuntosRedimidosTO().getPtsExcedentes() - redencionTO.getProductosTO().getValorPuntos());
/* 154 */         redencionTO.getPuntosRedimidosTO().setPtsExcedentesRedimidos(redencionTO.getProductosTO().getValorPuntos());
/* 155 */         redencionTO.getProductosTO().setValorPuntos(0);
/* 156 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 161 */     if ((rubros == 7) && (redencionTO.getPuntosRedimidosTO().getPtsRenta() > 0)) {
/* 162 */       if (redencionTO.getPuntosRedimidosTO().getPtsRenta() <= redencionTO.getProductosTO().getValorPuntos())
/*     */       {
/*     */ 
/* 165 */         redencionTO.getProductosTO().setValorPuntos(redencionTO.getProductosTO().getValorPuntos() - redencionTO.getPuntosRedimidosTO().getPtsRenta());
/* 166 */         redencionTO.getPuntosRedimidosTO().setPtsRentaRedimidos(redencionTO.getPuntosRedimidosTO().getPtsRenta());
/* 167 */         redencionTO.getPuntosRedimidosTO().setPtsRenta(0);
/*     */       }
/*     */       else
/*     */       {
/* 171 */         redencionTO.getPuntosRedimidosTO().setPtsRenta(redencionTO.getPuntosRedimidosTO().getPtsRenta() - redencionTO.getProductosTO().getValorPuntos());
/* 172 */         redencionTO.getPuntosRedimidosTO().setPtsRentaRedimidos(redencionTO.getProductosTO().getValorPuntos());
/* 173 */         redencionTO.getProductosTO().setValorPuntos(0);
/* 174 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getDescTipoRed(String tipoRed) {
/* 180 */     if ((tipoRed.equals("CON")) || (tipoRed.equals("CAREG")))
/* 181 */       return "CON FIRMA DE ADDENDUM";
/* 182 */     if (tipoRed.equals("SIN"))
/* 183 */       return "AMIGO KIT";
/* 184 */     if (tipoRed.equals("ACA"))
/* 185 */       return "AMIGO CHIP";
/* 186 */     if (tipoRed.equals("T3G")) {
/* 187 */       return "TARJETA INALAMBRICA 3G";
/*     */     }
/* 189 */     return tipoRed;
/*     */   }
/*     */   
/*     */   public static String getDescFormaRed(String formaRed) {
/* 193 */     if (formaRed.equals("PM"))
/* 194 */       return "TERMINO DE ADENDUM";
/* 195 */     if (formaRed.equals("PR"))
/* 196 */       return "SIN TERMINO DE ADENDUM ";
/* 197 */     if (formaRed.equals("PC"))
/* 198 */       return "SIN TERMINO DE ADENDUM CAREG";
/* 199 */     if (formaRed.equals("PD"))
/* 200 */       return "PUNTOS DISPONIBLES";
/* 201 */     return formaRed;
/*     */   }
/*     */   
/*     */   public static String validaTipoRedencion(String tipoRed) {
/* 205 */     if (("CON".equals(tipoRed.trim())) || (tipoRed.trim().equals("CAREG"))) return "C";
/* 206 */     if ("SIN".equals(tipoRed.trim())) return "S";
/* 207 */     if ("TAIR".equals(tipoRed.trim())) return "T";
/* 208 */     if ("T3G".equals(tipoRed.trim())) return "G";
/* 209 */     if ("ACA".equals(tipoRed.trim())) return "A";
/* 210 */     return null;
/*     */   }
/*     */   
/*     */   public static String validaTipoRedenBD(String sTipoRed) {
/* 214 */     if (sTipoRed.equals("C")) return "CON";
/* 215 */     if (sTipoRed.equals("S")) return "SIN";
/* 216 */     if (sTipoRed.equals("A")) return "ACA";
/* 217 */     if (sTipoRed.equals("G")) return "T3G";
/* 218 */     return sTipoRed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void validaRedencion(ParametrosTO parametrosTO, TelefonoTO telefonoTO, MobileTO mobileTO, boolean validaCAREG)
/*     */   {
/* 229 */     telefonoTO.setNMesActual(Utils.getMesActual(mobileTO.getFecAddM2K().trim()));
/* 230 */     telefonoTO.setNDiaActual(Utils.getDiaActual(mobileTO.getFecAddM2K().trim()));
/* 231 */     telefonoTO.setNDiasMes(Utils.getDiasMes());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 236 */     if ((parametrosTO.getTipoRed().equals("PTS")) || (parametrosTO.getTipoRed().equals("CAN")) || (parametrosTO.getTipoRed().equals("IMP"))) {
/* 237 */       parametrosTO.setFormaRed("CP");
/* 238 */       return; }
/* 239 */     if (parametrosTO.getTipoRed().equals("CON"))
/*     */     {
/* 241 */       boolean bTerminoAdendum = Utils.terminoAdendum(mobileTO.getFecAddM2K(), mobileTO.getAddM2K(), 
/* 242 */         parametrosTO.getRegion(), parametrosTO.getBRedencionAnct());
/*     */       
/*     */ 
/* 245 */       if ((mobileTO.getFecAddM2K() == null) || ("".equals(mobileTO.getFecAddM2K().trim())) || 
/* 246 */         (bTerminoAdendum) || (mobileTO.getAddM2K() == null) || ("0".equals(mobileTO.getAddM2K().trim())))
/*     */       {
/* 248 */         parametrosTO.setFormaRed("PM");
/* 249 */         return; }
/* 250 */       if ((parametrosTO.getRegion() == 9) && (!bTerminoAdendum) && (telefonoTO.getNMesActual() >= 0)) {
/* 251 */         parametrosTO.setFormaRed("PR");
/* 252 */         return; }
/* 253 */       if ((parametrosTO.getRegion() != 9) && (!bTerminoAdendum) && (telefonoTO.getNMesActual() >= 6)) {
/* 254 */         parametrosTO.setFormaRed("PR");
/* 255 */         return;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 260 */       parametrosTO.setFormaRed("NA");
/* 261 */       telefonoTO.agregaMensaje(1, "No ha concluido el tiempo estimado en el plazo forzoso para realizar una redencion. Para R1 a R8 a partir del 6to. mes.");
/*     */       
/*     */ 
/* 264 */       return;
/*     */     }
/* 266 */     if (parametrosTO.getTipoRed().trim().equals("CAREG")) {
/* 267 */       parametrosTO.setFormaRed("PC");
/*     */       
/* 269 */       if (validaCAREG) {
/* 270 */         validaRedencionCAREG(parametrosTO, telefonoTO);
/*     */       }
/*     */     }
/*     */     else {
/* 274 */       if ((parametrosTO.getTipoRed().equals("SIN")) || (parametrosTO.getTipoRed().equals("ACA")) || (parametrosTO.getTipoRed().equals("T3G"))) {
/* 275 */         parametrosTO.setFormaRed("PD");
/* 276 */         return;
/*     */       }
/* 278 */       telefonoTO.agregaMensaje(1, "El tipo de redencion no es valido");
/* 279 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static String validaRedencionDistribuidoresSisact(boolean isReservacionAnticipada, String fechaPlazoForzoso, String plazoForzoso)
/*     */     throws CAException
/*     */   {
/* 287 */     String formaRedencion = "";
/*     */     
/*     */ 
/* 290 */     if (isReservacionAnticipada)
/*     */     {
/*     */ 
/* 293 */       if (Utils.terminoAdendum(fechaPlazoForzoso, plazoForzoso, 9, 
/* 294 */         isReservacionAnticipada ? "1" : "0"))
/*     */       {
/* 296 */         formaRedencion = "PM";
/*     */       }
/*     */       else {
/* 299 */         throw new CAException(-1, "No termino adendum anticipado.");
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 304 */     else if (Utils.terminoAdendum(fechaPlazoForzoso, plazoForzoso, 9, 
/* 305 */       isReservacionAnticipada ? "1" : "0"))
/*     */     {
/*     */ 
/* 308 */       formaRedencion = "PM";
/*     */     }
/*     */     else {
/* 311 */       formaRedencion = "PR";
/*     */     }
/*     */     
/*     */ 
/* 315 */     return formaRedencion;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void validaRedencionCAREG(ParametrosTO parametrosTO, TelefonoTO telefonoTO)
/*     */   {
/* 328 */     if ((parametrosTO.getMesCareg().length() > 0) && (parametrosTO.getAddCareg().length() > 0)) {
/* 329 */       if (Integer.parseInt(parametrosTO.getMesCareg()) > Integer.parseInt(parametrosTO.getAddCareg()))
/* 330 */         telefonoTO.agregaMensaje(1, "El No. de Meses para Prorrateo CAREG debe ser Menor que el Addendum ");
/* 331 */     } else { if ((parametrosTO.getRegion() != 9) && (telefonoTO.getNMesActual() + Integer.parseInt(parametrosTO.getMesCareg()) <= 6))
/*     */       {
/* 333 */         telefonoTO.agregaMensaje(1, "No ha concluido el tiempo estimado en el plazo forzoso para realizar una redencion. Para R1 a R8 a partir del 6to. mes.");
/*     */         
/*     */ 
/* 336 */         return;
/*     */       }
/* 338 */       telefonoTO.agregaMensaje(1, "Para realizar un redencion por CAREG es necesario capturar el addendum y el numero de meses que lleva dentro de el mismo.");
/*     */       
/*     */ 
/* 341 */       return;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/util/Redencion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */