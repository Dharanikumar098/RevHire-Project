package com.revhire.employer;

import java.util.Scanner;

import com.revhire.dao.ApplicationDAO;
import com.revhire.dao.NotificationDAO;

public class ApplicationReviewService {

    ApplicationDAO applicationDAO = new ApplicationDAO();
    NotificationDAO notificationDAO = new NotificationDAO();

    public void reviewApplications(int employerId) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Application ID to review: ");
        int applicationId = sc.nextInt();
        sc.nextLine();

        int jobSeekerId =
            applicationDAO.getJobSeekerIdByApplication(applicationId, employerId);

        if (jobSeekerId == -1) {
            System.out.println("Invalid application or access denied");
            return;
        }

        System.out.print("Action (SHORTLIST / REJECT): ");
        String action = sc.nextLine().toUpperCase();

        if (!action.equals("SHORTLIST") && !action.equals("REJECT")) {
            System.out.println("Invalid action");
            return;
        }

        if (applicationDAO.updateApplicationStatus(applicationId, action)) {

            notificationDAO.createNotification(
                "JOBSEEKER",
                jobSeekerId,
                "Your application has been " + action
            );

            System.out.println("Application " + action + "ED successfully");

        } else {
            System.out.println("Failed to update application");
        }
    }
}
