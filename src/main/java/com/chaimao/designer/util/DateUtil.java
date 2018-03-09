package com.chaimao.designer.util;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: cmxu
 * @Description: 时间操作类
 * @Date： create in 22:42 2018/3/7
 * @Modified By:
 */
public class DateUtil {

    public static final String TERM_D = "D";// 期限天
    public static final String TERM_M = "M";// 期限月
    public static final String TERM_Y = "Y";// 期限年

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到月底
     */
    public static String getEndDateOfMonth(String curDate) throws ParseException {
        if (curDate == null || curDate.length() != 10) {
            return "";
        }
        curDate = curDate.substring(0, 8) + "01";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(formatter.parse(curDate));
        int maxDays = cal.getActualMaximum(Calendar.DATE);// 得到该月的最大天数
        cal.set(Calendar.DATE, maxDays);
        return formatter.format(cal.getTime());
    }

    public static String getRelativeDate(String date, String termUnit, String term) throws Exception {
        return DateUtil.getRelativeDate(date, date, termUnit, Integer.parseInt(term));
    }

    public static String getRelativeDate(String date, String termUnit, int term) throws Exception {
        return DateUtil.getRelativeDate(date, date, termUnit, term);
    }

    public static String getRelativeDate(String baseDate, String date, String termUnit, int step) throws Exception {
        return DateUtil.getRelativeDate(baseDate, false, date, termUnit, step);
    }

    public static String getRelativeDate(String baseDate, boolean endOfMonth, String date, String termUnit, int term)
            throws Exception {
        if (term == 0) {
            return date;
        }
        if (!TERM_D.equals(termUnit) && !TERM_M.equals(termUnit) && !TERM_Y.equals(termUnit)) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(formatter.parse(date));
        if (termUnit.equals(TERM_D)) {
            cal.add(Calendar.DATE, term);
        } else if (termUnit.equals(TERM_M)) {
            cal.add(Calendar.MONTH, term);
            if (DateUtil.monthEnd(baseDate) && endOfMonth) {
                String s = formatter.format(cal.getTime());
                return DateUtil.getEndDateOfMonth(s);
            }
        } else if (termUnit.equals(TERM_Y)) {
            cal.add(Calendar.YEAR, term);
        }
        return formatter.format(cal.getTime());
    }

    public static boolean monthEnd(String date) throws ParseException {
        return date.equals(getEndDateOfMonth(date));
    }

    /**
     * 获取现在日期
     *
     * @return 返回时间类型 yyyy-MM-dd
     */
    public static String getDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
