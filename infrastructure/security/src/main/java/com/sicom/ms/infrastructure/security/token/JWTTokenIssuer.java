package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
<<<<<<< HEAD
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.common.UUIDOperations;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.tokens.RefreshToken;
=======
import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.common.UUIDOperations;
import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.AutenticacionNSResponse;
>>>>>>> release
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.temporal.ChronoUnit;

<<<<<<< HEAD
=======
import static com.sicom.ms.domain.model.common.Constants.*;
>>>>>>> release

@Service
@RequiredArgsConstructor
public class JWTTokenIssuer implements SecurityGateway, UUIDOperations {

    private final TimeProvider timeProvider;
    private final JWTProperties jwtProperties;
    private final JWTAlgorithm jwtAlgorithm;
    private final JWTVerifier jwtVerifier;

    @Override
    public Mono<User> generateToken(User user) {
<<<<<<< HEAD
        return Mono.just(user).map(user1 -> user1.toBuilder().token(create(user1.getId())).build());
=======
        return Mono.just(user).map(user1 -> user1.toBuilder()
                .token(create(user1.getCode(),
                        user1.getSicomAgent(), user1.getUser()))
                .build());
>>>>>>> release
    }

    @Override
    public Mono<RefreshToken> refreshToken(RefreshToken refreshToken) {
        return jwtVerifier.verify(refreshToken.getToken())
<<<<<<< HEAD
                .map(decodedJWT -> refreshToken.toBuilder().token(create(decodedJWT.getSubject())).build());
    }

    private String create(String userId) {
        return JWT.create()
                .withSubject(userId)
=======
                .map(decodedJWT -> refreshToken.toBuilder()
                        .token(create(decodedJWT.getClaim(CODE).asInt(),
                                decodedJWT.getClaim(SICOM_AGENT).asString(),
                                decodedJWT.getClaim(SICOM_USER).asString()))
                        .build());
    }

    private String create(int code, String sicomAgent, String sicomUser) {
        return JWT.create()
>>>>>>> release
                .withJWTId(randomUUID())
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .withIssuedAt(timeProvider.currentDate())
<<<<<<< HEAD
//                .withExpiresAt(timeProvider.currentDatePlus(24, ChronoUnit.HOURS))
                .withExpiresAt(timeProvider.currentDatePlus(5, ChronoUnit.SECONDS))
=======
                .withExpiresAt(timeProvider.currentDatePlus(jwtProperties.getExpires(), ChronoUnit.HOURS))
                .withClaim(CODE, code)
                .withClaim(SICOM_AGENT, sicomAgent)
                .withClaim(SICOM_USER, sicomUser)
>>>>>>> release
                .sign(jwtAlgorithm.getAlgorithm());
    }

}
