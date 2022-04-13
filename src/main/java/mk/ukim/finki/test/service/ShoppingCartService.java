package mk.ukim.finki.test.service;

import mk.ukim.finki.test.model.Product;
import mk.ukim.finki.test.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
