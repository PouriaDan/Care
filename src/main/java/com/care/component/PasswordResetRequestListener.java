package com.care.component;

import com.care.model.tokens.PasswordResetToken;
import com.care.model.users.User;
import com.care.repository.tokenRepositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordResetRequestListener implements ApplicationListener<OnPasswordResetRequestEvent> {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnPasswordResetRequestEvent event) {
        this.sendPasswordResetMail(event);
    }

    private void sendPasswordResetMail(OnPasswordResetRequestEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        PasswordResetToken passToken = passwordResetTokenRepository.findByUser(user);
        if(passToken != null) {
            passToken.setToken(token);
            passwordResetTokenRepository.save(passToken);
        } else
            passwordResetTokenRepository.save(new PasswordResetToken(user, token));

        String recipientAddress = user.getEmail();
        String subject = "Password Recovery";
        String url = event.getAppUrl() + "/accounts/changePassword?id=" +
                user.getId() + "&token=" + token;
        String message = "Hi "+user.getFirstName()+", we got a request to reset your Instagram password."+"\n"
                +"Pleas Follow this link to reset your password:"+"\n"
                +"http://localhost:8080"+url;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
