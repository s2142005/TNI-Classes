package com.arms.app.micropost;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.arms.app.home.MicropostCreateForm;
import com.arms.domain.component.ControllerAspect;
import com.arms.domain.service.MicropostService;

@Controller
public class MicropostController {

	@Autowired
	ControllerAspect controllerAspect;

	@Autowired
	MicropostService micropostService;

	@RequestMapping("/micropost/create")
	public String create(@ModelAttribute MicropostCreateForm micropostCreateForm, Principal principal,
			RedirectAttributes redirectAttributes) {
		Integer userId = micropostService.getUserId(principal);
		micropostCreateForm.setUserId(userId);
		redirectAttributes.addFlashAttribute("message", "Feed was created");
		micropostService.createMicropost(micropostCreateForm);
		return "redirect:/";
	}

	@RequestMapping("/micropost/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		micropostService.deleteMicropost(id);
		redirectAttributes.addFlashAttribute("message", "Feed was deleted");
		return "redirect:/";
	}

}
