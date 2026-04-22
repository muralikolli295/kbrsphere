package com.kbrsphere.user_management.repository;

import com.kbrsphere.user_management.dto.Role;
import com.kbrsphere.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUserEmail(String userEmail);
    boolean existsByRole(Role role);
    boolean existsByUserEmail(String userEmail);
    List<User> findAllByRole(Role role);
}
