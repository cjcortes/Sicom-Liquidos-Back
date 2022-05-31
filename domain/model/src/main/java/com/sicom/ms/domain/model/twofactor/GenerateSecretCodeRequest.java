package com.sicom.ms.domain.model.twofactor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GenerateSecretCodeRequest {
    String user;
    String email;
}
