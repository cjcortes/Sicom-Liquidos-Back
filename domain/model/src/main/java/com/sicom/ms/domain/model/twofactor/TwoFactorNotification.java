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
public class TwoFactorNotification {
    String title;
    String body;
    Date date;
    String user;
    String sicomCode;
}
