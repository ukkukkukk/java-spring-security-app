package com.example.springbootsecurityapp.security;

public enum UserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write");

    private final String userPermission;

    UserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserPermission() {
        return this.userPermission;
    }
}
