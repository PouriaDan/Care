package com.care.controller;

import com.care.model.users.User;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/caregiver")
public class CaregiverController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView caregiverHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("welcomeMessage", "Welcome " + user.getFirstName() + " " + user.getLastName());
        modelAndView.setViewName("caregiver-profile");
        return modelAndView;
    }
}
