package com.care.model.jobs;

import com.care.model.enums.Gender;

import javax.persistence.Entity;

@Entity
public class SeniorCare extends Job{

    private Gender seniorGender;
    private int seniorAge;
    private boolean isIll;
    private String Illnesses;

    public SeniorCare() {
        super("پرستاری سالمندان");
    }

    public Gender getSeniorGender() {
        return seniorGender;
    }

    public void setSeniorGender(Gender seniorGender) {
        this.seniorGender = seniorGender;
    }

    public int getSeniorAge() {
        return seniorAge;
    }

    public void setSeniorAge(int seniorAge) {
        this.seniorAge = seniorAge;
    }

    public boolean getIsIll() {
        return isIll;
    }

    public void setIsIll(boolean isIll) {
        this.isIll = isIll;
    }

    public String getIllnesses() {
        return Illnesses;
    }

    public void setIllnesses(String illnesses) {
        Illnesses = illnesses;
    }
}
