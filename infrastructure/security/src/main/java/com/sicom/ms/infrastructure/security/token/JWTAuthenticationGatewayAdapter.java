package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.interfaces.Claim;
import com.sicom.ms.domain.model.common.AuthenticationGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sicom.ms.domain.model.common.Constants.CODE;
import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@Service
public class JWTAuthenticationGatewayAdapter implements AuthenticationGateway {

    public static final Map<String, Function<Claim, Object>> CLAIMS = Map.of(
            CODE, claim -> claim == null ? 0 : claim.asInt(),
            SICOM_AGENT, claim -> claim == null ? "0" : claim.asString()
    );

    @Override
    public Mono<Map<String, Object>> getClaims(Principal principal) {
        if (principal == null) {
            return Mono.empty();
        }
        return Mono.just(principal)
                .cast(JWTAuthentication.class)
                .map(jwtAuthentication -> jwtAuthentication.getToken().getClaims())
                .map(map -> CLAIMS.keySet()
                        .stream()
                        .collect(Collectors.toMap(
                                key -> key,
                                key -> CLAIMS.get(key).apply(map.get(key))
                                )
                        )
                );
    }

}
