package com.care.repository.verificationRepository;

import com.care.model.users.Employer;
import com.care.model.verification.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tokenRepository")
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByEmployer(Employer employer);
}