package com.yienx.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author wangyanbo29
 * @Date 2023/8/4
 * @Description
 */
public class DateTimeUtil {
    public static final int MILLISECONDS_PER_SECONDE = 1000;
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    public static final long MILLISECONDS_PER_DAY = 86400000L;
    public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MILLS_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    private static Pattern datePattern = Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

    public static String dateToStr(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    /**
     * 转换格式为 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, DATE_FORMAT_TIME);
    }

    /**
     * 转换格式为 yyyy-MM-dd HH:mm:ss.SSS
     * @param date
     * @return
     */
    public static String dateToMillisStr(Date date) {
        return dateToStr(date, DATE_FORMAT_MILLS_TIME);
    }

    public static Date strToDate(String dateStr, String format) throws ParseException {
        return (new SimpleDateFormat(format)).parse(dateStr);
    }

    /**
     * 转换格式为 yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
        return strToDate(dateStr, DATE_FORMAT_TIME);
    }

    /**
     * 转换格式为 yyyy-MM-dd HH:mm:ss.SSS
     * @param millisDateStr
     * @return
     * @throws ParseException
     */
    public static Date millisStrToDate(String millisDateStr) throws ParseException {
        return strToDate(millisDateStr, DATE_FORMAT_MILLS_TIME);
    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDateStr(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return false;
        }
        Matcher m = datePattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取今天是今年的第几周
     * 以星期一作为每周的第一天
     */
    public static int getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取前后n天
     * @param date
     * @param nDays 正数是后，负数是前
     * @return
     */
    public static Date getDateByDays(Date date, int nDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, nDays);
        return c.getTime();
    }

    /**
     * 返回两个日期的分钟差值
     *
     * @param start
     * @param end
     * @return
     */
    public static long differenceMinutes(Date start, Date end) {
        LocalDateTime one = parseLocalDateTimeByDate(start);
        LocalDateTime two = parseLocalDateTimeByDate(end);
        return ChronoUnit.MINUTES.between(one, two);
    }

    /**
     * 返回两个日期的天数差值
     *
     * @param start
     * @param end
     * @return
     */
    public static long differenceDays(Date start, Date end) {
        LocalDateTime one = parseLocalDateTimeByDate(start);
        LocalDateTime two = parseLocalDateTimeByDate(end);
        return ChronoUnit.DAYS.between(one, two);
    }

    /**
     * date转localDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime parseLocalDateTimeByDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

}
