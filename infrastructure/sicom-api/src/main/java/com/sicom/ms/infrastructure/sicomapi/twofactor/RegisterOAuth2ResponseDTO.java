package com.sicom.ms.infrastructure.sicomapi.twofactor;

import com.sicom.ms.domain.model.error.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterOAuth2ResponseDTO {
    Boolean success;
    RegisterOAuth2PayloadResponseDTO payload;
    Error error;
}
