package util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2017/11/30 at 下午2:39
 */
public class DateUtil {

    public static final String format = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        String result = format(null);
        System.out.println(result);

    }

    /**
     * 格式化 java.util.Date Format：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        String result = DateFormatUtils.format(date, format);
        return result;
    }



    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
            ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2017-10-15 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
            ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * 
     * @Description: 根据字符串日期和模板转换成Date
     * @param strDate
     * @param pattern
     * @return
     * @author denny
     * @date 2018年4月17日 下午7:55:50
     */
    public static Date formatDate(String strDate,String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDateTime.parse(strDate,dateTimeFormatter).atZone(ZoneId.systemDefault()).toInstant());
    }
    
}
