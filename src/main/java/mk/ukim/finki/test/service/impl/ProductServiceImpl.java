package mk.ukim.finki.test.service.impl;

import mk.ukim.finki.test.model.Category;
import mk.ukim.finki.test.model.Manufacturer;
import mk.ukim.finki.test.model.Product;
import mk.ukim.finki.test.model.dto.ProductDto;
import mk.ukim.finki.test.model.exceptions.CategoryNotFound;
import mk.ukim.finki.test.model.exceptions.ManufacturerNotFound;
import mk.ukim.finki.test.model.exceptions.ProductNotFound;
import mk.ukim.finki.test.repository.CategoryRepository;
import mk.ukim.finki.test.repository.ManufacturerRepository;
import mk.ukim.finki.test.repository.ProductRepository;
import mk.ukim.finki.test.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price,
                                  Integer quantity,
                                  Long categoryID,
                                  Long manufacturerID) {
    Category cat = this.categoryRepository.findById(categoryID)
            .orElseThrow(() -> new CategoryNotFound(categoryID));
    Manufacturer manu = this.manufacturerRepository.findById(manufacturerID)
            .orElseThrow(() -> new ManufacturerNotFound(manufacturerID));

    this.productRepository.deleteByName(name);
    Product p = new Product(name, price, quantity, manu, cat);
    this.productRepository.save(p);
    return Optional.of(p);
    }


    @Override
    public Optional<Product> save(ProductDto productDto) {
        Category cat = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFound(productDto.getCategory()));
        Manufacturer manu = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFound(productDto.getManufacturer()));

        this.productRepository.deleteByName(productDto.getName());
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), manu, cat);
        this.productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> edit(Long id, String name,
                                  Double price,
                                  Integer quantity,
                                  Long categoryID,
                                  Long manufacturerID) {
    Product product = this.productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFound(id));

    product.setName(name);
    product.setPrice(price);
    product.setQuantity(quantity);

    Category category = this.categoryRepository.findById(categoryID)
            .orElseThrow(() -> new CategoryNotFound(categoryID));
    product.setCategory(category);

    Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerID)
            .orElseThrow(() -> new ManufacturerNotFound(manufacturerID));
    product.setManufacturer(manufacturer);

    this.productRepository.save(product);
    return Optional.of(product);
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound(id));

        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());

        Category cat = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFound(productDto.getCategory()));
        product.setCategory(cat);

        Manufacturer manu = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFound(productDto.getManufacturer()));
        product.setManufacturer(manu);

        this.productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        System.out.println("TODO");
    }

    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        //return this.productRepository.listProductsByNameAndCategory(name, categoryId);
        return this.productRepository.findAll();
    }
}