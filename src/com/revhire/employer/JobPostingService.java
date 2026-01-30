package com.revhire.employer;

import java.sql.ResultSet;
import java.util.Scanner;
import com.revhire.dao.ApplicationDAO;
import com.revhire.dao.JobDAO;

public class JobPostingService {

    JobDAO jobDAO = new JobDAO();

    public void postJob(int employerId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- POST JOB ---");

        System.out.print("Job Title: ");
        String title = sc.nextLine();

        System.out.print("Job Description: ");
        String description = sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Salary: ");
        int salary = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Job Type (Full-Time / Part-Time / Intern): ");
        String jobType = sc.nextLine();

        if (jobDAO.createJob(
                employerId,
                title,
                description,
                location,
                salary,
                jobType)) {

            System.out.println("✅ Job posted successfully");

        } else {
            System.out.println("❌ Failed to post job");
        }
    }
    public void manageJobs(int employerId) {

        try {
            ResultSet rs = jobDAO.getJobsByEmployer(employerId);

            System.out.println("\n--- MY JOBS ---");

            boolean found = false;
            while (rs != null && rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("job_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("No jobs posted yet.");
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter Job ID to close (0 to skip): ");
            int jobId = sc.nextInt();

            if (jobId != 0) {
                if (jobDAO.closeJob(jobId, employerId)) {
                    System.out.println("✅ Job closed successfully");
                } else {
                    System.out.println("❌ Failed to close job");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewApplicants(int employerId) {

        Scanner sc = new Scanner(System.in);
        ApplicationDAO applicationDAO = new ApplicationDAO();

        System.out.print("Enter Job ID to view applicants: ");
        int jobId = sc.nextInt();

        try {
            ResultSet rs =
                applicationDAO.getApplicantsForJob(jobId, employerId);

            System.out.println("\n--- APPLICANTS ---");

            boolean found = false;

            while (rs != null && rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("application_id") + " | " +
                    rs.getString("full_name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("status") + " | " +
                    rs.getDate("applied_date")
                );
            }

            if (!found) {
                System.out.println("No applicants found for this job.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
