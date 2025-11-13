package product.micro.domain.exceptions;

public class DuplicateFantasyNameException extends RuntimeException {
    public DuplicateFantasyNameException(String message) {
        super(message);
    }
}
