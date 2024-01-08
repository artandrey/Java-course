package com.example.lab8.util;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtil {
    public static Date toDateWithoutTime(Date date) {
        return Date.from(date.toInstant().truncatedTo(ChronoUnit.DAYS));
    }

    public static String getMonthLiteral(Date date) {
        SimpleDateFormat monthDateFormat = new SimpleDateFormat("MMMM");
        return monthDateFormat.format(date);
    }
}
