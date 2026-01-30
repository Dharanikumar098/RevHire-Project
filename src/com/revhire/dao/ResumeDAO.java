package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revhire.util.DBConnection;

public class ResumeDAO {

    public boolean createOrUpdateResume(
            int jobSeekerId,
            String objective,
            String education,
            String experience,
            String skills,
            String projects) {

        try {
            // 🔹 Try UPDATE first
            String updateSql =
                "UPDATE resumes SET objective=?, education=?, experience=?, " +
                "skills=?, projects=?, updated_date=SYSDATE " +
                "WHERE job_seeker_id=?";

            PreparedStatement ups =
                DBConnection.getConnection().prepareStatement(updateSql);

            ups.setString(1, objective);
            ups.setString(2, education);
            ups.setString(3, experience);
            ups.setString(4, skills);
            ups.setString(5, projects);
            ups.setInt(6, jobSeekerId);

            int updated = ups.executeUpdate();

            if (updated > 0) {
                return true; // resume updated
            }

            // 🔹 If not updated, INSERT
            String insertSql =
                "INSERT INTO resumes " +
                "(resume_id, job_seeker_id, objective, education, experience, skills, projects) " +
                "VALUES (seq_resume.NEXTVAL, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ips =
                DBConnection.getConnection().prepareStatement(insertSql);

            ips.setInt(1, jobSeekerId);
            ips.setString(2, objective);
            ips.setString(3, education);
            ips.setString(4, experience);
            ips.setString(5, skills);
            ips.setString(6, projects);

            return ips.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getProfileCompletion(int jobSeekerId) {

        try {
            String sql =
                "SELECT objective, education, skills FROM resumes " +
                "WHERE job_seeker_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, jobSeekerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int percent = 50;
                if (rs.getString("education") != null) percent += 25;
                if (rs.getString("skills") != null) percent += 25;
                return percent;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
