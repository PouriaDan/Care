package com.care.model.jobs;

import com.care.model.enums.Gender;

import javax.persistence.Entity;

@Entity
public class SeniorCare extends Job{

    private Gender seniorGender;
    private int seniorAge;
    private int isIll;
    private String Illnesses;

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

    public int getIsIll() {
        return isIll;
    }

    public void setIsIll(int isIll) {
        this.isIll = isIll;
    }

    public String getIllnesses() {
        return Illnesses;
    }

    public void setIllnesses(String illnesses) {
        Illnesses = illnesses;
    }
}
