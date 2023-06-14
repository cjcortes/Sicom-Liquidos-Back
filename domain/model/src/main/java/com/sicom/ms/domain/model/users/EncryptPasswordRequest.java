package com.sicom.ms.domain.model.users;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EncryptPasswordRequest {
    String credencialesBase64;
}

