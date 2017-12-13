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
    private Employer employer;
    private CaregiverGender caregiverGender;
    private int pay;
    private String Description;
    private JobStatue statue;
    private Set<Request> requests;

//    private int jobType;
//    private String date;
//    private String startDate;
//    private String finishDate;
//    private String from;
//    private String until;

    public CaregiverGender getCaregiverGender() {
        return caregiverGender;
    }

    public void setCaregiverGender(CaregiverGender caregiverGender) {
        this.caregiverGender = caregiverGender;
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

//    public int getJobType() {
//        return jobType;
//    }
//
//    public void setJobType(int jobType) {
//        this.jobType = jobType;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
//
//    public String getFinishDate() {
//        return finishDate;
//    }
//
//    public void setFinishDate(String finishDate) {
//        this.finishDate = finishDate;
//    }
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getUntil() {
//        return until;
//    }
//
//    public void setUntil(String until) {
//        this.until = until;
//    }
}
