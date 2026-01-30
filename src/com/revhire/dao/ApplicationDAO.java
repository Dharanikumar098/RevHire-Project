package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.revhire.util.DBConnection;

public class ApplicationDAO {

    //  Check if already applied
    public boolean hasAlreadyApplied(int jobId, int jobSeekerId) {

        try {
            String sql =
                "SELECT application_id FROM applications " +
                "WHERE job_id=? AND job_seeker_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobId);
            ps.setInt(2, jobSeekerId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Apply job
 // Apply job (DB insert)
    public boolean applyJob(int jobSeekerId, int jobId) {

        PreparedStatement ps = null;

        try {
            // 🔹 Prevent duplicate application
            if (hasAlreadyApplied(jobId, jobSeekerId)) {
                System.out.println("⚠️ You already applied for this job");
                return false;
            }

            String sql =
                "INSERT INTO applications " +
                "(application_id, job_id, job_seeker_id, status, applied_date) " +
                "VALUES (seq_application.NEXTVAL, ?, ?, 'APPLIED', SYSDATE)";

            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, jobId);
            ps.setInt(2, jobSeekerId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                // 🔔 CREATE NOTIFICATIONS

                NotificationDAO notificationDAO = new NotificationDAO();
                JobDAO jobDAO = new JobDAO();

                // 🔹 Notify Job Seeker
                notificationDAO.createNotification(
                    "JOBSEEKER",
                    jobSeekerId,
                    "You successfully applied for Job ID: " + jobId
                );

                // 🔹 Notify Employer
                int employerId = jobDAO.getEmployerIdByJob(jobId);
                if (employerId != -1) {
                    notificationDAO.createNotification(
                        "EMPLOYER",
                        employerId,
                        "New application received for Job ID: " + jobId
                    );
                }

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
        return false;
    }

    public ResultSet getMyApplications(int jobSeekerId) {

        try {
            String sql =
                "SELECT a.application_id, j.title, a.status, a.applied_date " +
                "FROM applications a JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.job_seeker_id=? " +
                "ORDER BY a.applied_date DESC";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobSeekerId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 // Check if application belongs to user and is APPLIED
    public boolean canWithdraw(int applicationId, int jobSeekerId) {

        try {
            String sql =
                "SELECT application_id FROM applications " +
                "WHERE application_id=? AND job_seeker_id=? AND status='APPLIED'";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, applicationId);
            ps.setInt(2, jobSeekerId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Withdraw application
    public boolean withdrawApplication(int applicationId) {

        PreparedStatement ps = null;

        try {
            String sql =
                "UPDATE applications " +
                "SET status = 'WITHDRAWN' " +
                "WHERE application_id = ?";

            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, applicationId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;   // 🔑 MUST RETURN BOOLEAN

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }
    }

    public ResultSet getApplicantsForJob(int jobId, int employerId) {

        try {
            String sql =
                "SELECT a.application_id, js.full_name, js.email, a.status, a.applied_date " +
                "FROM applications a " +
                "JOIN job_seekers js ON a.job_seeker_id = js.job_seeker_id " +
                "JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.job_id=? AND j.employer_id=? " +
                "ORDER BY a.applied_date DESC";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobId);
            ps.setInt(2, employerId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 // Get Job Seeker ID for an application (ownership safe)
    public int getJobSeekerIdByApplication(int applicationId, int employerId) {

        try {
            String sql =
                "SELECT a.job_seeker_id " +
                "FROM applications a JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.application_id=? AND j.employer_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, applicationId);
            ps.setInt(2, employerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("job_seeker_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 🔹 Update application status
    public boolean updateApplicationStatus(int applicationId, String status) {

        try {
            String sql =
                "UPDATE applications SET status=? WHERE application_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

