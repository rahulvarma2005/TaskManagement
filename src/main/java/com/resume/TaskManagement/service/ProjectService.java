package com.resume.TaskManagement.service;

import com.resume.TaskManagement.Entity.Project;
import com.resume.TaskManagement.Entity.User;
import com.resume.TaskManagement.Repository.ProjectRepository;
import com.resume.TaskManagement.Repository.UserRepository;
import com.resume.TaskManagement.dto.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Project createProject(ProjectRequest projectRequest, User owner) {
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setOwner(owner);

        // Automatically add the owner as a member of the project
        Set<User> members = new HashSet<>();
        members.add(owner);
        project.setMembers(members);

        return projectRepository.save(project);
    }

    public List<Project> getProjectsForUser(Long userId) {
        return projectRepository.findByMembers_Id(userId);
    }
}
