package com.care.controller;

import com.care.model.users.Caregiver;
import com.care.model.users.Employer;
import com.care.service.userServices.CaregiverService;
import com.care.service.userServices.EmployerService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.model.users.User;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    private EmployerService employerService;

    @RequestMapping(value="/resume", method = RequestMethod.GET)
    public ModelAndView submitResume(){
        ModelAndView modelAndView = new ModelAndView();
        Caregiver caregiver = new Caregiver();
        modelAndView.addObject("caregiver", caregiver);
        modelAndView.setViewName("resume");
        return modelAndView;
    }

    @RequestMapping(value="/resume", method = RequestMethod.POST)
    public ModelAndView createNewCaregiver(@Valid Caregiver caregiver,
                                          BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(caregiver.getEmail());
        if(userExists!=null){
            bindingResult.rejectValue("email", "error.caregiver", "*There is already a users registered with the email provided");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("resume");
        } else {
            caregiverService.saveCaregiver(caregiver);
            return new ModelAndView("redirect:/");
        }
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Employer employer = new Employer();
        modelAndView.addObject("employer", employer);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView createNewEmployer(@Validated(User.RegisterValidator.class) Employer employer,
                                                  BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(employer.getEmail());
        if(userExists!=null){
            bindingResult.rejectValue("email", "error.employer",
                    "*There is already a users registered with the email provided");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("register");
        } else {
            employerService.saveEmployer(employer);
            return new ModelAndView("redirect:/");
        }
        return modelAndView;
    }
}
