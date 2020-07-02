package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JWTAuthenticationConverter implements ServerAuthenticationConverter {

    private static final Pattern authorizationPattern = Pattern.compile(
            "^Bearer (?<token>[a-zA-Z0-9-._~+/]+)=*$",
            Pattern.CASE_INSENSITIVE);

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(resolveTokenFromAuthorizationHeader(exchange.getRequest().getHeaders()))
                .map(JWT::decode)
                .onErrorMap(Exception.class, this::invalidTokenError)
                .map(decodedJWT -> new JWTAuthentication(decodedJWT, false));
    }

    private String resolveTokenFromAuthorizationHeader(HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
            Matcher matcher = authorizationPattern.matcher(authorization);

            if (!matcher.matches()) {
                throw invalidTokenError();
            }

            return matcher.group("token");
        }

        return null;
    }

    private UnauthorizedException invalidTokenError() {
        return invalidTokenError(null);
    }

    private UnauthorizedException invalidTokenError(Throwable cause) {
        return new UnauthorizedException("token.error.malformed", "Bearer token is malformed", cause);
    }

}
