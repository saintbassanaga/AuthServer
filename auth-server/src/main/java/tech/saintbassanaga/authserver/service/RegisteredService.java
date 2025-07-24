package tech.saintbassanaga.authserver.service;
import tech.saintbassanaga.authserver.domains.RegisteredClient;
import tech.saintbassanaga.authserver.dtos.RegisteredClientDto;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 7/24/25
 */

import java.util.Optional;

public interface RegisteredService {

    /**
     * Enregistre un nouveau client OAuth2.
     */
    RegisteredClient registerClient(RegisteredClientDto client);

    /**
     * Recherche un client par son clientId.
     */
    Optional<RegisteredClientDto> findByClientId(String clientId);

    /**
     * Supprime un client.
     */
    void deleteClient(String clientId);

    /**
     * Met à jour un client existant.
     */
    RegisteredClient updateClient(RegisteredClientDto client);
}
