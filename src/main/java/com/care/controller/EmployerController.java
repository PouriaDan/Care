package com.care.controller;

import com.care.model.enums.JobStatue;
import com.care.model.jobs.BabySitting;
import com.care.model.jobs.HouseCleaning;
import com.care.model.jobs.Job;
import com.care.model.jobs.SeniorCare;
import com.care.model.request.Request;
import com.care.model.users.Employer;
import com.care.model.users.User;
import com.care.repository.jobRepositories.JobRepository;
import com.care.service.jobServices.JobService;
import com.care.service.userServices.EmployerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employer")
@SessionAttributes("employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;
    @Autowired
    private JobService jobService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView employerHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());

        List<Job> allEmployerJobs = new ArrayList<>(employer.getJobs());
        List<Job> doneJobs = new ArrayList<>();
        List<Job> caregiverAcceptedJobs = new ArrayList<>();
        List<Job> waitForCaregiverJobs = new ArrayList<>();
        for(Job j : allEmployerJobs){
            switch (j.getStatue()){
                case Done:
                    doneJobs.add(j);
                    break;
                case CaregiverAccepted:
                    caregiverAcceptedJobs.add(j);
                    break;
                case WaitForCaregiver:
                    waitForCaregiverJobs.add(j);
                    break;
                default:
                    break;
            }
        }

        modelAndView.addObject("employer", employer);
        modelAndView.addObject("allEmployerJobs", allEmployerJobs);
        modelAndView.addObject("doneJobs", doneJobs);
        modelAndView.addObject("caregiverAcceptedJobs", caregiverAcceptedJobs);
        modelAndView.addObject("waitForCaregiverJobs", waitForCaregiverJobs);
        modelAndView.setViewName("employer-profile");
        return modelAndView;
    }

    @RequestMapping(value = "/add/job", method = RequestMethod.GET)
    public ModelAndView addWork(){
        ModelAndView modelAndView = new ModelAndView();
        BabySitting babySittingJob = new BabySitting();
        HouseCleaning houseCleaningJob = new HouseCleaning();
        SeniorCare seniorCareJob = new SeniorCare();
        modelAndView.addObject("babySittingJob", babySittingJob);
        modelAndView.addObject("houseCleaningJob", houseCleaningJob);
        modelAndView.addObject("seniorCareJob", seniorCareJob);
        modelAndView.setViewName("add-job");
        return modelAndView;
    }

    @RequestMapping(value = "/save/senior-care", method = RequestMethod.POST)
    public ModelAndView saveSeniorCareJob(@Valid SeniorCare seniorCareJob){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        seniorCareJob.setEmployer(employer);
        seniorCareJob.setStatue(JobStatue.WaitForCaregiver);
        jobService.saveJob(seniorCareJob);
        return new ModelAndView("redirect:/employer");
    }

    @RequestMapping(value = "/save/baby-sitting", method = RequestMethod.POST)
    public ModelAndView saveBabySittingJob(@Valid BabySitting babySitting){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        babySitting.setEmployer(employer);
        babySitting.setStatue(JobStatue.WaitForCaregiver);
        jobService.saveJob(babySitting);
        return new ModelAndView("redirect:/employer");
    }

    @RequestMapping(value = "/save/house-cleaning", method = RequestMethod.POST)
    public ModelAndView saveHouseCleaningJob(@Valid HouseCleaning houseCleaning){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        System.out.println(houseCleaning.getPet());
        houseCleaning.setEmployer(employer);
        houseCleaning.setStatue(JobStatue.WaitForCaregiver);
        jobService.saveJob(houseCleaning);
        return new ModelAndView("redirect:/employer");
    }

    @RequestMapping(value = "/job/{id}/requests", method = RequestMethod.GET)
    public ModelAndView caregiverDetail(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();

        Job job = jobService.findJobById(id);
        Iterable<Request> jobsRequests = job.getRequests();

        modelAndView.addObject("job", job);
        modelAndView.addObject("jobsRequests", jobsRequests);
        modelAndView.setViewName("requests");
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        modelAndView.addObject("employer", employer);
        modelAndView.setViewName("employer-edit");
        return modelAndView;
    }

    @RequestMapping(value="/getun", method = RequestMethod.GET)
    public @ResponseBody String getUN(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User employer = (User) auth.getPrincipal();
        return employer.getEmail();
    }

    @RequestMapping(value="/setun", method = RequestMethod.GET)
    public @ResponseBody String setUN(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = (Employer) auth.getPrincipal();
        employer.setEmail("employer2@gmail.com");
        employerService.saveEmployer(employer);
        return employer.getEmail();
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
