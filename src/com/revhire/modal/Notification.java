package com.revhire.modal;

import java.util.Date;

public class Notification {

    private int notificationId;
    private int userId;
    private String userType;
    private String message;
    private Date createdDate;
    private boolean isRead;

    public Notification() {}

    public Notification(int notificationId, int userId, String userType,
                        String message, Date createdDate, boolean isRead) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.userType = userType;
        this.message = message;
        this.createdDate = createdDate;
        this.isRead = isRead;
    }

    public int getNotificationId() {
        return notificationId;
    }
    
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isRead() {
        return isRead;
    }
    
    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
