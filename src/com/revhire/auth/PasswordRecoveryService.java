package com.revhire.auth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.revhire.util.DBConnection;

public class PasswordRecoveryService {

    public void recoverPassword(String userType) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Security Answer: ");
        String answer = sc.nextLine();

        try {
            String table =
                userType.equals("JOBSEEKER") ? "job_seekers" : "employers";

            String sql =
                "SELECT password FROM " + table +
                " WHERE email=? AND security_answer=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, email.toLowerCase());
            ps.setString(2, answer);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Your password is: " + rs.getString("password"));
            } else {
                System.out.println("❌ Incorrect security answer");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
