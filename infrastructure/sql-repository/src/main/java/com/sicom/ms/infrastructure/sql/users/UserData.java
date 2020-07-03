package com.sicom.ms.infrastructure.sql.users;

import lombok.Data;

@Data
public class UserData {

    int code;
    String user;
    String name;
    String userState;
    String sicomAgent;
    String agentSate;
    String agentType;
    String profile;

}
