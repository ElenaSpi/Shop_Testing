package mk.ukim.finki.test.service;

import mk.ukim.finki.test.model.Product;
import mk.ukim.finki.test.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price,
                           Integer quantity, Long categoryID,
                           Long manufacturerID);
    Optional<Product> save(ProductDto productDto);
    Optional<Product> edit(Long id, String name, Double price,
                           Integer quantity, Long categoryID,
                           Long manufacturerID);
    Optional<Product> edit(Long id, ProductDto productDto);
    void deleteById(Long id);

    void refreshMaterializedView();
    List<Product> listProductsByNameAndCategory(String name, Long categoryId);
}