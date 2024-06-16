package com.smartjob.createuser.utils;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilTest {

    @Test
    public void testConvertTimestampToString_ValidTimestamp() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 6, 12, 14, 30, 0);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        String expectedDate = "2024-06-12";
        String actualDate = Util.convertTimestampToString(timestamp);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testConvertTimestampToString_AnotherValidTimestamp() {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        String expectedDate = "2000-01-01";
        String actualDate = Util.convertTimestampToString(timestamp);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testConvertTimestampToString_NullTimestamp() {
        assertThrows(NullPointerException.class, () -> {
            Util.convertTimestampToString(null);
        });
    }
}
