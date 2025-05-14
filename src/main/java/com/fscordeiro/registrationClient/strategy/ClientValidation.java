package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientValidation {

    private final List<ClientValidator> validators;

    public void validate(ClientRequest request) {
        log.info("Starting client validation for: {}", request);

        validators.forEach(validator -> {
            log.debug("Running validator: {}", validator.getClass().getSimpleName());
            validator.validate(request);
        });

        log.info("Client validation completed for: {}", request);
    }
}


