package com.arms.app.user;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arms.domain.component.PageWrapper;
import com.arms.domain.entity.Micropost;
import com.arms.domain.entity.User;
import com.arms.domain.service.UserService;

@Controller
public class UserController {
	
	@Autowired
    UserService userService;

	 @RequestMapping("/user/list")
	    public ModelAndView list(ModelAndView modelAndView, Principal principal, Pageable pageable) {
	        Page<User> userPage = userService.findAllUser(pageable);
	        PageWrapper<User> page = new PageWrapper<>(userPage, "/user/list");
	        modelAndView.addObject("users", page.getContent());
	        modelAndView.addObject("page", page);
	        modelAndView.addObject("userId", userService.getUserId(principal));
	        modelAndView.setViewName("user/list");
	        return modelAndView;
	    }
	 @RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable int userId, ModelAndView modelAndView, Principal principal) {
	    	modelAndView.addObject("user", userService.findOne(userId));
	        modelAndView.addObject("userEditForm", userService.setUserEditForm(userId));
	    	modelAndView.setViewName("user/edit");
	        return modelAndView;
	    }
	 @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	    public Object edit(@Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelAndView modelAndView, Principal principal) throws NoSuchAlgorithmException {

	        if(bindingResult.hasErrors()) {
	            modelAndView.addObject("user", userService.findOne(userEditForm.getUserId()));
	            modelAndView.setViewName("user/edit");
	            return modelAndView;
	        }

	        userService.updateUser(userEditForm);
	        redirectAttributes.addFlashAttribute("message", "User was updated.");
	        return "redirect:/";
	    }	

	 @RequestMapping(value = "/user/delete/{userId}")
	    public String delete(@PathVariable int userId, RedirectAttributes redirectAttributes) {
	        userService.deleteUser(userId);
	        redirectAttributes.addFlashAttribute("message", "User was deleted.");
	        return "redirect:/";
	    }
	 @RequestMapping("/user/show/{userId}")
	    public ModelAndView show(@PathVariable int userId, ModelAndView modelAndView, Pageable pageable, Principal principal){
	        modelAndView.addObject("following", userService.getFollowingListByUserId(userId));
	        modelAndView.addObject("follower", userService.getFollowerListByUserId(userId));		 
		 	User user = userService.findOne(userId);
	        modelAndView.addObject("user", user);
	        User loginUser = userService.findOne(principal);
	        if(user.getId() != loginUser.getId()) {
	            modelAndView.addObject("isFollow", userService.isFollow(user.getId(), loginUser.getId()));
	            modelAndView.addObject("userFollowForm", userService.getUserFollowForm(user.getId(), loginUser.getId()));
	        }
	        Page<Micropost> micropostPage = userService.findAllMicropostByUserId(userId, pageable);
	        PageWrapper<Micropost> page = new PageWrapper<>(micropostPage, "/user/show" + '/' + userId);
	        modelAndView.addObject("microposts", page.getContent());
	        modelAndView.addObject("page", page);
	        modelAndView.setViewName("user/show");
	        return modelAndView;
	    }
	 @RequestMapping(value = "/user/following/{userId}")
	    public ModelAndView following(@PathVariable int userId, ModelAndView modelAndView, Principal principal, Pageable pageable) {
	        User user = userService.findOne(userId);
	        modelAndView.addObject("user", user);
	        modelAndView.addObject("follower", userService.getFollowerListByUserId(user.getId()));
	        modelAndView.addObject("following", userService.getFollowingListByUserId(user.getId()));
	        Page<User> userPage = userService.findAllFollowing(user.getId(), pageable);
	        PageWrapper<User> page = new PageWrapper<>(userPage, "/user/following" + '/' + userId);
	        modelAndView.addObject("followings", page.getContent());
	        modelAndView.addObject("page", page);
	        modelAndView.setViewName("user/following");
	        return modelAndView;
	    }
	 @RequestMapping(value = "/user/followers/{userId}")
	    public ModelAndView follower(@PathVariable int userId, ModelAndView modelAndView, Principal principal, Pageable pageable) {
	        User user = userService.findOne(userId);
	        modelAndView.addObject("user", user);
	        modelAndView.addObject("following", userService.getFollowingListByUserId(user.getId()));
	        modelAndView.addObject("follower", userService.getFollowerListByUserId(user.getId()));
	        Page<User> userPage = userService.findAllFollower(user.getId(), pageable);
	        PageWrapper<User> page = new PageWrapper<>(userPage, "/user/followers" + '/' + userId);
	        modelAndView.addObject("followers", page.getContent());
	        modelAndView.addObject("page", page);
	        modelAndView.setViewName("user/followers");
	        return modelAndView;
	    }

	 @RequestMapping(value = "/user/follow")
	    public String follow(@ModelAttribute("userFollowForm") UserFollowForm userFollowForm) {
	        userService.addFollow(userFollowForm);
	        return "redirect:/user/show/" + userFollowForm.getUserId();
	    }

	    @RequestMapping(value = "/user/unfollow")
	    public String unFollow(@ModelAttribute("userFollowForm") UserFollowForm userFollowForm) {
	        userService.deleteFollow(userFollowForm);
	        return "redirect:/user/show/" + userFollowForm.getUserId();
	    }





}
