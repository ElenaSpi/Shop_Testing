package mk.ukim.finki.test.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    @ManyToOne
    private Manufacturer manufacturer;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String name, Double price, Integer quantity, Manufacturer manufacturer, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}
