package com.care.service.jobServices;

import com.care.model.jobs.Job;
import com.care.model.users.Employer;

import java.util.List;

public interface JobService {
    void saveJob(Job job);
    Job findJobById(Integer id);
    Iterable<Job> findAllJobs();
    Iterable<Job> findAvailableJobs();
}
