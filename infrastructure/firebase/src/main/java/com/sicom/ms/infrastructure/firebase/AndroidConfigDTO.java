package com.sicom.ms.infrastructure.firebase;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AndroidConfigDTO {

    AndroidNotificationDTO notification;

}
