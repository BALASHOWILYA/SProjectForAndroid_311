package com.example.projecctforandroidlessons.data.roomdb;

import androidx.room.TypeConverter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converters {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @TypeConverter
    public static Date fromString(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date date) {
        return date == null ? null : dateFormat.format(date);
    }
}