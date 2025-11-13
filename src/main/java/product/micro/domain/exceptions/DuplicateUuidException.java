package product.micro.domain.exceptions;

public class DuplicateUuidException extends RuntimeException {
    public DuplicateUuidException(String message) {
        super(message);
    }
}
