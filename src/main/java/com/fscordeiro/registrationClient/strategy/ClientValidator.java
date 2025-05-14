package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;

public interface ClientValidator {
    void validate(ClientRequest request);
}
