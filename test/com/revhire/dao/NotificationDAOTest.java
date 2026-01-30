package com.revhire.dao;


import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.ResultSet;

import com.revhire.dao.NotificationDAO;

public class NotificationDAOTest {

    NotificationDAO dao = new NotificationDAO();

    @Test
    public void testCreateNotification() {

        dao.createNotification(
            "JOBSEEKER",
            1,
            "JUnit Test Notification"
        );

        ResultSet rs =
            dao.getNotifications("JOBSEEKER", 1);

        try {
            assertTrue("Notification should exist", rs.next());
        } catch (Exception e) {
            fail("Exception while testing notifications");
        }
    }
}

