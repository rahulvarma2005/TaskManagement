package com.resume.TaskManagement.controller;

import com.resume.TaskManagement.Entity.Project;
import com.resume.TaskManagement.Entity.User;
import com.resume.TaskManagement.Repository.UserRepository;
import com.resume.TaskManagement.Security.services.UserDetailsImpl;
import com.resume.TaskManagement.dto.ProjectRequest;
import com.resume.TaskManagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest projectRequest, @AuthenticationPrincipal UserDetailsImpl currentUser) {
        // Find the full User entity from the database using the ID from the principal
        User owner = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Error: Current user not found."));

        Project createdProject = projectService.createProject(projectRequest, owner);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjectsForUser(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        List<Project> projects = projectService.getProjectsForUser(currentUser.getId());
        return ResponseEntity.ok(projects);
    }
}