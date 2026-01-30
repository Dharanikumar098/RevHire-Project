package com.revhire.notification;

import java.sql.ResultSet;
import java.util.Scanner;

import com.revhire.dao.NotificationDAO;

public class NotificationService {

    NotificationDAO notificationDAO = new NotificationDAO();

    public void showNotifications(String userType, int userId) {

        try {
            ResultSet rs =
                notificationDAO.getNotifications(userType, userId);

            System.out.println("\n--- NOTIFICATIONS ---");

            boolean found = false;

            while (rs != null && rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("notification_id") + " | " +
                    rs.getString("message") + " | " +
                    rs.getDate("created_date") + " | Read: " +
                    rs.getString("is_read")
                );
            }

            if (!found) {
                System.out.println("No notifications found");
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Notification ID to mark as read (0 to skip): ");
            int nid = sc.nextInt();

            if (nid != 0) {
                notificationDAO.markAsRead(nid);
                System.out.println("Notification marked as read");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
