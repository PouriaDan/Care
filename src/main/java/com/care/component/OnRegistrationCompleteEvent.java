package com.care.component;

import com.care.model.users.Employer;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private Employer employer;
    private String appUrl;

    public OnRegistrationCompleteEvent(Employer employer,
                                       String appUrl){
        super(employer);
        this.employer = employer;
        this.appUrl = appUrl;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
