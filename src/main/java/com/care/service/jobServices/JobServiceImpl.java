package com.care.service.jobServices;

import com.care.model.jobs.Job;
import com.care.repository.jobRepositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Integer id){
        return jobRepository.findById(id);
    }

    @Override
    public Iterable<Job> findAllJobs(){
        return jobRepository.findAll();
    }

    @Override
    public Iterable<Job> findAvailableJobs(){
        Iterable<Job> allJobs = jobRepository.findAll();
        List<Job> availableJobs = new ArrayList<>();
        for (Job j : allJobs){
            //TODO (currentTime>JobFinishTime : add to available jobs)
        }
        return availableJobs;
    }
}