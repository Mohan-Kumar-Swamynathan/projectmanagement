package com.project.management.projectmanagement.biz;

import com.project.management.projectmanagement.constant.CommonStatusTypeEnum;
import com.project.management.projectmanagement.constant.UserRoleTypeEnum;
import com.project.management.projectmanagement.dao.projectdetails.ProjectDetailsDAO;
import com.project.management.projectmanagement.dao.projectmanager.ProjectManagerDAO;
import com.project.management.projectmanagement.dao.user.UserDAO;
import com.project.management.projectmanagement.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectManagerBiz {

    @Autowired
    private ProjectDetailsDAO projectDAO;

    @Autowired
    private ProjectManagerDAO projectManagerDAO;

    @Autowired
    private UserDAO userDAO;


    public CommonResponseWrapper<ProjectDetails> getProjectDetailsById(Long id) {
        log.info("Getting project details " + id);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        Optional<ProjectDetails> projectDetails = null;
        try {
            projectDetails = projectDAO.findById(id);
            if (projectDetails.isPresent()) {

                responseWrapper.setData(projectDetails);
            }
        } catch (Exception exception) {
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

    public CommonResponseWrapper createOrUpdateProject(ProjectDetails projectDetails) {
        log.info("create/update projectDetails", projectDetails);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        try {
            projectDAO.save(projectDetails);
        } catch (Exception e) {
            log.error("error while creating  project details", e);
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

    public CommonResponseWrapper<ProjectDetails> deleteProjectDetails(long id) {
        log.info("delete project details " + id);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        try {
            projectDAO.deleteById(id);
        } catch (Exception exception) {
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

    public CommonResponseWrapper createOrUpdateProjectManagementInfo(ProjectManager projectManager) {
        log.info("create/update project management", projectManager);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        try {
            projectManagerDAO.save(projectManager);
        } catch (Exception e) {
            log.error("error while creating project Management info", e);
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

    public CommonResponseWrapper<ProjectTrackerDetails> getProjectManagementDetailsById(Long projectId) {
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        ProjectTrackerDetails projectTrackerDetails = new ProjectTrackerDetails();

        try {
            Optional<ProjectDetails> projectDetails = projectDAO.findById(projectId);
            if (projectDetails.isPresent()) {
                projectTrackerDetails.setProjectDetails(projectDetails.get());
            }
            Optional<ProjectManager> projectManager = projectManagerDAO.findByProjectId(projectId);
            if (projectManager.isPresent()) {

                projectTrackerDetails.setProjectManager(projectManager.get());
            }

            Optional<List<User>> users = userDAO.findUserByTeamId(projectDetails.get().getTeamId());
            if (users.isPresent()) {
                List<User> developers = users.get().stream().filter(user ->
                        user.getUserRole() == UserRoleTypeEnum.DEVELOPER.getId()
                ).collect(Collectors.toList());
                projectTrackerDetails.setDevelopers(developers);
            }
            CommonStatusTypeEnum statusTypeEnum = CommonStatusTypeEnum.findByCode(projectManager.get().getProjectStatus());
            projectTrackerDetails.setProjectStatus(statusTypeEnum.getName());
            projectTrackerDetails.setStartTime(new Date(projectDetails.get().getStartTime()));
            projectTrackerDetails.setCompletionTime(new Date(projectDetails.get().getCompleteTime()));
            responseWrapper.setData(projectTrackerDetails);
        } catch (Exception exception) {
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }
}
