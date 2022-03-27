package com.project.management.projectmanagement.dao.projectmanager;

import com.project.management.projectmanagement.model.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectManagerDAO extends JpaRepository<ProjectManager, Long> {
    Optional<ProjectManager> findByProjectId(Long projectId);
}
