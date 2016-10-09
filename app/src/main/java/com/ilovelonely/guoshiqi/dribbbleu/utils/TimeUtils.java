package com.ilovelonely.guoshiqi.dribbbleu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guoshiqi on 16/5/31.
 */
public class TimeUtils {
    public static String getNowDate(String format){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        Date date=new Date();
        return simpleDateFormat.format(date);
    }
}
