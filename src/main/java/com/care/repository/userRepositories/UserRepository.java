package com.care.repository.userRepositories;

import com.care.model.users.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends BaseUserRepository<User> {
}