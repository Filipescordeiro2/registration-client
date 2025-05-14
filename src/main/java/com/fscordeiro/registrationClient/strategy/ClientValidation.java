package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientValidation {

    private final List<ClientValidationStrategy> strategies;

    public void validate(ClientRequest request) {
        log.info("Starting client validation for: {}", request);

        strategies.stream()
                .filter(strategy -> strategy.getSupportedType() == request.clientType())
                .forEach(strategy -> {
                    log.debug("Executing strategy: {}", strategy.getClass().getSimpleName());
                    strategy.validate(request);
                });

        log.info("Client validation completed for: {}", request);
    }
}
