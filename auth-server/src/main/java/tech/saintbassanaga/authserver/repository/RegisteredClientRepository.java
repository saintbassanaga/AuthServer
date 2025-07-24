package tech.saintbassanaga.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.saintbassanaga.authserver.domains.RegisteredClient;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface RegisteredClientRepository extends JpaRepository<RegisteredClient, UUID> {
  Optional<RegisteredClient> findByClientId(String clientId);
}