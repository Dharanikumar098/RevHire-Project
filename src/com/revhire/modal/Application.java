package com.revhire.modal;

import java.util.Date;

public class Application {

    private int applicationId;
    private int jobId;
    private int jobSeekerId;
    private Date appliedDate;
    private String status;

    public Application() {}

    public Application(int applicationId, int jobId, int jobSeekerId,
                       Date appliedDate, String status) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }
    
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }
    
    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
