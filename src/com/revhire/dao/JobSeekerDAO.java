package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revhire.util.DBConnection;

public class JobSeekerDAO {

    // ================= REGISTER =================
	public boolean registerJobSeeker(
	        String name,
	        String email,
	        String password,
	        String phone,
	        String location,
	        int experience,
	        String secQ,
	        String secA) {

	    try {
	        String sql =
	            "INSERT INTO job_seekers " +
	            "(job_seeker_id, full_name, email, password, phone, location, experience_years, security_question, security_answer) " +
	            "VALUES (seq_job_seeker.NEXTVAL,?,?,?,?,?,?,?,?)";

	        PreparedStatement ps =
	            DBConnection.getConnection().prepareStatement(sql);

	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        ps.setString(4, phone);
	        ps.setString(5, location);
	        ps.setInt(6, experience);
	        ps.setString(7, secQ);
	        ps.setString(8, secA);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


    // ================= LOGIN =================
    public int loginAndGetJobSeekerId(String email, String password) {

        try {
            String sql =
                "SELECT job_seeker_id FROM job_seekers WHERE email=? AND password=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("job_seeker_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // ================= CHANGE PASSWORD =================
    public boolean changePassword(int jobSeekerId, String oldPwd, String newPwd) {

        try {
            String sql =
                "UPDATE job_seekers SET password=? " +
                "WHERE job_seeker_id=? AND password=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, newPwd);
            ps.setInt(2, jobSeekerId);
            ps.setString(3, oldPwd);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 // ================= VIEW PROFILE =================
    public ResultSet getJobSeekerProfile(int jobSeekerId) {

        try {
            String sql =
                "SELECT job_seeker_id, full_name, email " +
                "FROM job_seekers WHERE job_seeker_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobSeekerId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
