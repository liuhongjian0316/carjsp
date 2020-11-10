package work.aijiu.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @data on 2020/9/3 10:51 AM
 * @auther
 * @describe java 时间戳/字符串 之间转换
 */
public class DateUtil {
    private static SimpleDateFormat sf = null;

    // 将时间戳转成字符串
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    //获取当前时间
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    //将字符串转换为时间戳
    public static Date getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //直接获取当前时间戳
    public static String getTimeStamp() {
        String currentDate = getCurrentDate();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date.getTime());
    }
}
