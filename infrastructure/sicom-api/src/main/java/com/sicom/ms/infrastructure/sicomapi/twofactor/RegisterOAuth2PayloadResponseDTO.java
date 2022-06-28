package com.sicom.ms.infrastructure.sicomapi.twofactor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterOAuth2PayloadResponseDTO {
    String fecha;
    String informacion;
}
