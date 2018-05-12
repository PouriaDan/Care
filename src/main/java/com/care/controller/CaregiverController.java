package com.care.controller;

import com.care.model.enums.CaregiverGender;
import com.care.model.enums.Gender;
import com.care.model.enums.JobStatue;
import com.care.model.jobs.Job;
import com.care.model.request.Request;
import com.care.model.users.Caregiver;
import com.care.repository.requestRepository.RequestRepository;
import com.care.service.jobServices.JobService;
import com.care.service.userServices.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/caregiver")
@SessionAttributes("caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private JobService jobService;
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView caregiverHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());

        List<Request> allCaregiverRequests = new ArrayList<>(caregiver.getRequests());
        List<Request> pendingRequests = new ArrayList<>();
        List<Request> acceptedRequests = new ArrayList<>();
        List<Request> finishedJobs = new ArrayList<>();

        for (Request request : allCaregiverRequests) {
            if(request.getAccepted()==1)
                acceptedRequests.add(request);
            else
                pendingRequests.add(request);
        }

        for(Request request : acceptedRequests){
            if(request.getJob().getStatue()== JobStatue.Done){
                pendingRequests.remove(request);
                finishedJobs.add(request);
            }
        }

        modelAndView.addObject("caregiver", caregiver);
        modelAndView.addObject("pendingRequests", pendingRequests);
        modelAndView.addObject("acceptedRequests", acceptedRequests);
        modelAndView.addObject("finishedJobs", finishedJobs);
        modelAndView.setViewName("caregiver-profile");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView findJob(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());

        ModelAndView modelAndView = new ModelAndView();
        Iterable<Job> allJobs = jobService.findAllJobs();
        List<Job> allAvailableJobs = new ArrayList<>();

        for (Job job : allJobs)
            if(job.getStatue()!=JobStatue.Done && genderMatch(caregiver, job))
                allAvailableJobs.add(job);

        modelAndView.addObject("allAvailableJobs", allAvailableJobs);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "/view/job/{id}", method = RequestMethod.GET)
    public String viewJobInfo(@PathVariable("id") Integer id, ModelMap modelMap){
        Job job = jobService.findJobById(id);
        modelMap.addAttribute("job", job);
        return "fragments :: jobDetail";
    }

    @RequestMapping(value = "/send/request", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void sendRequest(@RequestParam Integer id, @RequestParam String requestInfo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());
        Request request = new Request();
        request.setJob(jobService.findJobById(id));
        request.setCaregiver(caregiver);
        request.setInformation(requestInfo);
        System.out.println(id+" "+requestInfo);
        requestRepository.save(request);
    }

    private boolean genderMatch(Caregiver caregiver, Job job){
        if (job.getCaregiverGender().equals(CaregiverGender.Either))
            return true;
        if (caregiver.getGender().equals(Gender.Male))
            if(job.getCaregiverGender().equals(CaregiverGender.Male))
                return true;
        if (caregiver.getGender().equals(Gender.Female))
            if(job.getCaregiverGender().equals(CaregiverGender.Female))
                return true;
        return false;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Caregiver caregiver = caregiverService.findCaregiverByEmail(auth.getName());
        modelAndView.addObject("caregiver", caregiver);
        modelAndView.setViewName("caregiver-edit");
        return modelAndView;
    }
}