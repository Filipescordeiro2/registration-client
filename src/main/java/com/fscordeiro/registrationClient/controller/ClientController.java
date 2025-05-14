package com.fscordeiro.registrationClient.controller;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.dto.response.ClientResponse;
import com.fscordeiro.registrationClient.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@Valid @RequestBody ClientRequest clientRequest) {
        log.info("Starting endpoint [createClient] with request: {}", clientRequest);
        var clientResponse = clientService.registerClient(clientRequest);
        log.info("Finished endpoint [createClient] with response: {}", clientResponse);
        return clientResponse;
    }
}
