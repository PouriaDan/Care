package com.care.repository.userRepositories;

import com.care.model.users.Employer;
import org.springframework.stereotype.Repository;

@Repository("employerRepository")
public interface EmployerRepository extends BaseUserRepository<Employer> {
}
