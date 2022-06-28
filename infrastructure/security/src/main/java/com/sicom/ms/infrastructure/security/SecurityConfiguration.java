package com.sicom.ms.infrastructure.security;

import com.sicom.ms.infrastructure.security.token.JWTAuthenticationConverter;
import com.sicom.ms.infrastructure.security.token.JWTAuthenticationManager;
import com.sicom.ms.infrastructure.security.token.JWTProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@EnableWebFluxSecurity
@EnableConfigurationProperties({
        JWTProperties.class
})
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity,
                                                         JWTAuthenticationManager authenticationManager,
                                                         JWTAuthenticationConverter authenticationConverter) {
        AuthenticationWebFilter authentication = new AuthenticationWebFilter(authenticationManager);
        authentication.setServerAuthenticationConverter(authenticationConverter);

        return httpSecurity
                .addFilterAt(authentication, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                .pathMatchers(
                        "/",
                        "/api/security/login",
                        "/api/security/login-two-factor",
                        "/api/security/refresh-token",
                        "/api/fcm",
                        "/api/fcm/custom",
                        "/api/security/login-encrypt-password",
                        "/api/security/loginns",
                        "/api/two-factor/generate-secret-code",
                        "/api/two-factor/confirm-secret-code",
                        "/api/two-factor/register-notification-oauth2-webapp"
                )
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .httpBasic().disable()
                .build();
    }

}
