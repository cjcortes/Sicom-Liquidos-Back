package com.sicom.ms.domain.model.twofactor;

import com.sicom.ms.domain.model.error.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterOAuth2Response {
    Boolean success;
    RegisterOAuth2PayloadResponse payload;
    Error error;
}
