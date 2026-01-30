package com.revhire.auth;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revhire.dao.EmployerDAO;
import com.revhire.dao.JobSeekerDAO;
import com.revhire.util.InputValidator;

public class AuthenticationService {

    private static final Logger logger =
        Logger.getLogger(AuthenticationService.class);

    JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
    EmployerDAO employerDAO = new EmployerDAO();

    // ================= JOB SEEKER REGISTER =================
    public void registerJobSeeker() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Full Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("❌ Invalid email format");
            logger.warn("Invalid JobSeeker email during registration: " + email);
            return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Experience (Years): ");
        int experience = sc.nextInt();
        sc.nextLine();

        System.out.print("Security Question: ");
        String secQ = sc.nextLine();

        System.out.print("Security Answer: ");
        String secA = sc.nextLine();

        boolean result = jobSeekerDAO.registerJobSeeker(
            name,
            email.toLowerCase(),
            password,
            phone,
            location,
            experience,
            secQ,
            secA
        );

        if (result) {
            System.out.println("✅ Job Seeker registered successfully");
            logger.info("JobSeeker registered: " + email);
        } else {
            System.out.println("❌ Email already exists. Please login.");
            logger.warn("Duplicate JobSeeker registration attempt: " + email);
        }
    }

    // ================= JOB SEEKER LOGIN =================
    public int loginJobSeeker() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("❌ Invalid email format");
            return -1;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        logger.info("JobSeeker login attempt: " + email);

        int jobSeekerId =
            jobSeekerDAO.loginAndGetJobSeekerId(
                email.toLowerCase(), password);

        if (jobSeekerId != -1) {
            System.out.println("✅ Login successful! Welcome Job Seeker");
            logger.info("JobSeeker login success: " + email);
        } else {
            System.out.println("❌ Invalid email or password");
            logger.warn("JobSeeker login failed: " + email);
        }

        return jobSeekerId;
    }

    // ================= EMPLOYER REGISTER =================
    public void registerEmployer() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Full Name: ");
        String companyName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            logger.warn("Invalid Employer email during registration: " + email);
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

        System.out.print("Security Question: ");
        String secQ = sc.nextLine();

        System.out.print("Security Answer: ");
        String secA = sc.nextLine();

        boolean result = employerDAO.registerEmployer(
            companyName,
            email.toLowerCase(),
            password,
            industry,
            size,
            website,
            location,
            description,
            secQ,
            secA
        );

        if (result) {
            System.out.println("✅ Employer registered successfully");
            logger.info("Employer registered: " + email);
        } else {
            System.out.println("❌ Email already exists. Please login or use another email.");
            logger.warn("Duplicate Employer registration attempt: " + email);
        }
    }

    // ================= EMPLOYER LOGIN =================
    public int loginEmployer() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!InputValidator.isValidEmail(email)) {
            System.out.println("❌ Invalid email format");
            return -1;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();

        logger.info("Employer login attempt: " + email);

        int employerId =
            employerDAO.loginAndGetEmployerId(
                email.toLowerCase(), password);

        if (employerId != -1) {
            System.out.println("✅ Login successful! Welcome Employer");
            logger.info("Employer login success: " + email);
        } else {
            System.out.println("❌ Invalid email or password");
            logger.warn("Employer login failed: " + email);
        }

        return employerId;
    }
}
