package mk.ukim.finki.test.model.exceptions;

public class ProductAlreadyInShoppingCart extends RuntimeException {
    public ProductAlreadyInShoppingCart(Long productId, String username) {
        super(String.format("The product with ID %d already exists in the cart made by %s",
                productId, username));
    }
}
