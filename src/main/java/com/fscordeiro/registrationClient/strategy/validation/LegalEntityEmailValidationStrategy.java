package com.fscordeiro.registrationClient.strategy.validation;


import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.repository.LegalEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LegalEntityEmailValidationStrategy implements ClientValidationStrategy {

    private final LegalEntityRepository repository;

    @Override
    public ClientType getSupportedType() {
        return ClientType.LEGAL_ENTITY;
    }

    @Override
    public void validate(ClientRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new ClientException("A legal entity client with this email already exists");
        }
    }
}
