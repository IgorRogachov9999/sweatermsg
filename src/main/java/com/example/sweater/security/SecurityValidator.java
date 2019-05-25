package com.example.sweater.security;

public class SecurityValidator {

    public static String XSSValidate(String string) {
        return string
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");
    }

}
