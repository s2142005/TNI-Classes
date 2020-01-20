package com.arms.domain.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arms.app.project.ProjectForm;
import com.arms.domain.entity.Project;
import com.arms.domain.entity.Task;
import com.arms.domain.repository.ProjectRepository;
import com.arms.domain.repository.TaskRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
    TaskRepository taskRepository;


	public void save(ProjectForm projectForm) {
		Date date = Calendar.getInstance().getTime();
		Project project = new Project();
		project.setName(projectForm.getName());
		project.setCreatedDate(date);
		project.setUpdatedDate(date);
		projectRepository.save(project);
	}

	public List<Project> findAllProject() {
		return projectRepository.findAll();
	}

	public ProjectForm findProjectById(int id) {
		Project project = projectRepository.findOne(id);
		return new ProjectForm(project.getId(), project.getName());
	}
	
	public void update(ProjectForm projectForm) {
        Project project = projectRepository.findOne(projectForm.getId());
        project.setName(projectForm.getName());
        project.setUpdatedDate(Calendar.getInstance().getTime());
        projectRepository.save(project);
    }
	public void delete(int projectId) {
		taskRepository.deleteByProjectId(projectId);
		projectRepository.delete(projectId);
	    }

	public Map<Integer, Integer> calcRemainingTaskNumber(List<Project> projectList) {
        Map<Integer, Integer> remainingTaskNumberMap = new HashMap();
        for(Project project : projectList) {
            int taskCount = 0;
            for(Task task : project.getTaskList()) {
                if(!task.isStatus()) {
                    taskCount++;
                }
            }
            remainingTaskNumberMap.put(project.getId(), taskCount);
        }
        return remainingTaskNumberMap;
    }


}
