package com.care.model.request;

import com.care.model.jobs.Job;
import com.care.model.users.Caregiver;

import javax.persistence.*;

@Entity
public class Request {

    private int id;
    private Caregiver caregiver;
    private Job job;
    private int accepted;
    private String information;

    public Request() {
        this.accepted=0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    @ManyToOne
    @JoinColumn(name = "job_id")
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
