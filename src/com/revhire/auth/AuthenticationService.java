package com.revhire.auth;

import java.util.Scanner;


import com.revhire.dao.EmployerDAO;
import com.revhire.dao.JobSeekerDAO;
import com.revhire.util.InputValidator;

public class AuthenticationService {

    JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
    EmployerDAO employerDAO = new EmployerDAO();
    public void registerJobSeeker() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Full Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (jobSeekerDAO.registerJobSeeker(name, email.toLowerCase(), password)) {
            System.out.println("Job Seeker registered successfully");
        } else {
            System.out.println("Registration failed");
        }
    }

    
    public int loginJobSeeker() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            return -1;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        return jobSeekerDAO.loginAndGetJobSeekerId(
            email.toLowerCase(), password);
    }

    public void registerEmployer() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Company Name: ");
        String companyName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Industry: ");
        String industry = sc.nextLine();

        System.out.print("Company Size: ");
        String size = sc.nextLine();

        System.out.print("Website: ");
        String website = sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        if (employerDAO.registerEmployer(
                companyName,
                email.toLowerCase(),
                password,
                industry,
                size,
                website,
                location,
                description)) {

            System.out.println("Employer registered successfully");

        } else {
            System.out.println("Employer registration failed");
        }
    }

    public int loginEmployer() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            return -1;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        return employerDAO.loginAndGetEmployerId(
            email.toLowerCase(), password);
    }

    public void changeJobSeekerPassword(int jobSeekerId) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Current Password: ");
        String oldPwd = sc.nextLine();

        System.out.print("New Password: ");
        String newPwd = sc.nextLine();

        if (jobSeekerDAO.changePassword(jobSeekerId, oldPwd, newPwd)) {
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Current password incorrect");
        }
    }

    public void changeEmployerPassword(int employerId) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Current Password: ");
        String oldPwd = sc.nextLine();

        System.out.print("New Password: ");
        String newPwd = sc.nextLine();

        EmployerDAO employerDAO = new EmployerDAO();

        if (employerDAO.changePassword(employerId, oldPwd, newPwd)) {
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Current password incorrect");
        }
    }

}
