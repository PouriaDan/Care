package com.care.repository.jobRepositories;

import com.care.model.jobs.SeniorCare;
import org.springframework.stereotype.Repository;

@Repository("seniorCareRepository")
public interface SeniorCareRepository extends BaseJobRepository<SeniorCare> {
}
