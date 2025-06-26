package tech.saintbassanaga.authserver.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    private final RsaKeyProperties rsaKeyProperties;
    private final CorsConfig corsConfig;

    @Bean
    @Order(1) // ✅ Highest priority to take control
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // ✅ Use custom CORS configuration
                .csrf(AbstractHttpConfigurer::disable) // optional, depends on your use case
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/.well-known/jwks.json","/oauth2/token").permitAll() // ✅ make JWK public
                        .anyRequest().authenticated()
                ).with(OAuth2AuthorizationServerConfigurer.authorizationServer(),Customizer.withDefaults());

        return http.build();
    }

    @Bean
    RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(String.valueOf(UUID.randomUUID()))
                .clientId("saintpaul")
                .clientSecret("{noop}saintpaul-secret")
                .clientName("Saint Paul")
                .scope("write")
                .scope("read")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .build();
        RegisteredClient registered = RegisteredClient.withId(String.valueOf(UUID.randomUUID()))
                .clientId("client-id")
                .clientSecret("{noop}client-secret")
                .clientName("David Max")
                .scope("write")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)

                .build();
        return new InMemoryRegisteredClientRepository(registeredClient, registered);
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        RSAKey rsaKey = new RSAKey.Builder(rsaKeyProperties.publicKey())
                .privateKey(rsaKeyProperties.privateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JWKSet jwkSet() {
        RSAKey rsaKey = new RSAKey.Builder(rsaKeyProperties.publicKey())
                .algorithm(JWSAlgorithm.RS256)
                .keyUse(KeyUse.SIGNATURE)
                .keyID("public-key-id")
                .build();
        return new JWKSet(rsaKey);
    }

    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return (context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) || OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) {
                RegisteredClient registeredClient = context.getRegisteredClient();
                JwtClaimsSet.Builder builder = context.getClaims();

                builder.issuer("Auth-server");
                builder.subject(registeredClient.getClientId());
                builder.claims(claims -> claims.put("scope", registeredClient.getScopes()));
            }
        });
    }
}
