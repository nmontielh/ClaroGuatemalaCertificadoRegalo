/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.mail.Address
 *  javax.mail.Authenticator
 *  javax.mail.BodyPart
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.Multipart
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.MimeBodyPart
 *  javax.mail.internet.MimeMessage
 *  javax.mail.internet.MimeMultipart
 *  org.apache.log4j.Logger
 */
package com.claro.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.claro.catalogo.Catalogo;
import com.claro.exception.CAException;
import com.claro.seguridad.SeguridadCaUtil;
import com.claro.transfer.CatalogoTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.PuntosTO;

public class Utils
extends Constantes {
    protected static final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected static final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");

    public static String setFormatoDecimal(String _decimal) throws CAException {
        try {
            if (_decimal != null) {
                BigDecimal value = new BigDecimal(_decimal);
                value = value.setScale(2, 4);
                return DECIMAL_FORMATTER.format(value);
            }
            return "";
        }
        catch (Exception e) {
            throw new CAException(-1, "No se pudo convertir el valor Decimal en Cadena de texto");
        }
    }

    public static String formatFecha(String valor) {
        if (valor == null) {
            return null;
        }
        if (valor.substring(6, 7).equals("9")) {
            return "19" + valor.substring(6, 8) + "-" + valor.substring(3, 5) + "-" + valor.substring(0, 2);
        }
        return "20" + valor.substring(6, 8) + "-" + valor.substring(3, 5) + "-" + valor.substring(0, 2);
    }

    public static boolean getValEstatusTel(String sEstatus, String sMotivo) {
        boolean bResp = false;
        if (!(sEstatus == null || sMotivo == null || sEstatus.trim().equals("AC") || sEstatus.trim().equals("SU") && (sMotivo.trim().equals("ROEXT") || sMotivo.trim().equals("SUEXT") || sMotivo.trim().equals("SUPET")))) {
            bResp = true;
        }
        return bResp;
    }

    public static int getMesActual(String fecPzoFzo) {
        int inMesActual = 0;
        int inAdYear = Integer.parseInt(fecPzoFzo.substring(0, 4));
        int inAdDay = Integer.parseInt(fecPzoFzo.substring(8, 10));
        int inAdMon = Integer.parseInt(fecPzoFzo.substring(5, 7));
        int inCurYear = Integer.parseInt(Utils.getFechaInsert().substring(6, 10));
        int inCurDay = Integer.parseInt(Utils.getFechaInsert().substring(3, 5));
        int inCurMon = Integer.parseInt(Utils.getFechaInsert().substring(0, 2));
        int inAnioActual = inCurYear - inAdYear;
        inAnioActual = inAnioActual == 1 || inAnioActual == 0 ? 12 : 24;
        inMesActual = inCurYear == inAdYear ? inCurMon - inAdMon : (inCurMon <= inAdMon ? inAnioActual - (inAdMon - inCurMon) : inAnioActual + (inCurMon - inAdMon));
        if (inAdDay > inCurDay) {
            --inMesActual;
        }
        return inMesActual;
    }

    public static String getFechaInsert() {
        Locale currentLocale = new Locale("en", "US");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", currentLocale);
        return dateFormatter.format(new java.util.Date());
    }

    public static String getFechaActual() {
        Locale currentLocale = new Locale("en", "US");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd", currentLocale);
        return dateFormatter.format(new java.util.Date());
    }

    public static int getDiaActual(String fecPzoFzo) {
        boolean inMesActual = false;
        int inDiaActual = 0;
        int idiastrans = 0;
        int inAdYear = Integer.parseInt(fecPzoFzo.substring(0, 4));
        int inAdDay = Integer.parseInt(fecPzoFzo.substring(8, 10));
        int inAdMon = Integer.parseInt(fecPzoFzo.substring(5, 7));
        int inCurYear = Integer.parseInt(Utils.getFechaInsert().substring(6, 10));
        int inCurDay = Integer.parseInt(Utils.getFechaInsert().substring(3, 5));
        int inCurMon = Integer.parseInt(Utils.getFechaInsert().substring(0, 2));
        if (inAdDay <= inCurDay) {
            idiastrans = inDiaActual = inCurDay - inAdDay;
        } else {
            boolean iMesAnt = false;
            int idiasMes = 0;
            switch (inCurMon) {
                case 1: 
                case 3: 
                case 5: 
                case 7: 
                case 8: 
                case 10: 
                case 12: {
                    idiasMes = 31;
                    break;
                }
                case 2: {
                    if (Utils.esBisiesto(String.valueOf(inCurYear))) {
                        idiasMes = 30;
                        break;
                    }
                    idiasMes = 29;
                    break;
                }
                default: {
                    idiasMes = 30;
                }
            }
            idiastrans = idiasMes - inAdDay + inCurDay;
        }
        return idiastrans;
    }

    static boolean esBisiesto(String anioActual) {
        int anio = Integer.parseInt(anioActual);
        if (anio % 4 == 0) {
            return true;
        }
        return false;
    }

    public static int getDiasMes() {
        int idiasMes = 0;
        int inCurYear = Integer.parseInt(Utils.getFechaInsert().substring(6, 10));
        int inCurDay = Integer.parseInt(Utils.getFechaInsert().substring(3, 5));
        int inCurMon = Integer.parseInt(Utils.getFechaInsert().substring(0, 2));
        switch (inCurMon) {
            case 1: 
            case 3: 
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 12: {
                idiasMes = 31;
                break;
            }
            case 2: {
                if (Utils.esBisiesto(String.valueOf(inCurYear))) {
                    idiasMes = 30;
                    break;
                }
                idiasMes = 29;
                break;
            }
            default: {
                idiasMes = 30;
            }
        }
        return idiasMes;
    }

    public static boolean terminoAdendum(String fecAde, String pzo, int region, String sRedencionAntc) {
        int plazo = Integer.parseInt(pzo);
        int anioAde = 0;
        int mesAde = 0;
        int fecVen = 0;
        int anioVence = 0;
        String anioVen = "";
        String mesVen = "";
        String diaVen = "";
        String fechahoy = "";
        int anioV = 0;
        int mesV = 0;
        int diaV = 0;
        int anioA = 0;
        int mesA = 0;
        int diaA = 0;
        boolean resp = false;
        if (plazo == 0) {
            return true;
        }
        anioAde = Integer.parseInt(fecAde.substring(0, 4));
        mesAde = Integer.parseInt(fecAde.substring(5, 7));
        diaVen = fecAde.substring(8, 10);
        anioVen = mesAde == 12 ? Integer.toString((plazo + mesAde - 1) / 12 + anioAde) : Integer.toString((plazo + mesAde) / 12 + anioAde);
        mesVen = Integer.toString((mesAde + plazo) % 12);
        if (mesVen.length() == 1) {
            mesVen = "0" + mesVen;
        }
        if (mesVen.equals("00")) {
            mesVen = "12";
        }
        if (mesVen.equals("12") && mesAde != 12) {
            anioVence = Integer.parseInt(anioVen) - 1;
            anioVen = Integer.toString(anioVence);
        }
        fecVen = Integer.parseInt(String.valueOf(anioVen) + mesVen + diaVen);
        Locale currentLocale = new Locale("en", "US");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd", currentLocale);
        fechahoy = String.valueOf(dateFormatter.format(new java.util.Date()));
        anioV = Integer.parseInt(anioVen);
        mesV = Integer.parseInt(mesVen) - 1;
        diaV = Integer.parseInt(diaVen);
        anioA = Integer.parseInt(fechahoy.substring(0, 4));
        mesA = Integer.parseInt(fechahoy.substring(4, 6)) - 1;
        diaA = Integer.parseInt(fechahoy.substring(6, 8));
        GregorianCalendar fechaven1 = new GregorianCalendar(anioV, mesV, diaV);
        GregorianCalendar fechahoy1 = new GregorianCalendar(anioA, mesA, diaA);
        long difDias = Utils.diferenciaEnDias(fechaven1, fechahoy1);
        resp = region == 9 ? (difDias <= 25 ? true : difDias > 25 && difDias <= 90 && "1".equals(sRedencionAntc)) : (difDias <= 15 ? true : difDias > 15 && difDias <= 180 && "1".equals(sRedencionAntc));
        return resp;
    }

    public static long diferenciaEnDias(Calendar cal1, Calendar cal2) {
        long diferencia = 0;
        diferencia = cal1.getTime().getTime() - cal2.getTime().getTime();
        return diferencia/=86400000;
    }

    public static boolean getValEstatusCobranza(String sEstatusCob, int nReg, String sBoton, String sMotivo, String sStatusTel) {
        boolean bResp = false;
        if (sBoton != null && sStatusTel != null && sStatusTel.equals("AN")) {
            if (sBoton.trim().equals("Saldos")) {
                bResp = true;
            } else if (sBoton.trim().equals("Reden")) {
                return false;
            }
        }
        if (sEstatusCob != null && sBoton != null && (sEstatusCob.trim().equals("BH") || sEstatusCob.trim().equals("BN") || sMotivo.trim().equals("ROEXT") || sMotivo.trim().equals("SUEXT") || sMotivo.trim().equals("SUPET"))) {
            bResp = true;
        }
        if (sBoton.trim().equals("Saldos")) {
            if (sEstatusCob.trim().equals("EM") || sEstatusCob.trim().equals("HL") || sEstatusCob.trim().equals("SP") || sEstatusCob.trim().equals("SM") || sEstatusCob.trim().equals("RE") || sEstatusCob.trim().equals("ET") || sEstatusCob.trim().equals("SL") || sEstatusCob.trim().equals("ST")) {
                bResp = true;
            } else if (nReg != 9 && sEstatusCob.trim().equals("AR")) {
                bResp = true;
            } else if ((sEstatusCob.trim().equals("SU") || sEstatusCob.trim().equals("FU")) && nReg == 9) {
                bResp = true;
            }
        }
        if (sBoton.trim().equals("Saldos") && nReg != 9 && (sEstatusCob.trim().equals("EM") || sEstatusCob.trim().equals("HL") || sEstatusCob.trim().equals("SP") || sEstatusCob.trim().equals("AR") || sEstatusCob.trim().equals("ET") || sEstatusCob.trim().equals("RE") || sEstatusCob.trim().equals("SL") || sEstatusCob.trim().equals("SM") || sEstatusCob.trim().equals("ST"))) {
            bResp = true;
        }
        return bResp;
    }

    public static boolean ValidaCredito(String sClase, int region, PerfilTO perfilTO) {
        boolean tienePrivilegios = true;
        if ((sClase != null || perfilTO != null) && sClase.trim().equals("AR")) {
            tienePrivilegios = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "60");
        }
        return tienePrivilegios;
    }

    public static boolean defineIva(String tipoReden, PerfilTO perfilTO) {
        boolean resp = false;
        if (tipoReden.equals("SIN")) {
            resp = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "138");
        }
        if (tipoReden.equals("ACA")) {
            resp = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "143");
        }
        if (tipoReden.equals("CON")) {
            resp = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "121");
        }
        if (tipoReden.equals("CAREG")) {
            resp = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "125");
        }
        if (tipoReden.equals("T3G")) {
            resp = SeguridadCaUtil.getInstance().validaPerfilProcesoCa(perfilTO, "147");
        }
        return resp;
    }

    public static int comparaFechas(ArrayList<String> vFechas, int indice1, int indice2) {
        String lFecAlta1 = "";
        String lFecAlta2 = "";
        try {
            lFecAlta1 = vFechas.get(indice1);
            int dia1 = Integer.parseInt(lFecAlta1.substring(8, 10));
            int mes1 = Integer.parseInt(lFecAlta1.substring(5, 7));
            int anio1 = Integer.parseInt(lFecAlta1.substring(0, 4));
            lFecAlta2 = vFechas.get(indice2);
            int dia2 = Integer.parseInt(lFecAlta2.substring(8, 10));
            int mes2 = Integer.parseInt(lFecAlta2.substring(5, 7));
            int anio2 = Integer.parseInt(lFecAlta2.substring(0, 4));
            if (anio1 < anio2) {
                return indice1;
            }
            if (anio2 < anio1) {
                return indice2;
            }
            if (mes1 < mes2) {
                return indice1;
            }
            if (mes2 < mes1) {
                return indice2;
            }
            if (dia1 < dia2) {
                return indice1;
            }
            return indice2;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public static String validaResultSet(ResultSet resultSet, String campo, Class tipo) throws CAException {
        String valor = "";
        try {
            if (String.class == tipo && resultSet.getString(campo) != null) {
                valor = resultSet.getString(campo);
            }
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.validaResultSet [" + e.toString() + "]", e);
        }
        catch (Exception e) {
            throw new CAException(-1, "ConsultasDAO.validaResultSet[" + e.toString() + "]", e);
        }
        return valor;
    }

    public static int getMesFecha(String fvenc2, String freden) {
        int inMesActual = 0;
        int inYearvenc2 = Integer.parseInt(fvenc2.substring(0, 4));
        int inAdYear = Integer.parseInt(freden.substring(0, 4));
        int inMonvenc2 = Integer.parseInt(fvenc2.substring(5, 7));
        int inAdMon = Integer.parseInt(freden.substring(5, 7));
        int inDayvenc2 = Integer.parseInt(fvenc2.substring(8, 10));
        int inAdDay = Integer.parseInt(freden.substring(8, 10));
        int inAnioActual = inAdYear - inYearvenc2;
        int intAnioTot = 0;
        intAnioTot = inAnioActual == 1 || inAnioActual == 0 ? 12 : inAnioActual * 12;
        inMesActual = inYearvenc2 == inAdYear ? inAdMon - inMonvenc2 : (inAdMon <= inMonvenc2 ? intAnioTot - (inMonvenc2 - inAdMon) : intAnioTot + (inAdMon - inMonvenc2));
        if (inDayvenc2 > inAdDay) {
            --inMesActual;
        }
        return inMesActual;
    }

    public static String nombreMesfecha(java.util.Date sFecha) {
        int nMes = Integer.parseInt(sFecha.toString().substring(5, 7));
        String sMes = "";
        switch (nMes) {
            case 1: {
                sMes = "Enero";
                break;
            }
            case 2: {
                sMes = "Febrero";
                break;
            }
            case 3: {
                sMes = "Marzo";
                break;
            }
            case 4: {
                sMes = "Abril";
                break;
            }
            case 5: {
                sMes = "Mayo";
                break;
            }
            case 6: {
                sMes = "Junio";
                break;
            }
            case 7: {
                sMes = "Julio";
                break;
            }
            case 8: {
                sMes = "Agosto";
                break;
            }
            case 9: {
                sMes = "Septiembre";
                break;
            }
            case 10: {
                sMes = "Octubre";
                break;
            }
            case 11: {
                sMes = "Noviembre";
                break;
            }
            case 12: {
                sMes = "Diciembre";
            }
        }
        return sMes;
    }

    public static String generaFolio(String telefono) {
        String folio = "";
        folio = String.valueOf(Constantes.yyyyMMddHHmmss.format(new java.util.Date()).substring(2, 13)) + telefono.substring(2, 10);
        return folio;
    }

    public static boolean getValEstatusCobranza(String sEstatus, int nReg, String sBoton, String sMotivo) {
        boolean bResp = false;
        if (sEstatus != null && sBoton != null && (sEstatus.trim().equals("BH") || sEstatus.trim().equals("BN") || sMotivo.trim().equals("ROEXT") || sMotivo.trim().equals("SUEXT") || sMotivo.trim().equals("SUPET"))) {
            bResp = true;
        }
        if (sBoton.trim().equals("Saldos") && nReg != 9 && (sEstatus.trim().equals("EM") || sEstatus.trim().equals("HL") || sEstatus.trim().equals("SP") || sEstatus.trim().equals("AR") || sEstatus.trim().equals("ET") || sEstatus.trim().equals("RE") || sEstatus.trim().equals("SL") || sEstatus.trim().equals("SM") || sEstatus.trim().equals("ST"))) {
            bResp = true;
        }
        return bResp;
    }

    public static String validaTipoRedencion(String tipoRed) {
        if ("CON".equals(tipoRed) || tipoRed.equals("CAREG")) {
            return "C";
        }
        if ("SIN".equals(tipoRed)) {
            return "S";
        }
        if ("TAIR".equals(tipoRed)) {
            return "T";
        }
        if ("T3G".equals(tipoRed)) {
            return "G";
        }
        if ("ACA".equals(tipoRed)) {
            return "A";
        }
        return null;
    }

    public static long calcularDiasEntreFechas(long fecha1, long fecha2) {
        long diff = fecha2 - fecha1;
        long dias = diff / 86400000;
        return dias;
    }

    public static String setFormatoPtos(int puntos) {
        try {
            return formatea.format(puntos);
        }
        catch (Exception e) {
            return Integer.toString(puntos);
        }
    }

    public static String anexarCeros(int longi_total, String str) {
        if (longi_total <= str.length()) {
            return str.substring(str.length() - longi_total);
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 1; i <= longi_total - str.length(); ++i) {
            buf.append("0");
        }
        buf.append(str);
        return buf.toString();
    }

    public static boolean validaFechaActivacion(String fechaAltaM2K) {
        try {
            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaAlta = Calendar.getInstance();
            fechaAlta.setTime(Constantes.DATEFORMATyyyy_MM_dd.parse(fechaAltaM2K));
            if (fechaAlta.get(2) == fechaActual.get(2) - 1 && fechaAlta.get(1) == fechaActual.get(1) - 1 || fechaAlta.get(2) == fechaActual.get(2) && fechaAlta.get(1) == fechaActual.get(1)) {
                return false;
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static java.util.Date fechaExpiracion(boolean isDistribuidores) {
        Calendar fechaExp = Calendar.getInstance();
        if (isDistribuidores) {
            fechaExp.add(5, 7);
        } else {
            fechaExp.add(5, 1);
        }
        return fechaExp.getTime();
    }

    public static int limpiaFormatoDecimal(String lValorConFo) {
        double res = 0.0;
        String sValorSinFo = "";
        for (int i = 0; i < lValorConFo.length(); ++i) {
            char c = lValorConFo.charAt(i);
            switch (c) {
                case '0': 
                case '1': 
                case '2': 
                case '3': 
                case '4': 
                case '5': 
                case '6': 
                case '7': 
                case '8': 
                case '9': {
                    sValorSinFo = String.valueOf(sValorSinFo) + c;
                    break;
                }
            }
        }
        return Integer.parseInt(sValorSinFo);
    }

    public static String generaFolioAlianza(String telefono) {
        String folio = "";
        Locale currentLocale = new Locale("en", "US");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmmss", currentLocale);
        folio = String.valueOf(dateFormatter.format(new java.util.Date()).substring(3, 14)) + telefono.substring(2, 10);
        return folio;
    }

    public static PuntosRedimidosTO regresaPuntos(PuntosTO puntosTO, PuntosRedimidosTO puntosRedimidosTO) {
        PuntosRedimidosTO redimidosTO = new PuntosRedimidosTO();
        redimidosTO.setPtsRenta(puntosTO.getPtsRenta() + puntosRedimidosTO.getPtsRenta());
        redimidosTO.setPtsExcedentes(puntosTO.getPtsExcedentes() + puntosRedimidosTO.getPtsExcedentesRedimidos() - puntosRedimidosTO.getPtsSobrantes1());
        redimidosTO.setPtsPorVencer(puntosTO.getPtsPorVencer() + puntosRedimidosTO.getPtsPorVencer());
        redimidosTO.setPtsPorVencer1(puntosTO.getPtsPorVencer1() + puntosRedimidosTO.getPtsPorVencer1());
        redimidosTO.setPtsPorVencer2(puntosTO.getPtsPorVencer2() + puntosRedimidosTO.getPtsPorVencer2());
        redimidosTO.setPtsPromocion(puntosTO.getPtsPromocion() + puntosRedimidosTO.getPtsPromocionRedimidos());
        redimidosTO.setPtsAntiguedad(puntosTO.getPtsAntiguedad() + puntosRedimidosTO.getPtsPorAntiguedadRedimidos());
        redimidosTO.setPtsRedimidos(puntosTO.getPtsRedimidos() - puntosRedimidosTO.getPtsRedimidos());
        redimidosTO.setFecVencer(puntosRedimidosTO.getFecVencer());
        redimidosTO.setFecVencer1(puntosRedimidosTO.getFecVencer1());
        redimidosTO.setFecVencer2(puntosRedimidosTO.getFecVencer2());
        redimidosTO.setBonoEquipo(puntosTO.getBonoEquipo() + puntosRedimidosTO.getPtsSobrantes());
        redimidosTO.setBBono(puntosTO.getBBono());
        return redimidosTO;
    }

    public static PuntosRedimidosTO canjePuntos(PuntosTO puntosTO, int valorPuntos, int rubros) {
        PuntosRedimidosTO redimidosTO = new PuntosRedimidosTO(puntosTO);
        if (rubros <= 7 && puntosTO.getPtsPorVencer() > 0) {
            if (puntosTO.getPtsPorVencer() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsPorVencer();
                redimidosTO.setPtsPorVencerRedimidos(puntosTO.getPtsPorVencer());
                redimidosTO.setPtsPorVencer(0);
            } else {
                redimidosTO.setPtsPorVencer(puntosTO.getPtsPorVencer() - valorPuntos);
                redimidosTO.setPtsPorVencerRedimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros <= 7 && puntosTO.getPtsPorVencer1() > 0) {
            if (puntosTO.getPtsPorVencer1() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsPorVencer1();
                redimidosTO.setPtsPorVencer1Redimidos(puntosTO.getPtsPorVencer1());
                redimidosTO.setPtsPorVencer1(0);
            } else {
                redimidosTO.setPtsPorVencer1(puntosTO.getPtsPorVencer1() - valorPuntos);
                redimidosTO.setPtsPorVencer1Redimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros <= 7 && puntosTO.getPtsPorVencer2() > 0) {
            if (puntosTO.getPtsPorVencer2() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsPorVencer2();
                redimidosTO.setPtsPorVencer2Redimidos(puntosTO.getPtsPorVencer2());
                redimidosTO.setPtsPorVencer2(0);
            } else {
                redimidosTO.setPtsPorVencer2(puntosTO.getPtsPorVencer2() - valorPuntos);
                redimidosTO.setPtsPorVencer2Redimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros <= 7 && puntosTO.getPtsPromocion() > 0) {
            if (puntosTO.getPtsPromocion() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsPromocion();
                redimidosTO.setPtsPromocionRedimidos(puntosTO.getPtsPromocion());
                redimidosTO.setPtsPromocion(0);
            } else {
                redimidosTO.setPtsPromocion(puntosTO.getPtsPromocion() - valorPuntos);
                redimidosTO.setPtsPromocionRedimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros <= 7 && puntosTO.getPtsAntiguedad() > 0) {
            if (puntosTO.getPtsAntiguedad() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsAntiguedad();
                redimidosTO.setPtsPorAntiguedadRedimidos(puntosTO.getPtsAntiguedad());
                redimidosTO.setPtsAntiguedad(0);
            } else {
                redimidosTO.setPtsAntiguedad(puntosTO.getPtsAntiguedad() - valorPuntos);
                redimidosTO.setPtsPorAntiguedadRedimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros <= 7 && puntosTO.getPtsExcedentes() > 0) {
            if (puntosTO.getPtsExcedentes() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsExcedentes();
                redimidosTO.setPtsExcedentesRedimidos(puntosTO.getPtsExcedentes());
                redimidosTO.setPtsExcedentes(0);
            } else {
                redimidosTO.setPtsExcedentes(puntosTO.getPtsExcedentes() - valorPuntos);
                redimidosTO.setPtsExcedentesRedimidos(valorPuntos);
                return redimidosTO;
            }
        }
        if (rubros == 7 && puntosTO.getPtsRenta() > 0) {
            if (puntosTO.getPtsRenta() <= valorPuntos) {
                valorPuntos-=puntosTO.getPtsRenta();
                redimidosTO.setPtsRentaRedimidos(puntosTO.getPtsRenta());
                redimidosTO.setPtsRenta(0);
            } else {
                redimidosTO.setPtsRenta(puntosTO.getPtsRenta() - valorPuntos);
                redimidosTO.setPtsRentaRedimidos(valorPuntos);
                return redimidosTO;
            }
        }
        return redimidosTO;
    }

    public static long diferenciaDiasTransf(String _fecAlta) {
        long lDifDias = 60;
        try {
            Locale currentLocale = new Locale("en", "US");
            SimpleDateFormat dateFormatAlta = new SimpleDateFormat("yyyy-MM-dd", currentLocale);
            java.util.Date dFecAlta = dateFormatAlta.parse(_fecAlta);
            java.util.Date dFecHoy = new java.util.Date();
            lDifDias = dFecHoy.getTime() - dFecAlta.getTime();
            lDifDias/=86400000;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lDifDias;
    }

    public static int obtienePuntosPorAntiguedad(int aniosAntig) {
        int puntosAntig = 0;
        if (aniosAntig <= 0) {
            return puntosAntig;
        }
        if (aniosAntig > 0 && aniosAntig <= 3) {
            puntosAntig = aniosAntig * 1000;
            return puntosAntig;
        }
        if (aniosAntig > 3 && aniosAntig < 16) {
            for (int i = 0; i < 3; ++i) {
                if ((aniosAntig - (3 + i + 1)) % 3 != 0) continue;
                puntosAntig = (aniosAntig - i) * 1000;
            }
        } else {
            puntosAntig = 16000;
            return 16000;
        }
        return puntosAntig;
    }

    public static boolean validaFechaM2K(String fechaAltaM2K) {
        boolean valor = true;
        if (fechaAltaM2K.length() >= 10) {
            if (fechaAltaM2K.charAt(4) != '-' || fechaAltaM2K.charAt(7) != '-') {
                valor = false;
            }
        } else {
            valor = false;
        }
        return valor;
    }

    public static String setFormatoDecimalPrecio(String _decimal) throws CAException {
        try {
            if (_decimal != null) {
                BigDecimal value = new BigDecimal(_decimal);
                value = value.setScale(2, 4);
                return DECIMAL_FORMATTER_PRECIOS.format(value);
            }
            return "";
        }
        catch (Exception e) {
            throw new CAException(-1, "No se pudo convertir el valor Decimal en Cadena de texto");
        }
    }

    public static String generaFolio() {
        String folio = "";
        Locale currentLocale = new Locale("en", "US");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmmss", currentLocale);
        folio = dateFormatter.format(new java.util.Date()).substring(2, 14);
        return folio;
    }

    public static int calculaAntiguedad(java.util.Date lFecAlta) {
        int opcRes1 = 0;
        int opcRes2 = 0;
        String sFecAltaM2K = Constantes.DATEFORMATyyyy_MM_dd.format(lFecAlta);
        int diaAlta = Integer.parseInt(sFecAltaM2K.substring(8, 10));
        int mesAlta = Integer.parseInt(sFecAltaM2K.substring(5, 7));
        int anioAlta = Integer.parseInt(sFecAltaM2K.substring(0, 4));
        String hoy = Utils.getFechaInsert();
        int diaHoy = Integer.parseInt(hoy.substring(3, 5));
        int mesHoy = Integer.parseInt(hoy.substring(0, 2));
        int anioHoy = Integer.parseInt(hoy.substring(6, 10));
        opcRes1 = anioHoy - anioAlta;
        opcRes2 = anioHoy - anioAlta - 1;
        if (mesHoy > mesAlta) {
            return opcRes1;
        }
        if (mesHoy < mesAlta) {
            return opcRes2;
        }
        if (diaHoy > diaAlta) {
            return opcRes1;
        }
        if (diaHoy < diaAlta) {
            return opcRes2;
        }
        return opcRes1;
    }

    public static int calculaPuntos(String lFecAlta) {
        int nResult = 0;
        try {
            java.util.Date fechaAlta = new java.util.Date(Constantes.DATEFORMATyyyy_MM_dd.parse(lFecAlta).getTime());
            GregorianCalendar calendario = new GregorianCalendar();
            calendario.setTime(fechaAlta);
            calendario.add(5, -10);
            int anios = Utils.calculaAntiguedad(calendario.getTime());
            nResult = Utils.obtienePuntosPorAntiguedad(anios);
            return nResult;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public static boolean enviaCorreo(Map<String, String> parametrosCorreo, String subject, String mensaje, List<String> archivos) {
        File file = null;
        BufferedReader reader = null;
        try {
            try {
                logger.info((Object)"Inicio: Enviar Correo");
                Catalogo properties = new Catalogo();
                properties.setTabla("propiedades");
                properties.cargaCatalogo();
                String adress = new String();
                String from = parametrosCorreo.get("emisor");
                adress = parametrosCorreo.get("mailTo");
                String host = properties.getPropiedad("correoHost");
                Properties prop = System.getProperties();
                prop.put(properties.getPropiedad("correoSmtp"), host);
                Session ses1 = Session.getInstance((Properties)prop, (Authenticator)null);
                MimeMessage msg = new MimeMessage(ses1);
                msg.setFrom((Address)new InternetAddress(from));
                msg.addRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse((String)adress, (boolean)false));
                msg.setSubject(subject);
                MimeMultipart mPart = new MimeMultipart();
                MimeBodyPart msgBP = new MimeBodyPart();
                msgBP.setText(mensaje);
                mPart.addBodyPart((BodyPart)msgBP);
                if (archivos != null) {
                    for (String nombreArchivo : archivos) {
                        file = new File(nombreArchivo);
                        if (file != null) {
                            reader = new BufferedReader(new FileReader(file));
                            StringBuffer buffer = new StringBuffer();
                            String linea = null;
                            while ((linea = reader.readLine()) != null) {
                                buffer.append(linea).append("\n");
                            }
                            if (buffer.length() > 0) {
                                msgBP = new MimeBodyPart();
                                msgBP.setFileName(nombreArchivo);
                                msgBP.setText(buffer.toString());
                                mPart.addBodyPart((BodyPart)msgBP);
                            }
                        }
                        if (reader != null) {
                            reader.close();
                        }
                        if (file == null) continue;
                        file.delete();
                    }
                }
                msg.setContent((Multipart)mPart);
                Transport.send((Message)msg);
                logger.info((Object)"Termino: Enviar Correo");
            }
            catch (Exception e) {
                e.printStackTrace();
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (IOException var20_20) {
                        // empty catch block
                    }
                }
                if (file != null) {
                    file.delete();
                }
            }
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var20_22) {}
            }
            if (file != null) {
                file.delete();
            }
        }
        return true;
    }

    public static String formatoIdPromocionEP(int maxSize, int idPromocion) {
        StringBuffer idPromocionEP = new StringBuffer();
        String idPromo = String.valueOf(idPromocion);
        if (idPromo.length() < maxSize) {
            for (int i = 0; i < maxSize - idPromo.length(); ++i) {
                idPromocionEP.append("0");
            }
            idPromocionEP.append(idPromo);
        }
        return idPromocionEP.toString();
    }

    public static String obtieneValorPropiedad(ArrayList<CatalogoTO> lstCatalogoTO, String propiedad) {
        String result = "";
        CatalogoTO catalogoTO = null;
        if (lstCatalogoTO != null) {
            ListIterator<CatalogoTO> listIterator = lstCatalogoTO.listIterator();
            while (listIterator.hasNext()) {
                catalogoTO = listIterator.next();
                if (catalogoTO.getIdVariable() == null || !propiedad.equals(catalogoTO.getIdVariable())) continue;
                result = catalogoTO.getValor();
            }
        }
        return result;
    }

    public static String getMD5Encrypted(String e) {
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
        mdEnc.update(e.trim().getBytes());
        String hash = Utils.toHexString(mdEnc.digest());
        return new String(hash);
    }

    public static String toHexString(byte[] bytes) {
        char[] hexArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v / 16];
            hexChars[j * 2 + 1] = hexArray[v % 16];
        }
        return new String(hexChars);
    }

    public static int comparaFechasDistribuidores(Vector<String> vFechas, int indice1, int indice2) throws CAException {
        String lFecAlta1 = "";
        String lFecAlta2 = "";
        try {
            lFecAlta1 = vFechas.elementAt(indice1);
            int dia1 = Integer.parseInt(lFecAlta1.substring(8, 10));
            int mes1 = Integer.parseInt(lFecAlta1.substring(5, 7));
            int anio1 = Integer.parseInt(lFecAlta1.substring(0, 4));
            lFecAlta2 = vFechas.elementAt(indice2);
            int dia2 = Integer.parseInt(lFecAlta2.substring(8, 10));
            int mes2 = Integer.parseInt(lFecAlta2.substring(5, 7));
            int anio2 = Integer.parseInt(lFecAlta2.substring(0, 4));
            if (anio1 < anio2) {
                return indice1;
            }
            if (anio2 < anio1) {
                return indice2;
            }
            if (mes1 < mes2) {
                return indice1;
            }
            if (mes2 < mes1) {
                return indice2;
            }
            if (dia1 < dia2) {
                return indice1;
            }
            return indice2;
        }
        catch (Exception e) {
            throw new CAException(-1, e.getMessage());
        }
    }

    public static long diferenciaDiasCertificado(String _fecAlta) {
        long lDifDias = 60;
        try {
            Locale currentLocale = new Locale("en", "US");
            SimpleDateFormat dateFormatAlta = new SimpleDateFormat("ddMMyyyy", currentLocale);
            java.util.Date dFecAlta = dateFormatAlta.parse(_fecAlta);
            java.util.Date dFecHoy = new java.util.Date();
            lDifDias = dFecHoy.getTime() - dFecAlta.getTime();
            lDifDias/=86400000;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lDifDias;
    }
}

