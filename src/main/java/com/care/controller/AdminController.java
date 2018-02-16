package com.care.controller;

import com.care.model.users.Caregiver;
import com.care.model.users.User;
import com.care.service.userServices.CaregiverService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaregiverService caregiverService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView adminHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("welcomeMessage",
                "Welcome " + user.getFirstName() + " " + user.getLastName());
        modelAndView.setViewName("admin-panel");
        return modelAndView;
    }

    @RequestMapping(value = "/caregivers-list", method = RequestMethod.GET)
    public ModelAndView caregiversList(){
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Caregiver> caregivers = caregiverService.findAllCaregivers();
        modelAndView.addObject("caregivers", caregivers);
        modelAndView.setViewName("caregivers-list-page");
        return modelAndView;
    }

    @RequestMapping(value = "/caregiver-detail/{id}", method = RequestMethod.GET)
    public ModelAndView caregiverDetail(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Caregiver caregiver = caregiverService.findCaregiverById(id);
        modelAndView.addObject("caregiver", caregiver);
        modelAndView.setViewName("caregiver-detail");
        return modelAndView;
    }

    @RequestMapping(value="/activate-caregiver/{id}", method = RequestMethod.POST)
    public ModelAndView activateCaregiver(@PathVariable("id") int id){
        Caregiver caregiver = caregiverService.findCaregiverById(id);
        caregiverService.enableCaregiver(caregiver, true);
        return new ModelAndView("redirect:/admin/caregiver-detail/"+id);
    }

    @RequestMapping(value="/deactivate-caregiver/{id}", method = RequestMethod.POST)
    public ModelAndView deactivateCaregiver(@PathVariable("id") int id){
        Caregiver caregiver = caregiverService.findCaregiverById(id);
        caregiverService.enableCaregiver(caregiver, false);
        return new ModelAndView("redirect:/admin/caregiver-detail/"+id);
    }
}
