package ch.example.threetypesofexception.domain.entities;

import ch.example.threetypesofexception.domain.exceptions.BusinessException;
import ch.example.threetypesofexception.domain.exceptions.Problem;

public record CustomerName(String value) {

    public CustomerName {
        if (value == null || value.length() < 2) {
            throw new BusinessException(Problem.CUSTOMER_NAME_MUST_HAVE_AT_LEAST_TWO_CHARACTERS);
        }
    }
}
