package com.revhire.jobseeker;

import java.sql.ResultSet;

import com.revhire.dao.JobSeekerDAO;
import com.revhire.dao.ResumeDAO;

public class JobSeekerService {

    JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();

    // 🔴 THIS WAS MISSING
    ResumeDAO resumeDAO = new ResumeDAO();

    public void viewProfile(int jobSeekerId) {

        try {
            ResultSet rs = jobSeekerDAO.getJobSeekerProfile(jobSeekerId);

            if (rs != null && rs.next()) {
                System.out.println("\n--- JOB SEEKER PROFILE ---");
                System.out.println("ID    : " + rs.getInt("job_seeker_id"));
                System.out.println("Name  : " + rs.getString("full_name"));
                System.out.println("Email : " + rs.getString("email"));

                // 🔹 Profile completion
                int completion =
                    resumeDAO.getProfileCompletion(jobSeekerId);

                System.out.println("📊 Profile Completion: " + completion + "%");

            } else {
                System.out.println("❌ Profile not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
