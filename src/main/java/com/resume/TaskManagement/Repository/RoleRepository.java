package com.resume.TaskManagement.Repository;

import com.resume.TaskManagement.Entity.Role;
import com.resume.TaskManagement.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}