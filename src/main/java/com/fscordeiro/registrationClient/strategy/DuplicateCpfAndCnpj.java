package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.repository.IndividualRepository;
import com.fscordeiro.registrationClient.repository.LegalEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.fscordeiro.registrationClient.dto.request.ClientRequest;

@Component
@Slf4j
@RequiredArgsConstructor
public class DuplicateCpfAndCnpj implements ClientValidator {

    private final IndividualRepository individualRepository;
    private final LegalEntityRepository legalEntityRepository;

    @Override
    public void validate(ClientRequest request) {
        log.info("Validating CPF or CNPJ for client type: {}", request.clientType());

        if (request.clientType() == ClientType.INDIVIDUAL) {
            log.debug("Checking if CPF exists: {}", request.cpf());
            if (individualRepository.existsByCpf(request.cpf())) {
                log.warn("Duplicate CPF found: {}", request.cpf());
                throw new ClientException("An individual client with this CPF already exists");
            }
        }

        if (request.clientType() == ClientType.LEGAL_ENTITY) {
            log.debug("Checking if CNPJ exists: {}", request.cnpj());
            if (legalEntityRepository.existsByCnpj(request.cnpj())) {
                log.warn("Duplicate CNPJ found: {}", request.cnpj());
                throw new ClientException("A legal entity client with this CNPJ already exists");
            }
        }
    }
}


