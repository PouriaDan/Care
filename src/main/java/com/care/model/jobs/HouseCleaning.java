package com.care.model.jobs;

import javax.persistence.Entity;

@Entity
public class HouseCleaning extends Job {

    private String requiredServices;
    private int pet;

    public String getRequiredServices() {
        return requiredServices;
    }

    public void setRequiredServices(String requiredServices) {
        this.requiredServices = requiredServices;
    }

    public int getPet() {
        return pet;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }
}
