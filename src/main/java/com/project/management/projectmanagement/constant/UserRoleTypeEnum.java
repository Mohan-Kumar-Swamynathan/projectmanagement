package com.project.management.projectmanagement.constant;

import lombok.Getter;

public enum UserRoleTypeEnum {
    ADMIN(1, "ADMIN"),
    BUSINESS_DEVELOPER(2, "BUSINESS_DEVELOPER"),
    PROJECT_MANAGER(3, "PROJECT_MANAGER"),
    DEVELOPER(4, "DEVELOPER"),
    INVALID(-1, "INVALID");

    @Getter
    private final int id;
    @Getter
    private final String name;

    UserRoleTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserRoleTypeEnum findByCode(int value) {
        for (UserRoleTypeEnum roleTypeEnum : UserRoleTypeEnum.values()) {
            if (roleTypeEnum.getId() == value) {
                return roleTypeEnum;
            }
        }
        return UserRoleTypeEnum.INVALID;
    }
}
