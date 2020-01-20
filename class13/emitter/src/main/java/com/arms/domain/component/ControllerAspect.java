package com.arms.domain.component;

import com.arms.domain.service.AppService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Component
@Aspect
public class ControllerAspect {

    @Autowired
    AppService appService;

    

    @Before("execution(* com.arms.app.*.*Controller.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        Principal principal = null;
        ModelAndView modelAndView = null;
        for(Object object : joinPoint.getArgs()) {
            if(object instanceof Principal) {
                principal = (Principal) object;

            } else if(object instanceof ModelAndView) {
                modelAndView = (ModelAndView) object;
            }
        }

        if(principal != null && modelAndView != null) {
            setIsLogin(modelAndView, principal);
        }
    }

    private void setIsLogin(ModelAndView modelAndView, Principal principal) {
        Authentication auth = (Authentication) principal;
        if(auth != null && auth.isAuthenticated()) {
            modelAndView.addObject("isLogin", true);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            modelAndView.addObject("userInfo", userDetails);
            modelAndView.addObject("loginUser", appService.findOne(principal));
        } else {
            modelAndView.addObject("isLogin", false);
        }
    }
}


