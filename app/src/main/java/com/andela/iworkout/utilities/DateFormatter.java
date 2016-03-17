package com.andela.iworkout.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String getReadableDate(long milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date(milliSeconds);
        return simpleDateFormat.format(date);
    }

    public static String getReadableTime(long milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(milliSeconds);
        return simpleDateFormat.format(date);
    }

    public static String getReadableSeconds(long milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        Date date = new Date(milliSeconds);
        return simpleDateFormat.format(date);
    }
}
