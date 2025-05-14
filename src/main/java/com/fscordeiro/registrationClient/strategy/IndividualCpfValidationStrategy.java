package com.fscordeiro.registrationClient.strategy;

import com.fscordeiro.registrationClient.dto.request.ClientRequest;
import com.fscordeiro.registrationClient.enums.ClientType;
import com.fscordeiro.registrationClient.exception.ClientException;
import com.fscordeiro.registrationClient.repository.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndividualCpfValidationStrategy implements ClientValidationStrategy{

    private final IndividualRepository repository;

    @Override
    public ClientType getSupportedType() {
        return ClientType.INDIVIDUAL;
    }

    @Override
    public void validate(ClientRequest request) {
        if (repository.existsByCpf(request.cpf())) {
            throw new ClientException("An individual client with this CPF already exists");
        }
    }
}
