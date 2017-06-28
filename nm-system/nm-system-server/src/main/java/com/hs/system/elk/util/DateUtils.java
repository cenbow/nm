package com.hs.system.elk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils 
{
	public static String calendar2String(Calendar calendar) {
		StringBuffer sb = new StringBuffer();

		if (calendar != null) {
			sb.append(calendar.get(Calendar.YEAR));
			sb.append("-");
			sb.append((calendar.get(Calendar.MONTH))+1);
			sb.append("-");
			sb.append(calendar.get(Calendar.DAY_OF_MONTH));
			sb.append(" ");
			sb.append(calendar.get(Calendar.HOUR_OF_DAY));
			sb.append(":");
			sb.append(calendar.get(Calendar.MINUTE));
			sb.append(":");
			sb.append(calendar.get(Calendar.SECOND));
		}
		return sb.toString();
	}
	
	public static String date2String(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
