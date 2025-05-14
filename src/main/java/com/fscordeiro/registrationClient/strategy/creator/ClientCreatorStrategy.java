package com.fscordeiro.registrationClient.strategy.creator;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.model.Client;

public interface  ClientCreatorStrategy {
    ClientType getSupportedClientType();
    Client create(ClientRequest request);
}
