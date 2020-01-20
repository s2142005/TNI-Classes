package com.arms.app.task;

import com.arms.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("task")
public class TaskController {

    
	@Autowired
    TaskService taskService;

    @RequestMapping(value = "list/{project_id}", method = RequestMethod.GET)
    public String list(@PathVariable("project_id") int projectId, Model model) {
        TaskForm taskForm = new TaskForm();
        taskForm.setProjectId(projectId);
        model.addAttribute("taskForm", taskForm);
        model.addAttribute("project", taskService.findProjectByProjectId(projectId));
        return "task/list";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute TaskForm taskForm) {
        taskService.save(taskForm);
        return "redirect:/task/list/" + taskForm.getProjectId();
    }
    
    @RequestMapping(value = "edit/{task_id}/{status}", method = RequestMethod.GET)
    public String edit(@PathVariable("task_id") int taskId, @PathVariable("status") boolean status) {
        taskService.update4Status(taskId, status);
        return "redirect:/task/list/" + taskService.findProjectByTaskId(taskId).getId();
    }
    
    @RequestMapping(value = "delete/{project_id}/{task_id}", method = RequestMethod.GET)
    public String delete(@PathVariable("project_id") int projectId, @PathVariable("task_id") int taskId) {
        taskService.delete(taskId);
        return "redirect:/task/list/" + projectId;
    }



    
}


