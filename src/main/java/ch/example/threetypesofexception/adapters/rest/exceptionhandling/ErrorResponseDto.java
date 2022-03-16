package ch.example.threetypesofexception.adapters.rest.exceptionhandling;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime errorTime,
        String errorCode,
        String errorMessage,
        String correlationId) {
}
