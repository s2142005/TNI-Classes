package com.arms.app.home;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arms.domain.component.ControllerAspect;

@Controller
public class HomeController {
	
	@Autowired
	ControllerAspect controllerAspect;
	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView, Principal principal){
//		controllerAspect.setIsLogin(modelAndView, principal);
		modelAndView.setViewName("home/home");
		return modelAndView;
	}

}

