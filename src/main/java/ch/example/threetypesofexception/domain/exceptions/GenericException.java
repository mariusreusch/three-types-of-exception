package ch.example.threetypesofexception.domain.exceptions;

abstract class GenericException extends RuntimeException {

    private final Problem problem;

    public GenericException(Problem problem) {
        super(problem.code + " " + problem.message);
        this.problem = problem;
    }

    public GenericException(Problem problem, Throwable cause) {
        super(problem.code + " " + problem.message, cause);
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }
}
