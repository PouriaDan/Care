package com.care.controller;

import com.care.model.enums.CaregiverGender;
import com.care.model.enums.Gender;
import com.care.model.enums.JobStatue;
import com.care.model.jobs.BabySitting;
import com.care.model.jobs.HouseCleaning;
import com.care.model.jobs.Job;
import com.care.model.jobs.SeniorCare;
import com.care.model.request.Request;
import com.care.model.tokens.PasswordResetToken;
import com.care.model.users.Caregiver;
import com.care.model.users.Employer;
import com.care.repository.jobRepositories.BabysittingRepository;
import com.care.repository.jobRepositories.HouseCleaningRepository;
import com.care.repository.jobRepositories.JobRepository;
import com.care.repository.jobRepositories.SeniorCareRepository;
import com.care.repository.requestRepository.RequestRepository;
import com.care.repository.tokenRepositories.PasswordResetTokenRepository;
import com.care.repository.userRepositories.UserRepository;
import com.care.service.userServices.CaregiverService;
import com.care.service.userServices.EmployerService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.care.model.users.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private BabysittingRepository babysittingRepository;
    @Autowired
    private HouseCleaningRepository houseCleaningRepository;
    @Autowired
    private SeniorCareRepository seniorCareRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @RequestMapping(value="/addnewusers", method = RequestMethod.GET)
    public @ResponseBody String addNewUsers() {
        Employer e = new Employer();
        e.setFirstName("Employer");
        e.setLastName("Employer");
        e.setEmail("employer@gmail.com");
        e.setPassword("1374PaRSa");
        e.setEnable(true);
        employerService.saveEmployer(e);
        Caregiver c = new Caregiver();
        c.setFirstName("caregiver");
        c.setLastName("caregiver");
        c.setEmail("caregiver@gmail.com");
        c.setPassword("1374PaRSa");
        c.setEnable(true);
        caregiverService.saveCaregiver(c);
        return "Saved";
    }

    @RequestMapping(value="/addemployer", method = RequestMethod.GET)
    public @ResponseBody String addNewEmployer() {
        Employer e = new Employer();
        e.setFirstName("علیرضا");
        e.setLastName("ایرانشاهی");
        e.setEmail("employer@gmail.com");
        e.setPassword("1374PaRSa");
        e.setCity("تهران");
        e.setAddress("سعادت آباد");
        e.setPostalCode("1997984157");
        e.setNumber("22061101");
        e.setGender(Gender.Male);
        e.setEnable(true);
        employerService.saveEmployer(e);
        return "Saved";
    }

    @RequestMapping(value="/addcaregiver", method = RequestMethod.GET)
    public @ResponseBody String addNewCaregiver() {
        Caregiver c = new Caregiver();
        c.setFirstName("زیبا");
        c.setLastName("باریک‌بین");
        c.setCity("تهران");
        c.setAddress("نارمک");
        c.setEmail("caregiver@gmail.com");
        c.setPassword("1374PaRSa");
        c.setPostalCode("1997984157");
        c.setNumber("22061101");
        c.setGender(Gender.Female);
        c.setEnable(true);
        caregiverService.saveCaregiver(c);
        return "Saved";
    }

    @RequestMapping(value="/addadmin", method = RequestMethod.GET)
    public @ResponseBody String addNewAdmin() {
        User a = new User();
        a.setFirstName("admin");
        a.setLastName("admin");
        a.setEmail("admin@gmail.com");
        a.setPassword("1374PaRSa");
        a.setEnable(true);
        userService.saveAdmin(a);
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

    @GetMapping(path="/addbsjob")
    public @ResponseBody String addNewBSJob() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        BabySitting j = new BabySitting();
        j.setEmployer(employer);
        j.setCaregiverGender(CaregiverGender.Either);
        j.setTitle("bachche dari");
        j.setDescription("negahdari az bachcheha");
        j.setPay(500);
        j.setStatue(JobStatue.WaitForCaregiver);
        j.setNumbersOfKids_0_6m(1);
        j.setNumbersOfKids_7m_3y(0);
        j.setNumbersOfKids_4y_6y(2);
        j.setNumbersOfKids_7y_11y(0);
        j.setNumbersOfKids_12y(3);
        jobRepository.save(j);
        return "Saved";
    }

    @GetMapping(path="/addhcjob")
    public @ResponseBody String addNewHCJob() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        HouseCleaning j = new HouseCleaning();
        j.setEmployer(employer);
        j.setCaregiverGender(CaregiverGender.Either);
        j.setTitle("khane dari");
        j.setDescription("nezafate khane");
        j.setPay(200);
        j.setStatue(JobStatue.WaitForCaregiver);
        j.setPet(true);
        j.setRequiredServices("zarf shostan, tamiz kardan khane");
        jobRepository.save(j);
        return "Saved";
    }

    @GetMapping(path="/addscjob")
    public @ResponseBody String addNewSCJob() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employer employer = employerService.findEmployerByEmail(auth.getName());
        SeniorCare j = new SeniorCare();
        j.setEmployer(employer);
        j.setCaregiverGender(CaregiverGender.Either);
        j.setTitle("moraghebat az salmandan");
        j.setDescription("salmand care");
        j.setPay(200);
        j.setStatue(JobStatue.WaitForCaregiver);
        j.setSeniorGender(Gender.Male);
        j.setSeniorAge(70);
        j.setIsIll(false);
        j.setIllnesses("diabetes, cancer, HIV");
        jobRepository.save(j);
        return "Saved";
    }

    @GetMapping(path = "/allbsjobs")
    public @ResponseBody String getAllbsjobs() {
        String s="";
        Iterable<BabySitting> jobs = babysittingRepository.findAll();
        for (Job j : jobs){
            s+=j.getId()+" "+j.getEmployer().getEmail();
        }
        return s;
    }

    @GetMapping(path = "/allhcjobs")
    public @ResponseBody String getAllhcjobs() {
        String s="";
        Iterable<HouseCleaning> jobs = houseCleaningRepository.findAll();
        for (Job j : jobs){
            s+=j.getId()+" "+j.getEmployer().getEmail();
        }
        return s;
    }

    @GetMapping(path = "/allscjobs")
    public @ResponseBody String getAllscjobs() {
        String s="";
        Iterable<SeniorCare> jobs = seniorCareRepository.findAll();
        for (Job j : jobs){
            s+=j.getId()+" "+j.getEmployer().getEmail()+" "+j.getType();
        }
        return s;
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
    public @ResponseBody String getAllRequests() {
        String s="";
        Iterable<Request> requests = requestRepository.findAll();
        for (Request r : requests){
            s+=r.getId()+" "+r.getJob().getEmployer().getEmail()+" "+r.getCaregiver().getEmail();
        }
        return s;
    }

    @GetMapping(path = "/allpasstokens")
    public @ResponseBody String getAllPassTokens(){
        String s = "";
        Iterable<PasswordResetToken> tokens = passwordResetTokenRepository.findAll();
        for (PasswordResetToken token : tokens)
            s+=token.getUser().getEmail()+" "+token.getToken();
        return s;
    }

    @GetMapping(path = "/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test", 2);
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @GetMapping(path = "/who")
    public @ResponseBody String who(){
        User user = userRepository.findById(2);

        Iterable<User> users = userService.findAllUsers();
        List<User> allusers = new ArrayList<>();
        for(User u : users)
            allusers.add(u);
        System.out.println(allusers.size());

        return user.toString();
    }
}