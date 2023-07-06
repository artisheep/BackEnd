package com.swave.releasenotesharesystem.User.repository;

import com.swave.releasenotesharesystem.User.domain.UserInProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInProjectRepository extends JpaRepository<UserInProject, Long> {
}
