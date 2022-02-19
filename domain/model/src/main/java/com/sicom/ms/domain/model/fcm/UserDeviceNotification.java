package com.sicom.ms.domain.model.fcm;

import java.util.Date;



public class UserDeviceNotification {
    private String deviceToken;
    private String type;
    private Date registerDate;

    public UserDeviceNotification() {

    }
    public UserDeviceNotification(String deviceToken, String type, Date registerDate) {
        this.deviceToken = deviceToken;
        this.type = type;
        this.registerDate = registerDate;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getType() {
        return type;
    }

    public Date getRegisterDate() {
        return registerDate;
    }
}

