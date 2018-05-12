package com.care.model.jobs;

import javax.persistence.Entity;

@Entity
public class HouseCleaning extends Job {

    private String requiredServices;
    private boolean pet;

    public HouseCleaning() {
        super("خدمات منزل");
    }

    public String getRequiredServices() {
        return requiredServices;
    }

    public void setRequiredServices(String requiredServices) {
        this.requiredServices = requiredServices;
    }

    public boolean getPet() {
        return pet;
    }

    public void setPet(boolean pet) {
        this.pet = pet;
    }
}
