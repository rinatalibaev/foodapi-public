package ru.alibaev.foodapi.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alibaev.foodapi.config.properties.KeycloakProperties;

@Configuration
public class KeycloakAdminConfig {

    @Bean
    public Keycloak keycloakAdminClient(KeycloakProperties properties) {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getAuthServerUrl())
                .realm(properties.getRealm())
                .clientId(properties.getAdminClientId())
                .clientSecret(properties.getCredentials().get("secret"))
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
