/*
 * Decompiled with CFR 0_102.
 */
package com.claro.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constantes {
    static Locale currentLocale = new Locale("en", "US");
    public static final SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("hh:mm:ss", currentLocale);
    public static final SimpleDateFormat DATEFORMATyyyyMMdd = new SimpleDateFormat("yyyyMMdd", currentLocale);
    public static final SimpleDateFormat DATEFORMATyyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd", currentLocale);
    public static final SimpleDateFormat DATEFORMATdd_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy", currentLocale);
    public static final SimpleDateFormat DATEFORMATMM_dd_YYYY = new SimpleDateFormat("MM/dd/yyyy", currentLocale);
    public static final SimpleDateFormat DATEFORMATdd_MM_YYYY_2 = new SimpleDateFormat("dd/MM/yyyy", currentLocale);
    public static final SimpleDateFormat DATEFORMATMM_dd_YYYY_HHmmss = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", currentLocale);
    public static final SimpleDateFormat DATEFORMTALog = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", currentLocale);
    public static final SimpleDateFormat DATEFORMTAyyyy_MM_ddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", currentLocale);
    public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss", currentLocale);
    public static DecimalFormat formatea = new DecimalFormat("###,###,###,##0");
    public static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###.00");
    public static final DecimalFormat DECIMAL_FORMATTER_PRECIOS = new DecimalFormat("###,###,###,###.00");
    public static final int ID_ALIANZA_MILLAS = 1;
    public static final double CONSTOPUNTO = 0.021;
    public static final int MSG_ERROR = -1;
    public static final int MSG_EXITOSO = 0;
    public static final int MSG_FIN_PROCESO = 1;
    public static final int MSG_INFORMATIVO = 2;
    public static final int PANTALLA_CP = 2;
    public static final int GRUPO_T3G_R9 = 1347;
    public static final int GRUPO_T3G_R1 = 1346;
    public static final int GRUPO_ACHIP_R9 = 1178;
    public static final int GRUPO_ACHIP_R1 = 1084;
    public static final int GRUPO_AKIT_R1 = 1082;
    public static final int GRUPO_AKIT_R9 = 1048;
    public static final int GRUPO_CDTM = 1085;
    public static final int GRUPO_TAIRE = 1087;
    public static final int GRUPO_AMEX = 1086;
    public static final String TPRODUCTO_TARIFARIO = "E";
    public static final String TPRODUCTO_AKIT = "E";
    public static final String TPRODUCTO_T3G = "G";
    public static final String TPRODUCTO_CDTM = "M";
    public static final String TPRODUCTO_ACHIP = "S";
    public static final String TPPROMOCION_ACHIP = "AP";
    public static final String TPRODUCTO_TAIRE = "T";
    public static final String TPRODUCTO_AMEX = "X";
    public static final String TPRODUCTO_SIMCARD = "P";
    public static final String TPRED_CONFIRMA = "C";
    public static final String TPRED_AMIGOKIT = "S";
    public static final String TPRED_AMIGOCHIP = "A";
    public static final String TPRED_T3G = "G";
    public static final String TIPORED_CONFIRMA = "CON";
    public static final String TIPORED_AMIGOKIT = "SIN";
    public static final String TIPORED_AMIGOCHIP = "ACA";
    public static final String TIPORED_T3G = "T3G";
    public static final String TIPORED_CAREG = "CAREG";
    public static final String FORMARED_PUNTOSMINIMOS = "PM";
    public static final String FORMARED_PRORRATEO = "PR";
    public static final String FORMARED_CAREG = "PC";
    public static final String FORMARED_PUNTOSDISPONIBLES = "PD";
    public static final String CAC_FUERZAVENTA = "TELCEL";
    public static final String TODOS_FUERZAVENTA = "TODOS";
    public static final String USUARIO_PROCESOS = "VIBPT01";
    public static final String BANDERA_S = "S";
    public static final String BANDERA_N = "N";
    public static final String BANDERA_SI_CA = "SI";
    public static final String BANDERA_NO_CA = "NO";
    public static final String INFO_VALORACION = "SolicitudInformacionValoracion";
    public static final String INFO_PROMOCION = "SolicitudInformacionPromocion";
    public static final String CODIGO_ACEPTAPROMOCIONCA = "PromocionAceptadaCA";
    public static final String CONTADOR_PROMOCIONES_ROJO = "0";
    public static final String CONTADOR_PROMOCIONES_AMARILLO = "1";
    public static final String CONTADOR_PROMOCIONES_VERDE = "2";
    public static final String DESCRIPCION_ENDPOINT_GAP = "ENDPOINT.WS.GAP";
    public static final int ID_PROMOCIONACEPTADA = 1;
    public static final int ID_PROMOCIONRECHAZADA = 2;
    public static final int NO_ENVIADO_SOCKET = 5;
    public static final int ENVIADO_SOCKET = 6;
    public static final int ENVIADO_EP = 7;
    public static final int ID_MOV_RENUNCIAPTOS = 49;
    public static final int ID_MOV_REACTIVAPTOS = 48;
    public static final int ID_MOV_REDENCION = 5;
    public static final int ID_MOV_PTSSOBRANTES = 52;
    public static final int ID_MOV_ELIMINAPTSSOBRANTES = 53;
    public static final int ID_MOV_INSERTAMEMBRESIA = 37;
    public static final int ID_MOV_CANCELACION = 11;
    public static final int ID_MOV_CANCELAALIANZA = 20;
    public static final int ID_MOV_AJUSTE = 18;
    public static final int ID_NEUTRO = 0;
    public static final String ID_ESTATUS_LIBRE = "0";
    public static final String ID_ESTATUS_CONGELADOS = "C";
    public static final int ID_MOV_ASIGNAPTOS = 15;
    public static final int ID_MOV_ELIMINAPTOS = 18;
    public static final String ESTATUS_LINEA_RESERVADO = "R";
    public static final String ESTATUS_LINEA_ACTIVA = "AC";
    public static final String ESTATUS_LINEA_CANCELADA = "AN";
    public static final String ESTATUS_LINEA_SUSPENDIDA = "SU";
    public static final String C = "CON FIRMA DE ADDENDUM";
    public static final String SFA = "SIN FIRMA DE ADDENDUM";
    public static final int AMERICAN_EXPRES = 2;
    public static final String REDEN_ESTATUS_INACTIVO = "I";
    public static final String CONST_ESTATUS_INACTIVO = "I";
    public static final String ESTATUS_ACTIVO = "A";
    public static final String DESC_REGION = "R0";
    public static final String DEFAULT_ANACR = "0.9";
    public static final String TRANSFERENCIA_PUNTOS_ANEXO = "ANEXO";
    public static final String TRANSFERENCIA_PUNTOS_REGION = "REGION";
    public static final String TRANSFERENCIA_PUNTOS_EXCELENTE = "EXCELENTE";
    public static final int ID_MOV_TRANSF_PTOS = 13;
    public static final int DETALLE_NUMPTOS = 0;
    public static final int DETALLE_NUMPTOS_EXCE = 0;
    public static final int ID_MOV_CAREG = 16;
    public static final int ID_MOV_FMENS = 2;
    public static final int ID_MOV_CARIN = 1;
    public static final int ID_MOV_ASANT = 24;
    public static final int ID_MOV_TRANSF_PTOSCTE_EXCE = 56;
    public static final int ID_MOV_CANCELA_TRANSF_PTOS = 64;
    public static final String CANCELA_CARGA_PTS = "Cancelacion Carga Inicial por Traspaso de Puntos CAREG";
    public static final int dias_180 = 180;
    public static final int dias_60 = 60;
    public static final int dias_30 = 30;
    public static final int dias_90 = 90;
    public static final int ID_MOV_ALIANZA_CANJEAMEXMEX = 19;
    public static final int PUNTOS_ANTIG_POR_ANIO = 1000;
    public static final int BLOQUE_ANIOS_ANTIG = 3;
    public static final int ANIOS_ANTIG_MAXIMO = 16;
    public static int ELIMINAR_PUNTOS = 1;
    public static int ASIGNAR_PUNTOS = 2;
    public static final String DESCRIPCION_CORPORATIVO = "CORPORATIVO";
    public static final String ESTATUSCARTA_PROCESO = "P";
    public static final String ESTATUSCARTA_CANCEL = "O";
    public static final String REPORTE_REDENCIONES = "1";
    public static final String REPORTE_ACUMULADOS = "2";
    public static final String REPORTE_CERTIFICADOS_LEALTAD = "3";
    public static final String REPORTE_RETENCIONES = "4";
    public static final String REPORTE_ROEXT = "5";
    public static final String REPORTE_REDEN_LINEA = "6";
    public static final String REPORTE_ALIANZAS = "7";
    public static final String REPORTE_ALTO_VALOR = "8";
    public static final String REPORTE_COMISIONES = "9";
    public static final String REPORTE_PUNTOS_POR_VENCER = "10";
    public static final String REPORTE_REDENCIONES_DETALLE = "11";
    public static final String REPORTE_REDENCIONES_ONLINE = "12";
    public static final String REPORTE_INBURSA_RENTA = "13";
    public static final String REPORTE_INBURSA_MINUTOS = "14";
    public static final String REPORTE_INBURSA_DESC_1000 = "15";
    public static final String REPORTE_INBURSA_PAQUETES = "16";
    public static final String REPORTE_ALIANZAS_MEXICANA = "MEXICANA";
    public static final String REPORTE_ALIANZAS_AMEX = "AMEX";
    public static final String REPORTE_ALIANZAS_REDENCIONES_ENTREGA = "REDENCIONES_ENTREGA";
    public static final String REPORTE_ALIANZAS_REDENCIONES_AMIGOKIT = "REDENCIONES_AMIGOKIT";
    public static final String REPORTE_ALIANZAS_REDENCIONES_TIEMPOAIRE = "REDENCIONES_TIEMPOAIRE";
    public static final String REPORTE_ALIANZAS_REDENCIONES_ACTIVACIONES = "REDENCIONES_ACTIVACIONES";
    public static final String REPORTE_ALIANZAS_REDENCIONES_TARJETAS_3G = "REDENCIONES_TARJETAS_3G";
    public static final String DESC_CON = "CAMCA";
    public static final String DESC_CAREG = "CAREG";
    public static final String DESC_TAIR = "TIEMPO AIRE";
    public static final String DESC_SIN = "AMIGO KIT";
    public static final String DESC_ACA = "AMIGO CHIP";
    public static final String DESC_T3G = "TARJETA 3G";
    public static final String DESC_REDENCIONONLINE = "VIA ONLINE";
    public static final String SERVICIO_REDENCION_ONLINE = "RONLINE";
    public static final String IP_SMS = "10.203.15.172";
    public static final String PORTAL_CA = "P";
    public static final String SERVICIO_SMS = "S";
    public static final String PROMOCION_TA_ONLINE = "TA";
    public static final String PROMOCION_SMS_ONLINE = "SM";
    public static final int ESTATUS_AREAPROMO_ACTIVA = 1;
    public static final String APLICA_MULTICOTIZADOR = "ca.rendencion.multicotizador";
    public static final String PLANES_SISACT_BAN_SISACT = "S";
    public static final String PLANES_SISACT_TIPO_PLAN = "MASIVO";
    public static final String REPLICA_PROMO_DEUR_ESTATUS_TRANSFIRIENDO_ARCHIVO = "T";
    public static final String REPLICA_PROMO_DEUR_ERROR_TRANSFERIR_ARCHIVO = "ET";
    public static final String REPLICA_PROMO_REGION_CARGA_DEUR = "DEUR";
    public static final String REPLICA_PROMO_REGION_CARGA_R9 = "R9";
    public static final String REPLICA_PROMO_R1R8_FTP_ESTATUS_TRANSFIRIENDO_ARCHIVO = "T";
    public static final String REPLICA_PROMO_R1R8_FTP_ERROR_TRANSFERIR_ARCHIVO = "E";
    public static final String REPLICA_PROMO_R1R8_FTP_ACCION_ALTA = "A";
    public static final String REPLICA_PROMO_R1R8_FTP_ACCION_ACTUALIZACION = "U";
    public static final String REPLICA_PROMO_R1R8_FTP_ACCION_BAJA = "B";
    public static final String USR_SISACT = "SISACT";
    public static final String CARACTER_PIPE = "|";
    public static final int IDPROCESOCA_ASIG_GRAL = 25;
    public static final int IDPROCESOCA_ASIG_PUNTOS = 71;
    public static final int ASIGNACIONES_MAX_R9 = 1;
    public static final int ASIGNACIONES_MAX_DEUR = 3;
    public static final String INBURSAALTA = "A";
    public static final String INBURSABAJA = "B";
    public static final String INBURSANOAPLICA = "N";
    public static final String INBURSA_RTOTA = "RTOTA";
    public static final String INBURSA_C100N = "C100N";
    public static final String INBURSA_RCAIN = "RCAIN";
    public static final String INBURSA_DES_RTOTA = "RENTA INBURSA";
    public static final String INBURSA_DES_C100N = "100 MIN INBURSA";
    public static final String INBURSA_DES_RCAIN = "RENTA INBURSA";
    public static final String INBURSA_MODALIDAD_POSPAGO = "P";
    public static final String INBURSA_MODALIDAD_PREPAGO = "T";
    public static final String INBURSA_C101N = "C101N";
    public static final String INBURSA_C102N = "C102N";
    public static final String INBURSA_C103N = "C103N";
    public static final String INBURSA_C104N = "C104N";
    public static final String INBURSA_DES_C101N = "25 MINUTOS INBURSA";
    public static final String INBURSA_DES_C102N = "50 MINUTOS INBURSA";
    public static final String INBURSA_DES_C103N = "75 MINUTOS INBURSA";
    public static final String INBURSA_DES_C104N = "100 MINUTOS INBURSA";
    public static final String CLARO_CODIGO_SATISFACTORIO = "00";
    public static final String CLARO_CODIGO_ERROR_INFO = "01";
    public static final String CLARO_CODIGO_ERROR_NOEXISTECERTIFICADO = "02";
    public static final String CLARO_CODIGO_ERROR_NOEXISTEPEDIDO = "03";
    public static final String CLARO_CODIGO_ERROR_SOLICITUDNOVALIDA = "04";
    public static final String CLARO_CODIGO_ERROR_SALDOINSUFICIENTE = "05";
    public static final String CLARO_CODIGO_ERROR_CERTIFICADONOVIGENTE = "06";
    public static final String CLARO_CODIGO_ERROR_OTROS = "07";
    public static final String CLARO_CODIGO_ERROR_NOEXISTENMOVMIENTOS = "08";
    public static final String CLARO_ESTATUS_CERTIFICADO_NO_APLICADO = "N";
    public static final String CLARO_ESTATUS_CERTIFICADO_VENDIDO = "S";
    public static final String CLARO_ESTATUS_CERTIFICADO_CANCELADO = "C";
    public static final String CLARO_ESTATUS_TARJETA_CERTIFICADO_VIGENTE = "1";
    public static final String CLARO_ESTATUS_TARJETA_CERTIFICADO_CADUCADO = "2";
    public static final String CLARO_ESTATUS_TARJETA_CERTIFICADO_CANCELADO = "3";
    public static final String CLARO_PAGO_EN_CAJA = "1";
    public static final String CLARO_REVERSION_PAGO_EN_CAJA = "2";

    public static enum CODIGO_ERROR {
        C103("SBL_CAM_0103", "Error de proceso 1%"),
        C104("SBL_CAM_0104", "El formato del n\u00famero telef\u00f3nico es incorrecto"),
        C105("SBL_CAM_0105", "La longitud del mensaje es mayor a 150 caracteres"),
        C106("SBL_CAM_0106", "La longitud del n\u00famero telef\u00f3nico es diferente a 10 caracteres"),
        C107("SBL_CAM_0107", "El mensaje XML no esta bien estructurado"),
        C108("SBL_CAM_0108", "El mensaje no se deposito"),
        C109("SBL_CAM_0109", "El mensaje se deposito correctamente");
        
        private final String valor;
        private final String descripcion;

        private CODIGO_ERROR(String codigo, String mensaje) {
            this.valor = codigo;
            this.descripcion = mensaje;
        }

        public String getValor() {
            return this.valor;
        }

        public String getDescripcion() {
            return this.descripcion;
        }
    }

}

