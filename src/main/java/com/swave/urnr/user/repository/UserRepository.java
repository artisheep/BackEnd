package com.swave.urnr.user.repository;

import com.swave.urnr.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

      Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User findByEmailAndProvider(String email, String provider);


}
