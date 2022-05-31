package com.sicom.ms.domain.model.twofactor;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class RegisterUserResponse {
    String user;
    String status;
    Date date;
}
