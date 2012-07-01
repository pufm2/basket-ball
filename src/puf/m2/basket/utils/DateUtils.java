package puf.m2.basket.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final DateFormat DF = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	public static final String dateToString(Date date) {
		return DF.format(date);
	}

	public static Date parseDate(String source) {
		Date result = null;
		try {
			result = DF.parse(source);
		} catch (ParseException p) {

		}
		return result;
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return DF.format(cal.getTime());
	}

}
