package com.fscordeiro.registrationClient.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClientResponse(String message, LocalDateTime createdAt) {

}
