package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import org.springframework.stereotype.Component;

@Component
public class IndividualFieldValidationStrategy implements ClientValidationStrategy{

    @Override
    public ClientType getSupportedType() {
        return ClientType.INDIVIDUAL;
    }

    @Override
    public void validate(ClientRequest request) {
        if (request.cpf() == null) {
            throw new ClientException("CPF cannot be null");
        }
    }
}
