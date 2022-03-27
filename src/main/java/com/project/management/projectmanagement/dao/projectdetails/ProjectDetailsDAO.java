package com.project.management.projectmanagement.dao.projectdetails;

import com.project.management.projectmanagement.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailsDAO extends JpaRepository<ProjectDetails, Long> {
}
