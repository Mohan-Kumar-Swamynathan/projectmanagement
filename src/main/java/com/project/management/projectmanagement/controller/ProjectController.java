package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.UserThreadLocale;
import com.project.management.projectmanagement.biz.ProjectManagerBiz;
import com.project.management.projectmanagement.biz.UserRoleValidatorBiz;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectManagerBiz managerBiz;

    @Autowired
    private UserRoleValidatorBiz userRoleValidatorBiz;

    @GetMapping("/option")
    public String index() {
        return "Service is up";
    }

    // only admin and business developer can create project

    @PostMapping("/create")
    public CommonResponseWrapper createProject(@Validated @RequestBody ProjectDetails projectDetails) {
        boolean isValidUser;
        isValidUser = userRoleValidatorBiz.isAdmin(UserThreadLocale.getUserRole());
        if (!isValidUser) {
            isValidUser = userRoleValidatorBiz.isBusinessDeveloper(UserThreadLocale.getUserRole());
        }
        if (!isValidUser) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return managerBiz.createOrUpdateProject(projectDetails);
    }
    // only admin,business developer,project manager can update project

    @PutMapping("/update")
    public CommonResponseWrapper updateProject(@Validated @RequestBody ProjectDetails projectDetails) {
        if (!userRoleValidatorBiz.isProjectManager(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return managerBiz.createOrUpdateProject(projectDetails);
    }
    // all users can get project details

    @GetMapping("/{id}")
    public CommonResponseWrapper<ProjectDetails> getProjectDetails(@PathVariable(value = "id") long id) {
        return managerBiz.getProjectDetailsById(id);
    }
    // only admin can delete

    @GetMapping("/delete/{id}")
    public CommonResponseWrapper<ProjectDetails> deleteProjectDetails(@PathVariable(value = "id") long id) {
        if (!userRoleValidatorBiz.isAdmin(UserThreadLocale.getUserRole())) {
            return new CommonResponseWrapper(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return managerBiz.deleteProjectDetails(id);
    }

}
