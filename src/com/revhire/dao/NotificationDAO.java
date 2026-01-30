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

    public boolean markAsRead(
            int notificationId,
            String userType,
            int userId) {

        try {
            if (!isNotificationBelongsToUser(notificationId, userType, userId)) {
                return false;
            }

            String sql =
                "UPDATE notifications SET is_read='Y' WHERE notification_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, notificationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isNotificationBelongsToUser(
            int notificationId,
            String userType,
            int userId) {

        try {
            String sql =
                "SELECT notification_id FROM notifications " +
                "WHERE notification_id=? AND user_type=? AND user_id=?";

            PreparedStatement ps =
                DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, notificationId);
            ps.setString(2, userType);
            ps.setInt(3, userId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
