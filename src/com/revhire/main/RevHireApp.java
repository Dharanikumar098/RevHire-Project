package com.revhire.main;

import java.util.Scanner;

import com.revhire.auth.AuthenticationService;
import com.revhire.auth.PasswordRecoveryService;
import com.revhire.jobseeker.JobSeekerService;
import com.revhire.jobseeker.ResumeService;
import com.revhire.jobseeker.JobSearchService;
import com.revhire.jobseeker.ApplicationService;
import com.revhire.notification.NotificationService;

import com.revhire.employer.EmployerService;
import com.revhire.employer.JobPostingService;
import com.revhire.employer.ApplicationReviewService;

public class RevHireApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        AuthenticationService authService = new AuthenticationService();

        while (true) {
            System.out.println("\n===== REVHIRE JOB PORTAL =====");
            System.out.println("1. Job Seeker");
            System.out.println("2. Employer");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    jobSeekerMenu(authService);
                    break;
                case 2:
                    employerMenu(authService);
                    break;
                case 3:
                    System.out.println("✅ Thank you for using RevHire");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }

    // ================= JOB SEEKER =================
    private static void jobSeekerMenu(AuthenticationService authService) {

        JobSeekerService jsService = new JobSeekerService();
        ResumeService resumeService = new ResumeService();
        JobSearchService searchService = new JobSearchService();
        ApplicationService appService = new ApplicationService();
        NotificationService notifService = new NotificationService();

        while (true) {
            System.out.println("\n--- JOB SEEKER MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.println("4. Forgot Password");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                authService.registerJobSeeker();
            } 
            else if (ch == 2) {

                int jobSeekerId = authService.loginJobSeeker();

                if (jobSeekerId != -1) {
                    jobSeekerDashboard(
                        jobSeekerId,
                        authService,
                        jsService,
                        resumeService,
                        searchService,
                        appService,
                        notifService
                    );
                }

            } 
            else if (ch == 3) {
                PasswordRecoveryService prs = new PasswordRecoveryService();
                prs.recoverPassword("JOBSEEKER");
            }
            else {
                return;
            }
        }
    }

    private static void jobSeekerDashboard(
            int jobSeekerId,
            AuthenticationService authService,
            JobSeekerService jsService,
            ResumeService resumeService,
            JobSearchService searchService,
            ApplicationService appService,
            NotificationService notifService) {

        while (true) {
            System.out.println("\n--- JOB SEEKER DASHBOARD ---");
            System.out.println("1. View Profile");
            System.out.println("2. Create / Update Resume");
            System.out.println("3. View Jobs");
            System.out.println("4. Apply Job");
            System.out.println("5. View Applications");
            System.out.println("6. Withdraw Application");
            System.out.println("7. Notifications");
            
            System.out.println("8. Logout");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    jsService.viewProfile(jobSeekerId);
                    break;
                case 2:
                    resumeService.createOrUpdateResume(jobSeekerId);
                    break;
                case 3:
                    searchService.viewJobs();
                    break;
                case 4:
                    appService.applyJob(jobSeekerId);
                    break;
                case 5:
                    appService.viewMyApplications(jobSeekerId);
                    break;
                case 6:
                    appService.withdrawApplication(jobSeekerId);
                    break;
                case 7:
                    notifService.showNotifications("JOBSEEKER", jobSeekerId);
                    break;

                case 8:
                    return;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    // ================= EMPLOYER =================
    private static void employerMenu(AuthenticationService authService) {

        EmployerService empService = new EmployerService();
        JobPostingService jobService = new JobPostingService();
        ApplicationReviewService reviewService = new ApplicationReviewService();
        NotificationService notifService = new NotificationService();

        while (true) {
            System.out.println("\n--- EMPLOYER MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("3. Back");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                authService.registerEmployer();
            } 
            else if (ch == 2) {

                int employerId = authService.loginEmployer();

                if (employerId != -1) {
                    employerDashboard(
                        employerId,
                        authService,
                        empService,
                        jobService,
                        reviewService,
                        notifService
                    );
                }

            } 
            else if (ch == 3) {
                PasswordRecoveryService prs = new PasswordRecoveryService();
                prs.recoverPassword("EMPLOYER");
            }
            else {
                return;
            }
        }
    }

    private static void employerDashboard(
            int employerId,
            AuthenticationService authService,
            EmployerService empService,
            JobPostingService jobService,
            ApplicationReviewService reviewService,
            NotificationService notifService) {

        while (true) {
            System.out.println("\n--- EMPLOYER DASHBOARD ---");
            System.out.println("1. View Company Profile");
            System.out.println("2. Update Company Profile");
            System.out.println("3. Post Job");
            System.out.println("4. Manage Jobs");
            System.out.println("5. View Applicants");
            System.out.println("6. Review Applications");
            System.out.println("7. Notifications");
            System.out.println("8. Logout");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    empService.viewCompanyProfile(employerId);
                    break;
                case 2:
                    empService.updateCompanyProfile(employerId);
                    break;
                case 3:
                    jobService.postJob(employerId);
                    break;
                case 4:
                    jobService.manageJobs(employerId);
                    break;
                case 5:
                    jobService.viewApplicants(employerId);
                    break;
                case 6:
                    reviewService.reviewApplications(employerId);
                    break;
                case 7:
                    notifService.showNotifications("EMPLOYER", employerId);
                    break;
                
                case 8:
                    return;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }
}
