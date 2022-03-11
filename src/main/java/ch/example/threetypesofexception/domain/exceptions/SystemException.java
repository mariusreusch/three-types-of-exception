package ch.example.threetypesofexception.domain.exceptions;

/**
 * A {@link SystemException} should be thrown in case of any problem with a peripheral system
 * (including databases and queues). If you are not sure if this is the right exception for you case, ask yourself the
 * following question: "Who could fix this problem?". If your answer is "Ops team", it would be a good indicator
 * that you should go with {@link SystemException}.
 * <p>
 * In respect to an onion architecture, this exception should only be used in the infrastructure / interfaces layer.
 * <p>
 *
 * @see Problem
 */
public class SystemException extends GenericException {

    public SystemException(Problem problem) {
        super(problem);
    }

    public SystemException(Problem problem, Throwable cause) {
        super(problem, cause);
    }
}
