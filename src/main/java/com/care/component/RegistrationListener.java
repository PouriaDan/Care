package com.care.component;

import com.care.model.users.Employer;
import com.care.model.users.User;
import com.care.model.verification.VerificationToken;
import com.care.repository.verificationRepository.VerificationTokenRepository;
import com.care.service.userServices.EmployerService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmployerService employerService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.sendActivationMail(event);
    }

    private void sendActivationMail(OnRegistrationCompleteEvent event){
        Employer employer = event.getEmployer();
        String token = UUID.randomUUID().toString();
        //VerificationToken verificationToken = verificationTokenRepository.findByEmployer(employer);
        verificationTokenRepository.save(new VerificationToken(employer, token));

        String recipientAddress = employer.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl()+"/registrationConfirm?token="+token;
        String message = "Hi "+employer.getFirstName() + ", thank you for registering at care.ir"+"\n"
                +"Pleas Follow this link to activate your account:"+"\n"
                +"http://localhost:8080"+confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
