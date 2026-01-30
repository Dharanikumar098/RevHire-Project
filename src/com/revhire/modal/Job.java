package com.revhire.modal;

public class Job {

    private int jobId;
    private int employerId;
    private String title;
    private String description;
    private String location;
    private double salary;
    private String status;

    public Job() {}

    public Job(int jobId, int employerId, String title, String description,
               String location, double salary, String status) {
        this.jobId = jobId;
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.status = status;
    }

    public int getJobId() {
        return jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getEmployerId() {
        return employerId;
    }
    
    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
