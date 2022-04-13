package mk.ukim.finki.test.web.controller;

import mk.ukim.finki.test.model.Category;
import mk.ukim.finki.test.model.Manufacturer;
import mk.ukim.finki.test.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoriesPage(Model model) {
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewCategory(Model model) {
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("bodyContent", "add_category");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCategory(
            @RequestParam String name,
            @RequestParam String description)
    {
        this.categoryService.create(name, description);
        return "redirect:/categories";
    }
}
