package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FieldsValidation implements ClientValidator {

    @Override
    public void validate(ClientRequest request) {
        if (request == null) {
            throw new ClientException("The request body cannot be null");
        }
        if (request.clientType() == ClientType.INDIVIDUAL) {
            if (request.cpf() == null) {
                throw new ClientException("CPF cannot be null");
            }
        }
        if (request.clientType() == ClientType.LEGAL_ENTITY) {
            if (request.cnpj() == null) {
                throw new ClientException("CNPJ cannot be null");
            }
            if (request.responsibleCpf() == null) {
                throw new ClientException("Responsible CPF cannot be null");
            }
        }
    }
}

