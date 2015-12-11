package com.claro.gml.certificados.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateUtilsClaro {

	public static Date getDate(Timestamp timeStamp) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp.getTime());

		return cal.getTime();
	}

	public static boolean isBefore(Date begin, Date end) {
		return begin.compareTo(end) < 0;
	}

	public boolean isToday(Date begin, Date end) {
		return begin.compareTo(end) == 0;
	}

	public static boolean isAfter(Date begin, Date end) {
		return begin.compareTo(end) > 0;
	}

	/**
	 * @see entrega true si la fecha es igual o menor que hoy
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isLessOrEqual(Date begin, Date end) {
		return begin.compareTo(end) <= 0;
	}

	/**
	 * @see Obtiene la fecha de hoy
	 * @return
	 */
	public static Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * @see Obtiene la fecha del dia de hoy en formato timestamp
	 * @return
	 */
	public static Timestamp nowTimeStamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * @see convierte a un formato adecuado string el date
	 * @param fecha
	 * @param format
	 * @return
	 */
	public static String toFormatString(Date fecha, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(fecha);

	}

	/**
	 * @see Obtiene los dias entre 2 fechas
	 * @param beginDate
	 * @return
	 */
	public static int getDaysFromNow(Timestamp beginDate) {

		Date now = now();
		Date limit = getDate(beginDate);

		DateTime start = new DateTime(now);
		DateTime end = new DateTime(limit);

		Days days = Days.daysBetween(start, end);

		return days.getDays();
	}

	/**
	 * @see agrega la cantidad de meses a la fecha inicio
	 * @param fechaIni
	 * @param meses
	 * @return
	 */
	public static Date addMonth(Date fechaIni, int meses) {

		DateTime initial = new DateTime(fechaIni);
		DateTime end = initial.plusMonths(meses);

		return end.toDate();
	}

}
