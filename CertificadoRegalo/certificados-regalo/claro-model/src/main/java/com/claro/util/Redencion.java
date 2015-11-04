/*
 * Decompiled with CFR 0_102.
 */
package com.claro.util;

import com.claro.exception.CAException;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.TelefonoTO;

public class Redencion {
	public static void validaRedencion(ParametrosTO parametrosTO,
			TelefonoTO telefonoTO, MobileTO mobileTO) {
		Redencion.validaRedencion(parametrosTO, telefonoTO, mobileTO, true);
	}

	public static void validaPlan(ParametrosTO parametrosTO,
			TelefonoTO telefonoTO) {
		if (telefonoTO.getPlanTO().getTipoPromocion().trim().equals("AX")
				&& !parametrosTO.getFormaRed().trim().equals("PM")) {
			telefonoTO
					.agregaMensaje(1,
							"El Plan Anexo no puede Renovar antes de Finalizar Addendum");
			return;
		}
		if (telefonoTO.getPlanTO().getTipoPromocion().trim().equals("RE")
				&& !parametrosTO.getFormaRed().trim().equals("PM")) {
			telefonoTO
					.agregaMensaje(1,
							"El Plan Retencion no puede Renovar antes de Finalizar Addendum");
			return;
		}
	}

	public static void aplicaRedencion(RedencionTO redencionTO, int rubros) {
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsPorVencer() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencerRedimidos(
						redencionTO.getPuntosRedimidosTO().getPtsPorVencer());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsPorVencer(
								redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencerRedimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsPorVencer1() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer1() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer1());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer1Redimidos(
						redencionTO.getPuntosRedimidosTO().getPtsPorVencer1());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer1(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsPorVencer1(
								redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer1()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer1Redimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsPorVencer2() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsPorVencer2() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer2());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer2Redimidos(
						redencionTO.getPuntosRedimidosTO().getPtsPorVencer2());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer2(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsPorVencer2(
								redencionTO.getPuntosRedimidosTO()
										.getPtsPorVencer2()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsPorVencer2Redimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsPromocion() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsPromocion() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsPromocion());
				redencionTO.getPuntosRedimidosTO().setPtsPromocionRedimidos(
						redencionTO.getPuntosRedimidosTO().getPtsPromocion());
				redencionTO.getPuntosRedimidosTO().setPtsPromocion(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsPromocion(
								redencionTO.getPuntosRedimidosTO()
										.getPtsPromocion()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsPromocionRedimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsAntiguedad() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsAntiguedad() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsAntiguedad());
				redencionTO.getPuntosRedimidosTO()
						.setPtsPorAntiguedadRedimidos(
								redencionTO.getPuntosRedimidosTO()
										.getPtsAntiguedad());
				redencionTO.getPuntosRedimidosTO().setPtsAntiguedad(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsAntiguedad(
								redencionTO.getPuntosRedimidosTO()
										.getPtsAntiguedad()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO()
						.setPtsPorAntiguedadRedimidos(
								redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros <= 7
				&& redencionTO.getPuntosRedimidosTO().getPtsExcedentes() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsExcedentes() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsExcedentes());
				redencionTO.getPuntosRedimidosTO().setPtsExcedentesRedimidos(
						redencionTO.getPuntosRedimidosTO().getPtsExcedentes());
				redencionTO.getPuntosRedimidosTO().setPtsExcedentes(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsExcedentes(
								redencionTO.getPuntosRedimidosTO()
										.getPtsExcedentes()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsExcedentesRedimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
		if (rubros == 7 && redencionTO.getPuntosRedimidosTO().getPtsRenta() > 0) {
			if (redencionTO.getPuntosRedimidosTO().getPtsRenta() <= redencionTO
					.getProductosTO().getValorPuntos()) {
				redencionTO.getProductosTO().setValorPuntos(
						redencionTO.getProductosTO().getValorPuntos()
								- redencionTO.getPuntosRedimidosTO()
										.getPtsRenta());
				redencionTO.getPuntosRedimidosTO().setPtsRentaRedimidos(
						redencionTO.getPuntosRedimidosTO().getPtsRenta());
				redencionTO.getPuntosRedimidosTO().setPtsRenta(0);
			} else {
				redencionTO.getPuntosRedimidosTO()
						.setPtsRenta(
								redencionTO.getPuntosRedimidosTO()
										.getPtsRenta()
										- redencionTO.getProductosTO()
												.getValorPuntos());
				redencionTO.getPuntosRedimidosTO().setPtsRentaRedimidos(
						redencionTO.getProductosTO().getValorPuntos());
				redencionTO.getProductosTO().setValorPuntos(0);
				return;
			}
		}
	}

	public static String getDescTipoRed(String tipoRed) {
		if (tipoRed.equals("CON") || tipoRed.equals("CAREG")) {
			return "CON FIRMA DE ADDENDUM";
		}
		if (tipoRed.equals("SIN")) {
			return "AMIGO KIT";
		}
		if (tipoRed.equals("ACA")) {
			return "AMIGO CHIP";
		}
		if (tipoRed.equals("T3G")) {
			return "TARJETA INALAMBRICA 3G";
		}
		return tipoRed;
	}

	public static String getDescFormaRed(String formaRed) {
		if (formaRed.equals("PM")) {
			return "TERMINO DE ADENDUM";
		}
		if (formaRed.equals("PR")) {
			return "SIN TERMINO DE ADENDUM ";
		}
		if (formaRed.equals("PC")) {
			return "SIN TERMINO DE ADENDUM CAREG";
		}
		if (formaRed.equals("PD")) {
			return "PUNTOS DISPONIBLES";
		}
		return formaRed;
	}

	public static String validaTipoRedencion(String tipoRed) {
		if ("CON".equals(tipoRed.trim()) || tipoRed.trim().equals("CAREG")) {
			return "C";
		}
		if ("SIN".equals(tipoRed.trim())) {
			return "S";
		}
		if ("TAIR".equals(tipoRed.trim())) {
			return "T";
		}
		if ("T3G".equals(tipoRed.trim())) {
			return "G";
		}
		if ("ACA".equals(tipoRed.trim())) {
			return "A";
		}
		return null;
	}

	public static String validaTipoRedenBD(String sTipoRed) {
		if (sTipoRed.equals("C")) {
			return "CON";
		}
		if (sTipoRed.equals("S")) {
			return "SIN";
		}
		if (sTipoRed.equals("A")) {
			return "ACA";
		}
		if (sTipoRed.equals("G")) {
			return "T3G";
		}
		return sTipoRed;
	}

	public static void validaRedencion(ParametrosTO parametrosTO,
			TelefonoTO telefonoTO, MobileTO mobileTO, boolean validaCAREG) {
		telefonoTO.setNMesActual(Utils.getMesActual(mobileTO.getFecAddM2K()
				.trim()));
		telefonoTO.setNDiaActual(Utils.getDiaActual(mobileTO.getFecAddM2K()
				.trim()));
		telefonoTO.setNDiasMes(Utils.getDiasMes());
		if (parametrosTO.getTipoRed().equals("PTS")
				|| parametrosTO.getTipoRed().equals("CAN")
				|| parametrosTO.getTipoRed().equals("IMP")) {
			parametrosTO.setFormaRed("CP");
			return;
		}
		if (parametrosTO.getTipoRed().equals("CON")) {
			boolean bTerminoAdendum = Utils.terminoAdendum(
					mobileTO.getFecAddM2K(), mobileTO.getAddM2K(),
					parametrosTO.getRegion(), parametrosTO.getBRedencionAnct());
			if (mobileTO.getFecAddM2K() == null
					|| "".equals(mobileTO.getFecAddM2K().trim())
					|| bTerminoAdendum || mobileTO.getAddM2K() == null
					|| "0".equals(mobileTO.getAddM2K().trim())) {
				parametrosTO.setFormaRed("PM");
				return;
			}
			if (!(parametrosTO.getRegion() != 9 || bTerminoAdendum || telefonoTO
					.getNMesActual() < 0)) {
				parametrosTO.setFormaRed("PR");
				return;
			}
			if (!(parametrosTO.getRegion() == 9 || bTerminoAdendum || telefonoTO
					.getNMesActual() < 6)) {
				parametrosTO.setFormaRed("PR");
				return;
			}
			parametrosTO.setFormaRed("NA");
			telefonoTO
					.agregaMensaje(
							1,
							"No ha concluido el tiempo estimado en el plazo forzoso para realizar una redencion. Para R1 a R8 a partir del 6to. mes.");
			return;
		}
		if (parametrosTO.getTipoRed().trim().equals("CAREG")) {
			parametrosTO.setFormaRed("PC");
			if (validaCAREG) {
				Redencion.validaRedencionCAREG(parametrosTO, telefonoTO);
			}
		} else {
			if (parametrosTO.getTipoRed().equals("SIN")
					|| parametrosTO.getTipoRed().equals("ACA")
					|| parametrosTO.getTipoRed().equals("T3G")) {
				parametrosTO.setFormaRed("PD");
				return;
			}
			telefonoTO.agregaMensaje(1, "El tipo de redencion no es valido");
			return;
		}
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	public static String validaRedencionDistribuidoresSisact(
			boolean isReservacionAnticipada, String fechaPlazoForzoso,
			String plazoForzoso) throws CAException {
		String formaRedencion = "";
		if (isReservacionAnticipada) {
			if (!Utils.terminoAdendum(fechaPlazoForzoso, plazoForzoso, 9,
					isReservacionAnticipada ? "1" : "0"))
				throw new CAException(-1, "No termino adendum anticipado.");
			return "PM";
		}
		if (!Utils.terminoAdendum(fechaPlazoForzoso, plazoForzoso, 9,
				isReservacionAnticipada ? "1" : "0"))
			return "PR";
		return "PM";
	}

	public static void validaRedencionCAREG(ParametrosTO parametrosTO,
			TelefonoTO telefonoTO) {
		if (parametrosTO.getMesCareg().length() > 0
				&& parametrosTO.getAddCareg().length() > 0) {
			if (Integer.parseInt(parametrosTO.getMesCareg()) > Integer
					.parseInt(parametrosTO.getAddCareg())) {
				telefonoTO
						.agregaMensaje(1,
								"El No. de Meses para Prorrateo CAREG debe ser Menor que el Addendum ");
			}
		} else {
			if (parametrosTO.getRegion() != 9
					&& telefonoTO.getNMesActual()
							+ Integer.parseInt(parametrosTO.getMesCareg()) <= 6) {
				telefonoTO
						.agregaMensaje(
								1,
								"No ha concluido el tiempo estimado en el plazo forzoso para realizar una redencion. Para R1 a R8 a partir del 6to. mes.");
				return;
			}
			telefonoTO
					.agregaMensaje(
							1,
							"Para realizar un redencion por CAREG es necesario capturar el addendum y el numero de meses que lleva dentro de el mismo.");
			return;
		}
	}
}
