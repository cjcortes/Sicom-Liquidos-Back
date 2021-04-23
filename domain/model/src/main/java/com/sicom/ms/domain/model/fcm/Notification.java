package com.sicom.ms.domain.model.fcm;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Notification {

    String title;
    String body;
    Date dueDate;
    String fileUrl;
    String fileName;
}
