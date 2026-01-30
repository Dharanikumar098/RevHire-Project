package com.revhire.modal;

public class Resume {

    private int resumeId;
    private int jobSeekerId;
    private String education;
    private String experienceDetails;
    private String skills;

    public Resume() {}

    public Resume(int resumeId, int jobSeekerId, String education,
                  String experienceDetails, String skills) {
        this.resumeId = resumeId;
        this.jobSeekerId = jobSeekerId;
        this.education = education;
        this.experienceDetails = experienceDetails;
        this.skills = skills;
    }

    public int getResumeId() {
        return resumeId;
    }
    
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperienceDetails() {
        return experienceDetails;
    }
    
    public void setExperienceDetails(String experienceDetails) {
        this.experienceDetails = experienceDetails;
    }

    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
}
