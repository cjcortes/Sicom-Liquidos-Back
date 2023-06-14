package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

<<<<<<< HEAD

=======
>>>>>>> release
@Component
public class JWTAlgorithm {

    private final Algorithm algorithm;

    public JWTAlgorithm(JWTProperties jwtProperties) {
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

}
