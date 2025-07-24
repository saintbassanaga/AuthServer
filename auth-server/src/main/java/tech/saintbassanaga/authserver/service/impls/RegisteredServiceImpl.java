package tech.saintbassanaga.authserver.service.impls;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.saintbassanaga.authserver.domains.RegisteredClient;
import tech.saintbassanaga.authserver.dtos.RegisteredClientDto;
import tech.saintbassanaga.authserver.mappers.RegisteredClientMapper;
import tech.saintbassanaga.authserver.repository.RegisteredClientRepository;
import tech.saintbassanaga.authserver.service.RegisteredService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisteredServiceImpl implements RegisteredService {

    private final RegisteredClientRepository registeredClientRepository;
    private final RegisteredClientMapper mapper;

    @Override
    public RegisteredClient registerClient(RegisteredClientDto client) {
        RegisteredClient entity = mapper.toEntity(client);
        return registeredClientRepository.save(entity);
    }

    @Override
    public Optional<RegisteredClientDto> findByClientId(String clientId) {
        return registeredClientRepository
                .findByClientId(clientId).map(mapper::toDto);
    }

    @Override
    public void deleteClient(String clientId) {
        registeredClientRepository.findByClientId(clientId)
                .ifPresent(registeredClientRepository::delete);
    }

    @Override
    public RegisteredClient updateClient(RegisteredClientDto client) {
        RegisteredClient entity = registeredClientRepository.findByClientId(client.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + client.clientId()));

        RegisteredClient updated = mapper.toEntity(client);
        registeredClientRepository.save(updated);
        return mapper.toEntity(client);
    }
}
