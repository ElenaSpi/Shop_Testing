package mk.ukim.finki.test.web.controller;

import mk.ukim.finki.test.model.Category;
import mk.ukim.finki.test.model.Manufacturer;
import mk.ukim.finki.test.model.Product;
import mk.ukim.finki.test.service.CategoryService;
import mk.ukim.finki.test.service.ManufacturerService;
import mk.ukim.finki.test.service.ProductService;
import org.apache.bcel.verifier.statics.DOUBLE_Upper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewProduct(Model model) {
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        model.addAttribute("bodyContent", "add_product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam (required = false) Integer quantity,
            @RequestParam Long category,
            @RequestParam Long manufacturer)
    {
        Category _category = this.categoryService.findByID(category).get();
        Manufacturer _manufacturer = this.manufacturerService.findById(manufacturer).get();
        this.productService.save(name,price,quantity,_category.getId(),_manufacturer.getId());
        return "redirect:/products";
    }


    @PostMapping("/edit")
    public String editBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam Double price,
                           @RequestParam (required = false) Integer quantity,
                           @RequestParam Long category,
                           @RequestParam Long manufacturer) {
        Category _category = this.categoryService.findByID(category).get();
        Manufacturer _manufacturer = this.manufacturerService.findById(manufacturer).get();
        this.productService.edit(id, name, price,quantity,_category.getId(), _manufacturer.getId());
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBook(@PathVariable Long id, Model model) {
        if(this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("categories", this.categoryService.listCategories());
            model.addAttribute("manufacturers", this.manufacturerService.findAll());
            model.addAttribute("bodyContent", "edit_product");
            return "master-template";
        }
        return "redirect:/products";
    }
}
