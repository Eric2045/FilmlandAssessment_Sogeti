package com.assessment.sogeti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String username, String password);
    User findUsername(String username);
    User findByUsername(String username);
}
