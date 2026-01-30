package com.revhire.jobseeker;

import java.sql.ResultSet;

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
}
