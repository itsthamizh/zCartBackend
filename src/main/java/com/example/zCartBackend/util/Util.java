package com.example.zCartBackend.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Util {
    public static String getUniqueID(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }
    public static String getCurrentDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
