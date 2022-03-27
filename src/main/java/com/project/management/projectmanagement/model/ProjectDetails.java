package com.project.management.projectmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "project_details")
@Data
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long teamId;
    private Long startTime;
    private Long completeTime;
    private String projectRequirements;
}
