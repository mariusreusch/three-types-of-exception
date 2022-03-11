package ch.example.threetypesofexception.domain.entities;


import ch.example.threetypesofexception.domain.exceptions.BusinessException;
import ch.example.threetypesofexception.domain.exceptions.Problem;

import java.util.Arrays;

public enum Title {
    MR, MRS, UNKNOWN;

    public static Title from(String titleAsString) {
        return Arrays.stream(Title.values())
                .filter(title -> title.name().equalsIgnoreCase(titleAsString))
                .findFirst()
                .orElseThrow(() -> new BusinessException(Problem.UNKNOWN_TITLE));
    }
}
