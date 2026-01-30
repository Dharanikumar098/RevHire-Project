package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revhire.util.DBConnection;

public class JobDAO {

    public boolean createJob(
            int employerId,
            String title,
            String description,
            String location,
            int salary,
            String jobType) {

        try {
            String sql =
                "INSERT INTO jobs " +
                "(job_id, employer_id, title, description, location, salary, job_type, status) " +
                "VALUES (seq_job.NEXTVAL, ?, ?, ?, ?, ?, ?, 'OPEN')";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, employerId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setInt(5, salary);
            ps.setString(6, jobType);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ResultSet getJobsByEmployer(int employerId) {

        try {
            String sql =
                "SELECT job_id, title, status " +
                "FROM jobs WHERE employer_id=? " +
                "ORDER BY job_id";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, employerId);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Close job
    public boolean closeJob(int jobId, int employerId) {

        try {
            String sql =
                "UPDATE jobs SET status='CLOSED' " +
                "WHERE job_id=? AND employer_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobId);
            ps.setInt(2, employerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    } 
    public ResultSet getOpenJobs() {

        ResultSet rs = null;

        try {
            String sql =
                "SELECT job_id, title, location, salary, job_type " +
                "FROM jobs " +
                "WHERE status='OPEN' " +
                "ORDER BY created_date DESC";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
    public int getEmployerIdByJob(int jobId) {

        try {
            String sql = "SELECT employer_id FROM jobs WHERE job_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("employer_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
