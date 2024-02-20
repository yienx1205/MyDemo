package com.yienx.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wangyanbo29
 * @Date 2024/1/25
 * @Description
 */
public class ThreadLocalDateUtil {
    /**
     * 日期格式
     */
    private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    /**
     * 线程安全处理
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 线程安全处理
     */
    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(date_format);
            threadLocal.set(df);
        }
        return df;
    }

    /**
     * 线程安全处理日期格式化
     */
    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    /**
     * 线程安全处理日期解析
     */
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
}
