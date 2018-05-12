package com.care.service.securityService;

public interface SecurityService {
    String validatePasswordResetToken(Integer id, String token);
}
