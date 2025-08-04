package com.resume.TaskManagement.Repository;

import com.resume.TaskManagement.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByMembers_Id(Long userId);
}