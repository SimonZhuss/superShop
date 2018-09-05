package com.zss.superShop.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author zhushanshan
 *
 */
public class DateUtil {

    /**
     * 日期计算
     * @param date
     * @param dateType
     * @param amount
     * @return
     */
    public static Date DateCalcu(Date date, int dateType, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType,amount);
        return calendar.getTime();
    }
}
