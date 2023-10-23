package com.example.zCartBackend.util;

import java.util.UUID;

public class Util {

    public static String getUniqueID(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

}
