package com.nsk.enums;

/**
 * Перечисление типов пользователей на странице логина.
 */
public enum UserType {
    STANDARD("standard_user"),
    LOCKED_OUT("locked_out_user"),
    PROBLEM("problem_user"),
    PERFORMANCE_GLITCH("performance_glitch_user"),
    ERROR("error_user"),
    VISUAL("visual_user");

    private final String username;

    UserType(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
