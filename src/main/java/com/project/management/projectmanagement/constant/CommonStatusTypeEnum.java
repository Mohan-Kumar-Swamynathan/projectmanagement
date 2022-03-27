package com.project.management.projectmanagement.constant;

import lombok.Getter;

public enum CommonStatusTypeEnum {

    // statuses used for user
    ACTIVE(1L, "ACTIVE"),
    IN_ACTIVE(0L, "IN-ACTIVE"),
    // statuses used for project tracker
    IN_PROGRESS(1L, "IN-PROGRESS"),
    COMPLETED(2L, "COMPLETED"),
    INVALID(-1L, "INVALID");


    @Getter
    private final Long id;
    @Getter
    private final String name;

    CommonStatusTypeEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CommonStatusTypeEnum findByCode(Long status) {
        for (CommonStatusTypeEnum commonStatusTypeEnum : CommonStatusTypeEnum.values()) {
            if (commonStatusTypeEnum.getId() == status) {
                return commonStatusTypeEnum;
            }
        }
        return CommonStatusTypeEnum.INVALID;
    }
}
