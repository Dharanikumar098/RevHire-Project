package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revhire.util.DBConnection;

public class EmployerDAO {

    // ================= LOGIN / REGISTER =================

    public int validateLogin(String email, String password) {

        try {
            String sql =
                "SELECT employer_id FROM employers WHERE email=? AND password=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("employer_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean registerEmployer(String company, String email, String password) {

        try {
            String sql =
                "INSERT INTO employers " +
                "(employer_id, company_name, email, password) " +
                "VALUES (seq_employer.NEXTVAL, ?, ?, ?)";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, company);
            ps.setString(2, email);
            ps.setString(3, password);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= PROFILE =================

    public ResultSet getEmployerProfile(int employerId) {

        try {
            String sql =
                "SELECT company_name, email, industry, company_size, " +
                "website, location, description " +
                "FROM employers WHERE employer_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, employerId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCompanyProfile(
            int employerId,
            String industry,
            String size,
            String website,
            String location,
            String description) {

        try {
            String sql =
                "UPDATE employers SET " +
                "industry=?, company_size=?, website=?, location=?, description=? " +
                "WHERE employer_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, industry);
            ps.setString(2, size);
            ps.setString(3, website);
            ps.setString(4, location);
            ps.setString(5, description);
            ps.setInt(6, employerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= PASSWORD =================

    public boolean changePassword(int employerId, String oldPwd, String newPwd) {

        try {
            String sql =
                "UPDATE employers SET password=? " +
                "WHERE employer_id=? AND password=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, newPwd);
            ps.setInt(2, employerId);
            ps.setString(3, oldPwd);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int loginAndGetEmployerId(String email, String password) {

        try {
            String sql =
                "SELECT employer_id FROM employers WHERE email=? AND password=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("employer_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public boolean registerEmployer(
            String companyName,
            String email,
            String password,
            String industry,
            String size,
            String website,
            String location,
            String description) {

        try {
            String sql =
                "INSERT INTO employers " +
                "(employer_id, company_name, email, password, industry, company_size, website, location, description) " +
                "VALUES (seq_employer.NEXTVAL,?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, companyName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, industry);
            ps.setString(5, size);
            ps.setString(6, website);
            ps.setString(7, location);
            ps.setString(8, description);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
