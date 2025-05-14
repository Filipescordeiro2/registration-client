package com.fscordeiro.registrationClient.factory;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.model.Individual;
import com.fscordeiro.registrationClient.model.IndividualAddress;
import com.fscordeiro.registrationClient.model.LegalEntity;
import com.fscordeiro.registrationClient.model.LegalEntityAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class ClientFactory {

    public Individual createIndividual(ClientRequest request) {
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
        log.info("Individual created successfully for CPF: {}", request.cpf());
        return individual;
    }

    public LegalEntity createLegalEntity(ClientRequest request) {
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
        log.info("LegalEntity created successfully for CNPJ: {}", request.cnpj());
        return legalEntity;
    }

}



