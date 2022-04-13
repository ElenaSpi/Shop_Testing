package mk.ukim.finki.test.service;

import mk.ukim.finki.test.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
    Optional<Category> findByID(Long ID);
}
