package com.care.service.securityService;

import com.care.model.tokens.PasswordResetToken;
import com.care.model.users.User;
import com.care.repository.tokenRepositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public String validatePasswordResetToken(Integer id, String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if((passwordResetToken == null) || !passwordResetToken.getUser().getId().equals(id))
            return "Invalid token";

        User user = passwordResetToken.getUser();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null,
                        Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Valid token";
    }
}
