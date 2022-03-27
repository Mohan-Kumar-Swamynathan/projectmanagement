package com.project.management.projectmanagement;

import com.project.management.projectmanagement.constant.UserRoleTypeEnum;

public class UserThreadLocale {
    private static final ThreadLocal<UserRoleTypeEnum> CONTEXT = new ThreadLocal<>();

    public static UserRoleTypeEnum getUserRole() {
        return CONTEXT.get();
    }

    public static void setUserRole(UserRoleTypeEnum userRole) {
        CONTEXT.set(userRole);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
