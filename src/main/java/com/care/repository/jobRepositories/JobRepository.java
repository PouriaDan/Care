package com.care.repository.jobRepositories;

import com.care.model.jobs.Job;
import org.springframework.stereotype.Repository;

@Repository("jobRepository")
public interface JobRepository extends BaseJobRepository<Job> {
}
