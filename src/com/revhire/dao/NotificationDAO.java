package com.revhire.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revhire.util.DBConnection;

public class NotificationDAO {

    public void createNotification(String userType, int userId, String message) {

        try {
            String sql =
                "INSERT INTO notifications " +
                "(notification_id, user_type, user_id, message, is_read, created_date) " +
                "VALUES (seq_notification.NEXTVAL, ?, ?, ?, 'N', SYSDATE)";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, userType);
            ps.setInt(2, userId);
            ps.setString(3, message);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getNotifications(String userType, int userId) {

        try {
            String sql =
                "SELECT notification_id, message, created_date, is_read " +
                "FROM notifications WHERE user_type=? AND user_id=? " +
                "ORDER BY created_date DESC";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, userType);
            ps.setInt(2, userId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void markAsRead(int notificationId) {

        try {
            String sql =
                "UPDATE notifications SET is_read='Y' WHERE notification_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, notificationId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
