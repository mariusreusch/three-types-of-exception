package ch.example.threetypesofexception.interfaces.restexceptionhandling;

import ch.example.threetypesofexception.common.exceptions.BusinessException;
import ch.example.threetypesofexception.common.exceptions.ConsistencyException;
import ch.example.threetypesofexception.common.exceptions.Problem;
import ch.example.threetypesofexception.common.exceptions.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBusinessException(BusinessException businessException) {
        return mapToErrorResponse(businessException.getProblem());
    }

    @ExceptionHandler(ConsistencyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleConsistencyException(ConsistencyException consistencyException) {
        return mapToErrorResponse(consistencyException.getProblem());
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponseDto handleSystemException(SystemException systemException) {
        return mapToErrorResponse(systemException.getProblem());
    }

    private ErrorResponseDto mapToErrorResponse(Problem systemException) {
        return new ErrorResponseDto(
                LocalDateTime.now(),
                systemException.code,
                systemException.message,
                "put-correlation-id-here"
        );
    }
}
