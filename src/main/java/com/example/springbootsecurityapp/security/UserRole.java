package com.example.springbootsecurityapp.security;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public enum UserRole {
    STUDENT(ImmutableSet.of(UserPermission.STUDENT_READ)),
    ADMIN(ImmutableSet.of(UserPermission.STUDENT_READ, UserPermission.STUDENT_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getUserPermissions() {
        return this.permissions;
    }
}
