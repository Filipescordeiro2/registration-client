package com.fscordeiro.registrationClient.strategy.creator;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientCreator {

    private final List<ClientCreatorStrategy> strategies;

    public Client create(ClientRequest request) {
        return strategies.stream()
                .filter(s -> s.getSupportedClientType().equals(request.clientType()))
                .findFirst()
                .orElseThrow(() -> new ClientException("No strategy found for client type: " + request.clientType()))
                .create(request);
    }
}
