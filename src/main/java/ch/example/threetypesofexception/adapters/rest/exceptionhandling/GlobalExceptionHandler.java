package ch.example.threetypesofexception.adapters.rest.exceptionhandling;

import ch.example.threetypesofexception.domain.exceptions.BusinessException;
import ch.example.threetypesofexception.domain.exceptions.ConsistencyException;
import ch.example.threetypesofexception.domain.exceptions.Problem;
import ch.example.threetypesofexception.domain.exceptions.SystemException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/unknown")
    public ErrorResponseDto handleError() {
        return mapToErrorResponse(Problem.UNKNOWN_USECASE);
    }

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
