package com.zlht.pbr.algorithm.wcmp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
public class TimeUtils {

    public static Map<String, Date> getCurrentWeekRange(Date date) {
        Map<String, Date> map = new HashMap<>(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        Date endOfWeek = calendar.getTime();

        map.put("startOfWeek", startOfWeek);
        map.put("endOfWeek", endOfWeek);
        return map;
    }

    public static Map<String, Date> getCurrentMonthRange(Date date) {
        Map<String, Date> map = new HashMap<>(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date endOfMonth = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("startOfMonth", startOfMonth);
        map.put("endOfMonth", endOfMonth);
        return map;
    }
}
