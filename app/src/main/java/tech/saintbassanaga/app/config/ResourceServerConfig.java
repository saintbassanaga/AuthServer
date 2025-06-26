package tech.saintbassanaga.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */
@Configuration
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/.well-known/jwks.json","/swagger-ui/**",
                                "/swagger-ui.html","/oauth2/token",
                                "/v3/api-docs/**",
                                "/api-docs/**").permitAll()
                        .requestMatchers( "/api/products")
                        .hasAnyAuthority("SCOPE_read", "SCOPE_write")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        String jwkSetUrl = "http://localhost:8081/.well-known/jwks.json";
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUrl).build();
    }
}
