package com.smartjob.createuser.repository;

import com.smartjob.createuser.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
