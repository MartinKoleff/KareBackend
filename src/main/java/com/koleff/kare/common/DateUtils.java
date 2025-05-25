package com.koleff.kare.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    public static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public static Date getTodayDate() {
        return new Date();
    }

    public static Date getRandomDate() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        long randomDaysToAdd = (long) (Math.random() * (daysBetween + 1));
        LocalDate randomLocalDate = startDate.plusDays(randomDaysToAdd);

        return Date.from(randomLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isDateToday(Date date) {
        return dateFormat.format(date).equals(dateFormat.format(getTodayDate()));
    }

    public static boolean isDateYesterday(Date date) {
        return dateFormat.format(date).equals(dateFormat.format(getYesterdayDate()));
    }
}
