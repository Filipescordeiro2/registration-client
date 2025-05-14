package com.fscordeiro.registrationClient.strategy.creator;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.model.Client;
import com.fscordeiro.registrationClient.model.LegalEntity;
import com.fscordeiro.registrationClient.model.LegalEntityAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class LegalEntityClientCreator implements ClientCreatorStrategy {

    @Override
    public ClientType getSupportedClientType() {
        return ClientType.LEGAL_ENTITY;
    }

    @Override
    public Client create(ClientRequest request) {
        log.info("Creating LegalEntity for CNPJ: {}", request.cnpj());

        var legalEntity = LegalEntity.builder()
                .cnpj(request.cnpj())
                .responsibleCpf(request.responsibleCpf())
                .name(request.name())
                .phone(request.phone())
                .mobile(request.mobile())
                .email(request.email())
                .clientType(ClientType.LEGAL_ENTITY)
                .build();

        var addresses = request.address().stream()
                .map(e -> {
                    log.debug("Mapping address for LegalEntity: ZIP {}", e.postalCode());
                    return LegalEntityAddress.builder()
                            .zipCode(e.postalCode())
                            .city(e.city())
                            .state(e.state())
                            .number(e.number())
                            .complement(e.complement())
                            .neighborhood(e.neighborhood())
                            .street(e.street())
                            .legalEntity(legalEntity)
                            .build();
                })
                .collect(Collectors.toList());

        legalEntity.setAddresses(addresses);
        return legalEntity;
    }
}
