package com.project.management.projectmanagement.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long teamId;
    private String deviceEmail;
    private String phone;
    private Integer userStatus;
    private Integer userRole;
    private Long createTime;
}
