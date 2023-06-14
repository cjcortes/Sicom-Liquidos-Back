package com.sicom.ms.domain.model.twofactor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TwoFactorSecretCode {
    String user;
    String code;
    String secret;
    String status;
    Date date;
}
