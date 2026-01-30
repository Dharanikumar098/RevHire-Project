package com.revhire.jobseeker;

import java.sql.ResultSet;
import java.util.Scanner;

import com.revhire.dao.JobDAO;

public class JobSearchService {

    // 🔴 THIS WAS MISSING
    JobDAO jobDAO = new JobDAO();

    public void viewJobs() {

        try {
            ResultSet rs = jobDAO.getOpenJobs();

            System.out.println("\n--- AVAILABLE JOBS ---");

            boolean found = false;

            while (rs != null && rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("job_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("location") + " | " +
                    rs.getInt("salary") + " | " +
                    rs.getString("job_type")
                );
            }

            if (!found) {
                System.out.println("No open jobs available.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchJobs() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- JOB SEARCH FILTERS ---");

        System.out.print("Job Title (optional): ");
        String title = emptyToNull(sc.nextLine());

        System.out.print("Location (optional): ");
        String location = emptyToNull(sc.nextLine());

        System.out.print("Max Experience (optional): ");
        String expInput = sc.nextLine();
        Integer experience =
            expInput.isEmpty() ? null : Integer.parseInt(expInput);

        System.out.print("Company Name (optional): ");
        String company = emptyToNull(sc.nextLine());

        System.out.print("Min Salary (optional): ");
        String minSal = sc.nextLine();
        Integer minSalary =
            minSal.isEmpty() ? null : Integer.parseInt(minSal);

        System.out.print("Max Salary (optional): ");
        String maxSal = sc.nextLine();
        Integer maxSalary =
            maxSal.isEmpty() ? null : Integer.parseInt(maxSal);

        System.out.print("Job Type (Full-time / Part-time / Contract): ");
        String jobType = emptyToNull(sc.nextLine());

        try {
            ResultSet rs = jobDAO.searchJobs(
                title,
                location,
                experience,
                company,
                minSalary,
                maxSalary,
                jobType
            );

            System.out.println("\n--- SEARCH RESULTS ---");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("job_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("company_name") + " | " +
                    rs.getString("location") + " | " +
                    rs.getInt("experience") + " yrs | " +
                    rs.getInt("salary") + " | " +
                    rs.getString("job_type")
                );
            }

            if (!found) {
                System.out.println("❌ No matching jobs found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String emptyToNull(String val) {
        return (val == null || val.trim().isEmpty()) ? null : val.trim();
    }
}
