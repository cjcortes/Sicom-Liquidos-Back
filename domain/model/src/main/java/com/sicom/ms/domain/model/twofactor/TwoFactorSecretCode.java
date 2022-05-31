package com.sicom.ms.domain.model.twofactor;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
@RequiredArgsConstructor
@Getter
public class TwoFactorSecretCode {
    String user;
    String secret;
    String status;
    Date date;
}
