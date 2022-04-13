package mk.ukim.finki.test.repository;

import mk.ukim.finki.test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    //List<Product> listProductsByNameAndCategory(String name, Long categoryId);
    void deleteByName(String name);
}
