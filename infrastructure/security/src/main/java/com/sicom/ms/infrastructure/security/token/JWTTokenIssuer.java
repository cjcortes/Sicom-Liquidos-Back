package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.common.UUIDOperations;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class JWTTokenIssuer implements SecurityGateway, UUIDOperations {

    private final TimeProvider timeProvider;
    private final JWTProperties jwtProperties;
    private final JWTAlgorithm jwtAlgorithm;
    private final JWTVerifier jwtVerifier;

    @Override
    public Mono<User> generateToken(User user) {
        return Mono.just(user).map(user1 -> user1.toBuilder().token(create(user1.getId())).build());
    }

    @Override
    public Mono<RefreshToken> refreshToken(RefreshToken refreshToken) {
        return jwtVerifier.verify(refreshToken.getToken())
                .map(decodedJWT -> refreshToken.toBuilder().token(create(decodedJWT.getSubject())).build());
    }

    private String create(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withJWTId(randomUUID())
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .withIssuedAt(timeProvider.currentDate())
                .withExpiresAt(timeProvider.currentDatePlus(24, ChronoUnit.HOURS))
                .sign(jwtAlgorithm.getAlgorithm());
    }

}
