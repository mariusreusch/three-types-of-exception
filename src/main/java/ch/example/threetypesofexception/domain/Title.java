package ch.example.threetypesofexception.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Title {
    MR, MRS, UNKNOWN;

    public static Optional<Title> from(String titleAsString) {
        return Arrays.stream(Title.values())
                .filter(title -> title.name().equalsIgnoreCase(titleAsString))
                .findFirst();
    }
}
