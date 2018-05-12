package com.care.controller;

import java.io.IOException;

import com.care.model.users.Caregiver;
import com.care.model.users.Employer;
import com.care.model.users.User;
import com.care.repository.userRepositories.UserRepository;
import com.care.service.storageServices.StorageFileNotFoundException;
import com.care.service.storageServices.StorageService;
import com.care.service.userServices.CaregiverService;
import com.care.service.userServices.EmployerService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/profile/picture/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload/profile/picture")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        storageService.storeProfilePicture(file, user.getId());
        Employer employer = employerService.findEmployerByEmail(user.getEmail());
        if(employer!=null)
            return "redirect:/employer/edit";
        Caregiver caregiver = caregiverService.findCaregiverByEmail(user.getEmail());
        if(caregiver!=null)
            return "redirect:/caregiver/edit";
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
