package mk.ukim.finki.test.service.impl;

import mk.ukim.finki.test.model.Category;
import mk.ukim.finki.test.model.exceptions.InvalidArguments;
import mk.ukim.finki.test.repository.CategoryRepository;
import mk.ukim.finki.test.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if(name == null || name.isEmpty())  {
            throw new InvalidArguments();
        }
        Category c = new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if(name == null || name.isEmpty()) {
            throw new InvalidArguments();
        }
        Category c = new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name == null || name.isEmpty()) {
            throw new InvalidArguments();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return this.categoryRepository.findAllByNameLike(searchText);
    }

    @Override
    public Optional<Category> findByID(Long ID) {
        return this.categoryRepository.findById(ID);
    }
}