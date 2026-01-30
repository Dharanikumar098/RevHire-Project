package com.revhire.modal;

public class Employer {

    private int employerId;
    private String companyName;
    private String email;
    private String password;
    private String contactPerson;
    private String phone;

    public Employer() {}

    public Employer(int employerId, String companyName, String email,
                    String password, String contactPerson, String phone) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.contactPerson = contactPerson;
        this.phone = phone;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getContactPerson() {
        return contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
