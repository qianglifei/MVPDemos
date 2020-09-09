package com.bksx.mobile.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
	
	/**
	 * 按格式获取时间字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateString(Date date, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	
	/**
	 * 判断Date是否在指定的时间之内
	 * @param minutes
	 * @return
	 */
	public static boolean isLessThanMinutes(Date date, int minutes){
		Date currentDate = new Date();
		if(date.getTime() + (minutes * 60 * 1000) >= currentDate.getTime()){
			return true;
		}
		return false;
	}
	
	
	/**
	  *获取两个Date相差的天数
	  *@param beginDate 开始日期
	  *@param endDate   结束日期
	  *@return 
	  */
	public static long getDateMargin(Date beginDate, Date endDate){
	    long margin = 0;
	    margin = endDate.getTime() - beginDate.getTime();
	    margin = margin/(1000*60*60*24);
	    return margin;
	}
	
	
}
