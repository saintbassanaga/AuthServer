package tech.saintbassanaga.authserver.dtos;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link tech.saintbassanaga.authserver.domains.RegisteredClient}
 */
public record RegisteredClientDto(String clientId, String clientSecret, List<ClientAuthenticationMethod> clientAuthenticationMethods,
                                  List<AuthorizationGrantType> authorizationGrantTypes, Set<String> redirectUris, Set<String> scopes, String clientName,
                                  boolean requireProofKey) implements Serializable {
}