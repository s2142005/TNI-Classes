package com.arms.app.user;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arms.domain.service.UserService;

@Controller
public class SignupController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/user/signUp")
	public ModelAndView signup(ModelAndView modelAndView){
		modelAndView.setViewName("user/sign_up");
		modelAndView.addObject("userAddForm", new UserAddForm());
		return modelAndView;
	}
	

	@RequestMapping("/user/add")
    public Object add(@ModelAttribute("userAddForm") @Valid UserAddForm userAddForm, BindingResult bindingResult,
                      RedirectAttributes attributes, ModelAndView modelAndView) throws NoSuchAlgorithmException {

        if(bindingResult.hasErrors()) {
            return "user/sign_up";
        }
        userService.createUser(userAddForm);
        attributes.addFlashAttribute("messageDialog", "User was created.");
        return "redirect:/user/login";
    }


}

