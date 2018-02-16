package com.care.controller;

import com.care.model.users.Caregiver;
import com.care.service.userServices.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/caregiver")
@SessionAttributes("caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView caregiverHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());
        modelAndView.addObject("welcomeMessage", "Welcome " + caregiver.getFirstName() + " " + caregiver.getLastName());
        modelAndView.setViewName("caregiver-profile");
        return modelAndView;
    }
}
