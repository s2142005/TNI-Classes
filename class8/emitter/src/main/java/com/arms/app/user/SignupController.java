package com.arms.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

	@RequestMapping("/user/signUp")
	public ModelAndView signup(ModelAndView modelAndView){
		modelAndView.setViewName("user/sign_up");
		return modelAndView;
	}
}
