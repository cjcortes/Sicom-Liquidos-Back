package com.sicom.ms.domain.model.twofactor;

import lombok.*;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TwoFactorUser {
    String user;
    String uuid;
    String status;
    Date date;
}
