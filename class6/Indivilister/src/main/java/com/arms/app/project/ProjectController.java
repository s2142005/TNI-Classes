package com.arms.app.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
          model.addAttribute("projectRemainingTaskMap", projectService.calcRemainingTaskNumber(projectList));
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
    
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("projectForm", projectService.findProjectById(id));
        return "project/edit";
    }
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Object edit(@ModelAttribute ProjectForm projectForm) {
        projectService.update(projectForm);
        return "redirect:/project";
    }
    
    @RequestMapping(value = "delete/{id}",  method = RequestMethod.GET)
    public String delete(@PathVariable("id") int projectId) {
        projectService.delete(projectId);
        return "redirect:/project";
    }




}

