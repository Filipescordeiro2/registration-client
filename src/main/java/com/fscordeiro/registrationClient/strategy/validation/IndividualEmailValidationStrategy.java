package com.fscordeiro.registrationClient.strategy.validation;


import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.repository.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndividualEmailValidationStrategy implements ClientValidationStrategy{

    private final IndividualRepository repository;

    @Override
    public ClientType getSupportedType() {
        return ClientType.INDIVIDUAL;
    }

    @Override
    public void validate(ClientRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new ClientException("An individual client with this email already exists");
        }
    }
}
