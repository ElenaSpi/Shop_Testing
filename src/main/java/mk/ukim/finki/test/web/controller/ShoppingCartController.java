package mk.ukim.finki.test.web.controller;


import mk.ukim.finki.test.model.ShoppingCart;
import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest request,
                                      Model model) {
        String username = request.getRemoteUser();
        ShoppingCart cart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products", this.shoppingCartService.listAllProductsInCart(cart.getId()));
        model.addAttribute("bodyContent", "shopping_cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
        public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addProductToShoppingCart(user.getUsername(),id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException e) {
            return "redirect:/shopping-cart?error=" + e.getMessage();
        }
    }
}