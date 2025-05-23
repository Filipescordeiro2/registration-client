package com.fscordeiro.registrationClient.strategy.validation;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;

public interface ClientValidationStrategy {
    ClientType getSupportedType();
    void validate(ClientRequest request);
}
