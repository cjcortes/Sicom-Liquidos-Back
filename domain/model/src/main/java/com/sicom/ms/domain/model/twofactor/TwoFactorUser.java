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
public class TwoFactorUser {
    String user;
    String uuid;
    Date date;
}
