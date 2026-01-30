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
    public ResultSet searchJobs(
            String title,
            String location,
            Integer experience,
            String company,
            Integer minSalary,
            Integer maxSalary,
            String jobType) {

        ResultSet rs = null;

        try {
            String sql =
                "SELECT j.job_id, j.title, e.company_name, j.location, " +
                "j.experience, j.salary, j.job_type " +
                "FROM jobs j JOIN employers e ON j.employer_id = e.employer_id " +
                "WHERE 1=1 ";

            if (title != null)
                sql += " AND LOWER(j.title) LIKE ? ";
            if (location != null)
                sql += " AND LOWER(j.location) LIKE ? ";
            if (experience != null)
                sql += " AND j.experience <= ? ";
            if (company != null)
                sql += " AND LOWER(e.company_name) LIKE ? ";
            if (minSalary != null)
                sql += " AND j.salary >= ? ";
            if (maxSalary != null)
                sql += " AND j.salary <= ? ";
            if (jobType != null)
                sql += " AND LOWER(j.job_type) = ? ";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            int idx = 1;

            if (title != null)
                ps.setString(idx++, "%" + title.toLowerCase() + "%");
            if (location != null)
                ps.setString(idx++, "%" + location.toLowerCase() + "%");
            if (experience != null)
                ps.setInt(idx++, experience);
            if (company != null)
                ps.setString(idx++, "%" + company.toLowerCase() + "%");
            if (minSalary != null)
                ps.setInt(idx++, minSalary);
            if (maxSalary != null)
                ps.setInt(idx++, maxSalary);
            if (jobType != null)
                ps.setString(idx++, jobType.toLowerCase());

            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public boolean isJobExists(int jobId) {

        try {
            String sql =
                "SELECT job_id FROM jobs WHERE job_id=? AND status='OPEN'";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}




