package com.example.springdataresttest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthConverter extends JwtAuthenticationConverter {


    private static final String REALM_ACCESS = "realm_access";
    private static final String ROLES = "roles";

    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        log.debug("Jwt Being Decoded.");
        Map<String, Object> resources = (Map<String, Object>) jwt.getClaims().get(REALM_ACCESS);
        List<String> roles = (List<String>) resources.get(ROLES);
        log.debug("roles extracted = "+roles);

        Collection<GrantedAuthority> result = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return result;
    }
}
