package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.common.UUIDOperations;
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


    @Override
    public Mono<User> generateToken(User user) {
        return Mono.just(user).map(user1 -> user1.toBuilder().token(create()).build());
    }

    private String create() {
        return JWT.create()
                .withSubject(randomUUID())
                .withJWTId(randomUUID())
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .withIssuedAt(timeProvider.currentDate())
                .withExpiresAt(timeProvider.currentDatePlus(24, ChronoUnit.HOURS))
                .sign(jwtAlgorithm.getAlgorithm());
    }

}
