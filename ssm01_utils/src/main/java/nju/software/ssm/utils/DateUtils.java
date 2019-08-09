package nju.software.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 负责日期转换的工具类
 */
public class DateUtils {

    /**
     * 负责date转向String
     * @param date    date型日期
     * @param stringp 转换格式 例如yyyy-MM-dd
     */
    public static String date2String(Date date,String stringp){
        SimpleDateFormat sdf=new SimpleDateFormat(stringp);
        String format=sdf.format(date);
        return format;
    }

    //字符串转换成日期
    public static Date string2Date(String str,String stringp) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(stringp);
        Date parse=sdf.parse(str);
        return  parse;
    }


}
