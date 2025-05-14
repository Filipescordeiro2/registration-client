package com.fscordeiro.registrationClient.strategy.creator;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.model.Client;
import com.fscordeiro.registrationClient.model.Individual;
import com.fscordeiro.registrationClient.model.IndividualAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class IndividualClientCreator implements ClientCreatorStrategy{
    @Override
    public ClientType getSupportedClientType() {
        return ClientType.INDIVIDUAL;
    }

    @Override
    public Client create(ClientRequest request) {
        log.info("Creating Individual for CPF: {}", request.cpf());

        var individual = Individual.builder()
                .cpf(request.cpf())
                .name(request.name())
                .phone(request.phone())
                .mobile(request.mobile())
                .email(request.email())
                .clientType(ClientType.INDIVIDUAL)
                .build();
        var addresses = request.address().stream()
                .map(e -> {
                    log.debug("Mapping address for Individual: ZIP {}", e.postalCode());
                    return IndividualAddress.builder()
                            .zipCode(e.postalCode())
                            .city(e.city())
                            .state(e.state())
                            .number(e.number())
                            .complement(e.complement())
                            .neighborhood(e.neighborhood())
                            .street(e.street())
                            .individual(individual)
                            .build();
                })
                .collect(Collectors.toList());

        individual.setAddresses(addresses);
        return individual;
    }
}
