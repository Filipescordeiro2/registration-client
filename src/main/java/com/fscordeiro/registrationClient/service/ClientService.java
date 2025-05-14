package com.fscordeiro.registrationClient.service;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.dto.response.ClientResponse;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.factory.ClientFactory;
import com.fscordeiro.registrationClient.repository.ClientRepository;
import com.fscordeiro.registrationClient.strategy.ClientValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientValidation clientValidation;
    private final ClientFactory clientFactory;

    public ClientResponse registerClient(ClientRequest clientRequest) {
        log.info("Starting service [registerClient] for client type: {}", clientRequest.clientType());
        try {
            clientValidation.validate(clientRequest);

            var client = clientRequest.clientType() == ClientType.INDIVIDUAL
                    ? clientFactory.createIndividual(clientRequest)
                    : clientFactory.createLegalEntity(clientRequest);

            clientRepository.save(client);

            log.info("Finished service [registerClient] successfully");
            return ClientResponse.builder()
                    .message("Client successfully registered!")
                    .createdAt(LocalDateTime.now())
                    .build();
        } catch (ClientException e) {
            log.warn("Known client exception occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during client registration: {}", e.getMessage(), e);
            throw new ClientException("Error: " + e.getMessage());
        }
    }
}


