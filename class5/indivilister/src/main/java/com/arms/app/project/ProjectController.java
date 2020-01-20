package com.arms.app.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arms.domain.entity.Project;
import com.arms.domain.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
          List<Project> projectList = projectService.findAllProject();
          model.addAttribute("projectList", projectList);
        return "project/list";
    }

    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
    model.addAttribute("projectForm", new ProjectForm());
    return "project/create";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(@ModelAttribute ProjectForm projectForm) {
    projectService.save(projectForm);
    return "redirect:/project";
    }



}

