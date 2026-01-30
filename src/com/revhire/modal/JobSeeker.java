package com.revhire.modal;

public class JobSeeker {



    private int jobSeekerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String skills;
    private int experience;
    private String profileStatus;

    public JobSeeker() {}

    public JobSeeker(int jobSeekerId, String name, String email, String password,
                     String phone, String skills, int experience, String profileStatus) {
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.skills = skills;
        this.experience = experience;
        this.profileStatus = profileStatus;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getExperience() {
        return experience;
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getProfileStatus() {
        return profileStatus;
    }
    
    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }
}
