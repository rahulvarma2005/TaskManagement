package com.resume.TaskManagement.controller;

import com.resume.TaskManagement.Entity.Task;
import com.resume.TaskManagement.dto.TaskRequest;
import com.resume.TaskManagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long projectId, @Valid @RequestBody TaskRequest taskRequest) {
        Task createdTask = taskService.createTask(projectId, taskRequest);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<Task>> getTasksForProject(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }
}

