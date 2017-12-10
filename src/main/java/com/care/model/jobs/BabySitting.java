package com.care.model.jobs;

import javax.persistence.Entity;

@Entity
public class BabySitting extends Job {

    private int numbersOfKids_0_6m;
    private int numbersOfKids_7m_3y;
    private int numbersOfKids_4y_6y;
    private int numbersOfKids_7y_11y;
    private int numbersOfKids_12y;

    public int getNumbersOfKids_0_6m() {
        return numbersOfKids_0_6m;
    }

    public void setNumbersOfKids_0_6m(int numbersOfKids_0_6m) {
        this.numbersOfKids_0_6m = numbersOfKids_0_6m;
    }

    public int getNumbersOfKids_7m_3y() {
        return numbersOfKids_7m_3y;
    }

    public void setNumbersOfKids_7m_3y(int numbersOfKids_7m_3y) {
        this.numbersOfKids_7m_3y = numbersOfKids_7m_3y;
    }

    public int getNumbersOfKids_4y_6y() {
        return numbersOfKids_4y_6y;
    }

    public void setNumbersOfKids_4y_6y(int numbersOfKids_4y_6y) {
        this.numbersOfKids_4y_6y = numbersOfKids_4y_6y;
    }

    public int getNumbersOfKids_7y_11y() {
        return numbersOfKids_7y_11y;
    }

    public void setNumbersOfKids_7y_11y(int numbersOfKids_7y_11y) {
        this.numbersOfKids_7y_11y = numbersOfKids_7y_11y;
    }

    public int getNumbersOfKids_12y() {
        return numbersOfKids_12y;
    }

    public void setNumbersOfKids_12y(int numbersOfKids_12y) {
        this.numbersOfKids_12y = numbersOfKids_12y;
    }
}
