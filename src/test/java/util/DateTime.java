package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	//"MMM dd, yyyy"
	public static String convertToString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String reportDate = df.format(date);
		return reportDate;
	}

	public static Date convertToDate(String content, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date result = formatter.parse(content);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public static Date convertToDate(String content) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		try {
			Date result = formatter.parse(content);
			return result;
		} catch (ParseException e) {
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.parse(content);
		}
	}*/

	/*
	 * public static int getWeekOfDate(String content, String format) {
	 * SimpleDateFormat formatter = new SimpleDateFormat(format); int weekOfDate
	 * = 0; try { Calendar cal = Calendar.getInstance(); Date date =
	 * formatter.parse(content); cal.setTime(date); weekOfDate =
	 * cal.get(Calendar.WEEK_OF_YEAR); } catch (ParseException e) {
	 * e.printStackTrace(); } return weekOfDate; }
	 */

	/*
	 * public static int getDayOfWeek(String content, String format) {
	 * SimpleDateFormat formatter = new SimpleDateFormat(format); int dayOfWeek
	 * = 0; try { Calendar cal = Calendar.getInstance(); Date date =
	 * formatter.parse(content); cal.setTime(date); dayOfWeek =
	 * cal.get(Calendar.DAY_OF_WEEK); } catch (ParseException e) {
	 * e.printStackTrace(); } return dayOfWeek; }
	 */

	public static int getDayOfWeek(String content, String format) {
		int dayOfWeek = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertToDate(content, format));
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	public static int getWeekOfDate(String content, String format) {

		int weekOfDate = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertToDate(content, format));
		weekOfDate = cal.get(Calendar.WEEK_OF_YEAR);
		return weekOfDate;
	}
}
