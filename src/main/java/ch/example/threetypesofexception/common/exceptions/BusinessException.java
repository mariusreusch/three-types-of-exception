package ch.example.threetypesofexception.common.exceptions;

/**
 * A {@link BusinessException} should be thrown in case of a violated business rule. If you are not sure if this is the
 * right exception for you case, ask yourself the following question: "Who could fix this problem?". If your answer is
 * "the client or user", it would be a good indicator that you should go with {@link BusinessException}.
 * <p>
 * In respect to an onion architecture, this exception should only be used in domain an application (use case) rings.
 * <p>
 *
 * @see Problem
 */
public class BusinessException extends GenericException {

    public BusinessException(Problem problem) {
        super(problem);
    }
}
