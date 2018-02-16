package com.care.service.userServices;

import com.care.model.users.User;

public interface UserService {
    public Iterable<User> findAllUsers();
    public User findUserByEmail(String email);
    public void saveAdmin(User user);
}
