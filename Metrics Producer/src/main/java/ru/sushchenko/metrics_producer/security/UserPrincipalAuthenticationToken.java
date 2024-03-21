package ru.sushchenko.metrics_producer.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserDetails principal;
    public UserPrincipalAuthenticationToken(UserDetails principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserDetails getPrincipal() {
        return principal;
    }
}
