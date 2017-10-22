package com.smallkk.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/22 23:11
 */
public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 23:19
     * Description   返回今天是星期几
     */
    public static int getTodayWeek() {
        calendar.setTime(new Date());    // 今天的时间
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;   //今天是星期几
        if (week < 0) {
            return 7;
        }
        return week;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 23:22
     * Description  获取两个时间之间的时差
     */
    public static int getMinutes(Date startDate, Date endDate) {
        long start = startDate.getTime();   //这个是毫秒值
        long end = endDate.getTime();
        int minutes = (int) ((end - start) / (1000 * 60));
        return minutes;
    }

    /**
     * Author song  醉美柳舞之众星捧月
     * Date & Time  2017/10/22 23:31
     * Description  获取当天的某个时间
     */
    public static Date getDate(int hours, int minutes) {
        calendar.set(Calendar.HOUR_OF_DAY, hours);  //一天中的几点
        calendar.set(Calendar.MINUTE, minutes);//分钟
        return calendar.getTime();
    }
}
