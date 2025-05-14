package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.repository.IndividualRepository;
import com.fscordeiro.registrationClient.repository.LegalEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DuplicateEmail implements ClientValidator {

    private final IndividualRepository individualRepository;
    private final LegalEntityRepository legalEntityRepository;

    @Override
    public void validate(ClientRequest request) {
        log.info("Validating email for client type: {}", request.clientType());

        if (request.clientType() == ClientType.INDIVIDUAL) {
            log.debug("Checking if email exists: {}", request.email());
            if (individualRepository.existsByEmail(request.email())) {
                log.warn("Duplicate email found for Individual: {}", request.email());
                throw new ClientException("An individual client with this email already exists");
            }
        }
        if (request.clientType() == ClientType.LEGAL_ENTITY) {
            log.debug("Checking if email exists: {}", request.email());
            if (legalEntityRepository.existsByCnpj(request.cnpj())) {
                log.warn("Duplicate email found for Legal Entity: {}", request.email());
                throw new ClientException("A legal entity client with this email already exists");
            }
        }
    }
}


