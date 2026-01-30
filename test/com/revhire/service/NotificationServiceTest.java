package com.revhire.service;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.ResultSet;

import com.revhire.dao.NotificationDAO;

public class NotificationServiceTest {

    @Test
    public void testCreateAndFetchNotification() {

        NotificationDAO dao = new NotificationDAO();

        dao.createNotification(
            "JOBSEEKER",
            1,
            "JUnit Notification Test"
        );

        ResultSet rs =
            dao.getNotifications("JOBSEEKER", 1);

        try {
            assertTrue("Notification should exist", rs.next());
        } catch (Exception e) {
            fail("Exception occurred");
        }
    }
}
