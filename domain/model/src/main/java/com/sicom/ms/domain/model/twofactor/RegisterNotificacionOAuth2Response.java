package com.sicom.ms.domain.model.twofactor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterNotificacionOAuth2Response {
    Boolean success;
}
