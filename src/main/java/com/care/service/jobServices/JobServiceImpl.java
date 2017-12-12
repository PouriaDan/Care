package com.care.service.jobServices;

import com.care.repository.jobRepositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobService")
public class JobServiceImpl {

    @Autowired
    private JobRepository jobRepository;
}