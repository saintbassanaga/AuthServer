package tech.saintbassanaga.authserver.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "registeredClient")
public class RegisteredClient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    @Column(nullable = false)
    private List<ClientAuthenticationMethod> clientAuthenticationMethods;

    @Column(nullable = false)
    private List<AuthorizationGrantType> authorizationGrantTypes;

    @Column(nullable = false)
    private Set<String> redirectUris; // Comma-separated

    @Column(nullable = false)
    private Set<String> scopes; // Comma-separated: e.g., "openid,profile,email"

    @Column
    private String clientName;

    private ClientSettings clientSettings;

    @Column
    private boolean requireProofKey; // For PKCE

}