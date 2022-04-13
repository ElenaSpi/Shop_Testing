package mk.ukim.finki.test.model;

import lombok.Data;
import mk.ukim.finki.test.model.enumerations.ShoppingCartStatus;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToMany
    private List<Product> products;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.status = ShoppingCartStatus.CREATED;
        this.products = new ArrayList<>();
    }
}
