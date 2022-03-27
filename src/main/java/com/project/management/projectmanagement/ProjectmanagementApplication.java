package com.project.management.projectmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectmanagementApplication.class, args);
    }

}
