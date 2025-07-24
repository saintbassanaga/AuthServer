package tech.saintbassanaga.authserver.mappers;

import org.mapstruct.*;
import tech.saintbassanaga.authserver.domains.RegisteredClient;
import tech.saintbassanaga.authserver.dtos.RegisteredClientDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegisteredClientMapper {
    RegisteredClient toEntity(RegisteredClientDto registeredClientDto);

    RegisteredClientDto toDto(RegisteredClient registeredClient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RegisteredClient partialUpdate(RegisteredClientDto registeredClientDto, @MappingTarget RegisteredClient registeredClient);
}