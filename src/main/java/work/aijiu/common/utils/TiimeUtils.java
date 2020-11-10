package work.aijiu.common.utils;

import org.joda.time.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TiimeUtils {

    /**
     * 获取一段时间内按照一定时间分割的List
     * @param start
     * @param end
     * @param interval
     * @return
     */
    public static List<String> getIntervalTimeList(String start, String end, int interval){
        Date startDate = TiimeUtils.convertString2Date("yyyy-MM-dd",start);
        Date endDate = TiimeUtils.convertString2Date("yyyy-MM-dd",end);
        List<String> list = new ArrayList<>();
        while(startDate.getTime()<=endDate.getTime()){
            list.add(TiimeUtils.convertDate2String("yyyy-MM-dd",startDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH,interval);
            if(calendar.getTime().getTime()>endDate.getTime()){
                if(!startDate.equals(endDate)){
                    list.add(TiimeUtils.convertDate2String("yyyy-MM-dd",endDate));
                }
                startDate = calendar.getTime();
            }else{
                startDate = calendar.getTime();
            }

        }
        return list;
    }


    public static String convertDate2String(String format,Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return simpleDateFormat.format(date);
    }


    public static Date convertString2Date(String format, String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> list = TiimeUtils.getIntervalTimeList("2020-10-05","2020-11-04",1);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
