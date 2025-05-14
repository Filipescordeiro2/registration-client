package com.fscordeiro.registrationClient.dto.request;

import com.fscordeiro.registrationClient.enums.ClientType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Builder
public record ClientRequest(
        @CNPJ(message = "Invalid CNPJ")
        String cnpj,
        @CPF(message = "Invalid CPF")
        String cpf,
        @CPF(message = "Invalid CPF")
        String responsibleCpf,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Phone number is required")
        String phone,
        @NotBlank(message = "Mobile number is required")
        String mobile,
        @NotBlank(message = "Email is required")
        String email,
        @NotNull(message = "Client type is required")
        ClientType clientType,
        @NotNull(message = "Address is required")
        List<AddressRequest> address
) {}



