package mk.ukim.finki.test.model.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Long id) {
        super(String.format("Product with ID %d was not found!", id));
    }
}
