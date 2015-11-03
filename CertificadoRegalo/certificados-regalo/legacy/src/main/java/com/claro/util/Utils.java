/*      */package com.claro.util;

/*      */
/*      *//*      */
import java.math.BigDecimal;
/*      */
import java.security.MessageDigest;
/*      */
import java.security.NoSuchAlgorithmException;
/*      */
import java.sql.ResultSet;
/*      */
import java.sql.SQLException;
/*      */
import java.text.SimpleDateFormat;
/*      */
import java.util.ArrayList;
/*      */
import java.util.Calendar;
/*      */
import java.util.Date;
/*      */
import java.util.GregorianCalendar;
/*      */
import java.util.ListIterator;
/*      */
import java.util.Locale;
/*      */
import java.util.Vector;

/*      */
import org.apache.log4j.Logger;

import com.claro.exception.CAException;
/*      */
import com.claro.seguridad.SeguridadCaUtil;
/*      */
import com.claro.transfer.CatalogoTO;
/*      */
import com.claro.transfer.PerfilTO;
/*      */
import com.claro.transfer.PuntosRedimidosTO;
/*      */
import com.claro.transfer.PuntosTO;

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
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */public class Utils
/*      */extends Constantes
/*      */{
	/* 45 */protected static final Logger error = Logger
			.getLogger("loggerCirculoAzulError");
	/* 46 */protected static final Logger logger = Logger
			.getLogger("loggerCirculoAzul");

	/*      */
	/*      */public static String setFormatoDecimal(String _decimal)
			throws CAException {
		/*      */try {
			/* 50 */if (_decimal != null) {
				/* 51 */BigDecimal value = new BigDecimal(_decimal);
				/* 52 */value = value.setScale(2, 4);
				/* 53 */return DECIMAL_FORMATTER.format(value);
				/*      */}
			/* 55 */return "";
			/*      */} catch (Exception e) {
			/* 57 */throw new CAException(-1,
					"No se pudo convertir el valor Decimal en Cadena de texto");
			/*      */}
		/*      */}

	/*      */
	/*      */public static String formatFecha(String valor)
	/*      */{
		/* 63 */if (valor == null)
			/* 64 */return null;
		/* 65 */if (valor.substring(6, 7).equals("9")) {
			/* 66 */return "19" + valor.substring(6, 8) + "-"
					+ valor.substring(3, 5) + "-" + valor.substring(0, 2);
			/*      */}
		/* 68 */return "20" + valor.substring(6, 8) + "-"
				+ valor.substring(3, 5) + "-" + valor.substring(0, 2);
		/*      */}

	/*      */
	/*      */public static boolean getValEstatusTel(String sEstatus, String sMotivo)
	/*      */{
		/* 73 */boolean bResp = false;
		/* 74 */if ((sEstatus != null)
				&& (sMotivo != null)
				&&
				/* 75 */(!sEstatus.trim().equals("AC"))
				&& ((!sEstatus.trim().equals("SU")) || (
				/* 76 */(!sMotivo.trim().equals("ROEXT"))
						&& (!sMotivo.trim().equals("SUEXT")) &&
				/* 77 */(!sMotivo.trim().equals("SUPET"))))) {
			/* 78 */bResp = true;
			/*      */}
		/*      */
		/* 81 */return bResp;
		/*      */}

	/*      */
	/*      */public static int getMesActual(String fecPzoFzo)
	/*      */{
		/* 86 */int inMesActual = 0;
		/* 87 */int inAdYear = Integer.parseInt(fecPzoFzo.substring(0, 4));
		/* 88 */int inAdDay = Integer.parseInt(fecPzoFzo.substring(8, 10));
		/* 89 */int inAdMon = Integer.parseInt(fecPzoFzo.substring(5, 7));
		/* 90 */int inCurYear = Integer.parseInt(getFechaInsert().substring(6,
				10));
		/* 91 */int inCurDay = Integer.parseInt(getFechaInsert()
				.substring(3, 5));
		/* 92 */int inCurMon = Integer.parseInt(getFechaInsert()
				.substring(0, 2));
		/*      */
		/* 94 */int inAnioActual = inCurYear - inAdYear;
		/*      */
		/* 96 */if ((inAnioActual == 1) || (inAnioActual == 0)) {
			/* 97 */inAnioActual = 12;
			/*      */} else {
			/* 99 */inAnioActual = 24;
			/*      */}
		/*      */
		/* 102 */if (inCurYear == inAdYear) {
			/* 103 */inMesActual = inCurMon - inAdMon;
			/*      */}
		/* 105 */else if (inCurMon <= inAdMon) {
			/* 106 */inMesActual = inAnioActual - (inAdMon - inCurMon);
			/*      */} else {
			/* 108 */inMesActual = inAnioActual + (inCurMon - inAdMon);
			/*      */}
		/*      */
		/*      */
		/* 112 */if (inAdDay > inCurDay) {
			/* 113 */inMesActual--;
			/*      */}
		/* 115 */return inMesActual;
		/*      */}

	/*      */
	/*      */public static String getFechaInsert()
	/*      */{
		/* 120 */Locale currentLocale = new Locale("en", "US");
		/* 121 */SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy", currentLocale);
		/* 122 */return dateFormatter.format(new Date());
		/*      */}

	/*      */
	/*      */public static String getFechaActual()
	/*      */{
		/* 127 */Locale currentLocale = new Locale("en", "US");
		/* 128 */SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyy/MM/dd", currentLocale);
		/* 129 */return dateFormatter.format(new Date());
		/*      */}

	/*      */
	/*      */
	/*      */public static int getDiaActual(String fecPzoFzo)
	/*      */{
		/* 135 */int inMesActual = 0;
		/* 136 */int inDiaActual = 0;
		/* 137 */int idiastrans = 0;
		/* 138 */int inAdYear = Integer.parseInt(fecPzoFzo.substring(0, 4));
		/* 139 */int inAdDay = Integer.parseInt(fecPzoFzo.substring(8, 10));
		/* 140 */int inAdMon = Integer.parseInt(fecPzoFzo.substring(5, 7));
		/* 141 */int inCurYear = Integer.parseInt(getFechaInsert().substring(6,
				10));
		/* 142 */int inCurDay = Integer.parseInt(getFechaInsert().substring(3,
				5));
		/* 143 */int inCurMon = Integer.parseInt(getFechaInsert().substring(0,
				2));
		/*      */
		/*      */
		/* 146 */if (inAdDay <= inCurDay)
		/*      */{
			/* 148 */inDiaActual = inCurDay - inAdDay;
			/* 149 */idiastrans = inDiaActual;
			/*      */} else {
			/* 151 */int iMesAnt = 0;
			int idiasMes = 0;
			/*      */
			/* 153 */switch (inCurMon) {
			/*      */case 1:
				/*      */
			case 3:
				/*      */
			case 5:
				/*      */
			case 7:
				/*      */
			case 8:
				/*      */
			case 10:
				/*      */
			case 12:
				/* 161 */idiasMes = 31;
				/* 162 */break;
			/*      */case 2:
				/* 164 */if (esBisiesto(String.valueOf(inCurYear))) {
					/* 165 */idiasMes = 30;
					/*      */} else {
					/* 167 */idiasMes = 29;
					/*      */}
				/* 169 */break;
			/*      */case 4:
			case 6:
			case 9:
			case 11:
			default:
				/* 171 */idiasMes = 30;
				/*      */}
			/* 173 */idiastrans = idiasMes - inAdDay + inCurDay;
			/*      */}
		/* 175 */return idiastrans;
		/*      */}

	/*      */
	/*      */static boolean esBisiesto(String anioActual)
	/*      */{
		/* 180 */int anio = Integer.parseInt(anioActual);
		/* 181 */if (anio % 4 == 0)
			/* 182 */return true;
		/* 183 */return false;
		/*      */}

	/*      */
	/*      */
	/*      */public static int getDiasMes()
	/*      */{
		/* 189 */int idiasMes = 0;
		/* 190 */int inCurYear = Integer.parseInt(getFechaInsert().substring(6,
				10));
		/* 191 */int inCurDay = Integer.parseInt(getFechaInsert().substring(3,
				5));
		/* 192 */int inCurMon = Integer.parseInt(getFechaInsert().substring(0,
				2));
		/*      */
		/* 194 */switch (inCurMon) {
		/*      */case 1:
			/*      */
		case 3:
			/*      */
		case 5:
			/*      */
		case 7:
			/*      */
		case 8:
			/*      */
		case 10:
			/*      */
		case 12:
			/* 202 */idiasMes = 31;
			/* 203 */break;
		/*      */case 2:
			/* 205 */if (esBisiesto(String.valueOf(inCurYear))) {
				/* 206 */idiasMes = 30;
				/*      */} else {
				/* 208 */idiasMes = 29;
				/*      */}
			/* 210 */break;
		/*      */case 4:
		case 6:
		case 9:
		case 11:
		default:
			/* 212 */idiasMes = 30;
			/*      */}
		/* 214 */return idiasMes;
		/*      */}

	/*      */
	/*      */public static boolean terminoAdendum(String fecAde, String pzo,
			int region, String sRedencionAntc)
	/*      */{
		/* 219 */int plazo = Integer.parseInt(pzo);
		/* 220 */int anioAde = 0;
		int mesAde = 0;
		int fecVen = 0;
		int anioVence = 0;
		/* 221 */String anioVen = "";
		String mesVen = "";
		String diaVen = "";
		/* 222 */String fechahoy = "";
		/* 223 */int anioV = 0;
		int mesV = 0;
		int diaV = 0;
		/* 224 */int anioA = 0;
		int mesA = 0;
		int diaA = 0;
		/*      */
		/* 226 */boolean resp = false;
		/*      */
		/* 228 */if (plazo == 0) {
			/* 229 */return true;
			/*      */}
		/*      */
		/* 232 */anioAde = Integer.parseInt(fecAde.substring(0, 4));
		/* 233 */mesAde = Integer.parseInt(fecAde.substring(5, 7));
		/* 234 */diaVen = fecAde.substring(8, 10);
		/*      */
		/* 236 */if (mesAde == 12) {
			/* 237 */anioVen = Integer.toString((plazo + mesAde - 1) / 12
					+ anioAde);
			/*      */} else {
			/* 239 */anioVen = Integer
					.toString((plazo + mesAde) / 12 + anioAde);
			/*      */}
		/*      */
		/* 242 */mesVen = Integer.toString((mesAde + plazo) % 12);
		/*      */
		/* 244 */if (mesVen.length() == 1) {
			/* 245 */mesVen = "0" + mesVen;
			/*      */}
		/* 247 */if (mesVen.equals("00")) {
			/* 248 */mesVen = "12";
			/*      */}
		/* 250 */if ((mesVen.equals("12")) && (mesAde != 12)) {
			/* 251 */anioVence = Integer.parseInt(anioVen) - 1;
			/* 252 */anioVen = Integer.toString(anioVence);
			/*      */}
		/*      */
		/* 255 */fecVen = Integer.parseInt(anioVen + mesVen + diaVen);
		/*      */
		/*      */
		/* 258 */Locale currentLocale = new Locale("en", "US");
		/* 259 */SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyyMMdd", currentLocale);
		/*      */
		/* 261 */fechahoy = String.valueOf(dateFormatter.format(new Date()));
		/*      */
		/* 263 */anioV = Integer.parseInt(anioVen);
		/* 264 */mesV = Integer.parseInt(mesVen) - 1;
		/* 265 */diaV = Integer.parseInt(diaVen);
		/* 266 */anioA = Integer.parseInt(fechahoy.substring(0, 4));
		/* 267 */mesA = Integer.parseInt(fechahoy.substring(4, 6)) - 1;
		/* 268 */diaA = Integer.parseInt(fechahoy.substring(6, 8));
		/*      */
		/* 270 */GregorianCalendar fechaven1 = new GregorianCalendar(anioV,
				mesV, diaV);
		/* 271 */GregorianCalendar fechahoy1 = new GregorianCalendar(anioA,
				mesA, diaA);
		/*      */
		/* 273 */long difDias = diferenciaEnDias(fechaven1, fechahoy1);
		/* 274 */if (region == 9) {
			/* 275 */if (difDias <= 25L) {
				/* 276 */resp = true;
				/* 277 */} else if ((difDias > 25L) && (difDias <= 90L)
					&& ("1".equals(sRedencionAntc))) {
				/* 278 */resp = true;
				/*      */} else {
				/* 280 */resp = false;
				/*      */}
			/*      */}
		/* 283 */else if (difDias <= 15L) {
			/* 284 */resp = true;
			/* 285 */} else if ((difDias > 15L) && (difDias <= 180L)
				&& ("1".equals(sRedencionAntc))) {
			/* 286 */resp = true;
			/*      */} else {
			/* 288 */resp = false;
			/*      */}
		/*      */
		/*      */
		/* 292 */return resp;
		/*      */}

	/*      */
	/*      */public static long diferenciaEnDias(Calendar cal1, Calendar cal2)
	/*      */{
		/* 297 */long diferencia = 0L;
		/*      */
		/*      */
		/* 300 */diferencia = cal1.getTime().getTime()
				- cal2.getTime().getTime();
		/* 301 */diferencia /= 86400000L;
		/*      */
		/* 303 */return diferencia;
		/*      */}

	/*      */
	/*      */public static boolean getValEstatusCobranza(String sEstatusCob,
			int nReg, String sBoton, String sMotivo, String sStatusTel)
	/*      */{
		/* 308 */boolean bResp = false;
		/*      */
		/* 310 */if ((sBoton != null) && (sStatusTel != null)
				&& (sStatusTel.equals("AN"))) {
			/* 311 */if (sBoton.trim().equals("Saldos")) {
				/* 312 */bResp = true;
				/* 313 */} else if (sBoton.trim().equals("Reden")) {
				/* 314 */return false;
				/*      */}
			/*      */}
		/*      */
		/* 318 */if ((sEstatusCob != null)
				&& (sBoton != null)
				&& (
				/* 319 */(sEstatusCob.trim().equals("BH"))
						|| (sEstatusCob.trim().equals("BN"))
						|| (sMotivo.trim().equals("ROEXT"))
						|| (sMotivo.trim().equals("SUEXT")) || (sMotivo.trim()
						.equals("SUPET")))) {
			/* 320 */bResp = true;
			/*      */}
		/*      */
		/*      */
		/* 324 */if (sBoton.trim().equals("Saldos")) {
			/* 325 */if ((sEstatusCob.trim().equals("EM"))
					|| (sEstatusCob.trim().equals("HL"))
					|| (sEstatusCob.trim().equals("SP")) ||
					/* 326 */(sEstatusCob.trim().equals("SM"))
					|| (sEstatusCob.trim().equals("RE"))
					|| (sEstatusCob.trim().equals("ET")) ||
					/* 327 */(sEstatusCob.trim().equals("SL"))
					|| (sEstatusCob.trim().equals("ST"))) {
				/* 328 */bResp = true;
				/*      */}
			/* 330 */else if ((nReg != 9) && (sEstatusCob.trim().equals("AR"))) {
				bResp = true;
				/* 331 */} else if (((sEstatusCob.trim().equals("SU")) || (sEstatusCob
					.trim().equals("FU"))) && (nReg == 9)) {
				bResp = true;
				/*      */}
			/*      */}
		/*      */
		/* 335 */if ((sBoton.trim().equals("Saldos"))
				&& (nReg != 9)
				&& (
				/* 336 */(sEstatusCob.trim().equals("EM"))
						|| (sEstatusCob.trim().equals("HL"))
						|| (sEstatusCob.trim().equals("SP")) ||
						/* 337 */(sEstatusCob.trim().equals("AR"))
						|| (sEstatusCob.trim().equals("ET"))
						|| (sEstatusCob.trim().equals("RE")) ||
						/* 338 */(sEstatusCob.trim().equals("SL"))
						|| (sEstatusCob.trim().equals("SM")) || (sEstatusCob
							.trim().equals("ST")))) {
			/* 339 */bResp = true;
			/*      */}
		/*      */
		/* 342 */return bResp;
		/*      */}

	/*      */
	/*      */public static boolean ValidaCredito(String sClase, int region,
			PerfilTO perfilTO)
	/*      */{
		/* 347 */boolean tienePrivilegios = true;
		/* 348 */if (((sClase != null) || (perfilTO != null)) &&
		/* 349 */(sClase.trim().equals("AR"))) {
			/* 350 */tienePrivilegios = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "60");
			/*      */}
		/*      */
		/* 353 */return tienePrivilegios;
		/*      */}

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
	/*      */public static boolean defineIva(String tipoReden, PerfilTO perfilTO)
	/*      */{
		/* 378 */boolean resp = false;
		/*      */
		/* 380 */if (tipoReden.equals("SIN")) {
			/* 381 */resp = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "138");
			/*      */}
		/* 383 */if (tipoReden.equals("ACA")) {
			/* 384 */resp = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "143");
			/*      */}
		/* 386 */if (tipoReden.equals("CON")) {
			/* 387 */resp = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "121");
			/*      */}
		/* 389 */if (tipoReden.equals("CAREG")) {
			/* 390 */resp = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "125");
			/*      */}
		/* 392 */if (tipoReden.equals("T3G")) {
			/* 393 */resp = SeguridadCaUtil.getInstance()
					.validaPerfilProcesoCa(perfilTO, "147");
			/*      */}
		/* 395 */return resp;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */public static int comparaFechas(ArrayList<String> vFechas,
			int indice1, int indice2)
	/*      */{
		/* 402 */String lFecAlta1 = "";
		String lFecAlta2 = "";
		/*      */
		/*      */try
		/*      */{
			/* 406 */lFecAlta1 = (String) vFechas.get(indice1);
			/* 407 */int dia1 = Integer.parseInt(lFecAlta1.substring(8, 10));
			/* 408 */int mes1 = Integer.parseInt(lFecAlta1.substring(5, 7));
			/* 409 */int anio1 = Integer.parseInt(lFecAlta1.substring(0, 4));
			/* 410 */lFecAlta2 = (String) vFechas.get(indice2);
			/* 411 */int dia2 = Integer.parseInt(lFecAlta2.substring(8, 10));
			/* 412 */int mes2 = Integer.parseInt(lFecAlta2.substring(5, 7));
			/* 413 */int anio2 = Integer.parseInt(lFecAlta2.substring(0, 4));
			/*      */
			/* 415 */if (anio1 < anio2)
				/* 416 */return indice1;
			/* 417 */if (anio2 < anio1)
				/* 418 */return indice2;
			/* 419 */if (mes1 < mes2)
				/* 420 */return indice1;
			/* 421 */if (mes2 < mes1)
				/* 422 */return indice2;
			/* 423 */if (dia1 < dia2)
				/* 424 */return indice1;
			/* 425 */return indice2;
			/*      */}
		/*      */catch (Exception e) {
		}
		/* 428 */return -1;
		/*      */}

	/*      */
	/*      */public static String validaResultSet(ResultSet resultSet,
			String campo, Class tipo) throws CAException
	/*      */{
		/* 433 */String valor = "";
		/*      */try {
			/* 435 */if ((String.class == tipo) &&
			/* 436 */(resultSet.getString(campo) != null))
				/* 437 */valor = resultSet.getString(campo);
			/*      */} catch (SQLException e) {
			/* 439 */throw new CAException(-1, "SQLException.validaResultSet ["
					+ e.toString() + "]", e);
			/* 440 */} catch (Exception e) {
			throw new CAException(-1, "ConsultasDAO.validaResultSet["
					+ e.toString() + "]", e);
			/*      */}
		/* 442 */return valor;
		/*      */}

	/*      */
	/*      */public static int getMesFecha(String fvenc2, String freden)
	/*      */{
		/* 447 */int inMesActual = 0;
		/* 448 */int inYearvenc2 = Integer.parseInt(fvenc2.substring(0, 4));
		/* 449 */int inAdYear = Integer.parseInt(freden.substring(0, 4));
		/* 450 */int inMonvenc2 = Integer.parseInt(fvenc2.substring(5, 7));
		/* 451 */int inAdMon = Integer.parseInt(freden.substring(5, 7));
		/* 452 */int inDayvenc2 = Integer.parseInt(fvenc2.substring(8, 10));
		/* 453 */int inAdDay = Integer.parseInt(freden.substring(8, 10));
		/* 454 */int inAnioActual = inAdYear - inYearvenc2;
		/* 455 */int intAnioTot = 0;
		/*      */
		/*      */
		/* 458 */if ((inAnioActual == 1) || (inAnioActual == 0))
			intAnioTot = 12;
		else {
			/* 459 */intAnioTot = inAnioActual * 12;
			/*      */}
		/* 461 */if (inYearvenc2 == inAdYear) {
			inMesActual = inAdMon - inMonvenc2;
			/*      */}
		/* 463 */else if (inAdMon <= inMonvenc2)
			inMesActual = intAnioTot - (inMonvenc2 - inAdMon);
		else {
			/* 464 */inMesActual = intAnioTot + (inAdMon - inMonvenc2);
			/*      */}
		/*      */
		/*      */
		/* 468 */if (inDayvenc2 > inAdDay) {
			/* 469 */inMesActual--;
			/*      */}
		/* 471 */return inMesActual;
		/*      */}

	/*      */
	/*      */public static String nombreMesfecha(Date sFecha)
	/*      */{
		/* 476 */int nMes = Integer.parseInt(sFecha.toString().substring(5, 7));
		/* 477 */String sMes = "";
		/* 478 */switch (nMes) {
		/* 479 */case 1:
			sMes = "Enero";
			/* 480 */break;
		/* 481 */case 2:
			sMes = "Febrero";
			/* 482 */break;
		/* 483 */case 3:
			sMes = "Marzo";
			/* 484 */break;
		/* 485 */case 4:
			sMes = "Abril";
			/* 486 */break;
		/* 487 */case 5:
			sMes = "Mayo";
			/* 488 */break;
		/* 489 */case 6:
			sMes = "Junio";
			/* 490 */break;
		/* 491 */case 7:
			sMes = "Julio";
			/* 492 */break;
		/* 493 */case 8:
			sMes = "Agosto";
			/* 494 */break;
		/* 495 */case 9:
			sMes = "Septiembre";
			/* 496 */break;
		/* 497 */case 10:
			sMes = "Octubre";
			/* 498 */break;
		/* 499 */case 11:
			sMes = "Noviembre";
			/* 500 */break;
		/* 501 */case 12:
			sMes = "Diciembre";
			/*      */}
		/* 503 */return sMes;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */public static String generaFolio(String telefono)
	/*      */{
		/* 510 */String folio = "";
		/*      */
		/* 512 */folio = Constantes.yyyyMMddHHmmss.format(new Date())
				.substring(2, 13) + telefono.substring(2, 10);
		/* 513 */return folio;
		/*      */}

	/*      */
	/*      */
	/*      */public static boolean getValEstatusCobranza(String sEstatus, int nReg,
			String sBoton, String sMotivo)
	/*      */{
		/* 519 */boolean bResp = false;
		/*      */
		/* 521 */if ((sEstatus != null)
				&& (sBoton != null)
				&& (
				/* 522 */(sEstatus.trim().equals("BH"))
						|| (sEstatus.trim().equals("BN"))
						|| (sMotivo.trim().equals("ROEXT"))
						|| (sMotivo.trim().equals("SUEXT")) || (sMotivo.trim()
						.equals("SUPET")))) {
			/* 523 */bResp = true;
			/*      */}
		/* 525 */if ((sBoton.trim().equals("Saldos"))
				&& (nReg != 9)
				&& (
				/* 526 */(sEstatus.trim().equals("EM"))
						|| (sEstatus.trim().equals("HL"))
						|| (sEstatus.trim().equals("SP")) ||
						/* 527 */(sEstatus.trim().equals("AR"))
						|| (sEstatus.trim().equals("ET"))
						|| (sEstatus.trim().equals("RE")) ||
						/* 528 */(sEstatus.trim().equals("SL"))
						|| (sEstatus.trim().equals("SM")) || (sEstatus.trim()
						.equals("ST")))) {
			/* 529 */bResp = true;
			/*      */}
		/*      */
		/* 532 */return bResp;
		/*      */}

	/*      */
	/*      */public static String validaTipoRedencion(String tipoRed) {
		/* 536 */if (("CON".equals(tipoRed)) || (tipoRed.equals("CAREG")))
			return "C";
		/* 537 */if ("SIN".equals(tipoRed))
			return "S";
		/* 538 */if ("TAIR".equals(tipoRed))
			return "T";
		/* 539 */if ("T3G".equals(tipoRed))
			return "G";
		/* 540 */if ("ACA".equals(tipoRed))
			return "A";
		/* 541 */return null;
		/*      */}

	/*      */
	/*      */public static long calcularDiasEntreFechas(long fecha1, long fecha2)
	/*      */{
		/* 546 */long diff = fecha2 - fecha1;
		/*      */
		/* 548 */long dias = diff / 86400000L;
		/* 549 */return dias;
		/*      */}

	/*      */
	/*      */public static String setFormatoPtos(int puntos) {
		/*      */try {
			/* 554 */return formatea.format(puntos);
			/*      */}
		/*      */catch (Exception e) {
		}
		/* 557 */return Integer.toString(puntos);
		/*      */}

	/*      */
	/*      */public static String anexarCeros(int longi_total, String str)
	/*      */{
		/* 562 */if (longi_total <= str.length()) {
			/* 563 */return str.substring(str.length() - longi_total);
			/*      */}
		/* 565 */StringBuffer buf = new StringBuffer();
		/* 566 */for (int i = 1; i <= longi_total - str.length(); i++) {
			/* 567 */buf.append("0");
			/*      */}
		/* 569 */buf.append(str);
		/* 570 */return buf.toString();
		/*      */}

	/*      */
	/*      */public static boolean validaFechaActivacion(String fechaAltaM2K)
	/*      */{
		/*      */try
		/*      */{
			/* 577 */Calendar fechaActual = Calendar.getInstance();
			/* 578 */Calendar fechaAlta = Calendar.getInstance();
			/* 579 */fechaAlta.setTime(Constantes.DATEFORMATyyyy_MM_dd
					.parse(fechaAltaM2K));
			/* 580 */if (((fechaAlta.get(2) == fechaActual.get(2) - 1) && (fechaAlta
					.get(1) == fechaActual.get(1) - 1))
					|| (
					/* 581 */(fechaAlta.get(2) == fechaActual.get(2)) && (fechaAlta
							.get(1) == fechaActual.get(1))))
			/*      */{
				/* 583 */return false;
				/*      */}
			/* 585 */return true;
			/*      */}
		/*      */catch (Exception e) {
		}
		/* 588 */return false;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */public static Date fechaExpiracion(boolean isDistribuidores)
	/*      */{
		/* 595 */Calendar fechaExp = Calendar.getInstance();
		/*      */
		/*      */
		/* 598 */if (isDistribuidores) {
			/* 599 */fechaExp.add(5, 7);
			/*      */} else {
			/* 601 */fechaExp.add(5, 1);
			/*      */}
		/* 603 */return fechaExp.getTime();
		/*      */}

	/*      */
	/*      */
	/*      */public static int limpiaFormatoDecimal(String lValorConFo)
	/*      */{
		/* 609 */double res = 0.0D;
		/* 610 */String sValorSinFo = "";
		/* 611 */for (int i = 0; i < lValorConFo.length(); i++) {
			/* 612 */char c = lValorConFo.charAt(i);
			/* 613 */switch (c) {
			/*      */case '0':
				/*      */
			case '1':
				/*      */
			case '2':
				/*      */
			case '3':
				/*      */
			case '4':
				/*      */
			case '5':
				/*      */
			case '6':
				/*      */
			case '7':
				/*      */
			case '8':
				/*      */
			case '9':
				/* 624 */sValorSinFo = sValorSinFo + c;
				/*      */}
			/*      */
			/*      */}
		/*      */
		/*      */
		/* 630 */return Integer.parseInt(sValorSinFo);
		/*      */}

	/*      */
	/*      */public static String generaFolioAlianza(String telefono)
	/*      */{
		/* 635 */String folio = "";
		/* 636 */Locale currentLocale = new Locale("en", "US");
		/* 637 */SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyyMMddhhmmss", currentLocale);
		/* 638 */folio = dateFormatter.format(new Date()).substring(3, 14)
				+ telefono.substring(2, 10);
		/* 639 */return folio;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */public static PuntosRedimidosTO regresaPuntos(PuntosTO puntosTO,
			PuntosRedimidosTO puntosRedimidosTO)
	/*      */{
		/* 649 */PuntosRedimidosTO redimidosTO = new PuntosRedimidosTO();
		/* 650 */redimidosTO.setPtsRenta(puntosTO.getPtsRenta()
				+ puntosRedimidosTO.getPtsRenta());
		/* 651 */redimidosTO.setPtsExcedentes(puntosTO.getPtsExcedentes()
				+ puntosRedimidosTO.getPtsExcedentesRedimidos()
				- puntosRedimidosTO.getPtsSobrantes1());
		/* 652 */redimidosTO.setPtsPorVencer(puntosTO.getPtsPorVencer()
				+ puntosRedimidosTO.getPtsPorVencer());
		/* 653 */redimidosTO.setPtsPorVencer1(puntosTO.getPtsPorVencer1()
				+ puntosRedimidosTO.getPtsPorVencer1());
		/* 654 */redimidosTO.setPtsPorVencer2(puntosTO.getPtsPorVencer2()
				+ puntosRedimidosTO.getPtsPorVencer2());
		/* 655 */redimidosTO.setPtsPromocion(puntosTO.getPtsPromocion()
				+ puntosRedimidosTO.getPtsPromocionRedimidos());
		/* 656 */redimidosTO.setPtsAntiguedad(puntosTO.getPtsAntiguedad()
				+ puntosRedimidosTO.getPtsPorAntiguedadRedimidos());
		/* 657 */redimidosTO.setPtsRedimidos(puntosTO.getPtsRedimidos()
				- puntosRedimidosTO.getPtsRedimidos());
		/* 658 */redimidosTO.setFecVencer(puntosRedimidosTO.getFecVencer());
		/* 659 */redimidosTO.setFecVencer1(puntosRedimidosTO.getFecVencer1());
		/* 660 */redimidosTO.setFecVencer2(puntosRedimidosTO.getFecVencer2());
		/* 661 */redimidosTO.setBonoEquipo(puntosTO.getBonoEquipo()
				+ puntosRedimidosTO.getPtsSobrantes());
		/* 662 */redimidosTO.setBBono(puntosTO.getBBono());
		/* 663 */return redimidosTO;
		/*      */}

	/*      */
	/*      */
	/*      */public static PuntosRedimidosTO canjePuntos(PuntosTO puntosTO,
			int valorPuntos, int rubros)
	/*      */{
		/* 669 */PuntosRedimidosTO redimidosTO = new PuntosRedimidosTO(puntosTO);
		/* 670 */if ((rubros <= 7) && (puntosTO.getPtsPorVencer() > 0)) {
			/* 671 */if (puntosTO.getPtsPorVencer() <= valorPuntos)
			/*      */{
				/*      */
				/* 674 */valorPuntos -= puntosTO.getPtsPorVencer();
				/* 675 */redimidosTO.setPtsPorVencerRedimidos(puntosTO
						.getPtsPorVencer());
				/* 676 */redimidosTO.setPtsPorVencer(0);
				/*      */}
			/*      */else
			/*      */{
				/* 680 */redimidosTO.setPtsPorVencer(puntosTO.getPtsPorVencer()
						- valorPuntos);
				/* 681 */redimidosTO.setPtsPorVencerRedimidos(valorPuntos);
				/* 682 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/*      */
		/* 688 */if ((rubros <= 7) && (puntosTO.getPtsPorVencer1() > 0)) {
			/* 689 */if (puntosTO.getPtsPorVencer1() <= valorPuntos)
			/*      */{
				/*      */
				/* 692 */valorPuntos -= puntosTO.getPtsPorVencer1();
				/* 693 */redimidosTO.setPtsPorVencer1Redimidos(puntosTO
						.getPtsPorVencer1());
				/* 694 */redimidosTO.setPtsPorVencer1(0);
				/*      */
				/*      */}
			/*      */else
			/*      */{
				/* 699 */redimidosTO.setPtsPorVencer1(puntosTO
						.getPtsPorVencer1() - valorPuntos);
				/* 700 */redimidosTO.setPtsPorVencer1Redimidos(valorPuntos);
				/* 701 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/* 706 */if ((rubros <= 7) && (puntosTO.getPtsPorVencer2() > 0)) {
			/* 707 */if (puntosTO.getPtsPorVencer2() <= valorPuntos)
			/*      */{
				/*      */
				/* 710 */valorPuntos -= puntosTO.getPtsPorVencer2();
				/* 711 */redimidosTO.setPtsPorVencer2Redimidos(puntosTO
						.getPtsPorVencer2());
				/* 712 */redimidosTO.setPtsPorVencer2(0);
				/*      */}
			/*      */else
			/*      */{
				/* 716 */redimidosTO.setPtsPorVencer2(puntosTO
						.getPtsPorVencer2() - valorPuntos);
				/* 717 */redimidosTO.setPtsPorVencer2Redimidos(valorPuntos);
				/* 718 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/* 723 */if ((rubros <= 7) && (puntosTO.getPtsPromocion() > 0)) {
			/* 724 */if (puntosTO.getPtsPromocion() <= valorPuntos)
			/*      */{
				/*      */
				/* 727 */valorPuntos -= puntosTO.getPtsPromocion();
				/* 728 */redimidosTO.setPtsPromocionRedimidos(puntosTO
						.getPtsPromocion());
				/* 729 */redimidosTO.setPtsPromocion(0);
				/*      */}
			/*      */else
			/*      */{
				/* 733 */redimidosTO.setPtsPromocion(puntosTO.getPtsPromocion()
						- valorPuntos);
				/* 734 */redimidosTO.setPtsPromocionRedimidos(valorPuntos);
				/* 735 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/* 740 */if ((rubros <= 7) && (puntosTO.getPtsAntiguedad() > 0)) {
			/* 741 */if (puntosTO.getPtsAntiguedad() <= valorPuntos)
			/*      */{
				/*      */
				/*      */
				/* 745 */valorPuntos -= puntosTO.getPtsAntiguedad();
				/* 746 */redimidosTO.setPtsPorAntiguedadRedimidos(puntosTO
						.getPtsAntiguedad());
				/* 747 */redimidosTO.setPtsAntiguedad(0);
				/*      */}
			/*      */else
			/*      */{
				/* 751 */redimidosTO.setPtsAntiguedad(puntosTO
						.getPtsAntiguedad() - valorPuntos);
				/* 752 */redimidosTO.setPtsPorAntiguedadRedimidos(valorPuntos);
				/* 753 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/* 758 */if ((rubros <= 7) && (puntosTO.getPtsExcedentes() > 0)) {
			/* 759 */if (puntosTO.getPtsExcedentes() <= valorPuntos)
			/*      */{
				/*      */
				/* 762 */valorPuntos -= puntosTO.getPtsExcedentes();
				/* 763 */redimidosTO.setPtsExcedentesRedimidos(puntosTO
						.getPtsExcedentes());
				/* 764 */redimidosTO.setPtsExcedentes(0);
				/*      */}
			/*      */else
			/*      */{
				/* 768 */redimidosTO.setPtsExcedentes(puntosTO
						.getPtsExcedentes() - valorPuntos);
				/* 769 */redimidosTO.setPtsExcedentesRedimidos(valorPuntos);
				/* 770 */return redimidosTO;
				/*      */}
			/*      */}
		/*      */
		/*      */
		/* 775 */if ((rubros == 7) && (puntosTO.getPtsRenta() > 0)) {
			/* 776 */if (puntosTO.getPtsRenta() <= valorPuntos)
			/*      */{
				/*      */
				/* 779 */valorPuntos -= puntosTO.getPtsRenta();
				/* 780 */redimidosTO.setPtsRentaRedimidos(puntosTO
						.getPtsRenta());
				/* 781 */redimidosTO.setPtsRenta(0);
				/*      */}
			/*      */else
			/*      */{
				/* 785 */redimidosTO.setPtsRenta(puntosTO.getPtsRenta()
						- valorPuntos);
				/* 786 */redimidosTO.setPtsRentaRedimidos(valorPuntos);
				/* 787 */return redimidosTO;
				/*      */}
			/*      */}
		/* 790 */return redimidosTO;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */public static long diferenciaDiasTransf(String _fecAlta)
	/*      */{
		/* 801 */long lDifDias = 60L;
		/*      */try
		/*      */{
			/* 804 */Locale currentLocale = new Locale("en", "US");
			/* 805 */SimpleDateFormat dateFormatAlta = new SimpleDateFormat(
					"yyyy-MM-dd", currentLocale);
			/*      */
			/* 807 */Date dFecAlta = dateFormatAlta.parse(_fecAlta);
			/* 808 */Date dFecHoy = new Date();
			/*      */
			/* 810 */lDifDias = dFecHoy.getTime() - dFecAlta.getTime();
			/* 811 */lDifDias /= 86400000L;
			/*      */}
		/*      */catch (Exception e) {
			/* 814 */e.printStackTrace();
			/*      */}
		/*      */
		/* 817 */return lDifDias;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */public static int obtienePuntosPorAntiguedad(int aniosAntig)
	/*      */{
		/* 825 */int puntosAntig = 0;
		/*      */
		/* 827 */if (aniosAntig <= 0)
			/* 828 */return puntosAntig;
		/* 829 */if ((aniosAntig > 0) && (aniosAntig <= 3))
			/* 830 */return puntosAntig = aniosAntig * 1000;
		/* 831 */if ((aniosAntig > 3) && (aniosAntig < 16)) {
			/* 832 */for (int i = 0; i < 3; i++) {
				/* 833 */if ((aniosAntig - (3 + i + 1)) % 3 == 0) {
					/* 834 */puntosAntig = (aniosAntig - i) * 1000;
					/*      */}
				/*      */}
			/*      */} else {
			/* 838 */return puntosAntig = '㺀';
			/*      */}
		/* 840 */return puntosAntig;
		/*      */}

	/*      */
	/*      */public static boolean validaFechaM2K(String fechaAltaM2K) {
		/* 844 */boolean valor = true;
		/*      */
		/* 846 */if (fechaAltaM2K.length() >= 10)
		/*      */{
			/* 848 */if ((fechaAltaM2K.charAt(4) != '-')
					|| (fechaAltaM2K.charAt(7) != '-')) {
				/* 849 */valor = false;
				/*      */}
			/*      */} else {
			/* 852 */valor = false;
			/*      */}
		/* 854 */return valor;
		/*      */}

	/*      */
	/*      */public static String setFormatoDecimalPrecio(String _decimal)
			throws CAException {
		/*      */try {
			/* 859 */if (_decimal != null) {
				/* 860 */BigDecimal value = new BigDecimal(_decimal);
				/* 861 */value = value.setScale(2, 4);
				/* 862 */return DECIMAL_FORMATTER_PRECIOS.format(value);
				/*      */}
			/* 864 */return "";
			/*      */} catch (Exception e) {
			/* 866 */throw new CAException(-1,
					"No se pudo convertir el valor Decimal en Cadena de texto");
			/*      */}
		/*      */}

	/*      */
	/*      */
	/*      */public static String generaFolio()
	/*      */{
		/* 873 */String folio = "";
		/* 874 */Locale currentLocale = new Locale("en", "US");
		/* 875 */SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyyMMddhhmmss", currentLocale);
		/* 876 */folio = dateFormatter.format(new Date()).substring(2, 14);
		/* 877 */return folio;
		/*      */}

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
	/*      */public static int calculaAntiguedad(Date lFecAlta)
	/*      */{
		/* 892 */int opcRes1 = 0;
		int opcRes2 = 0;
		/*      */
		/* 894 */String sFecAltaM2K = Constantes.DATEFORMATyyyy_MM_dd
				.format(lFecAlta);
		/*      */
		/* 896 */int diaAlta = Integer.parseInt(sFecAltaM2K.substring(8, 10));
		/* 897 */int mesAlta = Integer.parseInt(sFecAltaM2K.substring(5, 7));
		/* 898 */int anioAlta = Integer.parseInt(sFecAltaM2K.substring(0, 4));
		/*      */
		/* 900 */String hoy = getFechaInsert();
		/*      */
		/* 902 */int diaHoy = Integer.parseInt(hoy.substring(3, 5));
		/* 903 */int mesHoy = Integer.parseInt(hoy.substring(0, 2));
		/* 904 */int anioHoy = Integer.parseInt(hoy.substring(6, 10));
		/*      */
		/*      */
		/* 907 */opcRes1 = anioHoy - anioAlta;
		/* 908 */opcRes2 = anioHoy - anioAlta - 1;
		/*      */
		/* 910 */if (mesHoy > mesAlta)
			/* 911 */return opcRes1;
		/* 912 */if (mesHoy < mesAlta)
			/* 913 */return opcRes2;
		/* 914 */if (diaHoy > diaAlta)
			/* 915 */return opcRes1;
		/* 916 */if (diaHoy < diaAlta)
			/* 917 */return opcRes2;
		/* 918 */return opcRes1;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */public static int calculaPuntos(String lFecAlta)
	/*      */{
		/* 925 */int nResult = 0;
		/*      */try
		/*      */{
			/* 928 */Date fechaAlta = new Date(Constantes.DATEFORMATyyyy_MM_dd
					.parse(lFecAlta).getTime());
			/*      */
			/* 930 */GregorianCalendar calendario = new GregorianCalendar();
			/* 931 */calendario.setTime(fechaAlta);
			/*      */
			/* 933 */calendario.add(5, -10);
			/*      */
			/* 935 */int anios = calculaAntiguedad(calendario.getTime());
			/* 936 */return obtienePuntosPorAntiguedad(anios);
			/*      */}
		/*      */catch (Exception e) {
		}
		/* 939 */return -1;
		/*      */}

	/*      */
	/*      *//* Error */
	/*      */public static boolean enviaCorreo(
			java.util.Map<String, String> parametrosCorreo, String subject,
			String mensaje, java.util.List<String> archivos)
	/*      */{
		/*      */// Byte code:
		/*      */// 0: aconst_null
		/*      */// 1: astore 4
		/*      */// 3: aconst_null
		/*      */// 4: astore 5
		/*      */// 6: getstatic 23 com/claro/util/Utils:logger
				// Lorg/apache/log4j/Logger;
		/*      */// 9: ldc_w 719
		/*      */// 12: invokevirtual 721 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;)V
		/*      */// 15: new 725 com/claro/catalogo/Catalogo
		/*      */// 18: dup
		/*      */// 19: invokespecial 727 com/claro/catalogo/Catalogo:<init> ()V
		/*      */// 22: astore 6
		/*      */// 24: aload 6
		/*      */// 26: ldc_w 728
		/*      */// 29: invokevirtual 730 com/claro/catalogo/Catalogo:setTabla
				// (Ljava/lang/String;)V
		/*      */// 32: aload 6
		/*      */// 34: invokevirtual 733 com/claro/catalogo/Catalogo:cargaCatalogo
				// ()V
		/*      */// 37: new 74 java/lang/String
		/*      */// 40: dup
		/*      */// 41: invokespecial 736 java/lang/String:<init> ()V
		/*      */// 44: astore 7
		/*      */// 46: aload_0
		/*      */// 47: ldc_w 737
		/*      */// 50: invokeinterface 739 2 0
		/*      */// 55: checkcast 74 java/lang/String
		/*      */// 58: astore 8
		/*      */// 60: aload_0
		/*      */// 61: ldc_w 744
		/*      */// 64: invokeinterface 739 2 0
		/*      */// 69: checkcast 74 java/lang/String
		/*      */// 72: astore 7
		/*      */// 74: aload 6
		/*      */// 76: ldc_w 746
		/*      */// 79: invokevirtual 748 com/claro/catalogo/Catalogo:getPropiedad
				// (Ljava/lang/String;)Ljava/lang/String;
		/*      */// 82: astore 9
		/*      */// 84: invokestatic 751 java/lang/System:getProperties
				// ()Ljava/util/Properties;
		/*      */// 87: astore 10
		/*      */// 89: aload 10
		/*      */// 91: aload 6
		/*      */// 93: ldc_w 757
		/*      */// 96: invokevirtual 748 com/claro/catalogo/Catalogo:getPropiedad
				// (Ljava/lang/String;)Ljava/lang/String;
		/*      */// 99: aload 9
		/*      */// 101: invokevirtual 759 java/util/Properties:put
				// (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
		/*      */// 104: pop
		/*      */// 105: aload 10
		/*      */// 107: aconst_null
		/*      */// 108: invokestatic 765 javax/mail/Session:getInstance
				// (Ljava/util/Properties;Ljavax/mail/Authenticator;)Ljavax/mail/Session;
		/*      */// 111: astore 11
		/*      */// 113: new 770 javax/mail/internet/MimeMessage
		/*      */// 116: dup
		/*      */// 117: aload 11
		/*      */// 119: invokespecial 772 javax/mail/internet/MimeMessage:<init>
				// (Ljavax/mail/Session;)V
		/*      */// 122: astore 12
		/*      */// 124: aload 12
		/*      */// 126: new 775 javax/mail/internet/InternetAddress
		/*      */// 129: dup
		/*      */// 130: aload 8
		/*      */// 132: invokespecial 777
				// javax/mail/internet/InternetAddress:<init>
				// (Ljava/lang/String;)V
		/*      */// 135: invokevirtual 778 javax/mail/Message:setFrom
				// (Ljavax/mail/Address;)V
		/*      */// 138: aload 12
		/*      */// 140: getstatic 784 javax/mail/Message$RecipientType:TO
				// Ljavax/mail/Message$RecipientType;
		/*      */// 143: aload 7
		/*      */// 145: iconst_0
		/*      */// 146: invokestatic 790 javax/mail/internet/InternetAddress:parse
				// (Ljava/lang/String;Z)[Ljavax/mail/internet/InternetAddress;
		/*      */// 149: invokevirtual 793 javax/mail/Message:addRecipients
				// (Ljavax/mail/Message$RecipientType;[Ljavax/mail/Address;)V
		/*      */// 152: aload 12
		/*      */// 154: aload_1
		/*      */// 155: invokevirtual 797 javax/mail/Message:setSubject
				// (Ljava/lang/String;)V
		/*      */// 158: new 800 javax/mail/internet/MimeMultipart
		/*      */// 161: dup
		/*      */// 162: invokespecial 802 javax/mail/internet/MimeMultipart:<init>
				// ()V
		/*      */// 165: astore 13
		/*      */// 167: new 803 javax/mail/internet/MimeBodyPart
		/*      */// 170: dup
		/*      */// 171: invokespecial 805 javax/mail/internet/MimeBodyPart:<init>
				// ()V
		/*      */// 174: astore 14
		/*      */// 176: aload 14
		/*      */// 178: aload_2
		/*      */// 179: invokevirtual 806 javax/mail/BodyPart:setText
				// (Ljava/lang/String;)V
		/*      */// 182: aload 13
		/*      */// 184: aload 14
		/*      */// 186: invokevirtual 811 javax/mail/Multipart:addBodyPart
				// (Ljavax/mail/BodyPart;)V
		/*      */// 189: aload_3
		/*      */// 190: ifnull +172 -> 362
		/*      */// 193: aload_3
		/*      */// 194: invokeinterface 817 1 0
		/*      */// 199: astore 15
		/*      */// 201: goto +151 -> 352
		/*      */// 204: aload 15
		/*      */// 206: invokeinterface 823 1 0
		/*      */// 211: checkcast 74 java/lang/String
		/*      */// 214: astore 16
		/*      */// 216: new 829 java/io/File
		/*      */// 219: dup
		/*      */// 220: aload 16
		/*      */// 222: invokespecial 831 java/io/File:<init> (Ljava/lang/String;)V
		/*      */// 225: astore 4
		/*      */// 227: aload 4
		/*      */// 229: ifnull +102 -> 331
		/*      */// 232: new 832 java/io/BufferedReader
		/*      */// 235: dup
		/*      */// 236: new 834 java/io/FileReader
		/*      */// 239: dup
		/*      */// 240: aload 4
		/*      */// 242: invokespecial 836 java/io/FileReader:<init>
				// (Ljava/io/File;)V
		/*      */// 245: invokespecial 839 java/io/BufferedReader:<init>
				// (Ljava/io/Reader;)V
		/*      */// 248: astore 5
		/*      */// 250: new 468 java/lang/StringBuffer
		/*      */// 253: dup
		/*      */// 254: invokespecial 470 java/lang/StringBuffer:<init> ()V
		/*      */// 257: astore 17
		/*      */// 259: aconst_null
		/*      */// 260: astore 18
		/*      */// 262: goto +17 -> 279
		/*      */// 265: aload 17
		/*      */// 267: aload 18
		/*      */// 269: invokevirtual 471 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*      */// 272: ldc_w 842
		/*      */// 275: invokevirtual 471 java/lang/StringBuffer:append
				// (Ljava/lang/String;)Ljava/lang/StringBuffer;
		/*      */// 278: pop
		/*      */// 279: aload 5
		/*      */// 281: invokevirtual 844 java/io/BufferedReader:readLine
				// ()Ljava/lang/String;
		/*      */// 284: dup
		/*      */// 285: astore 18
		/*      */// 287: ifnonnull -22 -> 265
		/*      */// 290: aload 17
		/*      */// 292: invokevirtual 847 java/lang/StringBuffer:length ()I
		/*      */// 295: ifle +36 -> 331
		/*      */// 298: new 803 javax/mail/internet/MimeBodyPart
		/*      */// 301: dup
		/*      */// 302: invokespecial 805 javax/mail/internet/MimeBodyPart:<init>
				// ()V
		/*      */// 305: astore 14
		/*      */// 307: aload 14
		/*      */// 309: aload 16
		/*      */// 311: invokevirtual 848 javax/mail/BodyPart:setFileName
				// (Ljava/lang/String;)V
		/*      */// 314: aload 14
		/*      */// 316: aload 17
		/*      */// 318: invokevirtual 474 java/lang/StringBuffer:toString
				// ()Ljava/lang/String;
		/*      */// 321: invokevirtual 806 javax/mail/BodyPart:setText
				// (Ljava/lang/String;)V
		/*      */// 324: aload 13
		/*      */// 326: aload 14
		/*      */// 328: invokevirtual 811 javax/mail/Multipart:addBodyPart
				// (Ljavax/mail/BodyPart;)V
		/*      */// 331: aload 5
		/*      */// 333: ifnull +8 -> 341
		/*      */// 336: aload 5
		/*      */// 338: invokevirtual 851 java/io/BufferedReader:close ()V
		/*      */// 341: aload 4
		/*      */// 343: ifnull +9 -> 352
		/*      */// 346: aload 4
		/*      */// 348: invokevirtual 854 java/io/File:delete ()Z
		/*      */// 351: pop
		/*      */// 352: aload 15
		/*      */// 354: invokeinterface 858 1 0
		/*      */// 359: ifne -155 -> 204
		/*      */// 362: aload 12
		/*      */// 364: aload 13
		/*      */// 366: invokevirtual 861 javax/mail/Message:setContent
				// (Ljavax/mail/Multipart;)V
		/*      */// 369: aload 12
		/*      */// 371: invokestatic 865 javax/mail/Transport:send
				// (Ljavax/mail/Message;)V
		/*      */// 374: getstatic 23 com/claro/util/Utils:logger
				// Lorg/apache/log4j/Logger;
		/*      */// 377: ldc_w 871
		/*      */// 380: invokevirtual 721 org/apache/log4j/Logger:info
				// (Ljava/lang/Object;)V
		/*      */// 383: goto +70 -> 453
		/*      */// 386: astore 6
		/*      */// 388: aload 6
		/*      */// 390: invokevirtual 672 java/lang/Exception:printStackTrace ()V
		/*      */// 393: aload 5
		/*      */// 395: ifnull +13 -> 408
		/*      */// 398: aload 5
		/*      */// 400: invokevirtual 851 java/io/BufferedReader:close ()V
		/*      */// 403: goto +5 -> 408
		/*      */// 406: astore 20
		/*      */// 408: aload 4
		/*      */// 410: ifnull +69 -> 479
		/*      */// 413: aload 4
		/*      */// 415: invokevirtual 854 java/io/File:delete ()Z
		/*      */// 418: pop
		/*      */// 419: goto +60 -> 479
		/*      */// 422: astore 19
		/*      */// 424: aload 5
		/*      */// 426: ifnull +13 -> 439
		/*      */// 429: aload 5
		/*      */// 431: invokevirtual 851 java/io/BufferedReader:close ()V
		/*      */// 434: goto +5 -> 439
		/*      */// 437: astore 20
		/*      */// 439: aload 4
		/*      */// 441: ifnull +9 -> 450
		/*      */// 444: aload 4
		/*      */// 446: invokevirtual 854 java/io/File:delete ()Z
		/*      */// 449: pop
		/*      */// 450: aload 19
		/*      */// 452: athrow
		/*      */// 453: aload 5
		/*      */// 455: ifnull +13 -> 468
		/*      */// 458: aload 5
		/*      */// 460: invokevirtual 851 java/io/BufferedReader:close ()V
		/*      */// 463: goto +5 -> 468
		/*      */// 466: astore 20
		/*      */// 468: aload 4
		/*      */// 470: ifnull +9 -> 479
		/*      */// 473: aload 4
		/*      */// 475: invokevirtual 854 java/io/File:delete ()Z
		/*      */// 478: pop
		/*      */// 479: iconst_1
		/*      */// 480: ireturn
		/*      */// Line number table:
		/*      */// Java source line #944 -> byte code offset #0
		/*      */// Java source line #945 -> byte code offset #3
		/*      */// Java source line #947 -> byte code offset #6
		/*      */// Java source line #951 -> byte code offset #15
		/*      */// Java source line #952 -> byte code offset #24
		/*      */// Java source line #953 -> byte code offset #32
		/*      */// Java source line #959 -> byte code offset #37
		/*      */// Java source line #960 -> byte code offset #46
		/*      */// Java source line #961 -> byte code offset #60
		/*      */// Java source line #963 -> byte code offset #74
		/*      */// Java source line #964 -> byte code offset #84
		/*      */// Java source line #966 -> byte code offset #89
		/*      */// Java source line #968 -> byte code offset #105
		/*      */// Java source line #969 -> byte code offset #113
		/*      */// Java source line #970 -> byte code offset #124
		/*      */// Java source line #972 -> byte code offset #138
		/*      */// Java source line #973 -> byte code offset #152
		/*      */// Java source line #975 -> byte code offset #158
		/*      */// Java source line #976 -> byte code offset #167
		/*      */// Java source line #978 -> byte code offset #176
		/*      */// Java source line #979 -> byte code offset #182
		/*      */// Java source line #981 -> byte code offset #189
		/*      */// Java source line #982 -> byte code offset #193
		/*      */// Java source line #983 -> byte code offset #201
		/*      */// Java source line #984 -> byte code offset #204
		/*      */// Java source line #985 -> byte code offset #216
		/*      */// Java source line #987 -> byte code offset #227
		/*      */// Java source line #988 -> byte code offset #232
		/*      */// Java source line #990 -> byte code offset #250
		/*      */// Java source line #992 -> byte code offset #259
		/*      */// Java source line #993 -> byte code offset #262
		/*      */// Java source line #994 -> byte code offset #265
		/*      */// Java source line #993 -> byte code offset #279
		/*      */// Java source line #997 -> byte code offset #290
		/*      */// Java source line #998 -> byte code offset #298
		/*      */// Java source line #999 -> byte code offset #307
		/*      */// Java source line #1000 -> byte code offset #314
		/*      */// Java source line #1001 -> byte code offset #324
		/*      */// Java source line #1004 -> byte code offset #331
		/*      */// Java source line #1005 -> byte code offset #341
		/*      */// Java source line #983 -> byte code offset #352
		/*      */// Java source line #1009 -> byte code offset #362
		/*      */// Java source line #1010 -> byte code offset #369
		/*      */// Java source line #1012 -> byte code offset #374
		/*      */// Java source line #1013 -> byte code offset #386
		/*      */// Java source line #1014 -> byte code offset #388
		/*      */// Java source line #1016 -> byte code offset #393
		/*      */// Java source line #1017 -> byte code offset #408
		/*      */// Java source line #1015 -> byte code offset #422
		/*      */// Java source line #1016 -> byte code offset #424
		/*      */// Java source line #1017 -> byte code offset #439
		/*      */// Java source line #1018 -> byte code offset #450
		/*      */// Java source line #1016 -> byte code offset #453
		/*      */// Java source line #1017 -> byte code offset #468
		/*      */// Java source line #1019 -> byte code offset #479
		/*      */// Local variable table:
		/*      */// start length slot name signature
		/*      */// 0 481 0 parametrosCorreo java.util.Map<String, String>
		/*      */// 0 481 1 subject String
		/*      */// 0 481 2 mensaje String
		/*      */// 0 481 3 archivos java.util.List<String>
		/*      */// 1 473 4 file java.io.File
		/*      */// 4 455 5 reader java.io.BufferedReader
		/*      */// 22 70 6 properties com.claro.catalogo.Catalogo
		/*      */// 386 3 6 e Exception
		/*      */// 44 100 7 adress String
		/*      */// 58 73 8 from String
		/*      */// 82 18 9 host String
		/*      */// 87 19 10 prop java.util.Properties
		/*      */// 111 7 11 ses1 javax.mail.Session
		/*      */// 122 248 12 msg javax.mail.Message
		/*      */// 165 200 13 mPart javax.mail.Multipart
		/*      */// 174 153 14 msgBP javax.mail.BodyPart
		/*      */// 199 154 15 iterator java.util.Iterator<String>
		/*      */// 214 96 16 nombreArchivo String
		/*      */// 257 60 17 buffer StringBuffer
		/*      */// 260 26 18 linea String
		/*      */// 422 29 19 localObject Object
		/*      */// 406 1 20 localIOException java.io.IOException
		/*      */// 437 1 20 localIOException1 java.io.IOException
		/*      */// 466 1 20 localIOException2 java.io.IOException
		/*      */// Exception table:
		/*      */// from to target type
		/*      */// 6 383 386 java/lang/Exception
		/*      */// 398 403 406 java/io/IOException
		/*      */// 6 393 422 finally
		/*      */// 429 434 437 java/io/IOException
		/*      */// 458 463 466 java/io/IOException
		// TODO se agrega el tipo de retorno para que compile
		return true;
		/*      */}

	/*      */
	/*      */public static String formatoIdPromocionEP(int maxSize, int idPromocion)
	/*      */{
		/* 1023 */StringBuffer idPromocionEP = new StringBuffer();
		/* 1024 */String idPromo = String.valueOf(idPromocion);
		/* 1025 */if (idPromo.length() < maxSize) {
			/* 1026 */for (int i = 0; i < maxSize - idPromo.length(); i++) {
				/* 1027 */idPromocionEP.append("0");
				/*      */}
			/* 1029 */idPromocionEP.append(idPromo);
			/*      */}
		/* 1031 */return idPromocionEP.toString();
		/*      */}

	/*      */
	/*      */public static String obtieneValorPropiedad(
			ArrayList<CatalogoTO> lstCatalogoTO, String propiedad)
	/*      */{
		/* 1036 */String result = "";
		/* 1037 */CatalogoTO catalogoTO = null;
		/* 1038 */if (lstCatalogoTO != null) {
			/* 1039 */ListIterator<CatalogoTO> listIterator = lstCatalogoTO
					.listIterator();
			/* 1040 */while (listIterator.hasNext()) {
				/* 1041 */catalogoTO = (CatalogoTO) listIterator.next();
				/* 1042 */if ((catalogoTO.getIdVariable() != null)
						&& (propiedad.equals(catalogoTO.getIdVariable()))) {
					/* 1043 */result = catalogoTO.getValor();
					/*      */}
				/*      */}
			/*      */}
		/* 1047 */return result;
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */public static String getMD5Encrypted(String e)
	/*      */{
		/* 1054 */MessageDigest mdEnc = null;
		/*      */try {
			/* 1056 */mdEnc = MessageDigest.getInstance("MD5");
			/*      */} catch (NoSuchAlgorithmException ex) {
			/* 1058 */return null;
			/*      */}
		/*      */
		/* 1061 */mdEnc.update(e.trim().getBytes());
		/* 1062 */String hash = toHexString(mdEnc.digest());
		/* 1063 */return new String(hash);
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */public static String toHexString(byte[] bytes)
	/*      */{
		/* 1073 */char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		/* 1074 */char[] hexChars = new char[bytes.length * 2];
		/*      */
		/* 1076 */for (int j = 0; j < bytes.length; j++) {
			/* 1077 */int v = bytes[j] & 0xFF;
			/* 1078 */hexChars[(j * 2)] = hexArray[(v / 16)];
			/* 1079 */hexChars[(j * 2 + 1)] = hexArray[(v % 16)];
			/*      */}
		/* 1081 */return new String(hexChars);
		/*      */}

	/*      */
	/*      */public static int comparaFechasDistribuidores(Vector<String> vFechas,
			int indice1, int indice2)
	/*      */throws CAException
	/*      */{
		/* 1087 */String lFecAlta1 = "";
		String lFecAlta2 = "";
		/*      */
		/*      */try
		/*      */{
			/* 1091 */lFecAlta1 = (String) vFechas.elementAt(indice1);
			/*      */
			/* 1093 */int dia1 = Integer.parseInt(lFecAlta1.substring(8, 10));
			/* 1094 */int mes1 = Integer.parseInt(lFecAlta1.substring(5, 7));
			/* 1095 */int anio1 = Integer.parseInt(lFecAlta1.substring(0, 4));
			/*      */
			/* 1097 */lFecAlta2 = (String) vFechas.elementAt(indice2);
			/*      */
			/* 1099 */int dia2 = Integer.parseInt(lFecAlta2.substring(8, 10));
			/* 1100 */int mes2 = Integer.parseInt(lFecAlta2.substring(5, 7));
			/* 1101 */int anio2 = Integer.parseInt(lFecAlta2.substring(0, 4));
			/*      */
			/* 1103 */if (anio1 < anio2)
				/* 1104 */return indice1;
			/* 1105 */if (anio2 < anio1)
				/* 1106 */return indice2;
			/* 1107 */if (mes1 < mes2)
				/* 1108 */return indice1;
			/* 1109 */if (mes2 < mes1)
				/* 1110 */return indice2;
			/* 1111 */if (dia1 < dia2)
				/* 1112 */return indice1;
			/* 1113 */return indice2;
			/*      */}
		/*      */catch (Exception e) {
			/* 1116 */throw new CAException(-1, e.getMessage());
			/*      */}
		/*      */}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */public static long diferenciaDiasCertificado(String _fecAlta)
	/*      */{
		/* 1129 */long lDifDias = 60L;
		/*      */try
		/*      */{
			/* 1132 */Locale currentLocale = new Locale("en", "US");
			/* 1133 */SimpleDateFormat dateFormatAlta = new SimpleDateFormat(
					"ddMMyyyy", currentLocale);
			/*      */
			/* 1135 */Date dFecAlta = dateFormatAlta.parse(_fecAlta);
			/* 1136 */Date dFecHoy = new Date();
			/*      */
			/* 1138 */lDifDias = dFecHoy.getTime() - dFecAlta.getTime();
			/* 1139 */lDifDias /= 86400000L;
			/*      */}
		/*      */catch (Exception e) {
			/* 1142 */e.printStackTrace();
			/*      */}
		/*      */
		/* 1145 */return lDifDias;
		/*      */}
	/*      */
}

/*
 * Location:
 * /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient
 * .jar!/com/claro/util/Utils.class Java compiler version: 6 (50.0) JD-Core
 * Version: 0.7.1
 */