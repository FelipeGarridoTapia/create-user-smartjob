package com.smartjob.createuser.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String convertTimestampToString(Timestamp timestamp) {
        LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
