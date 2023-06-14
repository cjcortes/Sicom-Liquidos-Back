package com.sicom.ms.infrastructure.security.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.security.jwt")
public class JWTProperties {

    private String issuer;
    private String secret;
    private String audience;
<<<<<<< HEAD
=======
    private int expires;
>>>>>>> release

}
