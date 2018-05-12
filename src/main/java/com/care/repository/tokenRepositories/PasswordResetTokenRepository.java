package com.care.repository.tokenRepositories;

import com.care.model.tokens.PasswordResetToken;
import com.care.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("passwordResetTokenRepository")
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
