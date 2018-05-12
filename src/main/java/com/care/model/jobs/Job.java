package com.care.model.jobs;

import com.care.model.enums.CaregiverGender;
import com.care.model.enums.JobStatue;
import com.care.model.request.Request;
import com.care.model.users.Employer;

import javax.persistence.*;
import java.util.Set;

@Entity
public abstract class Job {

    private Integer id;
    private String type;
    private String date;
    private String startTime;
    private String finishTime;
    private Employer employer;
    private String title;
    private CaregiverGender caregiverGender;
    private int pay;
    private String Description;
    private JobStatue statue;
    private Set<Request> requests;
    private Request acceptedRequest;

    public Job(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CaregiverGender getCaregiverGender() {
        return caregiverGender;
    }

    public void setCaregiverGender(CaregiverGender caregiverGender) {
        this.caregiverGender = caregiverGender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public JobStatue getStatue() {
        return statue;
    }

    public void setStatue(JobStatue statue) {
        this.statue = statue;
    }

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @OneToOne(targetEntity = Request.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    public Request getAcceptedRequest() {
        return acceptedRequest;
    }

    public void setAcceptedRequest(Request acceptedRequest) {
        this.acceptedRequest = acceptedRequest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
