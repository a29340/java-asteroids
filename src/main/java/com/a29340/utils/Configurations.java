package com.a29340.utils;

public class Configurations {
    public static final String RUN_MODE = System.getenv("RUN_MODE");


    public static Boolean debugMode() {
        return RUN_MODE != null && RUN_MODE.equalsIgnoreCase("debug");
    }
}
