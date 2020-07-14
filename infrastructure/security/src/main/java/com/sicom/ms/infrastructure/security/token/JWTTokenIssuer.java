package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.common.UUIDOperations;
import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.temporal.ChronoUnit;

import static com.sicom.ms.domain.model.common.Constants.CODE;
import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@Service
@RequiredArgsConstructor
public class JWTTokenIssuer implements SecurityGateway, UUIDOperations {

    private final TimeProvider timeProvider;
    private final JWTProperties jwtProperties;
    private final JWTAlgorithm jwtAlgorithm;
    private final JWTVerifier jwtVerifier;

    @Override
    public Mono<User> generateToken(User user) {
        return Mono.just(user).map(user1 -> user1.toBuilder()
                .token(create(user1.getCode(),
                        user1.getSicomAgent()))
                .build());
    }

    @Override
    public Mono<RefreshToken> refreshToken(RefreshToken refreshToken) {
        return jwtVerifier.verify(refreshToken.getToken())
                .map(decodedJWT -> refreshToken.toBuilder()
                        .token(create(decodedJWT.getClaim(CODE).asInt(),
                                decodedJWT.getClaim(SICOM_AGENT).asString()))
                        .build());
    }

    private String create(int code, String sicomAgent) {
        return JWT.create()
                .withJWTId(randomUUID())
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .withIssuedAt(timeProvider.currentDate())
                .withExpiresAt(timeProvider.currentDatePlus(jwtProperties.getExpires(), ChronoUnit.HOURS))
                .withClaim(CODE, code)
                .withClaim(SICOM_AGENT, sicomAgent)
                .sign(jwtAlgorithm.getAlgorithm());
    }

}
