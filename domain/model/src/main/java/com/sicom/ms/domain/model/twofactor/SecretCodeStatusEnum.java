package com.sicom.ms.domain.model.twofactor;

import lombok.ToString;

@ToString
public enum SecretCodeStatusEnum {
    SENDING,
    VALID,
    EXPIRED
}
