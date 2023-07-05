package com.swave.releasenotesharesystem.User.repository;

import com.swave.releasenotesharesystem.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
}
