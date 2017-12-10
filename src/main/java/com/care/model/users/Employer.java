package com.care.model.users;

import com.care.model.jobs.Job;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Employer extends User {

    private Set<Job> jobs;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
