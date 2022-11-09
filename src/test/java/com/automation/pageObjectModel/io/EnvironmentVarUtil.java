package com.automation.pageObjectModel.io;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class EnvironmentVarUtil {
    private final static String TARGET_ENV = "TARGET_ENV";
    private final static String APP_USERNAME = "APP_USERNAME";
    private final static String APP_PASSWORD = "APP_PASSWORD";

    public static String getTargetEnv() {
        return Optional
                .ofNullable(System.getenv(TARGET_ENV))
                .orElseThrow(() -> new RuntimeException("Environment variable TARGET_ENV is missing"));
    }

    public static String getAppUsername() {
        return Optional
                .ofNullable(System.getenv(APP_USERNAME))
                .orElseThrow(() -> new RuntimeException("Environment variable USERNAME is missing"));
    }

    public static String getAppPassword() {
        return Optional
                .ofNullable(System.getenv(APP_PASSWORD))
                .orElseThrow(() -> new RuntimeException("Environment variable PASSWORD is missing"));
    }
}
