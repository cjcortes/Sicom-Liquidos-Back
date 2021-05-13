package com.sicom.ms.domain.model.forti;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ValidateTokenRequest {
    String username;
    String token_code;
}
