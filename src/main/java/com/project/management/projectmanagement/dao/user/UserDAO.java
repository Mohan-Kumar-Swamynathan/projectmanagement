package com.project.management.projectmanagement.dao.user;

import com.project.management.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<List<User>> findUserByTeamId(long teamId);
}
