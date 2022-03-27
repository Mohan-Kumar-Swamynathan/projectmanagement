package com.project.management.projectmanagement.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectTrackerDetails {
    private String projectStatus;
    private Date startTime;
    private Date completionTime;
    private ProjectDetails projectDetails;
    private List<User> developers;
    private ProjectManager projectManager;

}
