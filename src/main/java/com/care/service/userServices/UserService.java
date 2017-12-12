package com.care.service.userServices;

import com.care.model.users.User;
import com.care.model.verification.VerificationToken;

public interface UserService {
    public Iterable<User> findAllUsers();
    public User findUserByEmail(String email);
}
