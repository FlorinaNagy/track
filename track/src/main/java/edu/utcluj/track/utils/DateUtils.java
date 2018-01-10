package edu.utcluj.track.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static Date getCurrentDate() throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        date = dateFormat.parse(dateFormat.format(date));
        return date;

    }

    public static Date convertStringToDate(String string){
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            date = (Date)dateFormat.parse((string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
