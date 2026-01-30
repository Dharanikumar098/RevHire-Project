package com.revhire.employer;
import java.util.*;
import java.sql.ResultSet;

import com.revhire.dao.EmployerDAO;
import org.apache.log4j.Logger;
public class EmployerService {
	 private static final Logger logger =
		        Logger.getLogger(EmployerService.class);
    EmployerDAO employerDAO = new EmployerDAO();

    public void viewCompanyProfile(int employerId) {

        try {
            ResultSet rs =
                employerDAO.getEmployerProfile(employerId);

            if (rs != null && rs.next()) {

                System.out.println("\n--- COMPANY PROFILE ---");
                System.out.println("Company Name : " + rs.getString("company_name"));
                System.out.println("Email        : " + rs.getString("email"));
                System.out.println("Industry     : " + rs.getString("industry"));
                System.out.println("Company Size : " + rs.getString("company_size"));
                System.out.println("Website      : " + rs.getString("website"));
                System.out.println("Location     : " + rs.getString("location"));
                System.out.println("Description  : " + rs.getString("description"));

            } else {
                System.out.println("❌ Company profile not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCompanyProfile(int employerId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- UPDATE COMPANY PROFILE ---");

        System.out.print("Industry: ");
        String industry = sc.nextLine();

        System.out.print("Company Size: ");
        String size = sc.nextLine();

        System.out.print("Website: ");
        String website = sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        if (employerDAO.updateCompanyProfile(
                employerId,
                industry,
                size,
                website,
                location,
                description)) {

            System.out.println("✅ Company profile updated successfully");

        } else {
            System.out.println("❌ Update failed");
        }
    }

}
