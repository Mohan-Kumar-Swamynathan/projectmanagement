package com.project.management.projectmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "project_manager")
@Data
public class ProjectManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long teamId;
    private Long projectId;
    private Long projectStatus;
}
