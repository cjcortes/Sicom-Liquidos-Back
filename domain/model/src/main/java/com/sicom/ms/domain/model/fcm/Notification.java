package com.sicom.ms.domain.model.fcm;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Notification {

    String title;
    String body;
    String dueDate;
    String fileUrl;
    String fileName;
}
