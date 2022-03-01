package ch.example.threetypesofexception.common.exceptions;

/**
 * A {@link ConsistencyException} should be thrown in case of programming errors or data inconsistencies. If you are not
 * sure if this is the right exception for you case, ask yourself the following question: "Who could fix this problem?".
 * If your answer is "The dev team", it would be a good indicator that you should go with {@link ConsistencyException}.
 * <p>
 * In respect to an onion architecture, this exception can be used in all layers.
 * <p>
 *
 * @see Problem
 */
public class ConsistencyException extends GenericException {

    public ConsistencyException(Problem problem) {
        super(problem);
    }
}
