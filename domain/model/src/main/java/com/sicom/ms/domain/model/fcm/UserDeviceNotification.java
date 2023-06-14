package com.sicom.ms.domain.model.fcm;

import java.util.Date;



public class UserDeviceNotification {
    private String deviceToken;


    public UserDeviceNotification() {

    }
    public UserDeviceNotification(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }
}

