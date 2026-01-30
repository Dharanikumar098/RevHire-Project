package com.revhire.jobseeker;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.util.Scanner;
import com.revhire.dao.JobDAO;
import com.revhire.dao.NotificationDAO;

import com.revhire.dao.ApplicationDAO;

public class ApplicationService {
	private static final Logger logger =
	        Logger.getLogger(ApplicationService.class);
	JobDAO jobDAO = new JobDAO();
	NotificationDAO notificationDAO = new NotificationDAO();
    ApplicationDAO applicationDAO = new ApplicationDAO();

    public void applyJob(int jobSeekerId) {
    	 
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Job ID to apply: ");
        int jobId = sc.nextInt();

        JobDAO jobDAO = new JobDAO();

        // ✅ CHECK JOB EXISTS FIRST
        if (!jobDAO.isJobExists(jobId)) {
            System.out.println(" Invalid Job ID. Please select a valid job.");
            return;
        }

        ApplicationDAO appDAO = new ApplicationDAO();

        if (applicationDAO.applyJob(jobSeekerId, jobId)) {

            // 🔹 Job Seeker Notification
            notificationDAO.createNotification(
                "JOBSEEKER",
                jobSeekerId,
                "You successfully applied for Job ID: " + jobId
            );

            // 🔹 Employer Notification (IMPORTANT)
            int employerId = jobDAO.getEmployerIdByJob(jobId);

            if (employerId != -1) {
                notificationDAO.createNotification(
                    "EMPLOYER",
                    employerId,
                    "New application received for Job ID: " + jobId
                );
            }

            System.out.println("✅ Job applied successfully");
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
        int appId = sc.nextInt();

        ApplicationDAO dao = new ApplicationDAO();

        if (!dao.canWithdraw(appId, jobSeekerId)) {
            System.out.println(
                "Cannot withdraw this application (invalid or already processed)."
            );
            return;
        }

        if (dao.withdrawApplication(appId)) {

            notificationDAO.createNotification(
                "JOBSEEKER",
                jobSeekerId,
                "You withdrew your application (Application ID: " + appId + ")"
            );

            System.out.println("✅ Application withdrawn successfully");
        }
 else {
            System.out.println("❌ Withdrawal failed");
        }

    }


}
