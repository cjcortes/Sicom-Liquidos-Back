package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JWTVerifier {

    private final com.auth0.jwt.JWTVerifier verifier;

    public JWTVerifier(JWTAlgorithm algorithm, JWTProperties jwtProperties) {
        verifier = JWT.require(algorithm.getAlgorithm())
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .build();
    }

    public Mono<JWTAuthentication> verify(JWTAuthentication authentication) {
        return Mono.just(authentication.getToken())
                .map(verifier::verify)
                .onErrorMap(Exception.class, this::invalidTokenError)
                .map(decodedJWT -> new JWTAuthentication(decodedJWT, true));
    }

    private UnauthorizedException invalidTokenError(Throwable cause) {
        return new UnauthorizedException("token.error.invalid", "Bearer token is invalid", cause);
    }

}
