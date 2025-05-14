package com.fscordeiro.registrationClient.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressRequest(
        @NotBlank(message = "Postal code is required")
        String postalCode,
        @NotBlank(message = "Street is required")
        String street,
        @NotNull(message = "Number is required")
        Long number,
        @NotBlank(message = "Complement is required")
        String complement,
        @NotBlank(message = "Neighborhood is required")
        String neighborhood,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "State is required")
        String state
) {}


