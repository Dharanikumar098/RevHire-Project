package com.revhire.jobseeker;
import org.apache.log4j.Logger;
import java.util.Scanner;

import com.revhire.dao.ResumeDAO;

public class ResumeService {

    private static final Logger logger =
        Logger.getLogger(ResumeService.class);
    ResumeDAO resumeDAO = new ResumeDAO();

    public void createOrUpdateResume(int jobSeekerId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- CREATE / UPDATE RESUME ---");

        System.out.print("Objective: ");
        String objective = sc.nextLine();

        System.out.print("Education: ");
        String education = sc.nextLine();

        System.out.print("Experience: ");
        String experience = sc.nextLine();

        System.out.print("Skills: ");
        String skills = sc.nextLine();

        System.out.print("Projects: ");
        String projects = sc.nextLine();

        if (resumeDAO.createOrUpdateResume(
                jobSeekerId,
                objective,
                education,
                experience,
                skills,
                projects)) {

            System.out.println("✅ Resume saved successfully");
        } else {
            System.out.println("❌ Resume save failed");
        }
    }
}
