package com.example.springdataresttest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Collections;

@Configuration
public class ConfigServerConfig {

    @Value("${spring.cloud.config.auth.uri}")
    private String uri;

    @Value("${spring.cloud.config.auth.client-id}")
    private String clientId;

    @Value("${spring.cloud.config.auth.secret}")
    private String secret;

    @Value("${spring.cloud.config.auth.grant-type}")
    private String grantType;

    @Value("${spring.cloud.config.auth.scope}")
    private String scope;


    private OAuth2RestTemplate oauth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(uri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(secret);
        resourceDetails.setGrantType(grantType);
        resourceDetails.setScope(Collections.singletonList(scope));
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(resourceDetails, clientContext);
    }

}

