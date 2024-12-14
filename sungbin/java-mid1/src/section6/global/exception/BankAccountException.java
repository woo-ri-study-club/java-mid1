package section6.global.exception;

public class BankAccountException extends RuntimeException {
    public BankAccountException(String message) {
        super(message);
    }
}
