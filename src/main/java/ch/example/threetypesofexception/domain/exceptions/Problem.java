package ch.example.threetypesofexception.domain.exceptions;

public enum Problem {

    UNKNOWN_USECASE("TTOP-0000"),
    CUSTOMER_NAME_MUST_HAVE_AT_LEAST_TWO_CHARACTERS("TTOP-0001"),
    UNKNOWN_TITLE("TTOP-0002"),
    CUSTOMER_DATABASE_IS_NOT_AVAILABLE("TTOP-0003"),
    CUSTOMER_ID_INVALID("TTOP-0004");


    //The problem code is a unique identifier for each problem. Once published, it must not be changed, as the code is
    //also sent back to clients in case of an exception and is therefore part of the interface contract. It is similar
    //to Oracle's ORA-XX codes. You could also start the code with your project's shortname (TTOP = Three Types Of Exception).
    public final String code;
    public final String message;

    Problem(String code) {
        this.code = code;
        this.message = this.name().replaceAll("_", " ");
    }
}
