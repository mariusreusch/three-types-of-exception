package ch.example.threetypesofexception.interfaces.restexceptionhandling;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime errorTime,
        String errorCode,
        String errorMessage,
        String correlationId) {
}
