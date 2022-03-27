package com.project.management.projectmanagement.biz;

import com.project.management.projectmanagement.constant.UserRoleTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class UserRoleValidatorBiz {
    public boolean isDeveloper(UserRoleTypeEnum userRoleTypeEnum) {
        return userRoleTypeEnum.getId() == UserRoleTypeEnum.DEVELOPER.getId();
    }

    public boolean isAdmin(UserRoleTypeEnum userRoleTypeEnum) {
        return userRoleTypeEnum.getId() == UserRoleTypeEnum.ADMIN.getId();
    }


    public boolean isBusinessDeveloper(UserRoleTypeEnum userRoleTypeEnum) {
        return userRoleTypeEnum.getId() == UserRoleTypeEnum.BUSINESS_DEVELOPER.getId();
    }

    public boolean isProjectManager(UserRoleTypeEnum userRoleTypeEnum) {
        return userRoleTypeEnum.getId() == UserRoleTypeEnum.PROJECT_MANAGER.getId();
    }
}
