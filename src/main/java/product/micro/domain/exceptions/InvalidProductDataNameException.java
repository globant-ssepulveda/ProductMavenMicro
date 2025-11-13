package product.micro.domain.exceptions;

public class InvalidProductDataNameException extends RuntimeException {
    public InvalidProductDataNameException(String message) {
        super(message);
    }
}
