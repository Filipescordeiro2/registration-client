package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import org.springframework.stereotype.Component;

@Component
public class LegalEntityFieldValidationStrategy implements ClientValidationStrategy {


    @Override
    public ClientType getSupportedType() {
        return ClientType.LEGAL_ENTITY;
    }

    @Override
    public void validate(ClientRequest request) {
        if (request.cnpj() == null) {
            throw new ClientException("CNPJ cannot be null");
        }
        if (request.responsibleCpf() == null) {
            throw new ClientException("Responsible CPF cannot be null");
        }
    }
}
