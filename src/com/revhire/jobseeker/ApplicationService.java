package com.revhire.jobseeker;

import java.sql.ResultSet;
import java.util.Scanner;
import com.revhire.dao.JobDAO;
import com.revhire.dao.NotificationDAO;

import com.revhire.dao.ApplicationDAO;

public class ApplicationService {
	JobDAO jobDAO = new JobDAO();
	NotificationDAO notificationDAO = new NotificationDAO();
    ApplicationDAO applicationDAO = new ApplicationDAO();

    public void applyJob(int jobSeekerId) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Job ID to apply: ");
        int jobId = sc.nextInt();

        if (applicationDAO.hasAlreadyApplied(jobId, jobSeekerId)) {
            System.out.println("⚠️ You have already applied for this job.");
            return;
        }

        if (applicationDAO.applyJob(jobId, jobSeekerId)) {

            int employerId = jobDAO.getEmployerIdByJob(jobId);

            notificationDAO.createNotification(
                "EMPLOYER",
                employerId,
                "New application received for Job ID " + jobId
            );

            System.out.println("✔ Job applied successfully");
        }
 else {
            System.out.println("❌ Job application failed");
        }
    }
    
    public void viewMyApplications(int jobSeekerId) {

        try {
            ResultSet rs =
                applicationDAO.getMyApplications(jobSeekerId);

            System.out.println("\n--- MY APPLICATIONS ---");

            boolean found = false;

            while (rs != null && rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("application_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("status") + " | " +
                    rs.getDate("applied_date")
                );
            }

            if (!found) {
                System.out.println("You have not applied to any jobs yet.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void withdrawApplication(int jobSeekerId) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Application ID to withdraw: ");
        int applicationId = sc.nextInt();

        if (!applicationDAO.canWithdraw(applicationId, jobSeekerId)) {
            System.out.println("❌ Cannot withdraw this application (invalid or already processed).");
            return;
        }

        System.out.print("Are you sure you want to withdraw? (Y/N): ");
        char confirm = sc.next().charAt(0);

        if (confirm == 'Y' || confirm == 'y') {

            if (applicationDAO.withdrawApplication(applicationId)) {
                System.out.println("✅ Application withdrawn successfully");
            } else {
                System.out.println("❌ Withdrawal failed");
            }

        } else {
            System.out.println("Withdrawal cancelled");
        }
    }

}
