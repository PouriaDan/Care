package com.care.controller;

import com.care.model.users.Employer;
import com.care.service.userServices.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employer")
@SessionAttributes("employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView employerHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        modelAndView.addObject("welcomeMessage", "Welcome " + employer.getFirstName() + " " + employer.getLastName());
        modelAndView.setViewName("employer-profile");
        return modelAndView;
    }

//    @RequestMapping(value="/edit", method = RequestMethod.GET)
//    public ModelAndView registration(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Employer employer = employerService.findEmployerByEmail(auth.getName());
//        modelAndView.addObject("employer", employer);
//        modelAndView.addObject("postalCode", employer.getPostalCode());
//        modelAndView.setViewName("employer-edit");
//        return modelAndView;
//    }
//
//    @RequestMapping(value="/edit", method = RequestMethod.POST)
//    public ModelAndView createNewEmployer(@Validated(User.EditValidator.class) Employer employer,
//                                          BindingResult bindingResult){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Employer employerExist = employerService.findEmployerByEmail(auth.getName());
//        if(!employer.getPassword().equals("") && !employerExist.getPassword().equals(employer.getPassword())){
//            bindingResult.rejectValue("password", "error.password",
//                    "*Incorrect password");
//        }
//        if(bindingResult.hasErrors()){
//            modelAndView.setViewName("employer-edit");
//        } else {
//            employerService.updateEmployer(employerExist, employer);
//            return new ModelAndView("redirect:/employer");
//        }
//        return modelAndView;
//    }
}
