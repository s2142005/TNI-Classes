package com.arms.domain.service;

import com.arms.app.task.TaskForm;
import com.arms.domain.entity.Project;
import com.arms.domain.entity.Task;
import com.arms.domain.repository.ProjectRepository;
import com.arms.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TaskService {


    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    public Project findProjectByProjectId(int projectId) {
        return projectRepository.findOne(projectId);
    }
    
    public void save(TaskForm taskForm) {
        Date date = Calendar.getInstance().getTime();
        Task task = new Task();
        task.setName(taskForm.getName());
        task.setProject(projectRepository.findOne(taskForm.getProjectId()));
        task.setStatus(false);
        task.setCreatedDate(date);
        task.setUpdatedDate(date);
        taskRepository.save(task);
    }
    public void update4Status(int taskId, boolean status) {
        Task task = taskRepository.findOne(taskId);
        task.setStatus(status);
        taskRepository.save(task);
    }
    
    public Project findProjectByTaskId(int taskId) {
        Task task = taskRepository.findOne(taskId);
        return task.getProject();
    }
    
    public void delete(int taskId) {
        taskRepository.delete(taskId);
    }




}


