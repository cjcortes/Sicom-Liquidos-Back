package com.sicom.ms.domain.model.users;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {

<<<<<<< HEAD
    String id;
    String name;
    String token;
=======
    int code;
    String user;
    String name;
    String userState;
    String sicomAgent;
    String agentSate;
    String agentType;
    String profile;
    String token;
    boolean twoFactorAuth;
    boolean resultAuth;
>>>>>>> release
}