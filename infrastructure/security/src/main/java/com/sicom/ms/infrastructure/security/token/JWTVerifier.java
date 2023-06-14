package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
<<<<<<< HEAD
import com.auth0.jwt.exceptions.JWTVerificationException;
=======
>>>>>>> release
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public Mono<DecodedJWT> verify(String token) {
        return Mono.just(token)
                .map(verifier::verify)
                .onErrorResume(TokenExpiredException.class, e -> Mono.just(JWT.decode(token)))
                .onErrorMap(Exception.class, this::invalidTokenError);
    }

    private UnauthorizedException invalidTokenError(Throwable cause) {
        return new UnauthorizedException("token.error.invalid", "Bearer token is invalid", cause);
    }

}
