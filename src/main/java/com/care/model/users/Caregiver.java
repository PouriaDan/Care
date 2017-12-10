package com.care.model.users;

import com.care.model.request.Request;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Caregiver extends User {
    private int ratings;
    private int jobsDone;
    private Set<Request> requests;

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getJobsDone() {
        return jobsDone;
    }

    public void setJobsDone(int jobsDone) {
        this.jobsDone = jobsDone;
    }

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }
}
