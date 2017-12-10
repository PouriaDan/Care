package com.care.repository.userRepositories;

import com.care.model.users.Caregiver;
import org.springframework.stereotype.Repository;

@Repository("caregiverRepository")
public interface CaregiverRepository extends BaseUserRepository<Caregiver> {
}
