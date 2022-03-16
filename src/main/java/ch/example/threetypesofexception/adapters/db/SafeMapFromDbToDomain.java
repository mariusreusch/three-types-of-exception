package ch.example.threetypesofexception.adapters.db;

import ch.example.threetypesofexception.domain.exceptions.BusinessException;
import ch.example.threetypesofexception.domain.exceptions.ConsistencyException;

import java.util.function.Function;

public abstract class SafeMapFromDbToDomain {
    public static <S, D> D safeMapFromDatabaseToDomain(Function<S, D> mapFunction, S source) {
        try {
            return mapFunction.apply(source);
        } catch (BusinessException e) {
            throw new ConsistencyException(e.getProblem());
        }

    }
}