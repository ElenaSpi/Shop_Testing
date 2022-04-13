package mk.ukim.finki.test.model.exceptions;

public class ShoppingCartNotFound extends RuntimeException {
    public ShoppingCartNotFound(Long id) {
        super(String.format
                ("The shopping cart with ID %d was not found!", id));
    }
}
