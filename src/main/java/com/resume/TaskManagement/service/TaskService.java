package com.resume.TaskManagement.service;

import com.resume.TaskManagement.Entity.Project;
import com.resume.TaskManagement.Entity.Task;
import com.resume.TaskManagement.Entity.User;
import com.resume.TaskManagement.Repository.ProjectRepository;
import com.resume.TaskManagement.Repository.TaskRepository;
import com.resume.TaskManagement.Repository.UserRepository;
import com.resume.TaskManagement.dto.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Task createTask(Long projectId, TaskRequest taskRequest) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Error: Project not found."));

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        task.setProject(project);

        if (taskRequest.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskRequest.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Error: Assignee user not found."));
            task.setAssignee(assignee);
        }

        return taskRepository.save(task);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}