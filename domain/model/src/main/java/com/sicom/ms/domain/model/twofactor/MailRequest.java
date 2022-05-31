package com.sicom.ms.domain.model.twofactor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class MailRequest {
    String email;
    String subject;
    String body;
}
