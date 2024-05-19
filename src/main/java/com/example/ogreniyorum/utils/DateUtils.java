package com.example.ogreniyorum.utils;

import java.time.LocalDate;


public class DateUtils {
    public static long getDaysBetween(LocalDate startDate, LocalDate endDate) {
        long diff;

        diff = Math.abs(startDate.toEpochDay() - endDate.toEpochDay());
        System.out.println("Gün farkı: " + diff);
        return diff;
    }
}