package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.UserThreadLocale;
import com.project.management.projectmanagement.biz.ProjectManagerBiz;
import com.project.management.projectmanagement.biz.UserRoleValidatorBiz;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.ProjectManager;
import com.project.management.projectmanagement.model.ProjectTrackerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project/management")
public class ProjectManagementController {
    @Autowired
    private ProjectManagerBiz managerBiz;

    @Autowired
    private UserRoleValidatorBiz roleValidatorBiz;

    @GetMapping("/option")
    public String index() {
        return "Service is up";
    }

    // only admin,business developer can manage project
    @PostMapping("/create")
    public CommonResponseWrapper saveUser(@Validated @RequestBody ProjectManager projectManager) {
        if (!roleValidatorBiz.isAdmin(UserThreadLocale.getUserRole()) ||
                !roleValidatorBiz.isBusinessDeveloper(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return managerBiz.createOrUpdateProjectManagementInfo(projectManager);
    }

    @GetMapping("/track/{projectId}")
    public CommonResponseWrapper<ProjectTrackerDetails> trackInfo(@PathVariable(value = "projectId") Long projectId) {
        return managerBiz.getProjectManagementDetailsById(projectId);
    }
}
