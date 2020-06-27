package com.sicom.ms.infrastructure.security.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Value
public class JWTAuthentication implements Authentication {

    DecodedJWT token;
    Boolean authenticated;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return token.getClaims();
    }

    @Override
    public Object getPrincipal() {
        return token.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Must create a new instance to specify that the authentication is valid");
    }

    @Override
    public String getName() {
        return token.getSubject();
    }
}
