package com.care.controller;

import com.care.model.enums.CaregiverGender;
import com.care.model.jobs.Job;
import com.care.model.request.Request;
import com.care.model.users.Caregiver;
import com.care.model.users.Employer;
import com.care.repository.jobRepositories.JobRepository;
import com.care.repository.requestRepository.RequestRepository;
import com.care.service.userServices.CaregiverService;
import com.care.service.userServices.EmployerService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.model.users.User;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(path="/addemployer")
    public @ResponseBody String addNewEmplouer() {
        Employer e = new Employer();
        e.setFirstName("Employer");
        e.setLastName("Employer");
        e.setEmail("employer@gmail.com");
        e.setPassword("1374PaRSa");
        e.setEnable(true);
        employerService.saveEmployer(e);
        return "Saved";
    }

    @GetMapping(path="/addcaregiver")
    public @ResponseBody String addNewCaregiver () {
        Caregiver e = new Caregiver();
        e.setFirstName("caregiver");
        e.setLastName("caregiver");
        e.setEmail("caregiver@gmail.com");
        e.setPassword("1374PaRSa");
        e.setEnable(true);
        caregiverService.saveCaregiver(e);
        return "Saved";
    }

    @GetMapping(path="/allusers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(path="/allcaregivers")
    public @ResponseBody Iterable<Caregiver> getAllCaregivers() {
        return caregiverService.findAllCaregivers();
    }

    @GetMapping(path="/allemployers")
    public @ResponseBody Iterable<Employer> getAllEmployers() {
        return employerService.findAllEmployers();
    }

    @GetMapping(path="/addjob")
    public @ResponseBody String addNewJob() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        Job j = new Job();
        j.setEmployer(employer);
        j.setCaregiverGender(CaregiverGender.Either);
        j.setDescription("Hichchi");
        jobRepository.save(j);
        return "Saved";
    }

    @GetMapping(path = "/alljobs")
    public @ResponseBody String getAlljobs() {
        String s="";
        Iterable<Job> jobs = jobRepository.findAll();
        for (Job j : jobs){
            s+=j.getId()+" "+j.getEmployer().getEmail();
        }
        return s;
    }

    @GetMapping(path = "/addrequest")
    public @ResponseBody String addNewRequest(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());
        Request r = new Request();
        r.setCaregiver(caregiver);
        r.setJob(jobRepository.findById(1));
        requestRepository.save(r);
        return "saved";
    }

    @GetMapping(path = "/allrequests")
    public @ResponseBody String getAllrequests() {
        String s="";
        Iterable<Request> requests = requestRepository.findAll();
        for (Request r : requests){
            s+=r.getId()+" "+r.getJob().getEmployer().getEmail()+" "+r.getCaregiver().getEmail();
        }
        return s;
    }
}