package product.micro.domain.exceptions;

public class InvalidProductDataPriceException extends RuntimeException {
    public InvalidProductDataPriceException(String message) {
        super(message);
    }
}
