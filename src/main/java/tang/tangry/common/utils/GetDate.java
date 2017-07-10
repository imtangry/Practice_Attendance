package tang.tangry.common.utils;

import tang.tangry.attend.entity.Attend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tryu on 2017/7/2.
 * 用来获得年月日，或者准确时间
 */
public class GetDate {
   private static Calendar calendar = Calendar.getInstance();

    /**
     * Create by tryu 2017/7/3 13:07
     * 获取年月日
     */
    public static String currentDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * Create by tryu 2017/7/3 13:10
     * 获取时分秒
     */
    public static String cuttentTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String timeString = formatter.format(date);
        return timeString;
    }

    /**
     * Create by tryu 2017/7/3 13:10
     * 返回星期，国外的Sunday常量是1
     */
    public static int currentDayOfWeek(Date date) {
        calendar.setTime(date);
        int weekInt = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekInt - 1 == 0) {
            return 7;
        } else {
            return weekInt - 1;
        }
    }

    /**
     * Create by tryu 2017/7/3 8:48
     * 计算缺勤时间，单位现在是分钟。
     */
    public static int absenceTimes(Date clickIn, Date clickOut) {
        long start = clickIn.getTime();
        long end = clickOut.getTime();
        return 480 - ((int) ((end - start) / (1000 * 60)));
    }
    /**
     *Create by tryu 2017/7/3 14:07
     *获取某个时间点
     */
    public static Date beSetDate(int hour,int minute){
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        return calendar.getTime();

    }
}
