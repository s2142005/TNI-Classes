package com.arms.domain.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arms.app.project.ProjectForm;
import com.arms.domain.entity.Project;
import com.arms.domain.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

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
}
