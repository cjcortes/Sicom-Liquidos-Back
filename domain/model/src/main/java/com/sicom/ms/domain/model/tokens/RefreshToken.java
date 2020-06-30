package com.sicom.ms.domain.model.tokens;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RefreshToken {

    String token;

}
