package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.biz.ProjectManagerBiz;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectManagerBiz managerBiz;

    @GetMapping("/option")
    public String index() {
        return "Service is up";
    }

    @PostMapping("/create")
    public CommonResponseWrapper createProject(@Validated @RequestBody ProjectDetails projectDetails) {
        return managerBiz.createOrUpdateProject(projectDetails);
    }

    @PutMapping("/update")
    public CommonResponseWrapper updateProject(@Validated @RequestBody ProjectDetails projectDetails) {
        return managerBiz.createOrUpdateProject(projectDetails);
    }


    @GetMapping("/{id}")
    public CommonResponseWrapper<ProjectDetails> getProjectDetails(@PathVariable(value = "id") long id) {
        return managerBiz.getProjectDetailsById(id);
    }

    @GetMapping("/delete/{id}")
    public CommonResponseWrapper<ProjectDetails> deleteProjectDetails(@PathVariable(value = "id") long id) {
        return managerBiz.deleteProjectDetails(id);
    }

}
