package com.arms.app.home;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arms.domain.component.ControllerAspect;
import com.arms.domain.component.PageWrapper;
import com.arms.domain.entity.Micropost;
import com.arms.domain.service.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	ControllerAspect controllerAspect;
    @Autowired
    HomeService homeService;

	@ModelAttribute
    MicropostCreateForm setMicropostCreateForm() { 
    	return new MicropostCreateForm(); 
    }
	
	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView, Principal principal, Pageable pageable){
		Integer userId = homeService.getUserId(principal);
		if(userId != null){
			modelAndView.addObject("userId", userId);
			modelAndView.addObject("userImage", homeService.findAvatar(userId));
	        modelAndView.addObject("following", homeService.getFollowingListByUserId(userId));
	        modelAndView.addObject("follower", homeService.getFollowerListByUserId(userId));
			List<Integer> micropostIdList = homeService.getMyMicropost(userId);
            homeService.setFollowingMicropost(userId, micropostIdList);

			Page<Micropost> micropostPage = homeService.findAllByIdList(micropostIdList, pageable);
                                       PageWrapper<Micropost> page = new PageWrapper<>(micropostPage, "/");
                                       modelAndView.addObject("microposts", page.getContent());
                                       modelAndView.addObject("page", page);
		}
		modelAndView.setViewName("home/home");
		return modelAndView;
	}


}

